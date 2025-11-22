import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202012_1 {
    private static char currDir = 'E';
    private static int dx = 1;
    private static int dy = 0;
    private static int x = 0;
    private static int y = 0;
    private static int n = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    processRecord(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void processRecord(String line) {
        char inputAction = line.charAt(0);
        int arg = Integer.parseInt(line.substring(1));

        computeDirection(inputAction, arg);
        computeDeltas();
        navigate(inputAction, arg);
    }

    private static void computeDirection(char inputAction, int arg) {
        char dir;
        if (inputAction == 'N' || inputAction == 'S' || inputAction == 'E' || inputAction == 'W') {
            dir = inputAction;
        } else if (inputAction == 'F') {
            dir = currDir;
        } else {
            int rotations = arg / 90;
            if (inputAction == 'R') {
                for (int i = 0; i < rotations; i++) {
                    rotateRight();
                }
            } else {
                for (int i = 0; i < rotations; i++) {
                    rotateLeft();
                }
            }
            dir = currDir;
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

    private static void navigate(char inputAction, int arg) {
        if (inputAction == 'L' || inputAction == 'R') {
            return;
        }
        x = x + dx * arg;
        y = y + dy * arg;
    }
}