public class StoogeSortTest {
    private static final int ARR_LEN = 7;
    
    public static void main(String[] args) {
        int[] arr = {4, 1, 20, 5, 23, 0, 0};
        
        System.out.print("Unsorted: ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.printf("%05d ", arr[i]);
        }
        System.out.println();
        
        stoogeSort(arr, null, null);
        
        System.out.print("Sorted:   ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.printf("%05d ", arr[i]);
        }
        System.out.println();
    }
    
    private static void stoogeSort(int[] arr, Integer iVal, Integer jVal) {
        int i;
        int j;
        
        if (iVal == null) {
            i = 0;
        } else {
            i = iVal;
        }
        
        if (jVal == null) {
            j = ARR_LEN - 1;
        } else {
            j = jVal;
        }
        
        if (arr[j] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            j = j - t;
            stoogeSort(arr, i, j);
            i = i + t;
            j = j + t;
            stoogeSort(arr, i, j);
            i = i - t;
            j = j - t;
            stoogeSort(arr, i, j);
        }
    }
}