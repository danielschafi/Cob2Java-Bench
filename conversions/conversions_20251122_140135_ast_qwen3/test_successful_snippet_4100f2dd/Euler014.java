public class Euler014 {
    private static final int TABLE_SIZE = 1000000;
    
    public static void main(String[] args) {
        double[] memoTerm = new double[TABLE_SIZE];
        double[] memoTermCount = new double[TABLE_SIZE];
        double maxTermCount = 0;
        double startingNumberMaxTerms = 0;
        double memoCur = 0;
        
        for (double startingNumber = 1; startingNumber < 1000000; startingNumber++) {
            double term = startingNumber;
            double termCount = 1;
            
            while (term != 1) {
                double collatzDiv = term / 2;
                double collatzRem = term % 2;
                
                if (collatzRem == 0) {
                    term = collatzDiv;
                } else {
                    term = term * 3 + 1;
                    if (term > Double.MAX_VALUE) {
                        System.out.println("Too big term on " + startingNumber);
                    }
                }
                
                if (term <= memoCur) {
                    termCount += memoTermCount[(int) term];
                    break;
                }
                
                termCount++;
            }
            
            memoCur = startingNumber;
            memoTerm[(int) memoCur] = startingNumber;
            memoTermCount[(int) memoCur] = termCount;
            
            if (termCount > maxTermCount) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }
        
        System.out.println("Starting number " + startingNumberMaxTerms + " has " +
                          maxTermCount + " terms in the Collatz chain.");
    }
}