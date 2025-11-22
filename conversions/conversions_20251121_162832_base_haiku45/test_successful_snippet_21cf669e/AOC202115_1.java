import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202115_1 {
    private static final int N = 100;
    private int[][] wsMap = new int[N][N];
    private int[][] wsCost = new int[N][N];

    public static void main(String[] args) {
        AOC202115_1 program = new AOC202115_1();
        program.run();
    }

    private void run() {
        readInput();
        computeCosts();
        int result = wsCost[N - 1][N - 1] - wsMap[0][0];
        System.out.println(result);
    }

    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d15.input"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < N) {
                for (int j = 0; j < line.length() && j < N; j++) {
                    wsMap[i][j] = Character.getNumericValue(line.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void computeCosts() {
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