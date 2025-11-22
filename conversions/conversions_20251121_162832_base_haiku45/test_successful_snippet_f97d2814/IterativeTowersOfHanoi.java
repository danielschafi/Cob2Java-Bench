public class IterativeTowersOfHanoi {
    private static final int NUM_DISKS = 4;
    private int n1;
    private int n2;
    private int fromPole;
    private int toPole;
    private int viaPole;
    private int fpTmp;
    private int toTmp;
    private int pTmp;
    private int tmpP;
    private int i;
    private int div;
    
    private int[] dnum = new int[4];
    private int[][] pole = new int[4][12];
    
    public static void main(String[] args) {
        IterativeTowersOfHanoi hanoi = new IterativeTowersOfHanoi();
        hanoi.run();
    }
    
    public void run() {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + NUM_DISKS + " DISKS.");
        
        n1 = NUM_DISKS + 1;
        n2 = NUM_DISKS + 2;
        
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
            pole[1][i] = i;
            pole[2][i] = 0;
            pole[3][i] = 0;
            i++;
        }
        
        fromPole = 1;
        div = NUM_DISKS / 2;
        int temp = 2 * div;
        
        if (temp != NUM_DISKS) {
            toPole = 3;
            viaPole = 2;
        } else {
            toPole = 2;
            viaPole = 3;
        }
        
        while (dnum[3] > 1) {
            moveDisk();
        }
        
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }
    
    private void moveDisk() {
        fpTmp = dnum[fromPole];
        i = pole[fromPole][fpTmp];
        
        System.out.println("MOVE DISK FROM " + pole[fromPole][n2] + " TO " + pole[toPole][n2]);
        
        dnum[fromPole]++;
        tmpP = viaPole;
        dnum[toPole]--;
        toTmp = dnum[toPole];
        pole[toPole][toTmp] = i;
        
        div = i / 2;
        int temp = 2 * div;
        
        if (i != temp) {
            moveToVia();
        } else {
            moveFromVia();
        }
    }
    
    private void moveToVia() {
        viaPole = toPole;
        fpTmp = dnum[fromPole];
        pTmp = dnum[tmpP];
        
        if (pole[fromPole][fpTmp] > pole[tmpP][pTmp]) {
            moveFromTo();
        } else {
            toPole = tmpP;
        }
    }
    
    private void moveFromTo() {
        toPole = fromPole;
        fromPole = tmpP;
        fpTmp = dnum[fromPole];
        pTmp = dnum[tmpP];
    }
    
    private void moveFromVia() {
        viaPole = fromPole;
        fromPole = tmpP;
    }
}