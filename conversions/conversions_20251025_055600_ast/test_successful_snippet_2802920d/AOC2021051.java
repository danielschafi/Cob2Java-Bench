import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021051 {
    private static final int SIZE = 1000;
    private static int[][] wsPoint = new int[SIZE][SIZE];
    private static int wsResult = 0;

    public static void main(String[] args) {
        String inputFileName = "d05.input";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        count();
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(",| -> ");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i < l; i++) {
                wsPoint[x1][i]++;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i < l; i++) {
                wsPoint[i][y1]++;
            }
        }
    }

    private static void count() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (wsPoint[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}