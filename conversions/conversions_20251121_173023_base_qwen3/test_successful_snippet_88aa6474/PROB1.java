import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PROB1 {
    private static final int MAX_SIZE = 100000;
    
    public static void main(String[] args) {
        int[] priorValue = new int[MAX_SIZE];
        int priorCount = 0;
        int runningTotal = 0;
        int verboseFlag = 1;
        String inputFile = "PROB1.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                int inputValue = Integer.parseInt(line.trim());
                
                if (inputValue < 0) {
                    System.out.println("Found duplicate");
                    System.out.println(-inputValue);
                    return;
                }
                
                if (verboseFlag == 1) {
                    if (inputValue > 0) {
                        System.out.println("Nice!");
                    } else {
                        System.out.println("Naughty!");
                    }
                }
                
                runningTotal += inputValue;
                
                for (int i = 0; i < priorCount; i++) {
                    if (priorValue[i] == runningTotal) {
                        System.out.println("Found duplicate");
                        System.out.println(runningTotal);
                        return;
                    }
                }
                
                if (priorCount >= MAX_SIZE) {
                    System.out.println("ERROR: Count exceeds 100,000.");
                    return;
                }
                
                priorValue[priorCount++] = runningTotal;
            }
            
            System.out.println("Resulting Frequency:");
            System.out.println(runningTotal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}