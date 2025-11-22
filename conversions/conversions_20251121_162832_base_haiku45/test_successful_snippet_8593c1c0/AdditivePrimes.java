public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private char[] compositeFlag = new char[501];
    private int amount = 0;
    private StringBuilder outLine = new StringBuilder();
    private int outPtr = 1;

    public static void main(String[] args) {
        AdditivePrimes program = new AdditivePrimes();
        program.begin();
    }

    private void begin() {
        initializeCompositeFlag();
        sieve();
        amount = 0;
        
        for (int candidate = 2; candidate <= MAXIMUM; candidate++) {
            testNumber(candidate);
        }
        
        System.out.println(outLine.toString());
        System.out.println();
        System.out.printf("Amount of additive primes found: %4d%n", amount);
    }

    private void initializeCompositeFlag() {
        for (int i = 0; i <= MAXIMUM; i++) {
            compositeFlag[i] = ' ';
        }
    }

    private boolean isPrime(int num) {
        if (num < 0 || num > MAXIMUM) {
            return false;
        }
        return compositeFlag[num] == ' ';
    }

    private void testNumber(int candidate) {
        int digitSum = getDigitSum(candidate);
        
        if (isPrime(candidate) && isPrime(digitSum)) {
            amount++;
            writeNumber(candidate);
        }
    }

    private int getDigitSum(int num) {
        int sum = 0;
        int temp = num;
        
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        
        return sum;
    }

    private void writeNumber(int candidate) {
        String numStr = String.format("%4d", candidate);
        outLine.append(numStr);
        outPtr += numStr.length();
        
        if (outPtr > 40) {
            System.out.println(outLine.toString());
            outLine = new StringBuilder();
            outPtr = 1;
        }
    }

    private void sieve() {
        initializeCompositeFlag();
        int sieveMax = MAXIMUM / 2;
        
        for (int sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (isPrime(sievePrime)) {
                int sieveCompStart = sievePrime * 2;
                for (int sieveComp = sieveCompStart; sieveComp <= MAXIMUM; sieveComp += sievePrime) {
                    compositeFlag[sieveComp] = 'X';
                }
            }
        }
    }
}