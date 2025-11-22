import java.util.Random;
import java.time.LocalTime;

public class insertionsort {
    public static void main(String[] args) {
        int a;
        int aLim = 10;
        int[] array = new int[11];
        
        int s;
        int o;
        int o1;
        int sortedLen;
        int sortedLim = 10;
        int[] sortedArray = new int[11];
        
        Random random = new Random(LocalTime.now().toSecondOfDay());
        
        for (a = 1; a <= aLim; a++) {
            array[a] = (int)(random.nextDouble() * 100);
        }
        
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a]);
        }
        System.out.println(" initial array");
        
        sortedLen = 0;
        for (a = 1; a <= aLim; a++) {
            s = 1;
            while (s <= sortedLen && array[a] > sortedArray[s]) {
                s++;
            }
            
            for (o = sortedLen; o >= s; o--) {
                o1 = o + 1;
                sortedArray[o1] = sortedArray[o];
            }
            
            sortedArray[s] = array[a];
            
            sortedLen++;
        }
        
        for (s = 1; s <= sortedLim; s++) {
            System.out.print(" " + sortedArray[s]);
        }
        System.out.println(" sorted array");
    }
}