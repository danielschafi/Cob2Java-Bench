import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cs08a {
    private static final String MYNAME = "cs08a";
    private static int wsRecCount = 0;
    private static int entryCount = 0;
    private static int unstringPtr = 0;
    private static int segmentCount = 0;
    private static int uniqueCount = 0;
    private static String processType = "";
    private static String inSignalPatterns = " ".repeat(60);
    private static String inFourDigits = " ".repeat(32);
    private static String wsInpt = " ".repeat(4096);
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static final String[][] entryTbl = new String[200][18];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        if (args.length > 0) {
            processType = args[0].toUpperCase();
        }

        processTest = processType.equals("TEST");

        if (processTest) {
            System.out.println("TRACE READY");
        }

        for (String[] row : entryTbl) {
            for (int i = 0; i < row.length; i++) {
                row[i] = " ".repeat(8);
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            readInptData(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!inptDataEof) {
            loadInput();
            try {
                readInptData(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int entryIndx = 0; entryIndx < entryCount; entryIndx++) {
            processEntries(entryIndx);
        }

        System.out.println(MYNAME + " unique count    " + uniqueCount);
        System.out.println(MYNAME + " records read    " + wsRecCount);

        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private static void readInptData(BufferedReader reader) throws IOException {
        wsInpt = reader.readLine();
        if (wsInpt == null) {
            inptDataEof = true;
        } else {
            wsRecCount++;
        }
    }

    private static void loadInput() {
        entryCount++;
        String[] parts = wsInpt.split(" \\| ");
        inSignalPatterns = parts[0];
        inFourDigits = parts[1];

        unstringPtr = 0;
        for (int signalIndx = 0; signalIndx < 10; signalIndx++) {
            String[] signalParts = inSignalPatterns.split(" ");
            entryTbl[entryCount - 1][signalIndx] = signalParts[signalIndx];
        }

        unstringPtr = 0;
        for (int digitIndx = 0; digitIndx < 4; digitIndx++) {
            String[] digitParts = inFourDigits.split(" ");
            entryTbl[entryCount - 1][digitIndx + 10] = digitParts[digitIndx];
        }
    }

    private static void processEntries(int entryIndx) {
        if (processTest) {
            System.out.println("TRACE RESET");
        }

        for (int digitIndx = 0; digitIndx < 4; digitIndx++) {
            segmentCount = 0;
            String digit = entryTbl[entryIndx][digitIndx + 10];
            for (char c : digit.toCharArray()) {
                if (c != ' ') {
                    segmentCount++;
                }
            }

            switch (segmentCount) {
                case 2:
                case 3:
                case 4:
                case 7:
                    uniqueCount++;
                    break;
                default:
                    continue;
            }
        }

        if (processTest) {
            System.out.println("TRACE READY");
        }
    }
}