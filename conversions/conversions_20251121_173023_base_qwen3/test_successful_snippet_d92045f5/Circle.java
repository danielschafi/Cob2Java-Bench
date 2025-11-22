import java.io.*;
import java.util.*;

public class Circle {
    private static final int GRID_SIZE = 31;
    private static final int MAX_POINTS = 404;
    private static final int CENTER = 16;
    private static final int MIN_RADIUS = 10;
    private static final int MAX_RADIUS = 15;
    
    private static int seed;
    private static int[] xPoints = new int[MAX_POINTS];
    private static int[] yPoints = new int[MAX_POINTS];
    private static int dotCount = 0;
    private static int[][] plotGrid = new int[GRID_SIZE][GRID_SIZE];
    private static char[] plotWork = new char[GRID_SIZE];
    
    public static void main(String[] args) {
        computeSeed();
        findAllValidPoints();
        shufflePointPairs();
        select100Dots();
        printDots();
    }
    
    private static void computeSeed() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        
        int dateValue = (year * 10000) + (month * 100) + day;
        int secondsSinceEpoch = (int)(cal.getTimeInMillis() / 1000L);
        
        seed = ((secondsSinceEpoch * 86400) + (hour * 3600) + (minute * 60) + second) % 32768;
    }
    
    private static void findAllValidPoints() {
        for (int x = -15; x <= 15; x++) {
            for (int y = -15; y <= 15; y++) {
                double distance = Math.sqrt(x * x + y * y);
                if (distance >= MIN_RADIUS && distance <= MAX_RADIUS) {
                    int gridX = x + CENTER;
                    int gridY = y + CENTER;
                    plotGrid[gridX][gridY] = 1;
                    xPoints[dotCount] = gridX;
                    yPoints[dotCount] = gridY;
                    dotCount++;
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }
    
    private static void shufflePointPairs() {
        Random rand = new Random(seed);
        for (int i = dotCount; i > 1; i--) {
            int j = rand.nextInt(i);
            // Swap elements
            int tempX = xPoints[j];
            int tempY = yPoints[j];
            xPoints[j] = xPoints[i - 1];
            yPoints[j] = yPoints[i - 1];
            xPoints[i - 1] = tempX;
            yPoints[i - 1] = tempY;
        }
    }
    
    private static void select100Dots() {
        for (int i = 0; i < 100; i++) {
            int x = xPoints[i];
            int y = yPoints[i];
            plotGrid[x][y] = 2;
        }
    }
    
    private static void printDots() {
        try {
            PrintWriter writer = new PrintWriter("circle.txt");
            
            for (int y = 0; y < GRID_SIZE; y++) {
                Arrays.fill(plotWork, ' ');
                for (int x = 0; x < GRID_SIZE; x++) {
                    if (plotGrid[x][y] == 2) {
                        plotWork[x] = 'o';
                    }
                }
                writer.println(new String(plotWork));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}