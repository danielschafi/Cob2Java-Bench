public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private static char[] marker = new char[MAXIMUM + 1];
    private static int sieveMax;
    private static int composite;
    private static int candidate;
    private static int factorNum;
    private static int factors;
    private static int factor;
    private static double quotient;
    private static int decimal;
    private static String outLine = "                                                                               ";
    private static int colPtr = 1;

    public static void main(String[] args) {
        sieve();
        for (candidate = 2; candidate <= MAXIMUM; candidate++) {
            factorNum = candidate;
            factorize();
            if (isPrime(factors)) {
                addToOutput();
            }
        }
        writeLine();
    }

    private static void sieve() {
        marker[1] = 'X';
        sieveMax = MAXIMUM / 2;
        for (candidate = 2; candidate <= sieveMax; candidate++) {
            composite = candidate * 2;
            while (composite <= MAXIMUM) {
                marker[composite] = 'X';
                composite += candidate;
            }
        }
    }

    private static void factorize() {
        factors = 0;
        for (factor = 2; factor <= MAXIMUM; factor++) {
            if (isPrime(factor)) {
                quotient = (double) factorNum / factor;
                decimal = (int) ((quotient - (int) quotient) * 1000);
                if (decimal == 0) {
                    factors++;
                    factorNum = (int) quotient;
                    factor--; // Decrement to compensate for loop increment
                }
            }
        }
    }

    private static boolean isPrime(int value) {
        return marker[value] == '\0';
    }

    private static void addToOutput() {
        String outNum = String.format("%4d", candidate);
        outLine = outLine.substring(0, colPtr - 1) + outNum + outLine.substring(colPtr + 3);
        colPtr += 4;
        if (colPtr >= 73) {
            writeLine();
        }
    }

    private static void writeLine() {
        System.out.println(outLine);
        outLine = "                                                                               ";
        colPtr = 1;
    }
}