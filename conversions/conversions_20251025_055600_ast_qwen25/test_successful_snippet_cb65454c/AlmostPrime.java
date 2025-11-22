import java.text.DecimalFormat;

public class AlmostPrime {
    private int k;
    private int i;
    private int seen;
    private int n;
    private int p;
    private int pSquared;
    private int f;
    private double nDivP;
    private int nextN;
    private boolean nDivsPid;
    private char[] kLn = new char[70];
    private int kLnPtr;
    private DecimalFormat kOutFormat = new DecimalFormat("K = #:");
    private DecimalFormat iOutFormat = new DecimalFormat(" ###");

    public static void main(String[] args) {
        AlmostPrime almostPrime = new AlmostPrime();
        almostPrime.begin();
    }

    public void begin() {
        for (k = 1; k <= 5; k++) {
            kAlmostPrimes();
        }
    }

    public void kAlmostPrimes() {
        for (int j = 0; j < kLn.length; j++) {
            kLn[j] = ' ';
        }
        kLnPtr = 0;
        seen = 0;
        String kOut = kOutFormat.format(k);
        for (char c : kOut.toCharArray()) {
            kLn[kLnPtr++] = c;
        }
        for (i = 2; seen < 10; i++) {
            iKAlmostPrime();
        }
        System.out.println(new String(kLn).trim());
    }

    public void iKAlmostPrime() {
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
            String iOut = iOutFormat.format(i);
            for (char c : iOut.toCharArray()) {
                kLn[kLnPtr++] = c;
            }
            seen++;
        }
    }

    public void primeFactor() {
        pSquared = p * p;
        nDivP = (double) n / p;
        divideFactor();
    }

    public void divideFactor() {
        n = nextN;
        f++;
        nDivP = (double) n / p;
        nDivsPid = nDivP == (int) nDivP;
    }
}