import java.util.Arrays;

public class Mertens {
    private int[] M = new int[1000];
    private int N;
    private int K;
    private int V;
    private int IS_ZERO = 0;
    private int CROSS_ZERO = 0;
    private char[] OUT_LINE = new char[30];
    private int OUT_PTR = 4;

    public static void main(String[] args) {
        Mertens mertens = new Mertens();
        mertens.generateMertens();
        mertens.writeTable();
        mertens.countZeroes();
    }

    private void generateMertens() {
        M[0] = 1;
        for (N = 1; N < 1000; N++) {
            M[N] = 1;
            for (K = 1; K <= N; K++) {
                V = N / (K + 1);
                M[N] -= M[V];
            }
        }
    }

    private void writeTable() {
        System.out.println("The first 99 Mertens numbers are: ");
        Arrays.fill(OUT_LINE, ' ');
        OUT_PTR = 4;
        for (N = 0; N < 99; N++) {
            writeItem(M[N]);
        }
    }

    private void writeItem(int num) {
        String outItem = String.format("%-2d ", num);
        for (char c : outItem.toCharArray()) {
            OUT_LINE[OUT_PTR++] = c;
            if (OUT_PTR == 30) {
                System.out.println(new String(OUT_LINE));
                Arrays.fill(OUT_LINE, ' ');
                OUT_PTR = 4;
            }
        }
    }

    private void countZeroes() {
        for (N = 1; N < 1000; N++) {
            if (M[N] == 0) {
                IS_ZERO++;
                K = N - 1;
                if (M[K] != 0) {
                    CROSS_ZERO++;
                }
            }
        }
        System.out.println("M(N) is zero " + IS_ZERO + " times.");
        System.out.println("M(N) crosses zero " + CROSS_ZERO + " times.");
    }
}