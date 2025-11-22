import java.util.Scanner;

public class StoogeSortTest {
    private static final int ARR_LEN = 7;
    private int[] arr;

    public StoogeSortTest() {
        arr = new int[ARR_LEN];
        String arrStr = "00004001000020000005000230000000000";
        for (int i = 0; i < ARR_LEN; i++) {
            arr[i] = Integer.parseInt(arrStr.substring(i * 5, (i + 1) * 5));
        }
    }

    public void display(String label) {
        System.out.print(label);
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(String.format("%05d ", arr[i]));
        }
        System.out.println();
    }

    public void stoogeSort(int i, int j) {
        if (arr[j] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            stoogeSort(i, j - t);
            stoogeSort(i + t, j);
            stoogeSort(i, j - t);
        }
    }

    public static void main(String[] args) {
        StoogeSortTest test = new StoogeSortTest();
        test.display("Unsorted: ");
        test.stoogeSort(0, ARR_LEN - 1);
        test.display("Sorted:   ");
    }
}