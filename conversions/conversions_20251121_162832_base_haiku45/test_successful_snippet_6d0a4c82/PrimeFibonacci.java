public class PrimeFibonacci {
    private static int fib = 1;
    private static int fibB = 1;
    private static int fibC;
    private static char primeFlag;
    private static int dsor;
    private static int dsorSq;
    private static long divRslt;
    private static int divisibleRemainder;

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
        while (primeFlag != 'X') {
            fibC = fib + fibB;
            fib = fibB;
            fibB = fibC;
            checkPrime();
        }
        String output = String.format("%5d", fib).replace(' ', '0');
        System.out.println(output.replaceFirst("^0+(?!$)", ""));
    }

    private static void checkPrime() {
        primeFlag = ' ';
        if (fib < 5) {
            trivialPrime();
            return;
        }
        
        divRslt = fib / 2;
        divisibleRemainder = (int)(fib % 2);
        if (divisibleRemainder == 0) {
            return;
        }
        
        divRslt = fib / 3;
        divisibleRemainder = (int)(fib % 3);
        if (divisibleRemainder == 0) {
            return;
        }
        
        dsor = 5;
        dsorSq = 25;
        primeFlag = 'X';
        
        while (primeFlag == 'X' && dsorSq <= fib) {
            testDivisor();
        }
    }

    private static void testDivisor() {
        divRslt = fib / dsor;
        divisibleRemainder = (int)(fib % dsor);
        if (divisibleRemainder == 0) {
            primeFlag = ' ';
        }
        
        dsor += 2;
        divRslt = fib / dsor;
        divisibleRemainder = (int)(fib % dsor);
        if (divisibleRemainder == 0) {
            primeFlag = ' ';
        }
        
        dsor += 4;
        dsorSq = dsor * dsor;
    }

    private static void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = 'X';
        }
    }
}