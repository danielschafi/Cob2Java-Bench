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



## Random
[ ] Try other llms for translation
    [ ] Qwen 3.5 Coder
    [ ] Claude Haiku 4.5
    [ ] ChatGPT 5.1


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