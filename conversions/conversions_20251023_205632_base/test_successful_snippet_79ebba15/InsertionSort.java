import java.util.Random;

public class InsertionSort {
    public static void main(String[] args) {
        int aLim = 10;
        int[] array = new int[aLim];
        int s, o, o1, sortedLen = 0;
        int sortedLim = 10;
        int[] sortedArray = new int[sortedLim];
        Random random = new Random(System.currentTimeMillis());

        for (int a = 0; a < aLim; a++) {
            array[a] = random.nextInt(100);
        }

        for (int a = 0; a < aLim; a++) {
            System.out.print(" " + array[a]);
        }
        System.out.println(" initial array");

        for (int a = 0; a < aLim; a++) {
            for (s = 0; s < sortedLen && array[a] > sortedArray[s]; s++) {
            }

            for (o = sortedLen - 1; o >= s; o--) {
                o1 = o + 1;
                sortedArray[o1] = sortedArray[o];
            }

            sortedArray[s] = array[a];
            sortedLen++;
        }

        for (s = 0; s < sortedLim; s++) {
            System.out.print(" " + sortedArray[s]);
        }
        System.out.println(" sorted array");
    }
}