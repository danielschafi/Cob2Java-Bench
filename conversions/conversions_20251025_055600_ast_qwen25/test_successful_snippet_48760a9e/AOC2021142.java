import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021142 {
    private static final int MAX_PAIRS = 100;
    private static final int MAX_LETTERS = 26;
    private static final int MAX_TEMPLATE_LENGTH = 20;

    private int fileStatus = 0;
    private int recLen = 0;
    private char[] wsArr = new char[MAX_TEMPLATE_LENGTH];
    private char[][] wsPairs = new char[MAX_PAIRS][3];
    private long[][] wsCounts = new long[MAX_PAIRS][3];
    private long[] wsLetters = new long[MAX_LETTERS];
    private int j = 1;
    private int m = 1;
    private long nMax = 0;
    private long nMin = 0;
    private long result = 0;

    public static void main(String[] args) {
        AOC2021142 program = new AOC2021142();
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen == 7) {
                    readPair(line);
                } else if (recLen > 0) {
                    readTemplate(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        m = j - 1;
        initCounts();
        for (int step = 0; step < 40; step++) {
            step();
        }
        countLetters();
        System.out.println(result);
    }

    private void readTemplate(String line) {
        for (int i = 0; i < recLen; i++) {
            wsArr[i] = line.charAt(i);
        }
    }

    private void readPair(String line) {
        wsPairs[j][0] = line.charAt(0);
        wsPairs[j][1] = line.charAt(1);
        wsPairs[j][2] = line.charAt(6);
        j++;
    }

    private void initCounts() {
        for (int i = 0; i < recLen - 1; i++) {
            for (int k = 1; k < j; k++) {
                if (wsPairs[k][0] == wsArr[i] && wsPairs[k][1] == wsArr[i + 1]) {
                    wsCounts[k][0]++;
                }
            }
        }
        for (int i = 0; i < recLen; i++) {
            int index = wsArr[i] - 'A';
            wsLetters[index]++;
        }
    }

    private void step() {
        for (int i = 1; i < j; i++) {
            wsCounts[i][1] += wsCounts[i][0];
            int index = wsPairs[i][2] - 'A';
            wsLetters[index] += wsCounts[i][0];

            for (int k = 1; k < j; k++) {
                if (wsPairs[k][0] == wsPairs[i][0] && wsPairs[k][1] == wsPairs[i][2]) {
                    wsCounts[k][2] += wsCounts[i][0];
                }
                if (wsPairs[k][0] == wsPairs[i][2] && wsPairs[k][1] == wsPairs[i][1]) {
                    wsCounts[k][2] += wsCounts[i][0];
                }
            }
        }

        for (int i = 1; i < j; i++) {
            wsCounts[i][0] = wsCounts[i][0] + wsCounts[i][2] - wsCounts[i][1];
            wsCounts[i][1] = 0;
            wsCounts[i][2] = 0;
        }
    }

    private void countLetters() {
        nMin = wsLetters[1];
        for (int i = 0; i < MAX_LETTERS; i++) {
            if (wsLetters[i] > nMax) {
                nMax = wsLetters[i];
            }
            if (wsLetters[i] > 0 && wsLetters[i] < nMin) {
                nMin = wsLetters[i];
            }
        }
        result = nMax - nMin;
    }
}