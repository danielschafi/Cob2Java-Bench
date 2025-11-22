import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2020_12_1 {
    private char currDir = 'E';
    private char dir = 'E';
    private int dx = 1;
    private int dy = 0;
    private int x = 0;
    private int y = 0;
    private int n = 0;
    private int arg = 0;

    public static void main(String[] args) {
        AOC_2020_12_1 program = new AOC_2020_12_1();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private void processRecord(String line) {
        if (line.isEmpty()) {
            return;
        }
        char inputAction = line.charAt(0);
        String inputArgStr = line.substring(1).trim();
        arg = Integer.parseInt(inputArgStr);
        
        computeDirection(inputAction);
        computeDeltas();
        navigate(inputAction);
    }

    private void computeDirection(char inputAction) {
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

    private void rotateRight() {
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

    private void rotateLeft() {
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

    private void computeDeltas() {
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

    private void navigate(char inputAction) {
        if (inputAction == 'L' || inputAction == 'R') {
            return;
        }
        x = x + dx * arg;
        y = y + dy * arg;
    }
}