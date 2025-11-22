import java.util.ArrayList;
import java.util.List;

public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private char[] marker = new char[MAXIMUM + 1];
    private int factorNum;
    private int factors;
    private String outLine = "";
    private int colPtr = 1;

    public static void main(String[] args) {
        AttractiveNumbers program = new AttractiveNumbers();
        program.begin();
    }

    private void begin() {
        sieve();
        for (int candidate = 2; candidate <= MAXIMUM; candidate++) {
            checkAttractive(candidate);
        }
        writeLine();
    }

    private void checkAttractive(int candidate) {
        factorNum = candidate;
        factorize();
        if (isPrime(factors)) {
            addToOutput(candidate);
        }
    }

    private void addToOutput(int candidate) {
        String outNum = String.format("%4d", candidate).replace(' ', ' ');
        outLine += outNum;
        colPtr += 4;
        if (colPtr >= 73) {
            writeLine();
        }
    }

    private void writeLine() {
        System.out.println(outLine);
        outLine = "";
        colPtr = 1;
    }

    private void factorize() {
        factors = 0;
        for (int factor = 2; factor <= MAXIMUM; factor++) {
            if (isPrime(factor)) {
                dividePrime(factor);
            }
        }
    }

    private void dividePrime(int factor) {
        while (factorNum % factor == 0) {
            factors++;
            factorNum = factorNum / factor;
        }
    }

    private void sieve() {
        for (int i = 0; i <= MAXIMUM; i++) {
            marker[i] = ' ';
        }
        marker[1] = 'X';
        int sieveMax = MAXIMUM / 2;
        for (int candidate = 2; candidate <= sieveMax; candidate++) {
            if (marker[candidate] == ' ') {
                int composite = candidate * 2;
                while (composite <= MAXIMUM) {
                    marker[composite] = 'X';
                    composite += candidate;
                }
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 1 || num > MAXIMUM) {
            return false;
        }
        return marker[num] == ' ';
    }
}