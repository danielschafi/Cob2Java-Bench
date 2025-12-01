import java.io.*;
import java.util.*;

public class AOC202009 {
    private static final int P_LEN = 25;
    private static final int MAX_NUMBERS = 1000;
    
    private long[] wsNumbers = new long[MAX_NUMBERS];
    private long wsSum = 0;
    private long wsResult = 0;
    private int fileStatus = 0;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int j0 = 1;
    private int foundNumber = 0;
    private int foundSum = 0;
    
    public static void main(String[] args) {
        AOC202009 program = new AOC202009();
        program.run();
    }
    
    private void run() {
        read();
        i = P_LEN + 1;
        findNumber();
        System.out.println(wsResult);
    }
    
    private void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processRecord(String inputRecord) {
        try {
            wsNumbers[i] = Long.parseLong(inputRecord.trim());
            i++;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    private void findNumber() {
        while (foundNumber == 0) {
            foundSum = 0;
            j0 = i - P_LEN;
            
            boolean found = false;
            for (j = j0; j <= i && !found; j++) {
                for (k = j; k <= i && !found; k++) {
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