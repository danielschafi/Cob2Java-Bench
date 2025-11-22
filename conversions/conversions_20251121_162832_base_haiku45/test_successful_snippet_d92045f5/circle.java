import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class circle {
    private static final int SEED_MOD = 32768;
    private static final int GRID_SIZE = 31;
    private static final int RADIUS_MIN = 10;
    private static final int RADIUS_MAX = 15;
    private static final int CENTER = 16;
    private static final int RANGE = 15;
    private static final int MAX_POINTS = 404;
    private static final int SELECT_COUNT = 100;

    private long seed;
    private int[][] dot;
    private List<int[]> xyTable;
    private int dotCount;
    private Random random;

    public circle() {
        dot = new int[GRID_SIZE + 1][GRID_SIZE + 1];
        xyTable = new ArrayList<>();
        dotCount = 0;
        random = new Random();
    }

    public static void main(String[] args) {
        circle program = new circle();
        program.computeSeed();
        program.findAllValidPoints();
        program.shufflePointPairs();
        program.select100Dots();
        program.printDots();
    }

    private void computeSeed() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = now.format(dateFormatter);
        long dateValue = Long.parseLong(dateStr);

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        long daysSinceEpoch = dateValue / 10000 * 365 + (dateValue % 10000) / 100 * 30 + (dateValue % 100);
        seed = (daysSinceEpoch * 86400L) + (hour * 3600L) + (minute * 60L) + second;
        seed = seed % SEED_MOD;
        random.setSeed(seed);
    }

    private void findAllValidPoints() {
        for (int x = -RANGE; x <= RANGE; x++) {
            for (int y = -RANGE; y <= RANGE; y++) {
                double distance = Math.sqrt(x * x + y * y);
                if (distance >= RADIUS_MIN && distance <= RADIUS_MAX) {
                    dot[x + CENTER][y + CENTER] = 1;
                    dotCount++;
                    xyTable.add(new int[]{x + CENTER, y + CENTER});
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }

    private void shufflePointPairs() {
        int dotCountSave = dotCount;
        for (int i = dotCount - 1; i >= 1; i--) {
            int randomIndex = (int) (random.nextDouble() * (i + 1));
            int[] temp = xyTable.get(randomIndex);
            xyTable.set(randomIndex, xyTable.get(i));
            xyTable.set(i, temp);
        }
    }

    private void select100Dots() {
        for (int i = 0; i < Math.min(SELECT_COUNT, xyTable.size()); i++) {
            int[] point = xyTable.get(i);
            int x = point[0];
            int y = point[1];
            dot[x][y] = 2;
        }
    }

    private void printDots() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("circle.txt"))) {
            for (int y = 1; y <= GRID_SIZE; y++) {
                StringBuilder line = new StringBuilder();
                for (int x = 1; x <= GRID_SIZE; x++) {
                    if (dot[x][y] == 2) {
                        line.append("o");
                    } else {
                        line.append(" ");
                    }
                }
                writer.println(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}