import com.iscobol.rts.*;
import com.iscobol.rts.ui.*;
import java.awt.event.*;

public class MouseExample {
    
    private static class WorkingStorage {
        int cursorLine = 0;
        int cursorCol = 0;
        String kbInput = " ";
        String crtKey1 = "  ";
        String crtKey2 = "  ";
        int mouseFlags = 0;
        char mouseClickedSw = 'N';
        char exitSw = 'N';
        int drawColor = 1;
    }
    
    private static final int COB_AUTO_MOUSE_HANDLING = 1;
    private static final int COB_ALLOW_LEFT_DOWN = 2;
    private static final int COB_ALLOW_LEFT_UP = 4;
    private static final int COB_ALLOW_MOUSE_MOVE = 8;
    private static final int COB_SCR_ESC = 2005;
    private static final int COB_SCR_LEFT_PRESSED = 2071;
    private static final int COB_SCR_LEFT_RELEASED = 2072;
    private static final int COB_COLOR_WHITE = 7;
    private static final int COB_COLOR_BLUE = 1;
    
    private WorkingStorage ws = new WorkingStorage();
    private ScreenIO screen;
    private int lastCrtStatus = 0;
    
    public static void main(String[] args) {
        new MouseExample().run();
    }
    
    public void run() {
        initializeEnvironment();
        mainProcedure();
    }
    
    private void initializeEnvironment() {
        System.setProperty("COB_SCREEN_EXCEPTIONS", "Y");
        System.setProperty("COB_SCREEN_ESC", "Y");
        System.setProperty("COB_EXIT_WAIT", "N");
        System.setProperty("COB_TIMEOUT_SCALE", "3");
        
        ws.mouseFlags = COB_AUTO_MOUSE_HANDLING + COB_ALLOW_LEFT_DOWN + 
                       COB_ALLOW_LEFT_UP + COB_ALLOW_MOUSE_MOVE;
        
        System.setProperty("COB_MOUSE_FLAGS", String.valueOf(ws.mouseFlags));
        
        screen = new ScreenIO();
        screen.initialize();
    }
    
    private void mainProcedure() {
        ws.drawColor = 1;
        
        displayAt("Current Draw color:", 19, 1, -1, -1, false);
        displayAt("Esc to exit. Number keys to change cursor draw color. Left mouse down to draw current color.",
                 20, 1, COB_COLOR_WHITE, COB_COLOR_BLUE, true);
        
        while (ws.exitSw != 'Y') {
            displayAt(" ", 19, 21, -1, ws.drawColor, false);
            
            ws.kbInput = acceptInput(50);
            
            if (!ws.kbInput.equals(" ")) {
                if (ws.kbInput.equalsIgnoreCase("Q")) {
                    System.exit(0);
                }
                
                if (Character.isDigit(ws.kbInput.charAt(0))) {
                    ws.drawColor = Character.getNumericValue(ws.kbInput.charAt(0));
                    if (ws.drawColor > 7) {
                        ws.drawColor = 7;
                    }
                }
            }
            
            switch (lastCrtStatus) {
                case COB_SCR_ESC:
                    ws.exitSw = 'Y';
                    break;
                    
                case COB_SCR_LEFT_PRESSED:
                    ws.mouseClickedSw = 'Y';
                    break;
                    
                case COB_SCR_LEFT_RELEASED:
                    ws.mouseClickedSw = 'N';
                    break;
            }
            
            if ((ws.cursorLine != 0 || ws.cursorCol != 0) &&
                ws.cursorLine < 19 &&
                ws.cursorCol <= 80 &&
                ws.mouseClickedSw == 'Y') {
                displayAt(" ", ws.cursorLine, ws.cursorCol, -1, ws.drawColor, false);
            }
        }
        
        System.exit(0);
    }
    
    private void displayAt(String text, int line, int col, int fgColor, int bgColor, boolean highlight) {
        screen.moveCursor(line, col);
        if (fgColor >= 0 && bgColor >= 0) {
            screen.setColors(fgColor, bgColor, highlight);
        } else if (bgColor >= 0) {
            screen.setBackgroundColor(bgColor);
        }
        screen.display(text);
        screen.resetColors();
    }
    
    private String acceptInput(int timeout) {
        InputEvent event = screen.acceptWithTimeout(timeout);
        
        if (event instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) event;
            lastCrtStatus = 0;
            
            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                lastCrtStatus = COB_SCR_ESC;
                return " ";
            }
            
            char c = ke.getKeyChar();
            if (c != KeyEvent.CHAR_UNDEFINED) {
                return String.valueOf(Character.toUpperCase(c));
            }
        } else if (event instanceof MouseEvent) {
            MouseEvent me = (MouseEvent) event;
            ws.cursorLine = screen.getMouseRow(me);
            ws.cursorCol = screen.getMouseCol(me);
            
            if (me.getID() == MouseEvent.MOUSE_PRESSED && me.getButton() == MouseEvent.BUTTON1) {
                lastCrtStatus = COB_SCR_LEFT_PRESSED;
            } else if (me.getID() == MouseEvent.MOUSE_RELEASED && me.getButton() == MouseEvent.BUTTON1) {
                lastCrtStatus = COB_SCR_LEFT_RELEASED;
            } else if (me.getID() == MouseEvent.MOUSE_MOVED || me.getID() == MouseEvent.MOUSE_DRAGGED) {
                lastCrtStatus = 0;
            }
        }
        
        return " ";
    }
    
    private class ScreenIO {
        private int currentRow = 1;
        private int currentCol = 1;
        
        public void initialize() {
            System.out.print("\033[2J");
            System.out.print("\033[?1003h");
            System.out.flush();
        }
        
        public void moveCursor(int row, int col) {
            currentRow = row;
            currentCol = col;
            System.out.print(String.format("\033[%d;%dH", row, col));
            System.out.flush();
        }
        
        public void setColors(int fg, int bg, boolean highlight) {
            int fgCode = 30 + fg;
            int bgCode = 40 + bg;
            if (highlight) {
                System.out.print(String.format("\033[1;%d;%dm", fgCode, bgCode));
            } else {
                System.out.print(String.format("\033[%d;%dm", fgCode, bgCode));
            }
            System.out.flush();
        }
        
        public void setBackgroundColor(int bg) {
            int bgCode = 40 + bg;
            System.out.print(String.format("\033[%dm", bgCode));
            System.out.flush();
        }
        
        public void resetColors() {
            System.out.print("\033[0m");
            System.out.flush();
        }
        
        public void display(String text) {
            System.out.print(text);
            System.out.flush();
        }
        
        public InputEvent acceptWithTimeout(int timeout) {
            try {
                Thread.sleep(timeout);
            } catch