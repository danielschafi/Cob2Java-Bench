import java.math.BigInteger;

public class Euler {
    private static final int TABLE_LENGTH = 250;
    private boolean finishedSearching = false;
    private int a, b, c, d;
    private BigInteger abcd;
    private int fifthRootOffs;
    private int powerCounter;
    private int powerIndex;
    private Pretty pretty;
    private BigInteger[] fifthPowerTable = new BigInteger[TABLE_LENGTH];

    public static void main(String[] args) {
        Euler euler = new Euler();
        euler.mainParagraph();
    }

    private void mainParagraph() {
        finishedSearching = false;
        powersOfFiveTableInit();
        for (a = 1; a < TABLE_LENGTH && !finishedSearching; a++) {
            for (b = 1; b <= a && !finishedSearching; b++) {
                for (c = 1; c <= b && !finishedSearching; c++) {
                    for (d = 1; d <= c && !finishedSearching; d++) {
                        powerComputations();
                    }
                }
            }
        }
    }

    private void powerComputations() {
        abcd = BigInteger.ZERO;
        abcd = abcd.add(fifthPowerTable[a - 1]);
        abcd = abcd.add(fifthPowerTable[b - 1]);
        abcd = abcd.add(fifthPowerTable[c - 1]);
        abcd = abcd.add(fifthPowerTable[d - 1]);

        powerIndex = 0;
        while (powerIndex < TABLE_LENGTH && !fifthPowerTable[powerIndex].equals(abcd)) {
            powerIndex++;
        }

        if (powerIndex < TABLE_LENGTH) {
            fifthRootOffs = powerIndex + 1;
            pretty = new Pretty(a, b, c, d, fifthRootOffs);
            System.out.println(pretty);
            finishedSearching = true;
        }
    }

    private void powersOfFiveTableInit() {
        for (powerCounter = 1; powerCounter <= TABLE_LENGTH; powerCounter++) {
            fifthPowerTable[powerCounter - 1] = BigInteger.valueOf(powerCounter).pow(5);
        }
    }

    private static class Pretty {
        private int a, b, c, d, fifthRootOffs;

        public Pretty(int a, int b, int c, int d, int fifthRootOffs) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.fifthRootOffs = fifthRootOffs;
        }

        @Override
        public String toString() {
            return String.format("%3d^5 + %3d^5 + %3d^5 + %3d^5 = %3d^5.", a, b, c, d, fifthRootOffs);
        }
    }
}