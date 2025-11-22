import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MouseExample {
    private static int cursorLine = 0;
    private static int cursorCol = 0;
    private static char kbInput = ' ';
    private static String crtStatus = "";
    private static boolean mouseClicked = false;
    private static boolean exitFlag = false;
    private static int drawColor = 1;
    private static final Scanner scanner = new Scanner(System.in);

    // Simulated screen constants
    private static final int MAX_LINES = 24;
    private static final int MAX_COLS = 80;

    public static void main(String[] args) {
        // Set up environment variables
        System.setProperty("COB_SCREEN_EXCEPTIONS", "Y");
        System.setProperty("COB_SCREEN_ESC", "Y");
        System.setProperty("COB_EXIT_WAIT", "N");
        System.setProperty("COB_TIMEOUT_SCALE", "3");

        // Set mouse flags
        int mouseFlags = 16384 + 1 + 2 + 4; // COB-AUTO-MOUSE-HANDLING + COB-ALLOW-LEFT-DOWN + COB-ALLOW-LEFT-UP + COB-ALLOW-MOUSE-MOVE
        System.setProperty("COB_MOUSE_FLAGS", String.valueOf(mouseFlags));

        // Main procedure
        System.out.print("\033[2J\033[H"); // Clear screen
        
        System.out.println("Current Draw color:" + "\033[19;1H");
        System.out.println("Esc to exit. Number keys to change cursor draw color. Left mouse down to draw current color." +
                          "\033[20;1H");
        
        while (!exitFlag) {
            // Display current color indicator
            System.out.print("\033[19;21H");
            System.out.print(" ");
            System.out.print("\033[4" + drawColor + "m");
            System.out.print("\033[0m");
            
            // Accept keyboard input with timeout
            kbInput = ' ';
            try {
                // This is a simplified approach - actual timeout handling would require more complex implementation
                System.out.flush();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Process keyboard input
            if (kbInput != ' ') {
                if (kbInput == 'Q' || kbInput == 'q') {
                    return;
                }
                
                if (Character.isDigit(kbInput)) {
                    drawColor = Character.getNumericValue(kbInput);
                    if (drawColor > 7) {
                        drawColor = 7;
                    }
                }
            }
            
            // Process CRT status
            switch (crtStatus) {
                case "ESC":
                    exitFlag = true;
                    break;
                case "LEFT-PRESSED":
                    mouseClicked = true;
                    break;
                case "LEFT-RELEASED":
                    mouseClicked = false;
                    break;
            }
            
            // Handle mouse drawing
            if (cursorLine != 0 && cursorCol != 0 && 
                cursorLine < 19 && cursorCol <= 80 && 
                mouseClicked) {
                System.out.print("\033[" + cursorLine + ";" + cursorCol + "H");
                System.out.print(" ");
                System.out.print("\033[4" + drawColor + "m");
                System.out.print("\033[0m");
            }
        }
    }
}