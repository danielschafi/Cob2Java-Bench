import java.util.Scanner;

public class cs06a {
    private static final String MYNAME = "cs06a";
    private static int WS_REC_COUNT = 0;
    private static int DAY_COUNT = 0;
    private static int NB_DAYS = 0;
    private static final int MAX_FISH = 1000000;
    private static int NB_FISH = 0;
    private static int UNSTRING_PTR = 1;
    private static String CLI_ARGS = new String(new char[80]).replace('\0', '\0');
    private static String FISH_X = new String(new char[1]).replace('\0', '\0');
    private static String PROCESS_TYPE = new String(new char[4]).replace('\0', '\0');
    private static String NB_DAYS_X = new String(new char[4]).replace('\0', '\0');
    private static String WS_INPT = new String(new char[1024]).replace('\0', ' ');
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = new String(new char[4]).replace('\0', '\0');
    private static final boolean PROCESS_TEST = "TEST".equals(PROCESS_SW);
    private static final int[] FISH = new int[MAX_FISH];
    private static final boolean[] NEW_FISH = new boolean[MAX_FISH];

    public static void main(String[] args) {
        CLI_ARGS = String.join(" ", args);
        String[] parts = CLI_ARGS.split(" ");
        PROCESS_TYPE = parts[0].toUpperCase();
        NB_DAYS = Integer.parseInt(parts[1]);

        System.out.println(MYNAME + " number of days " + NB_DAYS);

        if (PROCESS_TEST) {
            // Ready trace
        }

        for (int i = 0; i < MAX_FISH; i++) {
            FISH[i] = 0;
            NEW_FISH[i] = false;
        }

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        loadInput();

        scanner.close();

        System.out.println(MYNAME + " initial number of fish " + NB_FISH);

        for (DAY_COUNT = 1; DAY_COUNT <= NB_DAYS; DAY_COUNT++) {
            processOneDay();
        }

        DAY_COUNT--;
        System.out.println(MYNAME + " number of fish " + NB_FISH + " after " + DAY_COUNT + " days");
        System.out.println(MYNAME + " records read " + WS_REC_COUNT);
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
        while (true) {
            FISH_X = " ";
            String[] parts = WS_INPT.split(",");
            if (UNSTRING_PTR - 1 < parts.length) {
                FISH_X = parts[UNSTRING_PTR - 1].trim();
                UNSTRING_PTR++;
            }

            if (!FISH_X.equals(" ")) {
                NB_FISH++;
                FISH[NB_FISH - 1] = Integer.parseInt(FISH_X);
                NEW_FISH[NB_FISH - 1] = false;
            } else {
                break;
            }
        }
    }

    private static void processOneDay() {
        if (PROCESS_TEST) {
            // Reset trace
        }

        for (int FISH_INDX = 0; FISH_INDX < NB_FISH; FISH_INDX++) {
            if (FISH[FISH_INDX] == 0) {
                resetFish(FISH_INDX);
            } else {
                if (NEW_FISH[FISH_INDX]) {
                    NEW_FISH[FISH_INDX] = false;
                } else {
                    FISH[FISH_INDX]--;
                }
            }
        }

        if (PROCESS_TEST) {
            System.out.print(MYNAME + " day " + DAY_COUNT + " ");
            for (int FISH_INDX = 0; FISH_INDX < NB_FISH; FISH_INDX++) {
                System.out.print(FISH[FISH_INDX] + ",");
            }
            System.out.println();
            // Ready trace
        }
    }

    private static void resetFish(int FISH_INDX) {
        FISH[FISH_INDX] = 6;
        NB_FISH++;
        if (NB_FISH > MAX_FISH) {
            System.out.println(MYNAME + " internal fish table overflow on day " + DAY_COUNT);
            System.exit(8);
        }
        FISH[NB_FISH - 1] = 8;
        NEW_FISH[NB_FISH - 1] = true;
    }
}