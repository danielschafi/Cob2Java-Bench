import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021221 {
    private static final int SIZE = 200;
    private static final int OFFSET = 51;
    private static final int MIN_COORD = -50;
    private static final int MAX_COORD = 50;

    private static int[][][] cubes = new int[SIZE][SIZE][SIZE];
    private static long result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d22.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tally();
        System.out.println(result);
    }

    private static void processRecord(String record) {
        String[] parts = record.split("[ x=,z=]+");
        String onFlag = parts[0];
        int x1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y1 = Integer.parseInt(parts[3]);
        int y2 = Integer.parseInt(parts[4]);
        int z1 = Integer.parseInt(parts[5]);
        int z2 = Integer.parseInt(parts[6]);

        if (x1 >= MIN_COORD && x1 <= MAX_COORD &&
            x2 >= MIN_COORD && x2 <= MAX_COORD &&
            y1 >= MIN_COORD && y1 <= MAX_COORD &&
            y2 >= MIN_COORD && y2 <= MAX_COORD &&
            z1 >= MIN_COORD && z1 <= MAX_COORD &&
            z2 >= MIN_COORD && z2 <= MAX_COORD) {

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    for (int k = z1; k <= z2; k++) {
                        if ("on".equals(onFlag)) {
                            cubes[i + OFFSET][j + OFFSET][k + OFFSET] = 1;
                        } else {
                            cubes[i + OFFSET][j + OFFSET][k + OFFSET] = 0;
                        }
                    }
                }
            }
        }
    }

    private static void tally() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    result += cubes[i][j][k];
                }
            }
        }
    }
}