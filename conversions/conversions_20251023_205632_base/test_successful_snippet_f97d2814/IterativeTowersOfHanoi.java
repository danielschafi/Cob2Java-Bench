import java.util.Arrays;

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
    private static int[] numSet = new int[3];
    private static int[][] poles = new int[3][10];

    public static void main(String[] args) {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + NUM_DISKS + " DISKS.");
        n1 = NUM_DISKS + 1;
        n2 = NUM_DISKS + 2;
        numSet[0] = 1;
        numSet[1] = n1;
        numSet[2] = n1;
        Arrays.fill(poles[0], n1);
        Arrays.fill(poles[1], n1);
        Arrays.fill(poles[2], n1);
        poles[0][n1] = 1;
        poles[1][n1] = 2;
        poles[2][n1] = 3;
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
        while (numSet[2] > 1) {
            moveDisk();
        }
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }

    private static void initPuzzle() {
        poles[0][i] = i;
        poles[1][i] = 0;
        poles[2][i] = 0;
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
        fpTmp = numSet[fromPole];
        i = poles[fromPole][fpTmp];
        System.out.println("MOVE DISK FROM " + poles[fromPole][n2] + " TO " + poles[toPole][n2]);
        numSet[fromPole]++;
        tmpP = viaPole;
        toTmp = numSet[toPole] - 1;
        pTmp = numSet[tmpP];
        if (poles[fromPole][fpTmp] > poles[tmpP][pTmp]) {
            moveFromTo();
        } else {
            toPole = tmpP;
        }
    }

    private static void moveFromTo() {
        tmpP = toPole;
        toPole = fromPole;
        fromPole = tmpP;
        pTmp = numSet[fromPole];
        fpTmp = numSet[tmpP];
        if (poles[fromPole][fpTmp] > poles[tmpP][pTmp]) {
            moveFromTo();
        } else {
            tmpP = toPole;
            toPole = fromPole;
            fromPole = tmpP;
        }
    }

    private static void moveFromVia() {
        tmpP = viaPole;
        viaPole = fromPole;
        fromPole = tmpP;
    }
}