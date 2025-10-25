import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020221 {
    private static final int MAX_CARDS = 52;
    private static final int BUFFER_SIZE = 54;
    private static final int MAX_LINE_LENGTH = 9;

    private int fileStatus = 0;
    private int recLen;
    private String[] inputBuffer = new String[BUFFER_SIZE];
    private int[][] wsCards = new int[MAX_CARDS][2];
    private int c1, c2;
    private int n = 25;
    private int n1 = 1, n2 = 1;
    private int i = 1, k = 1;
    private int result = 0;

    public static void main(String[] args) {
        AOC2020221 program = new AOC2020221();
        program.run();
    }

    private void run() {
        readFile();
        initData();
        while (n1 > 0 && n2 > 0) {
            playGame();
        }
        tallyResult();
        System.out.println(result);
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("d22.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > MAX_LINE_LENGTH) {
                    line = line.substring(0, MAX_LINE_LENGTH);
                }
                inputBuffer[i++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        c1 = wsCards[0][0];
        c2 = wsCards[0][1];
        if (c1 > c2) {
            wsCards[n1][0] = c1;
            wsCards[n1 + 1][0] = c2;
            n1 += 2;
            n2--;
        } else {
            wsCards[n2][1] = c2;
            wsCards[n2 + 1][1] = c1;
            n2 += 2;
            n1--;
        }
        shiftCards();
    }

    private void shiftCards() {
        for (i = 0; i < MAX_CARDS - 1; i++) {
            wsCards[i][0] = wsCards[i + 1][0];
            wsCards[i][1] = wsCards[i + 1][1];
        }
        wsCards[n1][0] = 0;
        wsCards[n1 + 1][0] = 0;
        wsCards[n2][1] = 0;
        wsCards[n2 + 1][1] = 0;
    }

    private void tallyResult() {
        for (i = 0; i < 2 * n; i++) {
            k = (wsCards[i][0] + wsCards[i][1]) * (2 * n + 1 - i);
            result += k;
        }
    }
}