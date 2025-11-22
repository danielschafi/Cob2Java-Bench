import java.io.*;
import java.util.*;

public class IsbnConv {
    static final String ISBN10_FILE = "isbn10.dat";
    static final String ISBN13_FILE = "isbn13.dat";

    public static void main(String[] args) {
        try (BufferedReader isbn10Reader = new BufferedReader(new FileReader(ISBN10_FILE));
             BufferedWriter isbn13Writer = new BufferedWriter(new FileWriter(ISBN13_FILE))) {

            String isbn10;
            while ((isbn10 = isbn10Reader.readLine()) != null) {
                if (isbn10.length() == 10) {
                    String isbn10Formatted = formatIsbn10(isbn10);
                    System.out.print(isbn10Formatted + " -> ");

                    ValidationResult validationResult = new ValidationResult();
                    checkIsbn10(isbn10, validationResult);

                    if (validationResult.valid) {
                        String isbn13 = makeIsbn13(isbn10);
                        String isbn13Formatted = formatIsbn13(isbn13);
                        System.out.println(isbn13Formatted);
                        isbn13Writer.write(isbn13);
                        isbn13Writer.newLine();
                    } else {
                        System.out.println("not valid");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatIsbn10(String isbn10) {
        StringBuilder sb = new StringBuilder();
        sb.append(isbn10.substring(0, 1)).append("-");
        sb.append(isbn10.substring(1, 4)).append("-");
        sb.append(isbn10.substring(4, 9)).append("-");
        sb.append(isbn10.substring(9));
        return sb.toString();
    }

    private static String formatIsbn13(String isbn13) {
        StringBuilder sb = new StringBuilder();
        sb.append(isbn13.substring(0, 3)).append("-");
        sb.append(isbn13.substring(3, 4)).append("-");
        sb.append(isbn13.substring(4, 7)).append("-");
        sb.append(isbn13.substring(7, 12)).append("-");
        sb.append(isbn13.substring(12));
        return sb.toString();
    }

    private static void checkIsbn10(String isbn10, ValidationResult result) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (i + 1) * Character.getNumericValue(isbn10.charAt(i));
        }
        int remainder = sum % 11;
        int checkDigit = (isbn10.charAt(9) == 'X') ? 10 : Character.getNumericValue(isbn10.charAt(9));
        result.valid = (remainder == checkDigit);
    }

    private static String makeIsbn13(String isbn10) {
        int sum = 38; // 9 + 3 * 7 + 8 = 38
        for (int i = 0; i < 9; i++) {
            if ((i + 1) % 2 == 0) {
                sum += Character.getNumericValue(isbn10.charAt(i));
            } else {
                sum += 3 * Character.getNumericValue(isbn10.charAt(i));
            }
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return "978" + isbn10.substring(0, 9) + checkDigit;
    }

    static class ValidationResult {
        boolean valid = false;
    }
}