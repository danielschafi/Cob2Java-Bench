import java.text.DecimalFormat;

public class PrimeFibonacci {
    private int fib;
    private int fibB;
    private int fibC;
    private String fibOut;
    private char primeFlag;
    private int dsor;
    private int dsorSq;
    private double divRslt;

    public static void main(String[] args) {
        PrimeFibonacci primeFibonacci = new PrimeFibonacci();
        primeFibonacci.begin();
    }

    public void begin() {
        fib = 1;
        fibB = 1;
        for (int i = 0; i < 9; i++) {
            findPrimeFibonacci();
        }
    }

    public void findPrimeFibonacci() {
        fibC = fib + fibB;
        fibB = fib;
        fib = fibC;
        checkPrime();
        if (primeFlag != 'X') {
            findPrimeFibonacci();
        }
        DecimalFormat df = new DecimalFormat("00000#");
        fibOut = df.format(fib);
        System.out.println(fibOut);
    }

    public void checkPrime() {
        primeFlag = ' ';
        if (fib < 5) {
            trivialPrime();
        } else {
            divRslt = fib / 2.0;
            if (divRslt == (int) divRslt) {
                return;
            }
            divRslt = fib / 3.0;
            if (divRslt == (int) divRslt) {
                return;
            }
            dsor = 5;
            dsorSq = 25;
            primeFlag = 'X';
            while (primeFlag == 'X' && dsorSq <= fib) {
                testDivisor();
            }
        }
    }

    public void testDivisor() {
        divRslt = fib / (double) dsor;
        if (divRslt == (int) divRslt) {
            primeFlag = ' ';
        }
        dsor += 2;
        divRslt = fib / (double) dsor;
        if (divRslt == (int) divRslt) {
            primeFlag = ' ';
        }
        dsor += 4;
        dsorSq = dsor * dsor;
    }

    public void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = 'X';
        }
    }
}