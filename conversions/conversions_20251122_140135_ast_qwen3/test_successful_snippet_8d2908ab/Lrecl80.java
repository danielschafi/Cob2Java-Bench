import java.io.*;
import java.util.*;

public class Lrecl80 {
    private static final String INFILE_NAME = "infile.dat";
    private static final String OUTFILE_NAME = "outfile.dat";
    
    public static void main(String[] args) {
        try {
            // Open input file
            FileReader inputFileReader = new FileReader(INFILE_NAME);
            BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
            
            // Open output file
            FileWriter outputFileWriter = new FileWriter(OUTFILE_NAME);
            BufferedWriter outputBufferedWriter = new BufferedWriter(outputFileWriter);
            
            // Read and process input file
            String line;
            while ((line = inputBufferedReader.readLine()) != null) {
                StringBuilder reversedLine = new StringBuilder(line);
                reversedLine.reverse();
                
                outputBufferedWriter.write(reversedLine.toString());
                outputBufferedWriter.newLine();
            }
            
            // Close files
            inputBufferedReader.close();
            outputFileWriter.close();
            
            // Open output file for reading
            FileReader outputFileReader = new FileReader(OUTFILE_NAME);
            BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
            
            // Read and display output file content
            while ((line = outputBufferedReader.readLine()) != null) {
                System.out.println(line.trim());
            }
            
            // Close files
            outputBufferedReader.close();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}