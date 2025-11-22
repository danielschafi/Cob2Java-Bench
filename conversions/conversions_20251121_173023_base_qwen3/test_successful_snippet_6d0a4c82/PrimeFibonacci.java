public class PrimeFibonacci {
    private static int fib = 1;
    private static int fibB = 1;
    private static int fibC;
    private static int dSor;
    private static int dSorSq;
    private static double divRslt;
    private static boolean primeFlag;
    
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            findPrimeFibonacci();
        }
    }
    
    private static void findPrimeFibonacci() {
        fibC = fib + fibB;
        fib = fibB;
        fibB = fibC;
        checkPrime();
        if (!primeFlag) {
            findPrimeFibonacci();
        } else {
            System.out.println(String.format("%5d", fib));
        }
    }
    
    private static void checkPrime() {
        primeFlag = false;
        if (fib < 5) {
            trivialPrime();
            return;
        }
        
        divRslt = fib / 2.0;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            return;
        }
        
        divRslt = fib / 3.0;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            return;
        }
        
        dSor = 5;
        dSorSq = 25;
        primeFlag = true;
        
        while (primeFlag && dSorSq <= fib) {
            testDivisor();
        }
    }
    
    private static void testDivisor() {
        divRslt = fib / (double) dSor;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            primeFlag = false;
        }
        dSor += 2;
        divRslt = fib / (double) dSor;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            primeFlag = false;
        }
        dSor += 4;
        dSorSq = dSor * dSor;
    }
    
    private static void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = true;
        }
    }
}