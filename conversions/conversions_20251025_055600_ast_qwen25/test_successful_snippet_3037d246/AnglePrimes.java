import java.text.DecimalFormat;

public class AnglePrimes {
    private int antiPrimesCtr = 0;
    private int factorsCtr = 0;
    private int wsInteger = 1;
    private int wsMax = 0;
    private int wsI = 0;
    private int wsLimit = 1;
    private int wsRemainder = 0;

    private final String outHdr = "SEQ ANTI-PRIME FACTORS";
    private final DecimalFormat outSeqFormat = new DecimalFormat("000");
    private final DecimalFormat outAntiFormat = new DecimalFormat("00000");
    private final DecimalFormat outFactorsFormat = new DecimalFormat("00000");

    public static void main(String[] args) {
        AnglePrimes anglePrimes = new AnglePrimes();
        anglePrimes.displayHeader();
        while (anglePrimes.antiPrimesCtr < 20) {
            anglePrimes.getAntiPrimes();
        }
    }

    private void displayHeader() {
        System.out.println(outHdr);
    }

    private void getAntiPrimes() {
        factorsCtr = 0;
        wsLimit = 1 + (int) Math.sqrt(wsInteger);
        for (wsI = 1; wsI < wsLimit; wsI++) {
            countFactors();
        }
        if (factorsCtr > wsMax) {
            antiPrimesCtr++;
            wsMax = factorsCtr;
            System.out.println(outSeqFormat.format(antiPrimesCtr) + "   " +
                               outAntiFormat.format(wsInteger) + "   " +
                               outFactorsFormat.format(factorsCtr));
        }
        wsInteger++;
    }

    private void countFactors() {
        wsRemainder = wsInteger % wsI;
        if (wsRemainder == 0) {
            factorsCtr++;
            if (wsInteger != wsI * wsI) {
                factorsCtr++;
            }
        }
    }
}