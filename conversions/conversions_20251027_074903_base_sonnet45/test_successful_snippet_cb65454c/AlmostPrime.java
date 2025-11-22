public class AlmostPrime {
    public static void main(String[] args) {
        for (int k = 1; k <= 5; k++) {
            kAlmostPrimes(k);
        }
    }

    private static void kAlmostPrimes(int k) {
        StringBuilder kLn = new StringBuilder();
        int seen = 0;
        kLn.append("K = ").append(k).append(":");
        
        for (int i = 2; seen < 10; i++) {
            if (isKAlmostPrime(i, k)) {
                kLn.append(String.format(" %3d", i));
                seen++;
            }
        }
        
        System.out.println(kLn.toString());
    }

    private static boolean isKAlmostPrime(int n, int k) {
        int f = 0;
        int num = n;
        
        for (int p = 2; p * p <= num && f < k; p++) {
            while (num % p == 0) {
                num = num / p;
                f++;
            }
        }
        
        if (num > 1) {
            f++;
        }
        
        return f == k;
    }
}