import java.math.BigInteger;

public class Euler014 {
    public static void main(String[] args) {
        BigInteger term = BigInteger.ZERO;
        BigInteger termCount = BigInteger.ZERO;
        BigInteger startingNumber = BigInteger.ZERO;
        BigInteger collatzRem = BigInteger.ZERO;
        BigInteger collatzDiv = BigInteger.ZERO;
        BigInteger maxTermCount = BigInteger.ZERO;
        BigInteger startingNumberMaxTerms = BigInteger.ZERO;
        BigInteger memoCur = BigInteger.ZERO;
        BigInteger[] memoTerm = new BigInteger[1000001];
        BigInteger[] memoTermCount = new BigInteger[1000001];

        for (int i = 0; i < memoTerm.length; i++) {
            memoTerm[i] = BigInteger.ZERO;
            memoTermCount[i] = BigInteger.ZERO;
        }

        for (startingNumber = BigInteger.ONE; startingNumber.compareTo(BigInteger.valueOf(1000000)) < 0; startingNumber = startingNumber.add(BigInteger.ONE)) {
            term = startingNumber;
            termCount = BigInteger.ONE;

            while (!term.equals(BigInteger.ONE)) {
                collatzRem = term.mod(BigInteger.valueOf(2));
                collatzDiv = term.divide(BigInteger.valueOf(2));

                if (collatzRem.equals(BigInteger.ZERO)) {
                    term = collatzDiv;
                } else {
                    term = term.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
                    if (term.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
                        System.out.println("Too big term on " + startingNumber);
                    }
                }

                if (term.compareTo(memoCur) <= 0) {
                    termCount = termCount.add(memoTermCount[term.intValue()]);
                    break;
                }

                termCount = termCount.add(BigInteger.ONE);
            }

            memoCur = startingNumber;
            memoTerm[memoCur.intValue()] = startingNumber;
            memoTermCount[memoCur.intValue()] = termCount;

            if (termCount.compareTo(maxTermCount) > 0) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }

        System.out.println("Starting number " + startingNumberMaxTerms + " has " + maxTermCount + " terms in the Collatz chain.");
    }
}