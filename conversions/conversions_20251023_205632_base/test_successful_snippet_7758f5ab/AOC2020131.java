import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020131 {
    private static int recLen;
    private static int wsStart;
    private static int[] wsBuses = new int[99];
    private static int wsBuffer;
    private static int n = 99;
    private static int wsQuotient;
    private static int wsMod;
    private static int wsTime;
    private static int wsBusMin;
    private static int wsTimeMin = 99999;
    private static int result;
    private static int stringPtr;
    private static int i;
    private static int j;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d13.input"))) {
            read(reader);
            findEarliestBus();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read(BufferedReader reader) throws IOException {
        wsStart = Integer.parseInt(reader.readLine());
        String inputRecord = reader.readLine();
        stringPtr = 0;
        j = 1;
        while (j <= 99) {
            wsBuffer = 0;
            int commaIndex = inputRecord.indexOf(',', stringPtr);
            if (commaIndex == -1) {
                wsBuffer = Integer.parseInt(inputRecord.substring(stringPtr));
                stringPtr = inputRecord.length();
            } else {
                wsBuffer = Integer.parseInt(inputRecord.substring(stringPtr, commaIndex));
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
    }

    private static void findEarliestBus() {
        for (i = 0; i < n; i++) {
            wsQuotient = wsStart / wsBuses[i];
            wsMod = wsStart % wsBuses[i];
            wsTime = wsBuses[i] - wsMod;
            if (wsTime < wsTimeMin) {
                wsTimeMin = wsTime;
                wsBusMin = wsBuses[i];
            }
        }
        result = wsTimeMin * wsBusMin;
    }
}