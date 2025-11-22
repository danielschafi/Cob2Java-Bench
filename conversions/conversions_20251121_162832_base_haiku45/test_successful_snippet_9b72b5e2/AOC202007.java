```java
import java.io.*;
import java.util.*;

public class AOC202007 {
    static class Bag {
        String color;
        int done = 0;
        int bagsNumber = 0;
        String[] bags = new String[32];
    }

    static int fileStatus = 0;
    static int n = 0;
    static int result = 0;
    static int bagIdx = 1;
    static int q1 = 1;
    static int q2 = 1;

    static Bag[] wsBags = new Bag[594];
    static String[] wsBuffer = new String[32];
    static String[] wsQueue = new String[9999];

    public static void main(String[] args) {
        for (int i = 0; i < wsBags.length; i++) {
            wsBags[i] = new Bag();
        }

        try {
            readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        walkGraph();
        countResult();
        System.out.println(q2);
        System.out.println(result);
    }

    static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d07.input"));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            parseRecord(line, i);
            i++;
        }
        n = i;
        reader.close();
    }

    static void parseRecord(String inputRecord, int idx) {
        for (int j = 0; j < 32; j++) {
            wsBuffer[j] = "";
        }

        String[] parts = inputRecord.split(" ");
        for (int j = 0; j < parts.length && j < 32; j++) {
            wsBuffer[j] = parts[j];
        }

        String bagColor = wsBuffer[0] + " " + wsBuffer[1];
        wsBags[idx].color = bagColor;

        if (!wsBuffer[4].equals("no")) {
            parseSubBags(idx);
        }
    }

    static void parseSubBags(int idx) {
        int k = 0;
        for (int j = 4; j < 32; j += 4) {
            if (j < wsBuffer.length && !wsBuffer[j].isEmpty() && !wsBuffer[j].equals(" ")) {
                if (j + 2 < wsBuffer.length) {
                    String bagName = wsBuffer[j + 1] + " " + wsBuffer[j + 2];
                    wsBags[idx].bags[k] = bagName;
                    k++;
                }
            }
        }
        wsBags[idx].bagsNumber = k;
    }

    static void walkGraph() {
        wsQueue[0] = "shiny gold";
        q1 = 1;
        q2 = 1;

        while (q1 <= q2) {
            walkGraphLoop();
        }
    }

    static void walkGraphLoop() {
        String wsBag = wsQueue[q1 - 1];
        q1++;
        findBagIndex(wsBag);
        wsBags[bagIdx - 1].done = 1;

        for (int i = 0; i < n; i++) {
            if (wsBags[i].done == 0) {
                for (int j = 0; j < wsBags[i].bagsNumber; j++) {
                    if (wsBag.equals(wsBags[i].bags[j])) {
                        q2++;
                        wsQueue[q2 - 1] = wsBags[i].color;
                        break;
                    }
                }
            }
        }
    }

    static void findBagIndex(String wsBag) {
        for (int k = 0; k < n; k++) {
            if (wsBag.equals(wsBags[k].color)) {
                bagIdx = k + 1;
            }
        }
    }

    static void countResult() {
        result = 0;
        for (int i = 0; i < n; i++) {
            if (wsBags[i].done == 1) {
                result++;
            }
        }
        result--;
    }
}