import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021141 {
    private static final int MAX_SIZE = 100000;
    private static final int MAX_PAIRS = 100;
    private static final int MAX_LETTERS = 26;

    private int fileStatus = 0;
    private int recLen;
    private char[] wsArr = new char[MAX_SIZE];
    private char[] wsArr2 = new char[MAX_SIZE];
    private char[][] wsPairs = new char[MAX_PAIRS][3];
    private int[] wsLetters = new int[MAX_LETTERS];

    private int i, i1, j, k, l, n, m, nMax, nMin, result;

    public static void main(String[] args) {
        AOC2021141 program = new AOC2021141();
        program.execute();
    }

    private void execute() {
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
        for (int step = 0; step < 10; step++) {
            step();
        }
        countLetters();
        System.out.println(result);
    }

    private void readTemplate(String line) {
        for (i = 0; i < recLen; i++) {
            wsArr[i] = line.charAt(i);
        }
        n = recLen;
    }

    private void readPair(String line) {
        wsPairs[j][0] = line.charAt(0);
        wsPairs[j][1] = line.charAt(1);
        wsPairs[j][2] = line.charAt(6);
        j++;
    }

    private void step() {
        j = 0;
        for (i = 0; i < n; i++) {
            wsArr2[j] = wsArr[i];
            j++;
            for (k = 0; k < m; k++) {
                if (wsPairs[k][0] == wsArr[i] && wsPairs[k][1] == wsArr[i + 1]) {
                    wsArr2[j] = wsPairs[k][2];
                    j++;
                }
            }
        }
        wsArr2[j] = wsArr[n - 1];
        n = j + 1;
        for (i = 0; i < n; i++) {
            wsArr[i] = wsArr2[i];
        }
    }

    private void countLetters() {
        for (i = 0; i < n; i++) {
            j = wsArr[i] - 'A';
            wsLetters[j]++;
        }
        nMin = wsLetters[1];
        for (i = 0; i < MAX_LETTERS; i++) {
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