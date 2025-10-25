import java.util.Arrays;

public class Euler014 {
    public static void main(String[] args) {
        long term = 0;
        long termCount = 0;
        long startingNumber = 0;
        long collatzRem = 0;
        long collatzDiv = 0;
        long maxTermCount = 0;
        long startingNumberMaxTerms = 0;
        long memoCur = 0;
        long[] memoTerm = new long[1000001];
        long[] memoTermCount = new long[1000001];
        Arrays.fill(memoTerm, 0);
        Arrays.fill(memoTermCount, 0);

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
                    if (term < 0) {
                        System.out.println("Too big term on " + startingNumber);
                        return;
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
        System.out.println("Starting number " + startingNumberMaxTerms + " has " + maxTermCount + " terms in the Collatz chain.");
    }
}