import java.util.Random;
import java.util.Date;

public class BeadSort {
    private static final int ARRAY_SIZE = 9;
    private static char[][] row = new char[ARRAY_SIZE][ARRAY_SIZE];
    private static int[] array = new int[ARRAY_SIZE];
    private static int aLim = 9;
    private static Random random = new Random();
    
    public static void main(String[] args) {
        // Initialize random seed
        long secondsPastMidnight = System.currentTimeMillis() / 1000 % 86400;
        random.setSeed(secondsPastMidnight);
        
        // Initialize array with random values
        for (int a = 1; a <= aLim; a++) {
            array[a - 1] = (int) (random.nextDouble() * 10);
        }
        
        displayArray("initial array");
        
        // Create initial bead configuration
        for (int r = 1; r <= aLim; r++) {
            for (int i = 0; i < ARRAY_SIZE; i++) {
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
                
                r2 = r2 - 1;
                findOpening(pole, r2);
                r1 = r2 - 1;
                findBead(pole, r1);
            }
        }
        
        System.out.println("\ndropped beads");
        displayBeads();
        
        // Extract sorted array
        for (int r = 1; r <= aLim; r++) {
            array[r - 1] = 0;
            int count = 0;
            for (int i = 0; i < ARRAY_SIZE; i++) {
                if (row[r - 1][i] == 'o') {
                    count++;
                } else {
                    break;
                }
            }
            array[r - 1] = count;
        }
        
        displayArray("sorted array");
    }
    
    private static void displayArray(String label) {
        System.out.print("\n");
        for (int a = 1; a <= aLim; a++) {
            System.out.print(array[a - 1] + " ");
        }
        System.out.println("\n" + label);
    }
    
    private static void displayBeads() {
        for (int r = 1; r <= aLim; r++) {
            System.out.println(new String(row[r - 1]));
        }
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
}