import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class AOC_2020_09_1 {
    private static final int MAX_NUMBERS = 1000;
    private static final int PREAMBLE_LENGTH = 25;
    
    private BigInteger[] wsNumbers = new BigInteger[MAX_NUMBERS];
    private int currentIndex = 0;
    
    public static void main(String[] args) {
        AOC_2020_09_1 program = new AOC_2020_09_1();
        program.run();
    }
    
    public void run() {
        readFile();
        BigInteger result = findNumber();
        System.out.println(result);
    }
    
    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private void processRecord(String record) {
        if (record.length() > 0 && currentIndex < MAX_NUMBERS) {
            wsNumbers[currentIndex] = new BigInteger(record);
            currentIndex++;
        }
    }
    
    private BigInteger findNumber() {
        int i = PREAMBLE_LENGTH;
        
        while (i < currentIndex) {
            boolean foundSum = false;
            int j0 = i - PREAMBLE_LENGTH;
            
            outerLoop:
            for (int j = j0; j < i; j++) {
                for (int k = j; k < i; k++) {
                    BigInteger sum = wsNumbers[j].add(wsNumbers[k]);
                    if (sum.equals(wsNumbers[i])) {
                        foundSum = true;
                        break outerLoop;
                    }
                }
            }
            
            if (!foundSum) {
                return wsNumbers[i];
            }
            
            i++;
        }
        
        return BigInteger.ZERO;
    }
}