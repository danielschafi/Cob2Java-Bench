import java.text.DecimalFormat;

public class ISQRT {
    private long x;
    private long q;
    private long z;
    private long t;
    private long r;
    private int isqrtN;
    private char[] dispLn = new char[22];
    private String dispFmt;
    private int ptr = 1;
    private long pow7 = 7;
    private int powN = 1;
    private String powNOut;
    private String pow7Out;

    public static void main(String[] args) {
        ISQRT program = new ISQRT();
        program.begin();
    }

    public void begin() {
        sqrtsTo65();
        bigSqrts();
    }

    public void sqrtsTo65() {
        for (isqrtN = 0; isqrtN <= 65; isqrtN++) {
            dispSmallSqrt();
        }
    }

    public void dispSmallSqrt() {
        x = isqrtN;
        isqrt();
        dispFmt = String.format("%01d", r);
        dispLn = dispFmt.toCharArray();
        if (ptr > 22) {
            System.out.println(new String(dispLn));
            ptr = 1;
        }
    }

    public void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }

    public void bigSqrt() {
        x = pow7;
        isqrt();
        powNOut = String.format("%01d", powN);
        pow7Out = String.format("%010d", r);
        System.out.println("ISQRT(7^" + powNOut + ") = " + pow7Out);
        powN += 2;
        pow7 *= 49;
    }

    public void isqrt() {
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

    public void mulQBy4() {
        q *= 4;
    }

    public void isqrtStep() {
        q /= 4;
        t = z - r - q;
        r /= 2;
        if (t >= 0) {
            z = t;
            r += q;
        }
    }
}