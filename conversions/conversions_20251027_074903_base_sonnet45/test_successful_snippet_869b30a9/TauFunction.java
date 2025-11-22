public class TauFunction {
    private static class TauVars {
        int total;
        int n;
        int p;
        int pSquared;
        int nextN;
        int fCount;
        
        boolean isNEven() {
            int lastDigit = n % 10;
            return lastDigit == 0 || lastDigit == 2 || lastDigit == 4 || lastDigit == 6 || lastDigit == 8;
        }
        
        boolean isDivisible(int dividend, int divisor) {
            return dividend % divisor == 0;
        }
    }
    
    private static class OutVars {
        StringBuilder outStr = new StringBuilder();
        int outPtr = 0;
    }
    
    private TauVars tauVars = new TauVars();
    private OutVars outVars = new OutVars();
    
    public static void main(String[] args) {
        TauFunction program = new TauFunction();
        program.begin();
    }
    
    private void begin() {
        for (int i = 1; i <= 100; i++) {
            showTau(i);
        }
        if (outVars.outStr.length() > 0) {
            System.out.println(outVars.outStr.toString());
        }
    }
    
    private void showTau(int i) {
        tauVars.n = i;
        tau();
        String outItm = String.format("%3d", tauVars.total);
        outVars.outStr.append(outItm);
        outVars.outPtr += 3;
        
        if (outVars.outPtr >= 60) {
            System.out.println(outVars.outStr.toString());
            outVars.outStr = new StringBuilder();
            outVars.outPtr = 0;
        }
    }
    
    private void tau() {
        tauVars.total = 1;
        
        while (tauVars.isNEven()) {
            powerOf2();
        }
        
        tauVars.pSquared = 0;
        
        for (tauVars.p = 3; tauVars.pSquared <= tauVars.n; tauVars.p += 2) {
            oddFactor();
        }
        
        if (tauVars.n > 1) {
            tauVars.total *= 2;
        }
    }
    
    private void powerOf2() {
        tauVars.total += 1;
        tauVars.n /= 2;
    }
    
    private void oddFactor() {
        tauVars.pSquared = tauVars.p * tauVars.p;
        tauVars.fCount = 1;
        oddFactorLoop();
    }
    
    private void oddFactorLoop() {
        while (tauVars.isDivisible(tauVars.n, tauVars.p)) {
            tauVars.nextN = tauVars.n / tauVars.p;
            tauVars.n = tauVars.nextN;
            tauVars.fCount += 1;
        }
        tauVars.total *= tauVars.fCount;
    }
}