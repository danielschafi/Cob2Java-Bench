import java.io.*;
import java.util.*;

public class AOC202109_2 {
    private static final int MAX_SIZE = 100;
    private static final int STACK_SIZE = 999999;
    private static final int MAX_AREAS_SIZE = 9999;
    
    private int[][] wsMap = new int[MAX_SIZE][MAX_SIZE];
    private int[][] wsLows = new int[MAX_SIZE][MAX_SIZE];
    private int[][] wsDone = new int[MAX_SIZE][MAX_SIZE];
    private int[] wsStackX = new int[STACK_SIZE];
    private int[] wsStackY = new int[STACK_SIZE];
    private int[] wsMax = new int[MAX_AREAS_SIZE];
    
    private int m = 0;
    private int n = 0;
    private int wsArea = 0;
    private long wsResult = 0;
    
    public static void main(String[] args) {
        AOC202109_2 program = new AOC202109_2();
        program.run();
    }
    
    private void run() {
        readInput();
        findLows();
        findBassins();
        sortMaxAreas();
        wsResult = (long) wsMax[0] * wsMax[1] * wsMax[2];
        System.out.println(wsResult);
    }
    
    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (m == 0) {
                    n = line.length();
                }
                for (int j = 0; j < line.length(); j++) {
                    wsMap[m][j] = Character.getNumericValue(line.charAt(j));
                }
                m++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void findLows() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLow = true;
                
                if (i > 0 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j > 0 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (i < m - 1 && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = false;
                }
                if (j < n - 1 && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = false;
                }
                
                if (isLow) {
                    wsLows[i][j] = 1;
                    System.out.print(wsMap[i][j]);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
    
    private void findBassins() {
        int basinIndex = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (wsLows[i][j] == 1) {
                    measureBasin(i, j, basinIndex);
                    basinIndex++;
                }
            }
        }
    }
    
    private void measureBasin(int startI, int startJ, int basinIndex) {
        wsArea = 0;
        int s = 1;
        wsStackX[0] = startI;
        wsStackY[0] = startJ;
        
        while (s > 0) {
            int x = wsStackX[s - 1];
            int y = wsStackY[s - 1];
            s--;
            
            if (wsDone[x][y] == 1) {
                continue;
            }
            wsDone[x][y] = 1;
            
            if (wsMap[x][y] == 9) {
                continue;
            }
            
            wsArea++;
            
            if (x > 0) {
                wsStackX[s] = x - 1;
                wsStackY[s] = y;
                s++;
            }
            
            if (y > 0) {
                wsStackX[s] = x;
                wsStackY[s] = y - 1;
                s++;
            }
            
            if (x < m - 1) {
                wsStackX[s] = x + 1;
                wsStackY[s] = y;
                s++;
            }
            
            if (y < n - 1) {
                wsStackX[s] = x;
                wsStackY[s] = y + 1;
                s++;
            }
        }
        
        wsMax[basinIndex] = wsArea;
    }
    
    private void sortMaxAreas() {
        Integer[] temp = new Integer[MAX_AREAS_SIZE];
        for (int i = 0; i < MAX_AREAS_SIZE; i++) {
            temp[i] = wsMax[i];
        }
        Arrays.sort(temp, Collections.reverseOrder());
        for (int i = 0; i < MAX_AREAS_SIZE; i++) {
            wsMax[i] = temp[i];
        }
    }
}