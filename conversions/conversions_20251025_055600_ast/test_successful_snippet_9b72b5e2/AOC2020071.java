import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020071 {
    private static final int MAX_BAGS = 594;
    private static final int MAX_SUB_BAGS = 32;
    private static final int MAX_QUEUE = 9999;
    private static final int MAX_BUFFER = 32;

    private int fileStatus = 0;
    private int recLen;
    private String[] wsBuffer = new String[MAX_BUFFER];
    private String wsBag = new String(new char[32]);
    private String[][] wsBags = new String[MAX_BAGS][];
    private int[] wsBagDone = new int[MAX_BAGS];
    private int[] wsBagBagsNumber = new int[MAX_BAGS];
    private String[][] wsBagBags = new String[MAX_BAGS][MAX_SUB_BAGS];
    private String[] wsBagsQueue = new String[MAX_QUEUE];

    private int n = 0;
    private int result = 0;
    private int bagIdx = 1;
    private int i = 1;
    private int j;
    private int k;
    private int stringPtr = 1;
    private int q1 = 1;
    private int q2 = 1;

    public static void main(String[] args) {
        AOC2020071 program = new AOC2020071();
        program.execute();
    }

    private void execute() {
        readFile();
        walkGraph();
        countResult();
        System.out.println(q2);
        System.out.println(result);
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("d07.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                recLen = line.length();
                parseRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRecord(String inputRecord) {
        n++;
        stringPtr = 1;

        for (j = 0; j < MAX_BUFFER; j++) {
            int spaceIndex = inputRecord.indexOf(' ', stringPtr - 1);
            if (spaceIndex == -1) {
                wsBuffer[j] = inputRecord.substring(stringPtr - 1);
                break;
            }
            wsBuffer[j] = inputRecord.substring(stringPtr - 1, spaceIndex);
            stringPtr = spaceIndex + 2;
        }

        wsBags[i] = new String[32];
        wsBags[i][0] = wsBuffer[0] + " " + wsBuffer[1];
        wsBagColor(i, wsBags[i][0]);

        if (!wsBuffer[4].equals("no")) {
            parseSubBags();
        }
        i++;
    }

    private void parseSubBags() {
        k = 1;
        for (j = 4; j < MAX_BUFFER; j += 4) {
            if (!wsBuffer[j].substring(0, 1).equals(" ")) {
                wsBags[i][k] = wsBuffer[j + 1] + " " + wsBuffer[j + 2];
                k++;
            }
        }
        wsBagBagsNumber[i] = k - 1;
    }

    private void walkGraph() {
        wsBagsQueue[0] = "shiny gold";
        while (q1 <= q2) {
            wsBag = wsBagsQueue[q1 - 1];
            q1++;
            findBagIndex();
            wsBagDone[bagIdx - 1] = 1;

            for (i = 1; i <= n; i++) {
                if (wsBagDone[i - 1] == 0) {
                    for (j = 1; j <= wsBagBagsNumber[i - 1]; j++) {
                        if (wsBag.equals(wsBags[i][j])) {
                            q2++;
                            wsBagsQueue[q2 - 1] = wsBags[i][0];
                            break;
                        }
                    }
                }
            }
        }
    }

    private void findBagIndex() {
        for (k = 1; k <= n; k++) {
            if (wsBag.equals(wsBags[k][0])) {
                bagIdx = k;
                break;
            }
        }
    }

    private void countResult() {
        for (i = 1; i <= n; i++) {
            if (wsBagDone[i - 1] == 1) {
                result++;
            }
        }
        result--;
    }

    private void wsBagColor(int index, String color) {
        wsBags[index][0] = color;
    }
}