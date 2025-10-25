import java.util.Random;

public class Heapsort {
    private static final int A_LIM = 10;
    private static int a;
    private static int aStart;
    private static int aEnd;
    private static int aParent;
    private static int aChild;
    private static int aSibling;
    private static int arraySwap;
    private static final int[] array = new int[A_LIM];
    private static final Random random = new Random();

    public static void main(String[] args) {
        startHeapsort();
    }

    private static void startHeapsort() {
        a = random.nextInt();
        for (a = 1; a <= A_LIM; a++) {
            array[a - 1] = random.nextInt(100);
        }

        displayArray();
        System.out.println(" initial array");

        aEnd = A_LIM;
        aStart = (A_LIM + 1) / 2;
        for (aStart = aStart - 1; aStart >= 0; aStart--) {
            siftDown();
        }

        displayArray();
        System.out.println(" heapified");

        aStart = 1;
        aEnd = A_LIM;
        while (aEnd != aStart) {
            arraySwap = array[aEnd - 1];
            array[aEnd - 1] = array[aStart - 1];
            array[aStart - 1] = arraySwap;
            aEnd--;
            siftDown();
        }

        displayArray();
        System.out.println(" sorted");
    }

    private static void siftDown() {
        aParent = aStart;
        while (aParent * 2 + 1 < aEnd) {
            aChild = aParent * 2 + 1;
            aSibling = aChild + 1;
            if (aSibling < aEnd && array[aChild] < array[aSibling]) {
                aChild = aSibling;
            }
            if (array[aChild] > array[aParent]) {
                arraySwap = array[aChild];
                array[aChild] = array[aParent];
                array[aParent] = arraySwap;
            }
            aParent = aChild;
        }
    }

    private static void displayArray() {
        for (a = 1; a <= A_LIM; a++) {
            System.out.print(" " + array[a - 1]);
        }
        System.out.println();
    }
}