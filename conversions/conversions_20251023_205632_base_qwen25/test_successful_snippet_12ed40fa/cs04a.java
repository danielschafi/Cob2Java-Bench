import java.util.Scanner;

public class cs04a {
    private static final String MYNAME = "cs04a";
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
    private static String processType = "";
    private static String winningNumber = "";
    private static boolean inptDataEof = false;
    private static boolean processSwTest = false;
    private static boolean bingoSwYellsBingo = false;
    private static final String[] drawNumber = new String[200];
    private static final String[][][] boardVal = new String[200][5][5];
    private static final boolean[][][] bingoMark = new boolean[200][5][5];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processSwTest = processType.equals("TEST");
        }

        if (processSwTest) {
            System.out.println("TRACE READY");
        }

        readAndParseInpt();

        for (int numbersIndx = 1; numbersIndx <= numbersMax && !bingoSwYellsBingo; numbersIndx++) {
            playTheGame(numbersIndx);
        }

        if (bingoSwYellsBingo) {
            System.out.println(MYNAME + " bingo is declared for board " + winningBoard);
            dumpBoards();
            System.out.println(MYNAME + " winning number " + winningNumber);
            System.out.println(MYNAME + " bingo row " + bingoRow);
            System.out.println(MYNAME + " bingo col " + bingoCol);
            sumWinningBoard();
            currProduct = Long.parseLong(winningNumber) * winningSum;
            System.out.println(MYNAME + " final score " + currProduct);
        } else {
            System.out.println(MYNAME + " bingo remains elusive");
        }

        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void readAndParseInpt() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            wsRecCount++;
            if (wsRecCount == 1) {
                parseNumbers(line);
            } else if (line.trim().isEmpty()) {
                if (boardsMax == 0) {
                    boardsPtr = 1;
                } else {
                    boardsPtr++;
                }
                boardsMax++;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        boardVal[boardsPtr][i][j] = "";
                        bingoMark[boardsPtr][i][j] = false;
                    }
                }
            } else {
                parseBoard(line, boardsPtr);
                if (boardsPtr <= boardsMax) {
                    boardsPtr++;
                }
            }
        }
        scanner.close();

        System.out.println(MYNAME + " drawn numbers " + numbersMax);
        System.out.println(MYNAME + " boards " + boardsMax);

        if (processSwTest) {
            dumpBoards();
        }
    }

    private static void parseNumbers(String line) {
        String[] numbers = line.split(",");
        for (String number : numbers) {
            if (!number.trim().isEmpty()) {
                drawNumber[numbersMax] = number.trim();
                numbersMax++;
            }
        }
    }

    private static void parseBoard(String line, int boardIndx) {
        String[] values = line.trim().split("\\s+");
        for (int colIndx = 0; colIndx < values.length; colIndx++) {
            boardVal[boardIndx][boardsPtr - 1][colIndx] = values[colIndx];
            bingoMark[boardIndx][boardsPtr - 1][colIndx] = false;
        }
    }

    private static void playTheGame(int numbersIndx) {
        for (int boardIndx = 1; boardIndx <= boardsMax && !bingoSwYellsBingo; boardIndx++) {
            for (int rowIndx = 0; rowIndx < 5 && !bingoSwYellsBingo; rowIndx++) {
                for (int colIndx = 0; colIndx < 5; colIndx++) {
                    if (boardVal[boardIndx][rowIndx][colIndx].equals(drawNumber[numbersIndx - 1])) {
                        bingoMark[boardIndx][rowIndx][colIndx] = true;
                    }
                }
            }
            didSomeoneYellBingo(boardIndx);
            if (bingoSwYellsBingo) {
                winningBoard = boardIndx;
                winningNumber = drawNumber[numbersIndx - 1];
            }
        }
    }

    private static void didSomeoneYellBingo(int boardIndx) {
        for (int rowIndxB = 0; rowIndxB < 5 && bingoCount < 5; rowIndxB++) {
            bingoCount = 0;
            for (int colIndxB = 0; colIndxB < 5 && bingoCount < 5; colIndxB++) {
                if (bingoMark[boardIndx][rowIndxB][colIndxB]) {
                    bingoCount++;
                }
            }
        }

        if (bingoCount == 5) {
            bingoSwYellsBingo = true;
            bingoRow = bingoCount;
        } else {
            for (int colIndxB = 0; colIndxB < 5 && bingoCount < 5; colIndxB++) {
                bingoCount = 0;
                for (int rowIndxB = 0; rowIndxB < 5 && bingoCount < 5; rowIndxB++) {
                    if (bingoMark[boardIndx][rowIndxB][colIndxB]) {
                        bingoCount++;
                    }
                }
            }
            if (bingoCount == 5) {
                bingoSwYellsBingo = true;
                bingoCol = bingoCount;
            }
        }
    }

    private static void sumWinningBoard() {
        for (int rowIndx = 0; rowIndx < 5; rowIndx++) {
            for (int colIndx = 0; colIndx < 5; colIndx++) {
                if (!bingoMark[winningBoard][rowIndx][colIndx]) {
                    System.out.println(MYNAME + " adding BOARD-VAL(" + winningBoard + "," + (rowIndx + 1) + "," + (colIndx + 1) + ") " + boardVal[winningBoard][rowIndx][colIndx]);
                    winningSum += Integer.parseInt(boardVal[winningBoard][rowIndx][colIndx]);
                }
            }
        }
        System.out.println(MYNAME + " winning sum " + winningSum);
    }

    private static void dumpBoards() {
        if (processSwTest) {
            System.out.println("TRACE RESET");
        }

        for (int boardIndx = 1; boardIndx <= boardsMax; boardIndx++) {
            for (int rowIndx = 0; rowIndx < 5; rowIndx++) {
                System.out.print(MYNAME + " " + boardIndx);
                for (int colIndx = 0; colIndx < 5; colIndx++) {
                    System.out.print(" " + boardVal[boardIndx][rowIndx][colIndx] + " " + (bingoMark[boardIndx][rowIndx][colIndx] ? "Y" : "N"));
                }
                System.out.println();
            }
            System.out.println(MYNAME);
        }

        if (processSwTest) {
            System.out.println("TRACE READY");
        }
    }
}