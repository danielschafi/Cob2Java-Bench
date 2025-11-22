public class GameOfLifeProgram {
    private static char[][] cell = new char[5][5];
    private static char[][] nextGenCell = new char[5][5];
    private static int generation;
    private static int currentRow;
    private static int currentCell;
    private static int livingNeighbours;
    private static int neighbourRow;
    private static int neighbourCell;
    private static int checkRow;
    private static int checkCell;

    public static void main(String[] args) {
        controlParagraph();
    }

    private static void controlParagraph() {
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            blinkerParagraph();
        }
        for (generation = 0; generation <= 2; generation++) {
            showGridParagraph();
            lifeParagraph();
        }
    }

    private static void blinkerParagraph() {
        cell[3][currentCell] = '#';
    }

    private static void showGridParagraph() {
        System.out.println("GENERATION " + generation + ":");
        System.out.println("   +---+");
        for (currentRow = 2; currentRow <= 4; currentRow++) {
            showRowParagraph();
        }
        System.out.println("   +---+");
        System.out.println();
    }

    private static void lifeParagraph() {
        for (currentRow = 2; currentRow <= 4; currentRow++) {
            updateRowParagraph();
        }
        for (int i = 0; i < 5; i++) {
            System.arraycopy(nextGenCell[i], 0, cell[i], 0, 5);
        }
    }

    private static void showRowParagraph() {
        System.out.print("   |");
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            showCellParagraph();
        }
        System.out.println("|");
    }

    private static void showCellParagraph() {
        System.out.print(cell[currentRow][currentCell]);
    }

    private static void updateRowParagraph() {
        for (currentCell = 2; currentCell <= 4; currentCell++) {
            updateCellParagraph();
        }
    }

    private static void updateCellParagraph() {
        livingNeighbours = 0;
        for (checkRow = -1; checkRow <= 1; checkRow++) {
            checkRowParagraph();
        }
        switch (livingNeighbours) {
            case 2:
                nextGenCell[currentRow][currentCell] = cell[currentRow][currentCell];
                break;
            case 3:
                nextGenCell[currentRow][currentCell] = '#';
                break;
            default:
                nextGenCell[currentRow][currentCell] = ' ';
                break;
        }
    }

    private static void checkRowParagraph() {
        neighbourRow = currentRow + checkRow;
        for (checkCell = -1; checkCell <= 1; checkCell++) {
            checkCellParagraph();
        }
    }

    private static void checkCellParagraph() {
        neighbourCell = currentCell + checkCell;
        if (cell[neighbourRow][neighbourCell] == '#' && (checkCell != 0 || checkRow != 0)) {
            livingNeighbours++;
        }
    }
}