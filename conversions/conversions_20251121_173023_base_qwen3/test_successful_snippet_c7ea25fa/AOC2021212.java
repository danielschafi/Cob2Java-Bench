public class AOC2021212 {
    private static int p1 = 1;
    private static int p2 = 3;
    private static int score1 = 0;
    private static int score2 = 0;
    private static int die = 0;
    private static int dieCount = 0;
    private static int val = 0;
    private static int player = 1;
    private static int result = 0;

    public static void main(String[] args) {
        while (score1 < 1000 && score2 < 1000) {
            turn();
        }
        result = dieCount * Math.min(score1, score2);
        System.out.println(result);
    }

    public static void turn() {
        val = 0;
        for (int i = 0; i < 3; i++) {
            dieCount++;
            die++;
            if (die > 100) {
                die = 1;
            }
            val += die;
        }

        if (player == 1) {
            p1 += val;
            if (p1 % 10 == 0) {
                score1 += 10;
            } else {
                score1 += (p1 % 10);
            }
            player = 2;
        } else {
            p2 += val;
            if (p2 % 10 == 0) {
                score2 += 10;
            } else {
                score2 += (p2 % 10);
            }
            player = 1;
        }
    }
}