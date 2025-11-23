public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private static final int[] compositeFlag = new int[MAXIMUM + 1];
    private static int amount = 0;
    private static StringBuilder outLine = new StringBuilder();
    private static int outPtr = 1;

    public static void main(String[] args) {
        sieve();
        amount = 0;
        
        for (int candidate = 2; candidate <= MAXIMUM; candidate++) {
            int digitSum = 0;
            int temp = candidate;
            while (temp > 0) {
                digitSum += temp % 10;
                temp /= 10;
            }
            
            if (isPrime(candidate) && isPrime(digitSum)) {
                amount++;
                writeNumber(candidate);
            }
        }
        
        System.out.println(outLine);
        System.out.println();
        System.out.println("Amount of additive primes found: " + amount);
    }
    
    private static void sieve() {
        for (int i = 0; i <= MAXIMUM; i++) {
            compositeFlag[i] = 0;
        }
        
        int sieveMax = MAXIMUM / 2;
        for (int sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (isPrime(sievePrime)) {
                int sieveCompStart = sievePrime * 2;
                for (int sieveComp = sieveCompStart; sieveComp <= MAXIMUM; sieveComp += sievePrime) {
                    compositeFlag[sieveComp] = 1;
                }
            }
        }
    }
    
    private static boolean isPrime(int n) {
        return compositeFlag[n] == 0;
    }
    
    private static void writeNumber(int candidate) {
        String numFmt = String.format("%4d", candidate).trim();
        
        if (outPtr + numFmt.length() > 40) {
            System.out.println(outLine);
            outLine.setLength(0);
            outPtr = 1;
        }
        
        outLine.append(numFmt);
        outPtr += numFmt.length();
    }
}