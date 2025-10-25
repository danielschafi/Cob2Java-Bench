import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021221 {
    private static final int SIZE = 200;
    private static int[][][] cubes = new int[SIZE][SIZE][SIZE];
    private static int result = 0;

    public static void main(String[] args) {
        String inputFile = "d22.input";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
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
        String[] parts = record.split(" x=|\\.\\.|,y=|,z=");
        String onFlag = parts[0];
        int x1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y1 = Integer.parseInt(parts[3]);
        int y2 = Integer.parseInt(parts[4]);
        int z1 = Integer.parseInt(parts[5]);
        int z2 = Integer.parseInt(parts[6]);

        if (x1 >= -50 && x1 <= 50 && x2 >= -50 && x2 <= 50 &&
            y1 >= -50 && y1 <= 50 && y2 >= -50 && y2 <= 50 &&
            z1 >= -50 && z1 <= 50 && z2 >= -50 && z2 <= 50) {
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    for (int k = z1; k <= z2; k++) {
                        cubes[i + 51][j + 51][k + 51] = "on".equals(onFlag) ? 1 : 0;
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