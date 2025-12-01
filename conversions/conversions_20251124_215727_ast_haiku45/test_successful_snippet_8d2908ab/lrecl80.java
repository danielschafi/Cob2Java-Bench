import java.io.*;
import java.nio.file.*;

public class lrecl80 {
    private static String infileName = "infile.dat";
    private static String outfileName = "outfile.dat";
    private static String infileStatus = "";
    private static String outfileStatus = "";
    
    private static BufferedReader infileReader;
    private static BufferedWriter outfileWriter;
    private static BufferedReader outfileReaderForDisplay;
    
    public static void main(String[] args) {
        try {
            openInputFile();
            if (!infileStatus.equals("00")) {
                System.err.println("error opening input " + infileName);
                System.exit(1);
            }
            
            openOutputFile();
            if (!outfileStatus.equals("00")) {
                System.err.println("error opening output " + outfileName);
                System.exit(1);
            }
            
            String inputText = readInputFile();
            while (inputText != null) {
                String outputText = reverseString(inputText);
                writeOutputFile(outputText);
                if (!outfileStatus.equals("00")) {
                    System.err.println("error writing: " + outputText);
                }
                inputText = readInputFile();
            }
            
            closeFiles();
            
            openOutputFileForReading();
            if (!outfileStatus.equals("00")) {
                System.err.println("error opening input " + outfileName);
                System.exit(1);
            }
            
            String outputText = readOutputFile();
            while (outputText != null) {
                System.out.println(trimTrailing(outputText));
                outputText = readOutputFile();
            }
            
            closeOutputFileForReading();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void openInputFile() throws IOException {
        try {
            infileReader = new BufferedReader(new FileReader(infileName));
            infileStatus = "00";
        } catch (IOException e) {
            infileStatus = "10";
        }
    }
    
    private static void openOutputFile() throws IOException {
        try {
            outfileWriter = new BufferedWriter(new FileWriter(outfileName));
            outfileStatus = "00";
        } catch (IOException e) {
            outfileStatus = "10";
        }
    }
    
    private static void openOutputFileForReading() throws IOException {
        try {
            outfileReaderForDisplay = new BufferedReader(new FileReader(outfileName));
            outfileStatus = "00";
        } catch (IOException e) {
            outfileStatus = "10";
        }
    }
    
    private static String readInputFile() throws IOException {
        try {
            String line = infileReader.readLine();
            if (line == null) {
                infileStatus = "10";
                return null;
            }
            infileStatus = "00";
            if (line.length() < 80) {
                line = String.format("%-80s", line);
            } else if (line.length() > 80) {
                line = line.substring(0, 80);
            }
            return line;
        } catch (IOException e) {
            infileStatus = "10";
            return null;
        }
    }
    
    private static String readOutputFile() throws IOException {
        try {
            String line = outfileReaderForDisplay.readLine();
            if (line == null) {
                outfileStatus = "10";
                return null;
            }
            outfileStatus = "00";
            if (line.length() < 80) {
                line = String.format("%-80s", line);
            } else if (line.length() > 80) {
                line = line.substring(0, 80);
            }
            return line;
        } catch (IOException e) {
            outfileStatus = "10";
            return null;
        }
    }
    
    private static void writeOutputFile(String text) throws IOException {
        try {
            outfileWriter.write(text);
            outfileWriter.newLine();
            outfileStatus = "00";
        } catch (IOException e) {
            outfileStatus = "10";
        }
    }
    
    private static void closeFiles() throws IOException {
        if (infileReader != null) {
            infileReader.close();
        }
        if (outfileWriter != null) {
            outfileWriter.close();
        }
    }
    
    private static void closeOutputFileForReading() throws IOException {
        if (outfileReaderForDisplay != null) {
            outfileReaderForDisplay.close();
        }
    }
    
    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    private static String trimTrailing(String str) {
        return str.replaceAll("\\s+$", "");
    }
}