import java.io.*;
import java.nio.file.*;
import java.util.*;

public class lrecl80 {
    private static final String INFILE_NAME = "infile.dat";
    private static final String OUTFILE_NAME = "outfile.dat";

    public static void main(String[] args) {
        try {
            // Open input file for reading
            BufferedReader infile = new BufferedReader(new FileReader(INFILE_NAME));
            
            // Open output file for writing
            BufferedWriter outfile = new BufferedWriter(new FileWriter(OUTFILE_NAME));
            
            String inputText;
            
            // Read from input, reverse each line, write to output
            while ((inputText = infile.readLine()) != null) {
                // Pad to 80 characters if needed
                if (inputText.length() < 80) {
                    inputText = String.format("%-80s", inputText);
                }
                
                String outputText = new StringBuilder(inputText).reverse().toString();
                outfile.write(outputText);
                outfile.newLine();
            }
            
            infile.close();
            outfile.close();
            
            // Open output file for reading and display
            BufferedReader readOutfile = new BufferedReader(new FileReader(OUTFILE_NAME));
            
            String outputText;
            while ((outputText = readOutfile.readLine()) != null) {
                System.out.println(outputText.stripTrailing());
            }
            
            readOutfile.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}