```java
public class AlmostPrime {
    private static int K;
    private static int I;
    private static int SEEN;
    private static int N;
    private static int P;
    private static int P_SQUARED;
    private static int F;
    private static double N_DIV_P;
    private static int NEXT_N;
    
    private static String K_LN;
    private static int K_LN_PTR;
    private static String K_OUT;
    private static String I_OUT;
    
    public static void main(String[] args) {
        for (K = 1; K <= 5; K++) {
            kAlmostPrimes();
        }
    }
    
    private static void kAlmostPrimes() {
        K_LN = "";
        K_LN_PTR = 1;
        SEEN = 0;
        K_OUT = String.valueOf(K);
        String lnHdr = "K = " + K_OUT + ":";
        K_LN = lnHdr;
        
        for (I = 2; SEEN < 10; I++) {
            iKAlmostPrime();
        }
        System.out.println(K_LN);
    }
    
    private static void iKAlmostPrime() {
        F = 0;
        P_SQUARED = 0;
        N = I;
        
        for (P = 2; F < K && P_SQUARED <= N; P++) {
            primeFactor();
        }
        
        if (N > 1) {
            F++;
        }
        
        if (F == K) {
            I_OUT = String.format("%3d", I);
            SEEN++;
            K_LN += I_OUT;
        }
    }
    
    private static void primeFactor() {
        P_SQUARED = P * P;
        N_DIV_P = (double) N / P;
        NEXT_N = (int) N_DIV_P;
        
        while (N % P == 0) {
            divideFactor();
        }
    }
    
    private static void divideFactor() {
        N = NEXT_N;
        F++;
        N_DIV_P = (double) N / P;
        NEXT_N = (int) N_DIV_P;
    }
}