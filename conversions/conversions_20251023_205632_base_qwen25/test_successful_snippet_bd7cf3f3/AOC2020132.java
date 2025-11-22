import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020132 {
    private static final int MAX_LEN = 99;
    private static int[] wsBuses = new int[MAX_LEN];
    private static int[] wsRemainders = new int[MAX_LEN];
    private static int len;
    private static int result;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            br.readLine(); // Skip first line
            br.readLine(); // Skip second line
            String inputRecord = br.readLine();
            parseInputRecord(inputRecord);
            findTimestamp();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInputRecord(String inputRecord) {
        String[] parts = inputRecord.split(",");
        len = 0;
        for (String part : parts) {
            if (!part.equals("x")) {
                wsBuses[len] = Integer.parseInt(part);
                wsRemainders[len] = (wsBuses[len] - len + 1) % wsBuses[len];
                if (wsRemainders[len] < 0) {
                    wsRemainders[len] += wsBuses[len];
                }
                len++;
            }
        }
    }

    private static void findTimestamp() {
        int n = wsBuses[0];
        int a = wsRemainders[0];
        for (int i = 1; i < len; i++) {
            int n1 = wsBuses[i];
            int a1 = wsRemainders[i];
            int mod = 0;
            int quotient = 1;
            while (mod != a1) {
                a += n;
                mod = a % n1;
            }
            n *= n1;
        }
        result = a;
    }
}