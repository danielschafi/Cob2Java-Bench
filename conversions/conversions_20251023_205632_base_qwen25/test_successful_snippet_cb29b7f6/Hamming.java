import java.text.DecimalFormat;

public class Hamming {
    public static void main(String[] args) {
        double popcountIn = 0;
        int popcountOut = 0;
        int curPower3 = 1;
        int curEvilNum = 0;
        int curOdiusNum = 0;
        int lineIndex = 1;

        System.out.println("     3^   EVIL   ODD");

        for (int i = 0; i < 30; i++) {
            DecimalFormat linenoFormat = new DecimalFormat("0.");
            DecimalFormat pow3Format = new DecimalFormat("0");
            DecimalFormat evilFormat = new DecimalFormat("0");
            DecimalFormat odiusFormat = new DecimalFormat("0");

            System.out.print(linenoFormat.format(lineIndex));
            System.out.print(pow3Format.format(curPower3).substring(0, 3));
            System.out.print("    ");
            System.out.print(evilFormat.format(curEvilNum).substring(0, 3));
            System.out.print("    ");
            System.out.println(odiusFormat.format(curOdiusNum).substring(0, 3));

            popcountIn = curPower3;
            popcountOut = findPopcount(popcountIn);
            curPower3 *= 3;

            popcountIn = curEvilNum;
            popcountOut = findPopcount(popcountIn);
            while (popcountOut % 2 != 0) {
                curEvilNum++;
                popcountIn = curEvilNum;
                popcountOut = findPopcount(popcountIn);
            }

            popcountIn = curOdiusNum;
            popcountOut = findPopcount(popcountIn);
            while (popcountOut % 2 == 0) {
                curOdiusNum++;
                popcountIn = curOdiusNum;
                popcountOut = findPopcount(popcountIn);
            }

            curEvilNum++;
            curOdiusNum++;
            lineIndex++;
        }
    }

    public static int findPopcount(double popcountIn) {
        int popcountOut = 0;
        while (popcountIn != 0) {
            popcountIn /= 2;
            if (popcountIn % 1 != 0) {
                popcountOut++;
            }
            popcountIn = Math.floor(popcountIn);
        }
        return popcountOut;
    }
}