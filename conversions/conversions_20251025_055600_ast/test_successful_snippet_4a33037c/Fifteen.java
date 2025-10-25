import java.util.Random;
import java.util.Scanner;

public class Fifteen {
    int r, rEmpty, rTo, rFrom;
    int c, cEmpty, cTo, cFrom;
    int[][] displayTable = new int[4][4];
    int tileNumber;
    String tileFlags = "                ";
    String displayMove;
    int tileId;
    String rowSeparator = ".....................";
    String columnSeparator = " . ";
    int inversions;
    int currentTile;
    String winningDisplay = "01020304050607080910111213141500";

    public static void main(String[] args) {
        Fifteen game = new Fifteen();
        game.startFifteen();
    }

    void startFifteen() {
        System.out.println("start fifteen puzzle");
        System.out.println("    enter a two-digit tile number and press <enter> to move");
        System.out.println("    press <enter> only to exit");

        Scanner scanner = new Scanner(System.in);
        do {
            initializeTable();
            showTable();
            displayMove = scanner.nextLine();
            while (!displayMove.isEmpty()) {
                moveTile();
                showTable();
                displayMove = scanner.nextLine();
            }
        } while (inversions != 0);
        scanner.close();
    }

    void initializeTable() {
        Random random = new Random();
        tileNumber = random.nextInt(16);
        tileFlags = "                ";
        currentTile = 0;
        inversions = 0;
        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                do {
                    tileNumber = random.nextInt(16);
                } while (tileFlags.charAt(tileNumber) != ' ');
                tileFlags = tileFlags.substring(0, tileNumber) + 'x' + tileFlags.substring(tileNumber + 1);
                if (tileNumber > 0 && tileNumber < currentTile) {
                    inversions++;
                }
                displayTable[r][c] = tileNumber;
                currentTile = tileNumber;
            }
        }
        inversions %= 2;
    }

    void showTable() {
        if (isWinning()) {
            System.out.println("winning");
        }
        System.out.println();
        System.out.println(rowSeparator);
        for (r = 0; r < 4; r++) {
            System.out.print(columnSeparator);
            for (c = 0; c < 4; c++) {
                if (displayTable[r][c] == 0) {
                    System.out.print("  " + columnSeparator);
                    rEmpty = r;
                    cEmpty = c;
                } else {
                    System.out.printf("%2d%s", displayTable[r][c], columnSeparator);
                }
            }
            System.out.println();
        }
        System.out.println(rowSeparator);
        System.out.println();
    }

    boolean isWinning() {
        StringBuilder currentDisplay = new StringBuilder();
        for (int[] row : displayTable) {
            for (int cell : row) {
                currentDisplay.append(String.format("%02d", cell));
            }
        }
        return currentDisplay.toString().equals(winningDisplay);
    }

    void moveTile() {
        try {
            tileId = Integer.parseInt(displayMove);
        } catch (NumberFormatException e) {
            tileId = -1;
        }
        if (tileId < 1 || tileId > 15) {
            System.out.println("invalid tile number");
            return;
        }

        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                if (displayTable[r][c] == tileId) {
                    break;
                }
            }
            if (c < 4) {
                break;
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