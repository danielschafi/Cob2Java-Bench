import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020221 {
    private static final int MAX_CARDS = 54;
    private static final int MAX_DECK = 52;
    private static final int INITIAL_N = 25;

    private String[] inputBuffer = new String[MAX_CARDS];
    private int[][] wsCards = new int[MAX_DECK][2];
    private int c1, c2;
    private int fileStatus;
    private int recLen;
    private int n, n1, n2, i, k;
    private int result;

    public static void main(String[] args) {
        AOC2020221 program = new AOC2020221();
        program.run();
    }

    public void run() {
        readFile("d22.input");
        initData();
        playGame();
        tallyResult();
        System.out.println(result);
    }

    private void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            i = 1;
            while ((line = br.readLine()) != null && i < MAX_CARDS) {
                inputBuffer[i] = line;
                i++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }
    }

    private void initData() {
        for (i = 1; i <= n; i++) {
            wsCards[i - 1][0] = Integer.parseInt(inputBuffer[i]);
            k = i + n + 2;
            wsCards[i - 1][1] = Integer.parseInt(inputBuffer[k]);
        }
        n1 = n;
        n2 = n;
    }

    private void playGame() {
        while (n1 > 0 && n2 > 0) {
            c1 = wsCards[0][0];
            c2 = wsCards[0][1];
            if (c1 > c2) {
                wsCards[n1][0] = c1;
                wsCards[n1][1] = c2;
                n1++;
                n2--;
            } else {
                wsCards[n2][0] = c2;
                wsCards[n2][1] = c1;
                n2++;
                n1--;
            }
            shiftCards();
        }
    }

    private void shiftCards() {
        wsCards[n1][0] = 0;
        wsCards[n2][1] = 0;
        k = Math.max(n1, n2);
        for (i = 1; i < k; i++) {
            wsCards[i - 1][0] = wsCards[i][0];
            wsCards[i - 1][1] = wsCards[i][1];
        }
    }

    private void tallyResult() {
        for (i = 1; i <= 2 * n; i++) {
            k = (wsCards[i - 1][0] + wsCards[i - 1][1]) * (2 * n + 1 - i);
            result += k;
        }
    }
}