```java
import java.util.HashMap;
import java.util.Map;

public class euler014 {
    static long term;
    static long termCount;
    static long startingNumber;
    static long collatzRem;
    static long collatzDiv;
    static long maxTermCount = 0;
    static long startingNumberMaxTerms = 0;
    static Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        for (startingNumber = 1; startingNumber < 1000000; startingNumber++) {
            term = startingNumber;
            termCount = 1;
            
            while (term != 1) {
                collatzDiv = term / 2;
                collatzRem = term % 2;
                
                if (collatzRem == 0) {
                    term = collatzDiv;
                } else {
                    term = term * 3 + 1;
                }
                
                if (memo.containsKey(term)) {
                    termCount += memo.get(term);
                    break;
                }
                
                termCount++;
            }
            
            memo.put(startingNumber, termCount);
            
            if (termCount > maxTermCount) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }
        
        System.out.println("Starting number " + startingNumberMaxTerms + " has " +
                           maxTermCount + " terms in the Collatz chain.");
    }
}