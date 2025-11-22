import java.io.*;
import java.util.*;

public class AOC202111 {
    private static final int N = 10;
    private int[][] wsOcto = new int[N][N];
    private int[][] wsFlashed = new int[N][N];
    private int[] wsMustFlashX = new int[999999];
    private int[] wsMustFlashY = new int[999999];
    private int wsResult = 0;
    private int q1 = 1;
    private int q2 = 0;

    public static void main(String[] args) {
        AOC202111 program = new AOC202111();
        program.run();
    }

    private void run() {
        readInput();
        for (int step = 0; step < 100; step++) {
            performStep();
        }
        System.out.println(wsResult);
    }

    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d11.input"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < N) {
                for (int j = 0; j < line.length() && j < N; j++) {
                    wsOcto[i][j] = Character.getNumericValue(line.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void performStep() {
        q1 = 1;
        q2 = 0;

        // First, increase energy level of each octopus by 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                increase(i, j);
            }
        }

        // Then, process flashes
        while (q1 <= q2) {
            flashLoop();
        }

        // Finally, reset flashed octopuses
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (wsFlashed[i][j] == 1) {
                    wsOcto[i][j] = 0;
                    wsFlashed[i][j] = 0;
                }
            }
        }
    }

    private void increase(int k, int l) {
        if (wsOcto[k][l] < 9) {
            wsOcto[k][l]++;
        } else {
            q2++;
            wsMustFlashX[q2] = k;
            wsMustFlashY[q2] = l;
        }
    }

    private void flashLoop() {
        int x = wsMustFlashX[q1];
        int y = wsMustFlashY[q1];
        q1++;

        if (wsFlashed[x][y] == 1) {
            return;
        }
        if (wsOcto[x][y] < 9) {
            wsOcto[x][y]++;
            return;
        }

        wsResult++;
        wsFlashed[x][y] = 1;

        if (x > 0) {
            q2++;
            wsMustFlashX[q2] = x - 1;
            wsMustFlashY[q2] = y;

            if (y > 0) {
                q2++;
                wsMustFlashX[q2] = x - 1;
                wsMustFlashY[q2] = y - 1;
            }

            if (y < N - 1) {
                q2++;
                wsMustFlashX[q2] = x - 1;
                wsMustFlashY[q2] = y + 1;
            }
        }

        if (x < N - 1) {
            q2++;
            wsMustFlashX[q2] = x + 1;
            wsMustFlashY[q2] = y;

            if (y > 0) {
                q2++;
                wsMustFlashX[q2] = x + 1;
                wsMustFlashY[q2] = y - 1;
            }

            if (y < N - 1) {
                q2++;
                wsMustFlashX[q2] = x + 1;
                wsMustFlashY[q2] = y + 1;
            }
        }

        if (y > 0) {
            q2++;
            wsMustFlashX[q2] = x;
            wsMustFlashY[q2] = y - 1;
        }

        if (y < N - 1) {
            q2++;
            wsMustFlashX[q2] = x;
            wsMustFlashY[q2] = y + 1;
        }
    }
}