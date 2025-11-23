public class AlmostPrime {
    private static int k;
    private static int i;
    private static int seen;
    private static int n;
    private static int p;
    private static int pSquared;
    private static int f;
    private static double nDivP;
    private static int nextN;
    
    private static String kLn;
    private static int kLnPtr;
    private static String lnHdr;
    private static int kOut;
    private static String iFmt;
    private static String iOut;

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
        lnHdr = "K = " + k + ":";
        kLn = lnHdr;
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
        p = 1;
        
        do {
            p++;
            primeFactor();
        } while (!(f >= k || pSquared > n));
        
        if (n > 1) {
            f++;
        }
        
        if (f == k) {
            iOut = String.format("%3d", i);
            seen++;
            kLn += " " + iOut;
        }
    }

    private static void primeFactor() {
        pSquared = p * p;
        nDivP = (double) n / p;
        nextN = (int) nDivP;
        while (nDivP == (int) nDivP) {
            n = nextN;
            f++;
            nDivP = (double) n / p;
            nextN = (int) nDivP;
        }
    }
}