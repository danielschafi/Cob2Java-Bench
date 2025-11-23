import java.util.Random;

public class Heapsort {
    private static final int ALIM = 10;
    private static int[] array = new int[ALIM];
    private static int aStart;
    private static int aEnd;
    private static int aParent;
    private static int aChild;
    private static int aSibling;
    private static int arraySwap;
    
    public static void main(String[] args) {
        Random random = new Random();
        
        // fill the array
        int a = random.nextInt(100);
        for (int i = 1; i <= ALIM; i++) {
            array[i - 1] = random.nextInt(100);
        }
        
        displayArray();
        System.out.println("initial array");
        
        // heapify the array
        aEnd = ALIM;
        aStart = (ALIM + 1) / 2;
        for (int i = aStart; i >= 1; i--) {
            siftDown(i);
        }
        
        displayArray();
        System.out.println("heapified");
        
        // sort the array
        aStart = 1;
        aEnd = ALIM;
        while (aEnd != aStart) {
            arraySwap = array[aEnd - 1];
            array[aEnd - 1] = array[aStart - 1];
            array[aStart - 1] = arraySwap;
            aEnd--;
            siftDown();
        }
        
        displayArray();
        System.out.println("sorted");
    }
    
    private static void siftDown() {
        aParent = aStart;
        while (aParent * 2 <= aEnd) {
            aChild = aParent * 2;
            aSibling = aChild + 1;
            
            if (aSibling <= aEnd && array[aChild - 1] < array[aSibling - 1]) {
                // take the greater of the two
                aChild = aSibling;
            }
            
            if (aChild <= aEnd && array[aParent - 1] < array[aChild - 1]) {
                // the child is greater than the parent
                arraySwap = array[aChild - 1];
                array[aChild - 1] = array[aParent - 1];
                array[aParent - 1] = arraySwap;
            }
            
            // continue down the tree
            aParent = aChild;
        }
    }
    
    private static void siftDown(int start) {
        aParent = start;
        while (aParent * 2 <= aEnd) {
            aChild = aParent * 2;
            aSibling = aChild + 1;
            
            if (aSibling <= aEnd && array[aChild - 1] < array[aSibling - 1]) {
                // take the greater of the two
                aChild = aSibling;
            }
            
            if (aChild <= aEnd && array[aParent - 1] < array[aChild - 1]) {
                // the child is greater than the parent
                arraySwap = array[aChild - 1];
                array[aChild - 1] = array[aParent - 1];
                array[aParent - 1] = arraySwap;
            }
            
            // continue down the tree
            aParent = aChild;
        }
    }
    
    private static void displayArray() {
        for (int i = 1; i <= ALIM; i++) {
            System.out.print(array[i - 1] + " ");
        }
        System.out.println();
    }
}