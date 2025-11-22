import java.io.*;
import java.util.*;

public class MouseExample {
    private static final int COB_AUTO_MOUSE_HANDLING = 1;
    private static final int COB_ALLOW_LEFT_DOWN = 2;
    private static final int COB_ALLOW_LEFT_UP = 4;
    private static final int COB_ALLOW_MOUSE_MOVE = 8;
    private static final int COB_SCR_ESC = 27;
    private static final int COB_SCR_LEFT_PRESSED = 1;
    private static final int COB_SCR_LEFT_RELEASED = 2;

    private static class CursorPosition {
        int line;
        int col;

        CursorPosition() {
            this.line = 0;
            this.col = 0;
        }

        boolean isZeros() {
            return line == 0 && col == 0;
        }
    }

    private static class CrtStatus {
        int key1;
        int key2;

        CrtStatus() {
            this.key1 = 0;
            this.key2 = 0;
        }
    }

    private CursorPosition wsCursorPosition;
    private char wsKbInput;
    private CrtStatus wsCrtStatus;
    private int wsMouseFlags;
    private boolean wsMouseClicked;
    private boolean wsExit;
    private int wsDrawColor;

    public MouseExample() {
        wsCursorPosition = new CursorPosition();
        wsKbInput = ' ';
        wsCrtStatus = new CrtStatus();
        wsMouseFlags = 0;
        wsMouseClicked = false;
        wsExit = false;
        wsDrawColor = 1;
    }

    public void run() {
        wsDrawColor = 1;

        System.out.print("\033[19;01H" + "Current Draw color:");
        System.out.print("\033[20;01H" + "\033[1;37;44m" + 
            "Esc to exit. Number keys to change cursor draw color. Left mouse down to draw current color." + 
            "\033[0m");

        while (!wsExit) {
            System.out.print("\033[19;21H" + getColorCode(wsDrawColor) + " " + "\033[0m");

            try {
                if (System.in.available() > 0) {
                    int input = System.in.read();
                    wsKbInput = (char) input;
                    wsKbInput = Character.toUpperCase(wsKbInput);

                    if (wsKbInput != ' ') {
                        if (wsKbInput == 'Q') {
                            System.exit(0);
                        }

                        if (Character.isDigit(wsKbInput)) {
                            wsDrawColor = Character.getNumericValue(wsKbInput);
                            if (wsDrawColor > 7) {
                                wsDrawColor = 7;
                            }
                        }
                    }
                }

                if (wsCrtStatus.key1 == COB_SCR_ESC) {
                    wsExit = true;
                }

                if (wsCrtStatus.key1 == COB_SCR_LEFT_PRESSED) {
                    wsMouseClicked = true;
                }

                if (wsCrtStatus.key1 == COB_SCR_LEFT_RELEASED) {
                    wsMouseClicked = false;
                }

                if (!wsCursorPosition.isZeros() &&
                    wsCursorPosition.line < 19 &&
                    wsCursorPosition.col <= 80 &&
                    wsMouseClicked) {
                    System.out.print(String.format("\033[%d;%dH", wsCursorPosition.line, wsCursorPosition.col) +
                        getColorCode(wsDrawColor) + " " + "\033[0m");
                }

                Thread.sleep(50);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    private String getColorCode(int color) {
        return switch (color) {
            case 0 -> "\033[40m";
            case 1 -> "\033[41m";
            case 2 -> "\033[42m";
            case 3 -> "\033[43m";
            case 4 -> "\033[44m";
            case 5 -> "\033[45m";
            case 6 -> "\033[46m";
            case 7 -> "\033[47m";
            default -> "\033[40m";
        };
    }

    public static void main(String[] args) {
        MouseExample program = new MouseExample();
        program.run();
    }
}