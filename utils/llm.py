from transformers import AutoModelForCausalLM, AutoTokenizer, GenerationConfig
from pathlib import Path

# Environment Variables
import os
from dotenv import load_dotenv
import logging

load_dotenv()


gen_config = GenerationConfig(
    max_new_tokens=2048,
    do_sample=False,
)

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


class LLM:
    """
    Easy wrapper around huggingface model for predicting text
    """

    def __init__(self):
        self.model_name = os.getenv("MODEL_NAME", "Qwen/Qwen2.5-Coder-7B-Instruct")
        logger.info(f"Loading model: {self.model_name}")

        self.model = AutoModelForCausalLM.from_pretrained(
            self.model_name, torch_dtype="auto", device_map="auto"
        )
        # You can set the temperature in the GenerationConfig
        # For example, to set temperature to 0.7:
        self.tokenizer = AutoTokenizer.from_pretrained(self.model_name)

    def predict(
        self, prompt: str, system_prompt: str = "You are a helpful assistant."
    ) -> str:
        messages = [
            {"role": "system", "content": system_prompt},
            {"role": "user", "content": prompt},
        ]
        text = self.tokenizer.apply_chat_template(
            messages, tokenize=False, add_generation_prompt=True
        )
        model_inputs = self.tokenizer([text], return_tensors="pt").to(self.model.device)

        generated_ids = self.model.generate(
            **model_inputs, generation_config=gen_config
        )
        generated_ids = [
            output_ids[len(input_ids) :]
            for input_ids, output_ids in zip(model_inputs.input_ids, generated_ids)
        ]

        response = self.tokenizer.batch_decode(generated_ids, skip_special_tokens=True)[
            0
        ].strip()

        if not response:
            logger.warning("response from LLM is empty!")

        # Model tends to wrapp the whole code in Markdown Code brackets
        if response.startswith("```"):
            response = response.split("\n", 1)[1]  # remove first line
        if response.endswith("```"):
            response = "\n".join(response.split("\n")[:-1])  # remove last line

        response = response.strip()

        return response
