import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021051 {
    private static final int MAX_SIZE = 1000;
    private static int[][] wsPoint = new int[MAX_SIZE + 1][MAX_SIZE + 1];
    private static int wsResult = 0;

    public static void main(String[] args) {
        String inputFilePath = "d05.input";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
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
        int x1 = Integer.parseInt(parts[0].trim());
        int y1 = Integer.parseInt(parts[1].trim());
        int x2 = Integer.parseInt(parts[2].trim());
        int y2 = Integer.parseInt(parts[3].trim());

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i <= l; i++) {
                wsPoint[x1 + 1][i]++;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i <= l; i++) {
                wsPoint[i][y1 + 1]++;
            }
        }
    }

    private static void count() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            for (int j = 1; j <= MAX_SIZE; j++) {
                if (wsPoint[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}