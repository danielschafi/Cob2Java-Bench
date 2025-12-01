import java.util.Random;

public class insertionsort {
    private static int a;
    private static final int aLim = 10;
    private static int[] array = new int[10];
    
    private static int s;
    private static int o;
    private static int o1;
    private static int sortedLen;
    private static final int sortedLim = 10;
    private static int[] sortedArray = new int[10];
    
    public static void main(String[] args) {
        startInsertionsort();
    }
    
    private static void startInsertionsort() {
        Random random = new Random();
        
        // fill the array
        a = (int)(random.nextDouble() * (System.currentTimeMillis() % 1000));
        for (a = 1; a <= aLim; a++) {
            array[a - 1] = (int)(random.nextDouble() * 100);
        }
        
        // display the array
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a - 1]);
        }
        System.out.println(" initial array");
        
        // sort the array
        sortedLen = 0;
        for (a = 1; a <= aLim; a++) {
            // find the insertion point
            for (s = 1; s <= sortedLen && array[a - 1] > sortedArray[s - 1]; s++) {
                // continue
            }
            
            // open the insertion point
            for (o = sortedLen; o >= s; o--) {
                o1 = o + 1;
                sortedArray[o1 - 1] = sortedArray[o - 1];
            }
            
            // move the array-entry to the insertion point
            sortedArray[s - 1] = array[a - 1];
            
            sortedLen++;
        }
        
        // display the sorted array
        for (s = 1; s <= sortedLim; s++) {
            System.out.print(" " + sortedArray[s - 1]);
        }
        System.out.println(" sorted array");
        
        System.exit(0);
    }
}