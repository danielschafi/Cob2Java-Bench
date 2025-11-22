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
    private Random random;

    public static void main(String[] args) {
        GameLottery gameLottery = new GameLottery();
        gameLottery.mainProcedure();
    }

    public void mainProcedure() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Welcome in the game tip lottery !             -");
        System.out.println("- You choose one number from 1 to 100!          -");
        System.out.println("-------------------------------------------------");

        initSeed();
        generateNumbers();
        printNumber();
    }

    public void initSeed() {
        random = new Random(System.currentTimeMillis());
    }

    public void generateNumbers() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Generating numbers .......                    -");
        System.out.println("-------------------------------------------------");

        for (wR = 1; wR <= W_LEN_ARR; wR++) {
            wNum = random.nextInt(100) + 1;
            wArr[wR - 1] = wNum;
        }
    }

    public void printNumber() {
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