import java.io.*;
import java.nio.file.*;

public class AOC202012_2 {
    private static int wx = -1;
    private static int wy = 10;
    private static int w0 = 0;
    private static int x = 0;
    private static int y = 0;
    private static int n = 0;
    private static int arg = 0;

    public static void main(String[] args) {
        try {
            processFile("d12.input");
            n = Math.abs(x) + Math.abs(y);
            System.out.println(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    processRecord(line);
                }
            }
        }
    }

    private static void processRecord(String line) {
        char action = line.charAt(0);
        String argStr = line.substring(1);
        arg = Integer.parseInt(argStr);
        navigate(action);
    }

    private static void navigate(char action) {
        if (action == 'F') {
            x = x + wx * arg;
            y = y + wy * arg;
            return;
        }

        n = arg / 90;

        if (action == 'L') {
            for (int i = 0; i < n; i++) {
                w0 = wx;
                wx = -1 * wy;
                wy = w0;
            }
            return;
        }

        if (action == 'R') {
            for (int i = 0; i < n; i++) {
                w0 = wx;
                wx = wy;
                wy = -1 * w0;
            }
            return;
        }

        switch (action) {
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