```java
import java.util.Scanner;
import java.util.Random;

public class fifteen {
    private int r;
    private int rEmpty;
    private int rTo;
    private int rFrom;
    
    private int c;
    private int cEmpty;
    private int cTo;
    private int cFrom;
    
    private int[][] displayTable = new int[5][5];
    private String tileFlags = "                ";
    
    private String displayMove = "";
    private int tileId;
    
    private String rowSeparator = ".......................";
    private String columnSeparator = " . ";
    
    private int inversions;
    private int currentTile;
    
    private String winningDisplay = "01020304050607080910111213141500";
    
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    
    public static void main(String[] args) {
        fifteen game = new fifteen();
        game.startFifteen();
    }
    
    private void startFifteen() {
        System.out.println("start fifteen puzzle");
        System.out.println("    enter a two-digit tile number and press <enter> to move");
        System.out.println("    press <enter> only to exit");
        
        do {
            initializeTable();
        } while (inversions != 0);
        
        showTable();
        displayMove = scanner.nextLine();
        
        while (!displayMove.isEmpty()) {
            moveTile();
            showTable();
            displayMove = scanner.nextLine();
        }
        
        System.exit(0);
    }
    
    private void initializeTable() {
        long seed = System.currentTimeMillis() / 1000;
        random.setSeed(seed);
        
        tileFlags = "                ";
        currentTile = 0;
        inversions = 0;
        
        for (r = 1; r <= 4; r++) {
            for (c = 1; c <= 4; c++) {
                int tileNumber;
                do {
                    tileNumber = (int)(random.nextDouble() * 100);
                    tileNumber = tileNumber % 16;
                } while (tileFlags.charAt(tileNumber) != ' ');
                
                StringBuilder sb = new StringBuilder(tileFlags);
                sb.setCharAt(tileNumber, 'x');
                tileFlags = sb.toString();
                
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
        StringBuilder tableStr = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (displayTable[i][j] < 10) {
                    tableStr.append("0");
                }
                tableStr.append(displayTable[i][j]);
            }
        }
        
        if (tableStr.toString().equals(winningDisplay)) {
            System.out.println("winning");
        }
        
        System.out.println(" " + rowSeparator);
        
        for (r = 1; r <= 4; r++) {
            for (c = 1; c <= 4; c++) {
                System.out.print(columnSeparator);
                if (displayTable[r][c] == 0) {
                    System.out.print("  ");
                    rEmpty = r;
                    cEmpty = c;
                } else {
                    if (displayTable[r][c] < 10) {
                        System.out.print("0");
                    }
                    System.out.print(displayTable[r][c]);
                }
            }
            System.out.println(columnSeparator);
        }
        
        System.out.println(" " + rowSeparator);
    }
    
    private void moveTile() {
        try {
            tileId = Integer.parseInt(displayMove.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid tile number");
            return;
        }
        
        if (!(tileId >= 1 && tileId <= 15)) {
            System.out.println("invalid tile number");
            return;
        }
        
        boolean found = false;
        for (r = 1; r <= 4 && !found; r++) {
            for (c = 1; c <= 4 && !found; c++) {
                if (displayTable[r][c] == tileId) {
                    found = true;
                }
            }
        }
        
        if (r == rEmpty) {
            if (cEmpty < c) {
                for (cTo = cEmpty; cTo < c; cTo++) {
                    cFrom = cTo + 1;
                    displayTable[rEmpty][cTo] = displayTable[rEmpty][cFrom];
                }
            } else {
                for (cTo = cEmpty; cTo > c; cTo--) {
                    cFrom = cTo - 1;
                    displayTable[rEmpty][cTo] = displayTable[rEmpty][cFrom];
                }
            }
            displayTable[r][c] = 0;
        } else if (c == cEmpty) {
            if (rEmpty < r) {
                for (rTo = rEmpty; rTo < r; rTo++) {
                    rFrom = rTo + 1;
                    displayTable[rTo][cEmpty] = displayTable[rFrom][cEmpty];
                }
            } else {
                for (rTo = rEmpty; rTo > r; rTo--) {
                    rFrom = rTo - 1;
                    displayTable[rTo][cEmpty] = displayTable[rFrom][cEmpty];
                }
            }
            displayTable[r][c] = 0;
        } else {
            System.out.println("invalid move");
        }
    }
}