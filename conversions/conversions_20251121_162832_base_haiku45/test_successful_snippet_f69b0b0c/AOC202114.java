import java.io.*;
import java.util.*;

public class AOC202114 {
    private static final int MAX_ARR = 100000;
    private static final int MAX_PAIRS = 100;
    
    private char[] wsArr = new char[MAX_ARR];
    private char[] wsArr2 = new char[MAX_ARR];
    private char[] wsPair1 = new char[MAX_PAIRS];
    private char[] wsPair2 = new char[MAX_PAIRS];
    private char[] wsSub = new char[MAX_PAIRS];
    private long[] wsLetters = new long[26];
    
    private int fileStatus = 0;
    private int recLen = 0;
    private int i = 1;
    private int i1 = 1;
    private int j = 1;
    private int k = 1;
    private int l = 1;
    private int n = 1;
    private int m = 1;
    private long nMax = 0;
    private long nMin = 0;
    private long result = 0;
    
    public static void main(String[] args) {
        AOC202114 program = new AOC202114();
        program.run();
    }
    
    private void run() {
        try {
            read();
            m = j - 1;
            for (int step = 0; step < 10; step++) {
                step();
            }
            countLetters();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
    
    private void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("d14.input"));
        String line;
        
        while ((line = br.readLine()) != null) {
            recLen = line.length();
            processRecord(line);
        }
        br.close();
    }
    
    private void processRecord(String line) {
        if (recLen == 7) {
            readPair(line);
        } else if (recLen > 0) {
            readTemplate(line);
        }
    }
    
    private void readTemplate(String line) {
        for (i = 0; i < recLen; i++) {
            wsArr[i] = line.charAt(i);
        }
        n = recLen;
    }
    
    private void readPair(String line) {
        wsPair1[j - 1] = line.charAt(0);
        wsPair2[j - 1] = line.charAt(1);
        wsSub[j - 1] = line.charAt(6);
        j++;
    }
    
    private void step() {
        j = 0;
        for (i = 0; i < n - 1; i++) {
            wsArr2[j] = wsArr[i];
            j++;
            for (k = 0; k < m; k++) {
                if (wsPair1[k] == wsArr[i] && wsPair2[k] == wsArr[i + 1]) {
                    wsArr2[j] = wsSub[k];
                    j++;
                }
            }
        }
        wsArr2[j] = wsArr[n - 1];
        j++;
        n = j;
        for (i = 0; i < n; i++) {
            wsArr[i] = wsArr2[i];
        }
    }
    
    private void countLetters() {
        Arrays.fill(wsLetters, 0);
        for (i = 0; i < n; i++) {
            j = wsArr[i] - 'A';
            wsLetters[j]++;
        }
        
        nMax = 0;
        nMin = Long.MAX_VALUE;
        for (i = 0; i < 26; i++) {
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