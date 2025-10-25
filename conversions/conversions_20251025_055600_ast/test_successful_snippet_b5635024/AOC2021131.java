import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021131 {
    private static final int N = 2000;
    private static int[][] wsDotsArray = new int[N][N];
    private static int wsResult = 0;
    private static int x, y, i, j, k, i1, j1;

    public static void main(String[] args) {
        String inputFilePath = "d13.input";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
        if (inputRecord.charAt(0) == ' ') {
            x = N;
            y = N;
            return;
        }
        if (inputRecord.charAt(0) != 'f') {
            placeDot(inputRecord);
        } else {
            if (inputRecord.charAt(11) == 'x') {
                x = Integer.parseInt(inputRecord.substring(13, 16)) + 1;
                foldX();
            } else {
                y = Integer.parseInt(inputRecord.substring(13, 16)) + 1;
                foldY();
            }
            countDots();
            System.out.println(wsResult);
            System.exit(0);
        }
    }

    private static void placeDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        x = Integer.parseInt(parts[0]) + 1;
        y = Integer.parseInt(parts[1]) + 1;
        wsDotsArray[x][y] = 1;
    }

    private static void foldX() {
        i1 = x + 1;
        for (j = 1; j <= y; j++) {
            wsDotsArray[x][j] = 0;
            for (i = i1; i <= 2 * x; i++) {
                if (wsDotsArray[i][j] == 1) {
                    k = 2 * x - i;
                    wsDotsArray[k][j] = 1;
                    wsDotsArray[i][j] = 0;
                }
            }
        }
    }

    private static void foldY() {
        j1 = y + 1;
        for (i = 1; i <= x; i++) {
            wsDotsArray[i][y] = 0;
            for (j = j1; j <= 2 * y; j++) {
                if (wsDotsArray[i][j] == 1) {
                    k = 2 * y - j;
                    wsDotsArray[i][k] = 1;
                    wsDotsArray[i][j] = 0;
                }
            }
        }
    }

    private static void countDots() {
        for (i = 1; i < y; i++) {
            for (j = 1; j < x; j++) {
                if (wsDotsArray[j][i] == 1) {
                    wsResult++;
                }
            }
        }
    }
}