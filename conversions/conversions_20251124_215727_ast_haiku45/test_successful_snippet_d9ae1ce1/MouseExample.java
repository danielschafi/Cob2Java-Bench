import java.util.Scanner;

public class MouseExample {
    private static int wsCursorLine = 0;
    private static int wsCursorCol = 0;
    private static String wsKbInput = " ";
    private static String wsCrtKey1 = "";
    private static String wsCrtKey2 = "";
    private static int wsMouseFlags = 0;
    private static boolean wsMouseClicked = false;
    private static boolean wsExit = false;
    private static int wsDrawColor = 0;
    
    private static final int COB_AUTO_MOUSE_HANDLING = 1;
    private static final int COB_ALLOW_LEFT_DOWN = 2;
    private static final int COB_ALLOW_LEFT_UP = 4;
    private static final int COB_ALLOW_MOUSE_MOVE = 8;
    private static final String COB_SCR_ESC = "ESC";
    private static final String COB_SCR_LEFT_PRESSED = "LEFT_PRESSED";
    private static final String COB_SCR_LEFT_RELEASED = "LEFT_RELEASED";
    
    public static void main(String[] args) {
        wsDrawColor = 1;
        
        System.out.println("Current Draw color:");
        System.out.println("Esc to exit. Number keys to change cursor draw color. Left mouse down to draw current color.");
        
        wsMouseFlags = COB_AUTO_MOUSE_HANDLING + COB_ALLOW_LEFT_DOWN + COB_ALLOW_LEFT_UP + COB_ALLOW_MOUSE_MOVE;
        
        Scanner scanner = new Scanner(System.in);
        
        while (!wsExit) {
            System.out.print(" ");
            
            if (scanner.hasNextLine()) {
                wsKbInput = scanner.nextLine().toUpperCase();
                
                if (!wsKbInput.isEmpty() && !wsKbInput.equals(" ")) {
                    if (wsKbInput.equals("Q")) {
                        System.exit(0);
                    }
                    
                    if (Character.isDigit(wsKbInput.charAt(0))) {
                        wsDrawColor = Character.getNumericValue(wsKbInput.charAt(0));
                        if (wsDrawColor > 7) {
                            wsDrawColor = 7;
                        }
                    }
                }
            }
            
            if (wsCursorLine != 0 && wsCursorCol != 0 && wsCursorLine < 19 && wsCursorCol <= 80 && wsMouseClicked) {
                System.out.print(" ");
            }
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        scanner.close();
        System.exit(0);
    }
}