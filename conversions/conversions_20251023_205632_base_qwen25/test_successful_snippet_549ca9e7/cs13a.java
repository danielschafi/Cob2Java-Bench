import java.util.Scanner;

public class cs13a {
    private static final String MYNAME = "cs13a";
    private static final int MAX_SIZE = 2000;
    private static final char POINT = '#';
    private static final char EMPTY = '.';
    private static final char LOW_VALUE = '\0';

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
    private static String foldHdr = new String(new char[12]).replace('\0', ' ');
    private static String foldLineX = new String(new char[4]).replace('\0', ' ');
    private static String xX = new String(new char[4]).replace('\0', ' ');
    private static String yX = new String(new char[4]).replace('\0', ' ');
    private static String nbFoldsX = new String(new char[4]).replace('\0', ' ');
    private static String wsInpt = new String(new char[80]).replace('\0', ' ');
    private static boolean inptDataEofSw = false;
    private static boolean processSwTest = false;
    private static boolean foldingNowSw = false;

    private static char[][] pointTable = new char[MAX_SIZE][MAX_SIZE];
    private static char[][] pointTransformTable = new char[MAX_SIZE][MAX_SIZE];
    private static char[][] folds = new char[20][2];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        if (args.length > 0) {
            cliArgs = args[0];
        }

        String[] parts = cliArgs.split(" ");
        if (parts.length > 0) {
            processType = parts[0].toUpperCase();
        }
        if (parts.length > 1) {
            nbFolds = Integer.parseInt(parts[1]);
        }

        System.out.println(MYNAME + " nb folds  " + nbFolds);

        for (char[] row : pointTransformTable) {
            java.util.Arrays.fill(row, EMPTY);
        }
        for (char[] row : folds) {
            java.util.Arrays.fill(row, EMPTY);
        }

        for (char[] row : pointTable) {
            java.util.Arrays.fill(row, EMPTY);
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEofSw) {
            loadInput();
        }

        scanner.close();

        System.out.println(MYNAME + " initial x max " + xMax);
        System.out.println(MYNAME + " initial y max " + yMax);

        if (nbFolds > 0) {
            for (foldIdx = 0; foldIdx < foldMax && foldIdx < nbFolds; foldIdx++) {
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
                if (pointTable[yIdx][xIdx] == POINT) {
                    dotCount++;
                }
            }
        }

        System.out.println(MYNAME + " dot count       " + dotCount);
        System.out.println(MYNAME + " records read    " + wsRecCount);

        System.out.println(MYNAME + " " + java.time.LocalDate.now());
    }

    private static void readInptData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
        } else {
            inptDataEofSw = true;
        }
    }

    private static void loadInput() {
        if (foldingNowSw) {
            String[] parts = wsInpt.split("=");
            foldHdr = parts[0];
            foldLineX = parts[1];
            foldMax++;
            folds[foldMax - 1][0] = foldHdr.charAt(11);
            foldLineX = foldLineX.trim();
            foldLineX = foldLineX.substring(0, foldLineX.length() - 1);
            folds[foldMax - 1][1] = (char) (Integer.parseInt(foldLineX) + 1);
            if (folds[foldMax - 1][0] == 'x') {
                if (folds[foldMax - 1][1] * 2 > xMax) {
                    xMax = folds[foldMax - 1][1] * 2;
                }
            }
        } else if (wsInpt.trim().isEmpty()) {
            foldingNowSw = true;
        } else {
            String[] parts = wsInpt.split(",");
            xX = parts[0];
            yX = parts[1];
            xIdx = Integer.parseInt(xX) + 1;
            yIdx = Integer.parseInt(yX) + 1;
            if (processSwTest) {
                System.out.println(MYNAME + " " + xIdx + "," + yIdx);
            }
            pointTable[yIdx - 1][xIdx - 1] = POINT;
            if (yIdx > yMax) {
                yMax = yIdx;
            }
            if (xIdx > xMax) {
                xMax = xIdx;
            }
        }

        wsInpt = new String(new char[80]).replace('\0', ' ');
        readInptData(scanner);
    }

    private static void processInput() {
        if (processSwTest) {
            System.out.println(MYNAME + " before");
            pointdmp(yMax, xMax);
        }

        if (processSwTest || nbFolds > 0) {
            System.out.println(MYNAME + " " + folds[foldIdx][0] + " " + (int) folds[foldIdx][1]);
        }

        for (char[] row : pointTransformTable) {
            java.util.Arrays.fill(row, EMPTY);
        }
        foldStart = (int) folds[foldIdx][1] + 1;
        if (folds[foldIdx][0] == 'x') {
            for (yIdx = 0; yIdx < yMax; yIdx++) {
                for (xIdx = foldStart; xIdx < xMax; xIdx++) {
                    foldX();
                }
            }
            xMax = (int) folds[foldIdx][1] - 1;
            if (processSwTest) {
                System.out.println(MYNAME + " x max " + xMax);
            }
        } else {
            for (yIdx = foldStart; yIdx < yMax; yIdx++) {
                for (xIdx = 0; xIdx < xMax; xIdx++) {
                    foldY();
                }
            }
            yMax = (int) folds[foldIdx][1] - 1;
            if (processSwTest) {
                System.out.println(MYNAME + " y max " + yMax);
            }
        }

        for (int i = 0; i < yMax; i++) {
            System.arraycopy(pointTransformTable[i], 0, pointTable[i], 0, xMax);
        }

        if (processSwTest) {
            System.out.println(MYNAME + " after");
            pointdmp(yMax, xMax);
        }
    }

    private static void foldX() {
        tIdx = (int) folds[foldIdx][1] - (xIdx - (int) folds[foldIdx][1]);
        if ((pointTable[yIdx][tIdx] == EMPTY && pointTable[yIdx][xIdx] == POINT) ||
            (pointTable[yIdx][tIdx] == POINT && pointTable[yIdx][xIdx] == EMPTY) ||
            (pointTable[yIdx][tIdx] == POINT && pointTable[yIdx][xIdx] == POINT)) {
            pointTransformTable[yIdx][tIdx] = POINT;
        }
    }

    private static void foldY() {
        tIdx = (int) folds[foldIdx][1] - (yIdx - (int) folds[foldIdx][1]);
        if ((pointTable[tIdx][xIdx] == EMPTY && pointTable[yIdx][xIdx] == POINT) ||
            (pointTable[tIdx][xIdx] == POINT && pointTable[yIdx][xIdx] == EMPTY) ||
            (pointTable[tIdx][xIdx] == POINT && pointTable[yIdx][xIdx] == POINT)) {
            pointTransformTable[tIdx][xIdx] = POINT;
        }
    }

    private static void pointdmp(int yMax, int xMax) {
        for (yIdx = 0; yIdx < yMax; yIdx++) {
            System.out.print("pointdmp ");
            for (xIdx = 0; xIdx < xMax; xIdx++) {
                System.out