import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021051 {
    private static final int MAP_SIZE = 1000;
    private static int[][] map = new int[MAP_SIZE][MAP_SIZE];
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        countResult();
        System.out.println(result);
    }

    private static void processRecord(String record) {
        String[] parts = record.split("[,\\s]+");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[3]);
        int y2 = Integer.parseInt(parts[4]);

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i <= l; i++) {
                map[x1][i] += 1;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i <= l; i++) {
                map[i][y1] += 1;
            }
        }
    }

    private static void countResult() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] > 1) {
                    result++;
                }
            }
        }
    }
}