import java.util.Scanner;

public class testSubs {
    
    private static class TestISBNs {
        private String[] testIsbn = {
            "978-1734314502",
            "978-1734314509",
            "978-1788399081",
            "978-1788399083"
        };
        
        public String getTestIsbn(int index) {
            return testIsbn[index];
        }
        
        public int getLength() {
            return testIsbn.length;
        }
    }
    
    public static void main(String[] args) {
        TestISBNs testIsbns = new TestISBNs();
        
        for (int ix = 0; ix < testIsbns.getLength(); ix++) {
            String isbn = testIsbns.getTestIsbn(ix);
            System.out.print(isbn + "   ");
            
            if (validISBN13(isbn) == -1) {
                System.out.println("(bad)");
            } else {
                System.out.println("(good)");
            }
        }
    }
    
    private static int validISBN13(String passedIsbn) {
        int passedSize = passedIsbn.length();
        int wfCount = 0;
        int wfSum = 0;
        
        for (int ix = 0; ix < passedSize; ix++) {
            char wfDigit = passedIsbn.charAt(ix);
            
            if (Character.isDigit(wfDigit)) {
                wfCount++;
                int digitValue = Character.getNumericValue(wfDigit);
                
                if (isWeight1(wfCount)) {
                    wfSum += digitValue;
                } else {
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
    
    private static boolean isWeight1(int count) {
        return count == 1 || count == 3 || count == 5 || 
               count == 7 || count == 9 || count == 11 || count == 13;
    }
}