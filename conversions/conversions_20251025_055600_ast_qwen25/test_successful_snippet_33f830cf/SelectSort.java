import java.util.Random;

public class SelectSort {
    private static final int W_LEN_ARR = 30;
    private static final int W_MIN_NUMBER = 1;
    private static final int W_MAX_NUMBER = 200;

    private int[] wArr = new int[W_LEN_ARR];
    private int wR, wI, wJ, wH, wMin, wSwap, wRanNumber;

    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        selectSort.mainProcedure();
    }

    private void mainProcedure() {
        generateRandomNum();
        sortingArray();
    }

    private void generateRandomNum() {
        Random random = new Random();
        for (wR = 0; wR < W_LEN_ARR; wR++) {
            wRanNumber = random.nextInt(W_MAX_NUMBER - W_MIN_NUMBER + 1) + W_MIN_NUMBER;
            wArr[wR] = wRanNumber;
            System.out.println("POS: " + (wR + 1) + " RANDOM NUMBER: " + wArr[wR]);
        }
    }

    private void sortingArray() {
        for (wI = 0; wI < W_LEN_ARR; wI++) {
            wMin = wI;
            for (wJ = wI; wJ < W_LEN_ARR; wJ++) {
                if (wArr[wMin] > wArr[wJ]) {
                    wMin = wJ;
                }
            }
            wSwap = wArr[wMin];
            wArr[wMin] = wArr[wI];
            wArr[wI] = wSwap;
        }

        for (wH = 0; wH < W_LEN_ARR; wH++) {
            System.out.println("POS: " + (wH + 1) + " SORTED: " + wArr[wH]);
        }
    }
}