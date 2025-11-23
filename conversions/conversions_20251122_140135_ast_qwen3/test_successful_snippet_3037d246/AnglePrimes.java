public class AnglePrimes {
    private static int antiPrimesCtr = 0;
    private static int factorsCtr = 0;
    private static int wsInteger = 1;
    private static int wsMax = 0;
    private static int wsI = 0;
    private static int wsLimit = 1;
    private static int wsRemainder = 0;
    
    private static String outHdr = "SEQ ANTI-PRIME FACTORS";
    private static int outSeq = 0;
    private static int outAnti = 0;
    private static int outFactors = 0;

    public static void main(String[] args) {
        System.out.println(outHdr);
        getAntiPrimes();
    }

    public static void getAntiPrimes() {
        while (antiPrimesCtr < 20) {
            factorsCtr = 0;
            wsLimit = 1 + (int) Math.sqrt(wsInteger);
            countFactors();
            if (factorsCtr > wsMax) {
                antiPrimesCtr++;
                wsMax = factorsCtr;
                outSeq = antiPrimesCtr;
                outAnti = wsInteger;
                outFactors = factorsCtr;
                System.out.printf("%03d       %d        %d%n", outSeq, outAnti, outFactors);
            }
            wsInteger++;
        }
    }

    public static void countFactors() {
        for (wsI = 1; wsI <= wsLimit; wsI++) {
            wsRemainder = wsInteger % wsI;
            if (wsRemainder == 0) {
                factorsCtr++;
                if (wsInteger != wsI * wsI) {
                    factorsCtr++;
                }
            }
        }
    }
}