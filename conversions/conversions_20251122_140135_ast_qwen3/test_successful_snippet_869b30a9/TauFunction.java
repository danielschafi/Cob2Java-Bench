public class TauFunction {
    private static int total;
    private static int n;
    private static int p;
    private static int pSquared;
    private static double nDivP;
    private static int nextN;
    private static int fCount;
    private static int i;
    private static int outItm;
    private static String outStr = "                                                                                                                                ";
    private static int outPtr = 1;

    public static void main(String[] args) {
        for (i = 1; i <= 100; i++) {
            showTau();
        }
    }

    private static void showTau() {
        n = i;
        tau();
        outItm = total;
        String outItmStr = String.format("%3d", outItm);
        outStr = outStr.substring(0, outPtr - 1) + outItmStr + outStr.substring(outPtr - 1 + outItmStr.length());
        outPtr += outItmStr.length();
        
        if (outPtr > 60) {
            System.out.println(outStr);
            outPtr = 1;
        }
    }

    private static void tau() {
        total = 1;
        while (isEven(n)) {
            powerOf2();
        }
        pSquared = 0;
        for (p = 3; p * p <= n; p += 2) {
            oddFactor();
            while (isDivisible()) {
                n = nextN;
                fCount++;
                // Loop back to oddFactor
            }
            total *= fCount;
        }
        if (n > 1) {
            total *= 2;
        }
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }

    private static void powerOf2() {
        total++;
        n /= 2;
    }

    private static void oddFactor() {
        pSquared = p * p;
        fCount = 1;
    }

    private static boolean isDivisible() {
        nDivP = (double) n / p;
        nextN = (int) nDivP;
        return nDivP == nextN;
    }
}