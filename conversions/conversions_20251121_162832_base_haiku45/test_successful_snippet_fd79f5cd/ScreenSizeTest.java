import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ScreenSizeTest {
    
    private static byte wsScrlinesUnsigned;
    private static byte wsScrcols;
    private static String wsScrlinesDisp;
    private static String wsScrcols;
    
    public static void main(String[] args) {
        mainProcedure();
    }
    
    private static void mainProcedure() {
        clearScreen();
        
        System.out.println("Using 'ACCEPT ... FROM LINES' and 'ACCEPT ... FROM COLUMNS' to get screen size:");
        
        for (int i = 0; i < 3; i++) {
            wsScrlinesDisp = String.format("%3d", getTerminalLines());
            wsScrcols = String.format("%3d", getTerminalColumns());
            displayScreenSize();
        }
        
        clearScreen();
        System.out.println("Using 'CBL_GET_SCR_SIZE' to get screen size:");
        
        for (int i = 0; i < 3; i++) {
            int[] screenSize = getScreenSize();
            wsScrlinesUnsigned = (byte) screenSize[0];
            wsScrcols = (byte) screenSize[1];
            
            wsScrlinesDisp = String.format("%3d", wsScrlinesUnsigned & 0xFF);
            wsScrcols = String.format("%3d", wsScrcols & 0xFF);
            
            displayScreenSize();
        }
        
        System.out.println("Done.");
    }
    
    private static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
    
    private static int getTerminalLines() {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "tput lines");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            process.waitFor();
            return line != null ? Integer.parseInt(line.trim()) : 24;
        } catch (Exception e) {
            return 24;
        }
    }
    
    private static int getTerminalColumns() {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "tput cols");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            process.waitFor();
            return line != null ? Integer.parseInt(line.trim()) : 80;
        } catch (Exception e) {
            return 80;
        }
    }
    
    private static int[] getScreenSize() {
        int[] size = new int[2];
        size[0] = getTerminalLines();
        size[1] = getTerminalColumns();
        return size;
    }
    
    private static void displayScreenSize() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Current screen size: ");
        System.out.println("Columns: " + wsScrcols);
        System.out.println("  Lines: " + wsScrlinesDisp);
        System.out.println("Resize and press enter to continue");
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}