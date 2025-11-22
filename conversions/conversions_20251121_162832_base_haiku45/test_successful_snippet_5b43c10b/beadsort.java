```java
import java.time.Instant;

public class beadsort {
    static String[] row = new String[10];
    static int r;
    static int r1;
    static int r2;
    static int pole;
    static int a_lim = 9;
    static int a;
    static int[] array = new int[10];
    static String NL = "\n";
    
    public static void main(String[] args) {
        startBeadsort();
    }
    
    static void startBeadsort() {
        // Initialize row array
        for (int i = 0; i < 10; i++) {
            row[i] = "";
        }
        
        // Seed random and generate initial array
        long seed = Instant.now().getEpochSecond() % 86400;
        java.util.Random rand = new java.util.Random(seed);
        
        for (a = 1; a <= a_lim; a++) {
            array[a] = (int)(rand.nextDouble() * 10);
        }
        
        displayArray();
        System.out.print(" initial array");
        System.out.println();
        
        // Create initial beads representation
        for (r = 1; r <= a_lim; r++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                sb.append('.');
            }
            row[r] = sb.toString();
            
            for (pole = 1; pole <= array[r]; pole++) {
                row[r] = row[r].substring(0, pole - 1) + 'o' + row[r].substring(pole);
            }
        }
        
        System.out.println(NL + "initial beads");
        displayBeads();
        
        // Drop beads
        for (pole = 1; pole <= a_lim; pole++) {
            r2 = a_lim;
            findOpening();
            r1 = r2 - 1;
            findBead();
            
            while (r1 != 0) {
                // Move bead from r1 to r2
                row[r1] = row[r1].substring(0, pole - 1) + '.' + row[r1].substring(pole);
                row[r2] = row[r2].substring(0, pole - 1) + 'o' + row[r2].substring(pole);
                
                r2 = r2 - 1;
                findOpening();
                r1 = r2 - 1;
                findBead();
            }
        }
        
        System.out.println(NL + "dropped beads");
        displayBeads();
        
        // Count beads to get sorted array
        for (r = 1; r <= a_lim; r++) {
            array[r] = 0;
            for (int i = 0; i < row[r].length(); i++) {
                if (row[r].charAt(i) == 'o') {
                    array[r]++;
                } else {
                    break;
                }
            }
        }
        
        displayArray();
        System.out.print(" sorted array");
        System.out.println();
    }
    
    static void findOpening() {
        while (r2 > 1 && row[r2].charAt(pole - 1) != '.') {
            r2--;
        }
    }
    
    static void findBead() {
        while (r1 > 0 && row[r1].charAt(pole - 1) != 'o') {
            r1--;
        }
    }
    
    static void displayArray() {
        System.out.print(" ");
        for (a = 1; a <= a_lim; a++) {
            System.out.print(" " + array[a]);
        }
    }
    
    static void displayBeads() {
        for (r = 1; r <= a_lim; r++) {
            System.out.println(row[r]);
        }
    }
}