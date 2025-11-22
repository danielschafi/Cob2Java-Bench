import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2021092 {
    private static final int MAX_ROWS = 100;
    private static final int MAX_COLS = 100;
    private static final int MAX_STACK = 999999;
    private static final int MAX_AREAS = 9999;

    private static int[][] map = new int[MAX_ROWS][MAX_COLS];
    private static int[][] lows = new int[MAX_ROWS][MAX_COLS];
    private static int[][] done = new int[MAX_ROWS][MAX_COLS];
    private static int[] stackX = new int[MAX_STACK];
    private static int[] stackY = new int[MAX_STACK];
    private static int[] maxAreas = new int[MAX_AREAS];

    private static int m = 100;
    private static int n = 100;
    private static int i = 1;
    private static int j = 1;
    private static int s = 0;
    private static int x = 1;
    private static int y = 1;
    private static int isLow = 1;
    private static int area = 0;
    private static long result = 0;

    public static void main(String[] args) {
        readInput();
        findLows();
        findBasins();
        
        Arrays.sort(maxAreas);
        for (int k = 0; k < MAX_AREAS / 2; k++) {
            int temp = maxAreas[k];
            maxAreas[k] = maxAreas[MAX_AREAS - 1 - k];
            maxAreas[MAX_AREAS - 1 - k] = temp;
        }
        
        result = (long)maxAreas[0] * (long)maxAreas[1] * (long)maxAreas[2];
        System.out.println(result);
    }

    private static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < MAX_ROWS) {
                for (int col = 0; col < line.length() && col < MAX_COLS; col++) {
                    map[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findLows() {
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                isLow = 1;
                if (i > 0 && map[i-1][j] <= map[i][j]) {
                    isLow = 0;
                }
                if (j > 0 && map[i][j-1] <= map[i][j]) {
                    isLow = 0;
                }
                if (i < m - 1 && map[i+1][j] <= map[i][j]) {
                    isLow = 0;
                }
                if (j < n - 1 && map[i][j+1] <= map[i][j]) {
                    isLow = 0;
                }
                if (isLow == 1) {
                    lows[i][j] = 1;
                    System.out.print(map[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void findBasins() {
        j = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (lows[i][j] == 1) {
                    measureBasin();
                }
            }
        }
    }

    private static void measureBasin() {
        area = 0;
        s = 1;
        stackX[0] = i;
        stackY[0] = j;
        
        while (s > 0) {
            stackLoop();
        }
        j++;
        maxAreas[j] = area;
    }

    private static void stackLoop() {
        if (s == 0) {
            return;
        }

        x = stackX[s - 1];
        y = stackY[s - 1];
        s--;

        if (done[x][y] == 1) {
            stackLoop();
            return;
        }
        done[x][y] = 1;

        if (map[x][y] == 9) {
            stackLoop();
            return;
        }

        area++;

        if (x > 0) {
            s++;
            stackX[s - 1] = x - 1;
            stackY[s - 1] = y;
        }

        if (y > 0) {
            s++;
            stackX[s - 1] = x;
            stackY[s - 1] = y - 1;
        }

        if (x < m - 1) {
            s++;
            stackX[s - 1] = x + 1;
            stackY[s - 1] = y;
        }

        if (y < n - 1) {
            s++;
            stackX[s - 1] = x;
            stackY[s - 1] = y + 1;
        }
    }
}