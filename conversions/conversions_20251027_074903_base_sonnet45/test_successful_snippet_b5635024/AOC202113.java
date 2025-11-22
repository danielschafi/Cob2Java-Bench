import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202113 {
    private static final int MAX_SIZE = 2000;
    private static int[][] wsDots = new int[MAX_SIZE][MAX_SIZE];
    private static int wsResult = 0;
    private static int n = 2000;
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d13.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        if (inputRecord.isEmpty() || inputRecord.charAt(0) == ' ') {
            x = n;
            y = n;
            return;
        }

        if (inputRecord.charAt(0) != 'f') {
            placeDot(inputRecord);
        } else {
            if (inputRecord.charAt(11) == 'x') {
                x = Integer.parseInt(inputRecord.substring(13).trim());
                x = x + 1;
                foldX();
            } else {
                y = Integer.parseInt(inputRecord.substring(13).trim());
                y = y + 1;
                foldY();
            }
            countDots();
            System.out.println(wsResult);
            System.exit(0);
        }
    }

    private static void placeDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        x = Integer.parseInt(parts[0].trim()) + 1;
        y = Integer.parseInt(parts[1].trim()) + 1;
        wsDots[x - 1][y - 1] = 1;
    }

    private static void foldX() {
        int i1 = x + 1;
        for (int j = 1; j <= y; j++) {
            wsDots[x - 1][j - 1] = 0;
            for (int i = i1; i <= 2 * x; i++) {
                if (wsDots[i - 1][j - 1] == 1) {
                    int k = 2 * x - i;
                    wsDots[k - 1][j - 1] = 1;
                    wsDots[i - 1][j - 1] = 0;
                }
            }
        }
    }

    private static void foldY() {
        int j1 = y + 1;
        for (int i = 1; i <= x; i++) {
            wsDots[i - 1][y - 1] = 0;
            for (int j = j1; j <= 2 * y; j++) {
                if (wsDots[i - 1][j - 1] == 1) {
                    int k = 2 * y - j;
                    wsDots[i - 1][k - 1] = 1;
                    wsDots[i - 1][j - 1] = 0;
                }
            }
        }
    }

    private static void countDots() {
        wsResult = 0;
        for (int i = 1; i <= y - 1; i++) {
            for (int j = 1; j <= x - 1; j++) {
                if (wsDots[j - 1][i - 1] == 1) {
                    wsResult++;
                }
            }
        }
    }
}