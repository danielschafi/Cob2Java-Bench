import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;

public class Fifteen {
    private int r;
    private int rEmpty;
    private int rTo;
    private int rFrom;
    
    private int c;
    private int cEmpty;
    private int cTo;
    private int cFrom;
    
    private int[][] displayTable = new int[4][4];
    
    private int tileNumber;
    private boolean[] tileFlags = new boolean[16];
    
    private String displayMove;
    private int tileId;
    
    private String rowSeparator = ".......................";
    private String columnSeparator = " . ";
    
    private int inversions;
    private int currentTile;
    
    private String winningDisplay = "01020304050607080910111213141500";
    
    private Random random;
    
    public static void main(String[] args) {
        Fifteen fifteen = new Fifteen();
        fifteen.startFifteen();
    }
    
    private void startFifteen() {
        System.out.println("start fifteen puzzle");
        System.out.println("    enter a two-digit tile number and press <enter> to move");
        System.out.println("    press <enter> only to exit");
        
        Scanner scanner = new Scanner(System.in);
        
        do {
            initializeTable();
        } while (inversions != 0);
        
        showTable();
        displayMove = scanner.nextLine().trim();
        
        while (!displayMove.isEmpty()) {
            moveTile();
            showTable();
            displayMove = scanner.nextLine().trim();
        }
        
        scanner.close();
    }
    
    private void initializeTable() {
        if (random == null) {
            LocalTime now = LocalTime.now();
            int seed = now.toSecondOfDay();
            random = new Random(seed);
        }
        
        tileFlags = new boolean[16];
        currentTile = 0;
        inversions = 0;
        
        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                do {
                    tileNumber = (int)(random.nextDouble() * 100) % 16;
                } while (tileFlags[tileNumber]);
                
                tileFlags[tileNumber] = true;
                
                if (tileNumber > 0 && tileNumber < currentTile) {
                    inversions++;
                }
                
                displayTable[r][c] = tileNumber;
                currentTile = tileNumber;
            }
        }
        
        inversions = inversions % 2;
    }
    
    private void showTable() {
        StringBuilder tableDisplay = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tableDisplay.append(String.format("%02d", displayTable[i][j]));
            }
        }
        
        if (tableDisplay.toString().equals(winningDisplay)) {
            System.out.println("winning");
        }
        
        System.out.println(" " + rowSeparator);
        
        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                System.out.print(columnSeparator);
                if (displayTable[r][c] == 0) {
                    System.out.print("  ");
                    rEmpty = r;
                    cEmpty = c;
                } else {
                    System.out.printf("%02d", displayTable[r][c]);
                }
            }
            System.out.println(columnSeparator);
        }
        
        System.out.println(" " + rowSeparator);
    }
    
    private void moveTile() {
        try {
            tileId = Integer.parseInt(displayMove);
        } catch (NumberFormatException e) {
            System.out.println("invalid tile number");
            return;
        }
        
        if (tileId < 1 || tileId > 15) {
            System.out.println("invalid tile number");
            return;
        }
        
        boolean found = false;
        for (r = 0; r < 4 && !found; r++) {
            for (c = 0; c < 4 && !found; c++) {
                if (displayTable[r][c] == tileId) {
                    found = true;
                }
            }
        }
        r--;
        c--;
        
        if (r == rEmpty) {
            if (cEmpty < c) {
                for (cTo = cEmpty; cTo <= c; cTo++) {
                    cFrom = cTo + 1;
                    if (cFrom < 4) {
                        displayTable[rEmpty][cTo] = displayTable[rEmpty][cFrom];
                    }
                }
            } else {
                for (cTo = cEmpty; cTo >= c; cTo--) {
                    cFrom = cTo - 1;
                    if (cFrom >= 0) {
                        displayTable[rEmpty][cTo] = displayTable[rEmpty][cFrom];
                    }
                }
            }
            displayTable[r][c] = 0;
        } else if (c == cEmpty) {
            if (rEmpty < r) {
                for (rTo = rEmpty; rTo <= r; rTo++) {
                    rFrom = rTo + 1;
                    if (rFrom < 4) {
                        displayTable[rTo][cEmpty] = displayTable[rFrom][cEmpty];
                    }
                }
            } else {
                for (rTo = rEmpty; rTo >= r; rTo--) {
                    rFrom = rTo - 1;
                    if (rFrom >= 0) {
                        displayTable[rTo][cEmpty] = displayTable[rFrom][cEmpty];
                    }
                }
            }
            displayTable[r][c] = 0;
        } else {
            System.out.println("invalid move");
        }
    }
}