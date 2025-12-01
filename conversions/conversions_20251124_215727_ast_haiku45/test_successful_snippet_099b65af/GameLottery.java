```java
public class GameLottery {
    private static final int W_LEN_ARR = 100;
    private int[] wArr = new int[W_LEN_ARR];
    private int wNum = 0;
    private int wRandomTip = 0;
    private int wTip = 0;
    private int wR = 1;
    private int wI = 1;
    private int wX = 1;
    private double seed = 0.0;

    public static void main(String[] args) {
        GameLottery lottery = new GameLottery();
        lottery.mainProcedure();
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
        seed = Math.random();
    }

    private void generateNumbers() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Generating numbers .......                    -");
        System.out.println("-------------------------------------------------");

        for (wR = 1; wR <= W_LEN_ARR; wR++) {
            wNum = (int) (Math.random() * 100) + 1;
            wArr[wR - 1] = wNum;
        }
    }

    private void printNumber() {
        wX = 1;
        while (wX <= 200) {
            wRandomTip = (int) (Math.random() * 100) + 1;
            wX++;
        }

        wI = 1;
        while (wI <= wRandomTip) {
            wI++;
        }

        wTip = wArr[wI - 1];

        System.out.println("-------------------------------------------------");
        System.out.println("- Winning number is : " + wTip);
        System.out.println("-------------------------------------------------");
    }
}