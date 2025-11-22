import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class cs11a {
    private static final String MYNAME = "cs11a";
    private static final String WS_INIT = "initial";
    private static int WS_REC_COUNT = 0;
    private static int NB_STEPS = 0;
    private static int ROW_IDX = 0;
    private static int COL_IDX = 0;
    private static long STEP_COUNT = 0;
    private static String NB_STEPS_X = "";
    private static String CLI_ARGS = "";
    private static String WS_INPT = "          ";
    private static boolean INPT_DATA_EOF = false;
    private static String PROCESS_SW = "";
    private static boolean ALL_FLASHED = false;
    private static final int[][] OCTO_E_LVL = new int[10][10];
    private static final boolean[][] OCTO_FLASHED_SW = new boolean[10][10];
    private static long TOTAL_FLASHES = 0;
    private static String PROCESS_TYPE = "";

    public static void main(String[] args) throws IOException {
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        TOTAL_FLASHES = 0;

        if (args.length > 0) {
            CLI_ARGS = String.join(" ", args);
        }

        String[] parts = CLI_ARGS.split(" ");
        if (parts.length > 0) {
            PROCESS_TYPE = parts[0].toUpperCase();
        }
        if (parts.length > 1) {
            NB_STEPS_X = parts[1];
        }

        NB_STEPS = Integer.parseInt(NB_STEPS_X);

        System.out.println(MYNAME + " number of steps " + NB_STEPS);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            WS_REC_COUNT++;
            ROW_IDX++;
            COL_IDX = 0;
            for (char c : line.toCharArray()) {
                COL_IDX++;
                OCTO_E_LVL[ROW_IDX - 1][COL_IDX - 1] = Character.getNumericValue(c);
                OCTO_FLASHED_SW[ROW_IDX - 1][COL_IDX - 1] = false;
            }
        }

        for (int step = 0; step < NB_STEPS; step++) {
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    OCTO_E_LVL[r][c]++;
                }
            }

            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println(" intermediate after adding 1");
                octodump(MYNAME);
            }

            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    if (OCTO_E_LVL[r][c] > 9) {
                        flasher(r + 1, c + 1, WS_INIT);
                    }
                }
            }

            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println(MYNAME + " intermediate after calling flasher");
                octodump(MYNAME);
            }

            STEP_COUNT++;
            ALL_FLASHED = true;
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    if (OCTO_FLASHED_SW[r][c]) {
                        OCTO_E_LVL[r][c] = 0;
                        OCTO_FLASHED_SW[r][c] = false;
                    } else {
                        ALL_FLASHED = false;
                    }
                }
            }

            if (ALL_FLASHED) {
                System.out.println(MYNAME + " all flashed on step " + STEP_COUNT);
            }

            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println(MYNAME);
                octodump(MYNAME);
            }
        }

        System.out.println(MYNAME + " total flashes   " + TOTAL_FLASHES);
        System.out.println(MYNAME + " records read    " + WS_REC_COUNT);
        System.out.println(MYNAME + " " + java.time.LocalDate.now());
    }

    private static void flasher(int row, int col, String dir) {
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher called with " + row + " " + col + " " + dir);
        }

        if (row < 1 || col < 1 || row > 10 || col > 10) {
            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println("flasher out of range goback");
            }
            return;
        }

        OCTO_E_LVL[row - 1][col - 1]++;
        if (OCTO_E_LVL[row - 1][col - 1] > 9) {
            if (OCTO_FLASHED_SW[row - 1][col - 1]) {
                if (PROCESS_TYPE.equals("TEST")) {
                    System.out.println("flasher flashed goback");
                }
                return;
            } else {
                OCTO_FLASHED_SW[row - 1][col - 1] = true;
                TOTAL_FLASHES++;
            }
        } else {
            if (PROCESS_TYPE.equals("TEST")) {
                System.out.println("flasher e-lvl <= 9 goback");
            }
            return;
        }

        flasher(row - 1, col, "north");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after north with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row - 1, col + 1, "northeast");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after northeast with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row, col + 1, "east");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after east with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row + 1, col + 1, "southeast");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after southeast with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row + 1, col, "south");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after south with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row + 1, col - 1, "southwest");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after southwest with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row, col - 1, "west");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after west with " + row + " " + col);
            octodump(MYNAME);
        }

        flasher(row - 1, col - 1, "northwest");
        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher after northwest with " + row + " " + col);
            octodump(MYNAME);
        }

        if (PROCESS_TYPE.equals("TEST")) {
            System.out.println("flasher goback with " + row + " " + col);
        }
    }

    private static void octodump(String name) {
        for (int r = 0; r < 10; r++) {
            System.out.print(name);
            for (int c = 0; c < 10; c++) {
                System.out.print(" " + OCTO_E_LVL[r][c]);
            }
            System.out.println();
        }
    }
}