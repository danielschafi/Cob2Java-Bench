public class TestSubs {
    
    public static int validISBN13(String passedIsbn) {
        int passedSize = passedIsbn.length();
        int ix = 0;
        char wfDigit;
        int wfCount = 0;
        int wfSum = 0;
        
        for (ix = 0; ix < passedSize; ix++) {
            wfDigit = passedIsbn.charAt(ix);
            if (Character.isDigit(wfDigit)) {
                wfCount++;
                if (wfCount % 2 == 1) {
                    wfSum += Character.getNumericValue(wfDigit);
                } else {
                    wfSum += Character.getNumericValue(wfDigit) * 3;
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
        
        for (int ix = 0; ix < testIsbns.length; ix++) {
            System.out.print(testIsbns[ix] + "   ");
            if (validISBN13(testIsbns[ix]) == -1) {
                System.out.println("(bad)");
            } else {
                System.out.println("(good)");
            }
        }
    }
}