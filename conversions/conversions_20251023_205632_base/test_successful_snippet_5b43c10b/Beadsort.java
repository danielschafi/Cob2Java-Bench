import java.util.Random;

public class Beadsort {
    private static final int A_LIM = 9;
    private static final char NL = '\n';
    private static char[][] row = new char[A_LIM][A_LIM];
    private static int[] array = new int[A_LIM];

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());

        for (int a = 0; a < A_LIM; a++) {
            array[a] = random.nextInt(10);
        }

        displayArray();
        System.out.println(" initial array");

        for (int r = 0; r < A_LIM; r++) {
            for (int i = 0; i < A_LIM; i++) {
                row[r][i] = '.';
            }
            for (int pole = 0; pole < array[r]; pole++) {
                row[r][pole] = 'o';
            }
        }
        System.out.println(NL + "initial beads");
        displayBeads();

        for (int pole = 0; pole < A_LIM; pole++) {
            int r2 = A_LIM - 1;
            findOpening(pole, r2);
            int r1 = r2 - 1;
            findBead(pole, r1);

            while (r1 != 0) {
                row[r1][pole] = '.';
                row[r2][pole] = 'o';

                r2--;
                findOpening(pole, r2);
                r1 = r2 - 1;
                findBead(pole, r1);
            }
        }
        System.out.println(NL + "dropped beads");
        displayBeads();

        for (int r = 0; r < A_LIM; r++) {
            array[r] = 0;
            for (int i = 0; i < A_LIM && row[r][i] == 'o'; i++) {
                array[r]++;
            }
        }

        displayArray();
        System.out.println(" sorted array");
    }

    private static void findOpening(int pole, int r2) {
        while (r2 > 0 && row[r2][pole] != '.') {
            r2--;
        }
    }

    private static void findBead(int pole, int r1) {
        while (r1 > 0 && row[r1][pole] != 'o') {
            r1--;
        }
    }

    private static void displayArray() {
        System.out.println();
        for (int a = 0; a < A_LIM; a++) {
            System.out.print(array[a]);
        }
    }

    private static void displayBeads() {
        for (int r = 0; r < A_LIM; r++) {
            System.out.println(new String(row[r]));
        }
    }
}