import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class cs08b {
    private static final String MYNAME = "cs08b";
    private static int wsRecCount = 0;
    private static int entryCount = 0;
    private static int unstringPtr = 0;
    private static int stringPtr = 0;
    private static int segmentCount = 0;
    private static int xCount = 0;
    private static int sub1 = 0;
    private static int sub2 = 0;
    private static int sub3 = 0;
    private static long totalDigits = 0;
    private static String processType = "    ";
    private static String inSignalPatterns = "                                                  ";
    private static String inFourDigits = "                                ";
    private static char[] codedValueTbl = new char[8];
    private static char[] oneTbl = new char[8];
    private static char[] fourTbl = new char[8];
    private static char[] sevenTbl = new char[8];
    private static char[] eightTbl = new char[8];
    private static char[] zeroTbl = new char[8];
    private static char[] sixTbl = new char[8];
    private static char[] nineTbl = new char[8];
    private static char[] twoTbl = new char[8];
    private static char[] threeTbl = new char[8];
    private static char[] fiveTbl = new char[8];
    private static char mapA = ' ';
    private static char mapB = ' ';
    private static char mapC = ' ';
    private static char mapD = ' ';
    private static char mapE = ' ';
    private static char mapF = ' ';
    private static char mapG = ' ';
    private static char[] cdeTbl = new char[3];
    private static String wsInpt = "                                        ";
    private static boolean inptDataEof = false;
    private static String processSw = "    ";
    private static String[][] signalPatternTbl = new String[200][10];
    private static String[][] digitTbl = new String[200][4];
    private static char[] digitValTbl = new char[200];
    private static String[] allDigitsTbl = new String[200];
    private static String[] sixesTbl = new String[3];
    private static String[] fivesTbl = new String[3];

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(MYNAME + " " + dateFormat.format(new Date()));

        if (args.length > 0) {
            processType = args[0].toUpperCase();
        }
        processSw = processType;

        Arrays.fill(entryPatternTbl, "");
        Arrays.fill(sixesTbl, "");
        Arrays.fill(fivesTbl, "");
        Arrays.fill(cdeTbl, ' ');

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
            loadInput();
        }
        scanner.close();

        for (int entryIndx = 0; entryIndx < entryCount; entryIndx++) {
            processEntries(entryIndx);
        }

        System.out.println(MYNAME + " total digits    " + totalDigits);
        System.out.println(MYNAME + " records read    " + wsRecCount);

        System.out.println(MYNAME + " " + dateFormat.format(new Date()));
    }

    private static void loadInput() {
        String[] parts = wsInpt.split(" \\| ");
        inSignalPatterns = parts[0];
        inFourDigits = parts[1];

        unstringPtr = 0;
        for (int signalIndx = 0; signalIndx < 10; signalIndx++) {
            String[] signalParts = inSignalPatterns.split(" ");
            signalPatternTbl[entryCount][signalIndx] = signalParts[unstringPtr++];
        }

        unstringPtr = 0;
        for (int digitIndx = 0; digitIndx < 4; digitIndx++) {
            String[] digitParts = inFourDigits.split(" ");
            digitTbl[entryCount][digitIndx] = digitParts[unstringPtr++];
        }

        entryCount++;
    }

    private static void processEntries(int entryIndx) {
        if (processSw.equals("TEST")) {
            System.out.println("TRACE");
        }

        Arrays.fill(sixesTbl, "");
        Arrays.fill(cdeTbl, ' ');

        processSignals(entryIndx);

        process069(entryIndx);

        process235(entryIndx);

        Arrays.sort(oneTbl);
        Arrays.sort(twoTbl);
        Arrays.sort(threeTbl);
        Arrays.sort(fourTbl);
        Arrays.sort(fiveTbl);
        Arrays.sort(sixTbl);
        Arrays.sort(sevenTbl);
        Arrays.sort(eightTbl);
        Arrays.sort(nineTbl);
        Arrays.sort(zeroTbl);

        processDigits(entryIndx);
    }

    private static void processSignals(int entryIndx) {
        sub1 = 0;
        sub2 = 0;
        sub3 = 0;
        for (int signalIndx = 0; signalIndx < 10; signalIndx++) {
            segmentCount = 0;
            for (char c : signalPatternTbl[entryIndx][signalIndx].toCharArray()) {
                if (c != ' ') {
                    segmentCount++;
                }
            }
            switch (segmentCount) {
                case 2:
                    oneTbl[sub1++] = signalPatternTbl[entryIndx][signalIndx].charAt(0);
                    break;
                case 3:
                    sevenTbl[sub2++] = signalPatternTbl[entryIndx][signalIndx].charAt(0);
                    break;
                case 4:
                    fourTbl[sub3++] = signalPatternTbl[entryIndx][signalIndx].charAt(0);
                    break;
                case 7:
                    eightTbl[sub3++] = signalPatternTbl[entryIndx][signalIndx].charAt(0);
                    break;
                case 6:
                    sixesTbl[sub1++] = signalPatternTbl[entryIndx][signalIndx];
                    break;
                case 5:
                    fivesTbl[sub2++] = signalPatternTbl[entryIndx][signalIndx];
                    break;
            }
        }

        map();

        if (processSw.equals("TEST")) {
            System.out.println(" A " + mapA);
            System.out.println(" B " + mapB);
            System.out.println(" C " + mapC);
            System.out.println(" D " + mapD);
            System.out.println(" E " + mapE);
            System.out.println(" F " + mapF);
            System.out.println(" G " + mapG);
        }
    }

    private static void process069(int entryIndx) {
        Arrays.fill(zeroTbl, ' ');
        Arrays.fill(sixTbl, ' ');
        Arrays.fill(nineTbl, ' ');

        for (sub1 = 0; sub1 < 3; sub1++) {
            if (processSw.equals("TEST")) {
                System.out.println(MYNAME + " SIXES(" + sub1 + ") " + sixesTbl[sub1]);
            }
            if (Arrays.equals(zeroTbl, "        ".toCharArray())) {
                process0(entryIndx);
            }
            if (Arrays.equals(sixTbl, "        ".toCharArray())) {
                process6(entryIndx);
            }
            if (Arrays.equals(nineTbl, "        ".toCharArray())) {
                process9(entryIndx);
            }
        }
    }

    private static void process0(int entryIndx) {
        xCount = 0;
        for (char c : sixesTbl[sub1].toCharArray()) {
            if (c == mapA) {
                xCount++;
            }
        }
        if (xCount > 0) {
            xCount = 0;
            for (char c : sixesTbl[sub1].toCharArray()) {
                if (c == mapB) {
                    xCount++;
                }
            }
            if (xCount > 0) {
                xCount = 0;
                for (char c : sixesTbl[sub1].toCharArray()) {
                    if (c == mapC) {
                        xCount++;
                    }
                }
                if (xCount > 0) {
                    xCount = 0;
                    for (char c : sixesTbl[sub1].toCharArray()) {
                        if (c == mapE) {
                            xCount++;
                        }
                    }
                    if (xCount > 0) {
                        xCount = 0;
                        for (char c : sixesTbl[sub1].toCharArray()) {
                            if (c == mapF) {
                                xCount++;
                            }
                        }
                        if (xCount > 0) {
                            xCount = 0;
                            for (char c : sixesTbl[sub1].toCharArray()) {
                                if (c == mapG) {
                                    xCount++;
                                }
                            }
                            if (xCount > 0) {
                                zeroTbl = sixesTbl[sub1].toCharArray();
                                if (processSw.equals("TEST")) {
                                    System.out.println(MYNAME + " SIXES(" + sub1 + ") is ZER0");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void process6(int entryIndx) {
        xCount = 0;
        for (char c : sixesTbl[sub1].toCharArray()) {