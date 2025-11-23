public class IterativeTowersOfHanoi {
    private static int numDisks = 4;
    private static int n1;
    private static int n2;
    private static int fromPole;
    private static int toPole;
    private static int viaPole;
    private static int fpTmp;
    private static int toTmp;
    private static int pTmp;
    private static int tmpP;
    private static int i;
    private static int div;
    
    private static int[] dnum = new int[4]; // 1-indexed
    private static int[][] pole = new int[4][11]; // 1-indexed
    
    public static void main(String[] args) {
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
        while (i != n1) {
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
    
    private static void initPuzzle() {
        pole[1][i] = i;
        pole[2][i] = 0;
        pole[3][i] = 0;
        i++;
    }
    
    private static void initEven() {
        toPole = 2;
        viaPole = 3;
    }
    
    private static void initOdd() {
        toPole = 3;
        viaPole = 2;
    }
    
    private static void moveDisk() {
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
            move_to_via();
        } else {
            move_from_via();
        }
    }
    
    private static void move_to_via() {
        viaPole = toPole;
        fpTmp = dnum[fromPole];
        pTmp = dnum[tmpP];
        
        if (pole[fromPole][fpTmp] > pole[tmpP][pTmp]) {
            move_from_to();
        } else {
            toPole = tmpP;
        }
    }
    
    private static void move_from_to() {
        toPole = fromPole;
        fromPole = tmpP;
        fpTmp = dnum[fromPole];
        pTmp = dnum[tmpP];
    }
    
    private static void move_from_via() {
        viaPole = fromPole;
        fromPole = tmpP;
    }
}