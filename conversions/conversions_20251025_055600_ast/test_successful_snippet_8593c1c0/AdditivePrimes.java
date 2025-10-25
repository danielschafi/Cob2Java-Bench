import java.util.Arrays;

public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private static final int[] COMPOSITE_FLAG = new int[500];
    private static final int[] DIGIT = new int[3];
    private static final char[] OUT_LINE = new char[40];
    private static int AMOUNT = 0;
    private static int CANDIDATE = 0;
    private static int DIGITSUM = 0;
    private static int SIEVE_PRIME = 0;
    private static int SIEVE_COMP_START = 0;
    private static int SIEVE_COMP = 0;
    private static int SIEVE_MAX = 0;
    private static int OUT_PTR = 0;

    public static void main(String[] args) {
        Arrays.fill(COMPOSITE_FLAG, 0);
        Arrays.fill(OUT_LINE, ' ');

        sieve();
        AMOUNT = 0;
        for (CANDIDATE = 2; CANDIDATE <= MAXIMUM; CANDIDATE++) {
            testNumber();
        }
        System.out.println(new String(OUT_LINE));
        System.out.println();
        System.out.println("Amount of additive primes found: " + AMOUNT);
    }

    private static void testNumber() {
        DIGIT[0] = CANDIDATE / 100;
        DIGIT[1] = (CANDIDATE / 10) % 10;
        DIGIT[2] = CANDIDATE % 10;
        DIGITSUM = DIGIT[0] + DIGIT[1] + DIGIT[2];
        if (isPrime(CANDIDATE) && isPrime(DIGITSUM)) {
            AMOUNT++;
            writeNumber();
        }
    }

    private static void writeNumber() {
        String numFmt = String.format("%03d", CANDIDATE);
        numFmt.getChars(0, numFmt.length(), OUT_LINE, OUT_PTR);
        OUT_PTR += numFmt.length();
        if (OUT_PTR > 40) {
            System.out.println(new String(OUT_LINE));
            Arrays.fill(OUT_LINE, ' ');
            OUT_PTR = 0;
        }
    }

    private static void sieve() {
        Arrays.fill(COMPOSITE_FLAG, 0);
        SIEVE_MAX = MAXIMUM / 2;
        for (SIEVE_PRIME = 2; SIEVE_PRIME <= SIEVE_MAX; SIEVE_PRIME++) {
            if (isPrime(SIEVE_PRIME)) {
                SIEVE_COMP_START = SIEVE_PRIME * 2;
                for (SIEVE_COMP = SIEVE_COMP_START; SIEVE_COMP <= MAXIMUM; SIEVE_COMP += SIEVE_PRIME) {
                    COMPOSITE_FLAG[SIEVE_COMP] = 1;
                }
            }
        }
    }

    private static boolean isPrime(int number) {
        return COMPOSITE_FLAG[number] == 0;
    }
}