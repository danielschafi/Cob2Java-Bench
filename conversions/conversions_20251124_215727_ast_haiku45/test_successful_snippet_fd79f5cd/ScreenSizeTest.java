import java.util.Scanner;

public class ScreenSizeTest {
    private static int wsScreenLines = 0;
    private static int wsScreenCols = 0;
    private static String wsScreenLinesDisp = "";
    private static String wsScreenColsDisp = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainProcedure();
    }

    private static void mainProcedure() {
        clearScreen();

        System.out.println("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:");

        for (int i = 0; i < 3; i++) {
            wsScreenLines = getTerminalLines();
            wsScreenCols = getTerminalColumns();
            wsScreenLinesDisp = String.format("%3d", wsScreenLines).replace(' ', '0');
            wsScreenColsDisp = String.format("%3d", wsScreenCols).replace(' ', '0');
            displayScreensSize();
        }

        clearScreen();
        System.out.println("Using 'CBL_GET_SCR_SIZE' to get screen size:");

        for (int i = 0; i < 3; i++) {
            cblGetScrSize();
            wsScreenLinesDisp = String.format("%3d", wsScreenLines).replace(' ', '0');
            wsScreenColsDisp = String.format("%3d", wsScreenCols).replace(' ', '0');
            displayScreensSize();
        }

        System.out.println("Done.");
        System.exit(0);
    }

    private static void displayScreensSize() {
        System.out.println("-------------------------------------------------" + "------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + wsScreenColsDisp);
        System.out.println("  Lines: " + wsScreenLinesDisp);
        System.out.println("Resize and press enter to continue");
        scanner.nextLine();
    }

    private static int getTerminalLines() {
        String lines = System.getenv("LINES");
        if (lines != null) {
            try {
                return Integer.parseInt(lines);
            } catch (NumberFormatException e) {
                return 24;
            }
        }
        return 24;
    }

    private static int getTerminalColumns() {
        String cols = System.getenv("COLUMNS");
        if (cols != null) {
            try {
                return Integer.parseInt(cols);
            } catch (NumberFormatException e) {
                return 80;
            }
        }
        return 80;
    }

    private static void cblGetScrSize() {
        wsScreenLines = getTerminalLines();
        wsScreenCols = getTerminalColumns();
    }

    private static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
}