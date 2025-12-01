public class AlmostPrime {
    private int k;
    private int i;
    private int seen;
    private int n;
    private int p;
    private int pSquared;
    private int f;
    private int nDivP;
    private int nextN;
    
    private String kLn;
    private int kLnPtr;
    private String lnHdr;
    private String iFmt;
    private int kOut;
    private int iOut;
    
    public AlmostPrime() {
        kLn = "";
        lnHdr = "K = :";
    }
    
    public static void main(String[] args) {
        AlmostPrime program = new AlmostPrime();
        program.begin();
    }
    
    private void begin() {
        for (k = 1; k <= 5; k++) {
            kAlmostPrimes();
        }
    }
    
    private void kAlmostPrimes() {
        kLn = "";
        kLnPtr = 0;
        seen = 0;
        kOut = k;
        
        String header = "K = " + k + ":";
        kLn = header;
        kLnPtr = header.length();
        
        for (i = 2; seen < 10; i++) {
            iKAlmostPrime();
        }
        
        System.out.println(kLn);
    }
    
    private void iKAlmostPrime() {
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
            iOut = i;
            seen++;
            String formatted = String.format(" %3d", i);
            kLn += formatted;
        }
    }
    
    private void primeFactor() {
        pSquared = p * p;
        nDivP = n / p;
        
        while (nDivP * p == n) {
            divideFactor();
        }
    }
    
    private void divideFactor() {
        n = nDivP;
        f++;
        nDivP = n / p;
    }
}