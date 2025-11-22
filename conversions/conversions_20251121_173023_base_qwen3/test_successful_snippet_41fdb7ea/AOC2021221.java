import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021221 {
    private static final int SIZE = 200;
    private static final int OFFSET = 51;
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
        String[] parts = record.split("[x=,y=z=]");
        String onFlag = parts[0].trim();
        int x1 = Integer.parseInt(parts[1].trim());
        int x2 = Integer.parseInt(parts[2].trim());
        int y1 = Integer.parseInt(parts[3].trim());
        int y2 = Integer.parseInt(parts[4].trim());
        int z1 = Integer.parseInt(parts[5].trim());
        int z2 = Integer.parseInt(parts[6].trim());

        if (x1 >= -50 && x1 <= 50 && x2 >= -50 && x2 <= 50 &&
            y1 >= -50 && y1 <= 50 && y2 >= -50 && y2 <= 50 &&
            z1 >= -50 && z1 <= 50 && z2 >= -50 && z2 <= 50) {

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    for (int k = z1; k <= z2; k++) {
                        if (onFlag.equals("on")) {
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