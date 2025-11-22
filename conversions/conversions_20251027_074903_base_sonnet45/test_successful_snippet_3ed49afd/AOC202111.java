import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202111 {
    private static final int N = 10;
    private static final int MAX_FLASH_QUEUE = 999999;
    
    private int[][] wsMap = new int[N][N];
    private int[][] wsFlashed = new int[N][N];
    private int[] wsMustFlashX = new int[MAX_FLASH_QUEUE];
    private int[] wsMustFlashY = new int[MAX_FLASH_QUEUE];
    private int wsResult = 0;
    private int q1 = 0;
    private int q2 = -1;
    
    public static void main(String[] args) {
        AOC202111 program = new AOC202111();
        program.run();
    }
    
    public void run() {
        readInput();
        for (int step = 0; step < 100; step++) {
            performStep();
        }
        System.out.println(wsResult);
    }
    
    private void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d11.input"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < N) {
                for (int col = 0; col < N && col < line.length(); col++) {
                    wsMap[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void performStep() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                increase(i, j);
            }
        }
        
        while (q1 <= q2) {
            flashLoop();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (wsFlashed[i][j] == 1) {
                    wsMap[i][j] = 0;
                    wsFlashed[i][j] = 0;
                }
            }
        }
    }
    
    private void increase(int k, int l) {
        if (wsMap[k][l] < 9) {
            wsMap[k][l]++;
        } else {
            q2++;
            wsMustFlashX[q2] = k;
            wsMustFlashY[q2] = l;
        }
    }
    
    private void flashLoop() {
        int x = wsMustFlashX[q1];
        int y = wsMustFlashY[q1];
        q1++;
        
        if (wsFlashed[x][y] == 1) {
            return;
        }
        
        if (wsMap[x][y] < 9) {
            wsMap[x][y]++;
            return;
        }
        
        wsResult++;
        wsFlashed[x][y] = 1;
        
        if (x > 0) {
            q2++;
            wsMustFlashX[q2] = x - 1;
            wsMustFlashY[q2] = y;
            
            if (y > 0) {
                q2++;
                wsMustFlashX[q2] = x - 1;
                wsMustFlashY[q2] = y - 1;
            }
            
            if (y < N - 1) {
                q2++;
                wsMustFlashX[q2] = x - 1;
                wsMustFlashY[q2] = y + 1;
            }
        }
        
        if (x < N - 1) {
            q2++;
            wsMustFlashX[q2] = x + 1;
            wsMustFlashY[q2] = y;
            
            if (y > 0) {
                q2++;
                wsMustFlashX[q2] = x + 1;
                wsMustFlashY[q2] = y - 1;
            }
            
            if (y < N - 1) {
                q2++;
                wsMustFlashX[q2] = x + 1;
                wsMustFlashY[q2] = y + 1;
            }
        }
        
        if (y > 0) {
            q2++;
            wsMustFlashX[q2] = x;
            wsMustFlashY[q2] = y - 1;
        }
        
        if (y < N - 1) {
            q2++;
            wsMustFlashX[q2] = x;
            wsMustFlashY[q2] = y + 1;
        }
    }
}