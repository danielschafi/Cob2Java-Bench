import java.io.*;
import java.util.*;

public class PLUS5NUMBERS {
    private static final String IN_FILE_PATH = "numbers.txt";
    private static final String OUT_FILE_PATH = "newNumbers.txt";
    
    public static void main(String[] args) {
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_PATH));
            BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_PATH, true));
            
            String line;
            int recordCount = 0;
            
            while ((line = inFile.readLine()) != null) {
                recordCount++;
                int originalNumber = Integer.parseInt(line.trim());
                int result = originalNumber + 5;
                
                String formattedResult = String.format("%05d", result);
                outFile.write(formattedResult);
                outFile.newLine();
                
                System.out.println(formattedResult);
            }
            
            inFile.close();
            outFile.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}