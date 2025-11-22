import java.util.Arrays;

public class TestSubs {

    public static void main(String[] args) {
        String[] testISBNs = {
            "978-1734314502",
            "978-1734314509",
            "978-1788399081",
            "978-1788399083"
        };

        for (String isbn : testISBNs) {
            System.out.print(isbn + "   ");
            if (validISBN13(isbn) == -1) {
                System.out.println("(bad)");
            } else {
                System.out.println("(good)");
            }
        }
    }

    public static int validISBN13(String passedISBN) {
        int passedSize = passedISBN.length();
        int ix;
        int wfDigit;
        int wfCount = 0;
        int wfSum = 0;

        for (ix = 0; ix < passedSize; ix++) {
            wfDigit = Character.getNumericValue(passedISBN.charAt(ix));
            if (Character.isDigit(passedISBN.charAt(ix))) {
                wfCount++;
                if (wfCount % 2 != 0) {
                    wfSum += wfDigit;
                } else {
                    wfSum += wfDigit * 3;
                }
            }
        }

        return (wfSum % 10 == 0) ? 0 : -1;
    }
}