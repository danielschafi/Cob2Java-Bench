import java.util.Arrays;
import java.util.Scanner;

public class cs14b {
    private static final int MAX_LMNT = 26;
    private static final int MAX_PAIRS = 676;

    private static final String MYNAME = "cs14b";
    private static final String PROCESS_TEST = "TEST";

    private static int wsRecCount = 0;
    private static int ruleCount = 0;
    private static int nbSteps = 0;
    private static int stepCount = 0;
    private static int nbLmnt = 0;
    private static int newIdx = 0;
    private static int polyIdx = 0;
    private static int polyLen = 0;
    private static int polyPtr = 1;
    private static int lmntIdx = 0;
    private static long lmntMax = 0;
    private static long lmntMin = 9999999999L;
    private static int lmntCount = 0;
    private static long lmntDif = 0;
    private static String nbStepsX = "    ";
    private static char holdLmnt = ' ';
    private static String cliArgs = "        ";
    private static String processType = "    ";
    private static String initialPolymer = "        ";
    private static String newPair = "  ";

    private static String wsInpt = "        ";
    private static boolean inptDataEof = false;
    private static boolean rulesNow = false;
    private static String processSw = "    ";

    private static final String[][] ruleTbl = new String[100][2];
    private static final long[][] polyTbl = new long[MAX_PAIRS][3];
    private static final long[][] newPolyTbl = new long[MAX_PAIRS][3];
    private static final char[][] lmntTbl = new char[MAX_LMNT][2];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        cliArgs = String.join(" ", args);
        String[] parts = cliArgs.split(" ");
        processType = parts[0].toUpperCase();
        nbSteps = Integer.parseInt(parts[1]);

        System.out.println(MYNAME + " nb steps  " + nbSteps);

        for (String[] row : ruleTbl) {
            Arrays.fill(row, "  ");
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEof) {
            loadInput();
        }

        scanner.close();

        System.out.println(MYNAME + " number of rules " + ruleCount);

        Arrays.sort(ruleTbl, (a, b) -> a[0].compareTo(b[0]));

        for (int i = 0; i < nbSteps; i++) {
            constructPolymer();
        }

        if (processSw.equals(PROCESS_TEST)) {
            for (int i = 0; i < MAX_PAIRS; i++) {
                if (polyTbl[i][0] == 0) break;
                System.out.println(MYNAME + " " + i + " " + (char) polyTbl[i][0] + (char) polyTbl[i][1] + " " + polyTbl[i][2] + " " + polyTbl[i][3]);
            }
        }

        countElements();

        System.out.println(MYNAME + " most common     " + lmntMax);
        System.out.println(MYNAME + " least common    " + lmntMin);
        System.out.println(MYNAME + " difference      " + lmntDif);
        System.out.println(MYNAME + " records read    " + wsRecCount);

        System.out.println(MYNAME + " " + java.time.LocalDate.now());
    }

    private static void readInptData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
        } else {
            inptDataEof = true;
        }
        wsRecCount++;
    }

    private static void loadInput() {
        if (rulesNow) {
            ruleCount++;
            String[] parts = wsInpt.split(" -> ");
            ruleTbl[ruleCount - 1][0] = parts[0];
            ruleTbl[ruleCount - 1][1] = parts[1];
        } else if (wsRecCount == 1) {
            initPolymerTable();
            initElementTable();
        } else if (wsInpt.trim().isEmpty()) {
            rulesNow = true;
        }
        readInptData(scanner);
    }

    private static void initPolymerTable() {
        initialPolymer = wsInpt;
        for (long[] row : polyTbl) {
            Arrays.fill(row, 0);
        }
        for (polyIdx = 0; polyIdx < initialPolymer.length() - 1; polyIdx++) {
            polyTbl[polyIdx][0] = initialPolymer.charAt(polyIdx);
            polyTbl[polyIdx][1] = initialPolymer.charAt(polyIdx + 1);
            polyTbl[polyIdx][2] = 1;
            polyTbl[polyIdx][3] = 1;
        }
    }

    private static void initElementTable() {
        for (char[] row : lmntTbl) {
            Arrays.fill(row, ' ');
        }
        for (polyIdx = 0; polyIdx < initialPolymer.length(); polyIdx++) {
            lmntIdx = lmntIdx(initialPolymer.charAt(polyIdx));
            lmntTbl[lmntIdx][1] += 1;
        }
    }

    private static void constructPolymer() {
        for (long[] row : newPolyTbl) {
            System.arraycopy(row, 0, polyTbl[(int) row[0]], 0, 3);
        }
        stepCount++;
        System.out.println(MYNAME + " STEP " + stepCount);
        lmntdump();
        System.out.println(MYNAME + " POLYMER-TABLE");
        polydump(polyTbl);

        for (polyIdx = 0; polyIdx < MAX_PAIRS; polyIdx++) {
            if (polyTbl[polyIdx][2] > 0) {
                searchRules();
                lmntIdx = lmntIdx(ruleTbl[ruleIdx][1].charAt(0));
                if (processSw.equals(PROCESS_TEST)) {
                    System.out.println(MYNAME + " lmnt    " + lmntTbl[lmntIdx][0] + " lmnt-ct " + lmntTbl[lmntIdx][1] + " poly-pair-ct " + polyTbl[polyIdx][2]);
                    System.out.println(MYNAME + " rule " + ruleTbl[ruleIdx][0] + " -> " + ruleTbl[ruleIdx][1]);
                }
                lmntTbl[lmntIdx][1] += polyTbl[polyIdx][2];
                polyTbl[polyIdx][2] -= polyTbl[polyIdx][2];
                createNewPairs();
            }
        }

        System.out.println(MYNAME + " NEW-POLYMER-TABLE");
        polydump(newPolyTbl);
        for (long[] row : newPolyTbl) {
            System.arraycopy(row, 0, polyTbl[(int) row[0]], 0, 3);
        }
    }

    private static int ruleIdx;

    private static void searchRules() {
        for (ruleIdx = 0; ruleIdx < ruleCount; ruleIdx++) {
            if (ruleTbl[ruleIdx][0].equals("" + (char) polyTbl[polyIdx][0] + (char) polyTbl[polyIdx][1])) {
                break;
            }
        }
    }

    private static void createNewPairs() {
        newPair = "" + (char) polyTbl[polyIdx][0] + ruleTbl[ruleIdx][1].charAt(0);
        setNewPairActive();

        newPair = "" + ruleTbl[ruleIdx][1].charAt(0) + (char) polyTbl[polyIdx][1];
        setNewPairActive();
    }

    private static void setNewPairActive() {
        newIdx = pairIdx(newPair);
        newPolyTbl[newIdx][0] = newPair.charAt(0);
        newPolyTbl[newIdx][1] = newPair.charAt(1);
        newPolyTbl[newIdx][2] += polyTbl[polyIdx][2];
        newPolyTbl[newIdx][3] = 1;
    }

    private static void countElements() {
        lmntdump();
        for (lmntIdx = 0; lmntIdx < MAX_LMNT; lmntIdx++) {
            if (lmntTbl[lmntIdx][0] == ' ') break;
            if (lmntTbl[lmntIdx][1] < lmntMin) {
                lmntMin = lmntTbl[lmntIdx][1];
            }
            if (lmntTbl[lmntIdx][1] > lmntMax) {
                lmntMax = lmntTbl[lmntIdx][1];
            }
        }
        lmntDif = lmntMax - lmntMin;
    }

    private static int lmntIdx(char lsLmnt) {
        for (lmntIdx = 0; lmntIdx < MAX_LMNT; lmntIdx++) {
            if (lmntTbl[lmntIdx][0] == lsLmnt || lmntTbl[lmntIdx][0] == ' ') {
                break;
            }
        }
        if (lmntTbl[lmntIdx][0] == ' ') {
            lmntTbl[lmntIdx][0] = lsLmnt;
        }