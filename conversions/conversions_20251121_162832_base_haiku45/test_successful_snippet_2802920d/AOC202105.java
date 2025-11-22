import java.io.*;
import java.util.*;

public class AOC202105 {
    private static final int MAP_SIZE = 1000;
    private int[][] wsMap = new int[MAP_SIZE][MAP_SIZE];
    private int wsResult = 0;
    private int fileStatus = 0;

    public static void main(String[] args) {
        AOC202105 program = new AOC202105();
        program.run();
    }

    private void run() {
        try {
            readInput();
            count();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d05.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" -> ");
        if (parts.length != 2) {
            return;
        }

        String[] start = parts[0].split(",");
        String[] end = parts[1].split(",");

        int x1 = Integer.parseInt(start[0].trim());
        int y1 = Integer.parseInt(start[1].trim());
        int x2 = Integer.parseInt(end[0].trim());
        int y2 = Integer.parseInt(end[1].trim());

        if (x1 == x2) {
            int k = Math.min(y1, y2) + 1;
            int l = Math.max(y1, y2) + 1;
            for (int i = k; i < l; i++) {
                wsMap[x1][i]++;
            }
        }

        if (y1 == y2) {
            int k = Math.min(x1, x2) + 1;
            int l = Math.max(x1, x2) + 1;
            for (int i = k; i < l; i++) {
                wsMap[i][y1]++;
            }
        }
    }

    private void count() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (wsMap[i][j] > 1) {
                    wsResult++;
                }
            }
        }
    }
}