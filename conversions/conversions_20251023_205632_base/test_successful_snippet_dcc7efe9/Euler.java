import java.util.Arrays;

public class Euler {
    private static final int TABLE_LENGTH = 250;
    private static boolean finishedSearching = false;
    private static final Calc calc = new Calc();
    private static final Pretty pretty = new Pretty();
    private static final long[] fifthPowerTable = new long[TABLE_LENGTH];

    public static void main(String[] args) {
        powersOfFiveTableInit();
        for (calc.a = 1; calc.a < TABLE_LENGTH; calc.a++) {
            for (calc.b = 1; calc.b <= calc.a; calc.b++) {
                for (calc.c = 1; calc.c <= calc.b; calc.c++) {
                    for (calc.d = 1; calc.d <= calc.c; calc.d++) {
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
        calc.abcd = 0;
        calc.abcd += fifthPowerTable[calc.a - 1];
        calc.abcd += fifthPowerTable[calc.b - 1];
        calc.abcd += fifthPowerTable[calc.c - 1];
        calc.abcd += fifthPowerTable[calc.d - 1];

        for (int powerIndex = 0; powerIndex < TABLE_LENGTH; powerIndex++) {
            if (fifthPowerTable[powerIndex] == calc.abcd) {
                calc.fifthRootOffs = powerIndex + 1;
                pretty.a = calc.a;
                pretty.b = calc.b;
                pretty.c = calc.c;
                pretty.d = calc.d;
                pretty.fifthRootOffs = calc.fifthRootOffs;
                System.out.printf("%03d^5 + %03d^5 + %03d^5 + %03d^5 = %03d^5.%n", pretty.a, pretty.b, pretty.c, pretty.d, pretty.fifthRootOffs);
                finishedSearching = true;
                return;
            }
        }
    }

    private static void powersOfFiveTableInit() {
        for (calc.powerCounter = 1; calc.powerCounter <= TABLE_LENGTH; calc.powerCounter++) {
            fifthPowerTable[calc.powerCounter - 1] = (long) Math.pow(calc.powerCounter, 5);
        }
    }

    private static class Calc {
        int a;
        int b;
        int c;
        int d;
        long abcd;
        int fifthRootOffs;
        int powerCounter;
    }

    private static class Pretty {
        int a;
        int b;
        int c;
        int d;
        int fifthRootOffs;
    }
}