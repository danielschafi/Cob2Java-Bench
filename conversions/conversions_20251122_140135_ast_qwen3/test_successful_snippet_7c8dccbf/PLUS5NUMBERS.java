import java.io.*;
import java.util.*;

public class PLUS5NUMBERS {
    private static final String IN_FILE_PATH = "numbers.txt";
    private static final String OUT_FILE_PATH = "newNumbers.txt";
    
    public static void main(String[] args) {
        try {
            FileReader inFileReader = new FileReader(IN_FILE_PATH);
            BufferedReader inFile = new BufferedReader(inFileReader);
            
            FileWriter outFileWriter = new FileWriter(OUT_FILE_PATH, true);
            PrintWriter outFile = new PrintWriter(outFileWriter);
            
            String line;
            while ((line = inFile.readLine()) != null) {
                int originalNumber = Integer.parseInt(line.trim());
                int result = originalNumber + 5;
                
                String formattedResult = String.format("%5d", result).replace(' ', '0');
                outFile.println(formattedResult);
                System.out.println(formattedResult);
            }
            
            inFile.close();
            outFile.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}