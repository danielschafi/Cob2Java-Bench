import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cs09a {
    private static final String MYNAME = "cs09a";
    private static int wsRecCount = 0;
    private static int rowCount = 0;
    private static int rowMax = 0;
    private static int colMax = 0;
    private static int riskSum = 0;
    private static String processType = "";
    private static String inSignalPatterns = " ".repeat(60);
    private static String inFourDigits = " ".repeat(32);
    private static String wsInpt = " ".repeat(4096);
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static boolean aNewLow = false;
    private static final char[][] heightMap = new char[100][100];
    private static int alocMax = 0;
    private static int alocSub = 0;
    private static final char[] alocVal = new char[4];
    private static int lowPointMax = 0;
    private static int lowPointSub = 0;
    private static final char[] lowPoint = new char[10000];

    public static void main(String[] args) throws IOException {
        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        for (char[] row : heightMap) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        readInptData(reader);

        while (!inptDataEof) {
            loadInput();
            readInptData(reader);
        }

        System.out.println(MYNAME + " row max         " + rowMax);
        System.out.println(MYNAME + " col max         " + colMax);

        processHeightMap();

        System.out.println(MYNAME + " sum of all risk " + riskSum);
        System.out.println(MYNAME + " records read    " + wsRecCount);

        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private static void readInptData(BufferedReader reader) throws IOException {
        wsInpt = " ".repeat(4096);
        String line = reader.readLine();
        if (line == null) {
            inptDataEof = true;
        } else {
            wsRecCount++;
            wsInpt = line;
        }
    }

    private static void loadInput() {
        rowCount++;
        rowMax = rowCount;

        if (rowCount == 1) {
            colMax = wsInpt.chars().takeWhile(c -> c != ' ').count();
        }

        for (int i = 0; i < colMax; i++) {
            heightMap[rowCount - 1][i] = wsInpt.charAt(i);
        }
    }

    private static void processHeightMap() {
        for (int rowI1 = 0; rowI1 < rowMax; rowI1++) {
            for (int colI1 = 0; colI1 < colMax; colI1++) {
                findLowPoints(rowI1, colI1);
            }
        }

        System.out.println(MYNAME + " low point max " + lowPointMax);
        for (int lowPointSub = 0; lowPointSub < lowPointMax; lowPointSub++) {
            riskSum += 1 + Character.getNumericValue(lowPoint[lowPointSub]);
        }
    }

    private static void findLowPoints(int rowI1, int colI1) {
        determineAdjacent(rowI1, colI1);
        aNewLow = true;

        if (processTest || (rowI1 == 99 && colI1 == 0)) {
            System.out.println(MYNAME + " HEIGHT(" + (rowI1 + 1) + "," + (colI1 + 1) + ") " + heightMap[rowI1][colI1]);
        }

        for (int alocSub = 0; alocSub < alocMax && aNewLow; alocSub++) {
            if (processTest || (rowI1 == 99 && colI1 == 0)) {
                System.out.println(MYNAME + " " + alocVal[alocSub]);
            }

            if (alocVal[alocSub] <= heightMap[rowI1][colI1]) {
                aNewLow = false;
                if (processTest || (rowI1 == 99 && colI1 == 0) || (rowI1 == 88 && colI1 == 0) || (rowI1 == 14 && colI1 == 98) || (rowI1 == 22 && colI1 == 99) || (rowI1 == 0 && colI1 == 19)) {
                    System.out.println(MYNAME + " ALOC-VAL(" + (alocSub + 1) + ") " + alocVal[alocSub] + " <= HEIGHT(" + (rowI1 + 1) + "," + (colI1 + 1) + ") " + heightMap[rowI1][colI1]);
                }
            }
        }

        if (aNewLow) {
            lowPointMax++;
            lowPointSub++;
            lowPoint[lowPointSub - 1] = heightMap[rowI1][colI1];
            if (processTest || (rowI1 == 99 && colI1 == 0) || (rowI1 == 88 && colI1 == 0) || (rowI1 == 14 && colI1 == 98) || (rowI1 == 22 && colI1 == 99) || (rowI1 == 0 && colI1 == 19)) {
                System.out.println(MYNAME + " new low " + lowPoint[lowPointSub - 1]);
                System.out.println(MYNAME + " found " + (rowI1 + 1) + "," + (colI1 + 1));
            }
        }
    }

    private static void determineAdjacent(int rowI1, int colI1) {
        alocMax = 0;
        alocSub = 0;

        if (rowI1 > 0) {
            alocSub++;
            alocMax++;
            alocVal[alocSub - 1] = heightMap[rowI1 - 1][colI1];
        }

        if (rowI1 < rowMax - 1) {
            alocSub++;
            alocMax++;
            alocVal[alocSub - 1] = heightMap[rowI1 + 1][colI1];
        }

        if (colI1 > 0) {
            alocSub++;
            alocMax++;
            alocVal[alocSub - 1] = heightMap[rowI1][colI1 - 1];
        }

        if (colI1 < colMax - 1) {
            alocSub++;
            alocMax++;
            alocVal[alocSub - 1] = heightMap[rowI1][colI1 + 1];
        }

        if (alocMax < 2) {
            System.out.println(MYNAME + " error in determining adjacent locations " + alocMax + " " + (rowI1 + 1) + " " + (colI1 + 1));
        }
    }
}