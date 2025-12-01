```java
public class TauFunction {
    private int total;
    private int n;
    private int p;
    private int pSquared;
    private int nDivP;
    private int nextN;
    private int fCount;
    private int i;
    private String outStr;
    private int outPtr;

    public TauFunction() {
        outStr = "";
        outPtr = 1;
    }

    public static void main(String[] args) {
        TauFunction program = new TauFunction();
        program.begin();
    }

    private void begin() {
        for (i = 1; i <= 100; i++) {
            showTau();
        }
    }

    private void showTau() {
        n = i;
        tau();
        String outItm = String.format("%3d", total).replace(' ', ' ');
        outStr += outItm;
        outPtr += outItm.length();
        
        if (outPtr >= 61) {
            System.out.print(outStr);
            outStr = "";
            outPtr = 1;
        }
    }

    private void tau() {
        total = 1;
        powerOf2();
        pSquared = 0;
        
        for (p = 3; pSquared <= n; p += 2) {
            oddFactor();
        }
        
        if (n > 1) {
            total *= 2;
        }
    }

    private void powerOf2() {
        while (isNEven()) {
            total += 1;
            n = n / 2;
        }
    }

    private boolean isNEven() {
        int lastDigit = n % 10;
        return lastDigit == 0 || lastDigit == 2 || lastDigit == 4 || lastDigit == 6 || lastDigit == 8;
    }

    private void oddFactor() {
        pSquared = p * p;
        fCount = 1;
        oddFactorLoop();
    }

    private void oddFactorLoop() {
        while (true) {
            nDivP = n / p;
            int remainder = n % p;
            nextN = nDivP;
            
            if (remainder == 0) {
                n = nextN;
                fCount += 1;
            } else {
                break;
            }
        }
        total *= fCount;
    }
}