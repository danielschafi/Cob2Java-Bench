import java.io.*;
import java.util.*;

public class AOC_2021_D01_2 {
    private static final int MAX_ARRAY_SIZE = 2048;
    private int[] xsArr = new int[MAX_ARRAY_SIZE];
    private int xsLen = 0;
    private int wsI = 1;
    private int wsCount = 0;
    private int wsPrev = 9999;
    private int wsSum = 0;
    private int wsSolution = 0;

    public static void main(String[] args) {
        AOC_2021_D01_2 program = new AOC_2021_D01_2();
        program.readAndParseInput();
        program.solve();
        System.out.println(program.wsSolution);
    }

    private void readAndParseInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (xsLen < MAX_ARRAY_SIZE) {
                    try {
                        xsArr[xsLen] = Integer.parseInt(line.trim());
                        xsLen++;
                    } catch (NumberFormatException e) {
                        // Skip lines that cannot be parsed as integers
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solve() {
        wsCount = 0;
        wsI = 0;
        wsPrev = 9999;

        while (wsI <= xsLen - 3) {
            wsSum = xsArr[wsI] + xsArr[wsI + 1] + xsArr[wsI + 2];
            if (wsSum > wsPrev) {
                wsCount++;
            }
            wsPrev = wsSum;
            wsI++;
        }

        wsSolution = wsCount;
    }
}