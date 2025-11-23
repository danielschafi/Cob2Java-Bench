import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2020071 {
    private static final int MAX_BAGS = 594;
    private static final int MAX_QUEUE_SIZE = 9999;
    private static final int MAX_BUFFER_SIZE = 32;
    private static final int MAX_RECORD_LENGTH = 128;

    private static class Bag {
        String color;
        boolean done;
        int subBagCount;
        String[] subBags = new String[MAX_BUFFER_SIZE];
    }

    private static Bag[] bags = new Bag[MAX_BAGS];
    private static String[] queue = new String[MAX_QUEUE_SIZE];
    private static String[] buffer = new String[MAX_BUFFER_SIZE];

    private static int n = 0;
    private static int result = 0;
    private static int bagIdx = 0;
    private static int i = 0;
    private static int j = 0;
    private static int k = 0;
    private static int stringPtr = 0;
    private static int q1 = 1;
    private static int q2 = 1;

    public static void main(String[] args) {
        try {
            readInput();
            walkGraph();
            countResult();
            System.out.println(q2);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d07.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            parseRecord(line);
        }
        reader.close();
    }

    private static void parseRecord(String inputRecord) {
        n++;
        stringPtr = 1;

        // Unstring the input record
        StringTokenizer tokenizer = new StringTokenizer(inputRecord);
        int index = 0;
        while (tokenizer.hasMoreTokens() && index < MAX_BUFFER_SIZE) {
            buffer[index++] = tokenizer.nextToken();
        }

        // Build bag color
        StringBuilder sb = new StringBuilder();
        sb.append(buffer[0]).append(" ").append(buffer[1]);
        bags[i] = new Bag();
        bags[i].color = sb.toString();
        bags[i].done = false;
        bags[i].subBagCount = 0;

        if (!buffer[4].equals("no")) {
            parseSubBags();
        }
        i++;
    }

    private static void parseSubBags() {
        k = 1;
        for (j = 4; j < MAX_BUFFER_SIZE; j += 4) {
            if (j < buffer.length && buffer[j] != null && !buffer[j].isEmpty() && buffer[j].charAt(0) != ' ') {
                StringBuilder sb = new StringBuilder();
                sb.append(buffer[j + 1]).append(" ").append(buffer[j + 2]);
                bags[i].subBags[k - 1] = sb.toString();
                k++;
            }
        }
        bags[i].subBagCount = k - 1;
    }

    private static void walkGraph() {
        queue[0] = "shiny gold";
        q2 = 1;
        while (q1 <= q2) {
            walkGraphLoop();
        }
    }

    private static void walkGraphLoop() {
        String currentBag = queue[q1 - 1];
        q1++;
        findBagIndex(currentBag);
        bags[bagIdx].done = true;

        for (int idx = 0; idx < n; idx++) {
            if (!bags[idx].done) {
                for (int subIdx = 0; subIdx < bags[idx].subBagCount; subIdx++) {
                    if (currentBag.equals(bags[idx].subBags[subIdx])) {
                        q2++;
                        queue[q2 - 1] = bags[idx].color;
                        break;
                    }
                }
            }
        }
    }

    private static void findBagIndex(String bag) {
        for (int idx = 0; idx < n; idx++) {
            if (bag.equals(bags[idx].color)) {
                bagIdx = idx;
                return;
            }
        }
    }

    private static void countResult() {
        for (int idx = 0; idx < n; idx++) {
            if (bags[idx].done) {
                result++;
            }
        }
        result--;
    }
}