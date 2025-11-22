import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC_2021_04_1 {
    private static int[] wsDrawn = new int[99];
    private static int m = 0;
    private static int[][][] wsBoards = new int[100][5][5];
    private static int[][][] wsMarked = new int[100][5][5];
    private static int wsSum = 0;
    private static int wsProd = 1;
    private static int wsResult = 0;
    private static int y = 1;

    public static void main(String[] args) {
        try {
            readFile("d04.input");
            drawNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        
        reader.close();
    }

    private static void processRecord(String inputRecord) {
        int recLen = inputRecord.length();
        
        if (recLen > 14) {
            readNumbers(inputRecord);
        } else if (recLen > 0) {
            readBoards(inputRecord);
        } else {
            nextBoard();
        }
    }

    private static void readNumbers(String inputRecord) {
        String[] parts = inputRecord.split(",");
        for (int i = 0; i < parts.length && i < 99; i++) {
            wsDrawn[i] = Integer.parseInt(parts[i].trim());
        }
    }

    private static void readBoards(String inputRecord) {
        for (int x = 1; x <= 5; x++) {
            int startPos = x * 3 - 2 - 1;
            int endPos = x * 3 - 1;
            if (endPos <= inputRecord.length()) {
                String wsTmp = inputRecord.substring(startPos, endPos);
                wsBoards[m][y - 1][x - 1] = Integer.parseInt(wsTmp.trim());
            }
        }
        y++;
    }

    private static void nextBoard() {
        m++;
        y = 1;
    }

    private static void drawNumbers() {
        for (int i = 1; i <= 99; i++) {
            for (int j = 1; j <= m; j++) {
                for (int x = 1; x <= 5; x++) {
                    for (int yLoop = 1; yLoop <= 5; yLoop++) {
                        if (wsBoards[j - 1][x - 1][yLoop - 1] == wsDrawn[i - 1]) {
                            wsMarked[j - 1][x - 1][yLoop - 1] = 1;
                        }
                    }
                }
            }
            checkIfBingo(i);
        }
    }

    private static void checkIfBingo(int i) {
        for (int k = 1; k <= m; k++) {
            // Check columns
            for (int yLoop = 1; yLoop <= 5; yLoop++) {
                wsProd = 1;
                for (int x = 1; x <= 5; x++) {
                    wsProd = wsProd * wsMarked[k - 1][x - 1][yLoop - 1];
                }
                if (check(k, i)) {
                    return;
                }
            }
            
            // Check rows
            for (int x = 1; x <= 5; x++) {
                wsProd = 1;
                for (int yLoop = 1; yLoop <= 5; yLoop++) {
                    wsProd = wsProd * wsMarked[k - 1][x - 1][yLoop - 1];
                }
                if (check(k, i)) {
                    return;
                }
            }
        }
    }

    private static boolean check(int k, int i) {
        if (wsProd == 1) {
            wsSum = 0;
            for (int x = 1; x <= 5; x++) {
                for (int yLoop = 1; yLoop <= 5; yLoop++) {
                    if (wsMarked[k - 1][x - 1][yLoop - 1] == 0) {
                        wsSum = wsSum + wsBoards[k - 1][x - 1][yLoop - 1];
                    }
                }
            }
            wsResult = wsSum * wsDrawn[i - 1];
            System.out.println("Bingo! " + wsResult);
            System.exit(0);
            return true;
        }
        return false;
    }
}