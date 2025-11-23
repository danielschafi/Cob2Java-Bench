import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2021092 {
    private static final int MAX_SIZE = 100;
    private static final int MAX_STACK = 999999;
    private static final int MAX_AREAS = 9999;

    private static int[][] wsMap = new int[MAX_SIZE][MAX_SIZE];
    private static int[][] wsLows = new int[MAX_SIZE][MAX_SIZE];
    private static int[][] wsDoneArr = new int[MAX_SIZE][MAX_SIZE];
    private static int[] wsStackX = new int[MAX_STACK];
    private static int[] wsStackY = new int[MAX_STACK];
    private static int[] wsMaxAreas = new int[MAX_AREAS];
    
    private static int wsArea = 0;
    private static long wsResult = 0;
    private static int m = 100;
    private static int n = 100;
    private static int i = 1;
    private static int j = 1;
    private static int s = 0;
    private static int x = 1;
    private static int y = 1;
    private static int isLow = 0;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d09.input"));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < MAX_SIZE) {
                for (int col = 0; col < line.length() && col < MAX_SIZE; col++) {
                    wsMap[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
            reader.close();

            findLows();
            findBasins();
            
            Arrays.sort(wsMaxAreas);
            wsResult = (long) wsMaxAreas[MAX_AREAS - 1] * (long) wsMaxAreas[MAX_AREAS - 2] * (long) wsMaxAreas[MAX_AREAS - 3];
            
            System.out.println(wsResult);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findLows() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                isLow = 1;
                if (i > 0 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j > 0 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (i < m - 1 && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j < n - 1 && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (isLow == 1) {
                    wsLows[i][j] = 1;
                    System.out.print(wsMap[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void findBasins() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (wsLows[i][j] == 1) {
                    measureBasin(i, j);
                }
            }
        }
    }

    private static void measureBasin(int startI, int startJ) {
        wsArea = 0;
        s = 1;
        wsStackX[0] = startI;
        wsStackY[0] = startJ;
        
        while (s > 0) {
            x = wsStackX[s - 1];
            y = wsStackY[s - 1];
            s--;
            
            if (wsDoneArr[x][y] == 1) {
                continue;
            }
            wsDoneArr[x][y] = 1;
            
            if (wsMap[x][y] == 9) {
                continue;
            }
            
            wsArea++;
            
            if (x > 0) {
                s++;
                wsStackX[s - 1] = x - 1;
                wsStackY[s - 1] = y;
            }
            
            if (y > 0) {
                s++;
                wsStackX[s - 1] = x;
                wsStackY[s - 1] = y - 1;
            }
            
            if (x < m - 1) {
                s++;
                wsStackX[s - 1] = x + 1;
                wsStackY[s - 1] = y;
            }
            
            if (y < n - 1) {
                s++;
                wsStackX[s - 1] = x;
                wsStackY[s - 1] = y + 1;
            }
        }
        
        wsMaxAreas[j] = wsArea;
        j++;
    }
}