import java.util.Random;

public class BeadSort {
    private static final int SIZE = 9;
    private static char[][] row = new char[SIZE][SIZE];
    private static int[] array = new int[SIZE];
    private static int aLim = 9;
    private static Random random = new Random();

    public static void main(String[] args) {
        // Initialize random seed based on current time
        long currentTime = System.currentTimeMillis();
        random.setSeed(currentTime);

        // Fill array with random values
        for (int a = 1; a <= aLim; a++) {
            array[a - 1] = random.nextInt(10);
        }

        displayArray();
        System.out.println("initial array");

        // Initialize beads
        for (int r = 1; r <= aLim; r++) {
            for (int i = 0; i < SIZE; i++) {
                row[r - 1][i] = '.';
            }
            for (int pole = 1; pole <= array[r - 1]; pole++) {
                row[r - 1][pole - 1] = 'o';
            }
        }

        System.out.println("\ninitial beads");
        displayBeads();

        // Drop beads
        for (int pole = 1; pole <= aLim; pole++) {
            int r2 = aLim;
            findOpening(pole, r2);
            int r1 = r2 - 1;
            findBead(pole, r1);

            while (r1 != 0) {
                row[r1 - 1][pole - 1] = '.';
                row[r2 - 1][pole - 1] = 'o';

                r2--;
                findOpening(pole, r2);
                r1 = r2 - 1;
                findBead(pole, r1);
            }
        }

        System.out.println("\ndropped beads");
        displayBeads();

        // Read sorted values
        for (int r = 1; r <= aLim; r++) {
            array[r - 1] = 0;
            int count = 0;
            for (int i = 0; i < SIZE; i++) {
                if (row[r - 1][i] == 'o') {
                    count++;
                } else {
                    break;
                }
            }
            array[r - 1] = count;
        }

        displayArray();
        System.out.println("sorted array");
    }

    private static void findOpening(int pole, int r2) {
        while (r2 > 1 && row[r2 - 1][pole - 1] != '.') {
            r2--;
        }
    }

    private static void findBead(int pole, int r1) {
        while (r1 > 0 && row[r1 - 1][pole - 1] != 'o') {
            r1--;
        }
    }

    private static void displayArray() {
        System.out.print(" ");
        for (int a = 1; a <= aLim; a++) {
            System.out.printf("%d ", array[a - 1]);
        }
    }

    private static void displayBeads() {
        for (int r = 1; r <= aLim; r++) {
            System.out.println(new String(row[r - 1]));
        }
    }
}