import java.io.*;
import java.nio.file.*;

public class lrecl80 {
    private static final String INFILE_NAME = "infile.dat";
    private static final String OUTFILE_NAME = "outfile.dat";
    
    public static void main(String[] args) {
        try {
            // Open input file
            BufferedReader infile = Files.newBufferedReader(Paths.get(INFILE_NAME));
            
            // Open output file
            BufferedWriter outfile = Files.newBufferedWriter(Paths.get(OUTFILE_NAME));
            
            // Read and process input
            String inputText;
            while ((inputText = infile.readLine()) != null) {
                StringBuilder reversed = new StringBuilder(inputText).reverse();
                outfile.write(reversed.toString());
                outfile.newLine();
            }
            
            infile.close();
            outfile.close();
            
            // Reopen output file for reading
            BufferedReader outfileReader = Files.newBufferedReader(Paths.get(OUTFILE_NAME));
            
            // Display reversed lines
            String outputText;
            while ((outputText = outfileReader.readLine()) != null) {
                System.out.println(outputText.trim());
            }
            
            outfileReader.close();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}