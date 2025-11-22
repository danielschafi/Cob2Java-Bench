import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202115 {
    private static final int N = 100;
    private static int[][] wsMapArr = new int[N][N];
    private static int[][] wsCostArr = new int[N][N];

    public static void main(String[] args) {
        try {
            readInput();
            computeCosts();
            int result = wsCostArr[N - 1][N - 1] - wsMapArr[0][0];
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d15.input"));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null && i < N) {
            for (int j = 0; j < line.length() && j < N; j++) {
                wsMapArr[i][j] = Character.getNumericValue(line.charAt(j));
            }
            i++;
        }
        reader.close();
    }

    private static void computeCosts() {
        wsCostArr[0][0] = wsMapArr[0][0];
        
        for (int i = 1; i < N; i++) {
            wsCostArr[0][i] = wsCostArr[0][i - 1] + wsMapArr[0][i];
            wsCostArr[i][0] = wsCostArr[i - 1][0] + wsMapArr[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                wsCostArr[i][j] = wsMapArr[i][j] + 
                    Math.min(wsCostArr[i - 1][j], wsCostArr[i][j - 1]);
            }
        }
    }
}