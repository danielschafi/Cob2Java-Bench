import java.io.*;
import java.util.Arrays;

public class AOC2021092 {
    private static final int M = 100;
    private static final int N = 100;
    private static final int[][] WS_MAP = new int[M][100];
    private static final int[][] WS_LOWS = new int[M][100];
    private static final int[][] WS_DONE = new int[M][100];
    private static final int[][] WS_STACK = new int[999999][2];
    private static final int[] WS_MAX = new int[9999];
    private static int WS_AREA = 0;
    private static int WS_RESULT = 0;
    private static int I = 1;
    private static int J = 1;
    private static int S = 0;
    private static int X = 1;
    private static int Y = 1;
    private static int IS_LOW = 0;
    private static int fileStatus = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (I <= M) {
                    WS_MAP[I - 1] = line.chars().map(c -> c - '0').toArray();
                    I++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        findLows();
        findBasins();
        Arrays.sort(WS_MAX);
        WS_RESULT = WS_MAX[9998] * WS_MAX[9997] * WS_MAX[9996];
        System.out.println(WS_RESULT);
    }

    private static void findLows() {
        for (I = 1; I <= M; I++) {
            for (J = 1; J <= N; J++) {
                IS_LOW = 1;
                if (I > 1 && WS_MAP[I - 2][J - 1] <= WS_MAP[I - 1][J - 1]) {
                    IS_LOW = 0;
                }
                if (J > 1 && WS_MAP[I - 1][J - 2] <= WS_MAP[I - 1][J - 1]) {
                    IS_LOW = 0;
                }
                if (I < M && WS_MAP[I][J - 1] <= WS_MAP[I - 1][J - 1]) {
                    IS_LOW = 0;
                }
                if (J < N && WS_MAP[I - 1][J] <= WS_MAP[I - 1][J - 1]) {
                    IS_LOW = 0;
                }
                if (IS_LOW == 1) {
                    WS_LOWS[I - 1][J - 1] = 1;
                    System.out.print(WS_MAP[I - 1][J - 1]);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    private static void findBasins() {
        J = 0;
        for (I = 1; I <= M; I++) {
            for (J = 1; J <= N; J++) {
                if (WS_LOWS[I - 1][J - 1] == 1) {
                    measureBasin();
                }
            }
        }
    }

    private static void measureBasin() {
        WS_AREA = 0;
        S = 1;
        WS_STACK[S - 1][0] = I;
        WS_STACK[S - 1][1] = J;
        while (S > 0) {
            stackLoop();
        }
        WS_MAX[J - 1] = WS_AREA;
        J++;
    }

    private static void stackLoop() {
        if (S == 0) {
            return;
        }

        X = WS_STACK[S - 1][0];
        Y = WS_STACK[S - 1][1];
        S--;

        if (WS_DONE[X - 1][Y - 1] == 1) {
            return;
        }
        WS_DONE[X - 1][Y - 1] = 1;

        if (WS_MAP[X - 1][Y - 1] == 9) {
            return;
        }

        WS_AREA++;

        if (X > 1) {
            S++;
            WS_STACK[S - 1][0] = X - 1;
            WS_STACK[S - 1][1] = Y;
        }

        if (Y > 1) {
            S++;
            WS_STACK[S - 1][0] = X;
            WS_STACK[S - 1][1] = Y - 1;
        }

        if (X < M) {
            S++;
            WS_STACK[S - 1][0] = X + 1;
            WS_STACK[S - 1][1] = Y;
        }

        if (Y < N) {
            S++;
            WS_STACK[S - 1][0] = X;
            WS_STACK[S - 1][1] = Y + 1;
        }
    }
}