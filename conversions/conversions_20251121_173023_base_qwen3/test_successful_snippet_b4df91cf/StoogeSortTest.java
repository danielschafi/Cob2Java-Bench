import java.util.Arrays;

public class StoogeSortTest {
    private static final int ArrLen = 7;
    
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 0, 4, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 5, 0, 0, 2, 3, 0, 0, 0, 0, 0, 0};
        
        System.out.print("Unsorted: ");
        for (int i = 0; i < ArrLen; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        stoogeSort(arr, 0, ArrLen - 1);
        
        System.out.print("Sorted:   ");
        for (int i = 0; i < ArrLen; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void stoogeSort(int[] arr, int i, int j) {
        if (arr[j] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            j -= t;
            stoogeSort(arr, i, j);
            i += t;
            j += t;
            stoogeSort(arr, i, j);
            i -= t;
            j -= t;
            stoogeSort(arr, i, j);
        }
    }
}