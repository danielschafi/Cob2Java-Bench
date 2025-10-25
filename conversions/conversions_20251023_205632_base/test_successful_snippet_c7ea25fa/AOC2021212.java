import java.util.Random;

public class AOC2021212 {
    private static int P1 = 1;
    private static int P2 = 3;
    private static int SCORE1 = 0;
    private static int SCORE2 = 0;
    private static int DIE = 0;
    private static int DIE_COUNT = 0;
    private static int VAL = 0;
    private static int PLAYER = 1;
    private static int RESULT = 0;

    public static void main(String[] args) {
        Random random = new Random();
        while (SCORE1 < 1000 && SCORE2 < 1000) {
            turn(random);
        }
        RESULT = DIE_COUNT * Math.min(SCORE1, SCORE2);
        System.out.println(RESULT);
    }

    private static void turn(Random random) {
        VAL = 0;
        for (int i = 0; i < 3; i++) {
            DIE_COUNT++;
            DIE = random.nextInt(100) + 1;
            VAL += DIE;
        }

        if (PLAYER == 1) {
            P1 = (P1 + VAL - 1) % 10 + 1;
            SCORE1 += P1;
            PLAYER = 2;
        } else {
            P2 = (P2 + VAL - 1) % 10 + 1;
            SCORE2 += P2;
            PLAYER = 1;
        }
    }
}