from pathlib import Path
import subprocess
import json
import os
from dotenv import load_dotenv
import shutil
import logging

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

"""
- Run the test cases in the test case folder
- capture outputs of cobol code testcases
- save the outputs to a file or the metadata.json file within the folder of the testcase
"""


# class TestCaseSetup:
#     def __init__(self, testcase_dir):
#         """
#         params:
#         testcase_dir: directory that contains metadata.json
#         """
#         self.testcase_dir = Path(testcase_dir)
#         if not self.testcase_dir.exists():
#             raise FileNotFoundError(
#                 f"Test case directory '{testcase_dir}' does not exist."
#             )
#         self.metadata_file = self.testcase_dir / "test_metadata.json"
#         if not self.metadata_file.exists():
#             raise FileNotFoundError(
#                 f"Metadata file 'test_metadata.json' not found in '{testcase_dir}'."
#             )

#         self.metadata = self.read_metadata()

#     def setup(self):
#         input_files = self.metadata.get("input_files", [])
#         input_output_files = self.metadata.get("input_output_files", [])
#         cob_file = self.metadata.get("cobol_file", {})
#         sysin_file = self.metadata.get("sysin_file", {})

#         file_list = input_files + input_output_files + [cob_file]
#         if sysin_file:
#             file_list += [sysin_file] if sysin_file.get("content") else []
#         for file in file_list:
#             filename = file.get("file_name")
#             content = file.get("content", "")
#             if filename:
#                 file_path = self.testcase_dir / filename
#                 with open(file_path, "w") as f:
#                     f.write(content)
#                 logger.info(f"Setup: Created file '{file_path}' with content.")
#         return file_list

#     def teardown(self):
#         for f in self.testcase_dir.iterdir():
#             if f.name != "test_metadata.json":
#                 f.unlink()
#         logger.info(f"Teardown: Deleted all files in '{self.testcase_dir}'.")

#     def read_metadata(self):
#         with open(self.metadata_file, "r") as f:
#             metadata = json.load(f)
#         return metadata


class CobolOutputCapture:
    def __init__(self, testcase_dir):
        self.tests_dir = Path(
            testcase_dir
        )  # directory that contains subdirectories with testcases

        self.successful_tests_dir = self.tests_dir / "success"

        if not self.tests_dir.exists():
            raise FileNotFoundError(
                f"Test case directory '{testcase_dir}' does not exist."
            )

        self.failed_tests_dir = self.tests_dir / "failed"
        self.successful_tests_dir.mkdir(exist_ok=True)
        self.failed_tests_dir.mkdir(exist_ok=True)

        self.failed_compiles = []
        self.failed_runs = []
        self.timed_out = []

    def run_testcase(self, testcase_dir):
        metadata_file = Path(testcase_dir) / "test_metadata.json"
        metadata = json.loads(metadata_file.read_text())
        cob_file = metadata.get("cobol_file", {}).get("file_name")
        if not cob_file:
            raise ValueError(
                f"COBOL file not specified in metadata.json in '{testcase_dir}'."
            )

        sysin_file = metadata.get("sysin_file", {})
        if sysin_file:
            sysin_file = sysin_file.get("file_name")

        code_file = Path(testcase_dir) / cob_file
        compiled_file = Path(testcase_dir) / Path(cob_file).stem
        logger.info(f"\tcode: {code_file}")
        logger.info(f"\tcompiled: {compiled_file}")

        self.compile_file(code_file, compiled_file)
        if code_file in self.failed_compiles:
            return

        output = self.run_file(compiled_file, sysin_file=sysin_file)
        if output is not None:  # run successful
            self.add_to_metadata("code_file", Path(code_file).name, testcase_dir)
            self.add_to_metadata(
                "compiled_file", Path(compiled_file).name, testcase_dir
            )
            self.add_to_metadata("expected_output", output, testcase_dir)
            logger.info(f"\tCaptured output for '{cob_file}' and updated metadata.")
            return True
        else:
            logger.error(f"\tFailed to capture output for '{cob_file}'.")
            return False

    def compile_file(self, code_file, compiled_file):
        logger.info(f"\tCompiling COBOL file: {code_file}")
        compile_cmd = [
            "cobc",
            "-x",
            code_file,
            "-o",
            compiled_file,
        ]
        result = subprocess.run(compile_cmd, capture_output=True, text=True)
        if result.returncode != 0:
            logger.error(f"\t\tCompilation failed:{result.returncode}\n{result.stderr}")
            self.failed_compiles.append(code_file)
        else:
            logger.info(
                f"\t\tCompiled '{code_file}' to '{compiled_file}' in the data folder."
            )

    def run_file(self, compiled_file, sysin_file=None):
        logger.info(f"\tRunning compiled file: {compiled_file}")

        cwd = os.getcwd()
        os.chdir(compiled_file.parent)

        run_cmd = [compiled_file]

        stdin_input = None
        if sysin_file:
            sysin_path = Path(sysin_file)
            if sysin_path.exists():
                with open(sysin_path, "r") as f:
                    stdin_input = f.read()
                logger.info(f"\tUsing SYSIN from: {sysin_file}")

        # Try with text mode first
        try:
            try:
                result = subprocess.run(
                    run_cmd,
                    capture_output=True,
                    text=True,
                    input=stdin_input,
                    encoding="utf-8",
                    timeout=5,
                )
            except subprocess.TimeoutExpired:
                logger.error(f"\t\tExecution timed out for '{compiled_file}'.")
                self.timed_out.append(compiled_file)
                os.chdir(cwd)
                return None

            output = result.stdout
        except UnicodeDecodeError:
            # If UTF-8 fails, run again in binary mode
            logger.warning(f"\tUTF-8 decode failed, trying binary mode")
            stdin_bytes = stdin_input.encode("utf-8") if stdin_input else None
            try:
                result = subprocess.run(
                    run_cmd,
                    capture_output=True,
                    text=False,  # Binary mode
                    input=stdin_bytes,
                    timeout=5,
                )
            except subprocess.TimeoutExpired:
                logger.error(f"\t\tExecution timed out for '{compiled_file}'.")
                self.timed_out.append(compiled_file)
                os.chdir(cwd)
                return None
            # Try to decode with error handling
            output = result.stdout.decode("utf-8", errors="replace")
            logger.info(f"\tUsed binary mode with error replacement")

        os.chdir(cwd)

        if result.returncode != 0:
            stderr = (
                result.stderr
                if isinstance(result.stderr, str)
                else result.stderr.decode("utf-8", errors="replace")
            )
            logger.error(f"\t\tExecution failed:\n{stderr}")
            self.failed_runs.append(compiled_file)
            return None
        else:
            logger.info(f"\t\tExecuted '{compiled_file}' successfully.")
            return output

    def add_to_metadata(self, key, value, testcase_dir):
        metadata_file = Path(testcase_dir) / "test_metadata.json"

        with open(metadata_file, "r") as f:
            metadata = json.load(f)
        metadata[key] = value

        with open(metadata_file, "w") as f:
            json.dump(metadata, f, indent=4)

    def run_all_testcases(self):
        testcase_dirs = [Path(d) for d in self.tests_dir.iterdir() if d.is_dir()]

        logger.info(f"Found test case directories: {testcase_dirs}")

        for testcase in testcase_dirs:
            if testcase.name in ["success", "failed"]:
                continue  # Skip the success and failed directories
            file_list = self.setup_test_environment(testcase)
            cob_file = file_list[-1].get("file_name")
            logger.info(f"Processing test case: {cob_file}")
            success = self.run_testcase(testcase)
            if success:
                self.cleanup_test_environment(testcase)
                shutil.move(testcase, self.successful_tests_dir / testcase.name)
            else:
                # copy it with the files still in place for debugging
                shutil.move(testcase, self.failed_tests_dir / testcase.name)

        logger.info("Finished processing all test cases.")
        logger.info(f"Failed compiles: {self.failed_compiles}")
        logger.info(f"Failed runs: {self.failed_runs}")
        print(f"Failed compiles: {self.failed_compiles}")
        print(f"Failed runs: {self.failed_runs}")
        return self.failed_compiles, self.failed_runs

    def setup_test_environment(self, testcase_dir):
        """
        Reads the metadata.json file and creates necessary input files.

        Returns a list of created files. These should be deleted again after the test run.
        """
        logger.info(f"Setting up test environment in '{testcase_dir}'")
        metadata_file = testcase_dir / "test_metadata.json"
        if not (metadata_file).exists():
            raise FileNotFoundError(
                f"Metadata file 'test_metadata.json' not found in '{testcase_dir}'."
            )

        with open(metadata_file, "r") as f:
            metadata = json.load(f)

        input_files = metadata.get("input_files", [])
        input_output_files = metadata.get("input_output_files", [])
        cob_file = metadata.get("cobol_file", {})
        sysin_file = metadata.get("sysin_file", {})

        file_list = input_files + input_output_files + [cob_file]
        if sysin_file:
            file_list += [sysin_file] if sysin_file.get("content") else []
        for file in file_list:
            filename = file.get("file_name")
            content = file.get("content", "")
            if filename:
                file_path = testcase_dir / filename
                with open(file_path, "w") as f:
                    f.write(content)
                logger.info(f"\tCreated file '{file_path}' with content.")

        return file_list

    def cleanup_test_environment(self, testcase_dir):
        """
        Deletes all files besides test_metadata.json in the folder
        """
        logger.info(f"Cleaning up test environment in '{testcase_dir}'")
        for file in testcase_dir.iterdir():
            if file.name != "test_metadata.json":
                file.unlink()
                logger.info(f"\tDeleted file '{file}'.")


def main():
    logger.info("=" * 150)
    logger.info(" " * 60 + "Starting output capture")
    logger.info("=" * 150)
    testcase_dir = Path(os.environ.get("TESTS_DIR", "testcases"))
    outputCapture = CobolOutputCapture(testcase_dir=testcase_dir)
    outputCapture.run_all_testcases()


if __name__ == "__main__":
    main()
