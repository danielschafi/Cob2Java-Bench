import java.io.*;
import java.lang.reflect.Method;

public class ScreenSizeTest {
    private static int ws_scr_lines;
    private static int ws_scr_cols;
    private static String ws_scr_lines_disp;
    private static String ws_scr_cols_disp;

    public static void main(String[] args) {
        System.out.print("\033[2J"); // Clear screen
        
        System.out.println("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:");
        
        for (int i = 0; i < 3; i++) {
            // Simulate ACCEPT FROM LINES and ACCEPT FROM COLUMNS
            // In a real implementation, these would read from system calls
            ws_scr_lines = getTerminalLines();
            ws_scr_cols = getTerminalColumns();
            
            ws_scr_lines_disp = String.format("%03d", ws_scr_lines);
            ws_scr_cols_disp = String.format("%03d", ws_scr_cols);
            
            displayScreenSize();
        }
        
        System.out.print("\033[2J"); // Clear screen
        System.out.println("Using 'CBL_GET_SCR_SIZE' to get screen size:");
        
        for (int i = 0; i < 3; i++) {
            cbl_get_scr_size();
            
            ws_scr_lines_disp = String.format("%03d", ws_scr_lines);
            ws_scr_cols_disp = String.format("%03d", ws_scr_cols);
            
            displayScreenSize();
        }
        
        System.out.println("Done.");
    }
    
    private static void cbl_get_scr_size() {
        // This simulates the CBL_GET_SCR_SIZE function call
        // In a real implementation, this would be a native call or JNI
        ws_scr_lines = getTerminalLines();
        ws_scr_cols = getTerminalColumns();
    }
    
    private static void displayScreenSize() {
        System.out.println("--------------------------------------------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + ws_scr_cols_disp);
        System.out.println("  Lines: " + ws_scr_lines_disp);
        System.out.println("Resize and press enter to continue");
        try {
            System.in.read();
        } catch (IOException e) {
            // Handle exception
        }
    }
    
    private static int getTerminalLines() {
        // Try to get terminal size using system properties
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("cmd /c mode con");
            } else {
                process = Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty size"});
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = reader.readLine();
                if (line != null && line.contains(" ")) {
                    return Integer.parseInt(line.split(" ")[0]);
                }
            }
        } catch (Exception e) {
            // Fallback to default
        }
        return 24; // Default fallback
    }
    
    private static int getTerminalColumns() {
        // Try to get terminal size using system properties
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("cmd /c mode con");
            } else {
                process = Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty size"});
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = reader.readLine();
                if (line != null && line.contains(" ")) {
                    return Integer.parseInt(line.split(" ")[1]);
                }
            }
        } catch (Exception e) {
            // Fallback to default
        }
        return 80; // Default fallback
    }
}