import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2021_05_2 {
    private static final int MAP_SIZE = 1000;
    private static int[][] wsMap = new int[MAP_SIZE][MAP_SIZE];
    private static int wsResult = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            count();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" -> |,");
        int x1 = Integer.parseInt(parts[0].trim());
        int y1 = Integer.parseInt(parts[1].trim());
        int x2 = Integer.parseInt(parts[2].trim());
        int y2 = Integer.parseInt(parts[3].trim());

        if (x1 == x2) {
            int k = Math.min(y1, y2);
            int l = Math.max(y1, y2);
            for (int i = k; i <= l; i++) {
                wsMap[x1][i]++;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2);
            int l = Math.max(x1, x2);
            for (int i = k; i <= l; i++) {
                wsMap[i][y1]++;
            }
        }

        if (x1 != x2 && y1 != y2) {
            int k = Math.min(x1, x2);
            int l = Math.max(x1, x2);
            int j;
            int s;

            if (x1 < x2) {
                j = y1;
                if (y1 < y2) {
                    s = 1;
                } else {
                    s = -1;
                }
            } else {
                j = y2;
                if (y1 < y2) {
                    s = -1;
                } else {
                    s = 1;
                }
            }

            for (int i = k; i <= l; i++) {
                wsMap[i][j]++;
                j += s;
            }
        }
    }

    private static void count() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (wsMap[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}