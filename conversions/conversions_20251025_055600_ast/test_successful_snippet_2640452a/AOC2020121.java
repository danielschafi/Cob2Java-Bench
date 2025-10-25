import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020121 {
    private static int fileStatus = 0;
    private static int recLen;
    private static char currDir = 'E';
    private static char dir = 'E';
    private static int dx = 1;
    private static int dy = 0;
    private static int x = 0;
    private static int y = 0;
    private static int n = 0;
    private static int arg = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                recLen = line.length();
                if (recLen > 0) {
                    processRecord(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void processRecord(String record) {
        inputAction = record.charAt(0);
        arg = Integer.parseInt(record.substring(1).trim());
        computeDirection();
        computeDeltas();
        navigate();
    }

    private static void computeDirection() {
        if (inputAction == 'N' || inputAction == 'S' || inputAction == 'E' || inputAction == 'W') {
            dir = inputAction;
            return;
        }
        if (inputAction == 'F') {
            dir = currDir;
            return;
        }
        n = arg / 90;
        if (inputAction == 'R') {
            for (int i = 0; i < n; i++) {
                rotateRight();
            }
        } else {
            for (int i = 0; i < n; i++) {
                rotateLeft();
            }
        }
        dir = currDir;
    }

    private static void rotateRight() {
        switch (currDir) {
            case 'N':
                currDir = 'E';
                break;
            case 'E':
                currDir = 'S';
                break;
            case 'S':
                currDir = 'W';
                break;
            case 'W':
                currDir = 'N';
                break;
        }
    }

    private static void rotateLeft() {
        switch (currDir) {
            case 'N':
                currDir = 'W';
                break;
            case 'W':
                currDir = 'S';
                break;
            case 'S':
                currDir = 'E';
                break;
            case 'E':
                currDir = 'N';
                break;
        }
    }

    private static void computeDeltas() {
        switch (dir) {
            case 'N':
                dx = -1;
                dy = 0;
                break;
            case 'W':
                dx = 0;
                dy = -1;
                break;
            case 'S':
                dx = 1;
                dy = 0;
                break;
            case 'E':
                dx = 0;
                dy = 1;
                break;
        }
    }

    private static void navigate() {
        if (inputAction == 'L' || inputAction == 'R') {
            return;
        }
        x = x + dx * arg;
        y = y + dy * arg;
    }

    private static char inputAction;
}