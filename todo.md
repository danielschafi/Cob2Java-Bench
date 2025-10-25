# Cob2Java-Bench ToDo's

## Current Run
[ ] Wait for results of AST assisted conversion
    [] Compare results with conversion without AST, maybe we can already make some conclusions

## Better Experiment Logging
[ ] Before that add saving of every conversions results to a dataframe, export to csv so we can do other things with it, aggregate it differently 
[ ] Export the metrics json after every testcase (aggregated?) so that we can keep track
[ ] In the logs add counter x out of n testcases run...  e with running error, g with generation error, c with compile error
[ ] Should similarity 

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