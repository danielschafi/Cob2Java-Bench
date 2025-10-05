import sys
import os

def is_text_file(filename):
    text_exts = {'.cob', '.cbl', '.txt', '.cobol'}
    _, ext = os.path.splitext(filename)
    return ext.lower() in text_exts

def count_lines(filepath):
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            return sum(1 for _ in f)
    except Exception:
        return None

def main():
    if len(sys.argv) != 3:
        print("Usage: python get_mean_linecount.py <directory_path> <cutoff>")
        sys.exit(1)

    dir_path = sys.argv[1]
    if not os.path.isdir(dir_path):
        print(f"Error: {dir_path} is not a valid directory.")
        sys.exit(1)

    line_counts = []
    cutoff = int(sys.argv[2]) #only count files with at least this many lines
    files_considered = []

    for entry in os.listdir(dir_path):
        full_path = os.path.join(dir_path, entry)
        if os.path.isfile(full_path) and is_text_file(entry):
            lines = count_lines(full_path)
            if lines is not None:
                if lines > cutoff:
                    line_counts.append(lines)
                    files_considered.append(entry)

    if not line_counts:
        print("No readable text files with extensions found in the directory.")
        sys.exit(0)

    mean_lines = sum(line_counts) / len(line_counts)
    min_lines = min(line_counts)
    max_lines = max(line_counts)
    total_files = len(line_counts)

    print(f"Files Checked: {total_files}")
    print(f"Mean line count: {mean_lines:.2f}")
    print(f"Min line count: {min_lines}")
    print(f"Max line count: {max_lines}")

if __name__ == "__main__":
    main()