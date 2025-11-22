import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021111 {
    private static final int N = 10;
    private static int[][] wsMap = new int[N][N];
    private static int[][] wsFlashedArr = new int[N][N];
    private static int[][] wsMustFlash = new int[999999][2];
    private static int wsResult = 0;
    private static int i = 1;
    private static int j = 1;
    private static int k = 1;
    private static int l = 1;
    private static int x = 1;
    private static int y = 1;
    private static int q1 = 1;
    private static int q2 = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d11.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (j = 1; j <= N; j++) {
                    wsMap[i - 1][j - 1] = Character.getNumericValue(line.charAt(j - 1));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int step = 1; step <= 100; step++) {
            for (i = 1; i <= N; i++) {
                for (j = 1; j <= N; j++) {
                    k = i;
                    l = j;
                    increase();
                }
            }

            while (q1 <= q2) {
                flashLoop();
            }

            for (i = 1; i <= N; i++) {
                for (j = 1; j <= N; j++) {
                    if (wsFlashedArr[i - 1][j - 1] == 1) {
                        wsMap[i - 1][j - 1] = 0;
                        wsFlashedArr[i - 1][j - 1] = 0;
                    }
                }
            }
        }

        System.out.println(wsResult);
    }

    private static void increase() {
        if (wsMap[k - 1][l - 1] < 9) {
            wsMap[k - 1][l - 1]++;
        } else {
            q2++;
            wsMustFlash[q2 - 1][0] = k;
            wsMustFlash[q2 - 1][1] = l;
        }
    }

    private static void flashLoop() {
        x = wsMustFlash[q1 - 1][0];
        y = wsMustFlash[q1 - 1][1];
        q1++;

        if (wsFlashedArr[x - 1][y - 1] == 1) {
            return;
        }
        if (wsMap[x - 1][y - 1] < 9) {
            wsMap[x - 1][y - 1]++;
            return;
        }

        wsResult++;
        wsFlashedArr[x - 1][y - 1] = 1;

        if (x > 1) {
            q2++;
            wsMustFlash[q2 - 1][0] = x - 1;
            wsMustFlash[q2 - 1][1] = y;

            if (y > 1) {
                q2++;
                wsMustFlash[q2 - 1][0] = x - 1;
                wsMustFlash[q2 - 1][1] = y - 1;
            }

            if (y < N) {
                q2++;
                wsMustFlash[q2 - 1][0] = x - 1;
                wsMustFlash[q2 - 1][1] = y + 1;
            }
        }

        if (x < N) {
            q2++;
            wsMustFlash[q2 - 1][0] = x + 1;
            wsMustFlash[q2 - 1][1] = y;

            if (y > 1) {
                q2++;
                wsMustFlash[q2 - 1][0] = x + 1;
                wsMustFlash[q2 - 1][1] = y - 1;
            }

            if (y < N) {
                q2++;
                wsMustFlash[q2 - 1][0] = x + 1;
                wsMustFlash[q2 - 1][1] = y + 1;
            }
        }

        if (y > 1) {
            q2++;
            wsMustFlash[q2 - 1][0] = x;
            wsMustFlash[q2 - 1][1] = y - 1;
        }

        if (y < N) {
            q2++;
            wsMustFlash[q2 - 1][0] = x;
            wsMustFlash[q2 - 1][1] = y + 1;
        }
    }
}