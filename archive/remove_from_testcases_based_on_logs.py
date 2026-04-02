"""
Usefull to clean up if you notice some errors or weird things in the logs after generating sample io files.
"""

from pathlib import Path
import re
import argparse
import shutil
import logging

logging.basicConfig(level=logging.INFO, format="%(message)s")
logger = logging.getLogger(__file__)


def find_dirs_to_remove(log_path: Path):
    lines = log_path.read_text(errors="ignore").splitlines()
    dirs = []

    to_catch = [
        "I need",
        "I need to analyze",
        "do not read from SYSIN",
        "special cobol statement",
        "special ACCEPT statement",
        "Based on the COBOL context provided",
        "cannot generate",
        "Accept Entry-Screen",
        "interactive",
    ]
    # find each line that contains "I need" (case-insensitive)
    for i, line in enumerate(lines):
        if any(phrase.lower() in line.lower() for phrase in to_catch):
            # scan forward for the next "Generated test metadata at" line
            for j in range(i + 1, len(lines)):
                m = re.search(
                    r"Generated test metadata at\s*([^\s]+/test_metadata\.json)",
                    lines[j],
                )
                if m:
                    metadata_path = Path(m.group(1))
                    testcase_dir = metadata_path.parent
                    dirs.append(testcase_dir.resolve())
                    logger.info(
                        f"Marked for removal (from log line {j + 1}): {testcase_dir}"
                    )
                    break
    # unique and keep order
    seen = set()
    uniq = []
    for d in dirs:
        if d not in seen:
            seen.add(d)
            uniq.append(d)
    return uniq


def safe_within_repo(path: Path, repo_root: Path):
    try:
        return str(path).startswith(str(repo_root.resolve()))
    except Exception:
        return False


def main():
    p = argparse.ArgumentParser(
        description="Remove testcase folders referenced after failed sample generation in log"
    )
    p.add_argument(
        "--log", "-l", required=True, help="Path to generate_sample_io_files.log"
    )
    p.add_argument(
        "--yes",
        "-y",
        action="store_true",
        help="Actually delete the folders (default is dry-run)",
    )
    args = p.parse_args()

    log_path = Path(args.log)
    if not log_path.exists():
        logger.error(f"Log file not found: {log_path}")
        return

    repo_root = Path.cwd().resolve()
    dirs = find_dirs_to_remove(log_path)
    if not dirs:
        logger.info("No problematic testcase folders found in the log.")
        return

    logger.info("\nSummary of folders found:")
    for d in dirs:
        logger.info(f"  - {d}")

    if not args.yes:
        logger.info("\nDry run. To delete the listed folders run with --yes")
        return

    # perform deletions with safety checks
    for d in dirs:
        if not d.exists():
            logger.info(f"Skipping (not found): {d}")
            continue
        if not safe_within_repo(d, repo_root):
            logger.error(f"Skipping (outside repo root): {d}")
            continue
        try:
            shutil.rmtree(d)
            logger.info(f"Deleted folder: {d}")
        except Exception as e:
            logger.error(f"Failed to delete {d}: {e}")


if __name__ == "__main__":
    main()
