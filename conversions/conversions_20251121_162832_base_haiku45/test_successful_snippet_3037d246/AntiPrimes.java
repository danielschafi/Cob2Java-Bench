```java
public class AntiPrimes {
    public static void main(String[] args) {
        int antiPrimesCtr = 0;
        int wsInteger = 1;
        int wsMax = 0;
        
        System.out.println("SEQ ANTI-PRIME FACTORS");
        
        while (antiPrimesCtr < 20) {
            int factorsCtr = 0;
            int wsLimit = (int)(1 + Math.sqrt(wsInteger));
            
            for (int wsI = 1; wsI < wsLimit; wsI++) {
                int wsRemainder = wsInteger % wsI;
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
                
                String outSeq = String.format("%03d", antiPrimesCtr);
                String outAnti = String.format("%5d", wsInteger);
                String outFactors = String.format("%5d", factorsCtr);
                
                System.out.println(outSeq + "   " + outAnti + "    " + outFactors);
            }
            
            wsInteger++;
        }
    }
}