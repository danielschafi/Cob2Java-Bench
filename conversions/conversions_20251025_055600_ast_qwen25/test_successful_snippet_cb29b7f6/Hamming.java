import java.text.DecimalFormat;

public class Hamming {
    private double popcountIn;
    private int popcountOut;
    private int curPower3 = 1;
    private int curEvilNum = 0;
    private int curOdiousNum = 0;
    private int lineIndex = 1;

    public static void main(String[] args) {
        Hamming hamming = new Hamming();
        hamming.begin();
    }

    private void begin() {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private void makeLine() {
        DecimalFormat linenoFormat = new DecimalFormat("0");
        DecimalFormat pow3Format = new DecimalFormat("0");
        DecimalFormat evilFormat = new DecimalFormat("0");
        DecimalFormat odiousFormat = new DecimalFormat("0");

        System.out.print(linenoFormat.format(lineIndex) + ".  ");
        popcountIn = curPower3;
        findPopcount();
        System.out.print(pow3Format.format(popcountOut) + "    ");
        findEvil();
        System.out.print(evilFormat.format(curEvilNum) + "    ");
        findOdious();
        System.out.println(odiousFormat.format(curOdiousNum));

        curPower3 *= 3;
        curEvilNum++;
        curOdiousNum++;
        lineIndex++;
    }

    private void findEvil() {
        popcountIn = curEvilNum;
        findPopcount();
        if (popcountOut % 2 == 0) {
            curEvilNum++;
            findEvil();
        }
    }

    private void findOdious() {
        popcountIn = curOdiousNum;
        findPopcount();
        if (popcountOut % 2 == 1) {
            curOdiousNum++;
            findOdious();
        }
    }

    private void findPopcount() {
        popcountOut = 0;
        while (popcountIn != 0) {
            processBit();
        }
    }

    private void processBit() {
        popcountIn /= 2;
        if (popcountIn % 1 != 0) {
            popcountOut++;
        }
        popcountIn = Math.floor(popcountIn);
    }
}