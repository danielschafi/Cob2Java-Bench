import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Circle {
    private long seed;
    private int x;
    private int y;
    private int i;
    private int dotCount = 0;
    private int dotCountSave = 0;
    private int[] tempPoints = new int[2];
    private List<int[]> pointPairs = new ArrayList<>();
    private int[][] dot = new int[31][31];
    private int yyyymmdd;
    private int hh;
    private int mm;
    private int ss;
    private char[] plotWork = new char[31];

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.computeSeed();
        circle.findAllValidPoints();
        circle.shufflePointPairs();
        circle.select100Dots();
        circle.printDots();
    }

    private void findAllValidPoints() {
        for (x = -15; x <= 15; x++) {
            for (y = -15; y <= 15; y++) {
                double distance = Math.sqrt(x * x + y * y);
                if (distance >= 10 && distance <= 15) {
                    dot[x + 16][y + 16] = 1;
                    dotCount++;
                    int[] pair = new int[2];
                    pair[0] = x + 16;
                    pair[1] = y + 16;
                    pointPairs.add(pair);
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }

    private void shufflePointPairs() {
        dotCountSave = dotCount;
        i = (int) (Math.random() * dotCount) + 1;
        for (dotCount = dotCount; dotCount >= 2; dotCount--) {
            tempPoints[0] = pointPairs.get(i - 1)[0];
            tempPoints[1] = pointPairs.get(i - 1)[1];
            pointPairs.get(i - 1)[0] = pointPairs.get(dotCount - 1)[0];
            pointPairs.get(i - 1)[1] = pointPairs.get(dotCount - 1)[1];
            pointPairs.get(dotCount - 1)[0] = tempPoints[0];
            pointPairs.get(dotCount - 1)[1] = tempPoints[1];
            i = (int) (Math.random() * dotCount) + 1;
        }
        dotCount = dotCountSave;
    }

    private void select100Dots() {
        for (i = 1; i <= 100; i++) {
            x = pointPairs.get(i - 1)[0];
            y = pointPairs.get(i - 1)[1];
            dot[x][y] = 2;
        }
    }

    private void printDots() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("circle.txt"))) {
            for (y = 1; y <= 31; y++) {
                Arrays.fill(plotWork, ' ');
                for (x = 1; x <= 31; x++) {
                    if (dot[x][y] == 2) {
                        plotWork[x - 1] = 'o';
                    }
                }
                writer.println(new String(plotWork).trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void computeSeed() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        
        String dateStr = now.format(dateFormatter);
        String timeStr = now.format(timeFormatter);
        
        yyyymmdd = Integer.parseInt(dateStr);
        hh = Integer.parseInt(timeStr.substring(0, 2));
        mm = Integer.parseInt(timeStr.substring(2, 4));
        ss = Integer.parseInt(timeStr.substring(4, 6));
        
        seed = (long) dateToInteger(yyyymmdd) * 86400;
        seed = seed + (hh * 3600L) + (mm * 60L) + ss;
        seed = seed % 32768;
    }

    private int dateToInteger(int yyyymmdd) {
        int year = yyyymmdd / 10000;
        int month = (yyyymmdd / 100) % 100;
        int day = yyyymmdd % 100;
        
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }
        
        int daysSinceEpoch = 0;
        for (int y = 1601; y < year; y++) {
            daysSinceEpoch += isLeapYear(y) ? 366 : 365;
        }
        
        for (int m = 1; m < month; m++) {
            daysSinceEpoch += daysInMonth[m];
        }
        
        daysSinceEpoch += day;
        
        return daysSinceEpoch;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}