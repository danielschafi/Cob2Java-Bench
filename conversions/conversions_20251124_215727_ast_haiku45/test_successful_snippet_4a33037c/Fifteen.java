import java.util.Scanner;

public class Fifteen {
    private int r, rEmpty, rTo, rFrom;
    private int c, cEmpty, cTo, cFrom;
    private int[][] displayTable = new int[4][4];
    private int tileNumber;
    private String tileFlags;
    private String displayMove;
    private String tileId;
    private String rowSeparator;
    private String columnSeparator;
    private int inversions;
    private int currentTile;
    private String winningDisplay = "01020304050607080910111213141500";
    private Scanner scanner;

    public Fifteen() {
        scanner = new Scanner(System.in);
        rowSeparator = ".".repeat(21);
        columnSeparator = " . ";
        tileFlags = "";
        displayMove = "";
        tileId = "";
    }

    public static void main(String[] args) {
        Fifteen game = new Fifteen();
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

        while (!displayMove.trim().isEmpty()) {
            moveTile();
            showTable();
            displayMove = scanner.nextLine();
        }

        System.exit(0);
    }

    private void initializeTable() {
        tileNumber = (int) (System.currentTimeMillis() / 1000 % 16);
        tileFlags = "";
        currentTile = 0;
        inversions = 0;

        for (r = 0; r < 4; r++) {
            for (c = 0; c < 4; c++) {
                do {
                    tileNumber = (int) (Math.random() * 100);
                    tileNumber = tileNumber % 16;
                } while (tileNumber < 16 && tileFlags.contains(String.valueOf(tileNumber)));

                tileFlags += tileNumber;

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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tableStr.append(String.format("%02d", displayTable[i][j]));
            }
        }

        if (tableStr.toString().equals(winningDisplay)) {
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
                    System.out.print(String.format("%02d", displayTable[r][c]));
                }
            }
            System.out.println(columnSeparator);
        }
        System.out.println(" " + rowSeparator);
    }

    private void moveTile() {
        if (displayMove.trim().isEmpty()) {
            return;
        }

        try {
            int tileIdNum = Integer.parseInt(displayMove.trim());
            if (tileIdNum < 1 || tileIdNum > 15) {
                System.out.println("invalid tile number");
                return;
            }

            boolean found = false;
            for (r = 0; r < 4 && !found; r++) {
                for (c = 0; c < 4 && !found; c++) {
                    if (displayTable[r][c] == tileIdNum) {
                        found = true;
                    }
                }
            }
            r--;
            c--;

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
        } catch (NumberFormatException e) {
            System.out.println("invalid tile number");
        }
    }
}