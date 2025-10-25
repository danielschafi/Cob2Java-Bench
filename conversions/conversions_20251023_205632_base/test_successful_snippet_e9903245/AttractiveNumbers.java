import java.text.DecimalFormat;

public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private static final char[] MARKER = new char[MAXIMUM];
    private static int sieveMax;
    private static int composite;
    private static int candidate;
    private static int factorNum;
    private static int factors;
    private static int factor;
    private static double quotient;
    private static final char[] OUT_LINE = new char[72];
    private static int colPtr;

    public static void main(String[] args) {
        sieve();
        for (candidate = 2; candidate <= MAXIMUM; candidate++) {
            checkAttractive();
        }
        writeLine();
    }

    private static void checkAttractive() {
        factorNum = candidate;
        factorize();
        if (isPrime(factors)) {
            addToOutput();
        }
    }

    private static void addToOutput() {
        DecimalFormat outNumFormat = new DecimalFormat("000");
        String outNum = outNumFormat.format(candidate);
        outNum.getChars(0, 4, OUT_LINE, colPtr);
        colPtr += 4;
        if (colPtr == 73) {
            writeLine();
        }
    }

    private static void writeLine() {
        System.out.println(new String(OUT_LINE).trim());
        for (int i = 0; i < 72; i++) {
            OUT_LINE[i] = ' ';
        }
        colPtr = 0;
    }

    private static void factorize() {
        factors = 0;
        for (factor = 2; factor <= MAXIMUM; factor++) {
            dividePrime();
        }
    }

    private static void dividePrime() {
        if (isPrime(factor)) {
            quotient = (double) factorNum / factor;
            if (quotient == (int) quotient) {
                factors++;
                factorNum = (int) quotient;
                dividePrime();
            }
        }
    }

    private static void sieve() {
        MARKER[0] = 'X';
        sieveMax = MAXIMUM / 2;
        for (candidate = 2; candidate <= sieveMax; candidate++) {
            composite = candidate * 2;
            while (composite <= MAXIMUM) {
                MARKER[composite - 1] = 'X';
                composite += candidate;
            }
        }
    }

    private static boolean isPrime(int index) {
        return MARKER[index - 1] == ' ';
    }
}