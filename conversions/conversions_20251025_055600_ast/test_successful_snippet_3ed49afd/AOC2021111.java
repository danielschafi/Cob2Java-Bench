import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021111 {
    private static final int N = 10;
    private static final int MAX_STEPS = 100;
    private static final int MAX_FLASHES = 999999;

    private int[][] wsMap = new int[N][N];
    private int[][] wsFlashed = new int[N][N];
    private int[][] wsMustFlash = new int[MAX_FLASHES][2];
    private int wsResult = 0;
    private int i, j, k, l, x, y, q1, q2;

    public static void main(String[] args) {
        AOC2021111 program = new AOC2021111();
        program.run();
    }

    private void run() {
        readFile("d11.input");
        for (int step = 0; step < MAX_STEPS; step++) {
            step();
        }
        System.out.println(wsResult);
    }

    private void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            i = 0;
            while ((line = br.readLine()) != null) {
                wsMap[i] = line.chars().map(c -> c - '0').toArray();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void step() {
        q1 = 1;
        q2 = 0;

        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                k = i;
                l = j;
                increase();
            }
        }

        while (q1 <= q2) {
            flashLoop();
        }

        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (wsFlashed[i][j] == 1) {
                    wsMap[i][j] = 0;
                    wsFlashed[i][j] = 0;
                }
            }
        }
    }

    private void increase() {
        if (wsMap[k][l] < 9) {
            wsMap[k][l]++;
        } else {
            q2++;
            wsMustFlash[q2 - 1][0] = k;
            wsMustFlash[q2 - 1][1] = l;
        }
    }

    private void flashLoop() {
        x = wsMustFlash[q1 - 1][0];
        y = wsMustFlash[q1 - 1][1];
        q1++;

        if (wsFlashed[x][y] == 1) {
            return;
        }
        if (wsMap[x][y] < 9) {
            wsMap[x][y]++;
            return;
        }

        wsResult++;
        wsFlashed[x][y] = 1;

        if (x > 0) {
            q2++;
            wsMustFlash[q2 - 1][0] = x - 1;
            wsMustFlash[q2 - 1][1] = y;

            if (y > 0) {
                q2++;
                wsMustFlash[q2 - 1][0] = x - 1;
                wsMustFlash[q2 - 1][1] = y - 1;
            }

            if (y < N - 1) {
                q2++;
                wsMustFlash[q2 - 1][0] = x - 1;
                wsMustFlash[q2 - 1][1] = y + 1;
            }
        }

        if (x < N - 1) {
            q2++;
            wsMustFlash[q2 - 1][0] = x + 1;
            wsMustFlash[q2 - 1][1] = y;

            if (y > 0) {
                q2++;
                wsMustFlash[q2 - 1][0] = x + 1;
                wsMustFlash[q2 - 1][1] = y - 1;
            }

            if (y < N - 1) {
                q2++;
                wsMustFlash[q2 - 1][0] = x + 1;
                wsMustFlash[q2 - 1][1] = y + 1;
            }
        }

        if (y > 0) {
            q2++;
            wsMustFlash[q2 - 1][0] = x;
            wsMustFlash[q2 - 1][1] = y - 1;
        }

        if (y < N - 1) {
            q2++;
            wsMustFlash[q2 - 1][0] = x;
            wsMustFlash[q2 - 1][1] = y + 1;
        }
    }
}