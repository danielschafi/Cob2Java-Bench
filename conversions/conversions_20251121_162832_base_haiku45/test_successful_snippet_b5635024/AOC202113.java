import java.io.*;
import java.util.*;

public class AOC202113 {
    private static final int N = 2000;
    private int[][] wsDots = new int[N][N];
    private int wsResult = 0;
    private int fileStatus = 0;
    private int x, y, k, i1, j1;

    public static void main(String[] args) {
        AOC202113 program = new AOC202113();
        program.main001();
    }

    private void main001() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d13.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null && fileStatus == 0) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        if (inputRecord.length() > 0 && inputRecord.charAt(0) == ' ') {
            x = N;
            y = N;
            return;
        }

        if (inputRecord.length() > 0 && inputRecord.charAt(0) != 'f') {
            placeDot(inputRecord);
        } else {
            if (inputRecord.length() > 11 && inputRecord.charAt(11) == 'x') {
                String numStr = inputRecord.substring(13).trim();
                x = Integer.parseInt(numStr) + 1;
                foldX();
            } else {
                String numStr = inputRecord.substring(13).trim();
                y = Integer.parseInt(numStr) + 1;
                foldY();
            }
            countDots();
            System.out.println(wsResult);
            fileStatus = 1;
        }
    }

    private void placeDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        x = Integer.parseInt(parts[0].trim()) + 1;
        y = Integer.parseInt(parts[1].trim()) + 1;
        wsDots[x][y] = 1;
    }

    private void foldX() {
        i1 = x + 1;
        for (int j = 1; j <= y; j++) {
            wsDots[x][j] = 0;
            for (int i = i1; i <= 2 * x; i++) {
                if (wsDots[i][j] == 1) {
                    k = 2 * x - i;
                    wsDots[k][j] = 1;
                    wsDots[i][j] = 0;
                }
            }
        }
    }

    private void foldY() {
        j1 = y + 1;
        for (int i = 1; i <= x; i++) {
            wsDots[i][y] = 0;
            for (int j = j1; j <= 2 * y; j++) {
                if (wsDots[i][j] == 1) {
                    k = 2 * y - j;
                    wsDots[i][k] = 1;
                    wsDots[i][j] = 0;
                }
            }
        }
    }

    private void countDots() {
        wsResult = 0;
        for (int i = 1; i <= y - 1; i++) {
            for (int j = 1; j <= x - 1; j++) {
                if (wsDots[j][i] == 1) {
                    wsResult++;
                }
            }
        }
    }
}