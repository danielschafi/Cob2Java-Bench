import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021151 {
    private static final int N = 100;
    private static int[][] wsMap = new int[N][N];
    private static int[][] wsCost = new int[N][N];
    private static int fileStatus = 0;
    private static int result;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d15.input"))) {
            String inputRecord;
            int i = 0;
            while ((inputRecord = br.readLine()) != null) {
                for (int j = 0; j < N; j++) {
                    wsMap[i][j] = Character.getNumericValue(inputRecord.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        computeCosts();
        result = wsCost[N - 1][N - 1] - wsMap[0][0];
        System.out.println(result);
    }

    private static void computeCosts() {
        wsCost[0][0] = wsMap[0][0];
        for (int i = 1; i < N; i++) {
            wsCost[0][i] = wsCost[0][i - 1] + wsMap[0][i];
            wsCost[i][0] = wsCost[i - 1][0] + wsMap[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                wsCost[i][j] = wsMap[i][j] + Math.min(wsCost[i - 1][j], wsCost[i][j - 1]);
            }
        }
    }
}