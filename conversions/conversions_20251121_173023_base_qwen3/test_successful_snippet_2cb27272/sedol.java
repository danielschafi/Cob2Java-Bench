import java.io.*;
import java.util.*;

public class sedol {
    private static final String FILE_NAME = "sedol.txt";
    private static final int[] DIGIT_WEIGHTS = {1, 3, 1, 7, 3, 9, 1};
    private static final String VOWELS = "AEIOU";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() != 6) {
                    System.out.println("Invalid SEDOL: " + line);
                    continue;
                }

                line = line.toUpperCase();
                int weightedSum = 0;

                for (int i = 0; i < 6; i++) {
                    char c = line.charAt(i);
                    int value;

                    if (Character.isLetter(c)) {
                        if (VOWELS.indexOf(c) >= 0) {
                            System.out.println("Invalid SEDOL: " + line);
                            break;
                        }
                        value = (c - 'A' + 10) * DIGIT_WEIGHTS[i];
                    } else if (Character.isDigit(c)) {
                        value = Character.getNumericValue(c) * DIGIT_WEIGHTS[i];
                    } else {
                        System.out.println("Invalid SEDOL: " + line);
                        break;
                    }

                    weightedSum += value;
                }

                if (weightedSum > 0 && line.length() == 6) {
                    int checkDigit = (10 - (weightedSum % 10)) % 10;
                    System.out.println(line + checkDigit);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}