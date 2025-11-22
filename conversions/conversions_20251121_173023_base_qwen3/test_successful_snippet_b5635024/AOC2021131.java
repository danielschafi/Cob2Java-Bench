import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021131 {
    private static final int MAX_SIZE = 2000;
    private static int[][] dots = new int[MAX_SIZE][MAX_SIZE];
    private static int result = 0;
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            String line;
            boolean readingDots = true;
            
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    readingDots = false;
                    continue;
                }
                
                if (readingDots) {
                    if (line.charAt(0) == 'f') {
                        // Process fold instruction
                        if (line.charAt(11) == 'x') {
                            x = Integer.parseInt(line.substring(13, line.length()));
                            x++;
                            foldX();
                        } else {
                            y = Integer.parseInt(line.substring(13, line.length()));
                            y++;
                            foldY();
                        }
                        countDots();
                        System.out.println(result);
                        break;
                    } else {
                        // Process dot coordinate
                        String[] parts = line.split(",");
                        x = Integer.parseInt(parts[0]) + 1;
                        y = Integer.parseInt(parts[1]) + 1;
                        dots[x][y] = 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void foldX() {
        int i1 = x + 1;
        for (int j = 1; j <= y; j++) {
            dots[x][j] = 0;
            for (int i = i1; i <= 2 * x; i++) {
                if (dots[i][j] == 1) {
                    int k = 2 * x - i;
                    dots[k][j] = 1;
                    dots[i][j] = 0;
                }
            }
        }
    }

    private static void foldY() {
        int j1 = y + 1;
        for (int i = 1; i <= x; i++) {
            dots[i][y] = 0;
            for (int j = j1; j <= 2 * y; j++) {
                if (dots[i][j] == 1) {
                    int k = 2 * y - j;
                    dots[i][k] = 1;
                    dots[i][j] = 0;
                }
            }
        }
    }

    private static void countDots() {
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (dots[j][i] == 1) {
                    result++;
                }
            }
        }
    }
}