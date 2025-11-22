import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20210901 {
    private static final int MAX_SIZE = 100;
    private static int[][] wsMap = new int[MAX_SIZE][MAX_SIZE];
    private static int wsResult = 0;
    private static int m = 0;
    private static int n = 100;

    public static void main(String[] args) {
        readInput();
        countLows();
        System.out.println(wsResult);
    }

    private static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < MAX_SIZE) {
                processRecord(line, i);
                i++;
                m = i;
            }
            if (m > 0 && !line.isEmpty()) {
                n = line.length();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processRecord(String inputRecord, int rowIndex) {
        int length = Math.min(inputRecord.length(), MAX_SIZE);
        for (int j = 0; j < length; j++) {
            char c = inputRecord.charAt(j);
            if (Character.isDigit(c)) {
                wsMap[rowIndex][j] = c - '0';
            }
        }
    }

    private static void countLows() {
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
                    wsResult = wsResult + wsMap[i][j] + 1;
                }
            }
        }
    }
}