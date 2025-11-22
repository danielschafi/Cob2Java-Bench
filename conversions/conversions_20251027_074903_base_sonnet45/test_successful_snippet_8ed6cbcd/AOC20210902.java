import java.io.*;
import java.util.*;

public class AOC20210902 {
    private static final int M = 100;
    private static final int N = 100;
    private static int[][] wsMap = new int[M][N];
    private static int[][] wsLows = new int[M][N];
    private static int[][] wsDone = new int[M][N];
    private static List<Integer> wsMaxAreas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readInput();
            findLows();
            findBasins();
            Collections.sort(wsMaxAreas, Collections.reverseOrder());
            long result = (long) wsMaxAreas.get(0) * wsMaxAreas.get(1) * wsMaxAreas.get(2);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d09.input"));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null && i < M) {
            for (int j = 0; j < Math.min(line.length(), N); j++) {
                wsMap[i][j] = Character.getNumericValue(line.charAt(j));
            }
            i++;
        }
        reader.close();
    }

    private static void findLows() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                boolean isLow = true;
                if (i > 0 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j > 0 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (i < M - 1 && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j < N - 1 && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (isLow) {
                    wsLows[i][j] = 1;
                    System.out.print(wsMap[i][j]);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    private static void findBasins() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (wsLows[i][j] == 1) {
                    int area = measureBasin(i, j);
                    wsMaxAreas.add(area);
                }
            }
        }
    }

    private static int measureBasin(int startI, int startJ) {
        int area = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startI, startJ});

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int x = pos[0];
            int y = pos[1];

            if (wsDone[x][y] == 1) {
                continue;
            }
            wsDone[x][y] = 1;

            if (wsMap[x][y] == 9) {
                continue;
            }

            area++;

            if (x > 0) {
                stack.push(new int[]{x - 1, y});
            }
            if (y > 0) {
                stack.push(new int[]{x, y - 1});
            }
            if (x < M - 1) {
                stack.push(new int[]{x + 1, y});
            }
            if (y < N - 1) {
                stack.push(new int[]{x, y + 1});
            }
        }

        return area;
    }
}