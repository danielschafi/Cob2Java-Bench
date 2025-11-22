import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class cs10b {
    private static final String MYNAME = "cs10b";
    private static int wsRecCount = 0;
    private static int stackPtr = 0;
    private static int stackPtr1 = 0;
    private static int stackMax = 0;
    private static int stackCurrLvl = 0;
    private static int stackMaxLvl = 0;
    private static int charPtr = 0;
    private static int completionPtr = 1;
    private static int completionMax = 0;
    private static int fileScore = 0;
    private static long totalScoreMax = 0;
    private static int totalSub = 0;
    private static String processType = "";
    private static char completionChar = ' ';
    private static char closeChar = ' ';
    private static char theChar = ' ';
    private static final char[] COMPLETION_LIST = new char[100];
    private static final long[] TOTAL_SCORE = new long[100];
    private static final char[][] STACK_TBL = new char[256][2];
    private static final char[] WS_INPT = new char[4096];
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static boolean badRecord = false;

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        Arrays.fill(TOTAL_SCORE, 0);
        totalSub = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            readInptData(reader);

            while (!inptDataEof) {
                processInput();
            }

            reader.close();

            Arrays.sort(TOTAL_SCORE);
            for (int i = (int) totalScoreMax - 1; i >= 0; i--) {
                System.out.println(MYNAME + " " + (TOTAL_SCORE.length - i) + " total score " + TOTAL_SCORE[i]);
            }

            totalSub = (int) (totalScoreMax / 2);
            System.out.println(MYNAME + " file score      " + fileScore);
            System.out.println(MYNAME + " total score     " + TOTAL_SCORE[totalSub]);
            System.out.println(MYNAME + " records read    " + wsRecCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processInput() {
        badRecord = false;
        for (charPtr = 0; charPtr < WS_INPT.length && WS_INPT[charPtr] != ' ' && !badRecord; charPtr++) {
            theChar = WS_INPT[charPtr];
            if (isOpenChar(theChar)) {
                stackPtr++;
                STACK_TBL[stackPtr][0] = theChar;
            } else {
                if (stackPtr == 0) {
                    System.out.println(MYNAME + " stack pointer 0");
                    badRecord = true;
                } else {
                    if (processTest) {
                        System.out.println(MYNAME + " " + theChar + " " + STACK_TBL[stackPtr][0]);
                    }
                    if (isMatchingPair(STACK_TBL[stackPtr][0], theChar)) {
                        stackPtr--;
                    } else {
                        badRecord = true;
                    }
                }
            }
        }

        if (badRecord) {
            if (processTest) {
                System.out.println(MYNAME + " expected close for " + STACK_TBL[stackPtr][0] + " but found " + theChar + " instead");
            }
            switch (theChar) {
                case ')':
                    fileScore += 3;
                    break;
                case ']':
                    fileScore += 57;
                    break;
                case '}':
                    fileScore += 1197;
                    break;
                case '>':
                    fileScore += 25137;
                    break;
            }
        } else {
            completeTheLine();
        }

        try {
            readInptData(new BufferedReader(new InputStreamReader(System.in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void completeTheLine() {
        if (processTest) {
            System.out.println(MYNAME + " record " + new String(WS_INPT, 0, charPtr));
        }

        stackPtr = 0;
        stackMaxLvl = 0;
        stackCurrLvl = 0;
        completionPtr = 1;
        Arrays.fill(COMPLETION_LIST, ' ');

        for (charPtr = 0; charPtr < WS_INPT.length && WS_INPT[charPtr] != ' '; charPtr++) {
            theChar = WS_INPT[charPtr];
            if (isOpenChar(theChar)) {
                stackCurrLvl++;
            }
            if (stackCurrLvl < 1) {
                System.out.println(MYNAME + " logic error STACK-CURR-LVL " + stackCurrLvl);
                System.out.println(MYNAME + " record " + wsRecCount);
            }
            stackPtr++;
            STACK_TBL[stackPtr][0] = theChar;
            STACK_TBL[stackPtr][1] = (char) stackCurrLvl;
            if (stackCurrLvl > stackMaxLvl) {
                stackMaxLvl = stackCurrLvl;
            }
            if (isCloseChar(theChar)) {
                stackCurrLvl--;
            }
        }

        stackMax = stackPtr;
        for (stackPtr = 1; stackPtr <= stackMax; stackPtr++) {
            theChar = STACK_TBL[stackPtr][0];
            if (isOpenChar(theChar)) {
                switch (theChar) {
                    case '(':
                        closeChar = ')';
                        break;
                    case '[':
                        closeChar = ']';
                        break;
                    case '{':
                        closeChar = '}';
                        break;
                    case '<':
                        closeChar = '>';
                        break;
                }
                for (stackPtr1 = stackPtr; stackPtr1 <= stackMax && (STACK_TBL[stackPtr1][0] != ' ' || (STACK_TBL[stackPtr1][0] == closeChar && STACK_TBL[stackPtr1][1] == STACK_TBL[stackPtr][1])); stackPtr1++) {
                }
                if (STACK_TBL[stackPtr1][0] == ' ') {
                    addToCompletionList();
                }
            }
        }

        if (processTest) {
            System.out.println(MYNAME + " completion list " + new String(COMPLETION_LIST, 0, completionPtr - 1));
        }

        totalSub++;
        completionMax = completionPtr - 1;
        for (completionPtr = completionMax; completionPtr > 0; completionPtr--) {
            TOTAL_SCORE[totalSub] *= 5;
            switch (COMPLETION_LIST[completionPtr]) {
                case ')':
                    TOTAL_SCORE[totalSub] += 1;
                    break;
                case ']':
                    TOTAL_SCORE[totalSub] += 2;
                    break;
                case '}':
                    TOTAL_SCORE[totalSub] += 3;
                    break;
                case '>':
                    TOTAL_SCORE[totalSub] += 4;
                    break;
            }
        }

        totalScoreMax++;
    }

    private static void addToCompletionList() {
        switch (STACK_TBL[stackPtr][0]) {
            case '(':
                completionChar = ')';
                break;
            case '[':
                completionChar = ']';
                break;
            case '{':
                completionChar = '}';
                break;
            case '<':
                completionChar = '>';
                break;
        }
        COMPLETION_LIST[completionPtr++] = completionChar;
    }

    private static void readInptData(BufferedReader reader) throws IOException {
        Arrays.fill(WS_INPT, ' ');
        String line = reader.readLine();
        if (line == null) {
            inptDataEof = true;
        } else {
            wsRecCount++;
            line.getChars(0, Math.min(line.length(), WS_INPT.length), WS_INPT, 0);
        }
    }

    private static boolean isOpenChar(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }

    private static boolean isCloseChar(char c) {
        return c == ')' || c == ']' || c == '}' || c == '>';
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}') ||
               (open == '<' && close == '>');
    }
}