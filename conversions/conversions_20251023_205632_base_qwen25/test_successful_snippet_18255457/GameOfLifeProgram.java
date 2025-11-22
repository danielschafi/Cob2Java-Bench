import java.util.Arrays;

public class GameOfLifeProgram {
    private char[][] cellTable = new char[5][5];
    private char[][] nextGenCellTable = new char[5][5];
    private int generation;
    private int currentRow;
    private int currentCell;
    private int livingNeighbours;
    private int neighbourRow;
    private int neighbourCell;
    private int checkRow;
    private int checkCell;

    public static void main(String[] args) {
        GameOfLifeProgram game = new GameOfLifeProgram();
        game.controlParagraph();
    }

    private void controlParagraph() {
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            blinkerParagraph();
        }
        for (generation = 0; generation <= 2; generation++) {
            showGridParagraph();
            lifeParagraph();
        }
    }

    private void blinkerParagraph() {
        cellTable[3][currentCell] = '#';
    }

    private void showGridParagraph() {
        System.out.println("GENERATION " + generation + ":");
        System.out.println("   +---+");
        for (currentRow = 2; currentRow <= 4; currentRow++) {
            showRowParagraph();
        }
        System.out.println("   +---+");
        System.out.println();
    }

    private void lifeParagraph() {
        for (currentRow = 2; currentRow <= 4; currentRow++) {
            updateRowParagraph();
        }
        for (int i = 0; i < 5; i++) {
            System.arraycopy(nextGenCellTable[i], 0, cellTable[i], 0, 5);
        }
    }

    private void showRowParagraph() {
        System.out.print("   |");
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            showCellParagraph();
        }
        System.out.println("|");
    }

    private void showCellParagraph() {
        System.out.print(cellTable[currentRow][currentCell]);
    }

    private void updateRowParagraph() {
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            updateCellParagraph();
        }
    }

    private void updateCellParagraph() {
        livingNeighbours = 0;
        for (checkRow = -1; checkRow <= 1; checkRow++) {
            checkRowParagraph();
        }
        switch (livingNeighbours) {
            case 2:
                nextGenCellTable[currentRow][currentCell] = cellTable[currentRow][currentCell];
                break;
            case 3:
                nextGenCellTable[currentRow][currentCell] = '#';
                break;
            default:
                nextGenCellTable[currentRow][currentCell] = ' ';
                break;
        }
    }

    private void checkRowParagraph() {
        neighbourRow = currentRow + checkRow;
        for (checkCell = -1; checkCell <= 1; checkCell++) {
            checkCellParagraph();
        }
    }

    private void checkCellParagraph() {
        neighbourCell = currentCell + checkCell;
        if (neighbourRow >= 0 && neighbourRow < 5 && neighbourCell >= 0 && neighbourCell < 5) {
            if (cellTable[neighbourRow][neighbourCell] == '#' && (checkCell != 0 || checkRow != 0)) {
                livingNeighbours++;
            }
        }
    }
}