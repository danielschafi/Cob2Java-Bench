public class HAMMING {
    private static long popcountIn;
    private static long popcountRest;
    private static int popcountOut;
    private static boolean bitIsSet;
    private static long curPower3 = 1;
    private static int curEvilNum = 0;
    private static int curOdiousNum = 0;
    private static int lineIndex = 1;

    public static void main(String[] args) {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private static void makeLine() {
        int lineno = lineIndex;
        popcountIn = curPower3;
        findPopcount();
        int outPow3 = popcountOut;
        findEvil();
        int outEvil = curEvilNum;
        findOdious();
        int outOdious = curOdiousNum;
        System.out.printf("%3d.   %2d     %2d     %2d%n", lineno, outPow3, outEvil, outOdious);
        curPower3 *= 3;
        curEvilNum++;
        curOdiousNum++;
        lineIndex++;
    }

    private static void findEvil() {
        curEvilNum = curEvilNum;
        findPopcount();
        if (!isEvil()) {
            curEvilNum++;
            findEvil();
        }
    }

    private static void findOdious() {
        curOdiousNum = curOdiousNum;
        findPopcount();
        if (!isOdious()) {
            curOdiousNum++;
            findOdious();
        }
    }

    private static void findPopcount() {
        popcountOut = 0;
        while (popcountIn != 0) {
            processBit();
        }
    }

    private static void processBit() {
        long temp = popcountIn / 2;
        popcountRest = temp;
        bitIsSet = (popcountIn % 2 == 1);
        if (bitIsSet) {
            popcountOut++;
        }
        popcountIn = popcountRest;
    }

    private static boolean isEvil() {
        return popcountOut % 2 == 0;
    }

    private static boolean isOdious() {
        return popcountOut % 2 == 1;
    }
}