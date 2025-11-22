import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class cs10b {
    private static final String MYNAME = "cs10b";
    private static int WS_REC_COUNT = 0;
    private static int STACK_PTR = 0;
    private static int STACK_PTR1 = 0;
    private static int STACK_MAX = 0;
    private static int STACK_CURR_LVL = 0;
    private static int STACK_MAX_LVL = 0;
    private static int CHAR_PTR = 0;
    private static int COMPLETION_PTR = 1;
    private static int COMPLETION_MAX = 0;
    private static int FILE_SCORE = 0;
    private static int TOTAL_SCORE_MAX = 0;
    private static int TOTAL_SUB = 0;
    private static String PROCESS_TYPE = "";
    private static char COMPLETION_CHAR = ' ';
    private static char CLOSE_CHAR = ' ';
    private static char THE_CHAR = ' ';
    private static boolean THE_CHAR_IS_OPEN = false;
    private static boolean THE_CHAR_IS_CLOSE = false;
    private static String COMPLETION_LIST = "                                                                                                    ";
    private static long[] TOTAL_SCORE = new long[100];
    private static String WS_INPT = "                                                                                                                                                                                                                                                                ";
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = "    ";
    private static boolean PROCESS_TEST = false;
    private static boolean BAD_RECORD_SW = false;
    private static boolean BAD_RECORD = false;
    private static char[][] STACK_TBL = new char[256][2];
    private static int[] STACK_LVL = new int[256];

    public static void main(String[] args) {
        PROCESS_TYPE = args[0].toUpperCase();
        Arrays.fill(TOTAL_SCORE, 0);
        TOTAL_SUB = 0;
        readInputData();
        while (!INPT_DATA_EOF) {
            processInput();
        }
        closeInputData();
        Arrays.sort(TOTAL_SCORE);
        for (TOTAL_SUB = 1; TOTAL_SUB <= TOTAL_SCORE_MAX; TOTAL_SUB++) {
            System.out.println(MYNAME + " " + TOTAL_SUB + " total score " + TOTAL_SCORE[TOTAL_SUB - 1]);
        }
        TOTAL_SUB = (TOTAL_SCORE_MAX / 2) + 1;
        System.out.println(MYNAME + " file score      " + FILE_SCORE);
        System.out.println(MYNAME + " total score     " + TOTAL_SCORE[TOTAL_SUB - 1]);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);
    }

    private static void processInput() {
        BAD_RECORD = false;
        for (CHAR_PTR = 1; CHAR_PTR <= 4096 && WS_INPT.charAt(CHAR_PTR - 1) != ' ' && !BAD_RECORD; CHAR_PTR++) {
            THE_CHAR = WS_INPT.charAt(CHAR_PTR - 1);
            if (THE_CHAR_IS_OPEN) {
                STACK_PTR++;
                STACK_TBL[STACK_PTR - 1][0] = THE_CHAR;
            } else {
                if (STACK_PTR == 0) {
                    System.out.println(MYNAME + " stack pointer 0");
                    BAD_RECORD = true;
                } else {
                    if (PROCESS_TEST) {
                        System.out.println(MYNAME + " " + THE_CHAR + " " + STACK_TBL[STACK_PTR - 1][0]);
                    }
                    switch (THE_CHAR) {
                        case ')':
                            if (STACK_TBL[STACK_PTR - 1][0] == '(') {
                                STACK_PTR--;
                            } else {
                                BAD_RECORD = true;
                            }
                            break;
                        case ']':
                            if (STACK_TBL[STACK_PTR - 1][0] == '[') {
                                STACK_PTR--;
                            } else {
                                BAD_RECORD = true;
                            }
                            break;
                        case '}':
                            if (STACK_TBL[STACK_PTR - 1][0] == '{') {
                                STACK_PTR--;
                            } else {
                                BAD_RECORD = true;
                            }
                            break;
                        case '>':
                            if (STACK_TBL[STACK_PTR - 1][0] == '<') {
                                STACK_PTR--;
                            } else {
                                BAD_RECORD = true;
                            }
                            break;
                        default:
                            BAD_RECORD = true;
                            break;
                    }
                }
            }
        }
        if (BAD_RECORD) {
            if (PROCESS_TEST) {
                System.out.println(MYNAME + " expected close for " + STACK_TBL[STACK_PTR - 1][0] + " but found " + THE_CHAR + " instead");
            }
            switch (THE_CHAR) {
                case ')':
                    FILE_SCORE += 3;
                    break;
                case ']':
                    FILE_SCORE += 57;
                    break;
                case '}':
                    FILE_SCORE += 1197;
                    break;
                case '>':
                    FILE_SCORE += 25137;
                    break;
            }
        } else {
            completeTheLine();
        }
        readInputData();
    }

    private static void completeTheLine() {
        if (PROCESS_TEST) {
            System.out.println(MYNAME + " record " + WS_INPT.substring(0, CHAR_PTR - 1));
        }
        STACK_PTR = 0;
        STACK_MAX_LVL = 0;
        COMPLETION_PTR = 1;
        COMPLETION_LIST = "                                                                                                    ";
        COMPLETION_MAX = 0;
        for (CHAR_PTR = 1; CHAR_PTR <= 4096 && WS_INPT.charAt(CHAR_PTR - 1) != ' '; CHAR_PTR++) {
            THE_CHAR = WS_INPT.charAt(CHAR_PTR - 1);
            if (THE_CHAR_IS_OPEN) {
                STACK_PTR++;
                STACK_LVL[STACK_PTR - 1] = STACK_CURR_LVL;
                STACK_TBL[STACK_PTR - 1][0] = THE_CHAR;
            }
            if (STACK_CURR_LVL < 1) {
                System.out.println(MYNAME + " logic error STACK-CURR-LVL " + STACK_CURR_LVL);
                System.out.println(MYNAME + " record " + WS_REC_COUNT);
            }
            STACK_PTR1 = STACK_PTR;
            STACK_TBL[STACK_PTR - 1][1] = (byte) STACK_CURR_LVL;
            if (STACK_CURR_LVL > STACK_MAX_LVL) {
                STACK_MAX_LVL = STACK_CURR_LVL;
            }
            if (THE_CHAR_IS_CLOSE) {
                STACK_PTR1++;
                while (STACK_PTR1 <= STACK_MAX && STACK_TBL[STACK_PTR1 - 1][0] != CLOSE_CHAR || STACK_LVL[STACK_PTR1 - 1] != STACK_CURR_LVL) {
                    STACK_PTR1++;
                }
                if (STACK_PTR1 > STACK_MAX) {
                    addToCompletionList();
                }
            }
        }
        if (PROCESS_TEST) {
            System.out.println(MYNAME + " completion list " + COMPLETION_LIST);
        }
        TOTAL_SUB++;
        COMPLETION_MAX = COMPLETION_PTR - 1;
        for (COMPLETION_PTR = COMPLETION_MAX; COMPLETION_PTR >= 1; COMPLETION_PTR--) {
            TOTAL_SCORE[TOTAL_SUB - 1] *= 5;
            switch (COMPLETION_LIST.charAt(COMPLETION_PTR - 1)) {
                case ')':
                    TOTAL_SCORE[TOTAL_SUB - 1] += 1;
                    break;
                case ']':
                    TOTAL_SCORE[TOTAL_SUB - 1] += 2;
                    break;
                case '}':
                    TOTAL_SCORE[TOTAL_SUB - 1] += 3;
                    break;
                case '>':
                    TOTAL_SCORE[TOTAL_SUB - 1] += 4;
                    break;
            }
        }
        TOTAL_SCORE_MAX++;
    }

    private static void addToCompletionList() {
        switch (STACK_TBL[STACK_PTR - 1][0]) {
            case '(':
                COMPLETION_CHAR = ')';
                break;
            case '[':
                COMPLETION_CHAR = ']';
                break;
            case '{':
                COMPLETION_CHAR = '}';
                break;
            case '<':
                COMPLETION_CHAR = '>';
                break;
        }
        COMPLETION_LIST = COMPLETION_LIST.substring(0, COMPLETION_PTR - 1) + COMPLETION_CHAR + COMPLETION_LIST.substring(COMPLETION_PTR);
        COMPLETION_PTR++;
    }

    private static void readInputData() {
        WS_INPT = "                                                                                                                                                                                                                                                                ";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            WS_INPT = scanner.nextLine();
            WS_REC_COUNT++;
        } else {
            INPT_DATA_EOF = true;
        }
        scanner.close();
    }

    private static void closeInputData() {
        // In Java, closing standard input is not typically necessary.
    }
}