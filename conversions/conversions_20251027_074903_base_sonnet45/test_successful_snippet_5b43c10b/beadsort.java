import java.util.Random;
import java.time.LocalTime;

public class beadsort {
    private static final int A_LIM = 9;
    private static char[][] row = new char[A_LIM][A_LIM];
    private static int[] array = new int[A_LIM];
    private static int r, r1, r2, pole, a;
    private static Random random;

    public static void main(String[] args) {
        long seed = LocalTime.now().toSecondOfDay();
        random = new Random(seed);
        
        for (a = 0; a < A_LIM; a++) {
            array[a] = (int)(random.nextDouble() * 10);
        }

        displayArray();
        System.out.println(" initial array");

        for (r = 0; r < A_LIM; r++) {
            for (int i = 0; i < A_LIM; i++) {
                row[r][i] = '.';
            }
            for (pole = 0; pole < array[r]; pole++) {
                row[r][pole] = 'o';
            }
        }
        System.out.println("\ninitial beads");
        displayBeads();

        for (pole = 0; pole < A_LIM; pole++) {
            r2 = A_LIM - 1;
            findOpening();
            r1 = r2 - 1;
            findBead();
            while (r1 >= 0) {
                row[r1][pole] = '.';
                row[r2][pole] = 'o';
                
                r2 = r2 - 1;
                findOpening();
                r1 = r2 - 1;
                findBead();
            }
        }
        System.out.println("\ndropped beads");
        displayBeads();

        for (r = 0; r < A_LIM; r++) {
            array[r] = 0;
            for (int i = 0; i < A_LIM; i++) {
                if (row[r][i] == 'o') {
                    array[r]++;
                } else {
                    break;
                }
            }
        }

        displayArray();
        System.out.println(" sorted array");
    }

    private static void findOpening() {
        while (r2 > 0 && row[r2][pole] != '.') {
            r2--;
        }
    }

    private static void findBead() {
        while (r1 >= 0 && row[r1][pole] != 'o') {
            r1--;
        }
    }

    private static void displayArray() {
        System.out.print(" ");
        for (a = 0; a < A_LIM; a++) {
            System.out.print(" " + array[a]);
        }
    }

    private static void displayBeads() {
        for (r = 0; r < A_LIM; r++) {
            System.out.println(new String(row[r]));
        }
    }
}