import java.io.*;
import java.util.*;

public class AOC2020041 {
    private static int correctPassports = 0;
    private static int foundFields = 0;
    private static int stringPtr = 1;
    private static int i = 1;
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d4.input"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    nextPassport();
                } else {
                    processRow(line);
                }
            }
            
            nextPassport();
            System.out.println(correctPassports);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void nextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }
    
    private static void processRow(String inputRecord) {
        stringPtr = 1;
        
        String[] wsRow = new String[8];
        StringTokenizer tokenizer = new StringTokenizer(inputRecord);
        
        for (int j = 0; j < 8 && tokenizer.hasMoreTokens(); j++) {
            wsRow[j] = tokenizer.nextToken();
        }
        
        for (int j = 0; j < 8; j++) {
            if (wsRow[j] != null && wsRow[j].length() > 0) {
                char wsChar = wsRow[j].charAt(0);
                if (wsChar != 'c' && wsChar != ' ') {
                    foundFields++;
                }
            }
        }
    }
}