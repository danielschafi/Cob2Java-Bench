import java.text.DecimalFormat;

public class AlmostPrime {
    public static void main(String[] args) {
        for (int k = 1; k <= 5; k++) {
            kAlmostPrimes(k);
        }
    }

    public static void kAlmostPrimes(int k) {
        StringBuilder kLn = new StringBuilder("                                                                      ");
        int kLnPtr = 0;
        int seen = 0;
        kLn.append(String.format("K = %d:", k));
        kLnPtr += 6;

        for (int i = 2; seen < 10; i++) {
            int f = 0, pSquared = 0;
            int n = i;
            for (int p = 2; f < k && pSquared <= n; p++) {
                pSquared = p * p;
                double nDivP = (double) n / p;
                while (nDivP == (int) nDivP) {
                    n = (int) nDivP;
                    f++;
                    nDivP = (double) n / p;
                }
            }
            if (n > 1) {
                f++;
            }
            if (f == k) {
                kLn.append(String.format(" %3d", i));
                kLnPtr += 4;
                seen++;
            }
        }
        System.out.println(kLn.toString().trim());
    }
}