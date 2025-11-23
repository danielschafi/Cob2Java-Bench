public class StoogeSortTest {
    private static final int ArrLen = 7;
    private static int[] arrElt = new int[ArrLen];
    
    public static void main(String[] args) {
        String inputValue = "00004001000020000005000230000000000";
        
        for (int i = 0; i < ArrLen; i++) {
            arrElt[i] = Integer.parseInt(inputValue.substring(i * 5, (i + 1) * 5));
        }
        
        System.out.print("Unsorted: ");
        for (int i = 0; i < ArrLen; i++) {
            System.out.print(arrElt[i] + " ");
        }
        System.out.println();
        
        stoogeSort(arrElt, 0, ArrLen - 1);
        
        System.out.print("Sorted:   ");
        for (int i = 0; i < ArrLen; i++) {
            System.out.print(arrElt[i] + " ");
        }
        System.out.println();
    }
    
    public static void stoogeSort(int[] arrArea, int i, int j) {
        if (i >= j) {
            return;
        }
        
        if (arrArea[j] < arrArea[i]) {
            int temp = arrArea[i];
            arrArea[i] = arrArea[j];
            arrArea[j] = temp;
        }
        
        if (j - i + 1 >= 3) {
            int t = (j - i + 1) / 3;
            j = j - t;
            stoogeSort(arrArea, i, j);
            i = i + t;
            j = j + t;
            stoogeSort(arrArea, i, j);
            i = i - t;
            j = j - t;
            stoogeSort(arrArea, i, j);
        }
    }
}