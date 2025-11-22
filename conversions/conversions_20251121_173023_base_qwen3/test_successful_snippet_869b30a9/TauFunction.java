public class TauFunction {
    private static int total;
    private static int n;
    private static int p;
    private static int pSquared;
    private static double nDivP;
    private static int nextN;
    private static boolean divisible;
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

    public static void showTau() {
        n = i;
        tau();
        outItm = total;
        String outItemStr = String.format("%03d", outItm);
        outStr = outStr.substring(0, outPtr - 1) + outItemStr + outStr.substring(outPtr - 1 + outItemStr.length());
        outPtr += 3;
        if (outPtr > 60) {
            System.out.println(outStr);
            outPtr = 1;
        }
    }

    public static void tau() {
        total = 1;
        while (n % 2 == 0) {
            powerOf2();
        }
        pSquared = 0;
        p = 3;
        while (pSquared <= n) {
            oddFactor();
            p += 2;
        }
        if (n > 1) {
            total *= 2;
        }
    }

    public static void powerOf2() {
        total++;
        n /= 2;
    }

    public static void oddFactor() {
        pSquared = p * p;
        fCount = 1;
        while (true) {
            nDivP = (double)n / p;
            nextN = (int)nDivP;
            divisible = (nDivP == nextN);
            if (!divisible) {
                break;
            }
            n = nextN;
            fCount++;
        }
        total *= fCount;
    }
}