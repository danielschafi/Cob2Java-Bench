public class PrimeFibonacci {
    private static int fib = 1;
    private static int fibB = 1;
    private static int fibC = 0;
    private static String fibOut = "";
    
    private static char primeFlag = ' ';
    private static int dSor = 0;
    private static int dSorSq = 0;
    private static double divRslt = 0.0;
    
    public static void main(String[] args) {
        fib = 1;
        fibB = 1;
        for (int i = 0; i < 9; i++) {
            findPrimeFibonacci();
        }
    }
    
    private static void findPrimeFibonacci() {
        fibC = fib + fibB;
        fib = fibB;
        fibB = fibC;
        checkPrime();
        if (primeFlag != 'X') {
            findPrimeFibonacci();
            return;
        }
        fibOut = String.format("%5d", fib).trim();
        System.out.println(fibOut);
    }
    
    private static void checkPrime() {
        primeFlag = ' ';
        if (fib < 5) {
            trivialPrime();
            return;
        }
        
        divRslt = (double) fib / 2.0;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            return;
        }
        
        divRslt = (double) fib / 3.0;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            return;
        }
        
        dSor = 5;
        dSorSq = 25;
        primeFlag = 'X';
        
        while (primeFlag == 'X' && dSorSq <= fib) {
            testDivisor();
        }
    }
    
    private static void testDivisor() {
        divRslt = (double) fib / (double) dSor;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            primeFlag = ' ';
        }
        dSor += 2;
        divRslt = (double) fib / (double) dSor;
        if (divRslt == Math.floor(divRslt) && divRslt > 0) {
            primeFlag = ' ';
        }
        dSor += 4;
        dSorSq = dSor * dSor;
    }
    
    private static void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = 'X';
        }
    }
}