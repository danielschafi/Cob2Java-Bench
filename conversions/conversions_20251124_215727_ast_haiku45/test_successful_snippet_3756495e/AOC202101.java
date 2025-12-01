```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AOC202101 {
    private static final int MAX_ARRAY_SIZE = 2048;
    
    private int[] xsArr = new int[MAX_ARRAY_SIZE];
    private int xsLen = 0;
    private int wsI = 1;
    private int wsCount = 0;
    private int wsPrev = 9999;
    private int wsSum = 0;
    private int wsSolution = 0;
    
    public static void main(String[] args) {
        AOC202101 program = new AOC202101();
        program.readAndParseInput();
        program.solve();
        System.out.println(program.wsSolution);
    }
    
    private void readAndParseInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        int num = Integer.parseInt(line);
                        xsArr[xsLen] = num;
                        xsLen++;
                    } catch (NumberFormatException e) {
                        // Skip invalid lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void solve() {
        wsCount = 0;
        wsI = 1;
        
        while (wsI <= xsLen - 2) {
            wsSum = xsArr[wsI - 1] + xsArr[wsI] + xsArr[wsI + 1];
            
            if (wsSum > wsPrev) {
                wsCount++;
            }
            
            wsPrev = wsSum;
            wsI++;
        }
        
        wsSolution = wsCount;
    }
}