import java.util.Arrays;

public class AdditivePrimes {
    private static final int MAXIMUM = 500;
    private static int amount;
    private static int candidate;
    private static int[] digit = new int[3];
    private static int digitSum;
    private static char[] primeData = new char[500];
    private static int sievePrime;
    private static int sieveCompStart;
    private static int sieveComp;
    private static int sieveMax;
    private static char[] outLine = new char[40];
    private static int outPtr;

    public static void main(String[] args) {
        Arrays.fill(primeData, ' ');
        sieveMax = MAXIMUM / 2;
        for (sievePrime = 2; sievePrime <= sieveMax; sievePrime++) {
            if (primeData[sievePrime] == ' ') {
                sieveCompStart = sievePrime * 2;
                for (sieveComp = sieveCompStart; sieveComp <= MAXIMUM; sieveComp += sievePrime) {
                    primeData[sieveComp] = 'X';
                }
            }
        }
        amount = 0;
        for (candidate = 2; candidate <= MAXIMUM; candidate++) {
            digit[0] = candidate / 100;
            digit[1] = (candidate / 10) % 10;
            digit[2] = candidate % 10;
            digitSum = digit[0] + digit[1] + digit[2];
            if (primeData[candidate] == ' ' && primeData[digitSum] == ' ') {
                amount++;
                writeNumber();
            }
        }
        System.out.println(new String(outLine));
        System.out.println();
        System.out.println("Amount of additive primes found: " + String.format("%04d", amount));
    }

    private static void writeNumber() {
        String numFmt = String.format("%03d", candidate);
        for (char c : numFmt.toCharArray()) {
            outLine[outPtr++] = c;
            if (outPtr > 40) {
                System.out.println(new String(outLine));
                Arrays.fill(outLine, ' ');
                outPtr = 0;
            }
        }
    }
}