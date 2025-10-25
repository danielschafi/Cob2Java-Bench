import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020132 {
    private static final int MAX_BUSES = 99;
    private int[] wsBuses = new int[MAX_BUSES];
    private int[] wsRemainders = new int[MAX_BUSES];
    private int len;
    private int wsQuotient;
    private int wsMod;
    private long n;
    private long a;
    private long n1;
    private long a1;
    private long result;

    public static void main(String[] args) {
        AOC2020132 program = new AOC2020132();
        program.main();
    }

    private void main() {
        readInput();
        findTimestamp();
        System.out.println(result);
    }

    private void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            br.readLine(); // Skip first line
            String inputRecord = br.readLine();
            String[] values = inputRecord.split(",");
            int j = 0;
            for (String value : values) {
                if (!value.equals("x")) {
                    wsBuses[j] = Integer.parseInt(value);
                    wsMod = wsBuses[j] - j + 1;
                    if (wsMod < 0) {
                        wsMod += wsBuses[j];
                    }
                    wsRemainders[j] = wsMod;
                    j++;
                }
            }
            len = j;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findTimestamp() {
        n = wsBuses[0];
        a = wsRemainders[0];
        for (int i = 1; i < len; i++) {
            n1 = wsBuses[i];
            a1 = wsRemainders[i];
            wsMod = 0;
            wsQuotient = 1;
            while (wsMod != a1) {
                a += n;
                wsMod = (int) (a % n1);
            }
            n *= n1;
        }
        result = a;
    }
}