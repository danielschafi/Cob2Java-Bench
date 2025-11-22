```java
public class SelectSort {
    private static final int W_LEN_ARR = 30;
    private static final int W_MIN_NUMBER = 1;
    private static final int W_MAX_NUMBER = 200;
    
    private int[] W_ARR = new int[W_LEN_ARR];
    private int W_R = 1;
    private int W_I = 1;
    private int W_J = 1;
    private int W_H = 1;
    private int W_MIN = 0;
    private int W_SWAP = 0;
    private int W_RAN_NUMBER = 0;
    
    public static void main(String[] args) {
        SelectSort program = new SelectSort();
        program.mainProcedure();
    }
    
    private void mainProcedure() {
        generateRandomNum();
        sortingArray();
    }
    
    private void generateRandomNum() {
        for (W_R = 1; W_R <= W_LEN_ARR; W_R++) {
            for (int i = 0; i < W_LEN_ARR; i++) {
                W_RAN_NUMBER = (int) (Math.random() * (W_MAX_NUMBER - W_MIN_NUMBER + 1) + W_MIN_NUMBER);
            }
            W_ARR[W_R - 1] = W_RAN_NUMBER;
            System.out.println("POS: " + W_R + " RANDOM NUMBER: " + W_ARR[W_R - 1]);
        }
    }
    
    private void sortingArray() {
        W_I = 1;
        while (W_I <= W_LEN_ARR) {
            W_MIN = W_LEN_ARR;
            W_J = W_I;
            
            while (W_J <= W_LEN_ARR) {
                if (W_ARR[W_MIN - 1] > W_ARR[W_J - 1]) {
                    W_MIN = W_J;
                }
                W_J++;
            }
            
            W_SWAP = W_ARR[W_MIN - 1];
            W_ARR[W_MIN - 1] = W_ARR[W_I - 1];
            W_ARR[W_I - 1] = W_SWAP;
            
            W_I++;
        }
        
        for (W_H = 1; W_H <= W_LEN_ARR; W_H++) {
            System.out.println("POS: " + W_H + " SORTED: " + W_ARR[W_H - 1]);
        }
    }
}