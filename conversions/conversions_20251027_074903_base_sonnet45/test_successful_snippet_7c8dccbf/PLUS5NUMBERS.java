import java.io.*;
import java.nio.file.*;

public class PLUS5NUMBERS {
    
    private static final String IN_FILE = "numbers.txt";
    private static final String OUT_FILE = "newNumbers.txt";
    
    private String eofSwitch = "N";
    private int recCounter = 0;
    private int originalNumber = 0;
    private int result = 0;
    
    private BufferedReader inFileReader;
    private BufferedWriter outFileWriter;
    
    public static void main(String[] args) {
        PLUS5NUMBERS program = new PLUS5NUMBERS();
        program.run000Main();
    }
    
    private void run000Main() {
        perform100Initialize();
        while (!eofSwitch.equals("Y")) {
            perform200ProcessRecords();
        }
        perform300Terminate();
    }
    
    private void perform100Initialize() {
        try {
            inFileReader = new BufferedReader(new FileReader(IN_FILE));
            outFileWriter = new BufferedWriter(new FileWriter(OUT_FILE, true));
            readInFile();
        } catch (IOException e) {
            System.err.println("Error opening files: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void perform200ProcessRecords() {
        result = originalNumber + 5;
        
        String neResult = String.format("%5d", result);
        
        try {
            outFileWriter.write(String.format("%05d", result));
            outFileWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        
        System.out.println(neResult);
        
        readInFile();
    }
    
    private void perform300Terminate() {
        try {
            if (inFileReader != null) {
                inFileReader.close();
            }
            if (outFileWriter != null) {
                outFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing files: " + e.getMessage());
        }
    }
    
    private void readInFile() {
        try {
            String line = inFileReader.readLine();
            if (line == null) {
                eofSwitch = "Y";
            } else {
                line = line.trim();
                if (line.isEmpty()) {
                    eofSwitch = "Y";
                } else {
                    originalNumber = Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            eofSwitch = "Y";
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
            eofSwitch = "Y";
        }
    }
}