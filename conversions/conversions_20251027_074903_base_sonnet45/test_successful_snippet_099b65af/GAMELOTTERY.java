import java.util.Random;

public class GAMELOTTERY {
    private static final int W_LEN_ARR = 100;
    private int[] W_ARR = new int[W_LEN_ARR];
    private int W_NUM;
    private int W_RANDOM_TIP;
    private int W_TIP;
    private int W_R = 1;
    private int W_I = 1;
    private int W_X = 1;
    private Random random;

    public static void main(String[] args) {
        GAMELOTTERY program = new GAMELOTTERY();
        program.MAIN_PROCEDURE();
    }

    private void MAIN_PROCEDURE() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Welcome in the game tip lottery !             -");
        System.out.println("- You choose one number from 1 to 100!          -");
        System.out.println("-------------------------------------------------");

        INIT_SEED();
        GENERATE_NUMBERS();
        PRINT_NUMBER();
    }

    private void INIT_SEED() {
        long seed = System.currentTimeMillis() % 86400000 / 1000;
        random = new Random(seed);
        random.nextDouble();
    }

    private void GENERATE_NUMBERS() {
        System.out.println("-------------------------------------------------");
        System.out.println("- Generating numbers .......                    -");
        System.out.println("-------------------------------------------------");

        W_R = 1;
        while (W_R <= W_LEN_ARR) {
            W_NUM = (int) (random.nextDouble() * 100) + 1;
            W_ARR[W_R - 1] = W_NUM;
            W_R++;
        }
    }

    private void PRINT_NUMBER() {
        W_X = 1;
        while (W_X <= 200) {
            W_RANDOM_TIP = (int) (random.nextDouble() * 100) + 1;
            W_X++;
        }

        W_I = 1;
        while (W_I <= W_RANDOM_TIP) {
            W_I++;
        }

        W_TIP = W_ARR[W_I - 1];

        System.out.println("-------------------------------------------------");
        System.out.printf("- Winning number is : %03d%n", W_TIP);
        System.out.println("-------------------------------------------------");
    }
}