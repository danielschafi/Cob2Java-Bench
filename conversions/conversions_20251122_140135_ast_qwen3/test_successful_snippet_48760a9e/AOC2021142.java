import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021142 {
    private static final String INPUT_FILE = "d14.input";
    
    // File and record handling
    private static int fileStatus = 0;
    private static int recLen = 0;
    private static char[] wsArr = new char[20];
    private static char[] wsPair1 = new char[100];
    private static char[] wsPair2 = new char[100];
    private static char[] wsSub = new char[100];
    private static long[] wsCount = new long[100];
    private static long[] wsCreated = new long[100];
    private static long[] wsBroken = new long[100];
    private static long[] wsLetters = new long[26];
    
    // Loop counters and variables
    private static long i = 1;
    private static long j = 1;
    private static long k = 1;
    private static long n = 1;
    private static long m = 1;
    private static long nMax = 0;
    private static long nMin = 0;
    private static long result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen == 7) {
                    readPair(line);
                } else if (recLen > 0) {
                    readTemplate(line);
                }
            }
            
            m = j - 1;
            initCounts();
            for (int step = 0; step < 40; step++) {
                stepProcess();
            }
            countLetters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readTemplate(String inputRecord) {
        for (i = 0; i < recLen; i++) {
            wsArr[(int)i] = inputRecord.charAt((int)i);
        }
        n = recLen;
    }

    private static void readPair(String inputRecord) {
        wsPair1[(int)(j - 1)] = inputRecord.charAt(0);
        wsPair2[(int)(j - 1)] = inputRecord.charAt(1);
        wsSub[(int)(j - 1)] = inputRecord.charAt(6);
        j++;
    }

    private static void initCounts() {
        for (i = 0; i < n; i++) {
            for (k = 0; k < m; k++) {
                if (wsPair1[(int)k] == wsArr[(int)i] && 
                    wsPair2[(int)k] == wsArr[(int)(i + 1)]) {
                    wsCount[(int)k]++;
                }
            }
        }
        
        for (i = 0; i < n; i++) {
            int index = (int)(Character.toUpperCase(wsArr[(int)i]) - 'A');
            wsLetters[index]++;
        }
    }

    private static void stepProcess() {
        for (i = 0; i < m; i++) {
            wsBroken[(int)i] += wsCount[(int)i];
            int jIndex = (int)(Character.toUpperCase(wsSub[(int)i]) - 'A');
            wsLetters[jIndex] += wsCount[(int)i];

            for (k = 0; k < m; k++) {
                if (wsPair1[(int)k] == wsPair1[(int)i] && 
                    wsPair2[(int)k] == wsSub[(int)i]) {
                    wsCreated[(int)k] += wsCount[(int)i];
                }
                if (wsPair1[(int)k] == wsSub[(int)i] && 
                    wsPair2[(int)k] == wsPair2[(int)i]) {
                    wsCreated[(int)k] += wsCount[(int)i];
                }
            }
        }

        for (i = 0; i < m; i++) {
            wsCount[(int)i] = wsCount[(int)i] + wsCreated[(int)i] - wsBroken[(int)i];
            wsCreated[(int)i] = 0;
            wsBroken[(int)i] = 0;
        }
    }

    private static void countLetters() {
        nMin = wsLetters[1]; // Initialize with second letter count
        for (i = 0; i < 26; i++) {
            if (wsLetters[(int)i] > nMax) {
                nMax = wsLetters[(int)i];
            }
            if (wsLetters[(int)i] > 0 && wsLetters[(int)i] < nMin) {
                nMin = wsLetters[(int)i];
            }
        }

        result = nMax - nMin;
        System.out.println(result);
    }
}