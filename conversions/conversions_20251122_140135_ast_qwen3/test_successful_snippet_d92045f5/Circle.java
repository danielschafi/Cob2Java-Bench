import java.io.*;
import java.util.*;

public class Circle {
    private static long seed;
    private static int[] x = new int[1];
    private static int[] y = new int[1];
    private static int[] i = new int[1];
    private static int dotCount = 0;
    private static int dotCountSave = 0;
    private static int[][] tempPoints = new int[2][1];
    private static int[][] pointPair = new int[404][2];
    private static int[][] plotTable = new int[31][31];
    private static char[] plotWork = new char[31];
    
    public static void main(String[] args) {
        computeSeed();
        findAllValidPoints();
        shufflePointPairs();
        select100Dots();
        printDots();
    }
    
    private static void computeSeed() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        int yyyymmdd = year * 10000 + month * 100 + day;
        long dateValue = (long) yyyymmdd * 86400L;
        seed = dateValue + (hour * 3600L) + (minute * 60L) + second;
        seed = seed % 32768L;
    }
    
    private static void findAllValidPoints() {
        for (int xVal = -15; xVal <= 15; xVal++) {
            for (int yVal = -15; yVal <= 15; yVal++) {
                double distance = Math.sqrt(xVal * xVal + yVal * yVal);
                if (distance >= 10 && distance <= 15) {
                    plotTable[xVal + 16][yVal + 16] = 1;
                    dotCount++;
                    pointPair[dotCount - 1][0] = xVal + 16;
                    pointPair[dotCount - 1][1] = yVal + 16;
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }
    
    private static void shufflePointPairs() {
        dotCountSave = dotCount;
        Random random = new Random(seed);
        for (int j = dotCount; j >= 2; j--) {
            int index = random.nextInt(j) + 1;
            tempPoints[0][0] = pointPair[index - 1][0];
            tempPoints[1][0] = pointPair[index - 1][1];
            pointPair[index - 1][0] = pointPair[j - 1][0];
            pointPair[index - 1][1] = pointPair[j - 1][1];
            pointPair[j - 1][0] = tempPoints[0][0];
            pointPair[j - 1][1] = tempPoints[1][0];
        }
        dotCount = dotCountSave;
    }
    
    private static void select100Dots() {
        for (int k = 1; k <= 100; k++) {
            int xVal = pointPair[k - 1][0];
            int yVal = pointPair[k - 1][1];
            plotTable[xVal][yVal] = 2;
        }
    }
    
    private static void printDots() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("circle.txt"));
            for (int yIndex = 0; yIndex < 31; yIndex++) {
                Arrays.fill(plotWork, ' ');
                for (int xIndex = 0; xIndex < 31; xIndex++) {
                    if (plotTable[xIndex][yIndex] == 2) {
                        plotWork[xIndex] = 'o';
                    }
                }
                writer.write(new String(plotWork));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}