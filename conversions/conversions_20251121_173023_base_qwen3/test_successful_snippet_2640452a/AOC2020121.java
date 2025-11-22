import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020121 {
    private static String inputFile = "d12.input";
    private static int x = 0;
    private static int y = 0;
    private static char currDir = 'E';
    private static int dx = 1;
    private static int dy = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void processRecord(String record) {
        char action = record.charAt(0);
        int arg = Integer.parseInt(record.substring(1));

        computeDirection(action, arg);
        computeDeltas();
        navigate(action, arg);
    }

    private static void computeDirection(char action, int arg) {
        if (action == 'N' || action == 'S' || action == 'E' || action == 'W') {
            currDir = action;
            return;
        }

        if (action == 'F') {
            currDir = currDir;
            return;
        }

        int n = arg / 90;
        if (action == 'R') {
            for (int i = 0; i < n; i++) {
                rotateRight();
            }
        } else {
            for (int i = 0; i < n; i++) {
                rotateLeft();
            }
        }
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
        switch (currDir) {
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

    private static void navigate(char action, int arg) {
        if (action == 'L' || action == 'R') {
            return;
        }

        x += dx * arg;
        y += dy * arg;
    }
}