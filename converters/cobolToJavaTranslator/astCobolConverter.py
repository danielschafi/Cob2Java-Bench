import os
from dotenv import load_dotenv
from converters.cobolToJavaTranslator.cobolAstParser import CobolASTParser
import logging
from converters.baseConverter import BaseCob2JavaConverter

# Other Imports
# from converters.cobolToJavaTranslator.llm import LLM
from utils.llm_anthropic import LLM

# from utils.llm_huggingface import LLM
from converters.cobolToJavaTranslator.prompts import (
    convert_prompt_base,
    convert_prompt_with_ast,
    system_prompt,
)

from pathlib import Path
import subprocess
import re

load_dotenv()

# Logging Setup
logger = logging.getLogger(__name__)


class ASTCobolConverter(BaseCob2JavaConverter):
    """Converts COBOL to Java using AST as additional context"""

    def __init__(self, llm: LLM):
        self.llm = llm
        self.system_prompt = system_prompt()
        self.output_dir = Path(os.getcwd()) / "out_dir_ast"
        self.parser = CobolASTParser()

    def convert(self, cobol_code: str) -> str:
        # Generate AST from COBOL code
        ast = self._generate_ast_from_string(cobol_code)

        conversion_prompt = convert_prompt_with_ast(cobol_code, ast)
        java_code = self.llm.predict(
            prompt=conversion_prompt, system_prompt=self.system_prompt
        )
        logger.info("Conversion cob2Java complete (AST mode)")
        return java_code

    def _generate_ast_from_string(self, cobol_code: str) -> str:
        # Create temporary file for AST parsing
        temp_file = Path("temp_cobol.cob")

        # Write cleaned COBOL code
        with open(temp_file, "w") as f:
            cleaned_code = self._remove_comments(cobol_code)
            f.write(cleaned_code)

        try:
            ast = self.parser.parse_cobol_file(temp_file)
            return ast
        except Exception as e:
            logger.error(f"Error generating AST from string: {e}")
            raise e
        finally:
            if temp_file.exists():
                os.remove(temp_file)

    def _generate_ast_from_file(self, cobol_file_path: Path) -> str:
        temp_file = self._prepare_for_ast_extraction(cobol_file_path)
        try:
            ast = self.parser.parse_cobol_file(temp_file)
            return ast
        finally:
            if temp_file.exists():
                os.remove(temp_file)

    def _remove_comments(self, cobol_code: str) -> str:
        """
        Removes comments from COBOL code string.

        Args:
            cobol_code (str): Original COBOL code

        Returns:
            str: COBOL code without comments
        """
        lines = cobol_code.split("\n")
        cleaned_lines = []

        for line in lines:
            # Skip comment lines
            if re.match(r"^\s*\*", line):
                continue
            if re.match(r"^\s*\*+\s*$", line):
                continue
            if re.match(r"^\s*\*{2,}\s*$", line):
                continue
            if re.match(r"^\s*[\* ]+\s*$", line):
                continue
            # Remove EXEC SQL blocks
            line = re.sub(r"\s*EXEC\s+.*?END-EXEC\s*", "", line)
            cleaned_lines.append(line)

        return "\n".join(cleaned_lines)

    def _prepare_for_ast_extraction(self, filepath: Path) -> Path:
        """
        Prepares COBOL file for AST extraction by removing comments.

        Args:
            filepath (Path): Path to original COBOL file

        Returns:
            Path: Path to cleaned temporary file
        """
        temp_file_path = filepath.with_suffix(".temp")
        with open(filepath, "r") as original_file:
            content = original_file.read()
            cleaned_content = self._remove_comments(content)

        with open(temp_file_path, "w") as temp_file:
            temp_file.write(cleaned_content)

        return temp_file_path

    def convert_from_file(self, cobol_file_path: str | Path) -> str:
        with open(cobol_file_path, "r") as cobol_file:
            cobol_code = cobol_file.read()

        # Generate AST from file (more efficient than from string)
        ast = self._generate_ast_from_file(Path(cobol_file_path))

        conversion_prompt = convert_prompt_with_ast(cobol_code, ast)
        java_code = self.llm.predict(
            prompt=conversion_prompt, system_prompt=self.system_prompt
        )
        logger.info("Conversion cob2Java complete (AST mode)")
        return java_code

    def convert_and_save(
        self, cobol_code: str | Path, output_path: Path = None
    ) -> Path:
        # Read from file if path is provided
        if isinstance(cobol_code, Path) or (
            isinstance(cobol_code, str) and os.path.exists(cobol_code)
        ):
            java_code = self.convert_from_file(cobol_code)
        else:
            java_code = self.convert(cobol_code)

        # Save the generated code
        if output_path is None:
            os.makedirs(self.output_dir, exist_ok=True)
            output_filename = self._get_java_class_name(java_code) + ".java"
            output_path = self.output_dir / output_filename

        logger.info(f"Saving the generated Java code to: {output_path}")
        if os.path.exists(output_path):
            logger.info(f"File: {output_path} already exists. Overwriting...")
            os.remove(output_path)

        with open(output_path, "w") as java_file:
            java_file.write(java_code)

        if os.path.exists(output_path) and os.path.getsize(output_path) > 1:
            logger.info(f"Successfully written generated Java code to {output_path}")
        else:
            raise ValueError(
                f"Generated Java file {output_path} could not be found or was empty"
            )

        return output_path

    def _get_java_class_name(self, java_code: str) -> str:
        if len(java_code) == 0:
            raise ValueError(
                "No Java code was generated, could not extract class name."
            )

        # Regex to match 'public class ClassName'
        match = re.search(r"public\s+class\s+([A-Za-z_][A-Za-z0-9_]*)", java_code)

        if match:
            return match.group(1)
        else:
            raise ValueError(f"No public class found in Java code")

    def compile(self, java_filepath: Path) -> Path:
        try:
            logger.info(f"Attempting to compile: {java_filepath}")
            compile_result = subprocess.run(
                ["javac", str(java_filepath)], capture_output=True, text=True
            )
            if compile_result.returncode != 0:
                logger.warning(
                    f"Compilation failed of {java_filepath}\n{compile_result.stderr}"
                )
                raise RuntimeError(f"Compilation failed: {compile_result.stderr}")
            else:
                logger.info(f"Compilation of {java_filepath} successful")
                return java_filepath.with_suffix("")
        except Exception as e:
            logger.error(f"Compilation of {java_filepath} failed: {e}")
            raise

    def run_java_program(self, java_prog_path: Path):
        try:
            logger.info(f"Attempting to run: {java_prog_path}")
            dir = java_prog_path.parent
            filename = java_prog_path.stem

            result_program = subprocess.run(
                ["java", "-cp", str(dir), filename], capture_output=True, text=True
            )
            print(result_program.stdout)
            if result_program.stderr:
                print("STDERR:", result_program.stderr)
            logger.info(f"Running of {java_prog_path} successful!")
        except Exception as e:
            logger.error(f"Running of {java_prog_path} failed: {e}")
            raise

    def convert_compile_run(self, cobol_code: str | Path):
        """
        Converts COBOL code to Java using AST, compiles it, and runs the resulting program.

        Args:
            cobol_code (str | Path): The COBOL code string or path to file
        """
        java_filepath = self.convert_and_save(cobol_code)
        java_program_path = self.compile(java_filepath)
        self.run_java_program(java_program_path)


# Example usage
def main():
    llm = LLM()

    cobol_file = Path("/home/schafhdaniel@edu.local/cobolToJava/in_cob_file.cbl")
    ast_converter = ASTCobolConverter(llm)

    # Option 1: Convert and get string (AST generated automatically)
    # java_code = ast_converter.convert(cobol_code_string)

    # Option 2: Convert from file and get string
    # java_code = ast_converter.convert_from_file("path/to/file.cbl")

    # Option 3: Convert and save to file
    # java_filepath = ast_converter.convert_and_save(cobol_code_string)

    # Option 4: Full pipeline
    ast_converter.convert_compile_run(cobol_file)


if __name__ == "__main__":
    main()
