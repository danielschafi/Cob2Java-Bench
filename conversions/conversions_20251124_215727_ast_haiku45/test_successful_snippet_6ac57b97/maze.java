import java.util.Random;

public class maze {
    private static final int WIDTH = 39;
    private static final int HEIGHT = 23;
    
    private int[][] mazeArray;
    private long seed;
    private int x;
    private int y;
    private int lx;
    private int ly;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int dx;
    private int dy;
    private int dir;
    private int cnt;
    private Random random;
    
    public maze() {
        mazeArray = new int[WIDTH][HEIGHT];
        random = new Random();
    }
    
    public static void main(String[] args) {
        maze m = new maze();
        m.initializeMaze();
        m.generateMaze();
        m.showMaze();
    }
    
    private void initializeMaze() {
        for (y = 1; y <= HEIGHT; y++) {
            for (x = 1; x <= WIDTH; x++) {
                mazeArray[x - 1][y - 1] = 1;
            }
        }
    }
    
    private void generateMaze() {
        seed = System.currentTimeMillis();
        random.setSeed(seed);
        mazeArray[1][1] = 0;
        
        for (y = 2; y < HEIGHT; y += 2) {
            for (x = 2; x < WIDTH; x += 2) {
                lx = x;
                ly = y;
                carveMaze();
            }
        }
        
        mazeArray[1][0] = 0;
        mazeArray[WIDTH - 2][HEIGHT - 1] = 0;
    }
    
    private void showMaze() {
        for (y = 1; y <= HEIGHT; y++) {
            for (x = 1; x <= WIDTH; x++) {
                if (mazeArray[x - 1][y - 1] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print("[]");
                }
            }
            System.out.println();
        }
    }
    
    private void carveMaze() {
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
                mazeArray[x1 - 1][y1 - 1] == 1 && mazeArray[x2 - 1][y2 - 1] == 1) {
                
                mazeArray[x1 - 1][y1 - 1] = 0;
                mazeArray[x2 - 1][y2 - 1] = 0;
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