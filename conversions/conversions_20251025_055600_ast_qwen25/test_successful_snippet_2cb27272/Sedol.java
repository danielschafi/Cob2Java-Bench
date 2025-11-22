import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sedol {
    public static void main(String[] args) {
        String sedolFile = "sedol.txt";
        String sedolFileStatus = "00";
        int digitNum;
        int[] digitWeights = {1, 3, 1, 7, 3, 9, 1};
        int[] weightedSumParts = new int[6];
        int weightedSum;
        int checkDigit;

        try (BufferedReader br = new BufferedReader(new FileReader(sedolFile))) {
            String sedol;
            while ((sedol = br.readLine()) != null) {
                sedol = sedol.toUpperCase();
                sedolFileStatus = "00";

                for (digitNum = 0; digitNum < 6; digitNum++) {
                    char currentChar = sedol.charAt(digitNum);
                    if (Character.isUpperCase(currentChar)) {
                        if (currentChar == 'A' || currentChar == 'E' || currentChar == 'I' || currentChar == 'O' || currentChar == 'U') {
                            System.out.println("Invalid SEDOL: " + sedol);
                            sedolFileStatus = "99";
                            break;
                        }
                        weightedSumParts[digitNum] = (currentChar - 'A' + 10) * digitWeights[digitNum];
                    } else if (Character.isDigit(currentChar)) {
                        weightedSumParts[digitNum] = Character.getNumericValue(currentChar) * digitWeights[digitNum];
                    } else {
                        System.out.println("Invalid SEDOL: " + sedol);
                        sedolFileStatus = "99";
                        break;
                    }
                }

                if (!sedolFileStatus.equals("00")) {
                    continue;
                }

                weightedSum = 0;
                for (digitNum = 0; digitNum < 6; digitNum++) {
                    weightedSum += weightedSumParts[digitNum];
                }

                checkDigit = (10 - (weightedSum % 10)) % 10;
                System.out.println(sedol + checkDigit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}