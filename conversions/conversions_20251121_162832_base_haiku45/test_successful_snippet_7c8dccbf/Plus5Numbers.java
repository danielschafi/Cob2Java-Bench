import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Plus5Numbers {
    private static final String INPUT_FILE = "numbers.txt";
    private static final String OUTPUT_FILE = "newNumbers.txt";
    
    private int originalNumber;
    private int result;
    private String neResult;
    private String eofSwitch;
    private int recCounter;
    
    public Plus5Numbers() {
        this.eofSwitch = "N";
        this.recCounter = 0;
    }
    
    public static void main(String[] args) {
        Plus5Numbers program = new Plus5Numbers();
        program.run();
    }
    
    public void run() {
        initialize();
        while (!eofSwitch.equals("Y")) {
            processRecords();
        }
        terminate();
    }
    
    private void initialize() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
            String line = reader.readLine();
            if (line == null) {
                eofSwitch = "Y";
                reader.close();
            } else {
                originalNumber = Integer.parseInt(line.trim());
                reader.close();
            }
        } catch (IOException e) {
            eofSwitch = "Y";
        }
    }
    
    private void processRecords() {
        result = originalNumber + 5;
        neResult = String.format("%5d", result).replace(' ', '0');
        
        try (FileWriter fw = new FileWriter(OUTPUT_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(neResult);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(neResult);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum == recCounter + 2) {
                    originalNumber = Integer.parseInt(line.trim());
                    recCounter++;
                    return;
                }
            }
            eofSwitch = "Y";
        } catch (IOException e) {
            eofSwitch = "Y";
        }
    }
    
    private void terminate() {
    }
}