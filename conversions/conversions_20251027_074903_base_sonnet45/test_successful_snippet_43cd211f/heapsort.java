import java.util.Random;

public class heapsort {
    private int a;
    private int aStart;
    private int aEnd;
    private int aParent;
    private int aChild;
    private int aSibling;
    private int aLim = 10;
    private int arraySwap;
    private int[] array = new int[11];

    public static void main(String[] args) {
        heapsort program = new heapsort();
        program.startHeapsort();
    }

    private void startHeapsort() {
        Random random = new Random(System.currentTimeMillis());
        
        for (a = 1; a <= aLim; a++) {
            array[a] = (int)(random.nextDouble() * 100);
        }

        displayArray();
        System.out.println(" initial array");

        aEnd = aLim;
        aStart = (aLim + 1) / 2;
        for (int tempStart = aStart; tempStart >= 1; tempStart--) {
            aStart = tempStart;
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
    }

    private void siftDown() {
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

    private void displayArray() {
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a]);
        }
    }
}