import java.io.IOException;
import java.lang.reflect.Method;

public class ScreenSizeTest {
    private static int wsScrLines;
    private static int wsScrCols;
    private static String wsScrLinesDisp;
    private static String wsScrColsDisp;

    public static void main(String[] args) {
        System.out.print("\033[2J\033[H"); // Clear screen and move cursor to home

        System.out.println("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:");
        
        for (int i = 0; i < 3; i++) {
            getScreenSizeFromSystem();
            displayScreenSize();
        }

        System.out.print("\033[2J\033[H"); // Clear screen and move cursor to home
        System.out.println("Using 'CBL_GET_SCR_SIZE' to get screen size:");

        for (int i = 0; i < 3; i++) {
            getScreenSizeWithFunction();
            wsScrLinesDisp = String.format("%03d", wsScrLines);
            wsScrColsDisp = String.format("%03d", wsScrCols);
            displayScreenSize();
        }

        System.out.println("Done.");
    }

    private static void getScreenSizeFromSystem() {
        try {
            Process process = Runtime.getRuntime().exec("stty size");
            java.util.Scanner scanner = new java.util.Scanner(process.getInputStream());
            if (scanner.hasNextLine()) {
                String[] dimensions = scanner.nextLine().split(" ");
                wsScrLines = Integer.parseInt(dimensions[0]);
                wsScrCols = Integer.parseInt(dimensions[1]);
            }
            scanner.close();
        } catch (IOException | NumberFormatException e) {
            wsScrLines = 24;
            wsScrCols = 80;
        }
    }

    private static void getScreenSizeWithFunction() {
        try {
            Method method = ScreenSizeTest.class.getMethod("getScreenSizeNative");
            Object result = method.invoke(null);
            int[] sizeArray = (int[]) result;
            wsScrLines = sizeArray[0];
            wsScrCols = sizeArray[1];
        } catch (Exception e) {
            wsScrLines = 24;
            wsScrCols = 80;
        }
    }

    private static int[] getScreenSizeNative() {
        // Simulated native function behavior
        int[] size = new int[2];
        try {
            Process process = Runtime.getRuntime().exec("stty size");
            java.util.Scanner scanner = new java.util.Scanner(process.getInputStream());
            if (scanner.hasNextLine()) {
                String[] dimensions = scanner.nextLine().split(" ");
                size[0] = Integer.parseInt(dimensions[0]);
                size[1] = Integer.parseInt(dimensions[1]);
            } else {
                size[0] = 24;
                size[1] = 80;
            }
            scanner.close();
        } catch (IOException | NumberFormatException e) {
            size[0] = 24;
            size[1] = 80;
        }
        return size;
    }

    private static void displayScreenSize() {
        System.out.println("--------------------------------------------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + wsScrColsDisp);
        System.out.println("  Lines: " + wsScrLinesDisp);
        System.out.println("Resize and press enter to continue");
        try {
            System.in.read();
        } catch (IOException ignored) {}
    }
}