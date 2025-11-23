public class Hamming {
    private static long popcountIn;
    private static long popcountRest;
    private static int popcountOut;
    private static boolean bitIsSet;
    private static boolean evil;
    private static boolean odious;
    
    private static long curPower3 = 1;
    private static int curEvilNum = 0;
    private static int curOdiousNum = 0;
    private static int lineIndex = 1;
    
    private static String lineno;
    private static String outPow3;
    private static String outEvil;
    private static String outOdious;

    public static void main(String[] args) {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private static void makeLine() {
        lineno = String.format("%2d", lineIndex);
        popcountIn = curPower3;
        findPopcount();
        outPow3 = String.format("%2d", popcountOut);
        findEvil();
        outEvil = String.format("%2d", curEvilNum);
        findOdious();
        outOdious = String.format("%2d", curOdiousNum);
        System.out.printf("%s.%s%s%s%s%s%s%n", lineno, "  ", outPow3, "    ", outEvil, "    ", outOdious);
        curPower3 *= 3;
        curEvilNum++;
        curOdiousNum++;
        lineIndex++;
    }

    private static void findEvil() {
        curEvilNum = curEvilNum;
        popcountIn = curEvilNum;
        findPopcount();
        if (!evil) {
            do {
                curEvilNum++;
                popcountIn = curEvilNum;
                findPopcount();
            } while (!evil);
        }
    }

    private static void findOdious() {
        curOdiousNum = curOdiousNum;
        popcountIn = curOdiousNum;
        findPopcount();
        if (!odious) {
            do {
                curOdiousNum++;
                popcountIn = curOdiousNum;
                findPopcount();
            } while (!odious);
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
}