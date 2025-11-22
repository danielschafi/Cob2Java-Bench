import java.math.BigInteger;

public class ISQRT {
    private static BigInteger X = BigInteger.ZERO;
    private static BigInteger Q = BigInteger.ZERO;
    private static BigInteger Z = BigInteger.ZERO;
    private static BigInteger T = BigInteger.ZERO;
    private static BigInteger R = BigInteger.ZERO;
    
    private static int ISQRT_N = 0;
    private static String DISP_LN = "                      ";
    private static String DISP_FMT = "";
    private static int PTR = 1;
    
    private static BigInteger POW_7 = BigInteger.valueOf(7);
    private static int POW_N = 1;
    private static String POW_N_OUT = "";
    private static String POW_7_OUT = "";

    public static void main(String[] args) {
        sqrtsto65();
        bigSqrts();
    }

    public static void sqrtsto65() {
        for (ISQRT_N = 0; ISQRT_N <= 65; ISQRT_N++) {
            dispSmallSqrt();
        }
    }

    public static void dispSmallSqrt() {
        X = BigInteger.valueOf(ISQRT_N);
        isqrt();
        DISP_FMT = R.toString();
        StringBuilder sb = new StringBuilder(DISP_LN);
        sb.replace(PTR - 1, PTR - 1 + DISP_FMT.length(), DISP_FMT);
        DISP_LN = sb.toString();
        PTR += DISP_FMT.length();
        if (PTR > 22) {
            System.out.println(DISP_LN);
            PTR = 1;
        }
    }

    public static void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }

    public static void bigSqrt() {
        X = POW_7;
        isqrt();
        POW_N_OUT = String.format("%d", POW_N);
        POW_7_OUT = R.toString();
        System.out.println("ISQRT(7^" + POW_N_OUT + ") = " + POW_7_OUT);
        POW_N += 2;
        POW_7 = POW_7.multiply(BigInteger.valueOf(49));
    }

    public static void isqrt() {
        Q = BigInteger.ONE;
        while (Q.compareTo(X) <= 0) {
            mulQBy4();
        }
        Z = X;
        R = BigInteger.ZERO;
        while (Q.compareTo(BigInteger.ONE) > 0) {
            isqrtStep();
        }
    }

    public static void mulQBy4() {
        Q = Q.multiply(BigInteger.valueOf(4));
    }

    public static void isqrtStep() {
        Q = Q.divide(BigInteger.valueOf(4));
        T = Z.subtract(R).subtract(Q);
        R = R.divide(BigInteger.valueOf(2));
        if (T.compareTo(BigInteger.ZERO) >= 0) {
            Z = T;
            R = R.add(Q);
        }
    }
}