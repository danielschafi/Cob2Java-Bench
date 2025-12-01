public class Euler {
    private static final int TABLE_LENGTH = 250;
    private int searchingFlag = 0;
    private int a, b, c, d;
    private long abcd;
    private int fifthRootOffs;
    private int powerCounter;
    private long[] fifthPowerTable = new long[TABLE_LENGTH + 1];
    
    public static void main(String[] args) {
        new Euler().run();
    }
    
    private void run() {
        searchingFlag = 0;
        powersOfFiveTableInit();
        
        for (a = 1; a < TABLE_LENGTH; a++) {
            for (b = 1; b <= a; b++) {
                for (c = 1; c <= b; c++) {
                    for (d = 1; d <= c; d++) {
                        if (searchingFlag == 1) {
                            System.exit(0);
                        }
                        powerComputations();
                    }
                }
            }
        }
    }
    
    private void powerComputations() {
        abcd = 0;
        abcd = fifthPowerTable[a] + fifthPowerTable[b] + fifthPowerTable[c] + fifthPowerTable[d];
        
        for (int powerIndex = 1; powerIndex < TABLE_LENGTH; powerIndex++) {
            if (fifthPowerTable[powerIndex] == abcd) {
                fifthRootOffs = powerIndex;
                String output = String.format("%3d^5 + %3d^5 + %3d^5 + %3d^5 = %3d^5.", a, b, c, d, fifthRootOffs);
                System.out.println(output);
                searchingFlag = 1;
                return;
            }
        }
    }
    
    private void powersOfFiveTableInit() {
        for (powerCounter = 1; powerCounter < TABLE_LENGTH; powerCounter++) {
            fifthPowerTable[powerCounter] = (long) powerCounter * powerCounter * powerCounter * powerCounter * powerCounter;
        }
    }
}