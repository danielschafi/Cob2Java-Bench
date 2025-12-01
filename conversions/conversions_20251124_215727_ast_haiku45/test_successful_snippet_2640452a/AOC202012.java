import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202012 {
    private static int fileStatus = 0;
    private static char currDir = 'E';
    private static char dir = 'E';
    private static int dx = 1;
    private static int dy = 0;
    private static int x = 0;
    private static int y = 0;
    private static int n = 0;
    private static int arg = 0;
    private static char inputAction;
    private static int inputArg;

    public static void main(String[] args) {
        try {
            mainProcedure();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mainProcedure() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("d12.input"));
        
        for (String line : lines) {
            if (line.length() > 0) {
                inputAction = line.charAt(0);
                inputArg = Integer.parseInt(line.substring(1));
                processRecord();
            }
        }
        
        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void processRecord() {
        arg = inputArg;
        computeDirection();
        computeDeltas();
        navigate();
    }

    private static void computeDirection() {
        if (inputAction == 'N' || inputAction == 'S' || 
            inputAction == 'E' || inputAction == 'W') {
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
}