import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020032 {
    private static final int WS_ARR_LEN = 323;
    private static final int MAP_WIDTH = 31;
    private static final String INPUTFILE = "d3.input";

    private char[][] wsArray = new char[WS_ARR_LEN][MAP_WIDTH];
    private long wsTreesProduct = 1;

    public static void main(String[] args) {
        AOC2020032 program = new AOC2020032();
        program.run();
    }

    private void run() {
        readFile();
        processDeltas();
        System.out.println(wsTreesProduct);
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTFILE))) {
            String line;
            int x = 0;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < MAP_WIDTH; i++) {
                    wsArray[x][i] = line.charAt(i);
                }
                x++;
            }
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
        int x = 0;
        int y = 0;
        int trees = 0;
        while (x < WS_ARR_LEN) {
            x += deltaX;
            y += deltaY;
            y = y % MAP_WIDTH;
            if (y == 0) {
                y = MAP_WIDTH;
            }
            if (x < WS_ARR_LEN && wsArray[x][y - 1] == '#') {
                trees++;
            }
        }
        wsTreesProduct *= trees;
    }
}