import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Circle {
    private long seed;
    private int x;
    private int y;
    private int i;
    private int dotCount;
    private int dotCountSave;
    private int[][] tempPoints = new int[1][2];
    private int[][] pointPair = new int[405][2];
    private char[][] plotTable = new char[31][31];
    private String curDateTime;
    private char[] plotWork = new char[31];

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.computeSeed();
        circle.findAllValidPoints();
        circle.shufflePointPairs();
        circle.select100Dots();
        circle.printDots();
    }

    private void computeSeed() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        curDateTime = dateFormat.format(new Date());
        seed = (Long.parseLong(curDateTime.substring(0, 8)) * 86400);
        seed += (Integer.parseInt(curDateTime.substring(8, 10)) * 3600);
        seed += (Integer.parseInt(curDateTime.substring(10, 12)) * 60);
        seed += Integer.parseInt(curDateTime.substring(12, 14));
        seed = seed % 32768;
    }

    private void findAllValidPoints() {
        dotCount = 0;
        for (x = -15; x <= 15; x++) {
            for (y = -15; y <= 15; y++) {
                double distance = Math.sqrt(x * x + y * y);
                if (distance >= 10 && distance <= 15) {
                    plotTable[x + 16][y + 16] = '1';
                    dotCount++;
                    pointPair[dotCount][0] = x + 16;
                    pointPair[dotCount][1] = y + 16;
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }

    private void shufflePointPairs() {
        dotCountSave = dotCount;
        Random random = new Random(seed);
        for (dotCount = dotCount; dotCount > 1; dotCount--) {
            i = random.nextInt(dotCount) + 1;
            tempPoints[0][0] = pointPair[i][0];
            tempPoints[0][1] = pointPair[i][1];
            pointPair[i][0] = pointPair[dotCount][0];
            pointPair[i][1] = pointPair[dotCount][1];
            pointPair[dotCount][0] = tempPoints[0][0];
            pointPair[dotCount][1] = tempPoints[0][1];
        }
        dotCount = dotCountSave;
    }

    private void select100Dots() {
        for (i = 1; i <= 100; i++) {
            x = pointPair[i][0];
            y = pointPair[i][1];
            plotTable[x][y] = '2';
        }
    }

    private void printDots() {
        try (FileWriter plotFile = new FileWriter("circle.txt")) {
            for (y = 1; y <= 31; y++) {
                for (x = 1; x <= 31; x++) {
                    plotWork[x - 1] = (plotTable[x][y] == '2') ? 'o' : ' ';
                }
                plotFile.write(plotWork);
                plotFile.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}