import java.text.DecimalFormat;

public class TauFunction {
    private int total;
    private int n;
    private int p;
    private int pSquared;
    private double nDivP;
    private int nextN;
    private int fCount;
    private int i;
    private String outItm;
    private char[] outStr = new char[80];
    private int outPtr;

    public TauFunction() {
        for (int j = 0; j < outStr.length; j++) {
            outStr[j] = ' ';
        }
        outPtr = 1;
    }

    public static void main(String[] args) {
        TauFunction tauFunction = new TauFunction();
        tauFunction.begin();
    }

    private void begin() {
        for (i = 1; i <= 100; i++) {
            showTau();
        }
    }

    private void showTau() {
        n = i;
        tau();
        outItm = String.format("%03d", total);
        outStr = (outItm + new String(outStr)).toCharArray();
        if (outPtr == 61) {
            System.out.println(new String(outStr).trim());
            outPtr = 1;
        } else {
            outPtr += 3;
        }
    }

    private void tau() {
        total = 1;
        while ((n & 1) == 0) {
            powerOf2();
        }
        pSquared = 0;
        for (p = 3; pSquared <= n; p += 2) {
            oddFactor();
            while (nDivP == (int) nDivP) {
                nextN = (int) nDivP;
                fCount++;
                oddFactorLoop();
            }
            total *= fCount;
        }
        if (n > 1) {
            total *= 2;
        }
    }

    private void powerOf2() {
        total++;
        n /= 2;
    }

    private void oddFactor() {
        pSquared = p * p;
        fCount = 1;
    }

    private void oddFactorLoop() {
        nDivP = (double) n / p;
        if (nDivP == (int) nDivP) {
            n = nextN;
            fCount++;
        } else {
            total *= fCount;
        }
    }
}