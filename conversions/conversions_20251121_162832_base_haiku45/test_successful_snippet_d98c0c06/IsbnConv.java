import java.io.*;
import java.util.*;

public class IsbnConv {
    private static final String ISBN10_FILE = "isbn10.dat";
    private static final String ISBN13_FILE = "isbn13.dat";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ISBN10_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ISBN13_FILE))) {

            String isbn10;
            while ((isbn10 = reader.readLine()) != null) {
                if (isbn10.isEmpty()) continue;

                String isbn10Prn = formatIsbn10(isbn10);
                System.out.print(isbn10Prn + " -> ");

                int validationResult = checkIsbn10(isbn10);
                if (validationResult != 0) {
                    System.out.println("not valid");
                } else {
                    String isbn13 = makeIsbn13(isbn10);
                    String isbn13Prn = formatIsbn13(isbn13);
                    System.out.println(isbn13Prn);
                    writer.write(isbn13);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatIsbn10(String isbn10) {
        if (isbn10.length() != 10) return isbn10;
        return isbn10.charAt(0) + "-" + isbn10.substring(1, 4) + "-" + isbn10.substring(4, 9) + "-" + isbn10.charAt(9);
    }

    private static String formatIsbn13(String isbn13) {
        if (isbn13.length() != 13) return isbn13;
        return isbn13.substring(0, 3) + "-" + isbn13.charAt(3) + "-" + isbn13.substring(4, 7) + "-" + isbn13.substring(7, 12) + "-" + isbn13.charAt(12);
    }

    private static int checkIsbn10(String isbn) {
        if (isbn.length() != 10) return 1;

        int result = 0;
        for (int idx = 1; idx <= 9; idx++) {
            char digit = isbn.charAt(idx - 1);
            result += idx * Character.getNumericValue(digit);
        }

        result = result % 11;

        int checkNum;
        char isbnChk = isbn.charAt(9);
        if (isbnChk == 'X') {
            checkNum = 10;
        } else {
            checkNum = Character.getNumericValue(isbnChk);
        }

        if (result == checkNum) {
            return 0;
        } else {
            return 1;
        }
    }

    private static String makeIsbn13(String isbn10) {
        int result = 38;

        for (int idx = 1; idx <= 9; idx++) {
            int digit = Character.getNumericValue(isbn10.charAt(idx - 1));
            if (idx % 2 == 0) {
                result += digit;
            } else {
                result += 3 * digit;
            }
        }

        int checkNum = (10 - (result % 10)) % 10;

        String isbn10Num = isbn10.substring(0, 9);
        return "978" + isbn10Num + checkNum;
    }
}