import java.util.Random;

public class heapsort {
    private static int a;
    private static int aStart;
    private static int aEnd;
    private static int aParent;
    private static int aChild;
    private static int aSibling;
    private static int aLim = 10;
    private static int arraySwap;
    private static int[] array = new int[11];

    public static void main(String[] args) {
        startHeapsort();
    }

    private static void startHeapsort() {
        Random random = new Random();
        
        a = (int)(random.nextDouble() * System.currentTimeMillis());
        for (a = 1; a <= aLim; a++) {
            array[a] = (int)(random.nextDouble() * 100);
        }

        displayArray();
        System.out.println(" initial array");

        aEnd = aLim;
        aStart = (aLim + 1) / 2;
        for (; aStart >= 1; aStart--) {
            siftDown();
        }

        displayArray();
        System.out.println(" heapified");

        aStart = 1;
        aEnd = aLim;
        while (aEnd != aStart) {
            arraySwap = array[aEnd];
            array[aEnd] = array[aStart];
            array[aStart] = arraySwap;
            aEnd--;
            siftDown();
        }

        displayArray();
        System.out.println(" sorted");

        System.exit(0);
    }

    private static void siftDown() {
        aParent = aStart;
        while (aParent * 2 <= aEnd) {
            aChild = aParent * 2;
            aSibling = aChild + 1;
            if (aSibling <= aEnd && array[aChild] < array[aSibling]) {
                aChild = aSibling;
            }
            if (aChild <= aEnd && array[aParent] < array[aChild]) {
                arraySwap = array[aChild];
                array[aChild] = array[aParent];
                array[aParent] = arraySwap;
            }
            aParent = aChild;
        }
    }

    private static void displayArray() {
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a]);
        }
    }
}