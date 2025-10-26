import os
from pathlib import Path
import json
import shutil
from typing import Dict, List, Optional
from dataclasses import dataclass
from pydantic import BaseModel, Field

try:
    from utils import llm_anthropic as llm_module
except Exception:
    llm_module = None
from dotenv import load_dotenv
import logging
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

# Instantiate LLM
try:
    if llm_module is not None:
        llm = llm_module.LLM()
    else:
        raise ImportError("llm module not available")
except Exception:

    class _LLMStub:
        def predict(self, prompt: str, system_prompt: Optional[str] = None) -> str:
            return ""

    llm = _LLMStub()


# Pydantic models for structured output
class FileContent(BaseModel):
    """Represents a file with name and content"""

    filename: str = Field(
        description="Name of the file (e.g., 'input.dat', 'customers.txt')"
    )
    content: str = Field(description="Complete content of the file")


class StdinConfig(BaseModel):
    """Configuration for stdin redirection"""

    enabled: bool = Field(description="Whether stdin redirection is used")
    source_file: Optional[str] = Field(
        default=None,
        description="Filename to redirect from (e.g., 'input.txt' for ./prog < input.txt)",
    )


class TestDataSchema(BaseModel):
    """Schema for test data generation output"""

    input_files: List[FileContent] = Field(
        default_factory=list,
        description="List of input files with their content (regular files opened by the program)",
    )
    output_files: List[str] = Field(
        default_factory=list,
        description="List of output file names that the program creates (no content needed)",
    )
    input_output_files: List[FileContent] = Field(
        default_factory=list,
        description="Files that are both read and written (need initial content)",
    )
    command_line_args: List[str] = Field(
        default_factory=list,
        description="Command line arguments in order (e.g., ['input.dat', '2024', 'report.txt'])",
    )
    stdin: StdinConfig = Field(
        default_factory=lambda: StdinConfig(enabled=False),
        description="Stdin configuration if program reads from standard input",
    )
    sysin_file: Optional[FileContent] = Field(
        default=None,
        description="SYSIN file content if program uses ACCEPT statements for standard input",
    )


class SampleContentGenerator:
    def __init__(self, cobol_directory: Path, test_cases_dir: Path):
        self.cobol_directory = Path(cobol_directory)
        self.test_cases_dir = Path(test_cases_dir)

        self.nr_testcases_content_generated = 0
        self.nr_total_testcases = 0

    def generate_test_cases(self):
        """Generate test cases for all COBOL files"""
        # Clean up empty test case directories
        for f in self.test_cases_dir.glob("*"):
            if f.is_dir() and len(list(f.glob("*"))) == 0:
                logger.info(f"Removing empty testcases dir: {f}")
                f.rmdir()

        cobol_files = (
            list(self.cobol_directory.glob("*.cbl"))
            + list(self.cobol_directory.glob("*.cob"))
            + list(self.cobol_directory.glob("*.cobol"))
        )
        self.nr_total_testcases = len(cobol_files)
        np.random.shuffle(cobol_files)

        # Filter out already processed files
        logger.info(f"Before filtering, found {len(cobol_files)} cobol files")
        files_already_in_testcases = os.listdir(self.test_cases_dir)
        self.nr_testcases_content_generated = len(files_already_in_testcases)
        cobol_files = [
            f for f in cobol_files if "test_" + f.stem not in files_already_in_testcases
        ]
        logger.info(f"After filtering, found {len(cobol_files)} cobol files")

        for cobol_file in cobol_files:
            self.nr_testcases_content_generated += 1
            logger.info("\n" + "=" * 120)
            logger.info(
                f"Generating testcase {self.nr_testcases_content_generated}/{self.nr_total_testcases} | "
                f"Processing: {cobol_file}"
            )
            logger.info("=" * 120)

            try:
                self._generate_single_test_case(cobol_file)
            except Exception as e:
                logger.error(
                    f"Failed to generate test case for {cobol_file}: {e}", exc_info=True
                )

    def _generate_single_test_case(self, cobol_file: Path):
        """Generate test case for a single COBOL file"""
        full_code = cobol_file.read_text(encoding="utf-8", errors="ignore")

        output_dir = self.test_cases_dir / f"test_{cobol_file.stem}"
        shutil.rmtree(output_dir, ignore_errors=True)
        output_dir.mkdir(parents=True, exist_ok=True)
        logger.info(f"Setting up test case in {output_dir}")

        # Generate test data using LLM
        test_data = self._generate_test_data_with_llm(full_code, cobol_file.name)

        if test_data is None:
            logger.warning(f"Failed to generate test data for {cobol_file.name}")
            test_data = TestDataSchema()  # Use empty schema as fallback

        # Build final metadata structure
        metadata = {
            "cobol_file": {"file_name": cobol_file.name, "content": full_code},
            "input_files": [
                {"file_name": f.filename, "content": f.content}
                for f in test_data.input_files
            ],
            "output_files": test_data.output_files,
            "input_output_files": [
                {"file_name": f.filename, "content": f.content}
                for f in test_data.input_output_files
            ],
            "command_line_args": test_data.command_line_args,
            "stdin": {
                "enabled": test_data.stdin.enabled,
                "source_file": test_data.stdin.source_file,
            }
            if test_data.stdin.enabled
            else None,
            "sysin_file": {
                "file_name": test_data.sysin_file.filename,
                "content": test_data.sysin_file.content,
            }
            if test_data.sysin_file
            else None,
        }

        # Save metadata
        metadata_file = output_dir / "test_metadata.json"
        with open(metadata_file, "w", encoding="utf-8") as f:
            json.dump(metadata, f, indent=2)

        logger.info(f"Generated test metadata at {metadata_file}")
        logger.info(f"  - Input files: {len(test_data.input_files)}")
        logger.info(f"  - Output files: {len(test_data.output_files)}")
        logger.info(f"  - Input/Output files: {len(test_data.input_output_files)}")
        logger.info(f"  - Command line args: {len(test_data.command_line_args)}")
        logger.info(f"  - Stdin enabled: {test_data.stdin.enabled}")
        logger.info(f"  - SYSIN file: {'Yes' if test_data.sysin_file else 'No'}")

    def _generate_test_data_with_llm(
        self, cobol_code: str, filename: str
    ) -> Optional[TestDataSchema]:
        """Use LLM to analyze COBOL code and generate test data"""

        # Create schema example for the LLM
        schema_example = {
            "input_files": [
                {
                    "filename": "customers.dat",
                    "content": "00001JOHN DOE        123 MAIN ST       \n00002JANE SMITH      456 ELM AVE       ",
                }
            ],
            "output_files": ["report.txt", "summary.dat"],
            "input_output_files": [
                {
                    "filename": "master.dat",
                    "content": "ITEM001WIDGET     000125000000099950\nITEM002GADGET     000050000000149900",
                }
            ],
            "command_line_args": ["input.dat", "2024", "output.txt"],
            "stdin": {"enabled": True, "source_file": "stdin_data.txt"},
            "sysin_file": {"filename": "sysin.txt", "content": "5\ntest input\n9999"},
        }

        system_prompt = """You are an expert COBOL developer and test data generator.
Your task is to analyze COBOL programs and generate complete, realistic test data.

Key responsibilities:
1. Identify all file dependencies (SELECT...ASSIGN statements in FILE-CONTROL section)
2. Determine file types by analyzing usage:
   - INPUT: Files opened with "OPEN INPUT" or only READ
   - OUTPUT: Files opened with "OPEN OUTPUT" or only WRITE
   - INPUT-OUTPUT: Files opened with "OPEN I-O" or both READ and WRITE
3. Generate realistic file content matching COBOL record structures (look at FD sections and PIC clauses)
4. Identify command-line argument usage (ACCEPT FROM COMMAND-LINE/ARGUMENT-VALUE/ARGUMENT-NUMBER)
5. Detect stdin usage (ACCEPT statements without FROM clause, or explicit stdin redirection)
6. Detect SYSIN usage (ACCEPT FROM SYSIN or bare ACCEPT in mainframe-style programs)

COBOL data format rules:
- PIC 9(n) = n numeric digits, right-aligned with leading zeros
- PIC X(n) = n characters, left-aligned with trailing spaces  
- PIC S9(n) = signed numeric, can be negative
- PIC V indicates implied decimal (no actual decimal point in data)
- Fixed-length records must be exact length (pad with spaces/zeros)

Generate realistic business data (customer records, transactions, inventory, etc.) that would be typical for COBOL batch processing applications."""

        user_prompt = f"""Analyze this COBOL program and generate ALL necessary test data in the EXACT JSON format specified below.

COBOL Program: {filename}

```cobol
{cobol_code}
```

REQUIRED OUTPUT FORMAT (this is the exact JSON schema you must follow):
```json
{json.dumps(schema_example, indent=2)}
```

Your task:
1. **Identify all files** from SELECT...ASSIGN statements in FILE-CONTROL section
2. **For each file, determine its type:**
   - Check if opened with "OPEN INPUT" → input_files (MUST include content)
   - Check if opened with "OPEN OUTPUT" → output_files (filename only, no content)
   - Check if opened with "OPEN I-O" → input_output_files (MUST include content)
   - Analyze READ/WRITE usage if OPEN type is unclear

3. **Generate file content** for input and input_output files:
   - Look at FD (File Description) sections to find record structure
   - Look at WORKING-STORAGE for record layouts (01 level entries)
   - Match PIC clauses exactly (field lengths, types, positions)
   - Generate 5-10 realistic data records
   - Each record must be exact length (pad with spaces for X fields, zeros for 9 fields)

4. **Command-line arguments:**
   - Check for ACCEPT...FROM ARGUMENT-VALUE or ACCEPT...FROM COMMAND-LINE
   - Look at variable names to infer what arguments are expected (InputPath, FileName, etc.)
   - Generate 2-5 realistic arguments matching expected types

5. **Stdin detection:**
   - If program uses shell redirection (./program < file), set stdin.enabled=true and provide source_file
   - Look for bare ACCEPT statements (without FROM clause) that read from stdin

6. **SYSIN detection:**
   - Check for ACCEPT...FROM SYSIN
   - Check for SELECT SYSIN ASSIGN TO SYSIN patterns
   - Generate appropriate test input if found

CRITICAL REQUIREMENTS:
- Return ONLY valid JSON matching the schema above
- Do NOT include markdown code blocks, explanations, or any other text
- Input and input_output files MUST have "content" field with realistic data
- Output files should only have filename strings (no content)
- Match COBOL record structures EXACTLY (field positions, lengths, types)
- Use realistic business data (names, addresses, amounts, dates, IDs)
- Numeric fields: right-align with leading zeros, exact length
- Alphanumeric fields: left-align with trailing spaces, exact length
- If a file is assigned to a variable (e.g., ASSIGN TO InputPath), use a realistic filename
- Command-line args should match expected types from the program logic

Return ONLY the JSON object, nothing else."""

        try:
            # Get LLM response
            response = llm.predict(prompt=user_prompt, system_prompt=system_prompt)

            # Clean response (remove markdown if present)
            response = response.strip()
            if response.startswith("```json"):
                response = response[7:]
            if response.startswith("```"):
                response = response[3:]
            if response.endswith("```"):
                response = response[:-3]
            response = response.strip()

            logger.info(f"Raw LLM response (first 500 chars): {response[:500]}")

            # Parse JSON
            data_dict = json.loads(response)

            # Validate and parse with Pydantic
            test_data = TestDataSchema(**data_dict)

            logger.info("Successfully generated test data with LLM")
            logger.info(
                f"  Generated {len(test_data.input_files)} input files with content"
            )
            logger.info(f"  Generated {len(test_data.output_files)} output file names")
            logger.info(
                f"  Generated {len(test_data.input_output_files)} input-output files with content"
            )

            return test_data

        except json.JSONDecodeError as e:
            logger.error(f"Failed to parse LLM JSON response: {e}")
            logger.error(f"Response was: {response[:1000]}")
            return None
        except Exception as e:
            logger.error(f"Error generating test data: {e}", exc_info=True)
            return None


def reconstruct_files_from_metadata(metadata_file: Path, output_dir: Path):
    """
    Utility function to reconstruct all files from a metadata.json file.
    Useful for validation or re-creating test environments.
    """
    with open(metadata_file, "r", encoding="utf-8") as f:
        metadata = json.load(f)

    output_dir.mkdir(parents=True, exist_ok=True)

    # Reconstruct COBOL source file
    cobol_info = metadata.get("cobol_file", {})
    if cobol_info.get("content"):
        cobol_path = output_dir / cobol_info["file_name"]
        cobol_path.write_text(cobol_info["content"], encoding="utf-8")
        print(f"Created: {cobol_path}")

    # Reconstruct input files
    for file_info in metadata.get("input_files", []):
        file_path = output_dir / file_info["file_name"]
        file_path.write_text(file_info["content"], encoding="utf-8")
        print(f"Created: {file_path}")

    # Reconstruct input-output files
    for file_info in metadata.get("input_output_files", []):
        file_path = output_dir / file_info["file_name"]
        file_path.write_text(file_info["content"], encoding="utf-8")
        print(f"Created: {file_path}")

    # Create empty output files (optional)
    for filename in metadata.get("output_files", []):
        file_path = output_dir / filename
        file_path.touch()
        print(f"Created (empty): {file_path}")

    # Reconstruct stdin source file if present
    stdin_info = metadata.get("stdin")
    if stdin_info and stdin_info.get("enabled") and stdin_info.get("source_file"):
        # Check if content is in input_files
        source_filename = stdin_info["source_file"]
        found = False
        for file_info in metadata.get("input_files", []):
            if file_info["file_name"] == source_filename:
                print(
                    f"Stdin redirection: < {source_filename} (content in input_files)"
                )
                found = True
                break
        if not found:
            print(
                f"Stdin redirection: < {source_filename} (WARNING: source file not found in input_files)"
            )

    # Reconstruct SYSIN file
    sysin_info = metadata.get("sysin_file")
    if sysin_info:
        file_path = output_dir / sysin_info["file_name"]
        file_path.write_text(sysin_info["content"], encoding="utf-8")
        print(f"Created: {file_path}")

    # Print command line args
    cmd_args = metadata.get("command_line_args", [])
    if cmd_args:
        print(f"Command line args: {' '.join(cmd_args)}")

    print(f"\nAll files reconstructed in: {output_dir}")


if __name__ == "__main__":
    # cobol_directory = Path(os.environ.get("DATA_DIR", "src_data"))
    # assert cobol_directory.exists(), f"Directory {cobol_directory} does not exist."
    # output_directory = Path(os.environ.get("TESTS_DIR", "testcases"))
    # output_directory.mkdir(parents=True, exist_ok=True)

    # contentGenerator = SampleContentGenerator(cobol_directory, output_directory)
    # contentGenerator.generate_test_cases()
    # print("Test case generation completed.")
    Path("reconstructed_testcase_successful_snippet_4b0f3842").mkdir(
        parents=True, exist_ok=True
    )
    reconstruct_files_from_metadata(
        Path("testcases/test_successful_snippet_73c6cab7/test_metadata.json"),
        Path("test_successful_snippet_73c6cab7"),
    )
    print("File reconstruction completed.")
