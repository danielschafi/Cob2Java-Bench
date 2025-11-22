public class TestSubs {
    
    public static int validISBN13(String passedIsbn) {
        int passedSize = passedIsbn.length();
        int wfCount = 0;
        int wfSum = 0;
        
        for (int ix = 0; ix < passedSize; ix++) {
            char wfDigit = passedIsbn.charAt(ix);
            if (Character.isDigit(wfDigit)) {
                wfCount++;
                int digitValue = Character.getNumericValue(wfDigit);
                if (wfCount % 2 == 1) { // weight 1
                    wfSum += digitValue;
                } else { // weight 3
                    wfSum += digitValue * 3;
                }
            }
        }
        
        if (wfSum % 10 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public static void main(String[] args) {
        String[] testIsbns = {
            "978-1734314502",
            "978-1734314509", 
            "978-1788399081",
            "978-1788399083"
        };
        
        for (String isbn : testIsbns) {
            System.out.print(isbn + "   ");
            if (validISBN13(isbn) == -1) {
                System.out.println("(bad)");
            } else {
                System.out.println("(good)");
            }
        }
    }
}