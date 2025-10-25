import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020032 {
    private static final int MAP_WIDTH = 31;
    private static final int MAX_LINES = 500;
    private static final int INPUT_RECORD_LENGTH = 31;

    private int fileStatus = 0;
    private int arrLen = 323;
    private String[] wsArray = new String[MAX_LINES];
    private long wsTreesProduct = 1;

    private int trees = 0;
    private int x = 1;
    private int y = 1;
    private int deltaX = 1;
    private int deltaY = 1;

    public static void main(String[] args) {
        AOC2020032 program = new AOC2020032();
        program.main();
    }

    private void main() {
        readFile("d3.input");
        processDeltas();
        System.out.println(wsTreesProduct);
    }

    private void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null && x <= arrLen) {
                wsArray[x - 1] = line;
                x++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }
    }

    private void processDeltas() {
        processDeltasPair();
        deltaY = 3;
        processDeltasPair();
        deltaY = 5;
        processDeltasPair();
        deltaY = 7;
        processDeltasPair();
        deltaX = 2;
        deltaY = 1;
        processDeltasPair();
    }

    private void processDeltasPair() {
        x = 1;
        y = 1;
        trees = 0;
        loop();
        wsTreesProduct *= trees;
    }

    private void loop() {
        while (x < arrLen) {
            x += deltaX;
            y += deltaY;
            y = y % MAP_WIDTH;
            if (y == 0) {
                y = MAP_WIDTH;
            }
            if (wsArray[x - 1].charAt(y - 1) == '#') {
                trees++;
            }
        }
    }
}