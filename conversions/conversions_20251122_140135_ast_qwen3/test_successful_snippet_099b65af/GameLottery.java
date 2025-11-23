import java.util.Random;

public class GameLottery {
    private static final int W_LEN_ARR = 100;
    private static final int[] W_ARR = new int[W_LEN_ARR];
    private static int W_NUM;
    private static int W_RANDOM_TIP;
    private static int W_TIP;
    private static int W_R = 1;
    private static int W_I = 1;
    private static int W_X = 1;
    private static double SEED;

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println("- Welcome in the game tip lottery !             -");
        System.out.println("- You choose one number from 1 to 100!          -");
        System.out.println("-------------------------------------------------");

        initSeed();
        generateNumbers();
        printNumber();
    }

    private static void initSeed() {
        SEED = Math.random() * (double) (int) System.currentTimeMillis();
    }

    private static void generateNumbers() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Generating numbers .......                    -");
        System.out.println("-------------------------------------------------");

        Random random = new Random();
        for (int W_R = 1; W_R <= W_LEN_ARR; W_R++) {
            W_NUM = random.nextInt(100) + 1;
            W_ARR[W_R - 1] = W_NUM;
        }
    }

    private static void printNumber() {
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            W_RANDOM_TIP = random.nextInt(100) + 1;
            W_X++;
        }

        while (W_I <= W_RANDOM_TIP) {
            W_I++;
        }

        W_TIP = W_ARR[W_I - 1];

        System.out.println("-------------------------------------------------");
        System.out.println("- Winning number is : " + W_TIP);
        System.out.println("-------------------------------------------------");
    }
}