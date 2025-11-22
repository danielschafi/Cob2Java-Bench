import java.io.*;
import java.util.*;

public class AOC202004_1 {
    private static int correctPassports = 0;
    private static int foundFields = 0;
    private static String[] wsRow = new String[8];
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processRecord(String line) {
        if (line.trim().isEmpty()) {
            nextPassport();
        } else {
            processRow(line);
        }
    }
    
    private static void nextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }
    
    private static void processRow(String inputRecord) {
        String[] fields = inputRecord.split(" ");
        
        for (int i = 0; i < fields.length && i < 8; i++) {
            wsRow[i] = fields[i];
        }
        
        for (int i = 0; i < fields.length && i < 8; i++) {
            if (wsRow[i] != null && wsRow[i].length() > 0) {
                char wsChar = wsRow[i].charAt(0);
                if (wsChar != 'c' && wsChar != ' ') {
                    foundFields++;
                }
            }
        }
    }
}