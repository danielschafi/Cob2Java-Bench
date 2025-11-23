import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AOC2021051 {
    private static final int SIZE = 1000;
    private static int[][] wsMap = new int[SIZE][SIZE];
    private static int wsResult = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d05.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        count();
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
        StringTokenizer tokenizer = new StringTokenizer(inputRecord, ", ->");
        int x1 = Integer.parseInt(tokenizer.nextToken());
        int y1 = Integer.parseInt(tokenizer.nextToken());
        int x2 = Integer.parseInt(tokenizer.nextToken());
        int y2 = Integer.parseInt(tokenizer.nextToken());

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i <= l; i++) {
                wsMap[x1][i] += 1;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i <= l; i++) {
                wsMap[i][y1] += 1;
            }
        }
    }

    private static void count() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (wsMap[i][j] > 1) {
                    wsResult += 1;
                }
            }
        }
    }
}