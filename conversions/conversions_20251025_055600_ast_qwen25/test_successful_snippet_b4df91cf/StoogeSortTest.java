import java.util.Arrays;

public class StoogeSortTest {

    private static final int ARR_LEN = 7;
    private static final int[] arr = {4, 1, 2, 0, 5, 23, 0};

    public static void main(String[] args) {
        System.out.print("Unsorted: ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        stoogeSort(arr, 0, ARR_LEN - 1);

        System.out.print("Sorted:   ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void stoogeSort(int[] arr, int l, int h) {
        if (l >= h) {
            return;
        }

        if (arr[h] < arr[l]) {
            int temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
        }

        if (h - l + 1 >= 3) {
            int t = (h - l + 1) / 3;
            stoogeSort(arr, l, h - t);
            stoogeSort(arr, l + t, h);
            stoogeSort(arr, l, h - t);
        }
    }
}