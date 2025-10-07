import os
from dotenv import load_dotenv
from pathlib import Path
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
