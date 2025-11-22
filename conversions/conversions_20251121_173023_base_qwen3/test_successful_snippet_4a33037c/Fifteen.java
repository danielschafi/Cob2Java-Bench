import java.util.*;

public class Fifteen {
    private static int r, rEmpty, rTo, rFrom;
    private static int c, cEmpty, cTo, cFrom;
    private static int[][] displayTable = new int[4][4];
    private static int tileNumber;
    private static char[] tileFlags = new char[16];
    private static int tileId;
    private static String rowSeparator = ".....................";
    private static String columnSeparator = " . ";
    private static int inversions;
    private static int currentTile;
    private static String winningDisplay = "01020304050607080910111213141500";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("start fifteen puzzle");
        System.out.println("    enter a two-digit tile number and press <enter> to move");
        System.out.println("    press <enter> only to exit");

        initializeTable();
        showTable();
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("")) {
                break;
            }
            tileId = Integer.parseInt(input);
            moveTile();
            showTable();
        }
    }

    private static void initializeTable() {
        Random rand = new Random();
        tileNumber = rand.nextInt(100);
        Arrays.fill(tileFlags, ' ');
        inversions = 0;
        currentTile = 0;

        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                do {
                    tileNumber = rand.nextInt(16);
                } while (tileFlags[tileNumber] != ' ');

                tileFlags[tileNumber] = 'x';
                if (tileNumber > 0 && tileNumber < currentTile) {
                    inversions++;
                }
                displayTable[r][c] = tileNumber;
                currentTile = tileNumber;
            }
        }
        inversions = inversions % 2;
    }

    private static void showTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(displayTable[i][j]);
            }
        }
        if (sb.toString().equals(winningDisplay)) {
            System.out.println("winning");
        }

        System.out.println(" ");
        System.out.println(rowSeparator);
        
        for (r = 0; r < 4; r++) {
            System.out.print(columnSeparator);
            for (c = 0; c < 4; c++) {
                if (displayTable[r][c] == 0) {
                    System.out.print("  ");
                    rEmpty = r;
                    cEmpty = c;
                } else {
                    System.out.printf("%02d", displayTable[r][c]);
                }
                System.out.print(columnSeparator);
            }
            System.out.println();
        }
        System.out.println(rowSeparator);
    }

    private static void moveTile() {
        if (tileId < 1 || tileId > 15) {
            System.out.println("invalid tile number");
            return;
        }

        r = 0;
        c = 0;
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (displayTable[i][j] == tileId) {
                    r = i;
                    c = j;
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