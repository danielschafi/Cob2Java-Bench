import java.util.Scanner;

public class AOC2021212 {
    public static void main(String[] args) {
        int P1 = 1;
        int P2 = 3;
        int SCORE1 = 0;
        int SCORE2 = 0;
        int DIE = 0;
        int DIE_COUNT = 0;
        int VAL = 0;
        int PLAYER = 1;
        int RESULT = 0;

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
            if (P1 > 10) {
                P1 = P1 % 10;
            }
            if (P1 == 0) {
                P1 = 10;
            }
            SCORE1 += P1;
            PLAYER = 2;
        } else {
            P2 += VAL;
            if (P2 > 10) {
                P2 = P2 % 10;
            }
            if (P2 == 0) {
                P2 = 10;
            }
            SCORE2 += P2;
            PLAYER = 1;
        }
    }
}