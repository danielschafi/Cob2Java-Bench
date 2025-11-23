public class ISQRT {
    private static long x;
    private static long q;
    private static long z;
    private static long t;
    private static long r;
    
    private static int isqrtN;
    private static String dispLn = "                      ";
    private static String dispFmt;
    private static int ptr = 1;
    
    private static long pow7 = 7;
    private static int powN = 1;
    private static String powNOut;
    private static String pow7Out;

    public static void main(String[] args) {
        sqrtsto65();
        bigSqrts();
    }

    public static void sqrtsto65() {
        for (isqrtN = 0; isqrtN <= 65; isqrtN++) {
            dispSmallSqrt();
        }
    }

    public static void dispSmallSqrt() {
        x = isqrtN;
        isqrt();
        dispFmt = String.format("%d", r);
        if (ptr + dispFmt.length() > 22) {
            System.out.println(dispLn);
            ptr = 1;
        }
        dispLn = dispLn.substring(0, ptr - 1) + dispFmt + dispLn.substring(ptr - 1 + dispFmt.length());
        ptr += dispFmt.length();
    }

    public static void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }

    public static void bigSqrt() {
        x = pow7;
        isqrt();
        powNOut = String.format("%d", powN);
        pow7Out = String.format("%d", r);
        System.out.println("ISQRT(7^" + powNOut + ") = " + pow7Out);
        powN += 2;
        pow7 *= 49;
    }

    public static void isqrt() {
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

    public static void mulQBy4() {
        q *= 4;
    }

    public static void isqrtStep() {
        q /= 4;
        t = z - r - q;
        r /= 2;
        if (t >= 0) {
            z = t;
            r += q;
        }
    }
}