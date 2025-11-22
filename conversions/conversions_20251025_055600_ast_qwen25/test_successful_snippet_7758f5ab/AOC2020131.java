import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020131 {
    private static final int MAX_BUSES = 99;
    private static final int MAX_RECORD_LENGTH = 200;

    private int recLen;
    private long wsStart;
    private int[] wsBuses = new int[MAX_BUSES];
    private int wsBuffer;
    private int n = MAX_BUSES;
    private int wsQuotient;
    private int wsMod;
    private int wsTime;
    private int wsBusMin;
    private int wsTimeMin = 99999;

    private long result;
    private int stringPtr = 1;
    private int i;
    private int j = 1;

    public static void main(String[] args) {
        AOC2020131 program = new AOC2020131();
        program.run();
    }

    private void run() {
        readInput();
        findEarliestBus();
        System.out.println(result);
    }

    private void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            wsStart = Long.parseLong(br.readLine().trim());
            String inputRecord = br.readLine();
            while (j <= MAX_BUSES) {
                wsBuffer = 0;
                int commaIndex = inputRecord.indexOf(',', stringPtr);
                if (commaIndex == -1) {
                    wsBuffer = Integer.parseInt(inputRecord.substring(stringPtr).trim());
                    stringPtr = inputRecord.length();
                } else {
                    wsBuffer = Integer.parseInt(inputRecord.substring(stringPtr, commaIndex).trim());
                    stringPtr = commaIndex + 1;
                }
                if (wsBuffer != 0) {
                    wsBuses[j - 1] = wsBuffer;
                    j++;
                }
                if (stringPtr >= inputRecord.length()) {
                    break;
                }
            }
            n = j - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findEarliestBus() {
        for (i = 0; i < n; i++) {
            wsQuotient = (int) (wsStart / wsBuses[i]);
            wsMod = (int) (wsStart % wsBuses[i]);
            wsTime = wsBuses[i] - wsMod;
            if (wsTime < wsTimeMin) {
                wsTimeMin = wsTime;
                wsBusMin = wsBuses[i];
            }
        }
        result = (long) wsTimeMin * wsBusMin;
    }
}