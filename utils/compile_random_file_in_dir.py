import os
import random
import subprocess
from dotenv import load_dotenv
load_dotenv()
from pathlib import Path
# Path to the data folder
data_folder = Path(os.environ.get("DATA_DIR", "data"))
data_folder = data_folder / "cobol_theStack" / "gt100"
# List all COBOL files in the data folder
cobol_files = [f for f in os.listdir(data_folder) if f.lower().endswith('.cob')]

if not cobol_files:
    raise FileNotFoundError("No COBOL files found in the 'data' folder.")

# Pick a random COBOL file
random_cobol = random.choice(cobol_files)
cobol_path = os.path.join(data_folder, random_cobol)

# Output binary/executable name (without extension)
output_name = os.path.splitext(random_cobol)[0]

# Compile the COBOL file using GnuCOBOL (cobc)
compile_cmd = ['cobc', '-x', '-o', os.path.join(data_folder, output_name), cobol_path]
result = subprocess.run(compile_cmd, capture_output=True, text=True)

if result.returncode != 0:
    print(f"Compilation failed:\n{result.stderr}")
else:
    print(f"Compiled '{random_cobol}' to '{output_name}' in the data folder.")