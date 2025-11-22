import java.io.*;

public class IsbnConv {
    public static void main(String[] args) {
        String isbn10File = "isbn10.dat";
        String isbn13File = "isbn13.dat";
        String isbn10;
        String isbn13;
        String isbn10Prn;
        String isbn13Prn;
        int validationResult;

        try (BufferedReader reader = new BufferedReader(new FileReader(isbn10File));
             BufferedWriter writer = new BufferedWriter(new FileWriter(isbn13File))) {

            while ((isbn10 = reader.readLine()) != null) {
                isbn10Prn = isbn10.substring(0, 1) + "-" + isbn10.substring(1, 4) + "-" + isbn10.substring(4, 9) + "-" + isbn10.substring(9);
                System.out.print(isbn10Prn + " -> ");

                validationResult = checkIsbn10(isbn10);
                if (validationResult == 1) {
                    System.out.println("not valid");
                } else {
                    isbn13 = makeIsbn13(isbn10);
                    isbn13Prn = isbn13.substring(0, 3) + "-" + isbn13.substring(3, 4) + "-" + isbn13.substring(4, 7) + "-" + isbn13.substring(7, 12) + "-" + isbn13.substring(12);
                    System.out.println(isbn13Prn);
                    writer.write(isbn13);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int checkIsbn10(String isbn) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result += (i + 1) * Character.getNumericValue(isbn.charAt(i));
        }
        result %= 11;
        int checkNum = (isbn.charAt(9) == 'X') ? 10 : Character.getNumericValue(isbn.charAt(9));
        return (result == checkNum) ? 0 : 1;
    }

    public static String makeIsbn13(String isbn10) {
        int result = 38;
        for (int i = 0; i < 9; i++) {
            if ((i + 1) % 2 == 0) {
                result += Character.getNumericValue(isbn10.charAt(i));
            } else {
                result += 3 * Character.getNumericValue(isbn10.charAt(i));
            }
        }
        int checkNum = (10 - (result % 10)) % 10;
        return "978" + isbn10 + checkNum;
    }
}