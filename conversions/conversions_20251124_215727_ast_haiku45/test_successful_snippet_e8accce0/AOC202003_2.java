```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202003_2 {
    private static final int MAP_WIDTH = 31;
    private static final int MAX_ARRAY_SIZE = 500;
    
    private List<String> wsArray;
    private int wsArrLen;
    private long wsTreesProduct;
    private int fileStatus;
    
    public AOC202003_2() {
        wsArray = new ArrayList<>();
        wsArrLen = 0;
        wsTreesProduct = 1;
        fileStatus = 0;
    }
    
    public static void main(String[] args) {
        AOC202003_2 program = new AOC202003_2();
        program.run();
    }
    
    private void run() {
        readFile();
        processDeltas();
        System.out.println(wsTreesProduct);
    }
    
    private void readFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d3.input"));
            wsArray.addAll(lines);
            wsArrLen = lines.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processDeltas() {
        processDeltasPair(1, 1);
        processDeltasPair(3, 1);
        processDeltasPair(5, 1);
        processDeltasPair(7, 1);
        processDeltasPair(2, 1);
    }
    
    private void processDeltasPair(int deltaX, int deltaY) {
        int x = 1;
        int y = 1;
        int trees = 0;
        
        while (x < wsArrLen) {
            x += deltaX;
            y += deltaY;
            y = y % MAP_WIDTH;
            if (y == 0) {
                y = MAP_WIDTH;
            }
            
            if (x < wsArray.size()) {
                String line = wsArray.get(x);
                if (y <= line.length() && line.charAt(y - 1) == '#') {
                    trees++;
                }
            }
        }
        
        wsTreesProduct *= trees;
    }
}