"""
Simplified wrapper for Anthropic Claude using the Messages API.
Preserves the LLM class interface: predict(prompt, system_prompt=...)
"""

import os
import time
import logging
from dotenv import load_dotenv
from anthropic import Anthropic, RateLimitError

load_dotenv()
logger = logging.getLogger(__name__)


class LLM:
    """Wrapper to call Anthropic Claude via the Messages API."""

    def __init__(self):
        self.api_key = os.getenv("ANTHROPIC_API_KEY")
        if not self.api_key:
            raise RuntimeError("ANTHROPIC_API_KEY environment variable is required")

        self.model = os.getenv("ANTHROPIC_MODEL", "claude-sonnet-4-5-20250929")
        self.client = Anthropic(api_key=self.api_key)

        logger.info(f"Anthropic LLM initialized with model={self.model}")

    def predict(
        self, prompt: str, system_prompt: str = "You are a helpful assistant."
    ) -> str:
        """Send prompt to Anthropic and return the response text."""

        max_tokens = int(os.getenv("ANTHROPIC_MAX_TOKENS", "2048"))
        temperature = float(os.getenv("ANTHROPIC_TEMPERATURE", "0.0"))
        max_wait_seconds = int(os.getenv("ANTHROPIC_MAX_WAIT_SECONDS", "300"))

        start_time = time.time()
        backoff = 1.0

        while True:
            try:
                response = self.client.messages.create(
                    model=self.model,
                    system=system_prompt,
                    messages=[{"role": "user", "content": prompt}],
                    max_tokens=max_tokens,
                    temperature=temperature,
                )

                # CHECK FOR REFUSAL FIRST
                if response.stop_reason == "refusal":
                    logger.error(
                        f"Request refused by Claude API. Response ID: {response.id}"
                    )
                    logger.error(
                        f"This usually means the content triggered safety filters."
                    )
                    logger.error(f"Prompt length: {len(prompt)} chars")
                    # Log first 500 chars of prompt for debugging
                    logger.error(f"Prompt preview: {prompt[:500]}...")
                    raise RuntimeError(
                        f"Claude API refused the request (ID: {response.id}). "
                        "The content may have triggered safety filters. "
                        "Try rephrasing your prompt or check for sensitive patterns."
                    )

                try:
                    logger.info(f"Full response: {response}")
                    logger.info(f"Content: {response.content}")
                    logger.info(f"Text: {response.content[0].text}")
                except Exception as e:
                    logger.error(f"Error logging response details: {e}")

                # Extract text from response
                if not response.content:
                    logger.error("Empty response from Anthropic API")
                    raise RuntimeError("Received empty response from Anthropic")

                # Get the text content from the first content block
                content_block = response.content[0]
                if hasattr(content_block, "text"):
                    text = content_block.text
                elif isinstance(content_block, dict):
                    text = content_block.get("text", "")
                else:
                    logger.error(
                        f"Unexpected content block type: {type(content_block)}"
                    )
                    raise RuntimeError(
                        f"Unexpected response format: {type(content_block)}"
                    )

                if not text:
                    logger.warning("Anthropic returned empty text content")
                    return ""

                # Log usage
                if hasattr(response, "usage"):
                    logger.info(
                        f"Token usage - input: {response.usage.input_tokens}, "
                        f"output: {response.usage.output_tokens}"
                    )

                # remove markdown code fences if present
                if text.startswith("```"):
                    parts = text.split("\n", 1)
                    text = parts[1] if len(parts) > 1 else ""
                if text.endswith("```"):
                    text = "\n".join(text.split("\n")[:-1])
                return text.strip()

            except RateLimitError as e:
                elapsed = time.time() - start_time

                if elapsed >= max_wait_seconds:
                    logger.error(
                        f"Rate limit exceeded max wait time ({max_wait_seconds}s)"
                    )
                    raise RuntimeError(
                        f"Rate limit persisted for more than {max_wait_seconds}s"
                    ) from e

                # Exponential backoff with cap at 60s
                sleep_time = min(backoff, 60.0)
                logger.warning(
                    f"Rate limited. Sleeping {sleep_time:.1f}s (elapsed: {elapsed:.1f}s)"
                )

                time.sleep(sleep_time)
                backoff *= 2

            except Exception as e:
                logger.error(f"Anthropic API error: {e}")
                raise
