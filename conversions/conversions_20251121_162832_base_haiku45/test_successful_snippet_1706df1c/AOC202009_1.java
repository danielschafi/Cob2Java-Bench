import java.io.*;
import java.util.*;

public class AOC202009_1 {
    private static final int MAX_NUMBERS = 1000;
    private static final int P_LEN = 25;
    
    private long[] wsNumbers = new long[MAX_NUMBERS];
    private long wsSum;
    private long wsResult;
    private int fileStatus = 0;
    private int i = 1;
    private int foundNumber = 0;
    private int foundSum = 0;
    
    public static void main(String[] args) {
        AOC202009_1 program = new AOC202009_1();
        program.run();
    }
    
    private void run() {
        readFile();
        i = P_LEN + 1;
        findNumber();
        System.out.println(wsResult);
    }
    
    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processRecord(String record) {
        wsNumbers[i] = Long.parseLong(record.trim());
        i++;
    }
    
    private void findNumber() {
        while (foundNumber == 0) {
            foundSum = 0;
            
            int j0 = i - P_LEN;
            boolean found = false;
            
            for (int j = j0; j <= i && !found; j++) {
                for (int k = j; k <= i && !found; k++) {
                    wsSum = wsNumbers[j] + wsNumbers[k];
                    if (wsSum == wsNumbers[i]) {
                        foundSum = 1;
                        found = true;
                    }
                }
            }
            
            if (foundSum == 0) {
                foundNumber = 1;
                wsResult = wsNumbers[i];
            } else {
                i++;
            }
        }
    }
}