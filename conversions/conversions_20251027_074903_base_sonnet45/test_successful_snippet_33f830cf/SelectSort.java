import java.util.Random;

public class SelectSort {
    private static final int W_LEN_ARR = 30;
    private static final int W_MIN_NUMBER = 1;
    private static final int W_MAX_NUMBER = 200;
    
    private int[] wArr = new int[W_LEN_ARR];
    private Random random = new Random();
    
    public static void main(String[] args) {
        SelectSort program = new SelectSort();
        program.mainProcedure();
    }
    
    private void mainProcedure() {
        generateRandomNum();
        sortingArray();
    }
    
    private void generateRandomNum() {
        for (int wR = 0; wR < W_LEN_ARR; wR++) {
            int wRanNumber = 0;
            for (int i = 0; i < W_LEN_ARR; i++) {
                wRanNumber = random.nextInt(W_MAX_NUMBER - W_MIN_NUMBER + 1) + W_MIN_NUMBER;
            }
            wArr[wR] = wRanNumber;
            System.out.println("POS: " + (wR + 1) + " RANDOM NUMBER: " + wArr[wR]);
        }
    }
    
    private void sortingArray() {
        int wI = 0;
        
        while (wI < W_LEN_ARR) {
            int wMin = W_LEN_ARR - 1;
            int wJ = wI;
            
            while (wJ < W_LEN_ARR) {
                if (wArr[wMin] > wArr[wJ]) {
                    wMin = wJ;
                }
                wJ++;
            }
            
            int wSwap = wArr[wMin];
            wArr[wMin] = wArr[wI];
            wArr[wI] = wSwap;
            
            wI++;
        }
        
        for (int wH = 0; wH < W_LEN_ARR; wH++) {
            System.out.println("POS: " + (wH + 1) + " SORTED: " + wArr[wH]);
        }
    }
}