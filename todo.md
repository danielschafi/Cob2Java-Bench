# Cob2Java-Bench ToDo's

## Add command line arguments option
[x] Tried generating all files and contents by llm by providing only json output template. -> Worked only for some things, but not for others
[x] detect them
[a] generate sample arguments
[a] capture output, add those arguments when executing
[a] when running benchmark add those arguments when executing
[a] Check when there is a sysin/stdin and it is a filename, we need to generate contents for that file.
aborted

- Or really just say we do not work with command line arguments, its like 60 files (out of 390). Because handling them is more difficult. args could be a filename, how do i know if the arg is a filename where i would then need to generate the content of that file too. Adding in future ;D
 
-> We dont use these 47 programms that have cmd line args
This leaves us with 318 programms after all filterings

## Better Preparation
[x] Exclude files from, benchmark and filter them if
    [x] Screen Section 25.files
    [x] Accept * from console
[a] when making requests with claude we sometimes get errors that cause the current programm to crash, catch that

## generate sample io files 
[x] Generate sample io
[x] capture cobol output -> 244 successfull testcases, 70 failed
[x] Run benchmark with claude sonnet 4.5


## Random
[ ] Try other llms for translation
    [ ] Qwen 3.5 Coder
    [ ] Claude Haiku 4.5

## Comparison
[ ] Script to build a table to compare all the results in the benchmark_results folder

## Finally
[ ] Clean up Code
    [ ] Folder Structure
    [ ] Repo Structure
    [ ] Code Style
    [ ] Ease of use
[ ] Add readme, how to use
    [ ] Setup
    [ ] Usage
        [ ] How to test your own converter
[ ] Add default .env




## Remarks
- Did not run sonnet 4.5 with AST because it would have cost me like 30$. Not willing to pay that for just a project to have some more numbers (10-60k input tokens)