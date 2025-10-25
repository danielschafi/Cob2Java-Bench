import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021251 {
    private static final int M = 137;
    private static final int N = 139;
    private static char[][] wsMap = new char[M][N];
    private static char[][] wsMap2 = new char[M][N];
    private static int fileStatus = 0;
    private static int i = 0;
    private static int i1 = 0;
    private static int j = 1;
    private static int j1 = 1;
    private static int k = 1;
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d25.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                i++;
                inputRecord.getChars(0, N, wsMap[i - 1], 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (k != 0) {
            step();
        }
        System.out.println(result);
    }

    private static void step() {
        result++;
        k = 0;
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                wsMap2[i][j] = '.';
            }
        }

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                if (wsMap[i][j] == '>') {
                    j1 = (j + 1) % N;
                    if (wsMap[i][j1] == '.') {
                        k = 1;
                        wsMap2[i][j1] = '>';
                    } else {
                        wsMap2[i][j] = '>';
                    }
                }
                if (wsMap[i][j] == 'v') {
                    wsMap2[i][j] = 'v';
                }
            }
        }

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                wsMap[i][j] = wsMap2[i][j];
                wsMap2[i][j] = '.';
            }
        }

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                if (wsMap[i][j] == 'v') {
                    i1 = (i + 1) % M;
                    if (wsMap[i1][j] == '.') {
                        k = 1;
                        wsMap2[i1][j] = 'v';
                    } else {
                        wsMap2[i][j] = 'v';
                    }
                }
                if (wsMap[i][j] == '>') {
                    wsMap2[i][j] = '>';
                }
            }
        }

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                wsMap[i][j] = wsMap2[i][j];
            }
        }
    }
}