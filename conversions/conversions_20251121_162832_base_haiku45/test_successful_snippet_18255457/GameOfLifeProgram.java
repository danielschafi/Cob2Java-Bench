public class GameOfLifeProgram {
    private static final int GRID_SIZE = 5;
    private char[][] cell = new char[GRID_SIZE][GRID_SIZE];
    private char[][] nextGenCell = new char[GRID_SIZE][GRID_SIZE];
    
    private int generation;
    private int currentRow;
    private int currentCell;
    private int livingNeighbours;
    private int neighbourRow;
    private int neighbourCell;
    private int checkRow;
    private int checkCell;
    
    public GameOfLifeProgram() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cell[i][j] = ' ';
                nextGenCell[i][j] = ' ';
            }
        }
    }
    
    public void binkerParagraph(int currentCellParam) {
        cell[2][currentCellParam] = '#';
    }
    
    public void showGridParagraph() {
        System.out.println("GENERATION " + generation + ":");
        System.out.println("   +---+");
        for (currentRow = 1; currentRow <= 3; currentRow++) {
            showRowParagraph();
        }
        System.out.println("   +---+");
        System.out.println();
    }
    
    public void lifeParagraph() {
        for (currentRow = 1; currentRow <= 3; currentRow++) {
            updateRowParagraph();
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cell[i][j] = nextGenCell[i][j];
            }
        }
    }
    
    public void showRowParagraph() {
        System.out.print("   |");
        for (currentCell = 1; currentCell <= 3; currentCell++) {
            showCellParagraph();
        }
        System.out.println("|");
    }
    
    public void showCellParagraph() {
        System.out.print(cell[currentRow][currentCell]);
    }
    
    public void updateRowParagraph() {
        for (currentCell = 1; currentCell <= 3; currentCell++) {
            updateCellParagraph();
        }
    }
    
    public void updateCellParagraph() {
        livingNeighbours = 0;
        for (checkRow = -1; checkRow <= 1; checkRow++) {
            checkRowParagraph();
        }
        
        if (livingNeighbours == 2) {
            nextGenCell[currentRow][currentCell] = cell[currentRow][currentCell];
        } else if (livingNeighbours == 3) {
            nextGenCell[currentRow][currentCell] = '#';
        } else {
            nextGenCell[currentRow][currentCell] = ' ';
        }
    }
    
    public void checkRowParagraph() {
        neighbourRow = checkRow + currentRow;
        for (checkCell = -1; checkCell <= 1; checkCell++) {
            checkCellParagraph();
        }
    }
    
    public void checkCellParagraph() {
        neighbourCell = checkCell + currentCell;
        if (neighbourRow >= 0 && neighbourRow < GRID_SIZE && 
            neighbourCell >= 0 && neighbourCell < GRID_SIZE) {
            if (cell[neighbourRow][neighbourCell] == '#' &&
                (checkCell != 0 || checkRow != 0)) {
                livingNeighbours++;
            }
        }
    }
    
    public void controlParagraph() {
        for (currentCell = 1; currentCell <= 3; currentCell++) {
            binkerParagraph(currentCell);
        }
        for (generation = 0; generation <= 2; generation++) {
            showGridParagraph();
            lifeParagraph();
        }
    }
    
    public static void main(String[] args) {
        GameOfLifeProgram program = new GameOfLifeProgram();
        program.controlParagraph();
    }
}