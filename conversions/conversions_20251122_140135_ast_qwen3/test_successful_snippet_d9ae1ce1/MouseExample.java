import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MouseExample {
    private static final int COB_AUTO_MOUSE_HANDLING = 1;
    private static final int COB_ALLOW_LEFT_DOWN = 2;
    private static final int COB_ALLOW_LEFT_UP = 4;
    private static final int COB_ALLOW_MOUSE_MOVE = 8;
    
    private static final int COB_SCR_ESC = 1;
    private static final int COB_SCR_LEFT_PRESSED = 2;
    private static final int COB_SCR_LEFT_RELEASED = 4;
    
    private static final int COB_COLOR_WHITE = 7;
    private static final int COB_COLOR_BLUE = 1;
    
    private static int ws_cursor_line = 0;
    private static int ws_cursor_col = 0;
    private static char ws_kb_input = ' ';
    private static int ws_crt_key_1 = 0;
    private static int ws_crt_key_2 = 0;
    private static int ws_mouse_flags = 0;
    private static boolean ws_mouse_clicked = false;
    private static boolean ws_mouse_not_clicked = true;
    private static boolean ws_exit = false;
    private static int ws_draw_color = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public static void main(String[] args) {
        System.setProperty("COB_SCREEN_EXCEPTIONS", "Y");
        System.setProperty("COB_SCREEN_ESC", "Y");
        System.setProperty("COB_EXIT_WAIT", "N");
        System.setProperty("COB_TIMEOUT_SCALE", "3");
        
        ws_mouse_flags = COB_AUTO_MOUSE_HANDLING + COB_ALLOW_LEFT_DOWN + 
                        COB_ALLOW_LEFT_UP + COB_ALLOW_MOUSE_MOVE;
        
        System.setProperty("COB_MOUSE_FLAGS", String.valueOf(ws_mouse_flags));
        
        System.out.print("\033[2J\033[H"); // Clear screen
        
        System.out.println("Current Draw color:");
        System.out.println("Esc to exit. Number keys to change cursor draw color. Left mouse down to draw current color.");
        System.out.flush();
        
        // Start timeout thread
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (System.in.available() > 0) {
                    ws_kb_input = (char) System.in.read();
                }
            } catch (Exception e) {
                // Ignore
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
        
        while (!ws_exit) {
            // Display current color indicator
            System.out.print("\033[" + "19;21H");
            System.out.print("\033[4" + ws_draw_color + "m ");
            System.out.flush();
            
            // Check keyboard input
            if (ws_kb_input != ' ') {
                if (ws_kb_input == 'Q' || ws_kb_input == 'q') {
                    break;
                }
                
                if (Character.isDigit(ws_kb_input)) {
                    ws_draw_color = Character.getNumericValue(ws_kb_input);
                    if (ws_draw_color > 7) {
                        ws_draw_color = 7;
                    }
                }
                ws_kb_input = ' ';
            }
            
            // Simulate CRT status evaluation
            // In real implementation, this would check actual mouse events
            // For now, we'll simulate with keyboard input
            
            // Display current cursor position info
            System.out.print("\033[" + "19;21H");
            System.out.print("\033[4" + ws_draw_color + "m ");
            System.out.flush();
            
            // Simple delay to simulate loop behavior
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        scheduler.shutdown();
        System.out.println("\033[2J\033[H"); // Clear screen
        System.exit(0);
    }
}