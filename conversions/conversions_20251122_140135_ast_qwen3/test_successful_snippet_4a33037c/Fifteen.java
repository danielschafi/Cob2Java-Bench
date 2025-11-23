import java.util.*;

public class Fifteen {
    private static int r, rEmpty, rTo, rFrom;
    private static int c, cEmpty, cTo, cFrom;
    private static int[][] displayTable = new int[4][4];
    private static int tileNumber;
    private static char[] tileFlags = new char[16];
    private static String displayMove;
    private static int tileId;
    private static String rowSeparator = ".....................";
    private static String columnSeparator = " . ";
    private static int inversions;
    private static int currentTile;
    private static String winningDisplay = "01020304050607080910111213141500";

    public static void main(String[] args) {
        System.out.println("start fifteen puzzle");
        System.out.println("    enter a two-digit tile number and press <enter> to move");
        System.out.println("    press <enter> only to exit");

        initializeTable();
        showTable();
        
        Scanner scanner = new Scanner(System.in);
        displayMove = scanner.nextLine();
        
        while (!displayMove.equals("")) {
            tileId = Integer.parseInt(displayMove);
            moveTile();
            showTable();
            displayMove = "";
            displayMove = scanner.nextLine();
        }
        
        scanner.close();
    }

    private static void initializeTable() {
        Random rand = new Random();
        tileNumber = rand.nextInt(100);
        Arrays.fill(tileFlags, ' ');
        currentTile = 0;
        inversions = 0;
        
        for (r = 1; r <= 4; r++) {
            for (c = 1; c <= 4; c++) {
                do {
                    tileNumber = rand.nextInt(100);
                    tileNumber = tileNumber % 16;
                } while (tileFlags[tileNumber] == 'x');
                
                tileFlags[tileNumber] = 'x';
                if (tileNumber > 0 && tileNumber < currentTile) {
                    inversions++;
                }
                displayTable[r-1][c-1] = tileNumber;
                currentTile = tileNumber;
            }
        }
        
        inversions = inversions % 2;
    }

    private static void showTable() {
        StringBuilder tableStr = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tableStr.append(displayTable[i][j]);
            }
        }
        
        if (tableStr.toString().equals(winningDisplay)) {
            System.out.println("winning");
        }
        
        System.out.println(" " + rowSeparator);
        for (r = 1; r <= 4; r++) {
            System.out.print(columnSeparator);
            for (c = 1; c <= 4; c++) {
                if (displayTable[r-1][c-1] == 0) {
                    System.out.print("  ");
                    rEmpty = r;
                    cEmpty = c;
                } else {
                    System.out.printf("%02d", displayTable[r-1][c-1]);
                }
                System.out.print(columnSeparator);
            }
            System.out.println();
        }
        System.out.println(" " + rowSeparator);
    }

    private static void moveTile() {
        if (!(tileId >= 1 && tileId <= 15)) {
            System.out.println("invalid tile number");
            return;
        }

        r = 0;
        c = 0;
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (displayTable[i][j] == tileId) {
                    r = i + 1;
                    c = j + 1;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (r == rEmpty) {
            if (cEmpty < c) {
                for (cTo = cEmpty; cTo < c; cTo++) {
                    cFrom = cTo + 1;
                    displayTable[rEmpty-1][cTo] = displayTable[rEmpty-1][cFrom];
                }
            } else {
                for (cTo = cEmpty; cTo > c; cTo--) {
                    cFrom = cTo - 1;
                    displayTable[rEmpty-1][cTo] = displayTable[rEmpty-1][cFrom];
                }
            }
            displayTable[rEmpty-1][c-1] = 0;
        } else if (c == cEmpty) {
            if (rEmpty < r) {
                for (rTo = rEmpty; rTo < r; rTo++) {
                    rFrom = rTo + 1;
                    displayTable[rTo][cEmpty-1] = displayTable[rFrom][cEmpty-1];
                }
            } else {
                for (rTo = rEmpty; rTo > r; rTo--) {
                    rFrom = rTo - 1;
                    displayTable[rTo][cEmpty-1] = displayTable[rFrom][cEmpty-1];
                }
            }
            displayTable[r-1][cEmpty-1] = 0;
        } else {
            System.out.println("invalid move");
        }
    }
}