import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202109 {
    private static int[][] wsMap = new int[100][100];
    private static int wsResult = 0;
    private static int m = 100;
    private static int n = 100;
    private static int i = 1;
    private static int j = 1;
    private static int isLow = 0;
    private static int fileStatus = 0;
    private static int mapRows = 0;

    public static void main(String[] args) {
        readFile();
        countLows();
        System.out.println(wsResult);
    }

    private static void readFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d09.input"));
            mapRows = lines.size();
            
            for (int idx = 0; idx < lines.size(); idx++) {
                String line = lines.get(idx);
                for (int col = 0; col < line.length(); col++) {
                    wsMap[idx][col] = Character.getNumericValue(line.charAt(col));
                }
                n = line.length();
            }
            m = mapRows;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countLows() {
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
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
                    wsResult = wsResult + wsMap[i][j] + 1;
                }
            }
        }
    }
}