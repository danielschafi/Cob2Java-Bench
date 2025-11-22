import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class AOC20211D012 {
    private static ArrayList<Integer> xsArr = new ArrayList<>();
    private static int xsLen = 0;
    private static int wsI = 1;
    private static int wsCount = 0;
    private static int wsPrev = 9999;
    private static int wsSum = 0;
    private static int wsSolution = 0;

    public static void main(String[] args) {
        readAndParseInput();
        solve();
        System.out.println(wsSolution);
        System.exit(0);
    }

    private static void readAndParseInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    int num = Integer.parseInt(line);
                    xsArr.add(num);
                    xsLen++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void solve() {
        wsCount = 0;
        wsI = 0;
        while (wsI <= xsLen - 3) {
            wsSum = xsArr.get(wsI) + xsArr.get(wsI + 1) + xsArr.get(wsI + 2);
            if (wsSum > wsPrev) {
                wsCount++;
            }
            wsPrev = wsSum;
            wsI++;
        }
        wsSolution = wsCount;
    }
}