import java.io.*;
import java.util.*;

public class AOC202122_1 {
    private static final int ARRAY_SIZE = 200;
    private int[][][] cube = new int[ARRAY_SIZE][ARRAY_SIZE][ARRAY_SIZE];
    private long result = 0;

    public static void main(String[] args) {
        AOC202122_1 program = new AOC202122_1();
        program.run();
    }

    private void run() {
        try {
            readInput();
            tally();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d22.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private void processRecord(String record) {
        String[] parts = record.split(" ");
        String onFlag = parts[0];

        String xPart = parts[1].substring(2);
        String yPart = parts[2].substring(2);
        String zPart = parts[3].substring(2);

        String[] xRange = xPart.split("\\.\\.");
        String[] yRange = yPart.split("\\.\\.");
        String[] zRange = zPart.split("\\.\\.");

        int x1 = Integer.parseInt(xRange[0]);
        int x2 = Integer.parseInt(xRange[1]);
        int y1 = Integer.parseInt(yRange[0]);
        int y2 = Integer.parseInt(yRange[1]);
        int z1 = Integer.parseInt(zRange[0]);
        int z2 = Integer.parseInt(zRange[1]);

        if (x1 >= -50 && x1 <= 50 &&
            x2 >= -50 && x2 <= 50 &&
            y1 >= -50 && y1 <= 50 &&
            y2 >= -50 && y2 <= 50 &&
            z1 >= -50 && z1 <= 50 &&
            z2 >= -50 && z2 <= 50) {

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    for (int k = z1; k <= z2; k++) {
                        if (onFlag.equals("on")) {
                            cube[i + 51][j + 51][k + 51] = 1;
                        } else {
                            cube[i + 51][j + 51][k + 51] = 0;
                        }
                    }
                }
            }
        }
    }

    private void tally() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            for (int j = 0; j < ARRAY_SIZE; j++) {
                for (int k = 0; k < ARRAY_SIZE; k++) {
                    result += cube[i][j][k];
                }
            }
        }
    }
}