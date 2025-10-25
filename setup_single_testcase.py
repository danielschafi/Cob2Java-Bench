import json
import argparse
from pathlib import Path
import logging

logger = logging.getLogger(__name__)


class TestcaseHelper:
    def __init__(self, testcase_dir):
        self.testcase_dir = testcase_dir
        self.metadata = {}

    def setup(self, setup_dir=None):
        """
        Reads the metadata.json file and creates necessary input files.

        Returns a list of created files. These should be deleted again after the test run.

        Creates the files in setup_dir if provided, otherwise in testcase_dir.
        """
        logger.info(f"Setting up test environment in '{self.testcase_dir}'")

        self.metadata = self.get_metadata()

        # Setup Files
        input_files = self.metadata.get("input_files", [])
        input_output_files = self.metadata.get("input_output_files", [])
        cob_file = self.metadata.get("cobol_file", {})
        sysin_file = self.metadata.get("sysin_file", {})

        file_list = input_files + input_output_files + [cob_file]
        if sysin_file:
            file_list += [sysin_file] if sysin_file.get("content") else []
        for file in file_list:
            filename = file.get("file_name")
            content = file.get("content", "")
            if filename:
                file_path = (setup_dir if setup_dir else self.testcase_dir) / filename
                with open(file_path, "w") as f:
                    f.write(content)

        return file_list, self.metadata

    def get_metadata(self):
        metadata_file = self.testcase_dir / "test_metadata.json"
        if not (metadata_file).exists():
            raise FileNotFoundError(
                f"Metadata file 'test_metadata.json' not found in '{self.testcase_dir}'."
            )

        with open(metadata_file, "r") as f:
            return json.load(f)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Testcase setup utility")
    parser.add_argument(
        "--testcase_dir",
        type=str,
        help="Directory containing test_metadata.json",
        required=True,
    )
    parser.add_argument(
        "--setup-dir",
        type=str,
        default=None,
        required=False,
        help="Directory where to setup testcase files (default: testcase_dir)",
    )

    args = parser.parse_args()
    testcase_dir = Path(args.testcase_dir)
    setup_dir = Path(args.setup_dir) if args.setup_dir else None
    if setup_dir and not setup_dir.exists():
        setup_dir.mkdir(parents=True)

    helper = TestcaseHelper(testcase_dir)
    created_files = helper.setup(setup_dir)
    print(
        f"Created files: {[f.get('file_name') for f in created_files if isinstance(f, dict)]}"
    )
