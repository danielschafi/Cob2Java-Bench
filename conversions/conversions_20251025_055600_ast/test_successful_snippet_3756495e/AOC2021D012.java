import java.util.Scanner;

public class AOC2021D012 {
    private static final int MAX_SIZE = 2048;
    private int[] xsArr = new int[MAX_SIZE];
    private int xsLen = 0;
    private int wsI = 1;
    private int wsCount = 0;
    private int wsPrev = 9999;
    private int wsSum = 0;
    private int wsSolution = 0;

    public static void main(String[] args) {
        AOC2021D012 program = new AOC2021D012();
        program.readAndParseInput();
        program.solve();
        System.out.println(program.wsSolution);
    }

    private void readAndParseInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String numStr = scanner.nextLine().trim();
            if (numStr.length() <= 4) {
                xsArr[xsLen] = Integer.parseInt(numStr);
                xsLen++;
            }
        }
        scanner.close();
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