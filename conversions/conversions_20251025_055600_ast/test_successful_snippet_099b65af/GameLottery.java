import java.util.Random;

public class GameLottery {
    private static final int W_LEN_ARR = 100;
    private int[] wArr = new int[W_LEN_ARR];
    private int wNum;
    private int wRandomTip;
    private int wTip;
    private int wR = 1;
    private int wI = 1;
    private int wX = 1;
    private long seed;

    public static void main(String[] args) {
        GameLottery gameLottery = new GameLottery();
        gameLottery.mainProcedure();
    }

    private void mainProcedure() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Welcome in the game tip lottery !             -");
        System.out.println("- You choose one number from 1 to 100!          -");
        System.out.println("-------------------------------------------------");

        initSeed();
        generateNumbers();
        printNumber();
    }

    private void initSeed() {
        seed = System.currentTimeMillis() % 1000000000;
    }

    private void generateNumbers() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Generating numbers .......                    -");
        System.out.println("-------------------------------------------------");

        Random random = new Random(seed);
        for (wR = 1; wR <= W_LEN_ARR; wR++) {
            wNum = random.nextInt(100) + 1;
            wArr[wR - 1] = wNum;
        }
    }

    private void printNumber() {
        Random random = new Random(seed);
        while (wX <= 200) {
            wRandomTip = random.nextInt(100) + 1;
            wX++;
        }

        while (wI <= wRandomTip) {
            wI++;
        }

        wTip = wArr[wI - 1];

        System.out.println("-------------------------------------------------");
        System.out.println("- Winning number is : " + wTip);
        System.out.println("-------------------------------------------------");
    }
}