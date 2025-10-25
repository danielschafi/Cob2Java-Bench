import java.text.DecimalFormat;

public class AnglePrimes {
    private static int antiPrimesCtr = 0;
    private static int factorsCtr = 0;
    private static int wsInteger = 1;
    private static int wsMax = 0;
    private static int wsI = 0;
    private static int wsLimit = 1;
    private static int wsRemainder = 0;

    private static final String OUT_HDR = "SEQ ANTI-PRIME FACTORS";
    private static final DecimalFormat OUT_SEQ_FORMAT = new DecimalFormat("000");
    private static final DecimalFormat OUT_ANTI_FORMAT = new DecimalFormat("00000");
    private static final DecimalFormat OUT_FACTORS_FORMAT = new DecimalFormat("00000");

    public static void main(String[] args) {
        System.out.println(OUT_HDR);
        while (antiPrimesCtr < 20) {
            getAntiPrimes();
            wsInteger++;
        }
    }

    private static void getAntiPrimes() {
        factorsCtr = 0;
        wsLimit = 1 + (int) Math.sqrt(wsInteger);
        for (wsI = 1; wsI < wsLimit; wsI++) {
            countFactors();
        }
        if (factorsCtr > wsMax) {
            antiPrimesCtr++;
            wsMax = factorsCtr;
            String outLine = OUT_SEQ_FORMAT.format(antiPrimesCtr) + "   " +
                             OUT_ANTI_FORMAT.format(wsInteger) + "   " +
                             OUT_FACTORS_FORMAT.format(factorsCtr);
            System.out.println(outLine);
        }
    }

    private static void countFactors() {
        wsRemainder = wsInteger % wsI;
        if (wsRemainder == 0) {
            factorsCtr++;
            if (wsInteger != wsI * wsI) {
                factorsCtr++;
            }
        }
    }
}