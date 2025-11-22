import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrimeFibonacci {
    private int fib;
    private int fibB;
    private int fibC;
    private boolean primeFlag;
    private int dsor;
    private int dsorSq;

    public static void main(String[] args) {
        PrimeFibonacci program = new PrimeFibonacci();
        program.begin();
    }

    private void begin() {
        fib = 1;
        fibB = 1;
        for (int i = 0; i < 9; i++) {
            findPrimeFibonacci();
        }
    }

    private void findPrimeFibonacci() {
        while (true) {
            fibC = fib + fibB;
            fib = fibB;
            fibB = fibC;
            checkPrime();
            if (primeFlag) {
                break;
            }
        }
        System.out.println(String.format("%6d", fib));
    }

    private void checkPrime() {
        primeFlag = false;
        
        if (fib < 5) {
            trivialPrime();
            return;
        }
        
        BigDecimal fibDecimal = new BigDecimal(fib);
        BigDecimal divRslt;
        
        divRslt = fibDecimal.divide(new BigDecimal(2), 3, RoundingMode.DOWN);
        if (isDivisible(divRslt)) {
            return;
        }
        
        divRslt = fibDecimal.divide(new BigDecimal(3), 3, RoundingMode.DOWN);
        if (isDivisible(divRslt)) {
            return;
        }
        
        dsor = 5;
        dsorSq = 25;
        primeFlag = true;
        
        while (primeFlag && dsorSq <= fib) {
            testDivisor();
        }
    }

    private void testDivisor() {
        BigDecimal fibDecimal = new BigDecimal(fib);
        BigDecimal divRslt;
        
        divRslt = fibDecimal.divide(new BigDecimal(dsor), 3, RoundingMode.DOWN);
        if (isDivisible(divRslt)) {
            primeFlag = false;
        }
        
        dsor += 2;
        
        divRslt = fibDecimal.divide(new BigDecimal(dsor), 3, RoundingMode.DOWN);
        if (isDivisible(divRslt)) {
            primeFlag = false;
        }
        
        dsor += 4;
        dsorSq = dsor * dsor;
    }

    private void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = true;
        }
    }

    private boolean isDivisible(BigDecimal divRslt) {
        BigDecimal fractionalPart = divRslt.remainder(BigDecimal.ONE);
        int fractionalPartScaled = fractionalPart.movePointRight(3).intValue();
        return fractionalPartScaled == 0;
    }
}