import java.util.Arrays;
import java.util.Scanner;

public class cs08b {
    private static final int MAX_RECORDS = 200;
    private static final int MAX_SIGNALS = 10;
    private static final int MAX_DIGITS = 4;
    private static final int MAX_SEGMENTS = 8;
    private static final int MAX_TABLE_SIZE = 3;

    private static String MYNAME = "cs08b";
    private static int WS_REC_COUNT = 0;
    private static int ENTRY_COUNT = 0;
    private static int UNSTRING_PTR = 0;
    private static int STRING_PTR = 0;
    private static int SEGMENT_COUNT = 0;
    private static int X_COUNT = 0;
    private static int SUB1 = 0;
    private static int SUB2 = 0;
    private static int SUB3 = 0;
    private static long TOTAL_DIGITS = 0;
    private static String PROCESS_TYPE = "    ";
    private static String IN_SIGNAL_PATTERNS = "                                                            ";
    private static String IN_FOUR_DIGITS = "                                ";
    private static String[] CODED_VALUE_TBL = new String[8];
    private static String[] ONE = new String[8];
    private static String[] FOUR = new String[8];
    private static String[] SEVEN = new String[8];
    private static String[] EIGHT = new String[8];
    private static String[] ZER0 = new String[8];
    private static String[] SIX = new String[8];
    private static String[] NINE = new String[8];
    private static String[] TWO = new String[8];
    private static String[] THREE = new String[8];
    private static String[] FIVE = new String[8];
    private static char MAP_A = ' ';
    private static char MAP_B = ' ';
    private static char MAP_C = ' ';
    private static char MAP_D = ' ';
    private static char MAP_E = ' ';
    private static char MAP_F = ' ';
    private static char MAP_G = ' ';
    private static char[] CDE = new char[3];
    private static String WS_INPT = "                                                                                                                                                                                                                                                                ";
    private static char INPT_DATA_EOF_SW = 'N';
    private static String PROCESS_SW = "    ";
    private static String[][] ENTRY_TBL = new String[MAX_RECORDS][MAX_SIGNALS + MAX_DIGITS + 1];
    private static String[] SIXES = new String[3];
    private static String[] FIVES = new String[3];

    public static void main(String[] args) {
        PROCESS_TYPE = args[0].toUpperCase();
        Arrays.fill(ENTRY_TBL, new String[MAX_SIGNALS + MAX_DIGITS + 1]);
        Arrays.fill(SIXES, "        ");
        Arrays.fill(FIVES, "        ");
        Arrays.fill(CODED_VALUE_TBL, " ");
        Arrays.fill(ONE, " ");
        Arrays.fill(FOUR, " ");
        Arrays.fill(SEVEN, " ");
        Arrays.fill(EIGHT, " ");
        Arrays.fill(ZER0, " ");
        Arrays.fill(SIX, " ");
        Arrays.fill(NINE, " ");
        Arrays.fill(TWO, " ");
        Arrays.fill(THREE, " ");
        Arrays.fill(FIVE, " ");
        Arrays.fill(CDE, ' ');

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            WS_REC_COUNT++;
            WS_INPT = scanner.nextLine();
            loadInput();
        }
        scanner.close();

        for (int ENTRY_INDX = 0; ENTRY_INDX < ENTRY_COUNT; ENTRY_INDX++) {
            processEntries(ENTRY_INDX);
        }

        System.out.println(MYNAME + " total digits    " + TOTAL_DIGITS);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);
    }

    private static void loadInput() {
        ENTRY_COUNT++;
        int ENTRY_INDX = ENTRY_COUNT - 1;
        String[] parts = WS_INPT.split(" \\| ");
        IN_SIGNAL_PATTERNS = parts[0];
        IN_FOUR_DIGITS = parts[1];
        UNSTRING_PTR = 0;
        for (int SIGNAL_INDX = 0; SIGNAL_INDX < MAX_SIGNALS; SIGNAL_INDX++) {
            ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX] = IN_SIGNAL_PATTERNS.split(" ")[SIGNAL_INDX];
        }
        UNSTRING_PTR = 0;
        for (int DIGIT_INDX = 0; DIGIT_INDX < MAX_DIGITS; DIGIT_INDX++) {
            ENTRY_TBL[ENTRY_INDX][MAX_SIGNALS + DIGIT_INDX] = IN_FOUR_DIGITS.split(" ")[DIGIT_INDX];
        }
    }

    private static void processEntries(int ENTRY_INDX) {
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("TRACE");
        }
        Arrays.fill(SIXES, "        ");
        Arrays.fill(FIVES, "        ");
        Arrays.fill(CDE, ' ');
        processSignals(ENTRY_INDX);
        process069();
        process235();
        Arrays.sort(ONE);
        Arrays.sort(TWO);
        Arrays.sort(THREE);
        Arrays.sort(FOUR);
        Arrays.sort(FIVE);
        Arrays.sort(SIX);
        Arrays.sort(SEVEN);
        Arrays.sort(EIGHT);
        Arrays.sort(ZER0);
        processDigits(ENTRY_INDX);
    }

    private static void processSignals(int ENTRY_INDX) {
        SUB1 = 0;
        SUB2 = 0;
        SUB3 = 0;
        for (int SIGNAL_INDX = 0; SIGNAL_INDX < MAX_SIGNALS; SIGNAL_INDX++) {
            SEGMENT_COUNT = 0;
            for (char c : ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX].toCharArray()) {
                if (c != ' ') {
                    SEGMENT_COUNT++;
                }
            }
            switch (SEGMENT_COUNT) {
                case 2:
                    ONE[SIGNAL_INDX] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    break;
                case 3:
                    if (ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX].indexOf(MAP_A) != -1) {
                        SEVEN[SIGNAL_INDX] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    }
                    break;
                case 4:
                    FOUR[SIGNAL_INDX] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    break;
                case 7:
                    EIGHT[SIGNAL_INDX] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    break;
                case 6:
                    SIXES[SUB1++] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    break;
                case 5:
                    FIVES[SUB2++] = ENTRY_TBL[ENTRY_INDX][SIGNAL_INDX];
                    break;
            }
        }
        map();
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println(MYNAME + " A " + MAP_A);
            System.out.println(MYNAME + " B " + MAP_B);
            System.out.println(MYNAME + " C " + MAP_C);
            System.out.println(MYNAME + " D " + MAP_D);
            System.out.println(MYNAME + " E " + MAP_E);
            System.out.println(MYNAME + " F " + MAP_F);
            System.out.println(MYNAME + " G " + MAP_G);
        }
    }

    private static void process069() {
        SUB1 = 0;
        while (SUB1 < 3) {
            if (ZER0[0].equals("        ")) {
                map0();
            }
            if (SIX[0].equals("        ")) {
                map6();
            }
            if (NINE[0].equals("        ")) {
                map9();
            }
            SUB1++;
        }
    }

    private static void map0() {
        X_COUNT = 0;
        for (char c : SIXES[SUB1].toCharArray()) {
            if (c == MAP_A) {
                X_COUNT++;
            }
        }
        if (X_COUNT > 0) {
            X_COUNT = 0;
            for (char c : SIXES[SUB1].toCharArray()) {
                if (c == MAP_B) {
                    X_COUNT++;
                }
            }
            if (X_COUNT > 0) {
                X_COUNT = 0;
                for (char c : SIXES[SUB1].toCharArray()) {
                    if (c == MAP_C) {
                        X_COUNT++;
                    }
                }
                if (X_COUNT > 0) {
                    X_COUNT = 0;
                    for (char c : SIXES[SUB1].toCharArray()) {
                        if (c == MAP_E) {
                            X_COUNT++;
                        }
                    }
                    if (X_COUNT > 0) {
                        X_COUNT = 0;
                        for (char c : SIXES[SUB1].toCharArray()) {
                            if (c == MAP_F) {
                                X_COUNT++;
                            }
                        }
                        if (X_COUNT > 0) {
                            X_COUNT = 0;
                            for (char c : SIXES[SUB1].toCharArray()) {
                                if (c == MAP_G) {
                                    X_COUNT++;
                                }
                            }
                            if (X_COUNT > 0) {
                                ZER0[0] = SIXES[SUB1];
                                if (PROCESS_TYPE.equals("TEST")) {
                                    System.out.println(MYNAME + " SIXES(" + SUB1 + ") is ZER0");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void map6