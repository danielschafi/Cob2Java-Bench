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
    private static int occupiedAdjacent;
    private static int di;
    private static int dj;

    public static void main(String[] args) {
        readFile("d11.input");
        do {
            oneRound();
        } while (changes != 0);
        countOccupied();
        System.out.println(occupied);
    }

    private static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                processLine(line, i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(String line, int i) {
        wsArr[i] = line.toCharArray();
    }

    private static void oneRound() {
        changes = 0;
        for (int i = 0; i < N_ROWS; i++) {
            wsArr2[i] = wsArr[i].clone();
        }
        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                processSeat(i, j);
            }
        }
    }

    private static void processSeat(int i, int j) {
        if (wsArr[i][j] == '.') {
            return;
        }
        countOccupiedAdjacent(i, j);
        if (wsArr[i][j] == 'L' && occupiedAdjacent == 0) {
            wsArr[i][j] = '#';
            changes++;
        }
        if (wsArr[i][j] == '#' && occupiedAdjacent > 4) {
            wsArr[i][j] = 'L';
            changes++;
        }
    }

    private static void countOccupiedAdjacent(int i, int j) {
        occupiedAdjacent = 0;
        for (di = -1; di <= 1; di++) {
            for (dj = -1; dj <= 1; dj++) {
                countOccupiedAdjacentInDirection(i, j);
            }
        }
        if (wsArr2[i][j] == '#') {
            occupiedAdjacent--;
        }
    }

    private static void countOccupiedAdjacentInDirection(int i, int j) {
        for (int k = 1; k <= K_MAX; k++) {
            int x = i + k * di;
            int y = j + k * dj;
            if (x < 0 || y < 0 || x >= N_ROWS || y >= N_COLS) {
                return;
            }
            if (wsArr2[x][y] == 'L') {
                return;
            }
            if (wsArr2[x][y] == '#') {
                occupiedAdjacent++;
                return;
            }
        }
    }

    private static void countOccupied() {
        occupied = 0;
        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                if (wsArr[i][j] == '#') {
                    occupied++;
                }
            }
        }
    }
}