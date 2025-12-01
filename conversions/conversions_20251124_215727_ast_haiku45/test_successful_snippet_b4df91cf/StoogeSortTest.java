public class StoogeSortTest {
    private static final int ARR_LEN = 7;
    private int[] arr;

    public StoogeSortTest() {
        arr = new int[ARR_LEN];
        String valueStr = "00004001000020000005000230000000000";
        for (int i = 0; i < ARR_LEN; i++) {
            arr[i] = Integer.parseInt(valueStr.substring(i * 5, (i + 1) * 5));
        }
    }

    public static void main(String[] args) {
        StoogeSortTest test = new StoogeSortTest();
        test.run();
    }

    public void run() {
        System.out.print("Unsorted: ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(String.format("%05d", arr[i]) + " ");
        }
        System.out.println();

        stoogeSort(null, null);

        System.out.print("Sorted:   ");
        for (int i = 0; i < ARR_LEN; i++) {
            System.out.print(String.format("%05d", arr[i]) + " ");
        }
        System.out.println();
    }

    public void stoogeSort(Integer iVal, Integer jVal) {
        int i, j;

        if (iVal == null) {
            i = 1;
        } else {
            i = iVal;
        }

        if (jVal == null) {
            j = ARR_LEN;
        } else {
            j = jVal;
        }

        if (arr[j - 1] < arr[i - 1]) {
            int temp = arr[i - 1];
            arr[i - 1] = arr[j - 1];
            arr[j - 1] = temp;
        }

        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            j = j - t;
            stoogeSort(i, j);
            i = i + t;
            j = j + t;
            stoogeSort(i, j);
            i = i - t;
            j = j - t;
            stoogeSort(i, j);
        }
    }
}