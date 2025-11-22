import java.util.Random;

public class maze {
    private static final int WIDTH = 39;
    private static final int HEIGHT = 23;
    private static int[][] mazeArray = new int[WIDTH + 1][HEIGHT + 1];
    private static Random random = new Random();
    private static int x, y, lx, ly;
    private static int x1, y1, x2, y2;
    private static int dx, dy;
    private static int dir, cnt;

    public static void main(String[] args) {
        initializeMaze();
        generateMaze();
        showMaze();
    }

    private static void initializeMaze() {
        for (y = 1; y <= HEIGHT; y++) {
            for (x = 1; x <= WIDTH; x++) {
                mazeArray[x][y] = 1;
            }
        }
    }

    private static void generateMaze() {
        mazeArray[2][2] = 0;
        for (y = 2; y < HEIGHT; y += 2) {
            for (x = 2; x < WIDTH; x += 2) {
                lx = x;
                ly = y;
                carveMaze();
            }
        }
        mazeArray[2][1] = 0;
        mazeArray[WIDTH - 1][HEIGHT] = 0;
    }

    private static void showMaze() {
        for (y = 1; y <= HEIGHT; y++) {
            for (x = 1; x <= WIDTH; x++) {
                if (mazeArray[x][y] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print("[]");
                }
            }
            System.out.println();
        }
    }

    private static void carveMaze() {
        dir = (int) (random.nextDouble() * 4.0);
        cnt = 0;
        while (cnt <= 3) {
            dx = 0;
            dy = 0;
            switch (dir) {
                case 0:
                    dx = 1;
                    break;
                case 1:
                    dy = 1;
                    break;
                case 2:
                    dx = -1;
                    break;
                case 3:
                    dy = -1;
                    break;
            }
            x1 = lx + dx;
            y1 = ly + dy;
            x2 = x1 + dx;
            y2 = y1 + dy;
            if (x2 > 1 && x2 < WIDTH && y2 > 1 && y2 < HEIGHT &&
                mazeArray[x1][y1] == 1 && mazeArray[x2][y2] == 1) {
                mazeArray[x1][y1] = 0;
                mazeArray[x2][y2] = 0;
                lx = x2;
                ly = y2;
                dir = (int) (random.nextDouble() * 4.0);
                cnt = 0;
            } else {
                cnt++;
                dir++;
                if (dir == 4) {
                    dir = 0;
                }
            }
        }
    }
}