import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sedol {
    public static void main(String[] args) {
        String sedolFileStatus = "00";
        int[] digitWeights = {1, 3, 1, 7, 3, 9, 1};
        int[] weightedSumParts = new int[6];
        
        try (BufferedReader reader = new BufferedReader(new FileReader("sedol.txt"))) {
            String sedolLine;
            
            while ((sedolLine = reader.readLine()) != null) {
                if (sedolLine.length() < 6) {
                    continue;
                }
                
                String sedol = sedolLine.substring(0, 6).toUpperCase();
                boolean invalid = false;
                
                for (int digitNum = 0; digitNum < 6; digitNum++) {
                    char currentChar = sedol.charAt(digitNum);
                    
                    if (Character.isLetter(currentChar)) {
                        if (currentChar == 'A' || currentChar == 'E' || currentChar == 'I' || 
                            currentChar == 'O' || currentChar == 'U') {
                            System.out.println("Invalid SEDOL: " + sedol);
                            invalid = true;
                            break;
                        }
                        
                        weightedSumParts[digitNum] = (currentChar - 'A' + 10) * digitWeights[digitNum];
                        
                    } else if (Character.isDigit(currentChar)) {
                        weightedSumParts[digitNum] = (currentChar - '0') * digitWeights[digitNum];
                        
                    } else {
                        System.out.println("Invalid SEDOL: " + sedol);
                        invalid = true;
                        break;
                    }
                }
                
                if (invalid) {
                    continue;
                }
                
                int weightedSum = 0;
                for (int digitNum = 0; digitNum < 6; digitNum++) {
                    weightedSum += weightedSumParts[digitNum];
                }
                
                int checkDigit = (10 - (weightedSum % 10)) % 10;
                
                System.out.println(sedol + checkDigit);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}