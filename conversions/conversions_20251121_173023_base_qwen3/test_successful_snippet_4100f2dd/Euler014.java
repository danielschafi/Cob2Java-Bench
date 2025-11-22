import java.util.Arrays;

public class Euler014 {
    private static final int MAX = 1000000;
    private static final double[] memoTerm = new double[MAX];
    private static final double[] memoTermCount = new double[MAX];
    private static double maxTermCount = 0;
    private static double startingNumberMaxTerms = 0;
    private static double memoCur = 0;

    public static void main(String[] args) {
        for (double startingNumber = 1; startingNumber < MAX; startingNumber++) {
            double term = startingNumber;
            double termCount = 1;

            while (term != 1) {
                double collatzDiv = Math.floor(term / 2);
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
                    termCount += memoTermCount[(int) term - 1];
                    break;
                }

                termCount++;
            }

            memoCur = startingNumber;
            memoTerm[(int) memoCur - 1] = startingNumber;
            memoTermCount[(int) memoCur - 1] = termCount;

            if (termCount > maxTermCount) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }

        System.out.println("Starting number " + startingNumberMaxTerms + " has " +
                          maxTermCount + " terms in the Collatz chain.");
    }
}