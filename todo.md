# Cob2Java-Bench ToDo's

## Current Run
[x] Wait for results of AST assisted conversion
    [x] Compare results with conversion without AST, maybe we can already make some conclusions
    - With ast actually worse, probably because it is too much text and it cant actually use all that information so its just confusion for the llm. Or because programm paradigms are different 

## Better Experiment Logging
[x] Before that add saving of every conversions results to a dataframe, export to csv so we can do other things with it, aggregate it differently 
[x] Export the metrics json after every testcase (aggregated?) so that we can keep track
[x] In the logs add counter x out of n testcases run...  e with running error, g with generation error, c with compile error


## Other LLM to improve input data and conversion quality
[x] Get Claude Credits
[x] Build an LLM Class that can work with an online LLM, either ChatGPT or Claude
[ ] Use it to generate better sample data (Run that pipeline again)
[ ] Run the Conversion with and without the AST again


## Try some other llms maybe (optional)
[ ] Qwen3-Coder-30b


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