import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020032 {
    private static final int MAX_LINES = 500;
    private static final int LINE_LENGTH = 31;
    
    private static String[] wsArray = new String[MAX_LINES];
    private static long wsTreesProduct = 1;
    
    private static int trees = 0;
    private static int x = 1;
    private static int y = 1;
    private static int deltaX = 1;
    private static int deltaY = 1;
    private static int mapWidth = 31;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d3.input"))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null && lineNumber < MAX_LINES) {
                wsArray[lineNumber] = line;
                lineNumber++;
            }
            
            processDeltas();
            System.out.println(wsTreesProduct);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processDeltas() {
        processDeltaPair();
        
        deltaY = 3;
        processDeltaPair();
        
        deltaY = 5;
        processDeltaPair();
        
        deltaY = 7;
        processDeltaPair();
        
        deltaX = 2;
        deltaY = 1;
        processDeltaPair();
    }
    
    private static void processDeltaPair() {
        x = 1;
        y = 1;
        trees = 0;
        
        while (x < MAX_LINES) {
            x += deltaX;
            y += deltaY;
            
            if (y == 0) {
                y = mapWidth;
            } else {
                y = ((y - 1) % mapWidth) + 1;
            }
            
            if (x < MAX_LINES && y > 0 && y <= LINE_LENGTH) {
                char cell = wsArray[x - 1].charAt(y - 1);
                if (cell == '#') {
                    trees++;
                }
            } else {
                break;
            }
        }
        
        wsTreesProduct *= trees;
    }
}