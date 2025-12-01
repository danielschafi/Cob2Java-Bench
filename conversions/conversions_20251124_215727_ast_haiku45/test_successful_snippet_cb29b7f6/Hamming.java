```java
public class Hamming {
    private long popcountIn;
    private int popcountOut;
    private long curPower3 = 1;
    private int curEvilNum = 0;
    private int curOdiousNum = 0;
    private int lineIndex = 1;

    public static void main(String[] args) {
        Hamming hamming = new Hamming();
        hamming.run();
    }

    public void run() {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private void makeLine() {
        String linenoStr = String.format("%2d", lineIndex).replace(' ', ' ');
        popcountIn = curPower3;
        findPopcount();
        String outPow3Str = String.format("%2d", popcountOut).replace(' ', ' ');
        findEvil();
        String outEvilStr = String.format("%2d", curEvilNum).replace(' ', ' ');
        findOdious();
        String outOdiousStr = String.format("%2d", curOdiousNum).replace(' ', ' ');
        
        String output = linenoStr + ".  " + outPow3Str + "    " + outEvilStr + "    " + outOdiousStr;
        System.out.println(output);
        
        curPower3 *= 3;
        curEvilNum++;
        curOdiousNum++;
        lineIndex++;
    }

    private void findEvil() {
        popcountIn = curEvilNum;
        findPopcount();
        while (!isEvil(popcountOut)) {
            curEvilNum++;
            popcountIn = curEvilNum;
            findPopcount();
        }
    }

    private void findOdious() {
        popcountIn = curOdiousNum;
        findPopcount();
        while (!isOdious(popcountOut)) {
            curOdiousNum++;
            popcountIn = curOdiousNum;
            findPopcount();
        }
    }

    private void findPopcount() {
        popcountOut = 0;
        while (popcountIn != 0) {
            processBit();
        }
    }

    private void processBit() {
        long remainder = popcountIn % 2;
        popcountIn = popcountIn / 2;
        if (remainder == 1) {
            popcountOut++;
        }
    }

    private boolean isEvil(int num) {
        return num % 2 == 0;
    }

    private boolean isOdious(int num) {
        return num % 2 == 1;
    }
}