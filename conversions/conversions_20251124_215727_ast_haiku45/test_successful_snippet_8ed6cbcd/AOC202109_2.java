import java.io.*;
import java.util.*;

public class AOC202109_2 {
    private static final int MAX_SIZE = 100;
    private static final int STACK_SIZE = 999999;
    private static final int MAX_AREAS = 9999;
    
    private int[][] wsPoint = new int[MAX_SIZE][MAX_SIZE];
    private int[][] wsLow = new int[MAX_SIZE][MAX_SIZE];
    private int[][] wsDone = new int[MAX_SIZE][MAX_SIZE];
    private int[] wsStackX = new int[STACK_SIZE];
    private int[] wsStackY = new int[STACK_SIZE];
    private int[] wsMax = new int[MAX_AREAS];
    
    private int wsArea = 0;
    private long wsResult = 0;
    private int m = 100;
    private int n = 100;
    private int i = 1;
    private int j = 1;
    private int s = 0;
    private int x = 1;
    private int y = 1;
    private int isLow = 0;
    private int lineCount = 0;
    
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
            i = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    wsPoint[i][col] = Character.getNumericValue(line.charAt(col));
                }
                n = line.length();
                i++;
            }
            m = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void findLows() {
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                isLow = 1;
                
                if (i > 0 && wsPoint[i - 1][j] <= wsPoint[i][j]) {
                    isLow = 0;
                }
                if (j > 0 && wsPoint[i][j - 1] <= wsPoint[i][j]) {
                    isLow = 0;
                }
                if (i < m - 1 && wsPoint[i + 1][j] <= wsPoint[i][j]) {
                    isLow = 0;
                }
                if (j < n - 1 && wsPoint[i][j + 1] <= wsPoint[i][j]) {
                    isLow = 0;
                }
                
                if (isLow == 1) {
                    wsLow[i][j] = 1;
                    System.out.print(wsPoint[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    
    private void findBassins() {
        j = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (wsLow[i][j] == 1) {
                    measureBasin();
                }
            }
        }
    }
    
    private void measureBasin() {
        wsArea = 0;
        s = 1;
        wsStackX[0] = i;
        wsStackY[0] = j;
        
        while (s > 0) {
            stackLoop();
        }
        
        j++;
        wsMax[j] = wsArea;
    }
    
    private void stackLoop() {
        if (s == 0) {
            return;
        }
        
        x = wsStackX[s - 1];
        y = wsStackY[s - 1];
        s--;
        
        if (wsDone[x][y] == 1) {
            return;
        }
        
        wsDone[x][y] = 1;
        
        if (wsPoint[x][y] == 9) {
            return;
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
    
    private void sortMaxAreas() {
        Integer[] indices = new Integer[MAX_AREAS];
        for (int k = 0; k < MAX_AREAS; k++) {
            indices[k] = k;
        }
        
        Arrays.sort(indices, (a, b) -> Integer.compare(wsMax[b], wsMax[a]));
        
        int[] sorted = new int[MAX_AREAS];
        for (int k = 0; k < MAX_AREAS; k++) {
            sorted[k] = wsMax[indices[k]];
        }
        
        wsMax = sorted;
    }
}