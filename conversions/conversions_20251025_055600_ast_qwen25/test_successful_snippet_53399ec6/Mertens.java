import java.util.Arrays;

public class Mertens {
    private static final int SIZE = 1000;
    private int[] M = new int[SIZE];
    private int N, K, V;
    private int IS_ZERO = 0;
    private int CROSS_ZERO = 0;
    private int OUT_NUM;
    private char[] OUT_LINE = new char[30];
    private int OUT_PTR = 4;

    public static void main(String[] args) {
        Mertens mertens = new Mertens();
        mertens.generateMertens();
        mertens.writeTable();
        mertens.countZeroes();
    }

    private void generateMertens() {
        M[1] = 1;
        for (N = 2; N <= SIZE; N++) {
            M[N] = 1;
            for (K = 2; K <= N; K++) {
                V = N / K;
                M[N] -= M[V];
            }
        }
    }

    private void writeTable() {
        System.out.println("The first 99 Mertens numbers are: ");
        for (N = 1; N <= 99; N++) {
            writeItem();
        }
    }

    private void writeItem() {
        OUT_NUM = M[N];
        String outItem = String.format("%d ", OUT_NUM);
        outItem.getChars(0, outItem.length(), OUT_LINE, OUT_PTR);
        OUT_PTR += outItem.length();
        if (OUT_PTR >= 31) {
            System.out.println(new String(OUT_LINE));
            Arrays.fill(OUT_LINE, ' ');
            OUT_PTR = 4;
        }
    }

    private void countZeroes() {
        for (N = 2; N <= SIZE; N++) {
            testNZero();
        }
        System.out.println("M(N) is zero " + IS_ZERO + " times.");
        System.out.println("M(N) crosses zero " + CROSS_ZERO + " times.");
    }

    private void testNZero() {
        if (M[N] == 0) {
            IS_ZERO++;
            K = N - 1;
            if (M[K] != 0) {
                CROSS_ZERO++;
            }
        }
    }
}