import java.util.Arrays;

public class IterativeTowersOfHanoi {
    private static final int NUM_DISKS = 4;
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
    private static int[][] STACKNUMS = new int[3][1];
    private static int[][] GAMESET = new int[3][10];

    public static void main(String[] args) {
        HANOI();
    }

    private static void HANOI() {
        System.out.println("TOWERS OF HANOI PUZZLE WITH " + NUM_DISKS + " DISKS.");
        N1 = NUM_DISKS + 1;
        N2 = NUM_DISKS + 2;
        STACKNUMS[0][0] = 1;
        STACKNUMS[1][0] = N1;
        STACKNUMS[2][0] = N1;
        Arrays.fill(GAMESET[0], N1);
        Arrays.fill(GAMESET[1], N1);
        Arrays.fill(GAMESET[2], N1);
        GAMESET[0][N2 - 1] = 1;
        GAMESET[1][N2 - 1] = 2;
        GAMESET[2][N2 - 1] = 3;
        I = 1;
        while (I < N1) {
            INITPUZZLE();
        }
        FROM_POLE = 1;
        DIV = NUM_DISKS / 2;
        if (DIV * 2 != NUM_DISKS) {
            INITODD();
        } else {
            INITEVEN();
        }
        while (STACKNUMS[2][0] > 1) {
            MOVEDISK();
        }
        System.out.println("TOWERS OF HANOI PUZZLE COMPLETED!");
    }

    private static void INITPUZZLE() {
        GAMESET[0][I - 1] = I;
        GAMESET[1][I - 1] = 0;
        GAMESET[2][I - 1] = 0;
        I++;
    }

    private static void INITEVEN() {
        TO_POLE = 2;
        VIA_POLE = 3;
    }

    private static void INITODD() {
        TO_POLE = 3;
        VIA_POLE = 2;
    }

    private static void MOVEDISK() {
        FP_TMP = STACKNUMS[FROM_POLE - 1][0];
        I = GAMESET[FROM_POLE - 1][FP_TMP - 1];
        System.out.println("MOVE DISK FROM " + GAMESET[FROM_POLE - 1][N2 - 1] + " TO " + GAMESET[TO_POLE - 1][N2 - 1]);
        STACKNUMS[FROM_POLE - 1][0]++;
        TMP_P = VIA_POLE;
        STACKNUMS[TO_POLE - 1][0]--;
        TO_TMP = STACKNUMS[TO_POLE - 1][0];
        GAMESET[TO_POLE - 1][TO_TMP - 1] = I;
        DIV = I / 2;
        if (I != DIV * 2) {
            MOVETOVIA();
        } else {
            MOVEFROMVIA();
        }
    }

    private static void MOVETOVIA() {
        VIA_POLE = TO_POLE;
        FP_TMP = STACKNUMS[FROM_POLE - 1][0];
        P_TMP = STACKNUMS[TMP_P - 1][0];
        if (GAMESET[FROM_POLE - 1][FP_TMP - 1] > GAMESET[TMP_P - 1][P_TMP - 1]) {
            MOVEFROMTO();
        } else {
            TO_POLE = TMP_P;
        }
    }

    private static void MOVEFROMTO() {
        TO_POLE = FROM_POLE;
        FROM_POLE = TMP_P;
        FP_TMP = STACKNUMS[FROM_POLE - 1][0];
        P_TMP = STACKNUMS[TMP_P - 1][0];
    }

    private static void MOVEFROMVIA() {
        VIA_POLE = FROM_POLE;
        FROM_POLE = TMP_P;
    }
}