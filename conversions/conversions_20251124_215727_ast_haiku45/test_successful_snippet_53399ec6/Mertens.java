```java
public class Mertens {
    private static final int ARRAY_SIZE = 1000;
    private static final int DISPLAY_COUNT = 99;
    
    private int[] M = new int[ARRAY_SIZE + 1];
    private int N;
    private int K;
    private int V;
    private int isZero = 0;
    private int crossZero = 0;
    
    private String outLine = "";
    private int outPtr = 4;
    
    public static void main(String[] args) {
        Mertens mertens = new Mertens();
        mertens.generateMertens();
        mertens.writeTable();
        mertens.countZeroes();
    }
    
    private void generateMertens() {
        M[1] = 1;
        for (N = 2; N <= ARRAY_SIZE; N++) {
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
        M[N] = M[N] - M[V];
    }
    
    private void writeTable() {
        System.out.println("The first 99 Mertens numbers are: ");
        for (N = 1; N <= DISPLAY_COUNT; N++) {
            writeItem();
        }
        if (outPtr > 1) {
            System.out.println(outLine.substring(0, outPtr - 1));
        }
    }
    
    private void writeItem() {
        String numStr = String.format("%2d", M[N]);
        outLine = outLine + numStr + " ";
        outPtr += 3;
        
        if (outPtr >= 31) {
            System.out.println(outLine.substring(0, Math.min(30, outLine.length())));
            outLine = "";
            outPtr = 1;
        }
    }
    
    private void countZeroes() {
        for (N = 2; N <= ARRAY_SIZE; N++) {
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