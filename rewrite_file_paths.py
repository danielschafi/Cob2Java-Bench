import os
import re
from pathlib import Path


def replace_all_paths_with_filenames(source_dir):
    for index, cobol_file in enumerate(os.listdir(source_dir), 1):
        cobol_file_path = os.path.join(source_dir, cobol_file)

        if cobol_file.endswith(".cob"):
            print(f"Processing {cobol_file}...")
            rename_files_in_cobol(cobol_file_path, index)

    print("Renaming and restructuring complete.")


def rename_files_in_cobol(cobol_file, cobol_index):
    with open(cobol_file, "r") as file:
        content = file.read()

    found_file_paths = re.findall(
        r'assign\s+to\s+["\']([^"\']+)["\']', content, re.IGNORECASE
    )

    for match in found_file_paths:
        filename = os.path.basename(match.replace("\\", "/"))
        print("replacing:\t", match, "\twith:\t", Path(filename).name)
        # Normalize Windows and Unix paths to extract the filename

        new_path = Path(filename).name
        content = content.replace(match, new_path)

    # Write the modified content back to the file (or to a new file if you prefer)
    with open(cobol_file, "w") as file:
        file.write(content)


def main():
    source_dir = "/home/schafhdaniel@edu.local/cobolToJava/data/cobol_theStack/gt100"
    replace_all_paths_with_filenames(source_dir)


if __name__ == "__main__":
    main()
