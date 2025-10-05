import os
import shutil
import argparse
from pathlib import Path
def count_lines(filepath):
    with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
        return sum(1 for _ in f)

def copy_files_longer_than(src_dir, dest_dir, threshold):
    if not os.path.exists(dest_dir):
        os.makedirs(dest_dir)
    for root, _, files in os.walk(src_dir):
        for file in files:
            src_path = os.path.join(root, file)
            try:
                if os.path.isfile(src_path):
                    lines = count_lines(src_path)
                    if lines > threshold:
                        dest_path = os.path.join(dest_dir, Path(src_path).name)
                        shutil.copy2(src_path, dest_path)
                        print(f"Copied: {src_path} -> {dest_path} ({lines} lines)")
            except Exception as e:
                print(f"Error processing {src_path}: {e}")

def main():
    parser = argparse.ArgumentParser(description="Copy files with more than a threshold of lines.")
    parser.add_argument("source", help="Source directory")
    parser.add_argument("dest", help="Destination directory")
    parser.add_argument("threshold", type=int, help="Line threshold")
    args = parser.parse_args()

    copy_files_longer_than(args.source, args.dest, args.threshold)

if __name__ == "__main__":
    main()