import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020122 {
    private static int fileStatus = 0;
    private static int wx = -1;
    private static int wy = 10;
    private static int x = 0;
    private static int y = 0;
    private static int n = 0;
    private static int arg = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 2) continue;
                char action = line.charAt(0);
                arg = Integer.parseInt(line.substring(1));
                processRecord(action, arg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }

    private static void processRecord(char action, int arg) {
        switch (action) {
            case 'F':
                x += wx * arg;
                y += wy * arg;
                break;
            case 'L':
                for (int i = 0; i < arg / 90; i++) {
                    int temp = wx;
                    wx = -wy;
                    wy = temp;
                }
                break;
            case 'R':
                for (int i = 0; i < arg / 90; i++) {
                    int temp = wx;
                    wx = wy;
                    wy = -temp;
                }
                break;
            case 'N':
                wx -= arg;
                break;
            case 'W':
                wy -= arg;
                break;
            case 'S':
                wx += arg;
                break;
            case 'E':
                wy += arg;
                break;
        }
    }
}