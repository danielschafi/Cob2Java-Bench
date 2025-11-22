import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021091 {
    private static final int MAX_SIZE = 100;
    private int[][] wsMap = new int[MAX_SIZE][MAX_SIZE];
    private int wsResult = 0;
    private int m = MAX_SIZE;
    private int n = MAX_SIZE;
    private int i = 1;
    private int j = 1;
    private int isLow;

    public static void main(String[] args) {
        AOC2021091 program = new AOC2021091();
        program.run();
    }

    private void run() {
        readFile("d09.input");
        countLows();
        System.out.println(wsResult);
    }

    private void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null && i <= MAX_SIZE) {
                for (j = 1; j <= line.length() && j <= MAX_SIZE; j++) {
                    wsMap[i][j] = Character.getNumericValue(line.charAt(j - 1));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countLows() {
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                isLow = 1;
                if (i > 1 && wsMap[i - 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j > 1 && wsMap[i][j - 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (i < m && wsMap[i + 1][j] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (j < n && wsMap[i][j + 1] <= wsMap[i][j]) {
                    isLow = 0;
                }
                if (isLow == 1) {
                    wsResult += wsMap[i][j] + 1;
                }
            }
        }
    }
}