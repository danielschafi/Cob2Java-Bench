public class IterativeTowersOfHanoi {
    private static int NUM_DISKS = 4;
    private static int N1;
    private static int N2;
    private static int FROM_POLE;
    private static int TO_POLE;
    private static int VIA_POLE;
    private static int FP_TMP;
    private static int TO_TMP;
    private static int P_TMP;
    private static int TMP_P;
    private static int I;
    private static int DIV;
    
    private static int[] DNUM = new int[4];
    private static int[][][] POLE = new int[4][11][11];
    
    public static void main(String[] args) {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + NUM_DISKS + " DISKS.");
        
        N1 = NUM_DISKS + 1;
        N2 = NUM_DISKS + 2;
        
        DNUM[1] = 1;
        DNUM[2] = N1;
        DNUM[3] = N1;
        
        POLE[1][N1] = N1;
        POLE[2][N1] = N1;
        POLE[3][N1] = N1;
        
        POLE[1][N2] = 1;
        POLE[2][N2] = 2;
        POLE[3][N2] = 3;
        
        I = 1;
        while (I != N1) {
            initPuzzle();
        }
        
        FROM_POLE = 1;
        DIV = NUM_DISKS / 2;
        int temp = 2 * DIV;
        
        if (DIV != NUM_DISKS) {
            initOdd();
        } else {
            initEven();
        }
        
        while (DNUM[3] > 1) {
            moveDisk();
        }
        
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }
    
    private static void initPuzzle() {
        POLE[1][I] = I;
        POLE[2][I] = 0;
        POLE[3][I] = 0;
        I++;
    }
    
    private static void initEven() {
        TO_POLE = 2;
        VIA_POLE = 3;
    }
    
    private static void initOdd() {
        TO_POLE = 3;
        VIA_POLE = 2;
    }
    
    private static void moveDisk() {
        FP_TMP = DNUM[FROM_POLE];
        I = POLE[FROM_POLE][FP_TMP];
        
        System.out.println("MOVE DISK FROM " + POLE[FROM_POLE][N2] + " TO " + POLE[TO_POLE][N2]);
        
        DNUM[FROM_POLE]++;
        TMP_P = VIA_POLE;
        DNUM[TO_POLE]--;
        TO_TMP = DNUM[TO_POLE];
        POLE[TO_POLE][TO_TMP] = I;
        
        DIV = I / 2;
        int temp = 2 * DIV;
        
        if (I != temp) {
            moveToVia();
        } else {
            moveFromVia();
        }
    }
    
    private static void moveToVia() {
        VIA_POLE = TO_POLE;
        FP_TMP = DNUM[FROM_POLE];
        P_TMP = DNUM[TMP_P];
        
        if (POLE[FROM_POLE][FP_TMP] > POLE[TMP_P][P_TMP]) {
            moveFromTo();
        } else {
            TO_POLE = TMP_P;
        }
    }
    
    private static void moveFromTo() {
        TO_POLE = FROM_POLE;
        FROM_POLE = TMP_P;
        FP_TMP = DNUM[FROM_POLE];
        P_TMP = DNUM[TMP_P];
    }
    
    private static void moveFromVia() {
        VIA_POLE = FROM_POLE;
        FROM_POLE = TMP_P;
    }
}