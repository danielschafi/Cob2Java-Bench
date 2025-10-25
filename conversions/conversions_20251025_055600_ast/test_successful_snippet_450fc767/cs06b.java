import java.util.Scanner;

public class cs06b {
    private static final String MYNAME = "cs06b";
    private static int WS_REC_COUNT = 0;
    private static int DAY_COUNT = 0;
    private static int NB_DAYS = 0;
    private static int UNSTRING_PTR = 1;
    private static int FISH_SUB = 0;
    private static long TOTAL_FISH = 0;
    private static long FISH_SWAP = 0;
    private static String CLI_ARGS = new String(new char[80]).replace('\0', '\u0000');
    private static char FISH_X = '\u0000';
    private static String PROCESS_TYPE = new String(new char[4]).replace('\0', '\u0000');
    private static String NB_DAYS_X = new String(new char[4]).replace('\0', '\u0000');
    private static int FISH_SUB_OUT = 0;
    private static String WS_INPT = new String(new char[1024]).replace('\0', ' ');
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = new String(new char[4]).replace('\0', '\u0000');
    private static long[] FISH = new long[10];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        CLI_ARGS = String.join(" ", args);
        String[] parts = CLI_ARGS.split("[\\s\u0000]+");
        if (parts.length >= 2) {
            PROCESS_TYPE = parts[0].toUpperCase();
            NB_DAYS = Integer.parseInt(parts[1]);
        }

        PROCESS_SW = PROCESS_TYPE;

        System.out.println(MYNAME + " number of days " + NB_DAYS);

        if (PROCESS_SW.equals("TEST")) {
            // In Java, there's no direct equivalent to READY TRACE, so we'll just print a message
            System.out.println("TRACE READY");
        }

        for (int i = 0; i < FISH.length; i++) {
            FISH[i] = 0;
        }

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        while (FISH_X != ' ') {
            loadInput();
        }

        scanner.close();

        for (FISH_SUB = 2; FISH_SUB < 10; FISH_SUB++) {
            TOTAL_FISH += FISH[FISH_SUB];
        }
        System.out.println(MYNAME + " initial number of fish " + TOTAL_FISH);

        dumpFishTable();

        for (DAY_COUNT = 1; DAY_COUNT <= NB_DAYS; DAY_COUNT++) {
            processOneDay();
        }

        TOTAL_FISH = 0;
        for (FISH_SUB = 1; FISH_SUB < 10; FISH_SUB++) {
            TOTAL_FISH += FISH[FISH_SUB];
        }
        DAY_COUNT--;
        System.out.println(MYNAME + " number of fish " + TOTAL_FISH + " after " + DAY_COUNT + " days");
        System.out.println(MYNAME + " records read " + WS_REC_COUNT);

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
        FISH_X = ' ';
        if (UNSTRING_PTR < WS_INPT.length()) {
            int commaIndex = WS_INPT.indexOf(',', UNSTRING_PTR);
            if (commaIndex == -1) {
                FISH_X = WS_INPT.substring(UNSTRING_PTR).trim().charAt(0);
                UNSTRING_PTR = WS_INPT.length();
            } else {
                FISH_X = WS_INPT.substring(UNSTRING_PTR, commaIndex).trim().charAt(0);
                UNSTRING_PTR = commaIndex + 1;
            }
        }

        if (FISH_X != ' ') {
            FISH_SUB = Integer.parseInt(String.valueOf(FISH_X)) + 1;
            FISH[FISH_SUB]++;
        }
    }

    private static void processOneDay() {
        if (PROCESS_SW.equals("TEST")) {
            // In Java, there's no direct equivalent to RESET TRACE, so we'll just print a message
            System.out.println("TRACE RESET");
        }

        FISH_SWAP = FISH[1];
        for (FISH_SUB = 2; FISH_SUB < 10; FISH_SUB++) {
            FISH[FISH_SUB - 1] = FISH[FISH_SUB];
        }
        FISH[9] = 0;
        FISH[7] += FISH_SWAP;
        FISH[9] += FISH_SWAP;

        if (PROCESS_SW.equals("TEST")) {
            dumpFishTable();
            // In Java, there's no direct equivalent to READY TRACE, so we'll just print a message
            System.out.println("TRACE READY");
        }
    }

    private static void dumpFishTable() {
        System.out.print(MYNAME + " day " + DAY_COUNT + " ");
        for (FISH_SUB = 1; FISH_SUB < 10; FISH_SUB++) {
            FISH_SUB_OUT = FISH_SUB - 1;
            System.out.print(FISH_SUB_OUT + " " + FISH[FISH_SUB] + ",");
        }
        System.out.println();
    }
}