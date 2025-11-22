import java.util.Random;

public class Beadsort {
    private static final int A_LIM = 9;
    private static final char NL = '\n';
    private static final char BEAD = 'o';
    private static final char EMPTY = '.';
    private int[] array = new int[A_LIM];
    private char[][] row = new char[A_LIM][A_LIM];
    private Random random = new Random();

    public static void main(String[] args) {
        Beadsort beadsort = new Beadsort();
        beadsort.startBeadsort();
    }

    public void startBeadsort() {
        for (int a = 0; a < A_LIM; a++) {
            array[a] = random.nextInt(10);
        }

        displayArray();
        System.out.println(" initial array");

        for (int r = 0; r < A_LIM; r++) {
            for (int i = 0; i < A_LIM; i++) {
                row[r][i] = EMPTY;
            }
            for (int pole = 0; pole < array[r]; pole++) {
                row[r][pole] = BEAD;
            }
        }
        System.out.println(NL + " initial beads");
        displayBeads();

        for (int pole = 0; pole < A_LIM; pole++) {
            int r2 = A_LIM - 1;
            findOpening(pole, r2);
            int r1 = r2 - 1;
            findBead(pole, r1);
            while (r1 >= 0) {
                row[r1][pole] = EMPTY;
                row[r2][pole] = BEAD;
                r2--;
                findOpening(pole, r2);
                r1 = r2 - 1;
                findBead(pole, r1);
            }
        }
        System.out.println(NL + " dropped beads");
        displayBeads();

        for (int r = 0; r < A_LIM; r++) {
            array[r] = 0;
            for (int i = 0; i < A_LIM && row[r][i] == BEAD; i++) {
                array[r]++;
            }
        }

        displayArray();
        System.out.println(" sorted array");
    }

    private void findOpening(int pole, int r2) {
        while (r2 >= 0 && row[r2][pole] != EMPTY) {
            r2--;
        }
    }

    private void findBead(int pole, int r1) {
        while (r1 >= 0 && row[r1][pole] != BEAD) {
            r1--;
        }
    }

    private void displayArray() {
        System.out.println();
        for (int a = 0; a < A_LIM; a++) {
            System.out.print(array[a]);
        }
    }

    private void displayBeads() {
        for (int r = 0; r < A_LIM; r++) {
            System.out.println(new String(row[r]));
        }
    }
}