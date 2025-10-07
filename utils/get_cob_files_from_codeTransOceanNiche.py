from pathlib import Path
import json
from dotenv import load_dotenv
import os
import subprocess
import shutil
from tqdm import tqdm
from concurrent.futures import ThreadPoolExecutor


import logging

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
load_dotenv()


src_dir = Path(os.environ["DATA_DIR"]) / "tst"
src_dir.mkdir(parents=True, exist_ok=True)
tmp_dir = Path(os.environ["DATA_DIR"]) / "tmp"
tmp_dir.mkdir(parents=True, exist_ok=True)


def process_code_snippet(code_snippet: str):
    temp_file_path = src_dir / f"temp_{os.urandom(4).hex()}.cob"
    with open(temp_file_path, "w") as temp_file:
        temp_file.write(code_snippet)

    compile_success = compile_cobol(temp_file_path)

    if compile_success:
        success_file_path = src_dir / f"successful_snippet_{os.urandom(4).hex()}.cob"
        shutil.move(temp_file_path, success_file_path)
    else:
        os.remove(temp_file_path)


def compile_cobol(file_path: Path) -> bool:
    try:
        result = subprocess.run(
            ["cobc", "-x", str(file_path), "-o", str(tmp_dir / file_path.stem)],
            capture_output=True,
        )
        return result.returncode == 0
    except Exception:
        return False


def get_snippets(file):
    cobol_snippets = []
    with open(file, "r") as f:
        lines = f.readlines()
    for line in lines:
        try:
            obj = json.loads(line)
            if "COBOL" in obj:
                cobol_snippets.append(obj["COBOL"])
        except json.JSONDecodeError:
            continue
    return cobol_snippets


files = list(
    Path("/home/schafhdaniel@edu.local/Cob2Java-Bench/codeTransOceanNiche").glob(
        "*.json"
    )
)

for file in files:
    cobol_snippets = get_snippets(file)
    with ThreadPoolExecutor() as executor:
        list(
            tqdm(
                executor.map(process_code_snippet, cobol_snippets),
                total=len(cobol_snippets),
            )
        )


shutil.rmtree(tmp_dir, ignore_errors=True)
