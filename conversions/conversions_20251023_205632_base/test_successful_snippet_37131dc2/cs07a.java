import java.util.Scanner;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cs07a {
    private static final String MYNAME = "cs07a";
    private static int WS_REC_COUNT = 0;
    private static int NB_DAYS = 0;
    private static int UNSTRING_PTR = 1;
    private static int NB_CRABS = 0;
    private static long TOTAL_FUEL = 0;
    private static long LOW_TOTAL_FUEL = 999999999999L;
    private static int HIGH_CRAB = 0;
    private static int LOW_CRAB = 9999;
    private static int HPOSN_DIFF = 0;
    private static int HPOSN = 0;
    private static int LOW_HPOSN = 0;
    private static String CRAB_X = "    ";
    private static String PROCESS_TYPE = "    ";
    private static String WS_INPT = " ".repeat(4096);
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = "    ";
    private static int[] CRAB_TBL = new int[1000];
    private static int CRAB_INDX = 1;

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(MYNAME + " " + dateFormat.format(new Date()));

        if (args.length > 0) {
            PROCESS_TYPE = args[0].toUpperCase();
        }

        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("READY TRACE");
        }

        Arrays.fill(CRAB_TBL, 0);
        CRAB_INDX = 1;

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        while (!CRAB_X.trim().isEmpty()) {
            loadInput();
        }

        scanner.close();

        System.out.println(MYNAME + " low crab        " + LOW_CRAB);
        System.out.println(MYNAME + " high crab       " + HIGH_CRAB);
        System.out.println(MYNAME + " nb crabs        " + NB_CRABS);

        for (HPOSN = 0; HPOSN <= HIGH_CRAB + 1; HPOSN++) {
            process();
        }

        System.out.println(MYNAME + " low fuel hposn  " + LOW_HPOSN);
        System.out.println(MYNAME + " low fuel amount " + LOW_TOTAL_FUEL);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);

        System.out.println(MYNAME + " " + dateFormat.format(new Date()));
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
        CRAB_X = "    ";
        String[] parts = WS_INPT.split(",");
        if (UNSTRING_PTR - 1 < parts.length) {
            CRAB_X = parts[UNSTRING_PTR - 1].trim();
            UNSTRING_PTR++;
        }

        if (!CRAB_X.trim().isEmpty()) {
            NB_CRABS++;
            CRAB_INDX = NB_CRABS;
            CRAB_TBL[CRAB_INDX - 1] = Integer.parseInt(CRAB_X);
            if (CRAB_TBL[CRAB_INDX - 1] > HIGH_CRAB) {
                HIGH_CRAB = CRAB_TBL[CRAB_INDX - 1];
            }
            if (CRAB_TBL[CRAB_INDX - 1] < LOW_CRAB) {
                LOW_CRAB = CRAB_TBL[CRAB_INDX - 1];
            }
        }
    }

    private static void process() {
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("RESET TRACE");
            System.out.println(" hposn " + HPOSN);
        }

        TOTAL_FUEL = 0;
        for (CRAB_INDX = 1; CRAB_INDX <= NB_CRABS; CRAB_INDX++) {
            HPOSN_DIFF = CRAB_TBL[CRAB_INDX - 1] - HPOSN;
            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println(MYNAME + " diff  " + Math.abs(HPOSN_DIFF));
            }
            TOTAL_FUEL += Math.abs(HPOSN_DIFF);
        }

        if (TOTAL_FUEL < LOW_TOTAL_FUEL) {
            LOW_TOTAL_FUEL = TOTAL_FUEL;
            LOW_HPOSN = HPOSN;
        }

        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println(" total " + TOTAL_FUEL);
            System.out.println("READY TRACE");
        }
    }
}