import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202003_2 {
    private static final String INPUT_FILE = "d3.input";
    private static final int MAP_WIDTH = 31;
    
    private List<String> wsArray;
    private long wsTreesProduct;
    
    public AOC202003_2() {
        wsArray = new ArrayList<>();
        wsTreesProduct = 1;
    }
    
    public static void main(String[] args) {
        AOC202003_2 program = new AOC202003_2();
        program.run();
    }
    
    private void run() {
        readInput();
        processDeltas();
        System.out.println(wsTreesProduct);
    }
    
    private void readInput() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
            wsArray.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processDeltas() {
        processDeltasPair(1, 1);
        processDeltasPair(3, 1);
        processDeltasPair(5, 1);
        processDeltasPair(7, 1);
        processDeltasPair(1, 2);
    }
    
    private void processDeltasPair(int deltaX, int deltaY) {
        int x = 1;
        int y = 1;
        long trees = 0;
        
        while (x < wsArray.size()) {
            x += deltaX;
            y += deltaY;
            
            y = y % MAP_WIDTH;
            if (y == 0) {
                y = MAP_WIDTH;
            }
            
            if (x < wsArray.size()) {
                String line = wsArray.get(x - 1);
                if (y - 1 < line.length() && line.charAt(y - 1) == '#') {
                    trees++;
                }
            }
        }
        
        wsTreesProduct *= trees;
    }
}