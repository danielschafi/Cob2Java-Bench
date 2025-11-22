import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021091 {
    private static final int MAX_SIZE = 100;
    private static int[][] wsMap = new int[MAX_SIZE][MAX_SIZE];
    private static int wsResult = 0;
    private static int m = 100;
    private static int n = 100;
    private static int i = 0;
    private static int j = 0;
    private static int isLow;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                if (i >= MAX_SIZE) break;
                for (j = 0; j < inputRecord.length() && j < MAX_SIZE; j++) {
                    wsMap[i][j] = Character.getNumericValue(inputRecord.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        countLows();
        System.out.println(wsResult);
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
                    wsResult += wsMap[i][j] + 1;
                }
            }
        }
    }
}