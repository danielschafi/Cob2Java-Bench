import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021041 {
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    private static final int MAX_DRAWS = 99;

    private int fileStatus = 0;
    private int recLen;
    private int[] wsDrawn = new int[MAX_DRAWS];
    private String wsTmp;
    private int m = 0;
    private int[][][] wsBoards = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int[][][] wsMarked = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int wsSum = 0;
    private int wsProd = 1;
    private int wsResult;
    private int stringPtr = 1;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int x = 1;
    private int y = 1;

    public static void main(String[] args) {
        AOC2021041 program = new AOC2021041();
        program.execute();
    }

    private void execute() {
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
        stringPtr = 1;
        for (i = 0; i < MAX_DRAWS; i++) {
            int commaIndex = line.indexOf(',', stringPtr);
            if (commaIndex == -1) {
                wsTmp = line.substring(stringPtr - 1);
            } else {
                wsTmp = line.substring(stringPtr - 1, commaIndex);
                stringPtr = commaIndex + 1;
            }
            wsDrawn[i] = Integer.parseInt(wsTmp);
        }
    }

    private void readBoards(String line) {
        stringPtr = 1;
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
        for (i = 0; i < MAX_DRAWS; i++) {
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

    private void checkIfBingo(int drawIndex) {
        for (k = 0; k < m; k++) {
            for (y = 0; y < BOARD_SIZE; y++) {
                wsProd = 1;
                for (x = 0; x < BOARD_SIZE; x++) {
                    wsProd *= wsMarked[k][x][y];
                }
                check(drawIndex, k);
            }
            for (x = 0; x < BOARD_SIZE; x++) {
                wsProd = 1;
                for (y = 0; y < BOARD_SIZE; y++) {
                    wsProd *= wsMarked[k][x][y];
                }
                check(drawIndex, k);
            }
        }
    }

    private void check(int drawIndex, int boardIndex) {
        if (wsProd == 1) {
            wsSum = 0;
            for (x = 0; x < BOARD_SIZE; x++) {
                for (y = 0; y < BOARD_SIZE; y++) {
                    if (wsMarked[boardIndex][x][y] == 0) {
                        wsSum += wsBoards[boardIndex][x][y];
                    }
                }
            }
            wsResult = wsSum * wsDrawn[drawIndex];
            System.out.println("Bingo! " + wsResult);
            System.exit(0);
        }
    }
}