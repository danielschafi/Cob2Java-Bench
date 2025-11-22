import os
import re
from dotenv import load_dotenv
from pathlib import Path
import logging
import json
import subprocess
import time
import numpy as np
from converters.baseConverter import BaseCob2JavaConverter
from java_halstead_metrics_calculator import JavaHalsteadCalculator
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import Levenshtein
from tqdm import tqdm
import pandas as pd

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


# model_name = "Salesforce/codegen-350M-mono"  # or "codellama/CodeLlama-7b-Python-hf"
# tokenizer = AutoTokenizer.from_pretrained(model_name)
# model = AutoModelForCausalLM.from_pretrained(model_name).eval()
# device = "cuda" if torch.cuda.is_available() else "cpu"
# model.to(device)


class Testcase:
    """Runs a single testcase and gathers the results."""

    def __init__(
        self,
        testcase_dir: Path,
        conversion_dest_dir: Path,
        converter: BaseCob2JavaConverter,
        cleanup_java: bool = True,
    ) -> None:
        self.testcase_dir = Path(testcase_dir)
        self.conversion_dest_dir = Path(conversion_dest_dir)  # main conversions dir
        self.test_case_conversion_dir = (
            self.conversion_dest_dir / testcase_dir.stem
        )  # dir for this testcase we need it in a dir to setup the environment with files etc
        self.test_case_conversion_dir.mkdir(parents=True, exist_ok=True)
        # self.java_output_path = self.test_case_conversion_dir / (
        #     testcase_dir.stem + ".java"
        # )
        self.java_output_path = None  # will be set after conversion
        self.converter = converter
        self.metadata = {}
        self.cleanup_java = cleanup_java

    def get_metadata(self):
        metadata_file = self.testcase_dir / "test_metadata.json"
        if not (metadata_file).exists():
            raise FileNotFoundError(
                f"Metadata file 'test_metadata.json' not found in '{self.testcase_dir}'."
            )

        with open(metadata_file, "r") as f:
            return json.load(f)

    def setup(self):
        """
        Reads the metadata.json file and creates necessary input files.

        Returns a list of created files. These should be deleted again after the test run.
        """
        logger.info(f"Setting up test environment in '{self.testcase_dir}'")
        self.metadata = self.get_metadata()

        # env
        self.input_files = self.metadata.get("input_files", [])
        self.output_files = self.metadata.get("output_files", [])
        self.input_output_files = self.metadata.get("input_output_files", [])
        self.sysin_file = self.metadata.get("sysin_file", {})

        # cobol code
        self.cob_file = self.metadata.get("cobol_file", {})

        # outputs
        self.stdout_expected_output = self.metadata.get("expected_output", "")
        self.expected_output_files = self.metadata.get("expected_output_files", {})
        self.expected_input_output_files = self.metadata.get(
            "expected_input_output_files", {}
        )

        # create env files
        file_list = self.input_files + self.input_output_files + [self.cob_file]
        if self.sysin_file:
            file_list += [self.sysin_file] if self.sysin_file.get("content") else []
        for file in file_list:
            filename = file.get("file_name")
            content = file.get("content", "")
            if filename:
                file_path = self.test_case_conversion_dir / filename
                with open(file_path, "w") as f:
                    f.write(content)
                logger.info(f"\tCreated file '{file_path}' with content.")

        return file_list

    def convert_cobol_to_java(self):
        logger.info(f"Converting COBOL to Java in {self.testcase_dir}")

        cobol_code = self.metadata["cobol_file"]["content"]
        cobol_filename = self.metadata["cobol_file"]["file_name"]

        java_code = self.converter.convert(cobol_code)
        self.java_filename = self._get_java_class_name(java_code)
        self.java_output_path = self.test_case_conversion_dir / (self.java_filename)

        with open(self.java_output_path, "w") as f:
            f.write(java_code)

        logger.info(
            f"Converted COBOL file '{cobol_filename}' to Java file '{self.java_filename}'"
        )

        return java_code

    def compile_java(self):
        logger.info(f"Compiling Java code in {self.test_case_conversion_dir}")

        result = subprocess.run(
            ["javac", str(self.java_output_path)],
            check=True,
            cwd=self.test_case_conversion_dir,
        )
        if result.returncode != 0:
            logger.error(f"Java compilation failed with code {result.returncode}")
            return False

        return True

    def run_java(self):
        """Run the compiled Java class similarly to how COBOL executables are run.

        Behavior:
        - run from `self.test_case_conversion_dir`
        - use stdin from sysin_file if provided in metadata
        - attempt text mode with UTF-8, fall back to binary with replacement on decode errors
        - timeout after 5 seconds (same as COBOL runner)
        - return stdout string on success, None on failure
        """
        logger.info(f"Running Java code in {self.test_case_conversion_dir}")

        class_name = Path(self.java_filename).stem

        cwd = os.getcwd()
        os.chdir(self.test_case_conversion_dir)

        run_cmd = ["java", "-cp", ".", class_name]

        # Prepare stdin if sysin_file specified
        stdin_input = None
        if self.sysin_file:
            sysin_name = (
                self.sysin_file.get("file_name")
                if isinstance(self.sysin_file, dict)
                else None
            )
            if sysin_name:
                sysin_path = self.test_case_conversion_dir / sysin_name
                if sysin_path.exists():
                    with open(sysin_path, "r") as f:
                        stdin_input = f.read()
                    logger.info(f"Using SYSIN from: {sysin_path}")

        # Try text mode first
        try:
            try:
                result = subprocess.run(
                    run_cmd,
                    capture_output=True,
                    text=True,
                    input=stdin_input,
                    encoding="utf-8",
                    timeout=5,
                )
            except subprocess.TimeoutExpired:
                logger.error(f"\t\tExecution timed out for Java class '{class_name}'.")
                os.chdir(cwd)
                return None

            output = result.stdout
        except UnicodeDecodeError:
            # If UTF-8 fails, run again in binary mode
            logger.warning("\tUTF-8 decode failed for Java output, trying binary mode")
            stdin_bytes = stdin_input.encode("utf-8") if stdin_input else None
            try:
                result = subprocess.run(
                    run_cmd,
                    capture_output=True,
                    text=False,
                    input=stdin_bytes,
                    timeout=5,
                )
            except subprocess.TimeoutExpired:
                logger.error(f"\t\tExecution timed out for Java class '{class_name}'.")
                os.chdir(cwd)
                return None
            output = result.stdout.decode("utf-8", errors="replace")
            logger.info("\tUsed binary mode with error replacement")

        os.chdir(cwd)

        if result.returncode != 0:
            stderr_val = result.stderr
            if isinstance(stderr_val, bytes):
                stderr = stderr_val.decode("utf-8", errors="replace")
            else:
                try:
                    stderr = stderr_val.decode("utf-8", errors="replace")
                except Exception:
                    stderr = str(stderr_val)
            logger.error(
                f"Java execution failed with return code {result.returncode}:\n{stderr}"
            )
            return None

        logger.info(f"Java program output: {output}")
        return output if output else ""

    def _get_java_class_name(self, java_code: str) -> str:
        """
        Extracts the public class name from Java Code snippet.

        Args:
            java_file (str): the java source code

        Returns:
            str: Public class name
        """

        if len(java_code) == 0:
            raise ValueError(
                "No Java Code was generated, could not extract class name."
            )

        # Regex to match 'public class ClassName'
        match = re.search(r"public\s+class\s+([A-Za-z_][A-Za-z0-9_]*)", java_code)

        if match:
            return match.group(1) + ".java"
        else:
            raise ValueError(
                f"No public class found in {java_code[: min(200, len(java_code))]}"
            )

    def collect_metrics(self):
        # compare outputs with expected outputs from metadata
        to_compare = self.collect_outputs()
        sims = {
            "similarity_exact": [],
            "similarity_cosine": [],
            "similarity_levenshtein_distance": [],
            "similarity_levenshtein_similarity": [],
            "similarity_jaccard": [],
        }
        weights = []
        for item in to_compare:
            expected = item["expected"]
            actual = item["actual"]

            weights.append(len(expected) if expected else 1)
            # Similarity of outputs
            sim_scores = self.similarity_scores(expected, actual)
            for k in sims.keys():
                sims[k].append(sim_scores[k])
            logger.info(f"Similarity scores: {sim_scores}")

        # Aggregate similarity scores by weighted average by length of expected output
        aggregated_sims = {}
        total_weight = sum(weights) if sum(weights) > 0 else 1
        for k, v in sims.items():
            weighted_sum = sum(
                score * weight for score, weight in zip(v, weights) if score is not None
            )
            aggregated_sims[k] = (
                weighted_sum / total_weight if total_weight > 0 else None
            )
        logger.info(f"Aggregated similarity scores: {aggregated_sims}")

        # static code analysis of generated java code
        static_scores = self.static_analysis(self.generated_java_code)
        logger.info(f"Static analysis scores: {static_scores}")

        return {
            **aggregated_sims,
            **static_scores,
            "generation_success": self.generation_success,
            "compilation_success": self.compilation_success,
            "run_success": self.stdout_java_output is not None,
            "conversion_time": self.conversion_time,
        }

    def static_analysis(self, java_code: str) -> dict:
        results = {}

        static_java_analyzer = JavaHalsteadCalculator()
        metrics = static_java_analyzer.calculate_metrics(java_code)
        logger.info(f"Static analysis result: {metrics}")
        if not metrics:
            return results

        results["static_halstead_volume"] = metrics.get("volume", 0)
        results["static_halstead_difficulty"] = metrics.get("difficulty", 0)
        results["static_halstead_effort"] = metrics.get("effort", 0)
        results["static_cyclomatic_complexity"] = metrics.get(
            "cyclomatic_complexity", 0
        )
        results["static_lines_of_code"] = metrics.get("lines_of_code", 0)
        results["static_maintainability_index"] = metrics.get(
            "maintainability_index", 0
        )
        results["static_maintainability_rating"] = metrics.get(
            "maintainability_rating", "Unknown"
        )

        return results

    def similarity_scores(self, expected: str, actual: str) -> dict:
        """https://spotintelligence.com/2022/12/19/text-similarity-python/"""
        sims = {}
        # exact match
        sims["similarity_exact"] = bool(expected == actual)

        # normalize inputs
        expected = expected or ""
        actual = actual or ""
        exp = expected.lower()
        act = actual.lower()

        try:
            vect = CountVectorizer(token_pattern=r"\w+").fit([exp, act])
            # will fail if both documents contain only stop-words or no tokens
            X = vect.transform([exp, act]).toarray()
            if X.sum() == 0:
                sims["similarity_cosine"] = 1.0 if exp == act else 0.0
            else:
                sims["similarity_cosine"] = float(
                    cosine_similarity([X[0]], [X[1]])[0, 0]
                )
        except ValueError as e:
            msg = str(e).lower()
            if "empty vocabulary" in msg:
                logger.warning(
                    "CountVectorizer empty vocabulary for inputs; Check if both empty/whitespace..."
                )

                # fallback strategies:
                # - if both strings empty/whitespace -> identical
                # - if no word-tokens, fall back to exact equality
                tokens_exp = re.findall(r"\w+", exp)
                tokens_act = re.findall(r"\w+", act)

                if len(tokens_exp) == 0 and len(tokens_act) == 0:
                    sims["similarity_cosine"] = 1.0 if exp == act else 0.0
                    logger.info(
                        "Both inputs have no word tokens; setting cosine similarity to 1.0 if identical, else 0.0"
                    )
                else:
                    sims["similarity_cosine"] = 0.0
            else:
                raise e

        lev_dist = Levenshtein.distance(exp, act)

        max_len = max(len(exp), len(act))
        sims["similarity_levenshtein_distance"] = int(lev_dist)
        sims["similarity_levenshtein_similarity"] = float(
            1.0 - (lev_dist / max_len) if max_len > 0 else 1.0
        )

        # Jaccard similarity on token sets (use tokenization consistent with above)
        tokens_exp = re.findall(r"\w+", exp)
        tokens_act = re.findall(r"\w+", act)
        set_exp = set(tokens_exp)
        set_act = set(tokens_act)
        union = set_exp | set_act
        if len(union) == 0:
            sims["similarity_jaccard"] = 1.0 if exp == act else 0.0
        else:
            sims["similarity_jaccard"] = float(len(set_exp & set_act) / len(union))

        return sims

    def collect_outputs(self):
        to_compare = []

        # stdout
        to_compare.append(
            {"expected": self.stdout_expected_output, "actual": self.stdout_java_output}
        )

        # output files
        for file in self.expected_output_files:
            expected_content = file.get("content", "")
            filename = file.get("file_name", "")

            prod_content_filepath = Path(self.test_case_conversion_dir / filename)
            if prod_content_filepath.exists():
                prod_content = prod_content_filepath.read_text()
            else:
                prod_content = ""

            to_compare.append({"expected": expected_content, "actual": prod_content})

        # input-output files
        for file in self.expected_input_output_files:
            expected_content = file.get("content", "")
            filename = file.get("file_name", "")

            prod_content_filepath = Path(self.test_case_conversion_dir / filename)
            if prod_content_filepath.exists():
                prod_content = prod_content_filepath.read_text()
            else:
                prod_content = ""

            to_compare.append({"expected": expected_content, "actual": prod_content})

        return to_compare

    def teardown(self):
        """
        Deletes all files besides test_metadata.json in the folder
        """
        logger.info(f"Tearing down testcase in {self.testcase_dir}")
        logger.info(f"Cleaning up test environment in '{self.testcase_dir}'")
        for file in self.testcase_dir.iterdir():
            if file.name != "test_metadata.json":
                file.unlink()
                logger.info(f"\tDeleted file '{file}'.")

    def run(self):
        logger.info("=" * 120)
        logger.info(f"Running testcase in {self.testcase_dir}")
        logger.info("=" * 120)

        self.setup()

        start_time = time.time()
        self.generated_java_code = self.convert_cobol_to_java()
        end_time = time.time()
        self.conversion_time = end_time - start_time
        if self.generated_java_code and len(self.generated_java_code) > 0:
            self.generation_success = True
        else:
            self.generation_success = False

        self.compilation_success = self.compile_java()
        self.stdout_java_output = self.run_java()

        metrics = self.collect_metrics()
        self.teardown()

        return metrics


class Cob2JavaBenchmark:
    def __init__(
        self,
        benchmark_dir: Path,
        conversions_dest_dir: Path,
        converter: BaseCob2JavaConverter,
        resume_previous_run: bool = False,
    ) -> None:
        self.benchmark_dir = benchmark_dir
        self.conversions_dest_dir = conversions_dest_dir
        self.converter = converter
        self.resume_previous_run = resume_previous_run
        self.testcases = [
            Testcase(
                testcase_dir=testcase_dir,
                conversion_dest_dir=conversions_dest_dir,
                converter=converter,
            )
            for testcase_dir in benchmark_dir.iterdir()
            if testcase_dir.is_dir()
        ]

        self.metrics = {}
        self.curr_testcase_index = 0

    def aggregate_metrics(self):
        aggregated = {}
        for k, v in self.metrics.items():
            if isinstance(v, list) and len(v) > 0:
                if not isinstance(v[0], (int, float, bool)):
                    continue  # skip non-numeric lists

                if isinstance(v[0], bool):
                    aggregated[k + "_sum"] = int(sum(v))
                    aggregated[k + "_ratio"] = round(
                        aggregated[k + "_sum"] / self.num_testcases, 4
                    )
                else:
                    aggregated[k + "_mean"] = round(float(np.mean(v)), 4)
                    if isinstance(v[0], int):
                        aggregated[k + "_min"] = min(v)
                        aggregated[k + "_max"] = max(v)
                    else:
                        aggregated[k + "_min"] = round(float(min(v)), 4)
                        aggregated[k + "_max"] = round(float(max(v)), 4)
                    aggregated[k + "_std"] = round(float(np.std(v)), 4)
            else:
                aggregated[k] = v

        return aggregated

    def format_metrics_dict(self, aggregated: dict) -> dict:
        # split at the last underscore to get the base metric name
        # for better readable json output create a nested dict where every base metric name is a key

        logger.info(
            f"Aggregated metrics before nesting: {json.dumps(aggregated, indent=2)}"
        )
        nested_aggregated = {}
        for k, v in aggregated.items():
            if "_" in k:
                base_metric = "_".join(k.split("_")[:-1])
                metric_stat = k.split("_")[-1]
                if base_metric not in nested_aggregated:
                    nested_aggregated[base_metric] = {}
                nested_aggregated[base_metric][metric_stat] = v
            else:
                nested_aggregated[k] = v
        return nested_aggregated

    def run(self):
        timer_start = time.time()

        if self.resume_previous_run:
            # load metrics from previous run if exists
            intermediate_results_path = (
                self.conversions_dest_dir / "intermediate_per_testcase_results.json"
            )
            if intermediate_results_path.exists():
                with open(intermediate_results_path, "r") as f:
                    self.metrics = json.load(f)
                logger.info(
                    f"Resumed previous run, loaded intermediate results from {intermediate_results_path}"
                )
            else:
                logger.info(
                    f"No intermediate results found at {intermediate_results_path}, starting fresh."
                )
                self.curr_testcase_index = 0

            # add the time of already run testcases, this is probably not 100% accurate as it does not include compile+run time, but it should be very close
            timer_start -= sum(self.metrics.get("conversion_time", []))

            # determine how many testcases have already been run
            self.curr_testcase_index = len(
                [
                    dir
                    for dir in self.conversions_dest_dir.iterdir()
                    if dir.is_dir() and len(list(dir.iterdir())) > 0
                ]
            )

        self.num_testcases = len(self.testcases)

        if self.resume_previous_run:
            logger.info(
                f"Resuming benchmark run from testcase index {self.curr_testcase_index}/{self.num_testcases}"
            )
        else:
            logger.info(f"Running benchmark on {self.num_testcases} testcases.")

        for testcase in tqdm(self.testcases):
            if self.resume_previous_run:
                if len(list(testcase.test_case_conversion_dir.iterdir())) > 0:
                    logger.info(
                        f"Skipping already run testcase in {testcase.test_case_conversion_dir}"
                    )
                    continue
            try:
                self.curr_testcase_index += 1
                logger.info(
                    f"Starting testcase {self.curr_testcase_index}/{self.num_testcases}"
                )

                results = testcase.run()

                # just append all results to the metrics dict, we aggregate them later
                for k, v in results.items():
                    if k not in self.metrics:
                        # if instance is bool then += instead of append
                        self.metrics[k] = []

                    self.metrics[k].append(v)

                with open(
                    self.conversions_dest_dir
                    / "intermediate_per_testcase_results.json",
                    "w",
                ) as f:
                    json.dump(self.metrics, f, indent=2)

                intermediate_agg = self.aggregate_metrics()
                with open(
                    self.conversions_dest_dir / "intermediate_aggregated_results.json",
                    "w",
                ) as f:
                    json.dump(intermediate_agg, f, indent=2)

            except Exception as e:
                logger.error(f"Error running testcase in {testcase.testcase_dir}: {e}")
            finally:
                testcase.teardown()

        timer_end = time.time()
        aggregated_metrics = self.aggregate_metrics()
        formatted_metrics = self.format_metrics_dict(aggregated_metrics)
        formatted_metrics["total_benchmark_time_seconds"] = timer_end - timer_start
        formatted_metrics["total_testcases"] = len(self.testcases)
        return formatted_metrics


class DummyConvert(BaseCob2JavaConverter):
    def convert(self, cobol_code: str) -> str:
        # Dummy conversion logic for demonstration
        java_code = """public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}
"""
        return java_code


from converters.cobolToJavaTranslator.baseCobolConverter import BaseCobolConverter
from converters.cobolToJavaTranslator.astCobolConverter import ASTCobolConverter

# from converters.cobolToJavaTranslator.llm import LLM
# from utils.llm_anthropic import LLM
from utils.llm_huggingface import LLM


def main():
    # If you want to continnue a previous benchmark run (because it was interrupted),
    # set the following variable to the path of the previous conversions dir
    resume_previous_run = True
    previous_conversions_dir = None
    if resume_previous_run:
        previous_conversions_dir = Path(
            "/home/schafhdaniel@edu.local/Cob2Java-Bench/conversions/conversions_20251121_173023"
        )

    # ====================================================
    # ========= 1. Describe the Benchmark Run here =======
    # ====================================================

    run_description = "Running COBOL to Java conversion benchmark using Base Cobol Converter with LLM huggingface Qwen3."
    converter_description = "BaseCobolConverter huggingface Qwen3"

    # ====================================================
    # ========= 2. Set Up Your Converter here ============
    # ====================================================

    llm = LLM()
    converter = BaseCobolConverter(llm)
    # converter = ASTCobolConverter(llm)

    # ====================================================
    # ========= 3. Set up dirs according to .env =========
    # ====================================================

    timestamp = time.strftime("%Y%m%d_%H%M%S")
    testcases_dir = Path(os.environ.get("TESTS_DIR", "testcases"))
    conversions_main_dest_dir = Path(
        os.environ.get("CONVERSIONS_DEST_DIR", "conversions")
    )

    if resume_previous_run and previous_conversions_dir is not None:
        conversions_sub_dest_dir = previous_conversions_dir
        logger.info(f"Resuming previous benchmark run in {conversions_sub_dest_dir}")
    else:
        conversions_sub_dest_dir = (
            conversions_main_dest_dir / f"conversions_{timestamp}"
        )
        conversions_sub_dest_dir.mkdir(parents=True, exist_ok=True)

    if (testcases_dir / "success").exists():
        testcases_dir = testcases_dir / "success"
    results_output_path = Path(
        os.environ.get("BENCHMARK_RESULTS_DIR", "benchmark_results")
    )
    results_output_path.mkdir(parents=True, exist_ok=True)
    results_output_file = results_output_path / f"benchmark_results_{timestamp}.json"

    # ====================================================
    # ========= 4. Run the Benchmark =====================
    # ====================================================

    benchmark = Cob2JavaBenchmark(
        testcases_dir, conversions_sub_dest_dir, converter, resume_previous_run
    )
    results = benchmark.run()

    # ====================================================
    # ========= 5. Save the Results ======================
    # ====================================================

    results["benchmark_run_description"] = run_description.strip()
    results["converter_used"] = converter_description
    results["timestamp"] = timestamp

    output = json.dumps(results, indent=2)

    with open(results_output_file, "w") as f:
        f.write(output)

    print("Benchmark Results:\n", output)
    logger.info("Benchmark Results:\n" + output)
    logger.info("Benchmarking complete.")


if __name__ == "__main__":
    main()
