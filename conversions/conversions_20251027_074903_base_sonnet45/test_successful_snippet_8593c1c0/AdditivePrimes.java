public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private static boolean[] isPrime = new boolean[MAXIMUM + 1];
    private static int amount = 0;
    private static StringBuilder outLine = new StringBuilder();
    private static int outPtr = 0;

    public static void main(String[] args) {
        sieve();
        
        for (int candidate = 2; candidate <= MAXIMUM; candidate++) {
            testNumber(candidate);
        }
        
        if (outLine.length() > 0) {
            System.out.println(outLine.toString());
        }
        System.out.println();
        System.out.println("Amount of additive primes found: " + String.format("%4d", amount));
    }

    private static void testNumber(int candidate) {
        int digitSum = calculateDigitSum(candidate);
        
        if (isPrime[candidate] && digitSum <= MAXIMUM && isPrime[digitSum]) {
            amount++;
            writeNumber(candidate);
        }
    }

    private static int calculateDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static void writeNumber(int candidate) {
        String numFmt = String.format("%4d", candidate);
        
        if (outPtr + numFmt.length() > 40) {
            System.out.println(outLine.toString());
            outLine = new StringBuilder();
            outPtr = 0;
        }
        
        outLine.append(numFmt);
        outPtr += numFmt.length();
    }

    private static void sieve() {
        for (int i = 0; i <= MAXIMUM; i++) {
            isPrime[i] = true;
        }
        
        isPrime[0] = false;
        isPrime[1] = false;
        
        int sieveMax = MAXIMUM / 2;
        
        for (int sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (isPrime[sievePrime]) {
                int sieveCompStart = sievePrime * 2;
                for (int sieveComp = sieveCompStart; sieveComp <= MAXIMUM; sieveComp += sievePrime) {
                    isPrime[sieveComp] = false;
                }
            }
        }
    }
}