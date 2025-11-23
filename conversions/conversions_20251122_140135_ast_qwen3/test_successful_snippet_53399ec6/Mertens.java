public class Mertens {
    private static int[] M = new int[1001];
    private static int N;
    private static int K;
    private static int V;
    private static int IS_ZERO = 0;
    private static int CROSS_ZERO = 0;
    private static String OUT_LINE = "                              ";
    private static int OUT_PTR = 4;

    public static void main(String[] args) {
        generateMertens();
        writeTable();
        countZeroes();
    }

    public static void generateMertens() {
        M[1] = 1;
        for (N = 2; N <= 1000; N++) {
            M[N] = 1;
            for (K = 2; K <= N; K++) {
                V = N / K;
                M[N] -= M[V];
            }
        }
    }

    public static void writeTable() {
        System.out.println("The first 99 Mertens numbers are: ");
        for (N = 1; N <= 99; N++) {
            writeItem();
        }
    }

    public static void writeItem() {
        String outNumStr = String.format("%-2d", M[N]);
        OUT_LINE = OUT_LINE.substring(0, OUT_PTR - 1) + outNumStr + OUT_LINE.substring(OUT_PTR - 1 + outNumStr.length());
        OUT_PTR += outNumStr.length();
        if (OUT_PTR > 30) {
            System.out.println(OUT_LINE);
            OUT_PTR = 1;
        }
    }

    public static void countZeroes() {
        for (N = 2; N <= 1000; N++) {
            testNZero();
        }
        System.out.println("M(N) is zero " + IS_ZERO + " times.");
        System.out.println("M(N) crosses zero " + CROSS_ZERO + " times.");
    }

    public static void testNZero() {
        if (M[N] == 0) {
            IS_ZERO++;
            K = N - 1;
            if (M[K] != 0) {
                CROSS_ZERO++;
            }
        }
    }
}