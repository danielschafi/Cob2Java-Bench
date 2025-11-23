import java.io.*;
import java.util.*;

public class Sedol {
    private static final String FILE_NAME = "sedol.txt";
    private static final int[] DIGIT_WEIGHTS = {1, 3, 1, 7, 3, 9, 1};
    private static final int[] WEIGHTED_SUM_PARTS = new int[6];
    private static int weightedSum;
    private static int checkDigit;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processLine(String sedol) {
        if (sedol == null || sedol.isEmpty()) return;

        // Convert to uppercase
        sedol = sedol.toUpperCase();

        // Validate length
        if (sedol.length() != 6) {
            System.out.println("Invalid SEDOL: " + sedol);
            return;
        }

        // Process each character
        for (int i = 0; i < 6; i++) {
            char c = sedol.charAt(i);
            if (Character.isLetter(c)) {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    System.out.println("Invalid SEDOL: " + sedol);
                    return;
                }
                int digitValue = (c - 'A') + 10;
                WEIGHTED_SUM_PARTS[i] = digitValue * DIGIT_WEIGHTS[i];
            } else if (Character.isDigit(c)) {
                int digitValue = Character.getNumericValue(c);
                WEIGHTED_SUM_PARTS[i] = digitValue * DIGIT_WEIGHTS[i];
            } else {
                System.out.println("Invalid SEDOL: " + sedol);
                return;
            }
        }

        // Calculate weighted sum
        weightedSum = 0;
        for (int i = 0; i < 6; i++) {
            weightedSum += WEIGHTED_SUM_PARTS[i];
        }

        // Calculate check digit
        checkDigit = (10 - (weightedSum % 10)) % 10;

        // Display result
        System.out.println(sedol + checkDigit);
    }
}