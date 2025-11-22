import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2021D012 {
    private static final int MAX_SIZE = 2048;
    private static int[] xsArr = new int[MAX_SIZE];
    private static int xsLen = 0;
    private static int wsCount = 0;
    private static int wsI = 1;
    private static int wsPrev = 9999;
    private static int wsSum = 0;
    private static int wsSolution = 0;

    public static void main(String[] args) throws IOException {
        readAndParseInput();
        solve();
        System.out.println(wsSolution);
    }

    private static void readAndParseInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) break;
            xsArr[xsLen] = Integer.parseInt(line.trim());
            xsLen++;
        }
    }

    private static void solve() {
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