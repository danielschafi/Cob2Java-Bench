import os
from dotenv import load_dotenv
from pathlib import Path
import logging
from abc import ABC, abstractmethod
import json
import shutil
import subprocess
import time
import numpy as np

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


class Cob2JavaConverter(ABC):
    """
    Base class for COBOL to Java converters. All converters should inherit from this class
    and implement the convert method.
    """

    @abstractmethod
    def convert(self, cobol_code: str) -> str:
        """
        Convert COBOL code to Java code.

        Args:
            cobol_code (str): The COBOL source code as a string.

        Returns:
            str: The converted Java source code as a string.
        """
        pass


class Testcase:
    """Runs a single testcase and gathers the results."""

    def __init__(
        self,
        testcase_dir: Path,
        java_output_path: Path,
        converter: Cob2JavaConverter,
    ) -> None:
        self.testcase_dir = testcase_dir
        self.java_output_path = java_output_path
        self.converter = converter
        self.metadata = {}

    def get_metadata(self):
        metadata_file = self.testcase_dir / "test_metadata.json"
        if not (metadata_file).exists():
            raise FileNotFoundError(
                f"Metadata file 'test_metadata.json' not found in '{self.testcase_dir}'."
            )

        with open(metadata_file, "r") as f:
            return json.load(f)

    def setup(self):
        """
        Reads the metadata.json file and creates necessary input files.

        Returns a list of created files. These should be deleted again after the test run.
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
                file_path = self.testcase_dir / filename
                with open(file_path, "w") as f:
                    f.write(content)
                logger.info(f"\tCreated file '{file_path}' with content.")

        return file_list  # Add setup logic here

    def convert_cobol_to_java(self):
        logger.info(f"Converting COBOL to Java in {self.testcase_dir}")

        cobol_code = self.metadata["cobol_file"]["content"]
        cobol_filename = self.metadata["cobol_file"]["file_name"]
        java_filename = cobol_filename.replace(".cob", ".java")

        java_code = self.converter.convert(cobol_code)

        with open(self.java_output_path, "w") as f:
            f.write(java_code)

        logger.info(
            f"Converted COBOL file '{cobol_filename}' to Java file '{java_filename}'"
        )

        return java_code

    def compile_java(self):
        logger.info(f"Compiling Java code in {self.testcase_dir}")
        self.java_output_path

        subprocess.run(
            ["javac", str(self.java_output_path)],
            check=True,
            cwd=self.java_output_path.parent,
        )

    def teardown(self):
        """
        Deletes all files besides test_metadata.json in the folder
        """
        logger.info(f"Tearing down testcase in {self.testcase_dir}")
        logger.info(f"Cleaning up test environment in '{self.testcase_dir}'")
        for file in self.testcase_dir.iterdir():
            if file.name != "test_metadata.json":
                file.unlink()
                logger.info(f"\tDeleted file '{file}'.")

    def run(self):
        self.setup()
        java_code = self.convert_cobol_to_java()
        self.compile_java()

        self.teardown()


class Cob2JavaBenchmark:
    def __init__(
        self,
        benchmark_dir: Path,
        conversions_dest_dir: Path,
        converter: Cob2JavaConverter,
    ) -> None:
        self.benchmark_dir = benchmark_dir
        self.conversions_dest_dir = conversions_dest_dir
        self.converter = converter
        self.testcases = [
            Testcase(
                testcase_dir,
                conversions_dest_dir / (testcase_dir.stem + ".java"),
                converter,
            )
            for testcase_dir in benchmark_dir.iterdir()
            if testcase_dir.is_dir()
        ]

        self.metrics = {
            # "successful_conversions": 0,
            # "failed_conversions": 0,
            "compilation_success": [],
            "run_success": [],
            "output_equivalence_score": [],
            "code_quality": [],
            "conversion_time": [],
            # "total_conversion_time": 0.0,
            "total_testcases": len(self.testcases),
            "conversion_success": 0,
            "conversion_errors": 0,
            "compilation_errors": 0,
        }

    def run(self):
        for testcase in self.testcases:
            try:
                results = testcase.run()

                self.metrics["compilation_success"].append(
                    results["compilation_success"]
                )
                self.metrics["run_success"].append(results["run_success"])
                self.metrics["output_equivalence_score"].append(
                    results["output_equivalence_score"]
                )
                self.metrics["code_quality"].append(results["code_quality"])
                self.metrics["conversion_time"].append(results["conversion_time"])
                self.metrics["conversion_success"].append(results["conversion_success"])
                self.metrics["compilation_errors"].append(results["compilation_error"])

            except Exception as e:
                logger.error(f"Error running testcase in {testcase.testcase_dir}: {e}")
            finally:
                testcase.teardown()

        return {
            "total_testcases": len(self.testcases),
            "compilation_success_rate": np.mean(self.metrics["compilation_success"]),
            "run_success_rate": np.mean(self.metrics["run_success"]),
            "average_output_equivalence_score": np.mean(
                self.metrics["output_equivalence_score"]
            ),
            "average_code_quality": np.mean(self.metrics["code_quality"]),
            "average_conversion_time": np.mean(self.metrics["conversion_time"]),
            "conversion_success_rate": np.mean(self.metrics["conversion_success"]),
            "compilation_error_rate": np.mean(self.metrics["compilation_errors"]),
        }


class DummyConvert(Cob2JavaConverter):
    def convert(self, cobol_code: str) -> str:
        # Dummy conversion logic for demonstration
        java_code = f"// Converted Java code\n// Original COBOL code:\n{cobol_code}"
        return java_code


def main():
    testcases_dir = Path(os.environ.get("TESTS_DIR", "testcases"))
    conversions_dest_dir = Path(os.environ.get("CONVERSIONS_DEST_DIR", "conversions"))
    conversions_dest_dir.mkdir(parents=True, exist_ok=True)

    if (testcases_dir / "success").exists():
        testcases_dir = testcases_dir / "success"

    converter = DummyConvert()
    benchmark = Cob2JavaBenchmark(testcases_dir, conversions_dest_dir, converter)

    results = benchmark.run()
    print("Benchmark Results:\n", json.dumps(results))
    logger.info("Benchmark Results:\n" + json.dumps(results))
    logger.info("Benchmarking complete.")
