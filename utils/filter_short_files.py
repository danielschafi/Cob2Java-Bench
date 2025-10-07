import os
import shutil
import argparse
from pathlib import Path
import logging
from dotenv import load_dotenv

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


def count_lines(filepath):
    with open(filepath, "r", encoding="utf-8", errors="ignore") as f:
        return sum(1 for _ in f)


def copy_files_longer_than(src_dir, dont_keep_dir, threshold):
    if not os.path.exists(dont_keep_dir):
        os.makedirs(dont_keep_dir)
    for root, _, files in os.walk(src_dir):
        for file in files:
            src_path = os.path.join(root, file)
            try:
                if os.path.isfile(src_path):
                    lines = count_lines(src_path)
                    if lines < threshold:
                        dest_path = os.path.join(dont_keep_dir, Path(src_path).name)
                        shutil.move(src_path, dest_path)
                        logger.info(f"Moved: {src_path} -> {dest_path} ({lines} lines)")
                        print(f"Moved: {src_path} -> {dest_path} ({lines} lines)")
            except Exception as e:
                logger.error(f"Error processing {src_path}: {e}")
                print(f"Error processing {src_path}: {e}")


def main():
    parser = argparse.ArgumentParser(
        description="Copy files with more than a threshold of lines."
    )
    parser.add_argument("source", help="Source directory")
    parser.add_argument(
        "dont_keep_dir",
        help="Destination directory for files not meeting the threshold",
    )
    parser.add_argument("threshold", type=int, help="Line threshold")
    args = parser.parse_args()

    copy_files_longer_than(args.source, args.dont_keep_dir, args.threshold)


if __name__ == "__main__":
    main()
