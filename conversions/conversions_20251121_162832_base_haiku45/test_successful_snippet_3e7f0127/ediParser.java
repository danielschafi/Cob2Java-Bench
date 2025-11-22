import java.io.*;
import java.nio.file.*;

public class ediParser {
    private static final String INPUT_FILE = "Team3_EDI_Data.txt";
    private static final String OUTPUT_FILE = "Team3_EDI_FINAL.txt";
    
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private String currentLine;
    private boolean endOfFile;
    
    public static void main(String[] args) {
        ediParser parser = new ediParser();
        parser.begin();
    }
    
    private void begin() {
        try {
            openFiles();
            readLine();
            
            while (!endOfFile) {
                processEdi();
            }
            
            stopRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void openFiles() throws IOException {
        inputReader = new BufferedReader(new FileReader(INPUT_FILE));
        outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
    }
    
    private void readLine() throws IOException {
        currentLine = inputReader.readLine();
        if (currentLine == null) {
            endOfFile = true;
        } else {
            endOfFile = false;
        }
    }
    
    private void processEdi() throws IOException {
        if (currentLine == null || currentLine.isEmpty()) {
            readLine();
            return;
        }
        
        int stringEnd = currentLine.length() - 1;
        while (stringEnd >= 0 && currentLine.charAt(stringEnd) == ' ') {
            stringEnd--;
        }
        
        String ediSeg;
        if (stringEnd >= 0) {
            ediSeg = currentLine.substring(0, stringEnd + 1);
        } else {
            ediSeg = "";
        }
        
        String displayEdiSeg = ediSeg;
        
        outputWriter.write(displayEdiSeg);
        outputWriter.newLine();
        outputWriter.write(" ");
        outputWriter.newLine();
        
        readLine();
    }
    
    private void stopRun() throws IOException {
        if (inputReader != null) {
            inputReader.close();
        }
        if (outputWriter != null) {
            outputWriter.close();
        }
    }
}