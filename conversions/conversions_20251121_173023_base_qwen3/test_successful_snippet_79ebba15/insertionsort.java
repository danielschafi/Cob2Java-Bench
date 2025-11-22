import java.util.Random;

public class insertionsort {
    private static int[] array = new int[10];
    private static int[] sortedArray = new int[10];
    private static Random random = new Random();

    public static void main(String[] args) {
        int a;
        int s;
        int o;
        int o1;
        int sortedLen;
        int sortedLim = 10;

        // fill the array
        for (a = 1; a <= 10; a++) {
            array[a-1] = random.nextInt(100);
        }

        // display the array
        for (a = 1; a <= 10; a++) {
            System.out.print(" " + array[a-1]);
        }
        System.out.println(" initial array");

        // sort the array
        sortedLen = 0;
        for (a = 1; a <= 10; a++) {
            // find the insertion point
            s = 1;
            while (s <= sortedLen && array[a-1] > sortedArray[s-1]) {
                s++;
            }

            // open the insertion point
            for (o = sortedLen; o >= s; o--) {
                o1 = o + 1;
                sortedArray[o1-1] = sortedArray[o-1];
            }

            // move the array-entry to the insertion point
            sortedArray[s-1] = array[a-1];

            sortedLen++;
        }

        // display the sorted array
        for (s = 1; s <= sortedLim; s++) {
            System.out.print(" " + sortedArray[s-1]);
        }
        System.out.println(" sorted array");
    }
}