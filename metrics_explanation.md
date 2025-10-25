
## Benchmarks: Metric explanations

This document explains each metric that the `run_benchmark.py` script collects and writes to the benchmark JSON output. It focuses on what each metric measures and how to interpret the values generally (not the specific numbers from any run).

### Aggregation and naming conventions

- Numeric metrics are aggregated across testcases and reported with suffixes like `_mean`, `_min`, `_max`, and `_std` (standard deviation). Higher-level fields group these statistics under a base metric name in the JSON output.
- Boolean metrics (success flags) are aggregated using `_sum` (count of successful testcases) and `_ratio` (successful count divided by number of testcases).
- Times are reported in seconds.

### Output similarity metrics

- `similarity_exact` — True when the produced output exactly matches the expected output (byte/string-equality). Interpreting: a value of 1.0 (or `true` per-test) means perfect, exact match; non-exact matches can still be useful if other similarity metrics are high.

- `similarity_cosine` — Cosine similarity of token-count vectors between expected and actual outputs (range 0.0–1.0). Interpreting: values near 1.0 mean the outputs use very similar words/term frequencies; values near 0 mean very different token distributions. This metric is robust to small ordering differences but sensitive to vocabulary changes.

- `similarity_levenshtein_distance` — The raw Levenshtein (edit) distance between expected and actual outputs (non-negative integer). Interpreting: smaller is better; 0 means identical strings. Since it is an absolute count, distances scale with length and should be compared relative to input/output sizes.

- `similarity_levenshtein_similarity` — Normalized similarity derived from Levenshtein distance, computed as 1 - (distance / max_len) where max_len is the length of the longer string (range 0.0–1.0). Interpreting: values close to 1.0 mean minor edits; values close to 0 mean substantial differences.

- `similarity_jaccard` — Jaccard similarity computed on the sets of word tokens (range 0.0–1.0). Interpreting: 1.0 indicates token sets are identical; lower values indicate less overlap in unique tokens. Jaccard ignores frequency and ordering, focusing on shared vocabulary.

Notes on similarity metrics: use them together. For example, a high cosine similarity but low Jaccard may indicate similar term frequency but differing unique tokens (e.g., many repeated words). A high Levenshtein similarity plus high cosine typically indicates near-identical content.

### Static code quality / complexity metrics (from generated Java)

- `static_halstead_volume` — Halstead volume: a measure of the size of the implementation based on operator and operand counts. Interpreting: higher volume usually means more code and potentially more complexity. It is relative and should be compared across similar programs.

- `static_halstead_difficulty` — Halstead difficulty: estimates difficulty to write or understand the program. Interpreting: higher values indicate potentially harder-to-understand code and higher cognitive load.

- `static_halstead_effort` — Halstead effort: computed from volume and difficulty (an estimate of effort required). Interpreting: higher effort suggests the code is more complex to create/maintain. Units are abstract (not time) but useful for relative comparison.

- `static_cyclomatic_complexity` — Measures the number of independent paths through the program (rough estimate of branching complexity). Interpreting: higher values indicate more branches/decision points and typically harder-to-test and maintain code. Small functions should have low values (e.g., < 10); very high numbers warrant refactoring.

- `static_lines_of_code` — Count of source lines in the generated Java. Interpreting: a proxy for size. Larger values may increase complexity, but lines alone don't indicate quality.

- `static_maintainability_index` — Composite maintainability score (often 0–100). Interpreting: higher is better. Lower scores indicate code that's harder to maintain. Different tools compute this slightly differently, so use it for relative comparisons within the project.

- `static_maintainability_rating` — A human-readable label (if provided) derived from the maintainability index (e.g., “Good”, “Moderate”, “Poor”). Interpreting: quick qualitative guide; check the numeric index for details.

Notes on static metrics: these come from automated static analysis and are approximations. They are best used for comparing outputs across different converter runs or versions rather than as absolute judgments.

### Conversion and execution metrics

- `generation_success` — Per-test boolean indicating whether the converter produced non-empty Java source code. Aggregated as a count (`_sum`) and ratio (`_ratio`). Interpreting: higher ratio means the converter reliably emits code for more testcases.

- `compilation_success` — Per-test boolean indicating whether `javac` successfully compiled the generated Java source. Aggregated as a count and ratio. Interpreting: a low compilation ratio points to syntactic or API issues in generated code.

- `run_success` — Per-test boolean indicating whether running the compiled Java program produced output without runtime errors (and returned exit code 0). Aggregated as a count and ratio. Interpreting: this is the strongest functional indicator—programs that compile but crash or misbehave will have lower run success.

- `conversion_time` — Time (in seconds) taken by the converter to produce Java from COBOL for a testcase. Interpreting: lower is faster; outliers may indicate particularly large inputs or model timeouts. Reported per-test and aggregated as mean/min/max/std.

- `total_benchmark_time_seconds` — Total wall-clock time the benchmark run took. Interpreting: rough end-to-end measurement including setup, conversion, compilation, and execution overhead.

- `total_testcases` — The number of testcases processed in the benchmark run.

### Context fields

- `benchmark_run_description` — Human-readable description of how the benchmark was executed (converter details, strategy, etc.). Useful for understanding experimental setup.

- `converter_used` — Short identifier of the converter configuration used in the run. Useful when comparing multiple converters.

- `timestamp` — Run timestamp for bookkeeping.

### Practical tips for interpretation

- Use success ratios (`generation_success_ratio`, `compilation_success_ratio`, `run_success_ratio`) as primary pass/fail indicators. When `run_success_ratio` is much lower than `compilation_success_ratio`, investigate runtime differences (missing resources, incorrect I/O handling, encoding issues).
- Compare static metrics between converter versions or across test groups to spot regressions in code quality (e.g., sudden increases in cyclomatic complexity or Halstead effort).
- Combine similarity scores to get a fuller picture: Levenshtein-based metrics capture edit distance; cosine captures token-frequency similarity; Jaccard captures vocabulary overlap.
- Watch the standard deviation values (`_std`) when reviewing aggregated numeric metrics—high std means per-test behavior varies widely and you may want to inspect per-test results or outliers.