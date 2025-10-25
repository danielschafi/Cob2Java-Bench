import java.util.Arrays;

public class StoogeSortTest {
    private static final int ARR_LEN = 7;
    private static final int[] arr = {0, 4, 0, 1, 0, 0, 2, 0, 0, 0, 5, 0, 0, 2, 3, 0, 0, 0, 0, 0};

    public static void main(String[] args) {
        System.out.print("Unsorted: ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(arr[i * 3 + 1] + " ");
        }
        System.out.println();

        stoogeSort(arr, 0, ARR_LEN - 1);

        System.out.print("Sorted:   ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(arr[i * 3 + 1] + " ");
        }
        System.out.println();
    }

    private static void stoogeSort(int[] arr, int i, int j) {
        if (arr[j * 3 + 1] < arr[i * 3 + 1]) {
            int temp = arr[i * 3 + 1];
            arr[i * 3 + 1] = arr[j * 3 + 1];
            arr[j * 3 + 1] = temp;
        }

        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            stoogeSort(arr, i, j - t);
            stoogeSort(arr, i + t, j);
            stoogeSort(arr, i, j - t);
        }
    }
}