1. Literaturrecherche
2. Überlegen, wie die evaluation gemacht werden könnte.
    - Alle cobol scripts so schreiben, dass die Parameter mit argumenten übergeben werden können.
    - Dann für jedes Argument eine Range von Werten definieren
    - All diese Kombinationen in das COBOL programm füttern und die Outputs aufzeichnen
    - Alles in einem JSON speichern
    - Programm übersetzen, sollte mit gleichen inputs zu gleichen outputs führen.
    - Das sollte auf Equivalenz testen

    - Zusätzlich auch noch statische code analysen durchführen, readability etc.

3. Suche nach Datset
    - Nicht wirklich erfolgreich, habe zwei potentielle gefunden. Eines recht klein, das andere bis jetzt nicht zum laufen gekriegt.
    - Was kann man hier machen? Ideen?

4. Wie kann ich von python aus COBOL programme compilieren und ausführen, ihre outputs einfangen?
    - Cobol compiler installieren, dann einfach über subprocess.run(cobx ....)
5. Wie kann ich von python aus Java programme compilieren und ausführen, ihre outputs einfangen?
    - Auch compiler installieren, und über subprocess.run(javac ...), capture_output=True

6. LLM Abstraction layer
    - Einfaches laden von verschiedenen LLMs, setzen von system, user prompts, nur noch llm.predict(cobol_code)

7. Programm zur umwandlung von COBOL zu Java mit hilfe eines local LLM (Qwen2.5-coder-instruct-7B)
    - Base
    - Mit AST

8. proleap-cobol-parser verwenden, um den AST von COBOL zu extrahieren
    - Ist ein Java programm
    - Das aufrufbar machen, so dass man nur noch parser.getAST(code_file) machen muss
    - Wrapper darum -> Extrac Java file
    - python class um die interaktion damit zu handeln.
    - Hat lange gedauert das einzubinden
    - Danke Claude <3



# TODO
- after sample io generation check if it runs
- when there are no io files we need to copy the cob file to the testcase dir too. -> Done
- Check how much sense the outputs make
- also use a few smaller files (and github cobol kind of trash peobably)
- remove simmilar cobol source files 
- Check for equivalence in stdout output and output files

Rerun on dataset to be sure it works and we do not have duplicates




1. Generate the bigger dataset set file size limit smaller to like 60
2. Rerun the pipeline
3. Build the benchmarking tool
    - For each file in the testcases dic
    - setup the files
    - some object that inherits from converterbase with method convert(code or filepath, outputpath) is passed to the testcaserunner
    - the testcase runner uses that object to convert the cobol file
    - then compilation
    - run the java file
    - check the outputs (outputs stdout, outfiles) for equivalence (same but without whitespace maybe)
    - Keep track somewhere
    - Display results over all testcases
    - Code readability metrics
4. Fix the code, cleanup repo, make workflow as easy as possible