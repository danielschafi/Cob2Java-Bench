import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020071 {
    private static final int MAX_BAGS = 594;
    private static final int MAX_BAGS_PER_BAG = 32;
    private static final int MAX_QUEUE_SIZE = 9999;
    private static final int MAX_BUFFER_SIZE = 32;
    private static final int MAX_RECORD_LENGTH = 128;

    private int fileStatus = 0;
    private int recLen;
    private String[] wsBuffer = new String[MAX_BUFFER_SIZE];
    private String wsBag = new String(new char[32]);
    private Bag[] wsBags = new Bag[MAX_BAGS];
    private String[] wsBagsQueue = new String[MAX_QUEUE_SIZE];
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
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d07.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                parseRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        walkGraph();
        countResult();
        System.out.println(q2);
        System.out.println(result);
    }

    private void parseRecord(String inputRecord) {
        n++;
        stringPtr = 1;

        for (j = 1; j <= 32; j++) {
            int spaceIndex = inputRecord.indexOf(' ', stringPtr - 1);
            if (spaceIndex == -1) {
                wsBuffer[j - 1] = inputRecord.substring(stringPtr - 1);
                break;
            }
            wsBuffer[j - 1] = inputRecord.substring(stringPtr - 1, spaceIndex);
            stringPtr = spaceIndex + 1;
        }

        wsBags[i - 1] = new Bag();
        wsBags[i - 1].wsBagColor = wsBuffer[0] + " " + wsBuffer[1];

        if (!wsBuffer[4].equals("no")) {
            parseSubBags();
        }
        i++;
    }

    private void parseSubBags() {
        k = 1;
        for (j = 5; j <= 32; j += 4) {
            if (!wsBuffer[j - 1].substring(0, 1).equals(" ")) {
                wsBags[i - 1].wsBagBags[k - 1] = wsBuffer[j] + " " + wsBuffer[j + 1];
                k++;
            }
        }
        wsBags[i - 1].wsBagBagsNumber = k - 1;
    }

    private void walkGraph() {
        wsBagsQueue[0] = "shiny gold";
        while (q1 <= q2) {
            wsBag = wsBagsQueue[q1 - 1];
            q1++;
            findBagIndex();
            wsBags[bagIdx - 1].wsBagDone = 1;

            for (i = 1; i <= n; i++) {
                if (wsBags[i - 1].wsBagDone == 0) {
                    for (j = 1; j <= wsBags[i - 1].wsBagBagsNumber; j++) {
                        if (wsBag.equals(wsBags[i - 1].wsBagBags[j - 1])) {
                            q2++;
                            wsBagsQueue[q2 - 1] = wsBags[i - 1].wsBagColor;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void findBagIndex() {
        for (k = 1; k <= n; k++) {
            if (wsBag.equals(wsBags[k - 1].wsBagColor)) {
                bagIdx = k;
            }
        }
    }

    private void countResult() {
        for (i = 1; i <= n; i++) {
            if (wsBags[i - 1].wsBagDone == 1) {
                result++;
            }
        }
        result--;
    }

    private static class Bag {
        String wsBagColor = new String(new char[32]);
        int wsBagDone = 0;
        int wsBagBagsNumber = 0;
        String[] wsBagBags = new String[MAX_BAGS_PER_BAG];
    }
}