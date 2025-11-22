```java
import java.util.Random;

public class heapsort {
    static int a;
    static int aStart;
    static int aEnd;
    static int aParent;
    static int aChild;
    static int aSibling;
    static int aLim = 10;
    static int arraySwap;
    static int[] array = new int[11];

    public static void main(String[] args) {
        startHeapsort();
    }

    static void startHeapsort() {
        Random random = new Random();
        long secondsPastMidnight = System.currentTimeMillis() % 86400000;
        random.setSeed(secondsPastMidnight);

        for (a = 1; a <= aLim; a++) {
            array[a] = (int) (random.nextDouble() * 100);
        }

        displayArray();
        System.out.println(" initial array");

        aEnd = aLim;
        aStart = (aLim + 1) / 2;
        for (int i = aStart; i >= 1; i--) {
            aStart = i;
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

    static void siftDown() {
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

    static void displayArray() {
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a]);
        }
    }
}