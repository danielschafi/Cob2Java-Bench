import java.util.Random;

public class Maze {
    private static final int WIDTH = 39;
    private static final int HEIGHT = 23;
    private static final int[][] mazeArray = new int[WIDTH][HEIGHT];
    private static int seed;
    private static int x;
    private static int y;
    private static int lx;
    private static int ly;
    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;
    private static int dx;
    private static int dy;
    private static int dir;
    private static int cnt;

    public static void main(String[] args) {
        initializeMaze();
        generateMaze();
        showMaze();
    }

    private static void initializeMaze() {
        for (y = 0; y < HEIGHT; y++) {
            for (x = 0; x < WIDTH; x++) {
                mazeArray[x][y] = 1;
            }
        }
    }

    private static void generateMaze() {
        seed = (int) (System.currentTimeMillis() % 10000000);
        Random random = new Random(seed);
        seed = random.nextInt();
        mazeArray[2][2] = 0;
        for (y = 2; y < HEIGHT; y += 2) {
            for (x = 2; x < WIDTH; x += 2) {
                lx = x;
                ly = y;
                carveMaze(random);
            }
        }
        mazeArray[2][1] = 0;
        mazeArray[WIDTH - 2][HEIGHT - 1] = 0;
    }

    private static void showMaze() {
        for (y = 0; y < HEIGHT; y++) {
            for (x = 0; x < WIDTH; x++) {
                if (mazeArray[x][y] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print("[]");
                }
            }
            System.out.println();
        }
    }

    private static void carveMaze(Random random) {
        dir = random.nextInt(4);
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
            if (x2 > 0 && x2 < WIDTH - 1 && y2 > 0 && y2 < HEIGHT - 1 && mazeArray[x1][y1] == 1 && mazeArray[x2][y2] == 1) {
                mazeArray[x1][y1] = 0;
                mazeArray[x2][y2] = 0;
                lx = x2;
                ly = y2;
                dir = random.nextInt(4);
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