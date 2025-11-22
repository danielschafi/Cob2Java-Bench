import java.util.Arrays;

public class Euler {
    private static final int TABLE_LENGTH = 250;
    private static boolean finishedSearching = false;
    private static long[] fifthPowerTable = new long[TABLE_LENGTH];
    
    public static void main(String[] args) {
        initializePowersOfFive();
        
        for (int a = 1; a < TABLE_LENGTH; a++) {
            for (int b = 1; b < a; b++) {
                for (int c = 1; c < b; c++) {
                    for (int d = 1; d < c; d++) {
                        if (finishedSearching) {
                            return;
                        }
                        
                        computePowers(a, b, c, d);
                    }
                }
            }
        }
    }
    
    private static void computePowers(int a, int b, int c, int d) {
        long abcd = fifthPowerTable[a - 1] + fifthPowerTable[b - 1] + 
                   fifthPowerTable[c - 1] + fifthPowerTable[d - 1];
        
        for (int i = 0; i < TABLE_LENGTH; i++) {
            if (fifthPowerTable[i] == abcd) {
                System.out.printf("%3d^5 + %3d^5 + %3d^5 + %3d^5 = %3d^5%n", 
                                a, b, c, d, i + 1);
                finishedSearching = true;
                return;
            }
        }
    }
    
    private static void initializePowersOfFive() {
        for (int i = 1; i <= TABLE_LENGTH; i++) {
            fifthPowerTable[i - 1] = (long) i * i * i * i * i;
        }
    }
}