import java.util.Scanner;

public class cs09a {
    private static final String MYNAME = "cs09a";
    private static int WS_REC_COUNT = 0;
    private static int ROW_COUNT = 0;
    private static int ROW_MAX = 0;
    private static int COL_MAX = 0;
    private static int RISK_SUM = 0;
    private static String PROCESS_TYPE = "    ";
    private static String IN_SIGNAL_PATTERNS = "                                                            ";
    private static String IN_FOUR_DIGITS = "                                ";
    private static String WS_INPT = "                                        ";
    private static boolean INPT_DATA_EOF = false;
    private static boolean PROCESS_TEST = false;
    private static boolean LOW_POINT_SW = false;
    private static boolean A_NEW_LOW = false;
    private static char[][] HEIGHT_MAP = new char[100][100];
    private static int ALOC_MAX = 0;
    private static int ALOC_SUB = 0;
    private static char[] ALOC_VAL = new char[4];
    private static int LOW_POINT_MAX = 0;
    private static int LOW_POINT_SUB = 0;
    private static char[] LOW_POINT = new char[10000];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        if (args.length > 0) {
            PROCESS_TYPE = args[0].toUpperCase();
            PROCESS_TEST = PROCESS_TYPE.equals("TEST");
        }

        for (char[] row : HEIGHT_MAP) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        while (!INPT_DATA_EOF) {
            loadInput();
        }

        scanner.close();

        System.out.println(MYNAME + " row max         " + ROW_MAX);
        System.out.println(MYNAME + " col max         " + COL_MAX);

        processHeightMap();

        System.out.println(MYNAME + " sum of all risk " + RISK_SUM);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);

        System.out.println(MYNAME + " " + java.time.LocalDate.now());
    }

    private static void readInputData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            WS_INPT = scanner.nextLine();
            WS_REC_COUNT++;
        } else {
            INPT_DATA_EOF = true;
        }
    }

    private static void loadInput() {
        ROW_COUNT++;
        ROW_MAX = ROW_COUNT;

        if (ROW_COUNT == 1) {
            COL_MAX = WS_INPT.indexOf(' ');
            if (COL_MAX == -1) {
                COL_MAX = WS_INPT.length();
            }
        }

        for (int i = 0; i < COL_MAX; i++) {
            HEIGHT_MAP[ROW_COUNT - 1][i] = WS_INPT.charAt(i);
        }

        readInputData(scanner);
    }

    private static void processHeightMap() {
        for (int rowI1 = 0; rowI1 < ROW_MAX; rowI1++) {
            for (int colI1 = 0; colI1 < COL_MAX; colI1++) {
                findLowPoints(rowI1, colI1);
            }
        }

        System.out.println(MYNAME + " low point max " + LOW_POINT_MAX);
        for (int lowPointSub = 0; lowPointSub < LOW_POINT_MAX; lowPointSub++) {
            RISK_SUM += 1 + Character.getNumericValue(LOW_POINT[lowPointSub]);
        }
    }

    private static void findLowPoints(int rowI1, int colI1) {
        determineAdjacent(rowI1, colI1);
        A_NEW_LOW = true;

        if (PROCESS_TEST || (rowI1 == 99 && colI1 == 0)) {
            System.out.println(MYNAME + " HEIGHT(" + (rowI1 + 1) + "," + (colI1 + 1) + ") " + HEIGHT_MAP[rowI1][colI1]);
        }

        for (int alocSub = 0; alocSub < ALOC_MAX && A_NEW_LOW; alocSub++) {
            if (PROCESS_TEST || (rowI1 == 99 && colI1 == 0)) {
                System.out.println(MYNAME + " " + ALOC_VAL[alocSub]);
            }

            if (ALOC_VAL[alocSub] <= HEIGHT_MAP[rowI1][colI1]) {
                A_NEW_LOW = false;
                if (PROCESS_TEST || (rowI1 == 99 && colI1 == 0) || (rowI1 == 88 && colI1 == 0) || (rowI1 == 14 && colI1 == 98) || (rowI1 == 22 && colI1 == 99) || (rowI1 == 0 && colI1 == 19)) {
                    System.out.println(MYNAME + " ALOC-VAL(" + (alocSub + 1) + ") " + ALOC_VAL[alocSub] + " <= HEIGHT(" + (rowI1 + 1) + "," + (colI1 + 1) + ") " + HEIGHT_MAP[rowI1][colI1]);
                }
            }
        }

        if (A_NEW_LOW) {
            LOW_POINT_MAX++;
            LOW_POINT_SUB++;
            LOW_POINT[LOW_POINT_SUB - 1] = HEIGHT_MAP[rowI1][colI1];
            if (PROCESS_TEST || (rowI1 == 99 && colI1 == 0) || (rowI1 == 88 && colI1 == 0) || (rowI1 == 14 && colI1 == 98) || (rowI1 == 22 && colI1 == 99) || (rowI1 == 0 && colI1 == 19)) {
                System.out.println(MYNAME + " new low " + LOW_POINT[LOW_POINT_SUB - 1]);
                System.out.println(MYNAME + " found " + (rowI1 + 1) + "," + (colI1 + 1));
            }
        }
    }

    private static void determineAdjacent(int rowI1, int colI1) {
        int rowI2 = rowI1;
        int colI2 = colI1;
        ALOC_MAX = 0;
        ALOC_SUB = 0;

        if (rowI1 > 0) {
            ALOC_SUB++;
            ALOC_MAX++;
            rowI2--;
            ALOC_VAL[ALOC_SUB - 1] = HEIGHT_MAP[rowI2][colI2];
            rowI2 = rowI1;
        }

        if (rowI1 < ROW_MAX - 1) {
            ALOC_SUB++;
            ALOC_MAX++;
            rowI2++;
            ALOC_VAL[ALOC_SUB - 1] = HEIGHT_MAP[rowI2][colI2];
            rowI2 = rowI1;
        }

        if (colI1 > 0) {
            ALOC_SUB++;
            ALOC_MAX++;
            colI2--;
            ALOC_VAL[ALOC_SUB - 1] = HEIGHT_MAP[rowI2][colI2];
            colI2 = colI1;
        }

        if (colI1 < COL_MAX - 1) {
            ALOC_SUB++;
            ALOC_MAX++;
            colI2++;
            ALOC_VAL[ALOC_SUB - 1] = HEIGHT_MAP[rowI2][colI2];
            colI2 = colI1;
        }

        if (ALOC_MAX < 2) {
            System.out.println(MYNAME + " error in determining adjacent locations " + ALOC_MAX + " " + (rowI1 + 1) + " " + (colI1 + 1));
        }
    }
}