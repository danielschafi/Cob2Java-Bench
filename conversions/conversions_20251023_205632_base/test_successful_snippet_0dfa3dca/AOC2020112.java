import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020112 {
    private static final int N_ROWS = 93;
    private static final int N_COLS = 98;
    private static final int K_MAX = 98;
    private static char[][] wsArr = new char[N_ROWS][N_COLS];
    private static char[][] wsArr2 = new char[N_ROWS][N_COLS];
    private static int changes;
    private static int occupied;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d11.input"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                line.getChars(0, N_COLS, wsArr[i], 0);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            changes = 0;
            for (int k = 0; k < N_ROWS; k++) {
                System.arraycopy(wsArr[k], 0, wsArr2[k], 0, N_COLS);
            }
            for (int k = 0; k < N_ROWS; k++) {
                for (int l = 0; l < N_COLS; l++) {
                    processSeat(k, l);
                }
            }
        } while (changes != 0);

        countOccupied();
        System.out.println(occupied);
    }

    private static void processSeat(int i, int j) {
        if (wsArr[i][j] == '.') {
            return;
        }
        int occupiedAdjacent = countOccupiedAdjacent(i, j);
        if (wsArr[i][j] == 'L' && occupiedAdjacent == 0) {
            wsArr[i][j] = '#';
            changes++;
        } else if (wsArr[i][j] == '#' && occupiedAdjacent > 4) {
            wsArr[i][j] = 'L';
            changes++;
        }
    }

    private static int countOccupiedAdjacent(int i, int j) {
        int occupiedAdjacent = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                occupiedAdjacent += countOccupiedAdjacentInDirection(i, j, di, dj);
            }
        }
        if (wsArr2[i][j] == '#') {
            occupiedAdjacent--;
        }
        return occupiedAdjacent;
    }

    private static int countOccupiedAdjacentInDirection(int i, int j, int di, int dj) {
        for (int k = 1; k <= K_MAX; k++) {
            int x = i + k * di;
            int y = j + k * dj;
            if (x < 0 || y < 0 || x >= N_ROWS || y >= N_COLS) {
                break;
            }
            if (wsArr2[x][y] == 'L') {
                break;
            }
            if (wsArr2[x][y] == '#') {
                return 1;
            }
        }
        return 0;
    }

    private static void countOccupied() {
        occupied = 0;
        for (int k = 0; k < N_ROWS; k++) {
            for (int l = 0; l < N_COLS; l++) {
                if (wsArr[k][l] == '#') {
                    occupied++;
                }
            }
        }
    }
}