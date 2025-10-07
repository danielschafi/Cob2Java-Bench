# Cob2Java-Bench
Benchmark for cobol to java conversion. Can be used to test conversions to other programming languages other than Java too. 


## Adding New Cobol files to the benchmark dataset
1. Add the files to src_data
    - Check if they are compilable with gnucobol compiler before adding them
2. run utils/filter_short_files.py to remove all files from the directory that are shorter than a threshold number of lines

    ```
    python utils/filter_short_files.py src_data short_files 60
    ```
3. Rewrite file paths in the source code to be in the same dir e.g. data/experiment2/data.dat -> data.dat 

# Config
Set the Model you want to use in the env. you can use pretty much any model that is available on huggingface



## Data 
### (src_data)
- From codetransocean/niche dataset
- From theStack dataset on huggingface

The compilable cobol files get put into this folder