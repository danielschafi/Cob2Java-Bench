public class ADDITIVE_PRIMES {
    private static final int MAX_SIZE = 500;
    private static boolean[] compositeFlag = new boolean[MAX_SIZE + 1];
    private static int maximum = 500;
    private static int amount = 0;
    private static int candidate = 0;
    private static int[] digit = new int[4]; // 1-based indexing
    private static int digitSum = 0;
    private static StringBuilder outLine = new StringBuilder("                                        ");
    private static int outPtr = 1;

    public static void main(String[] args) {
        sieve();
        amount = 0;
        for (candidate = 2; candidate <= maximum; candidate++) {
            testNumber();
        }
        System.out.println(outLine);
        System.out.println();
        System.out.println("Amount of additive primes found: " + amount);
    }

    private static void testNumber() {
        digitSum = digit[1] + digit[2] + digit[3];
        if (isPrime(candidate) && isPrime(digitSum)) {
            amount++;
            writeNumber();
        }
    }

    private static void writeNumber() {
        String numFmt = String.format("%4d", candidate);
        for (int i = 0; i < numFmt.length(); i++) {
            if (outPtr > 40) {
                System.out.println(outLine);
                outLine.setLength(0);
                outLine.append("                                        ");
                outPtr = 1;
            }
            outLine.setCharAt(outPtr - 1, numFmt.charAt(i));
            outPtr++;
        }
    }

    private static void sieve() {
        for (int i = 0; i <= MAX_SIZE; i++) {
            compositeFlag[i] = false;
        }
        int sieveMax = maximum / 2;
        for (int sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (isPrime(sievePrime)) {
                int sieveCompStart = sievePrime * 2;
                for (int sieveComp = sieveCompStart; sieveComp <= maximum; sieveComp += sievePrime) {
                    compositeFlag[sieveComp] = true;
                }
            }
        }
    }

    private static boolean isPrime(int n) {
        return !compositeFlag[n];
    }
}