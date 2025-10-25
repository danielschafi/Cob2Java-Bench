from pathlib import Path
import argparse
import math
import sys
import json

#!/usr/bin/env python3
"""
token_count.py

Scan a "success" folder for COB files, count characters per file, sum them,
and give a rough estimate of input tokens required (default 1 token ≈ 4 chars).

Usage:
    python token_count.py [--dir PATH] [--chars-per-token N]
"""

DEFAULT_DIR = "success"
DEFAULT_CHARS_PER_TOKEN = 4.0
EXTENSIONS = {".json"}


def count_chars_in_file(path: Path) -> int:
    with open(path, "r", encoding="utf-8") as json_file:
        file = json.load(json_file)
    try:
        text = file.get("cobol_file", "").get("content", "")
    except Exception:
        # fallback to a permissive encoding if utf-8 fails
        text = path.read_text(encoding="latin-1")
    return len(text)


def find_cob_files(root: Path):
    for p in root.rglob("*"):
        if p.is_file() and p.suffix.lower() in EXTENSIONS:
            yield p


def main():
    p = argparse.ArgumentParser(
        description="Count characters in COB files and estimate tokens."
    )
    p.add_argument(
        "--dir", "-d", default=DEFAULT_DIR, help="Directory to scan (default: success)"
    )
    p.add_argument(
        "--chars-per-token",
        "-c",
        type=float,
        default=DEFAULT_CHARS_PER_TOKEN,
        help="Characters per token estimate (default: 4.0)",
    )
    args = p.parse_args()

    root = Path(args.dir)
    if not root.exists() or not root.is_dir():
        print(f"Directory not found: {root}", file=sys.stderr)
        sys.exit(2)

    total_chars = 0
    files = list(find_cob_files(root))
    if not files:
        print("No COB files found.")
        return

    print(f"Found {len(files)} COB file(s) under: {root}\n")
    print(f"{'chars':>10}  {'est_tokens':>12}  path")
    print("-" * 60)
    for f in sorted(files):
        chars = count_chars_in_file(f)
        est_tokens = math.ceil(chars / args.chars_per_token)
        total_chars += chars
        print(f"{chars:10d}  {est_tokens:12d}  {f}")

    total_tokens = math.ceil(total_chars / args.chars_per_token)
    print("\n" + "-" * 60)
    print(f"TOTAL chars: {total_chars}")
    print(
        f"Estimated input tokens (1 token ≈ {args.chars_per_token} chars): {total_tokens}"
    )
    print("\nNote: token estimate is approximate; adjust --chars-per-token as needed.")


if __name__ == "__main__":
    main()
