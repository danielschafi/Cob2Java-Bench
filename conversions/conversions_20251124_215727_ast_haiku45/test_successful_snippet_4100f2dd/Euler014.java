```java
import java.util.HashMap;
import java.util.Map;

public class Euler014 {
    private long term;
    private long termCount;
    private long startingNumber;
    private long collatzRem;
    private long collatzDiv;
    private long maxTermCount = 0;
    private long startingNumberMaxTerms = 0;
    private Map<Long, Long> memoTermCount = new HashMap<>();

    public static void main(String[] args) {
        Euler014 program = new Euler014();
        program.mainProcedure();
    }

    private void mainProcedure() {
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

                if (term <= startingNumber && memoTermCount.containsKey(term)) {
                    termCount += memoTermCount.get(term);
                    break;
                }

                termCount++;
            }

            memoTermCount.put(startingNumber, termCount);

            if (termCount > maxTermCount) {
                maxTermCount = termCount;
                startingNumberMaxTerms = startingNumber;
            }
        }

        System.out.println("Starting number " + startingNumberMaxTerms + " has " +
                maxTermCount + " terms in the Collatz chain.");
    }
}