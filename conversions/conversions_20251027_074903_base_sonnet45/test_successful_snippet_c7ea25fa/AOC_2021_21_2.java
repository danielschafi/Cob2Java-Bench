public class AOC_2021_21_2 {
    public static void main(String[] args) {
        int p1 = 1;
        int p2 = 3;
        int score1 = 0;
        int score2 = 0;
        int die = 0;
        int dieCount = 0;
        int val;
        int player = 1;
        int result = 0;

        while (score1 < 1000 && score2 < 1000) {
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
                while (p1 > 10) {
                    p1 -= 10;
                }
                if (p1 == 0) {
                    score1 += 10;
                } else {
                    score1 += p1;
                }
                player = 2;
            } else {
                p2 += val;
                while (p2 > 10) {
                    p2 -= 10;
                }
                if (p2 == 0) {
                    score2 += 10;
                } else {
                    score2 += p2;
                }
                player = 1;
            }
        }

        result = dieCount * Math.min(score1, score2);
        System.out.println(result);
    }
}