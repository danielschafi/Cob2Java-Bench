import java.io.*;
import java.util.*;

public class AOC202004_1 {
    private static int fileStatus = 0;
    private static int recLen = 0;
    private static String[] wsRow = new String[8];
    private static String wsChar = "";
    
    private static int correctPassports = 0;
    private static int foundFields = 0;
    private static int stringPtr = 1;
    private static int i = 1;

    public static void main(String[] args) {
        try {
            performMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performMain() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d4.input"));
        String line;
        
        while ((line = reader.readLine()) != null) {
            recLen = line.length();
            performRead(line);
        }
        
        reader.close();
        performNextPassport();
        System.out.println(correctPassports);
    }

    private static void performRead(String inputRecord) {
        if (recLen == 0) {
            performNextPassport();
        } else {
            performProcessRow(inputRecord);
        }
    }

    private static void performNextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }

    private static void performProcessRow(String inputRecord) {
        stringPtr = 0;
        
        String[] tokens = inputRecord.split(" ");
        for (i = 0; i < Math.min(8, tokens.length); i++) {
            wsRow[i] = tokens[i];
        }
        
        for (i = 0; i < Math.min(8, tokens.length); i++) {
            if (wsRow[i] != null && wsRow[i].length() > 0) {
                wsChar = wsRow[i].substring(0, 1);
                if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                    foundFields++;
                }
            }
        }
    }
}