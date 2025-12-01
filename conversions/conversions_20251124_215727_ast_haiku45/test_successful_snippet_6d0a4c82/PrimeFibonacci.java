public class PrimeFibonacci {
    private long fib = 1;
    private long fibB = 1;
    private long fibC = 0;
    private String primeFlag = " ";
    private int dsor = 0;
    private long dsorSq = 0;
    private double divRslt = 0;

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
            if (!primeFlag.equals("X")) {
                continue;
            }
            System.out.println(String.format("%6d", fib).replaceAll("^\\s+", ""));
            break;
        }
    }

    private void checkPrime() {
        primeFlag = " ";
        if (fib < 5) {
            trivialPrime();
            return;
        }

        divRslt = (double) fib / 2;
        if (isDivisible()) {
            return;
        }

        divRslt = (double) fib / 3;
        if (isDivisible()) {
            return;
        }

        dsor = 5;
        dsorSq = 25;
        primeFlag = "X";

        while (primeFlag.equals("X") && dsorSq <= fib) {
            testDivisor();
        }
    }

    private void testDivisor() {
        divRslt = (double) fib / dsor;
        if (isDivisible()) {
            primeFlag = " ";
        }
        dsor += 2;

        divRslt = (double) fib / dsor;
        if (isDivisible()) {
            primeFlag = " ";
        }
        dsor += 4;

        dsorSq = (long) dsor * dsor;
    }

    private void trivialPrime() {
        if (fib == 2 || fib == 3) {
            primeFlag = "X";
        }
    }

    private boolean isDivisible() {
        long intPart = (long) divRslt;
        long remainder = fib - (intPart * (fib / intPart));
        return remainder == 0;
    }
}