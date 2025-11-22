public class ALMOSTPRIME {
    private static int k;
    private static int i;
    private static int seen;
    private static int n;
    private static int p;
    private static int pSquared;
    private static int f;
    private static double nDivP;
    private static int nextN;
    private static boolean nDivsP;
    private static String kLn;
    private static int kLnPtr;
    private static String lnHdr;
    private static int kOut;
    private static String iFmt;
    private static int iOut;

    public static void main(String[] args) {
        for (k = 1; k <= 5; k++) {
            kAlmostPrimes();
        }
    }

    private static void kAlmostPrimes() {
        kLn = "";
        kLnPtr = 1;
        seen = 0;
        kOut = k;
        lnHdr = "K = " + kOut + ":";
        kLn = kLn + lnHdr;
        kLnPtr = kLn.length() + 1;

        for (i = 2; seen < 10; i++) {
            iKAlmostPrime();
        }
        System.out.println(kLn);
    }

    private static void iKAlmostPrime() {
        f = 0;
        pSquared = 0;
        n = i;
        for (p = 2; f < k && pSquared <= n; p++) {
            primeFactor();
        }
        if (n > 1) {
            f++;
        }
        if (f == k) {
            iOut = i;
            seen++;
            iFmt = " " + String.format("%3d", iOut);
            kLn = kLn + iFmt;
        }
    }

    private static void primeFactor() {
        pSquared = p * p;
        nDivP = (double) n / p;
        nextN = (int) nDivP;
        nDivsP = (nextN * p == n);
        while (nDivsP) {
            n = nextN;
            f++;
            nDivP = (double) n / p;
            nextN = (int) nDivP;
            nDivsP = (nextN * p == n);
        }
    }
}