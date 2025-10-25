import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021041 {
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    private static final int MAX_NUMBERS = 99;

    private int fileStatus = 0;
    private int recLen;
    private int[] wsDrawn = new int[MAX_NUMBERS];
    private String wsTmp;
    private int m = 0;
    private int[][][] wsBoards = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int[][][] wsMarked = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int wsSum = 0;
    private int wsProd = 1;
    private int wsResult;

    private int stringPtr = 1;
    private int i;
    private int j;
    private int k;
    private int x;
    private int y;

    public static void main(String[] args) {
        AOC2021041 program = new AOC2021041();
        program.main();
    }

    private void main() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d04.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen > 14) {
                    readNumbers(line);
                } else if (recLen > 0) {
                    readBoards(line);
                } else {
                    nextBoard();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawNumbers();
    }

    private void readNumbers(String line) {
        stringPtr = 0;
        for (i = 0; i < MAX_NUMBERS; i++) {
            int commaIndex = line.indexOf(',', stringPtr);
            if (commaIndex == -1) {
                commaIndex = line.length();
            }
            wsTmp = line.substring(stringPtr, commaIndex);
            wsDrawn[i] = Integer.parseInt(wsTmp);
            stringPtr = commaIndex + 1;
            if (stringPtr >= line.length()) {
                break;
            }
        }
    }

    private void readBoards(String line) {
        stringPtr = 0;
        for (x = 0; x < BOARD_SIZE; x++) {
            wsTmp = line.substring(x * 3 - 2, x * 3 - 1);
            wsBoards[m][y][x] = Integer.parseInt(wsTmp);
        }
        y++;
    }

    private void nextBoard() {
        m++;
        y = 0;
    }

    private void drawNumbers() {
        for (i = 0; i < MAX_NUMBERS; i++) {
            for (j = 0; j < m; j++) {
                for (x = 0; x < BOARD_SIZE; x++) {
                    for (y = 0; y < BOARD_SIZE; y++) {
                        if (wsBoards[j][x][y] == wsDrawn[i]) {
                            wsMarked[j][x][y] = 1;
                        }
                    }
                }
            }
            checkIfBingo(i);
        }
    }

    private void checkIfBingo(int drawnIndex) {
        for (k = 0; k < m; k++) {
            for (y = 0; y < BOARD_SIZE; y++) {
                wsProd = 1;
                for (x = 0; x < BOARD_SIZE; x++) {
                    wsProd *= wsMarked[k][x][y];
                }
                check(k, drawnIndex);
            }
            for (x = 0; x < BOARD_SIZE; x++) {
                wsProd = 1;
                for (y = 0; y < BOARD_SIZE; y++) {
                    wsProd *= wsMarked[k][x][y];
                }
                check(k, drawnIndex);
            }
        }
    }

    private void check(int boardIndex, int drawnIndex) {
        if (wsProd == 1) {
            wsSum = 0;
            for (x = 0; x < BOARD_SIZE; x++) {
                for (y = 0; y < BOARD_SIZE; y++) {
                    if (wsMarked[boardIndex][x][y] == 0) {
                        wsSum += wsBoards[boardIndex][x][y];
                    }
                }
            }
            wsResult = wsSum * wsDrawn[drawnIndex];
            System.out.println("Bingo! " + wsResult);
            System.exit(0);
        }
    }
}