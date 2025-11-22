"""
1. Iterate over the stack dataset files
2. Try to compile the snippet using the compiler
3. If it compiles, save the snippet to a new file
4. If it doesn't compile, discard the snippet
5. Repeat for all files in the stack dataset

6. If it compiles we need to be able to capture the output when we run the programm
"""

import os
from dotenv import load_dotenv
import shutil
from pathlib import Path

load_dotenv()

# Logging Setup
import logging

logger = logging.getLogger(__name__)
logging.basicConfig(
    filename=Path(os.getcwd()) / os.environ.get("LOG_DIR") / "app.log",
    level=logging.INFO,
    format="%(asctime)s %(levelname)-8s %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)
# Other Imports
from utils.llm_huggingface import LLM
from utils.download_from_theStack import StackDownloader

from enum import Enum
import subprocess
import re
import pandas as pd

DATA_DIR = Path(os.getcwd()) / os.environ.get("DATA_DIR", "data")
DATA_DIR.mkdir(parents=True, exist_ok=True)


def download_dataset():
    # Download the available cobol data
    downloader = StackDownloader()
    file_list = downloader.list_parquet_files("cobol")
    downloader.download_language("cobol", str(DATA_DIR), 5)
    return file_list


def process_parquet_files(parquet_file_list: list[str]):
    if DATA_DIR.exists():
        for file in parquet_file_list:
            file_path = DATA_DIR / file
            if file_path.exists():
                df = pd.read_parquet(file_path)
                logger.info(f"Processed {file_path.name}")
                process_dataset(df)


def process_dataset(df: pd.DataFrame):
    for idx, row in df.iterrows():
        code_snippet = row.get("content", "")
        cob_file_name = row.get
        if code_snippet:
            process_code_snippet(code_snippet)


def process_code_snippet(code_snippet: str):
    # Save the code snippet to a temporary file
    temp_file_path = DATA_DIR / "temp.cob"
    with open(temp_file_path, "w") as temp_file:
        temp_file.write(code_snippet)

    # Try to compile the code snippet
    compile_success = compile_cobol(temp_file_path)

    if compile_success:
        logger.info("Compilation succeeded")
        # Move the successful snippet to a new file
        success_file_path = DATA_DIR / f"successful_snippet_{os.urandom(4).hex()}.cob"
        shutil.move(temp_file_path, success_file_path)
    else:
        logger.info("Compilation failed")
        # Remove the temporary file
        os.remove(temp_file_path)


def compile_cobol(file_path: Path) -> bool:
    try:
        result = subprocess.run(["cobc", "-x", str(file_path)], capture_output=True)
        return result.returncode == 0
    except Exception as e:
        logger.error(f"Compilation error: {e}")
        return False


def main():
    parquet_file_list = download_dataset()
    process_parquet_files(parquet_file_list)


if __name__ == "__main__":
    main()
