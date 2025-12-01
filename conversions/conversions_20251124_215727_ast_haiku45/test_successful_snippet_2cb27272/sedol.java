import java.io.*;
import java.nio.file.*;
import java.util.*;

public class sedol {
    private static String sedolRecord;
    private static String sedolFileStatus;
    private static int digitNum;
    private static int[] digitWeights = {1, 3, 1, 7, 3, 9, 1};
    private static int[] weightedSumParts = new int[6];
    private static int weightedSum;
    private static int checkDigit;

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("sedol.txt"));
            
            for (String line : lines) {
                sedolRecord = line.toUpperCase();
                
                boolean validSedol = true;
                
                for (digitNum = 0; digitNum < 6; digitNum++) {
                    char currentChar = sedolRecord.charAt(digitNum);
                    
                    if (Character.isLetter(currentChar)) {
                        if (currentChar == 'A' || currentChar == 'E' || 
                            currentChar == 'I' || currentChar == 'O' || 
                            currentChar == 'U') {
                            System.out.println("Invalid SEDOL: " + sedolRecord);
                            validSedol = false;
                            break;
                        }
                        
                        weightedSumParts[digitNum] = 
                            ((int)currentChar - (int)'A' + 10) * digitWeights[digitNum];
                    } else if (Character.isDigit(currentChar)) {
                        weightedSumParts[digitNum] = 
                            Character.getNumericValue(currentChar) * digitWeights[digitNum];
                    } else {
                        System.out.println("Invalid SEDOL: " + sedolRecord);
                        validSedol = false;
                        break;
                    }
                }
                
                if (!validSedol) {
                    continue;
                }
                
                weightedSum = 0;
                for (digitNum = 0; digitNum < 6; digitNum++) {
                    weightedSum += weightedSumParts[digitNum];
                }
                
                checkDigit = (10 - (weightedSum % 10)) % 10;
                
                System.out.println(sedolRecord + checkDigit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}