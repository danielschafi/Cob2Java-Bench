import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021091 {
    private static final int MAX_ROWS = 100;
    private static final int MAX_COLS = 100;
    private static int[][] wsMap = new int[MAX_ROWS][MAX_COLS];
    private static int m = 100;
    private static int n = 100;
    private static int wsResult = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < MAX_ROWS) {
                for (int j = 0; j < Math.min(line.length(), MAX_COLS); j++) {
                    wsMap[i][j] = Character.getNumericValue(line.charAt(j));
                }
                i++;
            }
            m = i;
            n = Math.min(line.length(), MAX_COLS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        countLows();
        System.out.println(wsResult);
    }

    private static void countLows() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLow = true;
                if (i > 0 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j > 0 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (i < m - 1 && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j < n - 1 && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (isLow) {
                    wsResult += wsMap[i][j] + 1;
                }
            }
        }
    }
}