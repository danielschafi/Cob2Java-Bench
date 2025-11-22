import java.io.*;
import java.nio.file.*;

public class lrecl80 {
    private static final String INFILE_NAME = "infile.dat";
    private static final String OUTFILE_NAME = "outfile.dat";
    private static final int RECORD_LENGTH = 80;

    public static void main(String[] args) {
        String infileStatus = "00";
        String outfileStatus = "00";
        
        try (BufferedReader infile = Files.newBufferedReader(Paths.get(INFILE_NAME));
             BufferedWriter outfile = Files.newBufferedWriter(Paths.get(OUTFILE_NAME))) {
            
            String inputText;
            while ((inputText = readRecord(infile)) != null) {
                infileStatus = "00";
                String outputText = reverseString(inputText);
                writeRecord(outfile, outputText);
                if (!outfileStatus.equals("00")) {
                    System.err.println("error writing: " + outputText);
                }
            }
            infileStatus = "10";
            
        } catch (IOException e) {
            System.err.println("error opening input " + INFILE_NAME);
            return;
        }
        
        try (BufferedReader outfile = Files.newBufferedReader(Paths.get(OUTFILE_NAME))) {
            outfileStatus = "00";
            String outputText;
            while ((outputText = readRecord(outfile)) != null) {
                outfileStatus = "00";
                System.out.println(trimTrailing(outputText));
            }
            outfileStatus = "10";
            
        } catch (IOException e) {
            System.err.println("error opening input " + OUTFILE_NAME);
            return;
        }
    }
    
    private static String readRecord(BufferedReader reader) throws IOException {
        char[] buffer = new char[RECORD_LENGTH];
        int charsRead = reader.read(buffer, 0, RECORD_LENGTH);
        
        if (charsRead == -1) {
            return null;
        }
        
        if (charsRead < RECORD_LENGTH) {
            for (int i = charsRead; i < RECORD_LENGTH; i++) {
                buffer[i] = ' ';
            }
        }
        
        return new String(buffer);
    }
    
    private static void writeRecord(BufferedWriter writer, String text) throws IOException {
        String record = text;
        if (record.length() < RECORD_LENGTH) {
            record = String.format("%-80s", record);
        } else if (record.length() > RECORD_LENGTH) {
            record = record.substring(0, RECORD_LENGTH);
        }
        writer.write(record);
        writer.newLine();
    }
    
    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    private static String trimTrailing(String str) {
        int end = str.length() - 1;
        while (end >= 0 && str.charAt(end) == ' ') {
            end--;
        }
        return str.substring(0, end + 1);
    }
}