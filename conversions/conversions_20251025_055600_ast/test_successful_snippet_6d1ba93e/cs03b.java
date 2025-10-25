import java.util.Scanner;

public class cs03b {
    private static final String MYNAME = "cs03b";
    private static int WS_REC_COUNT = 0;
    private static int O2_COUNT = 9;
    private static int CO2_COUNT = 9;
    private static int O2_INDX = 0;
    private static int CO2_INDX = 0;
    private static int BIT_EXPONENT = 0;
    private static int NB_BITS = 1;
    private static int BIT_TO_COUNT = 0;
    private static long CURR_PRODUCT = 0;
    private static long O2_RATING = 0;
    private static long CO2_RATING = 0;
    private static String PROCESS_TYPE = "    ";
    private static String WS_INPT = "                        ";
    private static boolean INPT_DATA_EOF = false;
    private static boolean PROCESS_TEST = false;
    private static final int[][] BIT_COUNT_TABLE = new int[16][3];
    private static final String[][] INPT_TABLE = new String[2000][3];

    public static void main(String[] args) {
        if (args.length > 0) {
            PROCESS_TYPE = args[0].toUpperCase();
            PROCESS_TEST = PROCESS_TYPE.equals("TEST");
        }

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner);

        BIT_TO_COUNT = 1;
        while (!INPT_DATA_EOF) {
            readInputData(scanner);
        }

        scanner.close();

        while (BIT_TO_COUNT <= NB_BITS && O2_COUNT != 1) {
            for (int i = 0; i < 16; i++) {
                BIT_COUNT_TABLE[i][0] = 0;
                BIT_COUNT_TABLE[i][1] = 0;
            }
            for (int i = 0; i < WS_REC_COUNT; i++) {
                if (INPT_TABLE[i][1].equals("Y")) {
                    countBits(i);
                }
            }
            mostAndLeast();
            O2_COUNT = 0;
            for (int i = 0; i < WS_REC_COUNT; i++) {
                if (INPT_TABLE[i][0].charAt(BIT_TO_COUNT - 1) == BIT_COUNT_TABLE[BIT_TO_COUNT - 1][2] && INPT_TABLE[i][1].equals("Y")) {
                    O2_COUNT++;
                    O2_INDX = i;
                } else {
                    INPT_TABLE[i][1] = "N";
                }
            }
            if (PROCESS_TEST) {
                dumpTables();
            }
            BIT_TO_COUNT++;
        }

        BIT_TO_COUNT = 1;
        while (BIT_TO_COUNT <= NB_BITS && CO2_COUNT != 1) {
            for (int i = 0; i < 16; i++) {
                BIT_COUNT_TABLE[i][0] = 0;
                BIT_COUNT_TABLE[i][1] = 0;
            }
            for (int i = 0; i < WS_REC_COUNT; i++) {
                if (INPT_TABLE[i][2].equals("Y")) {
                    countBits(i);
                }
            }
            mostAndLeast();
            CO2_COUNT = 0;
            for (int i = 0; i < WS_REC_COUNT; i++) {
                if (INPT_TABLE[i][0].charAt(BIT_TO_COUNT - 1) == BIT_COUNT_TABLE[BIT_TO_COUNT - 1][3] && INPT_TABLE[i][2].equals("Y")) {
                    CO2_COUNT++;
                    CO2_INDX = i;
                } else {
                    INPT_TABLE[i][2] = "N";
                }
            }
            if (PROCESS_TEST) {
                dumpTables();
            }
            BIT_TO_COUNT++;
        }

        if (O2_COUNT != 1) {
            System.out.println(MYNAME + " O2-COUNT = " + O2_COUNT);
        }

        if (CO2_COUNT != 1) {
            System.out.println(MYNAME + " CO2-COUNT = " + CO2_COUNT);
        }

        System.out.println(MYNAME + " O2 rating   " + INPT_TABLE[O2_INDX][0]);
        System.out.println(MYNAME + " CO2 rating  " + INPT_TABLE[CO2_INDX][0]);

        for (int i = 1; i <= NB_BITS; i++) {
            BIT_EXPONENT = NB_BITS - i;
            if (INPT_TABLE[O2_INDX][0].charAt(i - 1) == '1') {
                O2_RATING += Math.pow(2, BIT_EXPONENT);
            }
            if (INPT_TABLE[CO2_INDX][0].charAt(i - 1) == '1') {
                CO2_RATING += Math.pow(2, BIT_EXPONENT);
            }
        }

        System.out.println(MYNAME + " O2 rating   " + O2_RATING);
        System.out.println(MYNAME + " CO2 rating  " + CO2_RATING);

        CURR_PRODUCT = O2_RATING * CO2_RATING;
        System.out.println(MYNAME + " product of O2 and CO2 " + CURR_PRODUCT);

        System.out.println(MYNAME + " records read " + WS_REC_COUNT);
    }

    private static void readInputData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            WS_INPT = scanner.nextLine();
            WS_REC_COUNT++;
            INPT_TABLE[WS_REC_COUNT - 1][0] = WS_INPT;
            INPT_TABLE[WS_REC_COUNT - 1][1] = "Y";
            INPT_TABLE[WS_REC_COUNT - 1][2] = "Y";
            if (WS_REC_COUNT == 1) {
                while (Character.isDigit(WS_INPT.charAt(NB_BITS - 1))) {
                    NB_BITS++;
                }
                NB_BITS--;
                System.out.println(MYNAME + " number of bits " + NB_BITS);
            }
        } else {
            INPT_DATA_EOF = true;
        }
    }

    private static void countBits(int index) {
        char bit = INPT_TABLE[index][0].charAt(BIT_TO_COUNT - 1);
        switch (bit) {
            case '0':
                BIT_COUNT_TABLE[BIT_TO_COUNT - 1][0]++;
                break;
            case '1':
                BIT_COUNT_TABLE[BIT_TO_COUNT - 1][1]++;
                break;
            default:
                System.out.println(MYNAME + " bad bit " + BIT_TO_COUNT + " " + index + " |" + INPT_TABLE[index][0] + "|");
                break;
        }
    }

    private static void mostAndLeast() {
        if (BIT_COUNT_TABLE[BIT_TO_COUNT - 1][0] > BIT_COUNT_TABLE[BIT_TO_COUNT - 1][1]) {
            BIT_COUNT_TABLE[BIT_TO_COUNT - 1][2] = '0';
            BIT_COUNT_TABLE[BIT_TO_COUNT - 1][3] = '1';
        } else {
            BIT_COUNT_TABLE[BIT_TO_COUNT - 1][2] = '1';
            BIT_COUNT_TABLE[BIT_TO_COUNT - 1][3] = '0';
        }
    }

    private static void dumpTables() {
        System.out.println(MYNAME + " " + BIT_TO_COUNT + " COUNT-0 " + BIT_COUNT_TABLE[BIT_TO_COUNT - 1][0] + " COUNT-1 " + BIT_COUNT_TABLE[BIT_TO_COUNT - 1][1] + " MOST-COMMON " + (char) BIT_COUNT_TABLE[BIT_TO_COUNT - 1][2] + " LEAST-COMMON " + (char) BIT_COUNT_TABLE[BIT_TO_COUNT - 1][3]);
        for (int i = 0; i < WS_REC_COUNT; i++) {
            System.out.println(MYNAME + " " + i + " " + INPT_TABLE[i][0] + " " + INPT_TABLE[i][1] + " " + INPT_TABLE[i][2]);
        }
    }
}