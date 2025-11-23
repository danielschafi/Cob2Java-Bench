import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020032 {
    private static final String INPUT_FILE = "d3.input";
    private static final int MAP_WIDTH = 31;
    private static final int MAP_HEIGHT = 323;

    private static String[] map = new String[MAP_HEIGHT];
    private static long treesProduct = 1;

    public static void main(String[] args) {
        readInput();
        processDeltas();
        System.out.println(treesProduct);
    }

    private static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < MAP_HEIGHT) {
                map[row++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDeltas() {
        // Right 1, down 1
        treesProduct *= countTrees(1, 1);
        
        // Right 3, down 1
        treesProduct *= countTrees(3, 1);
        
        // Right 5, down 1
        treesProduct *= countTrees(5, 1);
        
        // Right 7, down 1
        treesProduct *= countTrees(7, 1);
        
        // Right 1, down 2
        treesProduct *= countTrees(1, 2);
    }

    private static int countTrees(int deltaX, int deltaY) {
        int trees = 0;
        int x = 0;
        int y = 0;

        while (y < MAP_HEIGHT) {
            // Calculate the horizontal position with wrapping
            int wrappedX = x % MAP_WIDTH;
            
            // Check if there's a tree at current position
            if (map[y].charAt(wrappedX) == '#') {
                trees++;
            }
            
            // Move to next position
            x += deltaX;
            y += deltaY;
        }

        return trees;
    }
}