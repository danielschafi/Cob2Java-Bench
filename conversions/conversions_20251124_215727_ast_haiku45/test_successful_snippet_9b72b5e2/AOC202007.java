import java.io.*;
import java.util.*;

public class AOC202007 {
    static class Bag {
        String color;
        int done = 0;
        int bagsNumber = 0;
        String[] bags = new String[32];
    }

    static int n = 0;
    static int result = 0;
    static int bagIdx = 1;
    static int i = 1;
    static int j = 1;
    static int k = 1;
    static int stringPtr = 1;
    static int q1 = 1;
    static int q2 = 1;

    static String[] wsBuffer = new String[32];
    static String wsBag = "";
    static Bag[] wsBags = new Bag[594];
    static String[] wsBagsQueue = new String[9999];

    public static void main(String[] args) throws IOException {
        for (int idx = 0; idx < 594; idx++) {
            wsBags[idx] = new Bag();
            for (int b = 0; b < 32; b++) {
                wsBags[idx].bags[b] = "";
            }
        }
        for (int idx = 0; idx < 32; idx++) {
            wsBuffer[idx] = "";
        }
        for (int idx = 0; idx < 9999; idx++) {
            wsBagsQueue[idx] = "";
        }

        read();
        walkGraph();
        countResult();
        System.out.println(q2);
        System.out.println(result);
    }

    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("d07.input"));
        String line;
        while ((line = br.readLine()) != null) {
            parseRecord(line);
        }
        br.close();
    }

    static void parseRecord(String inputRecord) {
        n++;
        stringPtr = 0;

        String[] tokens = inputRecord.split(" ");
        for (int idx = 0; idx < Math.min(tokens.length, 32); idx++) {
            wsBuffer[idx] = tokens[idx];
        }

        wsBags[i - 1].color = wsBuffer[0] + " " + wsBuffer[1];

        if (!wsBuffer[4].equals("no")) {
            parseSubBags();
        }
        i++;
    }

    static void parseSubBags() {
        k = 0;
        for (j = 4; j < 32; j += 4) {
            if (j < wsBuffer.length && !wsBuffer[j].isEmpty() && !wsBuffer[j].equals(" ")) {
                if (j + 2 < wsBuffer.length) {
                    wsBags[i - 1].bags[k] = wsBuffer[j + 1] + " " + wsBuffer[j + 2];
                    k++;
                }
            }
        }
        wsBags[i - 1].bagsNumber = k;
    }

    static void walkGraph() {
        wsBagsQueue[0] = "shiny gold";
        q1 = 1;
        q2 = 1;
        while (q1 <= q2) {
            walkGraphLoop();
        }
    }

    static void walkGraphLoop() {
        wsBag = wsBagsQueue[q1 - 1];
        q1++;
        findBagIndex();
        wsBags[bagIdx - 1].done = 1;

        for (i = 1; i <= n; i++) {
            if (wsBags[i - 1].done == 0) {
                for (j = 1; j <= wsBags[i - 1].bagsNumber; j++) {
                    if (wsBag.equals(wsBags[i - 1].bags[j - 1])) {
                        q2++;
                        wsBagsQueue[q2 - 1] = wsBags[i - 1].color;
                        break;
                    }
                }
            }
        }
    }

    static void findBagIndex() {
        for (k = 1; k <= n; k++) {
            if (wsBag.equals(wsBags[k - 1].color)) {
                bagIdx = k;
            }
        }
    }

    static void countResult() {
        for (i = 1; i <= n; i++) {
            if (wsBags[i - 1].done == 1) {
                result++;
            }
        }
        result--;
    }
}