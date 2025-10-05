#!/usr/bin/env python3
"""
The Stack Dataset Parquet File Downloader

This script downloads parquet files from the BigCode/The Stack dataset on Hugging Face.
The Stack is organized by programming language in subdirectories like data/python/, data/java/, etc.
"""

import os
import requests
from pathlib import Path
from huggingface_hub import HfApi, hf_hub_download
from typing import List, Optional
import argparse
import time
from tqdm import tqdm


class StackDownloader:
    def __init__(self, repo_id: str = "bigcode/the-stack"):
        self.repo_id = repo_id
        self.api = HfApi()
        self.base_url = f"https://huggingface.co/datasets/{repo_id}/resolve/main"

    def list_available_languages(self) -> List[str]:
        """List all available programming languages in the dataset"""
        try:
            files = self.api.list_repo_files(self.repo_id, repo_type="dataset")
            languages = set()

            for file in files:
                if file.startswith("data/") and file.endswith(".parquet"):
                    # Extract language from path like "data/python/train-00000-of-01126.parquet"
                    parts = file.split("/")
                    if len(parts) >= 3:
                        languages.add(parts[1])

            return sorted(list(languages))
        except Exception as e:
            print(f"Error listing languages: {e}")
            return []

    def list_parquet_files(self, language: str) -> List[str]:
        """List all parquet files for a specific programming language"""
        try:
            files = self.api.list_repo_files(self.repo_id, repo_type="dataset")
            parquet_files = []

            for file in files:
                if file.startswith(f"data/{language}/") and file.endswith(".parquet"):
                    parquet_files.append(file)

            return sorted(parquet_files)
        except Exception as e:
            print(f"Error listing parquet files for {language}: {e}")
            return []

    def download_file(
        self, filename: str, local_dir: str = "./the-stack-data"
    ) -> Optional[str]:
        """Download a single parquet file"""
        try:
            print(f"Downloading {filename}...")
            local_path = hf_hub_download(
                repo_id=self.repo_id,
                filename=filename,
                repo_type="dataset",
                local_dir=local_dir,
                local_dir_use_symlinks=False,  # Download actual files, not symlinks
            )
            print(f"Downloaded to: {local_path}")
            return local_path
        except Exception as e:
            print(f"Error downloading {filename}: {e}")
            return None

    def download_language(
        self,
        language: str,
        local_dir: str = "./the-stack-data",
        max_files: Optional[int] = None,
    ):
        """Download all parquet files for a specific programming language"""
        parquet_files = self.list_parquet_files(language)

        if not parquet_files:
            print(f"No parquet files found for language: {language}")
            return

        if max_files:
            parquet_files = parquet_files[:max_files]

        print(f"Found {len(parquet_files)} parquet files for {language}")

        # Create local directory
        os.makedirs(local_dir, exist_ok=True)

        successful_downloads = 0
        for file in tqdm(parquet_files, desc=f"Downloading {language} files"):
            if self.download_file(file, local_dir):
                successful_downloads += 1
            time.sleep(0.1)  # Small delay to be nice to the servers

        print(
            f"Successfully downloaded {successful_downloads}/{len(parquet_files)} files for {language}"
        )

    def download_specific_files(
        self, file_list: List[str], local_dir: str = "./the-stack-data"
    ):
        """Download specific parquet files by filename"""
        os.makedirs(local_dir, exist_ok=True)

        successful_downloads = 0
        for filename in tqdm(file_list, desc="Downloading files"):
            if self.download_file(filename, local_dir):
                successful_downloads += 1
            time.sleep(0.1)

        print(f"Successfully downloaded {successful_downloads}/{len(file_list)} files")


def main():
    parser = argparse.ArgumentParser(
        description="Download parquet files from The Stack dataset"
    )
    parser.add_argument(
        "--list-languages",
        action="store_true",
        help="List all available programming languages",
    )
    parser.add_argument(
        "--language",
        type=str,
        help="Programming language to download (e.g., python, java, javascript)",
    )
    parser.add_argument(
        "--list-files", type=str, help="List parquet files for a specific language"
    )
    parser.add_argument(
        "--output-dir",
        type=str,
        default="./the-stack-data",
        help="Output directory for downloaded files",
    )
    parser.add_argument(
        "--max-files",
        type=int,
        help="Maximum number of files to download (useful for testing)",
    )
    parser.add_argument(
        "--files",
        nargs="+",
        help="Specific files to download (e.g., data/python/train-00000-of-01126.parquet)",
    )

    args = parser.parse_args()

    downloader = StackDownloader()

    if args.list_languages:
        languages = downloader.list_available_languages()
        print("Available programming languages:")
        for lang in languages:
            print(f"  - {lang}")
        return

    if args.list_files:
        files = downloader.list_parquet_files(args.list_files)
        print(f"Parquet files for {args.list_files}:")
        for file in files:
            print(f"  - {file}")
        return

    if args.files:
        downloader.download_specific_files(args.files, args.output_dir)
        return

    if args.language:
        downloader.download_language(args.language, args.output_dir, args.max_files)
        return

    # Interactive mode if no arguments provided
    print("The Stack Dataset Downloader")
    print("=" * 30)

    languages = downloader.list_available_languages()[:10]  # Show first 10
    print("Some available languages:", ", ".join(languages))

    # Example usage
    print("\nExample usage:")
    print("  python script.py --list-languages                    # List all languages")
    print(
        "  python script.py --language python --max-files 5     # Download 5 Python files"
    )
    print(
        "  python script.py --list-files python                 # List Python parquet files"
    )


if __name__ == "__main__":
    main()
