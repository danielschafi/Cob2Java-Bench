public class ANGLE_PRIMES {
    private static int ANTI_PRIMES_CTR = 0;
    private static int FACTORS_CTR = 0;
    private static int WS_INTEGER = 1;
    private static int WS_MAX = 0;
    private static int WS_I = 0;
    private static int WS_LIMIT = 1;
    private static int WS_REMAINDER = 0;
    
    private static String OUT_HDR = "SEQ ANTI-PRIME FACTORS";
    private static String OUT_LINE = "";
    private static int OUT_SEQ = 0;
    private static int OUT_ANTI = 0;
    private static int OUT_FACTORS = 0;

    public static void main(String[] args) {
        System.out.println(OUT_HDR);
        while (ANTI_PRIMES_CTR < 20) {
            getAntiPrimes();
            WS_INTEGER++;
        }
    }

    public static void getAntiPrimes() {
        FACTORS_CTR = 0;
        WS_LIMIT = 1 + (int)Math.sqrt(WS_INTEGER);
        for (WS_I = 1; WS_I < WS_LIMIT; WS_I++) {
            countFactors();
        }
        if (FACTORS_CTR > WS_MAX) {
            ANTI_PRIMES_CTR++;
            WS_MAX = FACTORS_CTR;
            OUT_SEQ = ANTI_PRIMES_CTR;
            OUT_ANTI = WS_INTEGER;
            OUT_FACTORS = FACTORS_CTR;
            System.out.printf("%03d%6d%8d%n", OUT_SEQ, OUT_ANTI, OUT_FACTORS);
        }
    }

    public static void countFactors() {
        WS_REMAINDER = WS_INTEGER % WS_I;
        if (WS_REMAINDER == 0) {
            FACTORS_CTR++;
            if (WS_INTEGER != WS_I * WS_I) {
                FACTORS_CTR++;
            }
        }
    }
}