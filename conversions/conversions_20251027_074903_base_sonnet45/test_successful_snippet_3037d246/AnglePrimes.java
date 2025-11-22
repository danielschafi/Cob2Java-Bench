public class AnglePrimes {
    public static void main(String[] args) {
        int antiPrimesCtr = 0;
        int factorsCtr = 0;
        int wsInteger = 1;
        int wsMax = 0;
        int wsI = 0;
        int wsLimit = 1;
        int wsRemainder = 0;

        System.out.println("SEQ ANTI-PRIME FACTORS");

        while (antiPrimesCtr < 20) {
            factorsCtr = 0;
            wsLimit = (int) (1 + Math.sqrt(wsInteger));

            for (wsI = 1; wsI < wsLimit; wsI++) {
                wsRemainder = wsInteger % wsI;
                if (wsRemainder == 0) {
                    factorsCtr++;
                    if (wsInteger != wsI * wsI) {
                        factorsCtr++;
                    }
                }
            }

            if (factorsCtr > wsMax) {
                antiPrimesCtr++;
                wsMax = factorsCtr;
                System.out.printf("%03d   %5d    %5d%n", antiPrimesCtr, wsInteger, factorsCtr);
            }

            wsInteger++;
        }
    }
}