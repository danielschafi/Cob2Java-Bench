import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AOC2021141 {
    private static final int MAX_RECORD_LENGTH = 20;
    private static final int MAX_PAIRS = 100;
    private static final int MAX_CHARS = 100000;

    private static char[] wsArr = new char[MAX_CHARS];
    private static char[] wsArr2 = new char[MAX_CHARS];
    private static char[] wsPair1 = new char[MAX_PAIRS];
    private static char[] wsPair2 = new char[MAX_PAIRS];
    private static char[] wsSub = new char[MAX_PAIRS];
    private static int[] wsLetters = new int[26];
    private static int j = 1;
    private static int n = 1;
    private static int m = 1;
    private static int nMax = 0;
    private static int nMin = 0;
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 7) {
                    readPair(line);
                } else if (!line.isEmpty()) {
                    readTemplate(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        m = j - 1;
        for (int i = 0; i < 10; i++) {
            step();
        }
        countLetters();
        System.out.println(result);
    }

    private static void readPair(String line) {
        wsPair1[j - 1] = line.charAt(0);
        wsPair2[j - 1] = line.charAt(1);
        wsSub[j - 1] = line.charAt(6);
        j++;
    }

    private static void readTemplate(String line) {
        for (int i = 0; i < line.length(); i++) {
            wsArr[i] = line.charAt(i);
        }
        n = line.length();
    }

    private static void step() {
        j = 1;
        for (int i = 0; i < n - 1; i++) {
            wsArr2[j - 1] = wsArr[i];
            j++;
            for (int k = 0; k < m; k++) {
                if (wsPair1[k] == wsArr[i] && wsPair2[k] == wsArr[i + 1]) {
                    wsArr2[j - 1] = wsSub[k];
                    j++;
                    break;
                }
            }
        }
        wsArr2[j - 1] = wsArr[n - 1];
        n = j;
        System.arraycopy(wsArr2, 0, wsArr, 0, n);
    }

    private static void countLetters() {
        Arrays.fill(wsLetters, 0);
        for (int i = 0; i < n; i++) {
            int index = (int) wsArr[i] - (int) 'A';
            wsLetters[index]++;
        }
        nMin = wsLetters[1];
        for (int i = 0; i < 26; i++) {
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