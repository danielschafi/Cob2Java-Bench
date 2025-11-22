```java
import java.util.Random;

public class insertionsort {
    static class WorkingStorage {
        int a;
        int aLim = 10;
        int[] array = new int[10];
        
        int s;
        int o;
        int o1;
        int sortedLen;
        int sortedLim = 10;
        int[] sortedArray = new int[10];
    }
    
    public static void main(String[] args) {
        WorkingStorage ws = new WorkingStorage();
        startInsertionsort(ws);
    }
    
    static void startInsertionsort(WorkingStorage ws) {
        Random random = new Random();
        
        // fill the array
        ws.a = (int)(random.nextDouble() * 100);
        for (ws.a = 1; ws.a <= ws.aLim; ws.a++) {
            ws.array[ws.a - 1] = (int)(random.nextDouble() * 100);
        }
        
        // display the array
        for (ws.a = 1; ws.a <= ws.aLim; ws.a++) {
            System.out.print(" " + ws.array[ws.a - 1]);
        }
        System.out.println(" initial array");
        
        // sort the array
        ws.sortedLen = 0;
        for (ws.a = 1; ws.a <= ws.aLim; ws.a++) {
            // find the insertion point
            for (ws.s = 1; ws.s <= ws.sortedLen && ws.array[ws.a - 1] > ws.sortedArray[ws.s - 1]; ws.s++) {
                // continue
            }
            
            // open the insertion point
            for (ws.o = ws.sortedLen; ws.o >= ws.s; ws.o--) {
                ws.o1 = ws.o + 1;
                ws.sortedArray[ws.o1 - 1] = ws.sortedArray[ws.o - 1];
            }
            
            // move the array-entry to the insertion point
            ws.sortedArray[ws.s - 1] = ws.array[ws.a - 1];
            
            ws.sortedLen++;
        }
        
        // display the sorted array
        for (ws.s = 1; ws.s <= ws.sortedLim; ws.s++) {
            System.out.print(" " + ws.sortedArray[ws.s - 1]);
        }
        System.out.println(" sorted array");
    }
}