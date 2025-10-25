import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021131 {
    private static final int N = 2000;
    private static final int[][] wsDotsArray = new int[N][N];
    private static int wsResult = 0;
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {
        String inputFilePath = "d13.input";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                if (inputRecord.isEmpty()) {
                    x = N;
                    y = N;
                    break;
                }
                if (!inputRecord.startsWith("f")) {
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
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void placeDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        int xCoord = Integer.parseInt(parts[0]) + 1;
        int yCoord = Integer.parseInt(parts[1]) + 1;
        wsDotsArray[xCoord][yCoord] = 1;
    }

    private static void foldX() {
        for (int j = 1; j <= y; j++) {
            wsDotsArray[x][j] = 0;
            for (int i = x + 1; i <= 2 * x; i++) {
                if (wsDotsArray[i][j] == 1) {
                    int k = 2 * x - i;
                    wsDotsArray[k][j] = 1;
                    wsDotsArray[i][j] = 0;
                }
            }
        }
    }

    private static void foldY() {
        for (int i = 1; i <= x; i++) {
            wsDotsArray[i][y] = 0;
            for (int j = y + 1; j <= 2 * y; j++) {
                if (wsDotsArray[i][j] == 1) {
                    int k = 2 * y - j;
                    wsDotsArray[i][k] = 1;
                    wsDotsArray[i][j] = 0;
                }
            }
        }
    }

    private static void countDots() {
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (wsDotsArray[j][i] == 1) {
                    wsResult++;
                }
            }
        }
    }
}