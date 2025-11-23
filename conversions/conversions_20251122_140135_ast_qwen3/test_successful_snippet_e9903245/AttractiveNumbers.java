public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private static final char[] MARKER = new char[MAXIMUM];
    private static int SIEVE_MAX;
    private static int COMPOSITE;
    private static int CANDIDATE;
    private static int FACTOR_NUM;
    private static int FACTORS;
    private static int FACTOR;
    private static double QUOTIENT;
    private static int FILLER;
    private static int DECIMAL;
    private static String OUT_NUM;
    private static String OUT_LINE = "                                                                       ";
    private static int COL_PTR = 1;

    public static void main(String[] args) {
        sieve();
        for (CANDIDATE = 2; CANDIDATE <= MAXIMUM; CANDIDATE++) {
            factorize();
            if (isPrime(FACTORS)) {
                addToOutput();
            }
        }
        writeLine();
    }

    private static void sieve() {
        MARKER[0] = 'X';
        SIEVE_MAX = MAXIMUM / 2;
        for (CANDIDATE = 2; CANDIDATE <= SIEVE_MAX; CANDIDATE++) {
            setComposites();
            setCompositesLoop();
        }
    }

    private static void setComposites() {
        COMPOSITE = CANDIDATE * 2;
    }

    private static void setCompositesLoop() {
        while (COMPOSITE <= MAXIMUM) {
            MARKER[COMPOSITE - 1] = 'X';
            COMPOSITE += CANDIDATE;
        }
    }

    private static void factorize() {
        FACTORS = 0;
        for (FACTOR = 2; FACTOR <= MAXIMUM; FACTOR++) {
            dividePrime();
        }
    }

    private static void dividePrime() {
        if (isPrime(FACTOR)) {
            QUOTIENT = (double) FACTOR_NUM / FACTOR;
            DECIMAL = (int) ((QUOTIENT - (int) QUOTIENT) * 1000);
            if (DECIMAL == 0) {
                FACTORS++;
                FACTOR_NUM = (int) QUOTIENT;
                dividePrime();
            }
        }
    }

    private static boolean isPrime(int index) {
        return MARKER[index - 1] == ' ';
    }

    private static void addToOutput() {
        OUT_NUM = String.format("%4d", CANDIDATE);
        OUT_LINE = OUT_LINE.substring(0, COL_PTR - 1) + OUT_NUM + OUT_LINE.substring(COL_PTR - 1 + OUT_NUM.length());
        COL_PTR += OUT_NUM.length();
        if (COL_PTR > 72) {
            writeLine();
        }
    }

    private static void writeLine() {
        System.out.println(OUT_LINE);
        OUT_LINE = "                                                                       ";
        COL_PTR = 1;
    }
}