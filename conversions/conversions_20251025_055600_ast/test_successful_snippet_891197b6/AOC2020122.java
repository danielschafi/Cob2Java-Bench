import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020122 {
    public static void main(String[] args) {
        String inputFile = "d12.input";
        int fileStatus = 0;
        int recLen = 0;
        int wx = -1;
        int wy = 10;
        int w0 = 0;
        int x = 0;
        int y = 0;
        int n = 0;
        int arg = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    char inputAction = line.charAt(0);
                    arg = Integer.parseInt(line.substring(1));

                    if (inputAction == 'F') {
                        x = x + wx * arg;
                        y = y + wy * arg;
                    } else {
                        n = arg / 90;

                        if (inputAction == 'L') {
                            for (int i = 0; i < n; i++) {
                                w0 = wx;
                                wx = -1 * wy;
                                wy = w0;
                            }
                        } else if (inputAction == 'R') {
                            for (int i = 0; i < n; i++) {
                                w0 = wx;
                                wx = wy;
                                wy = -1 * w0;
                            }
                        } else {
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
                }
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }
}