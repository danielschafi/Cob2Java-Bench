```java
public class ISQRT {
    private long X;
    private long Q;
    private long Z;
    private long T;
    private long R;
    private int ISQRT_N;
    private String DISP_LN;
    private int PTR;
    private long POW_7;
    private int POW_N;

    public ISQRT() {
        DISP_LN = "";
        POW_7 = 7;
        POW_N = 1;
    }

    public static void main(String[] args) {
        ISQRT program = new ISQRT();
        program.begin();
    }

    private void begin() {
        sqrtsTo65();
        bigSqrts();
    }

    private void sqrtsTo65() {
        for (ISQRT_N = 0; ISQRT_N <= 65; ISQRT_N++) {
            dispSmallSqrt();
        }
    }

    private void dispSmallSqrt() {
        X = ISQRT_N;
        isqrt();
        PTR = 1;
        DISP_LN = "";
        String formatted = String.format("%2d", R).replace(' ', '0');
        DISP_LN = formatted;
        PTR += formatted.length();
        if (PTR > 22) {
            System.out.print(DISP_LN);
            PTR = 1;
            DISP_LN = "";
        } else {
            System.out.print(DISP_LN);
        }
    }

    private void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }

    private void bigSqrt() {
        X = POW_7;
        isqrt();
        String powNOut = String.format("%2d", POW_N).trim();
        String pow7Out = String.format("%10d", R).trim();
        System.out.println("ISQRT(7^" + powNOut + ") = " + pow7Out);
        POW_N += 2;
        POW_7 *= 49;
    }

    private void isqrt() {
        Q = 1;
        while (Q <= X) {
            mulQBy4();
        }
        Z = X;
        R = 0;
        while (Q > 1) {
            isqrtStep();
        }
    }

    private void mulQBy4() {
        Q *= 4;
    }

    private void isqrtStep() {
        Q /= 4;
        T = Z - R - Q;
        R /= 2;
        if (T >= 0) {
            Z = T;
            R += Q;
        }
    }
}