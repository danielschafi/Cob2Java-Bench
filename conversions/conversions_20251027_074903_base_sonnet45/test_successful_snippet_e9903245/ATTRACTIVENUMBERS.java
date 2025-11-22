import java.util.Arrays;

public class ATTRACTIVENUMBERS {
    private static final int MAXIMUM = 120;
    private static char[] marker = new char[MAXIMUM + 1];
    private static int sieveMax;
    private static int composite;
    private static int candidate;
    
    private static int factorNum;
    private static int factors;
    private static int factor;
    private static double quotient;
    
    private static StringBuilder outLine = new StringBuilder();
    private static int colPtr = 0;
    
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
        String outNum = String.format("%4d", candidate);
        outLine.append(outNum);
        colPtr += 4;
        if (colPtr >= 72) {
            writeLine();
        }
    }
    
    private static void writeLine() {
        System.out.println(outLine.toString());
        outLine = new StringBuilder();
        colPtr = 0;
    }
    
    private static void factorize() {
        factors = 0;
        for (factor = 2; factor <= MAXIMUM; factor++) {
            dividePrime();
        }
    }
    
    private static void dividePrime() {
        while (isPrime(factor)) {
            quotient = (double) factorNum / factor;
            int decimal = (int) Math.round((quotient - Math.floor(quotient)) * 1000);
            if (decimal == 0) {
                factors++;
                factorNum = (int) quotient;
            } else {
                break;
            }
        }
    }
    
    private static void sieve() {
        Arrays.fill(marker, ' ');
        marker[1] = 'X';
        sieveMax = MAXIMUM / 2;
        for (candidate = 2; candidate <= sieveMax; candidate++) {
            setComposites();
        }
    }
    
    private static void setComposites() {
        composite = candidate * 2;
        while (composite <= MAXIMUM) {
            marker[composite] = 'X';
            composite += candidate;
        }
    }
    
    private static boolean isPrime(int index) {
        if (index < 1 || index > MAXIMUM) {
            return false;
        }
        return marker[index] == ' ';
    }
}