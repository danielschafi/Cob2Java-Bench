import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021131 {
    private static final int MAX_SIZE = 2000;
    private static int[][] wsDotsArray = new int[MAX_SIZE][MAX_SIZE];
    private static int wsResult = 0;
    private static int n = 2000;
    private static int i = 1;
    private static int j = 1;
    private static int k = 1;
    private static int i1 = 1;
    private static int j1 = 1;
    private static int x = 0; 
    private static int y = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d13.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        if (inputRecord.charAt(0) == ' ') {
            x = n;
            y = n;
            return;
        }
        
        if (inputRecord.charAt(0) != 'f') {
            placeDot(inputRecord);
        } else {
            if (inputRecord.charAt(11) == 'x') {
                x = Integer.parseInt(inputRecord.substring(13, 16).trim());
                x++;
                foldX();
            } else {
                y = Integer.parseInt(inputRecord.substring(13, 16).trim());
                y++;
                foldY();
            }
            countDots();
            System.out.println(wsResult);
        }
    }

    private static void placeDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        x = Integer.parseInt(parts[0].trim()) + 1;
        y = Integer.parseInt(parts[1].trim()) + 1;
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
        for (i = 1; i <= y - 1; i++) {
            for (j = 1; j <= x - 1; j++) {
                if (wsDotsArray[j][i] == 1) {
                    wsResult++;
                }
            }
        }
    }
}