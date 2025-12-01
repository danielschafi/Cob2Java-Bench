import java.io.*;
import java.util.*;

public class AOC202122 {
    private static final int CUBE_SIZE = 200;
    private int[][][] cube = new int[CUBE_SIZE][CUBE_SIZE][CUBE_SIZE];
    private String onFlag;
    private int x1, x2, y1, y2, z1, z2;
    private long result = 0;

    public static void main(String[] args) {
        AOC202122 program = new AOC202122();
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

    private void processRecord(String line) {
        String[] parts = line.split(" ");
        onFlag = parts[0];
        
        String[] coords = parts[1].split(",");
        
        String[] xRange = coords[0].substring(2).split("\\.\\.");
        x1 = Integer.parseInt(xRange[0]);
        x2 = Integer.parseInt(xRange[1]);
        
        String[] yRange = coords[1].substring(2).split("\\.\\.");
        y1 = Integer.parseInt(yRange[0]);
        y2 = Integer.parseInt(yRange[1]);
        
        String[] zRange = coords[2].substring(2).split("\\.\\.");
        z1 = Integer.parseInt(zRange[0]);
        z2 = Integer.parseInt(zRange[1]);
        
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
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                for (int k = 0; k < CUBE_SIZE; k++) {
                    result += cube[i][j][k];
                }
            }
        }
    }
}