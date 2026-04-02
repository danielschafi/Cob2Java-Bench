# Cob2Java-Bench

A benchmarking framework for evaluating LLM-based COBOL-to-Java converters. It runs a converter against a dataset of 246 real-world COBOL programs, compiles and executes the generated Java, compares the output against expected results captured from the original COBOL programs, and reports detailed quality metrics.

The framework is not limited to COBOL-to-Java — any converter that implements the simple `BaseCob2JavaConverter` interface can be plugged in and evaluated.

---

## How It Works

```
COBOL source
     │
     ▼
Your Converter  ──────────────────────────────────────────────────────┐
     │                                                                  │
     │  (optional: AST-enhanced)                                        │
     │  COBOL → AST extraction → LLM with structural context           │
     │                                                                  │
     │  (base)                                                          │
     │  COBOL → LLM → Java                                             │
     │                                                                  ▼
     ▼                                                         Generated Java
javac (compile)
     │
     ▼
java  (run)
     │
     ▼
Compare stdout & output files against expected ← captured from real GnuCOBOL run
     │
     ▼
Metrics: exact match, cosine/Levenshtein/Jaccard similarity,
         Halstead complexity, cyclomatic complexity, maintainability index, ...
```

Each test case is a `test_metadata.json` file that stores the COBOL source, input data files, stdin content, and the expected stdout/output files captured by running the original program through GnuCOBOL.

---

## Results

Five converter/model combinations have been benchmarked on 244 test cases:

| Converter | Model | Generation | Compilation | Run success | Cosine sim | Exact match |
|-----------|-------|:---:|:---:|:---:|:---:|:---:|
| Base | Claude Sonnet 4.5 | 90.6% | 90.6% | **82.0%** | **0.638** | **17.3%** |
| Base | Claude Haiku 4.5 | 73.4% | 73.4% | 65.2% | 0.590 | 13.4% |
| AST  | Claude Haiku 4.5 | 76.6% | 76.6% | 66.0% | 0.545 | 11.2% |
| Base | Qwen 3 (local) | 86.5% | 86.5% | 66.4% | 0.472 | 8.2% |
| AST  | Qwen 3 (local) | — | — | — | — | — |

> **Generation = Compilation** because the benchmark counts a test as compiled only when non-empty code was produced. The AST converter adds COBOL structural context to the prompt but does not consistently outperform the base approach — larger/smarter models benefit more from the extra context.

---

## Prerequisites

- Python 3.10+
- Java JDK (for `javac` / `java`)
- GnuCOBOL (only needed to add new test cases, not to run the benchmark)
- An Anthropic API key **or** a GPU with enough VRAM for a HuggingFace model

---

## Setup

**1. Install Python dependencies**

```bash
pip install -r requirements.txt
```

**2. Configure environment variables**

Create a `.env` file in the project root:

```env
# Paths (defaults shown — override if needed)
TESTS_DIR=testcases
CONVERSIONS_DEST_DIR=conversions
BENCHMARK_RESULTS_DIR=benchmark_results
LOG_DIR=logs

# Anthropic (if using Claude)
ANTHROPIC_API_KEY=sk-ant-...
ANTHROPIC_MODEL=claude-haiku-4-5

# HuggingFace (if using a local model)
MODEL_NAME=Qwen/Qwen2.5-Coder-7B-Instruct
```

**3. (AST converter only) Set up Java libraries**

```bash
bash setup.sh
```

Then manually download the Proleap COBOL Parser JAR from [github.com/uwol/proleap-cobol-parser/releases](https://github.com/uwol/proleap-cobol-parser/releases) and save it as `lib/proleap-cobol-parser.jar`. Finally compile the AST extractor:

```bash
javac -cp "lib/*" CobolAstToJson.java
```

---

## Running the Benchmark

Open `run_benchmark.py` and edit the `main()` function — it is the single configuration point:

```python
# 1. Describe the run
run_description = "Base converter with Claude Sonnet 4.5"
converter_description = "BaseCobolConverter claude-sonnet-4-5"

# 2. Choose your LLM backend
from utils.llm_anthropic import LLM   # Claude (API)
# from utils.llm_huggingface import LLM  # local HuggingFace model

# 3. Choose your converter
llm = LLM()
converter = BaseCobolConverter(llm)   # simple one-shot conversion
# converter = ASTCobolConverter(llm)  # AST-enhanced conversion
```

Then run:

```bash
python run_benchmark.py
```

Results are written to `benchmark_results/benchmark_results_<timestamp>.json`. Intermediate per-test results are saved to `conversions/conversions_<timestamp>/` after every test so the run is resumable.

**Resuming an interrupted run**

```python
resume_previous_run = True
previous_conversions_dir = Path("conversions/conversions_20251122_140135")
```

---

## Using Your Own Converter

Any converter that inherits from `BaseCob2JavaConverter` will work:

```python
from converters.baseConverter import BaseCob2JavaConverter

class MyConverter(BaseCob2JavaConverter):
    def convert(self, cobol_code: str) -> str:
        # call your tool / API / rule-based engine here
        java_code = my_translation_engine(cobol_code)
        return java_code
```

Then pass it to the benchmark:

```python
converter = MyConverter()
benchmark = Cob2JavaBenchmark(testcases_dir, conversions_dir, converter)
results = benchmark.run()
```

The LLM classes (`utils/llm_anthropic.py`, `utils/llm_huggingface.py`) are just helpers for the built-in converters — you do not need them if you bring your own converter.

---

## Metrics

### Success flags
| Metric | Description |
|---|---|
| `generation_success` | Converter returned non-empty Java code |
| `compilation_success` | `javac` compiled the code without errors |
| `run_success` | Program ran and exited with code 0 |
| `conversion_time` | Seconds taken to convert one program |

### Output similarity (expected vs. actual stdout/files)
| Metric | Range | Interpretation |
|---|---|---|
| `similarity_exact` | 0 / 1 | Byte-exact match |
| `similarity_cosine` | 0–1 | Token-frequency similarity |
| `similarity_levenshtein_similarity` | 0–1 | Normalized edit distance (1 = identical) |
| `similarity_levenshtein_distance` | ≥ 0 | Raw edit distance (lower = better) |
| `similarity_jaccard` | 0–1 | Shared vocabulary ratio |

Use similarity metrics together — high cosine + high Levenshtein means near-identical output; high cosine + low Jaccard suggests similar frequencies but different vocabulary.

### Static code quality (generated Java)
| Metric | Description |
|---|---|
| `static_halstead_volume` | Implementation size (operator/operand counts) |
| `static_halstead_difficulty` | Estimated cognitive difficulty |
| `static_halstead_effort` | Estimated implementation effort |
| `static_cyclomatic_complexity` | Number of independent paths (lower = simpler) |
| `static_lines_of_code` | Lines in the generated Java file |
| `static_maintainability_index` | Composite score 0–100 (higher = more maintainable) |
| `static_maintainability_rating` | Human-readable label (Good / Moderate / Poor) |

All numeric metrics are reported as `mean`, `min`, `max`, and `std` across all test cases. Success flags are reported as `sum` (count) and `ratio`.

See [`metrics_explanation.md`](metrics_explanation.md) for detailed interpretation guidance.

---

## Adding Test Cases

1. Place compilable `.cbl` files in `src_data/` (verify they compile with GnuCOBOL first).
2. Filter out very short files:
   ```bash
   python utils/filter_short_files.py src_data short_files 60
   ```
3. Rewrite any absolute file paths in the COBOL source to be relative (e.g. `data/experiment2/data.dat` → `data.dat`).
4. Generate sample I/O metadata:
   ```bash
   python generate_sample_io_files.py
   ```

---

## Project Structure

```
converters/
  baseConverter.py                  ← abstract interface for all converters
  cobolToJavaTranslator/
    baseCobolConverter.py           ← simple LLM converter
    astCobolConverter.py            ← AST-enhanced LLM converter
    prompts.py                      ← conversion prompts
    cobolAstParser.py               ← COBOL → AST (via Java/ANTLR)
utils/
  llm_anthropic.py                  ← Anthropic Claude wrapper
  llm_huggingface.py                ← HuggingFace Transformers wrapper
run_benchmark.py                    ← main entry point
testcases/success/                  ← 246 test cases (test_metadata.json each)
benchmark_results/                  ← final JSON reports per run
conversions/                        ← per-test intermediate outputs
```
