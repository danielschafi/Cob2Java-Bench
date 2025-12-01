import java.io.*;
import java.util.*;

public class AOC202114 {
    private static final int MAX_PAIRS = 100;
    private static final int MAX_TEMPLATE = 20;
    private static final int MAX_LETTERS = 26;
    
    private int fileStatus = 0;
    private int recLen = 0;
    private char[] wsArr = new char[MAX_TEMPLATE];
    private char[] wsPair1 = new char[MAX_PAIRS];
    private char[] wsPair2 = new char[MAX_PAIRS];
    private char[] wsSub = new char[MAX_PAIRS];
    private long[] wsCount = new long[MAX_PAIRS];
    private long[] wsCreated = new long[MAX_PAIRS];
    private long[] wsBroken = new long[MAX_PAIRS];
    private long[] wsLetters = new long[MAX_LETTERS];
    
    private long i = 1;
    private long j = 1;
    private long k = 1;
    private long n = 1;
    private long m = 1;
    private long nMax = 0;
    private long nMin = 0;
    private long result = 0;
    
    public static void main(String[] args) throws IOException {
        AOC202114 program = new AOC202114();
        program.run();
    }
    
    private void run() throws IOException {
        readInput();
        m = j - 1;
        initCounts();
        for (int step = 0; step < 40; step++) {
            performStep();
        }
        countLetters();
        System.out.println(result);
    }
    
    private void readInput() throws IOException {
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
        }
    }
    
    private void readTemplate(String line) {
        for (int idx = 0; idx < recLen; idx++) {
            wsArr[idx] = line.charAt(idx);
        }
        n = recLen;
    }
    
    private void readPair(String line) {
        int idx = (int) j - 1;
        wsPair1[idx] = line.charAt(0);
        wsPair2[idx] = line.charAt(1);
        wsSub[idx] = line.charAt(6);
        j++;
    }
    
    private void initCounts() {
        for (i = 1; i < n; i++) {
            for (k = 1; k <= m; k++) {
                int kIdx = (int) k - 1;
                int iIdx = (int) i - 1;
                int iNext = (int) i;
                if (wsPair1[kIdx] == wsArr[iIdx] && wsPair2[kIdx] == wsArr[iNext]) {
                    wsCount[kIdx]++;
                }
            }
        }
        
        for (i = 1; i <= n; i++) {
            int iIdx = (int) i - 1;
            j = wsArr[iIdx] - 'A' + 1;
            int jIdx = (int) j - 1;
            wsLetters[jIdx]++;
        }
    }
    
    private void performStep() {
        for (i = 1; i <= m; i++) {
            int iIdx = (int) i - 1;
            wsBroken[iIdx] += wsCount[iIdx];
            j = wsSub[iIdx] - 'A' + 1;
            int jIdx = (int) j - 1;
            wsLetters[jIdx] += wsCount[iIdx];
            
            for (k = 1; k <= m; k++) {
                int kIdx = (int) k - 1;
                if (wsPair1[kIdx] == wsPair1[iIdx] && wsPair2[kIdx] == wsSub[iIdx]) {
                    wsCreated[kIdx] += wsCount[iIdx];
                }
                if (wsPair1[kIdx] == wsSub[iIdx] && wsPair2[kIdx] == wsPair2[iIdx]) {
                    wsCreated[kIdx] += wsCount[iIdx];
                }
            }
        }
        
        for (i = 1; i <= m; i++) {
            int iIdx = (int) i - 1;
            wsCount[iIdx] = wsCount[iIdx] + wsCreated[iIdx] - wsBroken[iIdx];
            wsCreated[iIdx] = 0;
            wsBroken[iIdx] = 0;
        }
    }
    
    private void countLetters() {
        nMin = wsLetters[1];
        for (i = 1; i <= 26; i++) {
            int iIdx = (int) i - 1;
            if (wsLetters[iIdx] > nMax) {
                nMax = wsLetters[iIdx];
            }
            if (wsLetters[iIdx] > 0 && wsLetters[iIdx] < nMin) {
                nMin = wsLetters[iIdx];
            }
        }
        
        result = nMax - nMin;
    }
}