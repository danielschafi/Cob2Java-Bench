import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20201222 {
    private int wx = -1;
    private int wy = 10;
    private int w0 = 0;
    private int x = 0;
    private int y = 0;
    private int n = 0;
    private int arg = 0;

    public static void main(String[] args) {
        AOC20201222 program = new AOC20201222();
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
        if (line.length() > 0) {
            char inputAction = line.charAt(0);
            String argString = line.substring(1).trim();
            arg = Integer.parseInt(argString);
            navigate(inputAction);
        }
    }

    private void navigate(char inputAction) {
        if (inputAction == 'F') {
            x = x + wx * arg;
            y = y + wy * arg;
            return;
        }

        n = arg / 90;

        if (inputAction == 'L') {
            for (int i = 0; i < n; i++) {
                w0 = wx;
                wx = -1 * wy;
                wy = w0;
            }
            return;
        }

        if (inputAction == 'R') {
            for (int i = 0; i < n; i++) {
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