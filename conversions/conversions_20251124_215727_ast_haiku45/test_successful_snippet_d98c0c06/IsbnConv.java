import java.io.*;
import java.util.*;

public class IsbnConv {
    private static BufferedReader isbn10Reader;
    private static BufferedWriter isbn13Writer;
    private static boolean endOfIsbn10File = false;
    private static String currentIsbn10;

    public static void main(String[] args) {
        try {
            isbn10Reader = new BufferedReader(new FileReader("isbn10.dat"));
            isbn13Writer = new BufferedWriter(new FileWriter("isbn13.dat"));

            readRecord();
            while (!endOfIsbn10File) {
                String isbn10Prn = formatIsbn10(currentIsbn10);
                System.out.print(isbn10Prn + " -> ");

                int[] validationResult = new int[1];
                checkIsbn10(currentIsbn10, validationResult);

                if (validationResult[0] == 1) {
                    System.out.println("not valid");
                } else {
                    String isbn13 = makeIsbn13(currentIsbn10);
                    String isbn13Prn = formatIsbn13(isbn13);
                    System.out.println(isbn13Prn);
                    isbn13Writer.write(isbn13);
                    isbn13Writer.newLine();
                }
                readRecord();
            }

            isbn10Reader.close();
            isbn13Writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRecord() {
        try {
            currentIsbn10 = isbn10Reader.readLine();
            if (currentIsbn10 == null) {
                endOfIsbn10File = true;
            }
        } catch (IOException e) {
            endOfIsbn10File = true;
        }
    }

    private static String formatIsbn10(String isbn10) {
        if (isbn10 == null || isbn10.length() < 10) return isbn10;
        return isbn10.charAt(0) + "/" + isbn10.substring(1, 4) + "/" + isbn10.substring(4, 9) + "/" + isbn10.charAt(9);
    }

    private static String formatIsbn13(String isbn13) {
        if (isbn13 == null || isbn13.length() < 13) return isbn13;
        return isbn13.substring(0, 3) + "/" + isbn13.charAt(3) + "/" + isbn13.substring(4, 7) + "/" + isbn13.substring(7, 12) + "/" + isbn13.charAt(12);
    }

    private static void checkIsbn10(String isbn, int[] chkResult) {
        int result = 0;
        for (int idx = 0; idx < 9; idx++) {
            int digit = Character.getNumericValue(isbn.charAt(idx));
            result += (idx + 1) * digit;
        }
        result = result % 11;

        int checkNum;
        if (isbn.charAt(9) == 'X') {
            checkNum = 10;
        } else {
            checkNum = Character.getNumericValue(isbn.charAt(9));
        }

        if (result == checkNum) {
            chkResult[0] = 0;
        } else {
            chkResult[0] = 1;
        }
    }

    private static String makeIsbn13(String isbn10) {
        int result = 38;
        for (int idx = 0; idx < 9; idx++) {
            int digit = Character.getNumericValue(isbn10.charAt(idx));
            if ((idx + 1) % 2 == 0) {
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