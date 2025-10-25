import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenSizeTest {
    public static void main(String[] args) throws IOException {
        int wsScrLinesDisp = 0;
        int wsScrColsDisp = 0;

        System.out.println("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:");

        for (int i = 0; i < 3; i++) {
            wsScrLinesDisp = getScreenLines();
            wsScrColsDisp = getScreenColumns();

            displayScreenSize(wsScrLinesDisp, wsScrColsDisp);
        }

        System.out.println("Using 'CBL_GET_SCR_SIZE' to get screen size:");

        for (int i = 0; i < 3; i++) {
            int[] screenSize = getScreenSize();
            wsScrLinesDisp = screenSize[0];
            wsScrColsDisp = screenSize[1];

            displayScreenSize(wsScrLinesDisp, wsScrColsDisp);
        }

        System.out.println("Done.");
    }

    private static int getScreenLines() {
        // Placeholder for actual implementation to get screen lines
        return 24; // Example value
    }

    private static int getScreenColumns() {
        // Placeholder for actual implementation to get screen columns
        return 80; // Example value
    }

    private static int[] getScreenSize() {
        // Placeholder for actual implementation to get screen size
        return new int[]{24, 80}; // Example values
    }

    private static void displayScreenSize(int lines, int columns) throws IOException {
        System.out.println("------------------------------------------------------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + columns);
        System.out.println("  Lines: " + lines);
        System.out.println("Resize and press enter to continue");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
    }
}