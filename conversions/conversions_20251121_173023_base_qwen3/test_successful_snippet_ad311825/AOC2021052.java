import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2021052 {
    private static final int SIZE = 1000;
    private static int[][] map = new int[SIZE][SIZE];
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        countResult();
        System.out.println(result);
    }

    private static void processRecord(String record) {
        String[] parts = record.split("[, ]+");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);

        if (x1 == x2) {
            int k = Math.min(y1, y2);
            int l = Math.max(y1, y2);
            for (int i = k; i <= l; i++) {
                map[x1][i]++;
            }
        } else if (y1 == y2) {
            int k = Math.min(x1, x2);
            int l = Math.max(x1, x2);
            for (int i = k; i <= l; i++) {
                map[i][y1]++;
            }
        } else {
            int k = Math.min(x1, x2);
            int l = Math.max(x1, x2);
            int j = (x1 < x2) ? y1 : y2;
            int s = (x1 < x2) ? ((y1 < y2) ? 1 : -1) : ((y1 < y2) ? -1 : 1);

            for (int i = k; i <= l; i++) {
                map[i][j]++;
                j += s;
            }
        }
    }

    private static void countResult() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] > 1) {
                    result++;
                }
            }
        }
    }
}