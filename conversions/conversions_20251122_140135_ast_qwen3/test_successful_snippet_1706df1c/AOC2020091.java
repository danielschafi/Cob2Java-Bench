import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020091 {
    private static final int P_LEN = 25;
    private static final int MAX_NUMBERS = 1000;
    
    public static void main(String[] args) {
        long[] wsNumbers = new long[MAX_NUMBERS];
        int i = 0;
        int foundNumber = 0;
        long wsResult = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wsNumbers[i] = Long.parseLong(line.trim());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        i = P_LEN;
        while (foundNumber == 0) {
            long foundSum = 0;
            
            int j0 = i - P_LEN;
            
            for (int j = j0; j < i; j++) {
                for (int k = j; k < i; k++) {
                    long wsSum = wsNumbers[j] + wsNumbers[k];
                    if (wsSum == wsNumbers[i]) {
                        foundSum = 1;
                        break;
                    }
                }
                if (foundSum == 1) {
                    break;
                }
            }
            
            if (foundSum == 0) {
                foundNumber = 1;
                wsResult = wsNumbers[i];
            } else {
                i++;
            }
        }
        
        System.out.println(wsResult);
    }
}