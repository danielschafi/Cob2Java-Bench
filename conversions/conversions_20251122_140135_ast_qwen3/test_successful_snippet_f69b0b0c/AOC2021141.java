import java.io.*;
import java.util.*;

public class AOC2021141 {
    private static final int MAX_PAIRS = 100;
    private static final int MAX_LENGTH = 100000;
    private static final int MAX_LETTERS = 26;

    private static char[] wsArr = new char[MAX_LENGTH];
    private static char[] wsArr2 = new char[MAX_LENGTH];
    private static char[] wsPair1 = new char[MAX_PAIRS];
    private static char[] wsPair2 = new char[MAX_PAIRS];
    private static char[] wsSub = new char[MAX_PAIRS];
    private static int[] wsLetters = new int[MAX_LETTERS];
    
    private static int j = 1;
    private static int n = 1;
    private static int m = 1;
    private static int nMax = 0;
    private static int nMin = 0;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d14.input"));
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.length() == 7) {
                wsPair1[j-1] = line.charAt(0);
                wsPair2[j-1] = line.charAt(1);
                wsSub[j-1] = line.charAt(6);
                j++;
            } else if (line.length() > 0) {
                for (int i = 0; i < line.length(); i++) {
                    wsArr[i] = line.charAt(i);
                }
                n = line.length();
            }
        }
        reader.close();
        
        m = j - 1;
        
        for (int step = 0; step < 10; step++) {
            step();
        }
        
        countLetters();
        
        System.out.println(result);
    }
    
    private static void step() {
        int currentJ = 1;
        
        for (int i = 0; i < n; i++) {
            wsArr2[currentJ-1] = wsArr[i];
            currentJ++;
            
            for (int k = 0; k < m; k++) {
                if (wsPair1[k] == wsArr[i] && wsPair2[k] == wsArr[i+1]) {
                    wsArr2[currentJ-1] = wsSub[k];
                    currentJ++;
                }
            }
        }
        
        wsArr2[currentJ-1] = wsArr[n-1];
        n = currentJ;
        
        for (int i = 0; i < n; i++) {
            wsArr[i] = wsArr2[i];
        }
    }
    
    private static void countLetters() {
        for (int i = 0; i < n; i++) {
            int letterIndex = (int)(wsArr[i] - 'A');
            wsLetters[letterIndex]++;
        }
        
        nMin = wsLetters[1]; // 'B' is index 1
        
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