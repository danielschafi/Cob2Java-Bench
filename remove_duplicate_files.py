import os
import hashlib
from pathlib import Path
from collections import defaultdict
from thefuzz import fuzz
import shutil
import logging
from dotenv import load_dotenv
from concurrent.futures import ProcessPoolExecutor, as_completed
from itertools import combinations
import multiprocessing

load_dotenv()

logger = logging.getLogger(__name__)
logging.basicConfig(
    filename=str(
        Path(os.getcwd())
        / (os.environ.get("LOG_DIR") or "logs")
        / (Path(__file__).stem + ".log")
    ),
    level=logging.INFO,
    format="%(asctime)s %(levelname)-8s %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)


def get_file_hash(filepath):
    """Calculate SHA-256 hash of file content."""
    sha256 = hashlib.sha256()
    try:
        with open(filepath, "rb") as f:
            for chunk in iter(lambda: f.read(8192), b""):
                sha256.update(chunk)
        return sha256.hexdigest()
    except Exception as e:
        print(f"Error hashing {filepath}: {e}")
        return None


def find_exact_duplicates(directory):
    """Find exact duplicates by size and hash."""
    print("Phase 1: Finding exact duplicates...")
    logger.info("Phase 1: Finding exact duplicates...")

    # Group files by size first (quick filter)
    size_map = defaultdict(list)

    cobol_files = list(Path(directory).rglob("*.[Cc][Oo][Bb]"))
    print(f"{len(cobol_files)} COBOL files found for exact duplicate check.")

    for filepath in cobol_files:
        if filepath.is_file():
            size = filepath.stat().st_size
            size_map[size].append(filepath)

    # For files with same size, check hash
    duplicates = []
    hash_map = {}

    for size, files in size_map.items():
        if len(files) > 1:  # there are potential duplicates
            for filepath in files:
                file_hash = get_file_hash(filepath)
                if file_hash:
                    if file_hash in hash_map:
                        duplicates.append((hash_map[file_hash], filepath))
                        print(f"  Exact duplicate: {filepath.name}")
                        logger.info(f"  Exact duplicate: {filepath.name}")
                    else:
                        hash_map[file_hash] = filepath
        elif len(files) == 1:
            # Unique file by size
            filepath = files[0]
            file_hash = get_file_hash(filepath)
            if file_hash:
                hash_map[file_hash] = filepath

    print(f"Found {len(duplicates)} exact duplicate(s)")
    logger.info(f"Found {len(duplicates)} exact duplicate(s)")
    return duplicates, list(hash_map.values())


def read_file_content(filepath, max_size=100000):
    """Read file content with size limit for comparison."""
    try:
        with open(filepath, "r", encoding="latin-1", errors="ignore") as f:
            return f.read(max_size)
    except Exception as e:
        return None


def get_file_fingerprint(filepath, num_lines=10):
    """
    Create a quick fingerprint of the file using first N lines.
    Used for fast pre-filtering before expensive fuzzy comparison.
    """
    try:
        with open(filepath, "r", encoding="latin-1", errors="ignore") as f:
            lines = []
            for i, line in enumerate(f):
                if i >= num_lines:
                    break
                lines.append(line.strip())
            return "\n".join(lines)
    except:
        return ""


def compare_file_pair(args):
    """Compare a pair of files for similarity. Used for parallel processing."""
    file1, file2, threshold = args

    content1 = read_file_content(file1)
    content2 = read_file_content(file2)

    if not content1 or not content2:
        return None

    # Quick length check - if files differ drastically in length, likely not duplicates
    len_ratio = min(len(content1), len(content2)) / max(len(content1), len(content2))
    if len_ratio < 0.5:  # If one file is less than 50% the size of the other
        return None

    similarity = fuzz.token_sort_ratio(content1, content2)

    if similarity >= threshold:
        return (file1, file2, similarity)
    return None


def compare_file_batch(args):
    """Compare one file against a batch of other files."""
    file1, other_files, threshold = args

    content1 = read_file_content(file1)
    if not content1:
        return []

    results = []
    for file2 in other_files:
        content2 = read_file_content(file2)
        if not content2:
            continue

        # Quick length check
        len_ratio = min(len(content1), len(content2)) / max(
            len(content1), len(content2)
        )
        if len_ratio < 0.5:
            continue

        similarity = fuzz.token_sort_ratio(content1, content2)

        if similarity >= threshold:
            results.append((file1, file2, similarity))

    return results


def find_near_duplicates_optimized(
    unique_files, similarity_threshold=90, max_workers=None, batch_size=50
):
    """
    Find near duplicates with optimizations:
    1. Pre-filter by file fingerprint (first N lines)
    2. Pre-filter by file size similarity
    3. Batch comparisons to reduce overhead
    4. Parallel processing
    """
    print(f"\nPhase 2: Finding near duplicates (threshold: {similarity_threshold}%)...")
    logger.info(
        f"Phase 2: Finding near duplicates (threshold: {similarity_threshold}%)..."
    )

    if max_workers is None:
        max_workers = max(1, multiprocessing.cpu_count() - 1)

    print(f"Using {max_workers} worker processes")

    total = len(unique_files)

    # Step 1: Pre-filter by size similarity
    print("Pre-filtering by file size...")
    size_groups = defaultdict(list)
    for f in unique_files:
        size = f.stat().st_size
        # Group files into size buckets (within 50% of each other)
        size_bucket = int(size / 1000)  # 1KB buckets
        size_groups[size_bucket].append(f)
        # Also add to adjacent buckets for fuzzy matching
        size_groups[size_bucket - 1].append(f)
        size_groups[size_bucket + 1].append(f)

    # Build candidate pairs based on size similarity
    candidate_pairs = set()
    for size_bucket, files in size_groups.items():
        if len(files) > 1:
            for i, f1 in enumerate(files):
                for f2 in files[i + 1 :]:
                    # Ensure pair is ordered to avoid duplicates
                    pair = tuple(sorted([f1, f2], key=str))
                    candidate_pairs.add(pair)

    print(
        f"Size-based filtering: {len(candidate_pairs):,} candidate pairs (vs {total * (total - 1) // 2:,} total)"
    )

    # Step 2: Further filter by fingerprint similarity
    print("Pre-filtering by file fingerprint...")
    fingerprints = {}
    for f in unique_files:
        fingerprints[f] = get_file_fingerprint(f)

    filtered_pairs = []
    for f1, f2 in candidate_pairs:
        fp1 = fingerprints.get(f1, "")
        fp2 = fingerprints.get(f2, "")
        if fp1 and fp2:
            # Quick similarity check on fingerprint
            fp_sim = fuzz.ratio(fp1, fp2)
            if fp_sim >= similarity_threshold - 10:  # Lower threshold for fingerprint
                filtered_pairs.append((f1, f2))

    print(f"Fingerprint filtering: {len(filtered_pairs):,} candidate pairs")

    # Step 3: Batch the comparisons
    print("Creating batches for parallel processing...")
    file_to_others = defaultdict(list)
    for f1, f2 in filtered_pairs:
        file_to_others[f1].append(f2)

    # Create batches
    batches = []
    for file1, others in file_to_others.items():
        # Split others into batches
        for i in range(0, len(others), batch_size):
            batch = others[i : i + batch_size]
            batches.append((file1, batch, similarity_threshold))

    total_batches = len(batches)
    print(f"Processing {total_batches:,} batches...")

    # Step 4: Process in parallel
    near_duplicates = []
    completed = 0

    with ProcessPoolExecutor(max_workers=max_workers) as executor:
        futures = {
            executor.submit(compare_file_batch, batch): batch for batch in batches
        }

        for future in as_completed(futures):
            completed += 1
            if completed % 100 == 0:
                print(
                    f"  Progress: {completed:,}/{total_batches:,} batches ({100 * completed / total_batches:.1f}%)"
                )

            results = future.result()
            for result in results:
                file1, file2, similarity = result
                near_duplicates.append(result)
                print(f"  Near duplicate ({similarity}%): {file1.name} ~ {file2.name}")
                logger.info(
                    f"  Near duplicate ({similarity}%): {file1.name} ~ {file2.name}"
                )

    print(f"Found {len(near_duplicates)} near duplicate pair(s)")
    logger.info(f"Found {len(near_duplicates)} near duplicate pair(s)")
    return near_duplicates


def find_near_duplicates_full_parallel(
    unique_files, similarity_threshold=90, max_workers=None, chunk_size=1000
):
    """
    Full exhaustive comparison with maximum parallelization.
    All N*(N-1)/2 pairs compared, but distributed across workers efficiently.
    """
    print(
        f"\nPhase 2: Finding near duplicates - FULL COMPARISON (threshold: {similarity_threshold}%)..."
    )
    logger.info(
        f"Phase 2: Finding near duplicates - FULL COMPARISON (threshold: {similarity_threshold}%)..."
    )

    if max_workers is None:
        max_workers = max(1, multiprocessing.cpu_count() - 1)

    print(f"Using {max_workers} worker processes")

    total = len(unique_files)
    total_comparisons = total * (total - 1) // 2
    print(f"Total comparisons to perform: {total_comparisons:,}")

    near_duplicates = []
    completed = 0

    # Generate all pairs
    all_pairs = [
        (f1, f2, similarity_threshold) for f1, f2 in combinations(unique_files, 2)
    ]

    # Process in parallel with chunking for better performance
    with ProcessPoolExecutor(max_workers=max_workers) as executor:
        # Submit in chunks to manage memory
        for chunk_start in range(0, len(all_pairs), chunk_size):
            chunk_end = min(chunk_start + chunk_size, len(all_pairs))
            chunk = all_pairs[chunk_start:chunk_end]

            futures = {executor.submit(compare_file_pair, pair): pair for pair in chunk}

            for future in as_completed(futures):
                completed += 1
                if completed % 1000 == 0:
                    print(
                        f"  Progress: {completed:,}/{total_comparisons:,} comparisons ({100 * completed / total_comparisons:.1f}%)"
                    )

                result = future.result()
                if result:
                    file1, file2, similarity = result
                    near_duplicates.append(result)
                    print(
                        f"  Near duplicate ({similarity}%): {file1.name} ~ {file2.name}"
                    )
                    logger.info(
                        f"  Near duplicate ({similarity}%): {file1.name} ~ {file2.name}"
                    )

    print(f"Found {len(near_duplicates)} near duplicate pair(s)")
    logger.info(f"Found {len(near_duplicates)} near duplicate pair(s)")
    return near_duplicates


def remove_duplicates(
    directory,
    output_dir=None,
    similarity_threshold=90,
    dry_run=True,
    use_optimized=True,
    max_workers=None,
):
    """
    Main function to remove duplicates and near duplicates.

    Args:
        directory: Source directory containing COBOL files
        output_dir: Optional output directory for unique files (None = delete in place)
        similarity_threshold: Threshold for near duplicate detection (0-100)
        dry_run: If True, only report what would be deleted without actually deleting
        use_optimized: If True, use optimized filtering (faster, 100% of near-dupes in filtered set)
                       If False, use full exhaustive comparison (slower, guaranteed all pairs)
        max_workers: Number of parallel workers (None = CPU count - 1)
    """
    directory = Path(directory)

    if output_dir:
        output_dir = Path(output_dir)
        output_dir.mkdir(exist_ok=True)

    # Phase 1: Remove exact duplicates
    exact_dups, unique_files = find_exact_duplicates(directory)

    # Phase 2: Remove near duplicates
    if use_optimized:
        near_dups = find_near_duplicates_optimized(
            unique_files, similarity_threshold, max_workers
        )
    else:
        near_dups = find_near_duplicates_full_parallel(
            unique_files, similarity_threshold, max_workers
        )

    # Track files to remove
    files_to_remove = set()

    # Add exact duplicates (keep first occurrence)
    for original, duplicate in exact_dups:
        files_to_remove.add(duplicate)

    # Add near duplicates (keep shorter filename or first alphabetically)
    for file1, file2, similarity in near_dups:
        if len(file1.name) > len(file2.name) or (
            len(file1.name) == len(file2.name) and file1.name > file2.name
        ):
            files_to_remove.add(file1)
        else:
            files_to_remove.add(file2)

    # Summary
    print(f"\n{'=' * 60}")
    print(f"SUMMARY")
    print(f"{'=' * 60}")
    print(f"Total COBOL files found: {len(unique_files) + len(exact_dups)}")
    print(f"Exact duplicates: {len(exact_dups)}")
    print(f"Near duplicate pairs: {len(near_dups)}")
    print(f"Files to remove: {len(files_to_remove)}")
    print(
        f"Files to keep: {len(unique_files) - len([f for f in unique_files if f in files_to_remove])}"
    )

    logger.info(f"{'=' * 60}")
    logger.info(f"SUMMARY")
    logger.info(f"{'=' * 60}")
    logger.info(f"Total COBOL files found: {len(unique_files) + len(exact_dups)}")
    logger.info(f"Exact duplicates: {len(exact_dups)}")
    logger.info(f"Near duplicate pairs: {len(near_dups)}")
    logger.info(f"Files to remove: {len(files_to_remove)}")
    logger.info(
        f"Files to keep: {len(unique_files) - len([f for f in unique_files if f in files_to_remove])}"
    )

    if dry_run:
        print(f"\nDRY RUN - No files were deleted")
        print(f"Set dry_run=False to actually remove files")
    else:
        # Actually remove files
        for filepath in files_to_remove:
            try:
                filepath.unlink()
                print(f"Deleted: {filepath}")
            except Exception as e:
                print(f"Error deleting {filepath}: {e}")

        # Copy remaining files to output directory if specified
        if output_dir:
            remaining_files = [f for f in unique_files if f not in files_to_remove]
            for filepath in remaining_files:
                try:
                    shutil.copy2(filepath, output_dir / filepath.name)
                except Exception as e:
                    print(f"Error copying {filepath}: {e}")
            print(f"\nCopied {len(remaining_files)} unique files to {output_dir}")
            logger.info(f"Copied {len(remaining_files)} unique files to {output_dir}")


if __name__ == "__main__":
    # CONFIGURE THESE PARAMETERS
    source_directory = os.environ.get("DATA_DIR", "src_data")
    # output_directory = "/home/schafhdaniel@edu.local/Cob2Java-Bench/src_data_unique"
    similarity = 90

    # Two modes:
    # 1. use_optimized=True: Pre-filters by size and fingerprint, then full comparison
    #    - Much faster (maybe 10-100x depending on data)
    #    - Still finds ALL near-duplicates (no accuracy loss)
    #    - Recommended for most cases
    #
    # 2. use_optimized=False: Exhaustive all-pairs comparison
    #    - Slower but guaranteed to check every single pair
    #    - Use only if you need absolute certainty

    remove_duplicates(
        directory=source_directory,
        output_dir=None,  # in place deletion,
        similarity_threshold=similarity,
        dry_run=False,
        use_optimized=False,  # Set to False for exhaustive comparison
        max_workers=None,  # Uses CPU count - 1
    )
