public class ISQRT {

    private long x;
    private long q;
    private long z;
    private long t;
    private long r;

    private int isqrtN;
    private StringBuilder dispLn;
    private int ptr;

    private long pow7;
    private int powN;

    public static void main(String[] args) {
        ISQRT program = new ISQRT();
        program.begin();
    }

    public ISQRT() {
        x = 0;
        q = 0;
        z = 0;
        t = 0;
        r = 0;
        isqrtN = 0;
        dispLn = new StringBuilder("                      ");
        ptr = 1;
        pow7 = 7;
        powN = 1;
    }

    private void begin() {
        sqrtsTo65();
        bigSqrts();
    }

    private void sqrtsTo65() {
        for (isqrtN = 0; isqrtN <= 65; isqrtN++) {
            dispSmallSqrt();
        }
    }

    private void dispSmallSqrt() {
        x = isqrtN;
        isqrt();
        String dispFmt = String.format("%2d", r);
        
        if (ptr - 1 + dispFmt.length() > dispLn.length()) {
            System.out.println(dispLn.toString());
            dispLn = new StringBuilder("                      ");
            ptr = 1;
        }
        
        dispLn.replace(ptr - 1, ptr - 1 + dispFmt.length(), dispFmt);
        ptr += dispFmt.length();
        
        if (ptr > 22) {
            System.out.println(dispLn.toString());
            dispLn = new StringBuilder("                      ");
            ptr = 1;
        }
    }

    private void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }

    private void bigSqrt() {
        x = pow7;
        isqrt();
        String powNOut = String.format("%2d", powN);
        String pow7Out = String.format("%10d", r);
        System.out.println("ISQRT(7^" + powNOut + ") = " + pow7Out);
        powN += 2;
        pow7 *= 49;
    }

    private void isqrt() {
        q = 1;
        while (q <= x) {
            mulQBy4();
        }
        z = x;
        r = 0;
        while (q > 1) {
            isqrtStep();
        }
    }

    private void mulQBy4() {
        q *= 4;
    }

    private void isqrtStep() {
        q /= 4;
        t = z - r - q;
        r /= 2;
        if (t >= 0) {
            z = t;
            r += q;
        }
    }
}