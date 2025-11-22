import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202122Part1 {
    private static final int GRID_SIZE = 200;
    private static byte[][][] cubes = new byte[GRID_SIZE][GRID_SIZE][GRID_SIZE];
    private static long result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d22.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            tally();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        String[] parts = inputRecord.split(" ");
        if (parts.length < 2) return;
        
        String onFlag = parts[0];
        String coordinates = parts[1];
        
        String[] coords = coordinates.split(",");
        if (coords.length < 3) return;
        
        String xPart = coords[0].substring(2);
        String yPart = coords[1].substring(2);
        String zPart = coords[2].substring(2);
        
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
            
            byte value = onFlag.equals("on") ? (byte) 1 : (byte) 0;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    for (int k = z1; k <= z2; k++) {
                        cubes[i + 50][j + 50][k + 50] = value;
                    }
                }
            }
        }
    }

    private static void tally() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                for (int k = 0; k < GRID_SIZE; k++) {
                    result += cubes[i][j][k];
                }
            }
        }
    }
}