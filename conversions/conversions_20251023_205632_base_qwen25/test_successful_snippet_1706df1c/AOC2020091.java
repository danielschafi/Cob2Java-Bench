import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020091 {
    private static final int P_LEN = 25;
    private static final int MAX_LINES = 1000;
    private static int[] wsNumbers = new int[MAX_LINES];
    private static int wsSum;
    private static int wsResult;
    private static int fileStatus;
    private static int recLen;
    private static int i = 1;
    private static int j;
    private static int k;
    private static int j0;
    private static int foundNumber;
    private static int foundSum;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d09.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wsNumbers[i] = Integer.parseInt(line.trim());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        i = P_LEN + 1;
        while (foundNumber == 0) {
            foundSum = 0;

            j0 = i - P_LEN;
            for (j = j0; j < i; j++) {
                for (k = j; k < i; k++) {
                    wsSum = wsNumbers[j] + wsNumbers[k];
                    if (wsSum == wsNumbers[i]) {
                        foundSum = 1;
                        break;
                    }
                }
                if (foundSum == 1) {
                    break;
                }
            }

            if (foundSum == 0) {
                foundNumber = 1;
                wsResult = wsNumbers[i];
            }

            i++;
        }

        System.out.println(wsResult);
    }
}