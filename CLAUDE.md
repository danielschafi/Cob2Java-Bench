# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Purpose

Benchmarking framework that evaluates LLM-based code converters (primarily COBOL-to-Java). It runs a converter against 246 test cases, compiles and executes the generated Java, compares output against expected results, and aggregates quality metrics.

## Setup

```bash
pip install -r requirements.txt
bash setup.sh  # downloads ANTLR/Gson JARs for AST parsing
# Manually download Proleap COBOL Parser JAR if using AST converter
```

Configure `.env` with: `ANTHROPIC_API_KEY`, `ANTHROPIC_MODEL`, `MODEL_NAME` (HuggingFace), and directory paths (`LOG_DIR`, `DATA_DIR`, `TESTS_DIR`, `CONVERSIONS_DEST_DIR`, `BENCHMARK_RESULTS_DIR`).

## Running the Benchmark

```bash
python run_benchmark.py
```

To resume an interrupted run, point the benchmark to an existing `conversions/conversions_<timestamp>/` directory. Results are saved to `benchmark_results/`.

## Architecture

### Converter Interface
All converters extend `converters/baseConverter.py:BaseCob2JavaConverter` and implement a single `convert(cobol_code: str) -> str` method. Two implementations exist in `converters/cobolToJavaTranslator/`:
- **BaseCobolConverter** — single LLM call with a direct COBOL→Java prompt
- **ASTCobolConverter** — extracts a COBOL AST first (via `cobolAstParser.py` using Java/ANTLR), then passes AST context alongside the source to the LLM

The `converter.py` exposes a `ConversionType` enum to select between them.

### LLM Backends
- `utils/llm_anthropic.py` — Anthropic Claude API (retries, rate limit handling)
- `utils/llm_huggingface.py` — local HuggingFace Transformers inference (CUDA)

Swap backends by changing the import in your converter.

### Benchmark Engine (`run_benchmark.py`)
Two classes:
- **`Testcase`**: setup → convert → `javac` compile → `java` run → collect metrics → teardown for a single test
- **`Cob2JavaBenchmark`**: loads all tests from `testcases/success/`, orchestrates execution, aggregates metrics, writes final JSON to `benchmark_results/`

### Test Cases
Each test lives in `testcases/success/test_<id>/test_metadata.json` and contains the COBOL source, input/output file definitions, sysin data, and expected stdout/file outputs. No pre-compiled binaries—everything is converted and compiled at benchmark time.

### Metrics
**Success**: `generation_success`, `compilation_success`, `run_success`, `conversion_time`

**Output similarity**: exact match, cosine, Levenshtein distance/similarity, Jaccard

**Static analysis** (via `java_halstead_metrics_calculator.py`): Halstead volume/difficulty/effort, cyclomatic complexity, LOC, maintainability index/rating

See `metrics_explanation.md` for definitions.

## Adding a New Converter

1. Create a class inheriting from `BaseCob2JavaConverter` in `converters/`
2. Implement `convert(cobol_code: str) -> str`
3. Instantiate it in `run_benchmark.py` and pass it to `Cob2JavaBenchmark`

See `converters/anotherExampleConverter/` for a minimal template.
