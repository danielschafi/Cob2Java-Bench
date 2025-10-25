import java.util.Scanner;

public class cs04a {
    private static final int MAX_BOARDS = 200;
    private static final int MAX_NUMBERS = 200;
    private static final int BOARD_SIZE = 5;

    private static String myName = "cs04a";
    private static int wsRecCount = 0;
    private static int bingoCount = 0;
    private static int bingoCol = 0;
    private static int bingoRow = 0;
    private static int boardsMax = 0;
    private static int boardsPtr = 1;
    private static int winningBoard = 1;
    private static int winningSum = 0;
    private static int numbersMax = 0;
    private static int numbersPtr = 1;
    private static long currProduct = 0;
    private static int operationArg = 0;
    private static String processType = "    ";
    private static String winningNumber = "  ";

    private static String[] wsInpt = new String[512];
    private static boolean inptDataEofSw = false;
    private static boolean processTest = false;
    private static boolean bingoSw = false;
    private static boolean someoneYellsBingo = false;

    private static String[] drawNumber = new String[MAX_NUMBERS];
    private static String[][][] boardVal = new String[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private static boolean[][][] bingoMark = new boolean[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
        }

        if (processTest) {
            // Ready trace
        }

        readAndParseInpt();
        playTheGame();

        if (someoneYellsBingo) {
            System.out.println(myName + " bingo is declared for board " + winningBoard);
            dumpBoards();
            System.out.println(myName + " winning number " + winningNumber);
            System.out.println(myName + " bingo row " + bingoRow);
            System.out.println(myName + " bingo col " + bingoCol);
            sumWinningBoard();
            currProduct = Integer.parseInt(winningNumber) * winningSum;
            System.out.println(myName + " final score " + currProduct);
        } else {
            System.out.println(myName + " bingo remains elusive");
        }

        System.out.println(myName + " records read " + wsRecCount);
    }

    private static void readAndParseInpt() {
        Scanner scanner = new Scanner(System.in);

        readInptData();

        while (!inptDataEofSw) {
            if (wsRecCount == 1) {
                parseNumbers();
            } else if (wsInpt[0].trim().isEmpty()) {
                if (boardsMax == 0) {
                    boardsPtr = 1;
                } else {
                    boardsPtr++;
                }
                bingoMark[boardsPtr - 1] = new boolean[BOARD_SIZE][BOARD_SIZE];
                boardVal[boardsPtr - 1] = new String[BOARD_SIZE][BOARD_SIZE];
                boardsMax++;
            } else {
                parseBoard();
                if (boardsPtr < 5) {
                    boardsPtr++;
                }
            }
            readInptData();
        }

        scanner.close();

        System.out.println(myName + " drawn numbers " + numbersMax);
        System.out.println(myName + " boards " + boardsMax);

        if (processTest) {
            dumpBoards();
        }
    }

    private static void parseNumbers() {
        for (int i = 0; i < MAX_NUMBERS; i++) {
            if (wsInpt[numbersPtr].trim().isEmpty()) {
                break;
            }
            drawNumber[numbersMax] = wsInpt[numbersPtr].trim();
            numbersMax++;
            numbersPtr++;
        }
    }

    private static void parseBoard() {
        String[] values = wsInpt[0].trim().split("\\s+");
        for (int i = 0; i < BOARD_SIZE; i++) {
            boardVal[boardsPtr - 1][boardsPtr - 1][i] = values[i];
            bingoMark[boardsPtr - 1][boardsPtr - 1][i] = false;
        }
    }

    private static void playTheGame() {
        for (int boardIndx = 0; boardIndx < boardsMax && !someoneYellsBingo; boardIndx++) {
            for (int rowIndx = 0; rowIndx < BOARD_SIZE && !someoneYellsBingo; rowIndx++) {
                for (int colIndx = 0; colIndx < BOARD_SIZE; colIndx++) {
                    if (boardVal[boardIndx][rowIndx][colIndx].equals(drawNumber[numbersPtr - 1])) {
                        bingoMark[boardIndx][rowIndx][colIndx] = true;
                    }
                }
            }
            didSomeoneYellBingo(boardIndx);
            if (someoneYellsBingo) {
                winningBoard = boardIndx + 1;
                winningNumber = drawNumber[numbersPtr - 1];
            }
        }
    }

    private static void didSomeoneYellBingo(int boardIndx) {
        for (int rowIndxB = 0; rowIndxB < BOARD_SIZE && bingoCount != 5; rowIndxB++) {
            bingoCount = 0;
            for (int colIndxB = 0; colIndxB < BOARD_SIZE && bingoCount != 5; colIndxB++) {
                if (bingoMark[boardIndx][rowIndxB][colIndxB]) {
                    bingoCount++;
                }
            }
        }

        if (bingoCount == 5) {
            someoneYellsBingo = true;
            bingoRow = bingoCount;
        } else {
            for (int colIndxB = 0; colIndxB < BOARD_SIZE && bingoCount != 5; colIndxB++) {
                bingoCount = 0;
                for (int rowIndxB = 0; rowIndxB < BOARD_SIZE && bingoCount != 5; rowIndxB++) {
                    if (bingoMark[boardIndx][rowIndxB][colIndxB]) {
                        bingoCount++;
                    }
                }
            }
            if (bingoCount == 5) {
                someoneYellsBingo = true;
                bingoCol = bingoCount;
            }
        }
    }

    private static void sumWinningBoard() {
        for (int rowIndx = 0; rowIndx < BOARD_SIZE; rowIndx++) {
            for (int colIndx = 0; colIndx < BOARD_SIZE; colIndx++) {
                if (bingoMark[winningBoard - 1][rowIndx][colIndx]) {
                    continue;
                } else {
                    System.out.println(myName + " adding BOARD-VAL(" + winningBoard + "," + rowIndx + "," + colIndx + ") " + boardVal[winningBoard - 1][rowIndx][colIndx]);
                    winningSum += Integer.parseInt(boardVal[winningBoard - 1][rowIndx][colIndx]);
                }
            }
        }

        System.out.println(myName + " winning sum " + winningSum);
    }

    private static void readInptData() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            wsInpt[wsRecCount] = scanner.nextLine();
            wsRecCount++;
        } else {
            inptDataEofSw = true;
        }
        scanner.close();
    }

    private static void dumpBoards() {
        if (processTest) {
            // Reset trace
        }

        for (int boardIndx = 0; boardIndx < boardsMax; boardIndx++) {
            for (int rowIndx = 0; rowIndx < BOARD_SIZE; rowIndx++) {
                System.out.print(myName + " " + (boardIndx + 1));
                for (int colIndx = 0; colIndx < BOARD_SIZE; colIndx++) {
                    System.out.print(" " + boardVal[boardIndx][rowIndx][colIndx] + " " + (bingoMark[boardIndx][rowIndx][colIndx] ? "Y" : "N"));
                }
                System.out.println();
            }
            System.out.println(myName);
        }

        if (processTest) {
            // Ready trace
        }
    }
}