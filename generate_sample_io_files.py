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

llm = llm.LLM()


@dataclass
class FileInfo:
    path: str
    file_type: str  # 'input', 'output', 'input-output', 'sysin'
    record_format: Optional[str] = None
    record_length: Optional[int] = None
    organization: Optional[str] = None
    access_mode: Optional[str] = None


class SampleContentGenerator:
    def __init__(self, cobol_directory: Path, test_cases_dir: Path):
        self.cobol_directory = Path(cobol_directory)
        self.test_cases_dir = Path(test_cases_dir)
        self.metadata = {}

        self.nr_testcases_content_generated = 0
        self.nr_total_testcases = 0

    def generate_test_cases(self):
        # Clean up empty test case directories (may be leftover from previous runs that had errors)
        for f in self.test_cases_dir.glob("*"):
            if f.is_dir():
                if len(list(f.glob("*"))) == 0:
                    logger.info(f"Removing empty testcases dir: {f}")
                    f.rmdir()

        cobol_files = (
            list(self.cobol_directory.glob("*.cbl"))
            + list(self.cobol_directory.glob("*.cob"))
            + list(self.cobol_directory.glob("*.cobol"))
        )
        self.nr_total_testcases = len(cobol_files)
        np.random.shuffle(cobol_files)

        # dont process the files that are already in testcases
        logger.info(f"before filtering, found {len(cobol_files)} cobol files")
        files_already_in_testcases = os.listdir(self.test_cases_dir)

        self.nr_testcases_content_generated = len(files_already_in_testcases)
        cobol_files = [
            f for f in cobol_files if "test_" + f.stem not in files_already_in_testcases
        ]

        logger.info(f"after filtering, found {len(cobol_files)} cobol files")
        for cobol_file in cobol_files:
            self.nr_testcases_content_generated += 1
            logger.info("\n")
            logger.info("=" * 120)
            logger.info(
                f"Generating testcase file data {self.nr_testcases_content_generated}/{self.nr_total_testcases} | Processing COBOL file: {cobol_file} "
            )
            logger.info("=" * 120)
            sample_content = None
            test_metadata = {
                "cobol_file": {"file_name": cobol_file.name, "content": None},
                "input_files": [],
                "output_files": [],
                "input_output_files": [],
                "sysin_file": None,  # New field for SYSIN
            }

            full_code = cobol_file.read_text()
            test_metadata["cobol_file"]["content"] = full_code

            output_dir = self.test_cases_dir / f"test_{cobol_file.stem}"
            shutil.rmtree(output_dir, ignore_errors=True)
            output_dir.mkdir(parents=True, exist_ok=True)
            logger.info(f"Setting up test cases for {cobol_file} in {output_dir}")

            files_info = self.get_files_info_from_cobol_file(cobol_file)

            # Check for SYSIN usage
            sysin_info = self._check_sysin_usage(cobol_file)
            if sysin_info:
                files_info["SYSIN"] = sysin_info

            for file_path, file_info in files_info.items():
                sample_content = None
                if file_info.file_type == "sysin":
                    # Generate SYSIN content
                    sample_content = self._generate_sysin_content(file_info, full_code)

                    if sample_content:
                        file_content = {
                            "file_name": "sysin.txt",
                            "content": sample_content,
                        }
                        test_metadata["sysin_file"] = file_content
                        logger.info(f"Generated SYSIN sample data")

                elif file_info.file_type == "input":
                    cobol_context = self._get_cobol_context_for_file(
                        cobol_files, file_path
                    )
                    sample_content = self._generate_sample_content(
                        file_info, cobol_context, full_code
                    )

                    if sample_content:
                        file_content = {
                            "file_name": file_path,
                            "content": sample_content,
                        }
                        test_metadata["input_files"].append(file_content)

                elif file_info.file_type == "output":
                    test_metadata["output_files"].append(file_path)

                elif file_info.file_type == "input-output":
                    cobol_context = self._get_cobol_context_for_file(
                        cobol_files, file_path
                    )
                    sample_content = self._generate_sample_content(
                        file_info, cobol_context, full_code
                    )

                    if sample_content:
                        file_content = {
                            "file_name": file_path,
                            "content": sample_content,
                        }
                        test_metadata["input_output_files"].append(file_content)

                if sample_content:
                    logger.info(
                        f"sample_content for {file_info.file_type}: \n{sample_content[:400] if sample_content and len(sample_content) > 400 else sample_content or ''}"
                    )
            logger.info(f"Generated test metadata")

            logger.info(
                f"Copied COBOL file to test directory {output_dir / cobol_file.name}"
            )

            # Save metadata as JSON
            metadata_file = output_dir / "test_metadata.json"
            with open(metadata_file, "w") as f:
                json.dump(test_metadata, f, indent=2)

            logger.info(f"Generated test metadata at {metadata_file}")

    def _check_sysin_usage(self, cobol_file: Path) -> Optional[FileInfo]:
        """Check if the COBOL program uses SYSIN for input"""
        with open(cobol_file, "r", encoding="utf-8", errors="ignore") as f:
            content = f.read()

        content_upper = content.upper()

        # Check for common SYSIN patterns
        sysin_patterns = [
            r"ACCEPT\s+\w+\s+FROM\s+SYSIN",
            r"ACCEPT\s+(?!.*\s+FROM\s+(DATE|TIME|LINES|COLUMNS|OMITTED|DAY)\b|Entry-Screen\b|DAY-OF-WEEK\b|ENVIRONMENT\b|ARGUMENT-NUMBER\b|ARGUMENT-VALUE\b|COMMAND-LINE\b)\w+",
            r"SELECT\s+SYSIN",
            r"ASSIGN\s+TO\s+SYSIN",
        ]

        has_sysin = any(
            re.search(pattern, content_upper, re.IGNORECASE)
            for pattern in sysin_patterns
        )

        if has_sysin:
            # Try to determine what kind of input is expected
            record_info = self._analyze_sysin_structure(content)

            return FileInfo(
                path="SYSIN",
                file_type="sysin",
                record_format=record_info.get("format"),
                record_length=record_info.get("length"),
                organization="line sequential",
                access_mode="sequential",
            )

        return None

    def _analyze_sysin_structure(self, content: str) -> Dict:
        """Analyze what kind of SYSIN input the program expects"""
        info = {}

        # Look for ACCEPT statements to understand input structure
        accept_pattern = r"ACCEPT\s+([A-Z0-9\-]+)(?:\s+FROM\s+SYSIN)?"
        accepts = re.findall(accept_pattern, content.upper())

        if accepts:
            # Try to find the field definitions in WORKING-STORAGE
            for field in accepts:
                field_pattern = rf"\d+\s+{field}\s+PIC\s+([^\.\n]+)"
                field_match = re.search(field_pattern, content.upper())
                if field_match:
                    pic_clause = field_match.group(1).strip()
                    info["pic_clause"] = pic_clause
                    info["field_name"] = field
                    # Estimate length from PIC clause
                    length_match = re.search(r"[X9]+\((\d+)\)", pic_clause)
                    if length_match:
                        info["length"] = int(length_match.group(1))
                    break

        return info

    def _generate_sysin_content(
        self, file_info: FileInfo, cobol_context: str
    ) -> Optional[str]:
        """Generate sample SYSIN content based on ACCEPT statements in the program"""

        # Extract the complete record structure for ACCEPT
        record_structure = self._extract_sysin_record_structure(cobol_context)

        # Extract information about what the program accepts
        accept_info = self._extract_accept_info(cobol_context)

        # Extract termination condition
        termination_info = self._extract_sysin_termination(cobol_context)

        context_info = f"""
        SYSIN Input for COBOL Program
        
        The program uses ACCEPT statements to read from standard input (SYSIN).
        
        Detected ACCEPT fields: {accept_info if accept_info else "Generic input expected"}
        
        Record Structure:
        {record_structure if record_structure else "Could not determine structure"}
        
        Termination Condition:
        {termination_info if termination_info else "No explicit termination found"}
        
        COBOL Context (ACCEPT and field definitions):
        {self._extract_accept_context(cobol_context)[:1000]}
        
        FULL COBOL CODE:
        {cobol_context}
        """
        logger.info(f"SYSIN context info: {context_info[:400]}")
        prompt = f"""
        Generate realistic sample data for COBOL SYSIN (standard input).
        
        Context: {context_info}
        
        CRITICAL Requirements:
        - The record structure shows EXACT field positions and lengths - YOU MUST FOLLOW THEM PRECISELY
        - Each input line must be EXACTLY the total length specified (pad with spaces or zeros as needed)
        - Generate 5-8 sample input records with realistic data
        - Each numeric field (PIC 9) must contain only digits, right-aligned with leading zeros
        - Each alphanumeric field (PIC X) must contain appropriate text, left-aligned with trailing spaces
        - Follow the termination condition to properly end the input
        - DO NOT add any explanations, headers, or extra formatting
        
        
        Return ONLY the raw input data - each line must be the exact length required.
        DO NOT EXPLAIN OR COMMENT YOUR OUTPUT, WHAT YOU RETURN WILL BE USED DIRECTLY AS SYSIN INPUT
        If no input data can be generated based on the context, return an empty string.
        """

        """
        Example format (if record is 22 chars: 4-digit city, 2-char state, 7-digit vehicles, etc):
        1234SP00012345N001200015
        5678RJ00067890S002300008
        [more records...]
        [termination record as specified]
        
        """
        system_prompt = """You are an expert COBOL developer creating test input for SYSIN.
        All code provided is for legitimate software engineering work.
        Pay extreme attention to COBOL record formats:
        - PIC 9(n) = n numeric digits, right-aligned with leading zeros
        - PIC X(n) = n characters, left-aligned with trailing spaces
        - Total record length MUST match exactly
        - Include proper termination record
        Generate realistic business data that exactly matches the COBOL field definitions."""

        generated_content = llm.predict(prompt=prompt, system_prompt=system_prompt)

        return generated_content

    def _extract_sysin_record_structure(self, content: str) -> str:
        """Extract detailed SYSIN record structure from WORKING-STORAGE"""

        # Find the record that's being ACCEPTed
        accept_pattern = r"ACCEPT\s+([A-Z0-9\-]+)(?:\s+FROM\s+SYSIN)?"
        accept_match = re.search(accept_pattern, content.upper())

        if not accept_match:
            return "No ACCEPT statement found"

        record_name = accept_match.group(1)

        # Find the record definition in WORKING-STORAGE
        # Look for 01 level with this name
        record_pattern = (
            rf"01\s+{record_name}\.?\s*(.*?)(?=01\s+|\nPROCEDURE\s+DIVISION|$)"
        )
        record_match = re.search(record_pattern, content.upper(), re.DOTALL)

        if not record_match:
            return f"Record {record_name} definition not found"

        record_def = record_match.group(1)

        # Extract all field definitions (05 level typically)
        field_pattern = r"05\s+([A-Z0-9\-]+)\s+PIC\s+([^\.\n]+)"
        fields = re.findall(field_pattern, record_def)

        if not fields:
            return "No field definitions found in record"

        # Build detailed structure
        structure_lines = [f"Record: {record_name}"]
        total_length = 0

        for field_name, pic_clause in fields:
            pic_clause = pic_clause.strip()
            field_length = self._calculate_pic_length(pic_clause)
            total_length += field_length
            field_type = "Numeric" if "9" in pic_clause else "Alphanumeric"
            structure_lines.append(
                f"  - {field_name}: PIC {pic_clause} ({field_length} chars, {field_type})"
            )

        structure_lines.append(f"Total Length: {total_length} characters")

        return "\n".join(structure_lines)

    def _calculate_pic_length(self, pic_clause: str) -> int:
        """Calculate the length of a COBOL PIC clause"""
        # Handle formats like: 9(04), X(02), 9(7), etc.
        match = re.search(r"[9XxAa]+\((\d+)\)", pic_clause)
        if match:
            return int(match.group(1))

        # Handle formats like: 9999, XXXX, etc.
        count = 0
        for char in pic_clause:
            if char in "9XxAa":
                count += 1

        return count if count > 0 else 1

    def _extract_sysin_termination(self, content: str) -> str:
        """Extract the termination condition for SYSIN input"""

        # Look for common termination patterns
        termination_patterns = [
            (
                r"IF\s+([A-Z0-9\-]+)\s*=\s*ALL\s+'9'",
                "End when record = ALL '9' (all nines)",
            ),
            (
                r"IF\s+([A-Z0-9\-]+)\s*=\s*'9+'\s",
                "End when record = '999...' (all nines)",
            ),
            (r"IF\s+([A-Z0-9\-]+)\s*=\s*SPACES", "End when record = SPACES"),
            (r"IF\s+([A-Z0-9\-]+)\s*=\s*HIGH-VALUES", "End when record = HIGH-VALUES"),
            (r"UNTIL\s+([A-Z0-9\-]+)\s*=\s*'S'", "End when flag = 'S'"),
        ]

        content_upper = content.upper()

        for pattern, description in termination_patterns:
            match = re.search(pattern, content_upper)
            if match:
                return f"{description} (Variable: {match.group(1)})"

        return "No explicit termination condition found (may loop indefinitely)"

    def _extract_accept_info(self, content: str) -> str:
        """Extract information about ACCEPT statements"""
        accept_pattern = r"ACCEPT\s+([A-Z0-9\-]+)(?:\s+FROM\s+SYSIN)?"
        accepts = re.findall(accept_pattern, content.upper())

        if not accepts:
            return "No specific ACCEPT statements found"

        info_parts = []
        for field in accepts:  # Limit to first 5 fields
            field_pattern = rf"\d+\s+{field}\s+PIC\s+([^\.\n]+)"
            field_match = re.search(field_pattern, content.upper())
            if field_match:
                pic_clause = field_match.group(1).strip()
                info_parts.append(f"{field} (PIC {pic_clause})")
            else:
                info_parts.append(field)

        return ", ".join(info_parts)

    def _extract_accept_context(self, content: str) -> str:
        """Extract relevant context around ACCEPT statements"""
        lines = content.split("\n")
        context_lines = []

        for i, line in enumerate(lines):
            if "ACCEPT" in line.upper():
                # Get a few lines of context
                start = max(0, i - 2)
                end = min(len(lines), i + 3)
                context_lines.extend(lines[start:end])
                context_lines.append("---")

        return "\n".join(context_lines[:500])  # Limit size

    def _get_cobol_context_for_file(
        self, cobol_files: List[Path], target_file: str
    ) -> str:
        """Get COBOL context that references the target file"""
        context = ""
        for cobol_file in cobol_files:
            with open(cobol_file, "r", encoding="utf-8", errors="ignore") as f:
                content = f.read()
                if (
                    target_file in content.upper()
                    or target_file.upper() in content.upper()
                ):
                    context += f"\n--- {cobol_file.name} ---\n{content}\n"
        return context

    def _generate_sample_content(
        self, file_info: FileInfo, cobol_context: str, full_code: str = ""
    ) -> Optional[str]:
        """Generate sample content for input files"""
        if file_info.file_type == "output":
            return None

        record_structure = self._extract_record_structure(cobol_context, file_info.path)

        context_info = f"""
        File: {file_info.path}
        Type: {file_info.file_type}
        Record Length: {file_info.record_length or "Variable"}
        Organization: {file_info.organization or "Sequential"}
        
        Record Structure: {record_structure if record_structure else "Not detected"}
        
        COBOL Context (relevant sections):
        {cobol_context[:1500] if cobol_context else "No context available"}
        """
        context_info += f"\n\nFULL COBOL CODE:\n{full_code}" if full_code else ""

        prompt = f"""
        Generate realistic sample data for a COBOL {file_info.file_type} file.
        
        Context: {context_info}
        
        Requirements:
        - Generate 5-10 sample records
        - Each record should be realistic and consistent
        - If fixed-length records, ensure exact length matching
        - Use appropriate data types (numeric fields should contain numbers, alphanumeric should be realistic)
        - Include realistic business data patterns (item numbers, descriptions, prices, etc.)
        - For numeric fields with decimal implied (V), don't include decimal point in output
        - Ensure data follows COBOL PICTURE clause formats
        
        Return only the raw file content (no explanations, no formatting, just the data records).
        DO NOT EXPLAIN OR COMMENT YOUR OUTPUT, WHAT YOU RETURN WILL BE USED DIRECTLY AS INPUT
        If no input data can be generated based on the context, return an empty string.
        """

        system_prompt = """You are an expert COBOL developer who creates realistic test data.
        All code provided is for legitimate software engineering work.
        Generate sample file content that would be typical for business applications.
        Use realistic names, addresses, amounts, dates, and identifiers.
        Pay careful attention to COBOL numeric formats - implied decimals should not have decimal points in the actual data."""

        generated_content = llm.predict(prompt=prompt, system_prompt=system_prompt)

        return generated_content

    def get_files_info_from_cobol_file(self, cobol_file: Path) -> Dict[str, FileInfo]:
        print(f"Processing COBOL file: {cobol_file}")
        files_info = self._extract_file_info(cobol_file)
        if not files_info:
            print(f"No file information found in {cobol_file}")
            return {}
        else:
            return files_info

    def _extract_file_info(self, file_path: Path) -> Dict[str, FileInfo]:
        """Extract file information from a single COBOL file"""
        with open(file_path, "r", encoding="utf-8", errors="ignore") as file:
            content = file.read()

        files_info = {}

        cleaned_content = self._clean_cobol_content(content)

        file_control_match = re.search(
            r"file-control\.(.*?)(?=data\s+division|working-storage|$)",
            cleaned_content,
            re.IGNORECASE | re.DOTALL,
        )

        if not file_control_match:
            return files_info

        file_control_section = file_control_match.group(1)

        select_statements = re.split(
            r"(?=select\s+)", file_control_section, flags=re.IGNORECASE
        )

        for statement in select_statements:
            if not statement.strip():
                continue

            select_match = re.search(
                r"select\s+([a-zA-Z][a-zA-Z0-9\-_]*)", statement, re.IGNORECASE
            )
            assign_match = re.search(
                r'assign\s+to\s+["\']([^"\']+)["\']', statement, re.IGNORECASE
            )

            if select_match and assign_match:
                logical_name = select_match.group(1)
                physical_path = assign_match.group(1)

                file_type = self._determine_file_type(content, logical_name)

                record_info = self._extract_record_info(content, logical_name)

                files_info[physical_path] = FileInfo(
                    path=physical_path,
                    file_type=file_type,
                    record_format=record_info.get("format"),
                    record_length=record_info.get("length"),
                    organization=record_info.get("organization"),
                    access_mode=record_info.get("access_mode"),
                )

        return files_info

    def _extract_record_structure(self, content: str, file_path: str) -> str:
        """Extract record structure information from COBOL content"""
        if not content:
            return ""

        record_pattern = r"01\s+\w+.*?(?=01\s+|\nFD\s+|\nWORKING-STORAGE|$)"
        matches = re.findall(record_pattern, content, re.IGNORECASE | re.DOTALL)

        structure_info = []
        for match in matches:
            if "pic" in match.lower():
                field_pattern = r"05\s+(\S+)\s+pic\s+([^.\n]+)"
                fields = re.findall(field_pattern, match, re.IGNORECASE)
                if fields:
                    structure_info.append(f"Record fields: {fields}")

        return " | ".join(structure_info) if structure_info else ""

    def _clean_cobol_content(self, content: str) -> str:
        """Clean COBOL content by removing comments and normalizing whitespace"""
        lines = content.split("\n")
        cleaned_lines = []

        for line in lines:
            if len(line) > 6 and line[6] == "*":
                continue
            if "*" in line:
                comment_pos = line.find("*")
                if comment_pos > 6:
                    line = line[:comment_pos]
            cleaned_lines.append(line)

        cleaned = " ".join(cleaned_lines)
        cleaned = re.sub(r"\s+", " ", cleaned)
        return cleaned

    def _determine_file_type(self, content: str, logical_name: str) -> str:
        """Determine if file is input, output, or input-output based on COBOL operations"""
        content_upper = content.upper()
        logical_name_upper = logical_name.upper()

        read_pattern = f"READ\\s+{logical_name_upper}"
        write_pattern = f"WRITE\\s+{logical_name_upper}"
        open_input_pattern = f"OPEN\\s+INPUT\\s+{logical_name_upper}"
        open_output_pattern = f"OPEN\\s+OUTPUT\\s+{logical_name_upper}"
        open_io_pattern = f"OPEN\\s+I-O\\s+{logical_name_upper}"

        has_read = bool(re.search(read_pattern, content_upper, re.IGNORECASE))
        has_write = bool(re.search(write_pattern, content_upper, re.IGNORECASE))
        has_open_input = bool(
            re.search(open_input_pattern, content_upper, re.IGNORECASE)
        )
        has_open_output = bool(
            re.search(open_output_pattern, content_upper, re.IGNORECASE)
        )
        has_open_io = bool(re.search(open_io_pattern, content_upper, re.IGNORECASE))

        if has_open_io or (has_read and has_write):
            return "input-output"
        elif has_write or has_open_output:
            return "output"
        else:
            return "input"

    def _extract_record_info(self, content: str, logical_name: str) -> Dict:
        """Extract record format information for a file"""
        info = {}

        fd_pattern = (
            f"FD\\s+{logical_name}\\b.*?(?=FD\\s+|WORKING-STORAGE|01\\s+\\w+|$)"
        )
        fd_match = re.search(fd_pattern, content, re.IGNORECASE | re.DOTALL)

        if fd_match:
            fd_content = fd_match.group(0)

            record_len_pattern = r"record\s+contains\s+(\d+)\s+characters"
            len_match = re.search(record_len_pattern, fd_content, re.IGNORECASE)
            if len_match:
                info["length"] = int(len_match.group(1))

            if (
                "line sequential" in fd_content.lower()
                or "line sequential" in content.lower()
            ):
                info["organization"] = "line sequential"
            elif "sequential" in fd_content.lower():
                info["organization"] = "sequential"
            elif "indexed" in fd_content.lower():
                info["organization"] = "indexed"
            elif "relative" in fd_content.lower():
                info["organization"] = "relative"

        return info


if __name__ == "__main__":
    cobol_directory = Path(os.environ.get("DATA_DIR", "src_data"))
    assert cobol_directory.exists(), f"Directory {cobol_directory} does not exist."
    output_directory = Path(os.environ.get("TESTS_DIR", "testcases"))
    output_directory.mkdir(parents=True, exist_ok=True)

    contentGenerator = SampleContentGenerator(cobol_directory, output_directory)
    contentGenerator.generate_test_cases()
    print("Test case generation completed.")

    # this programm is not perfect yet. Could be improved to handle command line arguments and sysin better.
