import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

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
    private String yyyymmdd;
    private String hh;
    private String mm;
    private String ss;
    private char[] plotWork = new char[31];

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.begin();
    }

    public void begin() {
        computeSeed();
        findAllValidPoints();
        shufflePointPairs();
        select100Dots();
        printDots();
    }

    public void findAllValidPoints() {
        for (x = -15; x <= 15; x++) {
            for (y = -15; y <= 15; y++) {
                if (Math.sqrt(x * x + y * y) >= 10 && Math.sqrt(x * x + y * y) <= 15) {
                    plotTable[x + 16][y + 16] = '1';
                    dotCount++;
                    pointPair[dotCount][0] = x + 16;
                    pointPair[dotCount][1] = y + 16;
                }
            }
        }
        System.out.println("Total points: " + dotCount);
    }

    public void shufflePointPairs() {
        dotCountSave = dotCount;
        Random random = new Random(seed);
        i = random.nextInt(dotCount) + 1;
        for (dotCount = dotCount; dotCount > 1; dotCount--) {
            tempPoints[0][0] = pointPair[i][0];
            tempPoints[0][1] = pointPair[i][1];
            pointPair[i][0] = pointPair[dotCount][0];
            pointPair[i][1] = pointPair[dotCount][1];
            pointPair[dotCount][0] = tempPoints[0][0];
            pointPair[dotCount][1] = tempPoints[0][1];
            i = random.nextInt(dotCount) + 1;
        }
        dotCount = dotCountSave;
    }

    public void select100Dots() {
        for (i = 1; i <= 100; i++) {
            x = pointPair[i][0];
            y = pointPair[i][1];
            plotTable[x][y] = '2';
        }
    }

    public void printDots() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("circle.txt"))) {
            for (y = 1; y <= 31; y++) {
                for (x = 1; x <= 31; x++) {
                    plotWork[x - 1] = plotTable[x][y] == '2' ? 'o' : ' ';
                }
                writer.write(plotWork);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void computeSeed() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(new Date());
        yyyymmdd = formattedDate.substring(0, 8);
        hh = formattedDate.substring(8, 10);
        mm = formattedDate.substring(10, 12);
        ss = formattedDate.substring(12, 14);
        seed = (Long.parseLong(yyyymmdd) * 86400) + (Integer.parseInt(hh) * 3600) + (Integer.parseInt(mm) * 60) + Integer.parseInt(ss);
        seed = seed % 32768;
    }
}