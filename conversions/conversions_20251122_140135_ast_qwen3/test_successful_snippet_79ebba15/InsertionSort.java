import java.util.*;

public class InsertionSort {
    private static final int ARRAY_SIZE = 10;
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] array = new int[ARRAY_SIZE];
        int[] sortedArray = new int[ARRAY_SIZE];
        int sortedLen = 0;

        // Fill the array
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(100);
        }

        // Display the array
        System.out.print(" ");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("initial array");

        // Sort the array
        for (int a = 0; a < ARRAY_SIZE; a++) {
            // Find the insertion point
            int s = 0;
            while (s < sortedLen && array[a] > sortedArray[s]) {
                s++;
            }

            // Open the insertion point
            for (int o = sortedLen - 1; o >= s; o--) {
                sortedArray[o + 1] = sortedArray[o];
            }

            // Move the array entry to the insertion point
            sortedArray[s] = array[a];
            sortedLen++;
        }

        // Display the sorted array
        System.out.print(" ");
        for (int s = 0; s < ARRAY_SIZE; s++) {
            System.out.print(sortedArray[s] + " ");
        }
        System.out.println("sorted array");
    }
}