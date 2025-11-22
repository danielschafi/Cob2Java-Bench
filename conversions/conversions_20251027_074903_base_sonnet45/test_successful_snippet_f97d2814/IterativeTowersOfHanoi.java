public class IterativeTowersOfHanoi {
    private static final int NUM_DISKS = 4;
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
    private static int[] dnum = new int[3];
    private static int[][] pole = new int[3][10];

    public static void main(String[] args) {
        hanoi();
    }

    private static void hanoi() {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + NUM_DISKS + " DISKS.");
        n1 = NUM_DISKS + 1;
        n2 = NUM_DISKS + 2;
        dnum[0] = 1;
        dnum[1] = n1;
        dnum[2] = n1;
        pole[0][n1 - 1] = n1;
        pole[1][n1 - 1] = n1;
        pole[2][n1 - 1] = n1;
        pole[0][n2 - 1] = 1;
        pole[1][n2 - 1] = 2;
        pole[2][n2 - 1] = 3;
        i = 1;
        while (i < n1) {
            initPuzzle();
        }
        fromPole = 1;
        div = NUM_DISKS / 2;
        if (div * 2 != NUM_DISKS) {
            initOdd();
        } else {
            initEven();
        }
        while (dnum[2] > 1) {
            moveDisk();
        }
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }

    private static void initPuzzle() {
        pole[0][i - 1] = i;
        pole[1][i - 1] = 0;
        pole[2][i - 1] = 0;
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
        fpTmp = dnum[fromPole - 1];
        i = pole[fromPole - 1][fpTmp - 1];
        System.out.println("MOVE DISK FROM " + pole[fromPole - 1][n2 - 1] + " TO " + pole[toPole - 1][n2 - 1]);
        dnum[fromPole - 1]++;
        tmpP = viaPole;
        dnum[toPole - 1]--;
        toTmp = dnum[toPole - 1];
        pole[toPole - 1][toTmp - 1] = i;
        div = i / 2;
        if (i != div * 2) {
            moveToVia();
        } else {
            moveFromVia();
        }
    }

    private static void moveToVia() {
        viaPole = toPole;
        fpTmp = dnum[fromPole - 1];
        pTmp = dnum[tmpP - 1];
        if (pole[fromPole - 1][fpTmp - 1] > pole[tmpP - 1][pTmp - 1]) {
            moveFromTo();
        } else {
            toPole = tmpP;
        }
    }

    private static void moveFromTo() {
        toPole = fromPole;
        fromPole = tmpP;
        fpTmp = dnum[fromPole - 1];
        pTmp = dnum[tmpP - 1];
    }

    private static void moveFromVia() {
        viaPole = fromPole;
        fromPole = tmpP;
    }
}