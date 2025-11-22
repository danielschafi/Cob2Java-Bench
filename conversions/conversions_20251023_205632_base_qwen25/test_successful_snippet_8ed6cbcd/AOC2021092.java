import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AOC2021092 {
    private static final int M = 100;
    private static final int N = 100;
    private static final int MAX_STACK_SIZE = 999999;
    private static final int MAX_AREAS = 9999;

    private static char[][] wsMap = new char[M][100];
    private static int[][] wsLow = new int[M][N];
    private static int[][] wsDone = new int[M][N];
    private static int[][] wsStack = new int[MAX_STACK_SIZE][2];
    private static int[] wsMax = new int[MAX_AREAS];
    private static int wsArea;
    private static int wsResult;
    private static int fileStatus;
    private static int s;
    private static int x;
    private static int y;
    private static int isLow;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                wsMap[i] = line.toCharArray();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        findLows();
        findBasins();
        Arrays.sort(wsMax);
        wsResult = wsMax[MAX_AREAS - 1] * wsMax[MAX_AREAS - 2] * wsMax[MAX_AREAS - 3];
        System.out.println(wsResult);
    }

    private static void findLows() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                isLow = 1;
                if (i > 0 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j > 0 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (i < M - 1 && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j < N - 1 && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (isLow == 1) {
                    wsLow[i][j] = 1;
                    System.out.print(wsMap[i][j]);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    private static void findBasins() {
        int j = 0;
        for (int i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                if (wsLow[i][j] == 1) {
                    measureBasin(i, j);
                }
            }
        }
    }

    private static void measureBasin(int i, int j) {
        wsArea = 0;
        s = 1;
        wsStack[0][0] = i;
        wsStack[0][1] = j;
        while (s > 0) {
            x = wsStack[s - 1][0];
            y = wsStack[s - 1][1];
            s--;

            if (wsDone[x][y] == 1) {
                continue;
            }
            wsDone[x][y] = 1;

            if (wsMap[x][y] == '9') {
                continue;
            }

            wsArea++;

            if (x > 0) {
                wsStack[s][0] = x - 1;
                wsStack[s][1] = y;
                s++;
            }

            if (y > 0) {
                wsStack[s][0] = x;
                wsStack[s][1] = y - 1;
                s++;
            }

            if (x < M - 1) {
                wsStack[s][0] = x + 1;
                wsStack[s][1] = y;
                s++;
            }

            if (y < N - 1) {
                wsStack[s][0] = x;
                wsStack[s][1] = y + 1;
                s++;
            }
        }
        wsMax[j] = wsArea;
    }
}