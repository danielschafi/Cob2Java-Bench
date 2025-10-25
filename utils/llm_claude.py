"""Small wrapper that exposes an LLM-like interface but uses Anthropic Claude.

This preserves the original `LLM` class name and `predict(prompt, system_prompt=...)`
signature so callers in the codebase can stay the same.

Notes / assumptions:
- Uses the official Anthropic Python SDK (package name `anthropic`).
- Requires an environment variable `ANTHROPIC_API_KEY` to be set.
- Default model is `claude-4-5` (if your Anthropic account uses a different
    model name, set the `ANTHROPIC_MODEL` env var).
"""

import os
from dotenv import load_dotenv
import logging
import time


from anthropic import Anthropic


load_dotenv()


logger = logging.getLogger(__name__)


class LLM:
    """Wrapper to call Anthropic Claude via the official Python SDK and return a text response.

    Public API matches the previous class: __init__() and predict(prompt, system_prompt).
    """

    def __init__(self):
        self.api_key = os.getenv("ANTHROPIC_API_KEY")
        if not self.api_key:
            raise RuntimeError(
                "ANTHROPIC_API_KEY environment variable is required to use Claude."
            )

        # Default model name; override with ANTHROPIC_MODEL env var if needed.
        self.model = os.getenv("ANTHROPIC_MODEL", "claude-4-5")

        if Anthropic is None:
            raise RuntimeError(
                "The 'anthropic' Python package is required. Install with `pip install anthropic`."
            )

        # create the SDK client
        # The SDK client expects the API key either via env var or passed directly.
        # We pass it explicitly to be explicit about credentials.
        try:
            self.client = Anthropic(api_key=self.api_key)
        except TypeError:
            # older versions of the SDK used Client instead of Anthropic class
            try:
                from anthropic import Client as AnthropicClient

                self.client = AnthropicClient(api_key=self.api_key)
            except Exception:
                raise

        logger.info(f"Anthropic LLM configured for model={self.model}")

    def _build_prompt(self, user_prompt: str, system_prompt: str) -> str:
        # Anthropic convention: use explicit Human / Assistant markers.
        # Put system instructions first as an initial assistant instruction.
        system_block = (
            f"Assistant is a helpful and precise assistant. {system_prompt}"
            if system_prompt
            else ""
        )
        # Human and Assistant markers separated by double-newline as recommended.
        prompt = f"{system_block}\n\nHuman: {user_prompt}\n\nAssistant:"
        return prompt

    def predict(
        self, prompt: str, system_prompt: str = "You are a helpful assistant."
    ) -> str:
        """Send the prompt to Anthropic and return the assistant text.

        This is synchronous and will retry on transient HTTP errors.
        """
        body_prompt = self._build_prompt(prompt, system_prompt)

        max_tokens = int(os.getenv("ANTHROPIC_MAX_TOKENS", "2048"))
        temperature = float(os.getenv("ANTHROPIC_TEMPERATURE", "0.0"))

        # total time we'll wait if rate-limited before giving up (seconds)
        max_wait_seconds = int(os.getenv("ANTHROPIC_MAX_WAIT_SECONDS", "300"))

        start_time = time.time()
        attempts = 0
        max_attempts = 3
        backoff = float(os.getenv("ANTHROPIC_BACKOFF_INITIAL", "1.0"))
        backoff_cap = float(os.getenv("ANTHROPIC_BACKOFF_MAX", "60.0"))

        def _is_rate_limit_error(exc: Exception) -> bool:
            # Detect rate limit by inspecting exception attributes and message
            msg = str(exc).lower()
            if "429" in msg or "rate limit" in msg or "too many requests" in msg:
                return True
            # Some SDKs expose status or status_code
            status = getattr(exc, "status", None) or getattr(exc, "status_code", None)
            try:
                if status is not None and int(status) == 429:
                    return True
            except Exception:
                pass
            return False

        def _extract_request_id(obj) -> str | None:
            # Many SDK responses/exceptions expose a _request_id property or include it in json
            try:
                if obj is None:
                    return None
                # object attribute
                rid = getattr(obj, "_request_id", None)
                if rid:
                    return rid
                # dict-like
                if isinstance(obj, dict):
                    return obj.get("_request_id") or obj.get("request_id")
                # exceptions may have a response attribute with headers or json
                resp = getattr(obj, "response", None)
                if resp is not None:
                    # try headers
                    try:
                        h = getattr(resp, "headers", {})
                        if h:
                            return h.get("request-id") or h.get("request_id")
                    except Exception:
                        pass
                    # try json body
                    try:
                        j = resp.json()
                        if isinstance(j, dict):
                            err = j.get("error") or {}
                            if isinstance(err, dict):
                                return err.get("request_id") or j.get("request_id")
                    except Exception:
                        pass
            except Exception:
                return None
            return None

        while True:
            attempts += 1
            try:
                resp = self.client.completions.create(
                    model=self.model,
                    prompt=body_prompt,
                    max_tokens_to_sample=max_tokens,
                    temperature=temperature,
                    stop_sequences=["\n\nHuman:"],
                )

                # Log request id when available for observability
                req_id = _extract_request_id(resp)
                if req_id:
                    logger.debug("Anthropic request id: %s", req_id)

                # Extract text from response (support object or dict-like)
                text = None
                if resp is None:
                    text = ""
                else:
                    text = getattr(resp, "completion", None)
                    if text is None and isinstance(resp, dict):
                        text = resp.get("completion")

                if text is None:
                    text = str(resp)

                response = text.strip()

                if not response:
                    logger.warning("response from Anthropic is empty!")

                # strip enclosing markdown fences if present
                if response.startswith("```"):
                    parts = response.split("\n", 1)
                    response = parts[1] if len(parts) > 1 else ""
                if response.endswith("```"):
                    response = "\n".join(response.split("\n")[:-1])

                return response.strip()

            except Exception as exc:
                # If it's a rate-limit, wait until allowed again (up to max_wait_seconds)
                if _is_rate_limit_error(exc):
                    elapsed = time.time() - start_time
                    rid = _extract_request_id(exc)
                    if rid:
                        logger.warning("Rate limit error request id: %s", rid)
                    if elapsed >= max_wait_seconds:
                        logger.error(
                            "Rate limit persisted beyond max wait (%ss); aborting. request_id=%s",
                            max_wait_seconds,
                            rid,
                        )
                        raise RuntimeError(
                            f"Rate limit persisted for more than {max_wait_seconds} seconds"
                        ) from exc

                    # log and sleep a backoff period (with small jitter)
                    sleep_for = min(backoff, backoff_cap)
                    jitter = sleep_for * 0.1 * (0.5 - (time.time() % 1))
                    sleep_for = max(0.1, sleep_for + jitter)
                    logger.warning(
                        "Rate limited by Anthropic (attempt=%d, elapsed=%.1fs). Sleeping %.1fs before retrying...",
                        attempts,
                        elapsed,
                        sleep_for,
                    )
                    time.sleep(sleep_for)
                    # exponential backoff
                    backoff = min(backoff * 2.0, backoff_cap)
                    # continue trying until max_wait_seconds is exceeded
                    continue

                # non-rate-limit errors: retry a few times then abort
                logger.warning("Anthropic SDK error (attempt=%d): %s", attempts, exc)
                if attempts >= max_attempts:
                    logger.error(
                        "Exceeded max attempts (%d) for non-rate-limit errors.",
                        max_attempts,
                    )
                    raise

                # sleep and retry
                sleep_for = min(backoff, backoff_cap)
                time.sleep(sleep_for)
                backoff = min(backoff * 2.0, backoff_cap)
