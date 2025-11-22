import java.io.*;
import java.util.*;

public class AOC20200202 {
    private static int correctRows = 0;

    public static void main(String[] args) {
        try {
            readAndProcessFile("d2.input");
            System.out.println(correctRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndProcessFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private static void processRecord(String inputRecord) {
        int conditionsMet = 0;
        
        String[] parts = inputRecord.split("[\\s\\-:]+");
        
        int wsPos1 = Integer.parseInt(parts[0]);
        int wsPos2 = Integer.parseInt(parts[1]);
        char wsChar = parts[2].charAt(0);
        String wsPassword = parts[3];
        
        if (wsPassword.length() >= wsPos1 && wsPassword.charAt(wsPos1 - 1) == wsChar) {
            conditionsMet++;
        }
        
        if (wsPassword.length() >= wsPos2 && wsPassword.charAt(wsPos2 - 1) == wsChar) {
            conditionsMet++;
        }
        
        if (conditionsMet == 1) {
            correctRows++;
        }
    }
}