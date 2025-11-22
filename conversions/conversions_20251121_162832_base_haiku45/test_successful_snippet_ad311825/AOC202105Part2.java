```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202105Part2 {
    private static final int MAP_SIZE = 1000;
    private int[][] wsPoint = new int[MAP_SIZE][MAP_SIZE];
    private int wsResult = 0;

    public static void main(String[] args) {
        AOC202105Part2 program = new AOC202105Part2();
        program.run();
    }

    private void run() {
        try {
            readAndProcess();
            count();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndProcess() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        }
    }

    private void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" -> ");
        if (parts.length != 2) {
            return;
        }

        String[] start = parts[0].split(",");
        String[] end = parts[1].split(",");

        int x1 = Integer.parseInt(start[0].trim());
        int y1 = Integer.parseInt(start[1].trim());
        int x2 = Integer.parseInt(end[0].trim());
        int y2 = Integer.parseInt(end[1].trim());

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i < l; i++) {
                wsPoint[x1][i]++;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i < l; i++) {
                wsPoint[i][y1]++;
            }
        }

        if (x1 != x2 && y1 != y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            int j;
            int s;

            if (x1 < x2) {
                j = y1 + 1;
                s = (y1 < y2) ? 1 : -1;
            } else {
                j = y2 + 1;
                s = (y1 < y2) ? -1 : 1;
            }

            for (int i = k; i < l; i++) {
                wsPoint[i][j]++;
                j += s;
            }
        }
    }

    private void count() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (wsPoint[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}