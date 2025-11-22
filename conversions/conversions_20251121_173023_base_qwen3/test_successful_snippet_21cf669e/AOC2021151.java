import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021151 {
    private static final int N = 100;
    private static int[][] map = new int[N][N];
    private static int[][] cost = new int[N][N];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d15.input"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < N) {
                for (int j = 0; j < line.length() && j < N; j++) {
                    map[i][j] = Character.getNumericValue(line.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        cost[0][0] = map[0][0];
        
        for (int i = 1; i < N; i++) {
            cost[0][i] = cost[0][i - 1] + map[0][i];
            cost[i][0] = cost[i - 1][0] + map[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                cost[i][j] = map[i][j] + Math.min(cost[i - 1][j], cost[i][j - 1]);
            }
        }

        int result = cost[N - 1][N - 1] - map[0][0];
        System.out.println(result);
    }
}