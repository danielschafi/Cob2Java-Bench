import java.util.Random;

public class Maze {
    private static final int WIDTH = 39;
    private static final int HEIGHT = 23;
    private static int[][] mazeArray = new int[WIDTH][HEIGHT];
    private static Random random;
    private static int lx;
    private static int ly;

    public static void main(String[] args) {
        initializeMaze();
        generateMaze();
        showMaze();
    }

    private static void initializeMaze() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                mazeArray[x][y] = 1;
            }
        }
    }

    private static void generateMaze() {
        long seed = System.currentTimeMillis();
        random = new Random(seed);
        
        mazeArray[1][1] = 0;
        
        for (int y = 1; y < HEIGHT; y += 2) {
            for (int x = 1; x < WIDTH; x += 2) {
                lx = x;
                ly = y;
                carveMaze();
            }
        }
        
        mazeArray[1][0] = 0;
        mazeArray[WIDTH - 2][HEIGHT - 1] = 0;
    }

    private static void showMaze() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
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
        int dir = (int)(random.nextDouble() * 4.0);
        int cnt = 0;
        
        while (cnt <= 3) {
            int dx = 0;
            int dy = 0;
            
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
            
            int x1 = lx + dx;
            int y1 = ly + dy;
            int x2 = x1 + dx;
            int y2 = y1 + dy;
            
            if (x2 > 0 && x2 < WIDTH - 1 &&
                y2 > 0 && y2 < HEIGHT - 1 &&
                mazeArray[x1][y1] == 1 &&
                mazeArray[x2][y2] == 1) {
                
                mazeArray[x1][y1] = 0;
                mazeArray[x2][y2] = 0;
                lx = x2;
                ly = y2;
                dir = (int)(random.nextDouble() * 4.0);
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