public class Euler {
    private static final int TABLE_LENGTH = 250;
    private static boolean finishedSearching = false;
    private static int[] fifthPowerTable = new int[TABLE_LENGTH];
    private static int a, b, c, d;
    private static long abcd;
    private static int fifthRootOffs;
    private static int powerCounter;

    public static void main(String[] args) {
        finishedSearching = false;
        powersOfFiveTableInit();
        
        for (a = 1; a < TABLE_LENGTH; a++) {
            for (b = 1; b < a; b++) {
                for (c = 1; c < b; c++) {
                    for (d = 1; d < c; d++) {
                        if (finishedSearching) {
                            return;
                        }
                        powerComputations();
                    }
                }
            }
        }
    }

    private static void powerComputations() {
        abcd = 0;
        abcd += fifthPowerTable[a - 1];
        abcd += fifthPowerTable[b - 1];
        abcd += fifthPowerTable[c - 1];
        abcd += fifthPowerTable[d - 1];

        int powerIndex = 0;
        for (int i = 0; i < TABLE_LENGTH; i++) {
            if (fifthPowerTable[i] == abcd) {
                powerIndex = i + 1;
                fifthRootOffs = powerIndex;
                System.out.println(String.format("%3d^5 + %3d^5 + %3d^5 + %3d^5 = %3d^5", a, b, c, d, fifthRootOffs));
                finishedSearching = true;
                break;
            }
        }
    }

    private static void powersOfFiveTableInit() {
        for (powerCounter = 1; powerCounter <= TABLE_LENGTH; powerCounter++) {
            fifthPowerTable[powerCounter - 1] = powerCounter * powerCounter * powerCounter * powerCounter * powerCounter;
        }
    }
}