import os
import json
import pandas as pd
from pathlib import Path

# Path to benchmark results folder
RESULTS_DIR = Path(__file__).parent.parent / "benchmark_results"

# Collect all JSON files in the results directory
json_files = [f for f in RESULTS_DIR.iterdir() if f.suffix == ".json"]

records = []
for fname in json_files:
    fpath = RESULTS_DIR / fname
    with open(fpath, "r") as f:
        data = json.load(f)
        # Extract key metrics
        record = {
            "file": fname.stem,
            "converter": data.get("converter_used", ""),
            "gen_success": data.get("generation_success", {}).get("ratio", None),
            "comp_success": data.get("compilation_success", {}).get("ratio", None),
            "run_success": data.get("run_success", {}).get("ratio", None),
            "similarity_cosine": data.get("similarity_cosine", {}).get("mean", None),
            "similarity_levenshtein": data.get(
                "similarity_levenshtein_similarity", {}
            ).get("mean", None),
            "similarity_jaccard": data.get("similarity_jaccard", {}).get("mean", None),
            "halstead_volume": data.get("static_halstead_volume", {}).get("mean", None),
            "cyclomatic_complexity": data.get("static_cyclomatic_complexity", {}).get(
                "mean", None
            ),
            "lines_of_code": data.get("static_lines_of_code", {}).get("mean", None),
            "maintainability_index": data.get("static_maintainability_index", {}).get(
                "mean", None
            ),
            "conversion_time": data.get("conversion_time", {}).get("mean", None),
            "total_testcases": data.get("total_testcases", None),
            "timestamp": data.get("timestamp", None),
        }
        records.append(record)

# Create DataFrame
results_df = pd.DataFrame(records)

# For demonstration: print the DataFrame
print(results_df)


import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from matplotlib.patches import Rectangle

# Assuming you have your results_df from the data loading script
# results_df should have columns: converter, gen_success, comp_success, run_success,
# similarity_cosine, similarity_levenshtein, maintainability_index, cyclomatic_complexity, conversion_time

# Set style for publication-quality plots
plt.rcParams["font.size"] = 9
plt.rcParams["font.family"] = "sans-serif"
plt.rcParams["axes.labelsize"] = 9
plt.rcParams["axes.titlesize"] = 10
plt.rcParams["xtick.labelsize"] = 8
plt.rcParams["ytick.labelsize"] = 8
plt.rcParams["legend.fontsize"] = 8

# Create a compact 2x2 subplot figure
fig = plt.figure(figsize=(7, 6))
gs = fig.add_gridspec(
    2, 2, hspace=0.35, wspace=0.35, left=0.1, right=0.95, top=0.95, bottom=0.08
)

# Extract converter names (shorten if needed)
converters = results_df["file"].values
n_converters = len(converters)
x_pos = np.arange(n_converters)

# Color scheme
colors = ["#3b82f6", "#10b981", "#f59e0b", "#808080"]

# ============= Plot 1: Success Rates =============
ax1 = fig.add_subplot(gs[0, 0])
width = 0.25

gen_rates = results_df["gen_success"].values * 100
comp_rates = results_df["comp_success"].values * 100
run_rates = results_df["run_success"].values * 100

ax1.bar(x_pos - width, gen_rates, width, label="Generation", color=colors[0], alpha=0.8)
ax1.bar(x_pos, comp_rates, width, label="Compilation", color=colors[1], alpha=0.8)
ax1.bar(x_pos + width, run_rates, width, label="Run", color=colors[2], alpha=0.8)

ax1.set_ylabel("Success Rate (%)")
ax1.set_title("(a) Success Rates", fontweight="bold", loc="left")
ax1.set_xticks(x_pos)
ax1.set_xticklabels(converters, rotation=15, ha="right")
ax1.set_ylim(0, 105)
ax1.legend(loc="lower right", frameon=True, framealpha=0.9)
ax1.grid(axis="y", alpha=0.3, linestyle="--")

# ============= Plot 2: Output similarity =============
ax2 = fig.add_subplot(gs[0, 1])

cosine_sim = results_df["similarity_cosine"].values * 100
lev_sim = results_df["similarity_levenshtein"].values * 100
jaccard_sim = results_df["similarity_jaccard"].values * 100


ax2.bar(
    x_pos - width,
    cosine_sim,
    width,
    label="Cosine Sim.",
    color=colors[0],
    alpha=0.8,
)
ax2.bar(x_pos, lev_sim, width, label="Levenshtein", color=colors[1], alpha=0.8)
ax2.bar(
    x_pos + width,
    jaccard_sim,
    width,
    label="Jaccard Sim",
    color=colors[2],
    alpha=0.8,
)

ax2.set_ylabel("Similarity")
ax2.set_title("(b) Output Similarity", fontweight="bold", loc="left")
ax2.set_xticks(x_pos)
ax2.set_xticklabels(converters, rotation=15, ha="right")
ax2.set_ylim(0, 105)
ax2.legend(loc="lower right", frameon=True, framealpha=0.9)
ax2.grid(axis="y", alpha=0.3, linestyle="--")

# ============= Plot 3: Code Quality Metrics =============
ax3 = fig.add_subplot(gs[1, 0])

maintain = results_df["maintainability_index"].values

ax3.bar(
    x_pos,
    maintain,
    width,
    label="Maintainability Index",
    color=colors[3],
    alpha=0.8,
)

alpha = 0.5
ax3.hlines(
    y=[9, 20],
    linestyles="dashed",
    colors="red",
    xmin=-0.5,
    xmax=n_converters - 0.5,
    alpha=alpha,
)
ax3.text(-0.7, 38, "High", fontsize=7, alpha=alpha)
ax3.text(-0.7, 17, "Moderate", fontsize=7, alpha=alpha)
ax3.text(-0.7, 7, "Low", fontsize=7, alpha=alpha)

ax3.set_ylabel("Maintainability Index")
ax3.set_title("(c) Code Quality", fontweight="bold", loc="left")
ax3.set_xticks(x_pos)
ax3.set_xticklabels(converters, rotation=15, ha="right")
ax3.legend(loc="lower right", frameon=True, framealpha=0.9)
ax3.grid(axis="y", alpha=0.3, linestyle="--")


ax4 = fig.add_subplot(gs[1, 1])

conversion_time = results_df["conversion_time"].values

ax4.bar(
    x_pos,
    conversion_time,
    width,
    label="Conversion Time (s)",
    color=colors[3],
    alpha=0.8,
)


ax4.set_ylabel("time (s)")
ax4.set_title("(d) Conversion Time", fontweight="bold", loc="left")
ax4.set_xticks(x_pos)
ax4.set_xticklabels(converters, rotation=15, ha="right")
ax4.legend(loc="upper right", frameon=True, framealpha=0.9)
ax4.grid(axis="y", alpha=0.3, linestyle="--")


plt.savefig("converter_comparison.pdf", dpi=300, bbox_inches="tight")
plt.savefig("converter_comparison.png", dpi=300, bbox_inches="tight")
plt.show()

# ============= Optional: Summary Statistics Table =============
print("\n=== Summary Statistics ===")
summary = pd.DataFrame(
    {
        "Converter": converters,
        "Gen %": gen_rates.round(1),
        "Comp %": comp_rates.round(1),
        "Run %": run_rates.round(1),
        "Cosine": (cosine_sim / 100).round(3),
        "Maintain": maintain.round(1),
        "Time (s)": results_df["conversion_time"].values.round(1),
    }
)
print(summary.to_string(index=False))


rounded_df = results_df.round(2)
rounded_df.drop(columns=["timestamp", "converter", "total_testcases"], inplace=True)
rounded_df["gen_success"] = (rounded_df["gen_success"] * 100).round(1)
rounded_df["comp_success"] = (rounded_df["comp_success"] * 100).round(1)
rounded_df["run_success"] = (rounded_df["run_success"] * 100).round(1)

rounded_df.rename(
    columns={
        "file": "Converter",
        "gen_success": "Gen %",
        "comp_success": "Comp %",
        "run_success": "Run %",
        "similarity_cosine": "Cosine",
        "similarity_levenshtein": "Levenshtein",
        "similarity_jaccard": "Jaccard",
        "halstead_volume": "Halstead Vol.",
        "cyclomatic_complexity": "Cyclomatic Cmplx.",
        "lines_of_code": "Lines of Code",
        "maintainability_index": "Maintainability",
        "conversion_time": "Time (s)",
    },
    inplace=True,
)
rounded_df.sort_values(by="Converter", inplace=True)
print("\n=== Rounded DataFrame ===")
print(rounded_df.to_string(index=False))
print(rounded_df.transpose().to_csv())
print(
    rounded_df.to_latex(
        index=False,
        float_format="%.2f",
        caption="Benchmark Results Summary",
        label="tab:benchmark_results",
    )
)
