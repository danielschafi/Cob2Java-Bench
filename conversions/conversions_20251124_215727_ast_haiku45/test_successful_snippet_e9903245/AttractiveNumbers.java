public class AttractiveNumbers {
    private static final int MAXIMUM = 120;
    private char[] marker = new char[121];
    private int sieveMax;
    private int composite;
    private int candidate;
    private int factorNum;
    private int factors;
    private int factor;
    private double quotient;
    private int outNum;
    private StringBuilder outLine = new StringBuilder();
    private int colPtr;

    public AttractiveNumbers() {
        for (int i = 0; i <= MAXIMUM; i++) {
            marker[i] = ' ';
        }
        colPtr = 1;
    }

    public static void main(String[] args) {
        AttractiveNumbers program = new AttractiveNumbers();
        program.sieve();
        for (program.candidate = 2; program.candidate <= MAXIMUM; program.candidate++) {
            program.checkAttractive();
        }
        program.writeLine();
    }

    private void sieve() {
        marker[1] = 'X';
        sieveMax = MAXIMUM / 2;
        for (candidate = 2; candidate <= sieveMax; candidate++) {
            setComposites();
        }
    }

    private void setComposites() {
        composite = candidate * 2;
        while (composite <= MAXIMUM) {
            marker[composite] = 'X';
            composite += candidate;
        }
    }

    private void checkAttractive() {
        factorNum = candidate;
        factorize();
        if (isPrime(factors)) {
            addToOutput();
        }
    }

    private void factorize() {
        factors = 0;
        for (factor = 2; factor <= MAXIMUM; factor++) {
            if (isPrime(factor)) {
                quotient = (double) factorNum / factor;
                int decimal = (int) ((quotient - Math.floor(quotient)) * 1000);
                if (decimal == 0) {
                    factors++;
                    factorNum = (int) quotient;
                    factor--;
                }
            }
        }
    }

    private boolean isPrime(int index) {
        if (index >= 1 && index <= MAXIMUM) {
            return marker[index] == ' ';
        }
        return false;
    }

    private void addToOutput() {
        String numStr = String.format("%4d", candidate).replace(' ', ' ');
        outLine.append(numStr);
        colPtr += numStr.length();
        if (colPtr >= 73) {
            writeLine();
        }
    }

    private void writeLine() {
        System.out.println(outLine.toString());
        outLine = new StringBuilder();
        colPtr = 1;
    }
}