```java
public class AntiPrimes {
    private static int antiPrimesCtr = 0;
    private static int factorsCtr = 0;
    private static int wsInteger = 1;
    private static int wsMax = 0;
    private static int wsI = 0;
    private static int wsLimit = 1;
    private static int wsRemainder = 0;

    private static final String OUT_HDR = "SEQ ANTI-PRIME FACTORS";

    public static void main(String[] args) {
        System.out.println(OUT_HDR);
        
        for (wsInteger = 1; antiPrimesCtr < 20; wsInteger++) {
            getAntiPrimes();
        }
    }

    private static void getAntiPrimes() {
        factorsCtr = 0;
        wsLimit = (int) (1 + Math.sqrt(wsInteger));
        
        for (wsI = 1; wsI < wsLimit; wsI++) {
            countFactors();
        }
        
        if (factorsCtr > wsMax) {
            antiPrimesCtr++;
            wsMax = factorsCtr;
            
            String outSeq = String.format("%3d", antiPrimesCtr);
            String outAnti = String.format("%5d", wsInteger);
            String outFactors = String.format("%5d", factorsCtr);
            
            System.out.println(outSeq + "   " + outAnti + "    " + outFactors);
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