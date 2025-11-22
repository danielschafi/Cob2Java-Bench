import java.util.Scanner;

public class cs06a {
    private static final String MYNAME = "cs06a";
    private static int WS_REC_COUNT = 0;
    private static int DAY_COUNT = 0;
    private static int NB_DAYS = 0;
    private static final int MAX_FISH = 1000000;
    private static int NB_FISH = 0;
    private static int UNSTRING_PTR = 1;
    private static String CLI_ARGS = new String(new char[80]).replace('\0', '\u0000');
    private static String FISH_X = new String(new char[1]).replace('\0', '\u0000');
    private static String PROCESS_TYPE = new String(new char[4]).replace('\0', '\u0000');
    private static String NB_DAYS_X = new String(new char[4]).replace('\0', '\u0000');
    private static String WS_INPT = new String(new char[1024]).replace('\0', ' ');
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = new String(new char[4]).replace('\0', '\u0000');
    private static final int PROCESS_TEST = 0;
    private static int[][] FISH_TBL = new int[MAX_FISH][2];
    private static final int FISH = 0;
    private static final int NEW_FISH_SW = 1;

    public static void main(String[] args) {
        CLI_ARGS = String.join(" ", args);
        String[] parts = CLI_ARGS.split(" ");
        if (parts.length > 0) PROCESS_TYPE = parts[0].toUpperCase();
        if (parts.length > 1) NB_DAYS = Integer.parseInt(parts[1]);

        System.out.println(MYNAME + " number of days " + NB_DAYS);

        if (PROCESS_TYPE.equals("TEST")) {
            // READY TRACE
        }

        for (int i = 0; i < MAX_FISH; i++) {
            FISH_TBL[i][FISH] = 0;
            FISH_TBL[i][NEW_FISH_SW] = 0;
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            WS_INPT = scanner.nextLine();
            WS_REC_COUNT++;
            loadInput();
        }
        scanner.close();

        System.out.println(MYNAME + " initial number of fish " + NB_FISH);

        for (DAY_COUNT = 1; DAY_COUNT <= NB_DAYS; DAY_COUNT++) {
            processOneDay();
        }

        DAY_COUNT--;
        System.out.println(MYNAME + " number of fish " + NB_FISH + " after " + DAY_COUNT + " days");
        System.out.println(MYNAME + " records read " + WS_REC_COUNT);
    }

    private static void loadInput() {
        FISH_X = " ";
        String[] values = WS_INPT.split(",");
        if (UNSTRING_PTR - 1 < values.length) {
            FISH_X = values[UNSTRING_PTR - 1].trim();
            UNSTRING_PTR++;
        }

        if (!FISH_X.equals(" ")) {
            NB_FISH++;
            FISH_TBL[NB_FISH - 1][FISH] = Integer.parseInt(FISH_X);
            FISH_TBL[NB_FISH - 1][NEW_FISH_SW] = 0;
        }
    }

    private static void processOneDay() {
        if (PROCESS_TYPE.equals("TEST")) {
            // RESET TRACE
        }

        for (int FISH_INDX = 0; FISH_INDX < NB_FISH; FISH_INDX++) {
            if (FISH_TBL[FISH_INDX][FISH] == 0) {
                resetFish(FISH_INDX);
            } else {
                if (FISH_TBL[FISH_INDX][NEW_FISH_SW] == 1) {
                    FISH_TBL[FISH_INDX][NEW_FISH_SW] = 0;
                } else {
                    FISH_TBL[FISH_INDX][FISH]--;
                }
            }
        }

        if (PROCESS_TYPE.equals("TEST")) {
            System.out.print(MYNAME + " day " + DAY_COUNT + " ");
            for (int FISH_INDX = 0; FISH_INDX < NB_FISH; FISH_INDX++) {
                System.out.print(FISH_TBL[FISH_INDX][FISH] + ",");
            }
            System.out.println();
            // READY TRACE
        }
    }

    private static void resetFish(int FISH_INDX) {
        FISH_TBL[FISH_INDX][FISH] = 6;
        NB_FISH++;
        if (NB_FISH > MAX_FISH) {
            System.out.println(MYNAME + " internal fish table overflow on day " + DAY_COUNT);
            System.exit(8);
        }
        FISH_TBL[NB_FISH - 1][FISH] = 8;
        FISH_TBL[NB_FISH - 1][NEW_FISH_SW] = 1;
    }
}