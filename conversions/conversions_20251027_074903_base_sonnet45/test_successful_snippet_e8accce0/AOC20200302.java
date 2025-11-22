import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC20200302 {
    private static final int MAP_WIDTH = 31;
    private static List<String> wsArray = new ArrayList<>();
    private static long wsTreesProduct = 1;

    public static void main(String[] args) {
        readInputFile();
        processDeltas();
        System.out.println(wsTreesProduct);
    }

    private static void readInputFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("d3.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wsArray.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDeltas() {
        processDeltasPair(1, 1);
        processDeltasPair(1, 3);
        processDeltasPair(1, 5);
        processDeltasPair(1, 7);
        processDeltasPair(2, 1);
    }

    private static void processDeltasPair(int deltaX, int deltaY) {
        int x = 0;
        int y = 0;
        int trees = 0;
        int wsArrLen = wsArray.size();

        while (x < wsArrLen) {
            x += deltaX;
            y += deltaY;
            
            if (x >= wsArrLen) {
                break;
            }

            int adjustedY = y % MAP_WIDTH;
            if (adjustedY == 0) {
                adjustedY = MAP_WIDTH;
            }

            String row = wsArray.get(x);
            if (row.charAt(adjustedY - 1) == '#') {
                trees++;
            }
        }

        wsTreesProduct *= trees;
    }
}