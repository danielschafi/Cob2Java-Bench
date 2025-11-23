public class AOC2021212 {
    private static int P1 = 1;
    private static int P2 = 3;
    private static int SCORE1 = 0;
    private static int SCORE2 = 0;
    private static int DIE = 0;
    private static int DIE_COUNT = 0;
    private static int VAL = 0;
    private static int PLAYER = 1;
    private static long RESULT = 0;

    public static void main(String[] args) {
        while (SCORE1 < 1000 && SCORE2 < 1000) {
            turn();
        }
        RESULT = DIE_COUNT * Math.min(SCORE1, SCORE2);
        System.out.println(RESULT);
    }

    private static void turn() {
        VAL = 0;
        for (int i = 0; i < 3; i++) {
            DIE_COUNT++;
            DIE++;
            if (DIE > 100) {
                DIE = 1;
            }
            VAL += DIE;
        }

        if (PLAYER == 1) {
            P1 += VAL;
            if (P1 == 0) {
                SCORE1 += 10;
            } else {
                SCORE1 += P1;
            }
            PLAYER = 2;
        } else {
            P2 += VAL;
            if (P2 == 0) {
                SCORE2 += 10;
            } else {
                SCORE2 += P2;
            }
            PLAYER = 1;
        }
    }
}