import java.math.BigInteger;

public class euler014 {
    
    private static long term;
    private static long termCount;
    private static long startingNumber;
    private static long collatzRem;
    private static long collatzDiv;
    private static long maxTermCount = 0;
    private static long startingNumberMaxTerms = 0;
    private static long memoCur = 0;
    private static long[] memoTerm = new long[1000000];
    private static long[] memoTermCount = new long[1000000];
    
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
                    try {
                        term = term * 3 + 1;
                    } catch (ArithmeticException e) {
                        System.out.println("Too big term on " + startingNumber);
                    }
                }
                
                if (term <= memoCur) {
                    termCount += memoTermCount[(int)term - 1];
                    break;
                }
                
                termCount++;
            }
            
            memoCur = startingNumber;
            memoTerm[(int)memoCur - 1] = startingNumber;
            memoTermCount[(int)memoCur - 1] = termCount;
            
            if (termCount > maxTermCount) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }
        
        System.out.println("Starting number " + startingNumberMaxTerms + " has " + 
                          maxTermCount + " terms in the Collatz chain.");
    }
}