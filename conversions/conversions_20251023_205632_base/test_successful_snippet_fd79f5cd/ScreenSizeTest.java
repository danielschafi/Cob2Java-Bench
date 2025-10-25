import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenSizeTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Using 'System.getenv' to get screen size:");

        for (int i = 0; i < 3; i++) {
            String cols = System.getenv("COLUMNS");
            String lines = System.getenv("LINES");

            int wsScrColsDisp = cols != null ? Integer.parseInt(cols) : 0;
            int wsScrLinesDisp = lines != null ? Integer.parseInt(lines) : 0;

            displayScreenSize(wsScrLinesDisp, wsScrColsDisp);

            System.out.println("Resize and press enter to continue");
            reader.readLine();
        }

        System.out.println("Using 'stty size' to get screen size:");

        for (int i = 0; i < 3; i++) {
            Process process = Runtime.getRuntime().exec("stty size");
            BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String[] size = processReader.readLine().split(" ");
            processReader.close();

            int wsScrLines = Integer.parseInt(size[0]);
            int wsScrCols = Integer.parseInt(size[1]);

            int wsScrLinesDisp = wsScrLines;
            int wsScrColsDisp = wsScrCols;

            displayScreenSize(wsScrLinesDisp, wsScrColsDisp);

            System.out.println("Resize and press enter to continue");
            reader.readLine();
        }

        System.out.println("Done.");
    }

    private static void displayScreenSize(int wsScrLinesDisp, int wsScrColsDisp) {
        System.out.println("-------------------------------------------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + String.format("%03d", wsScrColsDisp));
        System.out.println("  Lines: " + String.format("%03d", wsScrLinesDisp));
    }
}