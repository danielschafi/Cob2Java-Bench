import java.io.*;
import java.util.*;

public class AOC202114 {
    static class Pair {
        char pair1;
        char pair2;
        char sub;
        long count = 0;
        long created = 0;
        long broken = 0;
    }

    public static void main(String[] args) throws IOException {
        char[] wsArr = new char[20];
        List<Pair> wsPairs = new ArrayList<>();
        long[] wsLetters = new long[26];
        
        int n = 0;
        int m = 0;
        
        // Read input file
        try (BufferedReader br = new BufferedReader(new FileReader("d14.input"))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                int recLen = line.length();
                
                if (recLen == 7) {
                    // Read pair
                    Pair p = new Pair();
                    p.pair1 = line.charAt(0);
                    p.pair2 = line.charAt(1);
                    p.sub = line.charAt(6);
                    wsPairs.add(p);
                } else if (recLen > 0) {
                    // Read template
                    for (int i = 0; i < recLen; i++) {
                        wsArr[i] = line.charAt(i);
                    }
                    n = recLen;
                }
            }
        }
        
        m = wsPairs.size();
        
        // Initialize counts
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < m; k++) {
                if (wsPairs.get(k).pair1 == wsArr[i] && 
                    wsPairs.get(k).pair2 == wsArr[i + 1]) {
                    wsPairs.get(k).count++;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            int j = wsArr[i] - 'A';
            wsLetters[j]++;
        }
        
        // Perform 40 steps
        for (int step = 0; step < 40; step++) {
            for (int i = 0; i < m; i++) {
                Pair p = wsPairs.get(i);
                p.broken += p.count;
                int j = p.sub - 'A';
                wsLetters[j] += p.count;
                
                for (int k = 0; k < m; k++) {
                    Pair pk = wsPairs.get(k);
                    if (pk.pair1 == p.pair1 && pk.pair2 == p.sub) {
                        pk.created += p.count;
                    }
                    if (pk.pair1 == p.sub && pk.pair2 == p.pair2) {
                        pk.created += p.count;
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                Pair p = wsPairs.get(i);
                p.count = p.count + p.created - p.broken;
                p.created = 0;
                p.broken = 0;
            }
        }
        
        // Count letters
        long nMax = 0;
        long nMin = wsLetters[1];
        
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