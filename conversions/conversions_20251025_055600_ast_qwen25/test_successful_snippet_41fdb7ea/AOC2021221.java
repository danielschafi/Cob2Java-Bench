import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021221 {
    private static final int SIZE = 200;
    private static int[][][] cubesArr = new int[SIZE][SIZE][SIZE];
    private static int fileStatus = 0;
    private static int recLen;
    private static String onFlag;
    private static int x1, x2, y1, y2, z1, z2;
    private static int i, j, k;
    private static long result = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d22.input"))) {
            while (fileStatus == 0) {
                read(br);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tally();
        System.out.println(result);
    }

    private static void read(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) {
            fileStatus = 1;
        } else {
            processRecord(line);
        }
    }

    private static void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" x=|..|,y=|,z=");
        onFlag = parts[0];
        x1 = Integer.parseInt(parts[1]);
        x2 = Integer.parseInt(parts[2]);
        y1 = Integer.parseInt(parts[3]);
        y2 = Integer.parseInt(parts[4]);
        z1 = Integer.parseInt(parts[5]);
        z2 = Integer.parseInt(parts[6]);

        if (x1 >= -50 && x1 <= 50 && x2 >= -50 && x2 <= 50 &&
            y1 >= -50 && y1 <= 50 && y2 >= -50 && y2 <= 50 &&
            z1 >= -50 && z1 <= 50 && z2 >= -50 && z2 <= 50) {
            for (i = x1; i <= x2; i++) {
                for (j = y1; j <= y2; j++) {
                    for (k = z1; k <= z2; k++) {
                        if (onFlag.equals("on")) {
                            cubesArr[i + 50][j + 50][k + 50] = 1;
                        } else {
                            cubesArr[i + 50][j + 50][k + 50] = 0;
                        }
                    }
                }
            }
        }
    }

    private static void tally() {
        for (i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {
                for (k = 0; k < SIZE; k++) {
                    result += cubesArr[i][j][k];
                }
            }
        }
    }
}