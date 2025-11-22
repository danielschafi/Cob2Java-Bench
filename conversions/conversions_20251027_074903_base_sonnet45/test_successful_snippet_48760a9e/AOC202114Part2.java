import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202114Part2 {
    
    static class Pair {
        char pair1;
        char pair2;
        char sub;
        long count;
        long created;
        long broken;
        
        public Pair() {
            this.count = 0;
            this.created = 0;
            this.broken = 0;
        }
    }
    
    static char[] wsArr = new char[20];
    static Pair[] wsPairs = new Pair[100];
    static long[] wsLetters = new long[26];
    static int n = 1;
    static int m = 1;
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            wsPairs[i] = new Pair();
        }
        for (int i = 0; i < 26; i++) {
            wsLetters[i] = 0;
        }
        
        int j = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int recLen = line.length();
                if (recLen == 7) {
                    wsPairs[j].pair1 = line.charAt(0);
                    wsPairs[j].pair2 = line.charAt(1);
                    wsPairs[j].sub = line.charAt(6);
                    j++;
                } else if (recLen > 0) {
                    for (int i = 0; i < recLen; i++) {
                        wsArr[i] = line.charAt(i);
                    }
                    n = recLen;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        m = j;
        initCounts();
        
        for (int step = 0; step < 40; step++) {
            performStep();
        }
        
        countLetters();
    }
    
    static void initCounts() {
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < m; k++) {
                if (wsPairs[k].pair1 == wsArr[i] && wsPairs[k].pair2 == wsArr[i + 1]) {
                    wsPairs[k].count++;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            int j = wsArr[i] - 'A';
            wsLetters[j]++;
        }
    }
    
    static void performStep() {
        for (int i = 0; i < m; i++) {
            wsPairs[i].broken += wsPairs[i].count;
            int j = wsPairs[i].sub - 'A';
            wsLetters[j] += wsPairs[i].count;
            
            for (int k = 0; k < m; k++) {
                if (wsPairs[k].pair1 == wsPairs[i].pair1 && wsPairs[k].pair2 == wsPairs[i].sub) {
                    wsPairs[k].created += wsPairs[i].count;
                }
                if (wsPairs[k].pair1 == wsPairs[i].sub && wsPairs[k].pair2 == wsPairs[i].pair2) {
                    wsPairs[k].created += wsPairs[i].count;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            wsPairs[i].count = wsPairs[i].count + wsPairs[i].created - wsPairs[i].broken;
            wsPairs[i].created = 0;
            wsPairs[i].broken = 0;
        }
    }
    
    static void countLetters() {
        long nMin = Long.MAX_VALUE;
        long nMax = 0;
        
        for (int i = 0; i < 26; i++) {
            if (wsLetters[i] > nMax) {
                nMax = wsLetters[i];
            }
            if (wsLetters[i] > 0 && wsLetters[i] < nMin) {
                nMin = wsLetters[i];
            }
        }
        
        long result = nMax - nMin;
        System.out.println(result);
    }
}