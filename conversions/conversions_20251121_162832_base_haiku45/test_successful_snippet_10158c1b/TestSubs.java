public class TestSubs {
    private static final String[] TEST_ISBNS = {
        "978-1734314502",
        "978-1734314509",
        "978-1788399081",
        "978-1788399083"
    };

    public static void main(String[] args) {
        for (int ix = 0; ix < 4; ix++) {
            System.out.print(TEST_ISBNS[ix] + "   ");
            if (validISBN13(TEST_ISBNS[ix]) == -1) {
                System.out.println("(bad)");
            } else {
                System.out.println("(good)");
            }
        }
    }

    public static int validISBN13(String passedISBN) {
        int passedSize = passedISBN.length();
        int wfCount = 0;
        long wfSum = 0;

        for (int ix = 0; ix < passedSize; ix++) {
            char wfDigit = passedISBN.charAt(ix);
            if (Character.isDigit(wfDigit)) {
                wfCount++;
                int digitValue = Character.getNumericValue(wfDigit);
                
                if (wfCount == 1 || wfCount == 3 || wfCount == 5 || 
                    wfCount == 7 || wfCount == 9 || wfCount == 11 || wfCount == 13) {
                    wfSum += digitValue;
                } else {
                    wfSum += (digitValue * 3);
                }
            }
        }

        if (wfSum % 10 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}