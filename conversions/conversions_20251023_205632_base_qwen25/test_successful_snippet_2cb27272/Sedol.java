import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sedol {
    public static void main(String[] args) {
        String sedolFile = "sedol.txt";
        String line;
        int[] digitWeights = {1, 3, 1, 7, 3, 9, 1};
        int[] weightedSumParts = new int[6];
        int weightedSum;
        int checkDigit;

        try (BufferedReader br = new BufferedReader(new FileReader(sedolFile))) {
            while ((line = br.readLine()) != null) {
                line = line.toUpperCase();
                boolean isValid = true;

                for (int digitNum = 0; digitNum < 6; digitNum++) {
                    char ch = line.charAt(digitNum);
                    if (Character.isUpperCase(ch)) {
                        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                            System.out.println("Invalid SEDOL: " + line);
                            isValid = false;
                            break;
                        }
                        weightedSumParts[digitNum] = (ch - 'A' + 10) * digitWeights[digitNum];
                    } else if (Character.isDigit(ch)) {
                        weightedSumParts[digitNum] = Character.getNumericValue(ch) * digitWeights[digitNum];
                    } else {
                        System.out.println("Invalid SEDOL: " + line);
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    weightedSum = 0;
                    for (int digitNum = 0; digitNum < 6; digitNum++) {
                        weightedSum += weightedSumParts[digitNum];
                    }
                    checkDigit = (10 - (weightedSum % 10)) % 10;
                    System.out.println(line + checkDigit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}