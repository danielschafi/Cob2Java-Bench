import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

public class Circle {
    private static class PointPair {
        int xPoint;
        int yPoint;
    }

    private int seed;
    private int x;
    private int y;
    private int i;
    private int dotCount = 0;
    private int dotCountSave = 0;
    private int[] tempPoints = new int[2];
    private PointPair[] xyTable = new PointPair[404];
    private int[][] plotTable = new int[31][31];
    private Random random;

    public Circle() {
        for (int idx = 0; idx < 404; idx++) {
            xyTable[idx] = new PointPair();
        }
        for (int row = 0; row < 31; row++) {
            for (int col = 0; col < 31; col++) {
                plotTable[row][col] = 0;
            }
        }
    }

    public void run() {
        computeSeed();
        findAllValidPoints();
        shufflePointPairs();
        select100Dots();
        printDots();
    }

    private void findAllValidPoints() {
        for (x = -15; x <= 15; x++) {
            for (y = -15; y <= 15; y++) {
                double distance = Math.sqrt(x * x + y * y);
                if (distance >= 10 && distance <= 15) {
                    plotTable[y + 15][x + 15] = 1;
                    dotCount++;
                    xyTable[dotCount - 1].xPoint = x + 16;
                    xyTable[dotCount - 1].yPoint = y + 16;
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }

    private void shufflePointPairs() {
        dotCountSave = dotCount;
        random = new Random(seed);
        i = (int)(random.nextDouble() * dotCount);
        
        while (dotCount >= 2) {
            tempPoints[0] = xyTable[i].xPoint;
            tempPoints[1] = xyTable[i].yPoint;
            
            xyTable[i].xPoint = xyTable[dotCount - 1].xPoint;
            xyTable[i].yPoint = xyTable[dotCount - 1].yPoint;
            
            xyTable[dotCount - 1].xPoint = tempPoints[0];
            xyTable[dotCount - 1].yPoint = tempPoints[1];
            
            dotCount--;
            i = (int)(random.nextDouble() * dotCount);
        }
        dotCount = dotCountSave;
    }

    private void select100Dots() {
        for (i = 1; i <= 100; i++) {
            x = xyTable[i - 1].xPoint;
            y = xyTable[i - 1].yPoint;
            plotTable[y - 1][x - 1] = 2;
        }
    }

    private void printDots() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("circle.txt"))) {
            for (y = 1; y <= 31; y++) {
                StringBuilder line = new StringBuilder();
                for (x = 1; x <= 31; x++) {
                    if (plotTable[y - 1][x - 1] == 2) {
                        line.append("o ");
                    } else {
                        line.append("  ");
                    }
                }
                writer.println(line.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void computeSeed() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);
        int ss = cal.get(Calendar.SECOND);
        
        int yyyymmdd = year * 10000 + month * 100 + day;
        
        int daysSinceEpoch = (int)((cal.getTimeInMillis() / (1000 * 60 * 60 * 24)));
        seed = daysSinceEpoch * 86400;
        seed = seed + (hh * 3600) + (mm * 60) + ss;
        seed = seed % 32768;
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.run();
    }
}