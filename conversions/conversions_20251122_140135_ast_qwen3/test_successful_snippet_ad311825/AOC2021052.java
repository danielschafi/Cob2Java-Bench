import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AOC2021052 {
    private static final int[][] wsMap = new int[1000][1000];
    private static int wsResult = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        count();
        System.out.println(wsResult);
    }
    
    private static void processRecord(String inputRecord) {
        String[] parts = inputRecord.split("[,\\s]+");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);
        
        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i < l; i++) {
                wsMap[x1][i] += 1;
            }
        }
        
        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i < l; i++) {
                wsMap[i][y1] += 1;
            }
        }
        
        if (x1 != x2 && y1 != y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            int j;
            int s;
            
            if (x1 < x2) {
                j = y1 + 1;
                if (y1 < y2) {
                    s = 1;
                } else {
                    s = -1;
                }
            } else {
                j = y2 + 1;
                if (y1 < y2) {
                    s = -1;
                } else {
                    s = 1;
                }
            }
            
            for (int i = k; i < l; i++) {
                wsMap[i][j] += 1;
                j += s;
            }
        }
    }
    
    private static void count() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (wsMap[i][j] > 1) {
                    wsResult += 1;
                }
            }
        }
    }
}