import java.io.*;
import java.util.*;

public class AOC202115 {
    private static final int N = 100;
    private int[][] wsMap = new int[N][N];
    private int[][] wsCost = new int[N][N];
    private int fileStatus = 0;
    private int i = 0;
    private int j = 0;
    private int result = 0;

    public static void main(String[] args) {
        AOC202115 program = new AOC202115();
        program.run();
    }

    private void run() {
        readInput();
        computeCosts();
        result = wsCost[N - 1][N - 1] - wsMap[0][0];
        System.out.println(result);
    }

    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d15.input"))) {
            String line;
            i = 0;
            while ((line = reader.readLine()) != null && i < N) {
                processRecord(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String record) {
        for (int col = 0; col < record.length() && col < N; col++) {
            wsMap[i][col] = Character.getNumericValue(record.charAt(col));
        }
    }

    private void computeCosts() {
        wsCost[0][0] = wsMap[0][0];

        for (i = 1; i < N; i++) {
            wsCost[0][i] = wsCost[0][i - 1] + wsMap[0][i];
            wsCost[i][0] = wsCost[i - 1][0] + wsMap[i][0];
        }

        for (i = 1; i < N; i++) {
            for (j = 1; j < N; j++) {
                wsCost[i][j] = wsMap[i][j] + Math.min(wsCost[i - 1][j], wsCost[i][j - 1]);
            }
        }
    }
}