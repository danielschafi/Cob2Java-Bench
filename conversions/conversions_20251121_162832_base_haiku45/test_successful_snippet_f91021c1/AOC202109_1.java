import java.io.*;
import java.nio.file.*;

public class AOC202109_1 {
    private static final int MAX_ROWS = 100;
    private static final int MAX_COLS = 100;
    
    private int[][] wsMap = new int[MAX_ROWS][MAX_COLS];
    private int wsResult = 0;
    private int m = 0;
    private int n = MAX_COLS;
    
    public static void main(String[] args) {
        AOC202109_1 program = new AOC202109_1();
        program.run();
    }
    
    private void run() {
        try {
            readInput();
            countLows();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readInput() throws IOException {
        Path path = Paths.get("d09.input");
        if (!Files.exists(path)) {
            return;
        }
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < MAX_ROWS) {
                for (int j = 0; j < line.length() && j < MAX_COLS; j++) {
                    wsMap[i][j] = Character.getNumericValue(line.charAt(j));
                }
                n = Math.max(n, line.length());
                i++;
            }
            m = i;
        }
    }
    
    private void countLows() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int isLow = 1;
                
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
                    wsResult = wsResult + wsMap[i][j] + 1;
                }
            }
        }
    }
}