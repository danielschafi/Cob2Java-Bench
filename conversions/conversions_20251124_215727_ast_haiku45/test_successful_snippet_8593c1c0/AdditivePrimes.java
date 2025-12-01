```java
public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private char[] compositeFlag = new char[501];
    private int amount = 0;
    private int candidate = 0;
    private int digitSum = 0;
    private int sievePrime = 0;
    private int sieveCompStart = 0;
    private int sieveComp = 0;
    private int sieveMax = 0;
    private String outLine = "";
    private int outPtr = 1;

    public static void main(String[] args) {
        AdditivePrimes program = new AdditivePrimes();
        program.run();
    }

    private void run() {
        sieve();
        amount = 0;
        for (candidate = 2; candidate <= MAXIMUM; candidate++) {
            testNumber();
        }
        System.out.println(outLine);
        System.out.println();
        String formatted = String.format("%4d", amount).replaceAll("^\\s+", "");
        System.out.println("Amount of additive primes found: " + formatted);
    }

    private void testNumber() {
        String candStr = String.format("%03d", candidate);
        int digit1 = Character.getNumericValue(candStr.charAt(0));
        int digit2 = Character.getNumericValue(candStr.charAt(1));
        int digit3 = Character.getNumericValue(candStr.charAt(2));
        digitSum = digit1 + digit2 + digit3;

        if (isPrime(candidate) && isPrime(digitSum)) {
            amount++;
            writeNumber();
        }
    }

    private void writeNumber() {
        String formatted = String.format("%4d", candidate).replaceAll("^\\s+", "");
        outLine = outLine + formatted;
        outPtr += formatted.length();

        if (outPtr > 40) {
            System.out.println(outLine);
            outLine = "";
            outPtr = 1;
        }
    }

    private void sieve() {
        for (int i = 0; i <= MAXIMUM; i++) {
            compositeFlag[i] = ' ';
        }
        sieveMax = MAXIMUM / 2;

        for (sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (isPrime(sievePrime)) {
                sieveCompStart = sievePrime * 2;
                for (sieveComp = sieveCompStart; sieveComp <= MAXIMUM; sieveComp += sievePrime) {
                    compositeFlag[sieveComp] = 'X';
                }
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 0 || num > MAXIMUM) {
            return false;
        }
        return compositeFlag[num] == ' ';
    }
}