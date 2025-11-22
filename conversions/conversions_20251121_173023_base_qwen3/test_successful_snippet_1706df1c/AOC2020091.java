import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020091 {
    private static final int P_LEN = 25;
    private static final int MAX_NUMBERS = 1000;
    
    public static void main(String[] args) {
        long[] wsNumbers = new long[MAX_NUMBERS];
        int i = 1;
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            while ((line = br.readLine()) != null) {
                wsNumbers[i] = Long.parseLong(line.trim());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        i = P_LEN + 1;
        long result = 0;
        boolean foundNumber = false;
        
        while (!foundNumber && i < i) { // This condition seems incorrect in original COBOL
            long foundSum = 0;
            int j0 = i - P_LEN;
            
            for (int j = j0; j < i; j++) {
                for (int k = j; k < i; k++) {
                    long sum = wsNumbers[j] + wsNumbers[k];
                    if (sum == wsNumbers[i]) {
                        foundSum = 1;
                        break;
                    }
                }
                if (foundSum == 1) break;
            }
            
            if (foundSum == 0) {
                foundNumber = true;
                result = wsNumbers[i];
            } else {
                i++;
            }
        }
        
        System.out.println(result);
    }
}