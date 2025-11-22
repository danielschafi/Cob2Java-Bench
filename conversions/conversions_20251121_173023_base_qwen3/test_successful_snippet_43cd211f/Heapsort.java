public class Heapsort {
    private static int[] array = new int[10];
    private static int aLim = 10;
    private static int arraySwap;
    
    public static void main(String[] args) {
        // fill the array
        java.util.Random rand = new java.util.Random();
        for (int a = 1; a <= aLim; a++) {
            array[a-1] = rand.nextInt(100);
        }
        
        displayArray();
        System.out.println("initial array");
        
        // heapify the array
        int aEnd = aLim;
        int aStart = (aLim + 1) / 2;
        for (int i = aStart; i >= 1; i--) {
            siftDown(i, aEnd);
        }
        
        displayArray();
        System.out.println("heapified");
        
        // sort the array
        int aStartSort = 1;
        aEnd = aLim;
        while (aEnd > aStartSort) {
            arraySwap = array[aEnd-1];
            array[aEnd-1] = array[aStartSort-1];
            array[aStartSort-1] = arraySwap;
            aEnd--;
            siftDown(aStartSort, aEnd);
        }
        
        displayArray();
        System.out.println("sorted");
    }
    
    public static void siftDown(int start, int end) {
        int aParent = start;
        while (aParent * 2 <= end) {
            int aChild = aParent * 2;
            int aSibling = aChild + 1;
            
            if (aSibling <= end && array[aChild-1] < array[aSibling-1]) {
                // take the greater of the two
                aChild = aSibling;
            }
            
            if (aChild <= end && array[aParent-1] < array[aChild-1]) {
                // the child is greater than the parent
                arraySwap = array[aChild-1];
                array[aChild-1] = array[aParent-1];
                array[aParent-1] = arraySwap;
            }
            
            // continue down the tree
            aParent = aChild;
        }
    }
    
    public static void displayArray() {
        for (int a = 1; a <= aLim; a++) {
            System.out.print(array[a-1] + " ");
        }
        System.out.println();
    }
}