import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021151 {
    private static final int N = 100;
    private static final int[][] WS_MAP = new int[N][N];
    private static final int[][] WS_COST = new int[N][N];
    private static int I = 1;
    private static int J = 1;
    private static int RESULT;

    public static void main(String[] args) {
        int fileStatus = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("d15.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                for (int k = 0; k < inputRecord.length(); k++) {
                    WS_MAP[I - 1][k] = Character.getNumericValue(inputRecord.charAt(k));
                }
                I++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        if (fileStatus == 0) {
            computeCosts();
            RESULT = WS_COST[N - 1][N - 1] - WS_MAP[0][0];
            System.out.println(RESULT);
        }
    }

    private static void computeCosts() {
        WS_COST[0][0] = WS_MAP[0][0];
        for (I = 1; I < N; I++) {
            WS_COST[0][I] = WS_COST[0][I - 1] + WS_MAP[0][I];
            WS_COST[I][0] = WS_COST[I - 1][0] + WS_MAP[I][0];
        }

        for (I = 1; I < N; I++) {
            for (J = 1; J < N; J++) {
                WS_COST[I][J] = WS_MAP[I][J] + Math.min(WS_COST[I - 1][J], WS_COST[I][J - 1]);
            }
        }
    }
}