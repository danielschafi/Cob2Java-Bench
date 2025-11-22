import java.text.DecimalFormat;

public class TauFunction {
    int total;
    int n;
    int p;
    int pSquared;
    double nDivP;
    int nextN;
    boolean divisible;
    int fCount;
    int i;
    String outItm;
    String outStr;
    int outPtr;

    public static void main(String[] args) {
        TauFunction tauFunction = new TauFunction();
        tauFunction.begin();
    }

    public void begin() {
        for (i = 1; i <= 100; i++) {
            showTau();
        }
    }

    public void showTau() {
        n = i;
        tau();
        DecimalFormat df = new DecimalFormat("000");
        outItm = df.format(total);
        outStr = String.format("%-80s", outStr.substring(0, outPtr - 1) + outItm);
        outPtr += 3;
        if (outPtr == 61) {
            System.out.println(outStr);
            outPtr = 1;
            outStr = String.format("%80s", "").replace(' ', ' ');
        }
    }

    public void tau() {
        total = 1;
        while ((n % 2) == 0) {
            powerOf2();
        }
        pSquared = 0;
        for (p = 3; pSquared <= n; p += 2) {
            oddFactor();
            while (divisible) {
                oddFactorLoop();
            }
            total *= fCount;
        }
        if (n > 1) {
            total *= 2;
        }
    }

    public void powerOf2() {
        total++;
        n /= 2;
    }

    public void oddFactor() {
        pSquared = p * p;
        fCount = 1;
    }

    public void oddFactorLoop() {
        nDivP = (double) n / p;
        nextN = (int) nDivP;
        divisible = nDivP == nextN;
        if (divisible) {
            n = nextN;
            fCount++;
        }
    }
}