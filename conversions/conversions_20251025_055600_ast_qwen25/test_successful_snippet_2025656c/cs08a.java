import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class cs08a {
    private static final String MYNAME = "cs08a";
    private static int WS_REC_COUNT = 0;
    private static int ENTRY_COUNT = 0;
    private static int UNSTRING_PTR = 0;
    private static int SEGMENT_COUNT = 0;
    private static int UNIQUE_COUNT = 0;
    private static String PROCESS_TYPE = "    ";
    private static String IN_SIGNAL_PATTERNS = "                                                            ";
    private static String IN_FOUR_DIGITS = "                                ";
    private static String WS_INPT = "                                        ";
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = "    ";
    private static final int MAX_ENTRIES = 200;
    private static final int MAX_SIGNALS = 10;
    private static final int MAX_DIGITS = 4;
    private static String[][] SIGNAL_PATTERN = new String[MAX_ENTRIES][MAX_SIGNALS];
    private static String[][] DIGIT = new String[MAX_ENTRIES][MAX_DIGITS];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        if (args.length > 0) {
            PROCESS_TYPE = args[0].toUpperCase();
        }

        if (PROCESS_TYPE.equals("TEST")) {
            // Simulate trace ready
        }

        for (int i = 0; i < MAX_ENTRIES; i++) {
            for (int j = 0; j < MAX_SIGNALS; j++) {
                SIGNAL_PATTERN[i][j] = "        ";
            }
            for (int j = 0; j < MAX_DIGITS; j++) {
                DIGIT[i][j] = "        ";
            }
        }

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        while (!INPT_DATA_EOF) {
            loadInput();
        }

        scanner.close();

        for (int ENTRY_INDX = 0; ENTRY_INDX < ENTRY_COUNT; ENTRY_INDX++) {
            processEntries(ENTRY_INDX);
        }

        System.out.println(MYNAME + " unique count    " + UNIQUE_COUNT);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);

        System.out.println(MYNAME + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
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
        ENTRY_COUNT++;
        String[] parts = WS_INPT.split(" \\| ");
        if (parts.length >= 2) {
            IN_SIGNAL_PATTERNS = String.format("%-60s", parts[0]);
            IN_FOUR_DIGITS = String.format("%-32s", parts[1]);
        }

        UNSTRING_PTR = 0;
        for (int SIGNAL_INDX = 0; SIGNAL_INDX < MAX_SIGNALS; SIGNAL_INDX++) {
            int spaceIndex = IN_SIGNAL_PATTERNS.indexOf(' ', UNSTRING_PTR);
            if (spaceIndex != -1) {
                SIGNAL_PATTERN[ENTRY_COUNT - 1][SIGNAL_INDX] = IN_SIGNAL_PATTERNS.substring(UNSTRING_PTR, spaceIndex).trim();
                UNSTRING_PTR = spaceIndex + 1;
            } else {
                SIGNAL_PATTERN[ENTRY_COUNT - 1][SIGNAL_INDX] = IN_SIGNAL_PATTERNS.substring(UNSTRING_PTR).trim();
                break;
            }
        }

        UNSTRING_PTR = 0;
        for (int DIGIT_INDX = 0; DIGIT_INDX < MAX_DIGITS; DIGIT_INDX++) {
            int spaceIndex = IN_FOUR_DIGITS.indexOf(' ', UNSTRING_PTR);
            if (spaceIndex != -1) {
                DIGIT[ENTRY_COUNT - 1][DIGIT_INDX] = IN_FOUR_DIGITS.substring(UNSTRING_PTR, spaceIndex).trim();
                UNSTRING_PTR = spaceIndex + 1;
            } else {
                DIGIT[ENTRY_COUNT - 1][DIGIT_INDX] = IN_FOUR_DIGITS.substring(UNSTRING_PTR).trim();
                break;
            }
        }

        readInputData(scanner);
    }

    private static void processEntries(int ENTRY_INDX) {
        if (PROCESS_TYPE.equals("TEST")) {
            // Simulate trace reset
        }

        for (int DIGIT_INDX = 0; DIGIT_INDX < MAX_DIGITS; DIGIT_INDX++) {
            SEGMENT_COUNT = 0;
            for (char c : DIGIT[ENTRY_INDX][DIGIT_INDX].toCharArray()) {
                if (c != ' ') {
                    SEGMENT_COUNT++;
                } else {
                    break;
                }
            }
            switch (SEGMENT_COUNT) {
                case 2:
                case 3:
                case 4:
                case 7:
                    UNIQUE_COUNT++;
                    break;
                default:
                    break;
            }
        }

        if (PROCESS_TYPE.equals("TEST")) {
            // Simulate trace ready
        }
    }
}