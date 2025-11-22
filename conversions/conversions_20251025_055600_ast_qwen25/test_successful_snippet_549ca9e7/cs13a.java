import java.util.Scanner;

public class cs13a {
    private static final int MAX_SIZE = 2000;
    private static final char DOT = '.';
    private static final char HASH = '#';
    private static final char SPACE = ' ';
    private static final char LOW_VALUE = '\0';

    private static String myName = "cs13a";
    private static int wsRecCount = 0;
    private static int dotCount = 0;
    private static int nbFolds = 0;
    private static int foldMax = 0;
    private static int yMax = 0;
    private static int xMax = 0;
    private static int foldIdx = 0;
    private static int foldStart = 0;
    private static int yIdx = 0;
    private static int xIdx = 0;
    private static int tIdx = 0;
    private static String cliArgs = new String(new char[80]).replace('\0', LOW_VALUE);
    private static String processType = new String(new char[4]).replace('\0', LOW_VALUE);
    private static String foldHdr = new String(new char[12]).replace('\0', SPACE);
    private static String foldLineX = new String(new char[4]).replace('\0', SPACE);
    private static String xX = new String(new char[4]).replace('\0', SPACE);
    private static String yX = new String(new char[4]).replace('\0', SPACE);
    private static String nbFoldsX = new String(new char[4]).replace('\0', SPACE);
    private static String wsInpt = new String(new char[80]).replace('\0', SPACE);
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static boolean foldingNow = false;
    private static char[][] pointTable = new char[MAX_SIZE][MAX_SIZE];
    private static char[][] pointTransformTable = new char[MAX_SIZE][MAX_SIZE];
    private static char[][] folds = new char[20][2];
    private static int[] foldLine = new int[20];

    public static void main(String[] args) {
        System.out.println(myName + " " + java.time.LocalDate.now());

        if (args.length > 0) {
            cliArgs = args[0];
        }

        String[] parts = cliArgs.split(" ");
        processType = parts[0].toUpperCase();
        nbFolds = Integer.parseInt(parts[1]);

        System.out.println(myName + " nb folds  " + nbFolds);

        for (char[] row : pointTransformTable) {
            java.util.Arrays.fill(row, DOT);
        }
        java.util.Arrays.fill(folds, new char[2]);
        java.util.Arrays.fill(foldLine, 0);

        for (char[] row : pointTable) {
            java.util.Arrays.fill(row, DOT);
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEof) {
            loadInput();
        }

        scanner.close();

        System.out.println(myName + " initial x max " + xMax);
        System.out.println(myName + " initial y max " + yMax);

        if (nbFolds > 0) {
            for (foldIdx = 0; foldIdx < nbFolds && foldIdx < foldMax; foldIdx++) {
                processInput();
            }
        } else {
            for (foldIdx = 0; foldIdx < foldMax; foldIdx++) {
                processInput();
            }
            pointdmp(yMax, xMax);
        }

        for (yIdx = 0; yIdx < yMax; yIdx++) {
            for (xIdx = 0; xIdx < xMax; xIdx++) {
                if (pointTable[yIdx][xIdx] == HASH) {
                    dotCount++;
                }
            }
        }

        System.out.println(myName + " dot count       " + dotCount);
        System.out.println(myName + " records read    " + wsRecCount);

        System.out.println(myName + " " + java.time.LocalDate.now());
    }

    private static void readInptData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
        } else {
            inptDataEof = true;
        }
    }

    private static void loadInput() {
        if (foldingNow) {
            String[] parts = wsInpt.split("=");
            foldHdr = parts[0];
            foldLineX = parts[1];
            foldMax++;
            folds[foldMax - 1][0] = foldHdr.charAt(11);
            foldLine[foldMax - 1] = Integer.parseInt(foldLineX) + 1;
            if (folds[foldMax - 1][0] == 'x') {
                if (foldLine[foldMax - 1] * 2 > xMax) {
                    xMax = foldLine[foldMax - 1] * 2;
                }
            }
        } else if (wsInpt.equals("")) {
            foldingNow = true;
        } else {
            String[] parts = wsInpt.split(",");
            xX = parts[0];
            yX = parts[1];
            xIdx = Integer.parseInt(xX) + 1;
            yIdx = Integer.parseInt(yX) + 1;
            if (processTest) {
                System.out.println(myName + " " + xIdx + "," + yIdx);
            }
            pointTable[yIdx - 1][xIdx - 1] = HASH;
            if (yIdx > yMax) {
                yMax = yIdx;
            }
            if (xIdx > xMax) {
                xMax = xIdx;
            }
        }

        wsInpt = "";
        readInptData(new Scanner(System.in));
    }

    private static void processInput() {
        if (processTest) {
            System.out.println(myName + " before");
            pointdmp(yMax, xMax);
        }

        if (processTest || nbFolds > 0) {
            System.out.println(myName + " " + folds[foldIdx][0] + " " + foldLine[foldIdx]);
        }

        for (char[] row : pointTransformTable) {
            java.util.Arrays.fill(row, DOT);
        }
        foldStart = foldLine[foldIdx] + 1;
        if (folds[foldIdx][0] == 'x') {
            for (yIdx = 0; yIdx < yMax; yIdx++) {
                for (xIdx = foldStart; xIdx < xMax; xIdx++) {
                    foldX();
                }
            }
            xMax = foldLine[foldIdx] - 1;
            if (processTest) {
                System.out.println(myName + " x max " + xMax);
            }
        } else {
            for (yIdx = foldStart; yIdx < yMax; yIdx++) {
                for (xIdx = 0; xIdx < xMax; xIdx++) {
                    foldY();
                }
            }
            yMax = foldLine[foldIdx] - 1;
            if (processTest) {
                System.out.println(myName + " y max " + yMax);
            }
        }

        for (int i = 0; i < yMax; i++) {
            System.arraycopy(pointTransformTable[i], 0, pointTable[i], 0, xMax);
        }

        if (processTest) {
            System.out.println(myName + " after");
            pointdmp(yMax, xMax);
        }
    }

    private static void foldX() {
        tIdx = foldLine[foldIdx] - (xIdx - foldLine[foldIdx]);
        if ((pointTable[yIdx][tIdx] == DOT && pointTable[yIdx][xIdx] == HASH) ||
            (pointTable[yIdx][tIdx] == HASH && pointTable[yIdx][xIdx] == DOT) ||
            (pointTable[yIdx][tIdx] == HASH && pointTable[yIdx][xIdx] == HASH)) {
            pointTransformTable[yIdx][tIdx] = HASH;
        }
    }

    private static void foldY() {
        tIdx = foldLine[foldIdx] - (yIdx - foldLine[foldIdx]);
        if ((pointTable[tIdx][xIdx] == DOT && pointTable[yIdx][xIdx] == HASH) ||
            (pointTable[tIdx][xIdx] == HASH && pointTable[yIdx][xIdx] == DOT) ||
            (pointTable[tIdx][xIdx] == HASH && pointTable[yIdx][xIdx] == HASH)) {
            pointTransformTable[tIdx][xIdx] = HASH;
        }
    }

    private static void pointdmp(int yMax, int xMax) {
        String myName = "pointdmp";
        for (yIdx = 0; yIdx < yMax; yIdx++) {
            System.out.print(myName + " ");
            for (xIdx = 0; xIdx < xMax; xIdx++) {
                System.out.print(pointTable[yIdx][xIdx]);
            }
            System.out.println();
        }
    }
}