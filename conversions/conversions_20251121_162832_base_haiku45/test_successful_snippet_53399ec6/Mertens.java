```java
public class Mertens {
    private static final int MAX_SIZE = 1000;
    private int[] M = new int[MAX_SIZE + 1];
    private int N;
    private int K;
    private int V;
    private int isZero = 0;
    private int crossZero = 0;

    public static void main(String[] args) {
        Mertens mertens = new Mertens();
        mertens.generateMertens();
        mertens.writeTable();
        mertens.countZeroes();
    }

    private void generateMertens() {
        M[1] = 1;
        for (N = 2; N <= 1000; N++) {
            mertenOuterLoop();
        }
    }

    private void mertenOuterLoop() {
        M[N] = 1;
        for (K = 2; K <= N; K++) {
            mertenInnerLoop();
        }
    }

    private void mertenInnerLoop() {
        V = N / K;
        M[N] -= M[V];
    }

    private void writeTable() {
        System.out.println("The first 99 Mertens numbers are: ");
        StringBuilder outLine = new StringBuilder();
        for (N = 1; N <= 99; N++) {
            String outNum = String.format("%2d", M[N]);
            outLine.append(outNum).append(" ");
            if (outLine.length() >= 30) {
                System.out.println(outLine.toString());
                outLine = new StringBuilder();
            }
        }
        if (outLine.length() > 0) {
            System.out.println(outLine.toString());
        }
    }

    private void countZeroes() {
        for (N = 2; N <= 1000; N++) {
            testNZero();
        }
        System.out.println("M(N) is zero " + isZero + " times.");
        System.out.println("M(N) crosses zero " + crossZero + " times.");
    }

    private void testNZero() {
        if (M[N] == 0) {
            isZero++;
            K = N - 1;
            if (M[K] != 0) {
                crossZero++;
            }
        }
    }
}