import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202114 {
    private static final int MAX_ARR = 100000;
    private static final int MAX_PAIRS = 100;
    private static final int ALPHABET_SIZE = 26;
    
    private char[] wsArr = new char[MAX_ARR];
    private char[] wsArr2 = new char[MAX_ARR];
    private char[] wsPair1 = new char[MAX_PAIRS];
    private char[] wsPair2 = new char[MAX_PAIRS];
    private char[] wsSub = new char[MAX_PAIRS];
    private long[] wsLetters = new long[ALPHABET_SIZE];
    
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
    
    private BufferedReader reader;
    private String inputRecord;
    
    public static void main(String[] args) {
        AOC202114 program = new AOC202114();
        program.run();
    }
    
    public void run() {
        try {
            openInput();
            while (fileStatus == 0) {
                readRecord();
            }
            closeInput();
            
            m = j - 1;
            
            for (int step = 0; step < 10; step++) {
                performStep();
            }
            
            countLetters();
            
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void openInput() throws IOException {
        reader = new BufferedReader(new FileReader("d14.input"));
    }
    
    private void closeInput() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
    
    private void readRecord() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            fileStatus = 1;
            return;
        }
        
        inputRecord = line;
        recLen = line.length();
        
        processRecord();
    }
    
    private void processRecord() {
        if (recLen == 7) {
            readPair();
        } else if (recLen > 0) {
            readTemplate();
        }
    }
    
    private void readTemplate() {
        for (i = 0; i < recLen; i++) {
            wsArr[i] = inputRecord.charAt(i);
        }
        n = recLen;
    }
    
    private void readPair() {
        wsPair1[j - 1] = inputRecord.charAt(0);
        wsPair2[j - 1] = inputRecord.charAt(1);
        wsSub[j - 1] = inputRecord.charAt(6);
        j++;
    }
    
    private void performStep() {
        j = 1;
        
        for (i = 0; i < n; i++) {
            wsArr2[j - 1] = wsArr[i];
            j++;
            
            for (k = 0; k < m; k++) {
                if (wsPair1[k] == wsArr[i] && wsPair2[k] == wsArr[i + 1]) {
                    wsArr2[j - 1] = wsSub[k];
                    j++;
                }
            }
        }
        
        wsArr2[j - 1] = wsArr[n - 1];
        n = j;
        
        for (i = 0; i < n; i++) {
            wsArr[i] = wsArr2[i];
        }
    }
    
    private void countLetters() {
        for (i = 0; i < ALPHABET_SIZE; i++) {
            wsLetters[i] = 0;
        }
        
        for (i = 0; i < n; i++) {
            j = (int) wsArr[i] - (int) 'A' + 1;
            wsLetters[j - 1]++;
        }
        
        nMin = wsLetters[1];
        
        for (i = 0; i < ALPHABET_SIZE; i++) {
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