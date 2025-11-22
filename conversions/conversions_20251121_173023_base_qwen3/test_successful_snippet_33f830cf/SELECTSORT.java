import java.util.Random;

public class SELECTSORT {
    private static final int W_LEN_ARR = 30;
    private static final int W_MIN_NUMBER = 1;
    private static final int W_MAX_NUMBER = 200;
    
    private static int[] w_arr = new int[W_LEN_ARR];
    private static int w_r = 1;
    private static int w_i = 1;
    private static int w_j = 1;
    private static int w_h = 1;
    private static int w_min = 0;
    private static int w_swap = 0;
    private static int w_ran_number = 0;
    
    public static void main(String[] args) {
        generateRandomNum();
        sortingArray();
    }
    
    public static void generateRandomNum() {
        Random random = new Random();
        
        for (w_r = 1; w_r <= W_LEN_ARR; w_r++) {
            w_ran_number = random.nextInt(W_MAX_NUMBER - W_MIN_NUMBER + 1) + W_MIN_NUMBER;
            w_arr[w_r - 1] = w_ran_number;
            System.out.println("POS: " + w_r + " RANDOM NUMBER: " + w_arr[w_r - 1]);
        }
    }
    
    public static void sortingArray() {
        while (w_i <= W_LEN_ARR) {
            w_min = W_LEN_ARR;
            w_j = w_i;
            
            while (w_j <= W_LEN_ARR) {
                if (w_arr[w_min - 1] > w_arr[w_j - 1]) {
                    w_min = w_j;
                }
                w_j++;
            }
            
            w_swap = w_arr[w_min - 1];
            w_arr[w_min - 1] = w_arr[w_i - 1];
            w_arr[w_i - 1] = w_swap;
            
            w_i++;
        }
        
        for (w_h = 1; w_h <= W_LEN_ARR; w_h++) {
            System.out.println("POS: " + w_h + " SORTED: " + w_arr[w_h - 1]);
        }
    }
}