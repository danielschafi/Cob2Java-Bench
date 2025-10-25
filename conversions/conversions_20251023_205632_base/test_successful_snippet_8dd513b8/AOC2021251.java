import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021251 {
    private static final int M = 137;
    private static final int N = 139;
    private static char[][] wsMap = new char[M + 1][N + 1];
    private static char[][] wsMap2 = new char[M + 1][N + 1];
    private static int fileStatus = 0;
    private static int i = 0;
    private static int i1 = 0;
    private static int j = 1;
    private static int j1 = 1;
    private static int k = 1;
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d25.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                i++;
                inputRecord.getChars(0, N, wsMap[i], 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (k != 0) {
            result++;
            k = 0;
            for (int x = 1; x <= M; x++) {
                for (int y = 1; y <= N; y++) {
                    wsMap2[x][y] = '.';
                }
            }

            for (int x = 1; x <= M; x++) {
                for (int y = 1; y <= N; y++) {
                    if (wsMap[x][y] == '>') {
                        j1 = y + 1;
                        if (y == N) {
                            j1 = 1;
                        }
                        if (wsMap[x][j1] == '.') {
                            k = 1;
                            wsMap2[x][j1] = '>';
                        } else {
                            wsMap2[x][y] = '>';
                        }
                    }
                    if (wsMap[x][y] == 'v') {
                        wsMap2[x][y] = 'v';
                    }
                }
            }

            for (int x = 1; x <= M; x++) {
                for (int y = 1; y <= N; y++) {
                    wsMap[x][y] = wsMap2[x][y];
                    wsMap2[x][y] = '.';
                }
            }

            for (int x = 1; x <= M; x++) {
                for (int y = 1; y <= N; y++) {
                    if (wsMap[x][y] == 'v') {
                        i1 = x + 1;
                        if (x == M) {
                            i1 = 1;
                        }
                        if (wsMap[i1][y] == '.') {
                            k = 1;
                            wsMap2[i1][y] = 'v';
                        } else {
                            wsMap2[x][y] = 'v';
                        }
                    }
                    if (wsMap[x][y] == '>') {
                        wsMap2[x][y] = '>';
                    }
                }
            }

            for (int x = 1; x <= M; x++) {
                for (int y = 1; y <= N; y++) {
                    wsMap[x][y] = wsMap2[x][y];
                }
            }
        }

        System.out.println(result);
    }
}