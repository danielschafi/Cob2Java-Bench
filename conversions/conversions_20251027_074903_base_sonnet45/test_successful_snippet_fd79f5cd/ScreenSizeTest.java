import java.io.IOException;
import java.util.Scanner;

public class ScreenSizeTest {

    private static int wsScr_lines;
    private static int wsScr_cols;
    private static String wsScr_lines_disp;
    private static String wsScr_cols_disp;

    public static void main(String[] args) {
        clearScreen();
        displayAt("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:", 1, 1);

        for (int i = 0; i < 3; i++) {
            getScreenSizeFromTerminal();
            wsScr_lines_disp = formatNumber(wsScr_lines);
            wsScr_cols_disp = formatNumber(wsScr_cols);
            displayScreensSize();
        }

        clearScreen();
        displayAt("Using 'CBL_GET_SCR_SIZE' to get screen size:", 1, 1);

        for (int i = 0; i < 3; i++) {
            getScreenSize();
            wsScr_lines_disp = formatNumber(wsScr_lines);
            wsScr_cols_disp = formatNumber(wsScr_cols);
            displayScreensSize();
        }

        displayAt("Done.", 9, 1);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void displayAt(String text, int line, int col) {
        System.out.print(String.format("\033[%d;%dH%s", line, col, text));
        System.out.flush();
    }

    private static void getScreenSizeFromTerminal() {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty size < /dev/tty"};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            if (result != null && !result.isEmpty()) {
                String[] dimensions = result.split(" ");
                wsScr_lines = Integer.parseInt(dimensions[0]);
                wsScr_cols = Integer.parseInt(dimensions[1]);
            } else {
                wsScr_lines = 24;
                wsScr_cols = 80;
            }
        } catch (Exception e) {
            wsScr_lines = 24;
            wsScr_cols = 80;
        }
    }

    private static void getScreenSize() {
        getScreenSizeFromTerminal();
    }

    private static String formatNumber(int number) {
        return String.format("%3d", number);
    }

    private static void displayScreensSize() {
        displayAt("-------------------------------------------------------------", 2, 1);
        displayAt("Current screen size: ", 3, 1);
        displayAt("Columns: " + wsScr_cols_disp, 4, 1);
        displayAt("  Lines: " + wsScr_lines_disp, 5, 1);
        displayAt("Resize and press enter to continue", 7, 1);
        acceptOmitted();
    }

    private static void acceptOmitted() {
        try {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}