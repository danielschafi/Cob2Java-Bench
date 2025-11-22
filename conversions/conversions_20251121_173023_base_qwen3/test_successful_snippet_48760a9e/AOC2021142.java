import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021142 {
    private static final int MAX_PAIRS = 100;
    private static final int MAX_LENGTH = 20;
    private static final int MAX_LETTERS = 26;

    private static char[] template = new char[MAX_LENGTH];
    private static int templateLength = 0;

    private static char[] pair1 = new char[MAX_PAIRS];
    private static char[] pair2 = new char[MAX_PAIRS];
    private static char[] sub = new char[MAX_PAIRS];
    private static long[] count = new long[MAX_PAIRS];
    private static long[] created = new long[MAX_PAIRS];
    private static long[] broken = new long[MAX_PAIRS];
    private static long[] letters = new long[MAX_LETTERS];

    private static int pairCount = 0;

    public static void main(String[] args) {
        readInput();
        performSteps(40);
        countLetters();
    }

    private static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            boolean firstLineProcessed = false;
            while ((line = br.readLine()) != null) {
                if (!firstLineProcessed && line.length() == 7 && line.charAt(6) == '=') {
                    // Process pair rule
                    pair1[pairCount] = line.charAt(0);
                    pair2[pairCount] = line.charAt(1);
                    sub[pairCount] = line.charAt(6);
                    pairCount++;
                } else if (!line.isEmpty()) {
                    // Process template
                    for (int i = 0; i < line.length(); i++) {
                        template[i] = line.charAt(i);
                    }
                    templateLength = line.length();
                    firstLineProcessed = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performSteps(int steps) {
        initializeCounts();
        for (int step = 0; step < steps; step++) {
            stepOnce();
        }
    }

    private static void initializeCounts() {
        for (int i = 0; i < templateLength - 1; i++) {
            for (int k = 0; k < pairCount; k++) {
                if (pair1[k] == template[i] && pair2[k] == template[i + 1]) {
                    count[k]++;
                    break;
                }
            }
        }

        for (int i = 0; i < templateLength; i++) {
            int index = template[i] - 'A';
            letters[index]++;
        }
    }

    private static void stepOnce() {
        // Reset created and broken arrays
        for (int i = 0; i < pairCount; i++) {
            broken[i] = 0;
            created[i] = 0;
        }

        for (int i = 0; i < pairCount; i++) {
            if (count[i] > 0) {
                broken[i] += count[i];
                int letterIndex = sub[i] - 'A';
                letters[letterIndex] += count[i];

                for (int k = 0; k < pairCount; k++) {
                    if (pair1[k] == pair1[i] && pair2[k] == sub[i]) {
                        created[k] += count[i];
                    }
                    if (pair1[k] == sub[i] && pair2[k] == pair2[i]) {
                        created[k] += count[i];
                    }
                }
            }
        }

        for (int i = 0; i < pairCount; i++) {
            count[i] = count[i] + created[i] - broken[i];
        }
    }

    private static void countLetters() {
        long max = 0;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < MAX_LETTERS; i++) {
            if (letters[i] > max) {
                max = letters[i];
            }
            if (letters[i] > 0 && letters[i] < min) {
                min = letters[i];
            }
        }

        System.out.println(max - min);
    }
}