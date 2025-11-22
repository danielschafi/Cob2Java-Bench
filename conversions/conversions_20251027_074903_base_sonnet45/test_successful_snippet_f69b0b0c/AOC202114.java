import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202114 {
    private static final int MAX_ARRAY_SIZE = 100000;
    private static final int MAX_PAIRS = 100;
    private static final int ALPHABET_SIZE = 26;
    
    private char[] wsArr = new char[MAX_ARRAY_SIZE];
    private char[] wsArr2 = new char[MAX_ARRAY_SIZE];
    private char[] wsPair1 = new char[MAX_PAIRS];
    private char[] wsPair2 = new char[MAX_PAIRS];
    private char[] wsSub = new char[MAX_PAIRS];
    private int[] wsLetters = new int[ALPHABET_SIZE];
    
    private int n = 1;
    private int m = 1;
    private int pairCount = 0;
    
    public static void main(String[] args) {
        AOC202114 program = new AOC202114();
        program.run();
    }
    
    public void run() {
        readInput();
        m = pairCount;
        
        for (int step = 0; step < 10; step++) {
            performStep();
        }
        
        countLetters();
    }
    
    private void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private void processRecord(String line) {
        int recLen = line.length();
        
        if (recLen == 7) {
            readPair(line);
        } else if (recLen > 0) {
            readTemplate(line);
        }
    }
    
    private void readTemplate(String line) {
        int recLen = line.length();
        for (int i = 0; i < recLen; i++) {
            wsArr[i] = line.charAt(i);
        }
        n = recLen;
    }
    
    private void readPair(String line) {
        wsPair1[pairCount] = line.charAt(0);
        wsPair2[pairCount] = line.charAt(1);
        wsSub[pairCount] = line.charAt(6);
        pairCount++;
    }
    
    private void performStep() {
        int j = 0;
        
        for (int i = 0; i < n - 1; i++) {
            wsArr2[j] = wsArr[i];
            j++;
            
            for (int k = 0; k < m; k++) {
                if (wsPair1[k] == wsArr[i] && wsPair2[k] == wsArr[i + 1]) {
                    wsArr2[j] = wsSub[k];
                    j++;
                    break;
                }
            }
        }
        
        wsArr2[j] = wsArr[n - 1];
        j++;
        n = j;
        
        for (int i = 0; i < n; i++) {
            wsArr[i] = wsArr2[i];
        }
    }
    
    private void countLetters() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            wsLetters[i] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            int index = wsArr[i] - 'A';
            if (index >= 0 && index < ALPHABET_SIZE) {
                wsLetters[index]++;
            }
        }
        
        int nMax = 0;
        int nMin = Integer.MAX_VALUE;
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (wsLetters[i] > nMax) {
                nMax = wsLetters[i];
            }
            if (wsLetters[i] > 0 && wsLetters[i] < nMin) {
                nMin = wsLetters[i];
            }
        }
        
        int result = nMax - nMin;
        System.out.println(result);
    }
}