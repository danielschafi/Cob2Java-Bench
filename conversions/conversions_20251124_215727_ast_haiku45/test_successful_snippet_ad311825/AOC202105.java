import java.io.*;
import java.util.*;

public class AOC202105 {
    private static final int MAP_SIZE = 1000;
    private int[][] wsPoint = new int[MAP_SIZE + 1][MAP_SIZE + 1];
    private long wsResult = 0;
    private int fileStatus = 0;
    
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int l = 1;
    private int s = 1;
    private int x1 = 1;
    private int y1 = 1;
    private int x2 = 1;
    private int y2 = 1;
    
    public static void main(String[] args) {
        AOC202105 program = new AOC202105();
        program.run();
    }
    
    private void run() {
        try {
            read();
            count();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d05.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }
    
    private void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" -> ");
        String[] start = parts[0].split(",");
        String[] end = parts[1].split(",");
        
        x1 = Integer.parseInt(start[0].trim());
        y1 = Integer.parseInt(start[1].trim());
        x2 = Integer.parseInt(end[0].trim());
        y2 = Integer.parseInt(end[1].trim());
        
        if (x1 == x2) {
            k = Math.min(y1, y2) + 1;
            l = Math.max(y1, y2) + 1;
            for (i = k; i <= l; i++) {
                wsPoint[x1 + 1][i]++;
            }
        }
        
        if (y1 == y2) {
            k = Math.min(x1, x2) + 1;
            l = Math.max(x1, x2) + 1;
            for (i = k; i <= l; i++) {
                wsPoint[i][y1 + 1]++;
            }
        }
        
        if (x1 != x2 && y1 != y2) {
            k = Math.min(x1, x2) + 1;
            l = Math.max(x1, x2) + 1;
            
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
            
            for (i = k; i <= l; i++) {
                wsPoint[i][j]++;
                j += s;
            }
        }
    }
    
    private void count() {
        for (i = 1; i <= MAP_SIZE; i++) {
            for (j = 1; j <= MAP_SIZE; j++) {
                if (wsPoint[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}