import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sedol {
    public static void main(String[] args) {
        String[] digitWeights = {"1", "3", "1", "7", "3", "9", "1"};
        
        try (BufferedReader reader = new BufferedReader(new FileReader("sedol.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String sedolCode = line.trim().toUpperCase();
                
                if (sedolCode.length() != 6) {
                    System.out.println("Invalid SEDOL: " + sedolCode);
                    continue;
                }
                
                boolean valid = true;
                int[] weightedSumParts = new int[6];
                
                for (int digitNum = 0; digitNum < 6; digitNum++) {
                    char ch = sedolCode.charAt(digitNum);
                    int weight = Integer.parseInt(digitWeights[digitNum]);
                    
                    if (Character.isLetter(ch)) {
                        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                            System.out.println("Invalid SEDOL: " + sedolCode);
                            valid = false;
                            break;
                        }
                        int charValue = (ch - 'A' + 10) * weight;
                        weightedSumParts[digitNum] = charValue;
                    } else if (Character.isDigit(ch)) {
                        int numValue = Character.getNumericValue(ch) * weight;
                        weightedSumParts[digitNum] = numValue;
                    } else {
                        System.out.println("Invalid SEDOL: " + sedolCode);
                        valid = false;
                        break;
                    }
                }
                
                if (!valid) {
                    continue;
                }
                
                int weightedSum = 0;
                for (int digitNum = 0; digitNum < 6; digitNum++) {
                    weightedSum += weightedSumParts[digitNum];
                }
                
                int checkDigit = (10 - (weightedSum % 10)) % 10;
                
                System.out.println(sedolCode + checkDigit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}