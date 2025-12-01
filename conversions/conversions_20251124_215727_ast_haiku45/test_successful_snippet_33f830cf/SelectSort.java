```java
public class SelectSort {
    private static final int W_LEN_ARR = 30;
    private int[] wArr = new int[W_LEN_ARR];
    private int wR = 1;
    private int wI = 1;
    private int wJ = 1;
    private int wH = 1;
    private int wMin = 0;
    private int wSwap = 0;
    private int wMinNumber = 1;
    private int wMaxNumber = 200;
    private int wRanNumber = 0;

    public static void main(String[] args) {
        SelectSort program = new SelectSort();
        program.mainProcedure();
    }

    private void mainProcedure() {
        generateRandomNum();
        sortingArray();
    }

    private void generateRandomNum() {
        wR = 1;
        while (wR <= W_LEN_ARR) {
            for (int i = 0; i < W_LEN_ARR; i++) {
                wRanNumber = (int) (Math.random() * (wMaxNumber - wMinNumber + 1) + wMinNumber);
            }
            wArr[wR - 1] = wRanNumber;
            System.out.println("POS: " + wR + " RANDOM NUMBER: " + wArr[wR - 1]);
            wR++;
        }
    }

    private void sortingArray() {
        wI = 1;
        while (wI <= W_LEN_ARR) {
            wMin = W_LEN_ARR;
            wJ = wI;

            while (wJ <= W_LEN_ARR) {
                if (wArr[wMin - 1] > wArr[wJ - 1]) {
                    wMin = wJ;
                }
                wJ++;
            }

            wSwap = wArr[wMin - 1];
            wArr[wMin - 1] = wArr[wI - 1];
            wArr[wI - 1] = wSwap;

            wI++;
        }

        wH = 1;
        while (wH <= W_LEN_ARR) {
            System.out.println("POS: " + wH + " SORTED: " + wArr[wH - 1]);
            wH++;
        }
    }
}