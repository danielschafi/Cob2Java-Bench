public class AOC2021111 {
    private static final int N = 10;
    private static int[][] wsOcto = new int[N][N];
    private static int[][] wsFlashed = new int[N][N];
    private static int[] wsMustFlashX = new int[999999];
    private static int[] wsMustFlashY = new int[999999];
    private static int wsResult = 0;
    private static int q1 = 1;
    private static int q2 = 0;

    public static void main(String[] args) {
        readInput();
        for (int i = 0; i < 100; i++) {
            step();
        }
        System.out.println(wsResult);
    }

    private static void readInput() {
        // Simulate reading from file
        // In a real implementation, you would read from d11.input file
        // For now, we'll assume the input is already loaded into wsOcto array
        // This part would need to be implemented based on actual file reading
    }

    private static void step() {
        // First, the energy level of each octopus increases by 1.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                increase(i, j);
            }
        }

        // Then, any octopus with an energy level greater than 9 flashes.
        while (q1 <= q2) {
            flashLoop();
        }

        // Finally, any octopus that flashed during this step has its energy level
        // set to 0, as it used all of its energy to flash.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (wsFlashed[i][j] == 1) {
                    wsOcto[i][j] = 0;
                    wsFlashed[i][j] = 0;
                }
            }
        }
    }

    private static void increase(int k, int l) {
        if (wsOcto[k][l] < 9) {
            wsOcto[k][l]++;
        } else {
            q2++;
            wsMustFlashX[q2] = k;
            wsMustFlashY[q2] = l;
        }
    }

    private static void flashLoop() {
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