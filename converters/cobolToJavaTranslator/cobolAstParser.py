import subprocess
import json
import os
from typing import Dict, Any, Optional
from pathlib import Path
import logging

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


class CobolASTParser:
    """
    Parser for COBOL source files to generate AST using an external Java program.
    """

    def parse_cobol_file(self, cobol_file_path: str) -> Dict[str, Any]:
        """
        Parse a COBOL file and return the AST as a Python dictionary.

        Args:
            cobol_file_path: Path to the COBOL file to parse

        Returns:
            Dictionary representation of the AST

        Raises:
            FileNotFoundError: If the COBOL file doesn't exist
            subprocess.CalledProcessError: If the Java parser fails
            json.JSONDecodeError: If the output is not valid JSON
        """
        # Ensure we work with a plain string path (avoid PosixPath in command list)
        cobol_file_path = str(Path(cobol_file_path).resolve(strict=True))

        if not os.path.exists(cobol_file_path):
            raise FileNotFoundError(f"COBOL file not found: {cobol_file_path}")

        logger.info(f"Parsing COBOL file for AST: {cobol_file_path}")
        # java -cp ".:lib/*:lib" CobolAstToJson /home/schafhdaniel@edu.local/Cob2Java-Bench/conversions/conversions_20251024_093939/test_successful_snippet_0a5789fe/successful_snippet_0a5789fe.cob
        # Call the Java program
        # cp = f"{self.jar_file_path}/*"
        cmd = [
            "java",
            "-cp",
            ".:lib/*:lib",
            "CobolAstToJson",
            cobol_file_path,
        ]

        # Make sure every element in cmd is a str to avoid TypeError when joining or running subprocess
        cmd = [str(p) for p in cmd]

        try:
            cwd = str(Path(__file__).resolve().parent)
            logger.info(f"Running command: {' '.join(cmd)} in {cwd}")
            result = subprocess.run(
                cmd, capture_output=True, text=True, check=True, cwd=cwd
            )
            logger.info("Java parser executed successfully.")
            logger.info(f"Java parser output:\n{result.stdout[:200]}...")
            # Parse the JSON output
            ast_dict = json.loads(result.stdout)
            return ast_dict

        except subprocess.CalledProcessError as e:
            error_msg = f"Java parser failed with return code {e.returncode}"
            if e.stderr:
                error_msg += f"\nStderr: {e.stderr}"
            if e.stdout:
                error_msg += f"\nStdout: {e.stdout}"
            raise RuntimeError(error_msg) from e

        except json.JSONDecodeError as e:
            raise json.JSONDecodeError(
                f"Failed to parse JSON output from: {result.stdout[:200]}...",
                result.stdout,
                e.pos,
            ) from e

    def parse_cobol_string(
        self, cobol_code: str, temp_file_path: str = "temp_cobol.cob"
    ) -> Dict[str, Any]:
        """
        Parse COBOL code from a string.

        Args:
            cobol_code: COBOL source code as a string
            temp_file_path: Path for temporary file (will be cleaned up)

        Returns:
            Dictionary representation of the AST
        """
        try:
            # Write to temporary file
            with open(temp_file_path, "w", encoding="utf-8") as f:
                f.write(cobol_code)

            # Parse the file
            return self.parse_cobol_file(temp_file_path)

        finally:
            # Clean up temporary file
            if os.path.exists(temp_file_path):
                os.remove(temp_file_path)


def main():
    """Example usage of the CobolASTParser."""
    parser = CobolASTParser()

    # Example COBOL code
    sample_cobol = """       IDENTIFICATION DIVISION.
       PROGRAM-ID. HELLO-WORLD.
       
       PROCEDURE DIVISION.
       DISPLAY 'Hello, World!'.
       STOP RUN."""

    try:
        print("Parsing COBOL code...")
        ast = parser.parse_cobol_string(sample_cobol)
        print("AST parsed successfully!")
        print(json.dumps(ast, indent=2)[:1000] + "...")  # Print first 1000 chars

    except Exception as e:
        print(f"Error: {e}")


if __name__ == "__main__":
    main()
