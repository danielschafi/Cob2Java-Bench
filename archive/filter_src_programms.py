import os
from pathlib import Path
import re
import json
import shutil
from typing import Dict, List, Tuple, Optional
from dataclasses import dataclass

# from utils import llm
from utils import llm_anthropic as llm
from dotenv import load_dotenv
import logging
import numpy as np
import argparse


load_dotenv()

logger = logging.getLogger(__file__)


def check_filter_program(cobol_code: str) -> tuple[bool, str, str]:
    patterns = [
        r"SCREEN\s+SECTION",
        r"ACCEPT\s+.*\s+FROM\s+CONSOLE",
        r"ACCEPT\s+.*\s+FROM\s+COMMAND-LINE",
        r"ACCEPT\s+.*\s+FROM\s+ARGUMENT-NUMBER",
        r"ACCEPT\s+.*\s+FROM\s+ARGUMENT-VALUE",
    ]

    for pattern in patterns:
        match = re.search(pattern, cobol_code, re.IGNORECASE)
        if match:
            return True, pattern, match.group()
    return False, "", ""


def filter_programs(
    src_dir: Path, filtered_dest: Optional[Path], dry_run: bool = False
):
    in_place = filtered_dest is None
    if not in_place:
        if not filtered_dest.exists():
            filtered_dest.mkdir(parents=True)
    print(len(list(src_dir.glob("*.cob"))), "programms found in", src_dir, "\n")
    hits = 0
    for programm in src_dir.glob("*.cob"):
        if programm.is_file():
            cobol_code = programm.read_text(errors="ignore")
            do_filter, pattern, match = check_filter_program(cobol_code)
            if do_filter:
                hits += 1
                logger.info(
                    f"\nFound program to filter: {programm.name} \n\tmatched pattern {pattern} \n\tmatched text: {match}"
                )
                print(
                    f"\nFound program to filter: {programm.name} \n\tmatched pattern {pattern} \n\tmatched text: {match}"
                )

                if in_place:
                    if not dry_run:
                        programm.unlink()
                    logger.info(f"\tDeleted program: {programm.name}")
                    print(f"\tDeleted program: {programm.name}")
                else:
                    dest_file = filtered_dest / programm.name
                    if not dry_run:
                        shutil.move(programm, dest_file)
                    logger.info(f"\tMoved program: {programm.name} to {dest_file}")
                    print(f"\tMoved program: {programm.name} to {dest_file}")
    print(
        "\n",
        hits,
        "programs to filter found in",
        src_dir,
        "after filtering",
    )


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Filter out COBOL programs from src dir that match certain patterns. Either delete them in place or copy them to a filtered destination.",
    )
    parser.add_argument(
        "--src-dir",
        type=Path,
        required=True,
        help="Path to the directory containing the source COBOL programs.",
    )
    parser.add_argument(
        "--filtered-dest",
        type=Path,
        required=False,
        help="Path to the directory where filtered test cases will be moved. If not set, files will be deleted in place.",
    )
    parser.add_argument(
        "--dry-run",
        type=bool,
        default=False,
        help="If set, do not actually copy files, just log what would be done.",
    )

    args = parser.parse_args()

    filter_programs(args.src_dir, args.filtered_dest, args.dry_run)
