public class NinetyNine {
    private static final String[] AFTER_TEN_WORDS = {
        "", "Twenty", "Thirty", "Forty", "Fifty", 
        "Sixty", "Seventy", "Eighty", "Ninety", ""
    };
    
    private static final String[] DIGIT_WORDS = {
        "One", "Two", "Three", "Four", "Five", 
        "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen", ""
    };
    
    public static void main(String[] args) {
        for (int counter = 99; counter > 0; counter--) {
            String stringified = showNumber(counter);
            System.out.println(stringified + " of beer on the wall");
            System.out.println(stringified + " of beer");
            
            if (counter == 1) {
                System.out.println("Take it down and pass it round");
            } else {
                System.out.println("Take one down and pass it round");
            }
            
            String nextStringified = showNumber(counter - 1);
            System.out.println(nextStringified + " of beer on the wall");
            System.out.println();
        }
        
        System.out.println("No more bottles of beer on the wall");
        System.out.println("No more bottles of beer");
        System.out.println("Go to the store and buy some more");
        System.out.println("Ninety-Nine bottles of beer on the wall");
    }
    
    private static String showNumber(int counter) {
        String result;
        
        if (counter == 0) {
            result = "No more";
        } else if (counter < 20) {
            result = DIGIT_WORDS[counter - 1];
        } else {
            int tens = counter / 10;
            int digits = counter % 10;
            
            if (digits == 0) {
                result = AFTER_TEN_WORDS[tens - 1];
            } else {
                result = AFTER_TEN_WORDS[tens - 1] + "-" + DIGIT_WORDS[digits - 1];
            }
        }
        
        if (counter == 1) {
            result += " bottle";
        } else {
            result += " bottles";
        }
        
        return result;
    }
}