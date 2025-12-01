import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202012_2 {
    private static long fileStatus = 0;
    private static long recLen = 0;
    private static long wx = -1;
    private static long wy = 10;
    private static long w0 = 0;
    private static long x = 0;
    private static long y = 0;
    private static long n = 0;
    private static long arg = 0;
    private static char inputAction;
    private static long inputArg;

    public static void main(String[] args) {
        main001();
    }

    private static void main001() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d12.input"));
            fileStatus = 0;
            for (String line : lines) {
                if (fileStatus == 1) break;
                read002(line);
            }
        } catch (IOException e) {
            fileStatus = 1;
        }
        
        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void read002(String line) {
        if (line.isEmpty()) {
            fileStatus = 1;
            return;
        }
        
        inputAction = line.charAt(0);
        inputArg = Long.parseLong(line.substring(1));
        
        processRecord003();
    }

    private static void processRecord003() {
        arg = inputArg;
        navigate004();
    }

    private static void navigate004() {
        if (inputAction == 'F') {
            x = x + wx * arg;
            y = y + wy * arg;
            return;
        }

        n = arg / 90;

        if (inputAction == 'L') {
            for (long i = 0; i < n; i++) {
                w0 = wx;
                wx = -1 * wy;
                wy = w0;
            }
            return;
        }

        if (inputAction == 'R') {
            for (long i = 0; i < n; i++) {
                w0 = wx;
                wx = wy;
                wy = -1 * w0;
            }
            return;
        }

        switch (inputAction) {
            case 'N':
                wx = wx - arg;
                break;
            case 'W':
                wy = wy - arg;
                break;
            case 'S':
                wx = wx + arg;
                break;
            case 'E':
                wy = wy + arg;
                break;
        }
    }
}