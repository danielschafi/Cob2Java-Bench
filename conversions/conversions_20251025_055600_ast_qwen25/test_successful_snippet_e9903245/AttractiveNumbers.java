import java.text.DecimalFormat;

public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private static final char[] MARKER = new char[120];
    private static int SIEVE_MAX;
    private static int COMPOSITE;
    private static int CANDIDATE;

    private static int FACTOR_NUM;
    private static int FACTORS;
    private static int FACTOR;
    private static double QUOTIENT;
    private static int DECIMAL;

    private static int OUT_NUM;
    private static char[] OUT_LINE = new char[72];
    private static int COL_PTR;

    public static void main(String[] args) {
        sieve();
        for (CANDIDATE = 2; CANDIDATE <= MAXIMUM; CANDIDATE++) {
            checkAttractive();
        }
        writeLine();
    }

    private static void checkAttractive() {
        FACTOR_NUM = CANDIDATE;
        factorize();
        if (isPrime(FACTORS)) {
            addToOutput();
        }
    }

    private static void addToOutput() {
        OUT_NUM = CANDIDATE;
        DecimalFormat df = new DecimalFormat("000");
        String numStr = df.format(OUT_NUM);
        for (int i = 0; i < numStr.length(); i++) {
            OUT_LINE[COL_PTR++] = numStr.charAt(i);
        }
        if (COL_PTR == 73) {
            writeLine();
        }
    }

    private static void writeLine() {
        System.out.println(new String(OUT_LINE).trim());
        for (int i = 0; i < OUT_LINE.length; i++) {
            OUT_LINE[i] = ' ';
        }
        COL_PTR = 1;
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
            DECIMAL = (int) (QUOTIENT % 1 * 1000);
            if (DECIMAL == 0) {
                FACTORS++;
                FACTOR_NUM = (int) QUOTIENT;
                dividePrime();
            }
        }
    }

    private static void sieve() {
        MARKER[0] = 'X';
        SIEVE_MAX = MAXIMUM / 2;
        for (CANDIDATE = 2; CANDIDATE <= SIEVE_MAX; CANDIDATE++) {
            setComposites();
        }
    }

    private static void setComposites() {
        COMPOSITE = CANDIDATE * 2;
        while (COMPOSITE <= MAXIMUM) {
            MARKER[COMPOSITE - 1] = 'X';
            COMPOSITE += CANDIDATE;
        }
    }

    private static boolean isPrime(int index) {
        return MARKER[index - 1] == ' ';
    }
}