public class IterativeTowersOfHanoi {
    private static final int MAX_DISKS = 10;
    private static final int MAX_POLES = 3;
    
    private static int numDisks = 4;
    private static int n1, n2;
    private static int fromPole, toPole, viaPole;
    private static int fpTmp, toTmp, pTmp, tmpP;
    private static int i, div;
    
    private static int[] dnum = new int[4];
    private static int[][] pole = new int[MAX_POLES + 1][MAX_DISKS + 1];
    
    public static void main(String[] args) {
        hanoi();
    }
    
    public static void hanoi() {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + numDisks + " DISKS.");
        
        n1 = numDisks + 1;
        n2 = numDisks + 2;
        
        dnum[1] = 1;
        dnum[2] = n1;
        dnum[3] = n1;
        
        pole[1][n1] = n1;
        pole[2][n1] = n1;
        pole[3][n1] = n1;
        
        pole[1][n2] = 1;
        pole[2][n2] = 2;
        pole[3][n2] = 3;
        
        i = 1;
        while (i < n1) {
            initPuzzle();
        }
        
        fromPole = 1;
        
        div = numDisks / 2;
        div *= 2;
        
        if (div != numDisks) {
            initOdd();
        } else {
            initEven();
        }
        
        while (dnum[3] > 1) {
            moveDisk();
        }
        
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }
    
    public static void initPuzzle() {
        pole[1][i] = i;
        pole[2][i] = 0;
        pole[3][i] = 0;
        i++;
    }
    
    public static void initEven() {
        toPole = 2;
        viaPole = 3;
    }
    
    public static void initOdd() {
        toPole = 3;
        viaPole = 2;
    }
    
    public static void moveDisk() {
        fpTmp = dnum[fromPole];
        i = pole[fromPole][fpTmp];
        
        System.out.println("MOVE DISK FROM " + pole[fromPole][n2] + " TO " + pole[toPole][n2]);
        
        dnum[fromPole]++;
        
        tmpP = viaPole;
        dnum[toPole]--;
        toTmp = dnum[toPole];
        pole[toPole][toTmp] = i;
        
        div = i / 2;
        div *= 2;
        
        if (i != div) {
            moveToVia();
        } else {
            moveFromVia();
        }
    }
    
    public static void moveToVia() {
        viaPole = toPole;
        fpTmp = dnum[fromPole];
        pTmp = tmpP;
        
        if (pole[fromPole][fpTmp] > pole[tmpP][pTmp]) {
            moveFromTo();
        } else {
            toPole = tmpP;
        }
    }
    
    public static void moveFromTo() {
        toPole = fromPole;
        fromPole = tmpP;
        fpTmp = dnum[fromPole];
        pTmp = tmpP;
    }
    
    public static void moveFromVia() {
        viaPole = fromPole;
        fromPole = tmpP;
    }
}