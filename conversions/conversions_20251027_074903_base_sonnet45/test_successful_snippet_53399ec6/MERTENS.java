public class MERTENS {
    private static final int MAX_SIZE = 1000;
    private int[] M = new int[MAX_SIZE + 1];
    private int N;
    private int K;
    private int V;
    private int IS_ZERO = 0;
    private int CROSS_ZERO = 0;
    private StringBuilder OUT_LINE = new StringBuilder();
    private int OUT_PTR = 4;

    public static void main(String[] args) {
        MERTENS program = new MERTENS();
        program.BEGIN();
    }

    private void BEGIN() {
        GENERATE_MERTENS();
        WRITE_TABLE();
        COUNT_ZEROES();
    }

    private void GENERATE_MERTENS() {
        M[1] = 1;
        for (N = 2; N <= 1000; N++) {
            MERTENS_OUTER_LOOP();
        }
    }

    private void MERTENS_OUTER_LOOP() {
        M[N] = 1;
        for (K = 2; K <= N; K++) {
            MERTENS_INNER_LOOP();
        }
    }

    private void MERTENS_INNER_LOOP() {
        V = N / K;
        M[N] -= M[V];
    }

    private void WRITE_TABLE() {
        System.out.println("The first 99 Mertens numbers are: ");
        OUT_LINE = new StringBuilder();
        OUT_PTR = 1;
        for (N = 1; N <= 99; N++) {
            WRITE_ITEM();
        }
        if (OUT_LINE.length() > 0) {
            System.out.println(OUT_LINE.toString());
        }
    }

    private void WRITE_ITEM() {
        String outNum = String.format("%2d", M[N]);
        OUT_LINE.append(outNum).append(" ");
        OUT_PTR += 3;
        if (OUT_PTR >= 31) {
            System.out.println(OUT_LINE.toString());
            OUT_LINE = new StringBuilder();
            OUT_PTR = 1;
        }
    }

    private void COUNT_ZEROES() {
        for (N = 2; N <= 1000; N++) {
            TEST_N_ZERO();
        }
        System.out.println("M(N) is zero " + IS_ZERO + " times.");
        System.out.println("M(N) crosses zero " + CROSS_ZERO + " times.");
    }

    private void TEST_N_ZERO() {
        if (M[N] == 0) {
            IS_ZERO++;
            K = N - 1;
            if (M[K] != 0) {
                CROSS_ZERO++;
            }
        }
    }
}