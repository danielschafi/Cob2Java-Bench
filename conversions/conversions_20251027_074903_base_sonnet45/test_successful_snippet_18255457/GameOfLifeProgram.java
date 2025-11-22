public class GameOfLifeProgram {
    private static final int GRID_SIZE = 5;
    private static char[][] cell = new char[GRID_SIZE][GRID_SIZE];
    private static char[][] nextGenCell = new char[GRID_SIZE][GRID_SIZE];
    
    public static void main(String[] args) {
        initializeGrid();
        
        for (int currentCell = 2; currentCell <= 4; currentCell++) {
            cell[3][currentCell] = '#';
        }
        
        for (int generation = 0; generation <= 2; generation++) {
            showGrid(generation);
            updateLife();
            copyNextGenToCell();
        }
    }
    
    private static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cell[i][j] = ' ';
                nextGenCell[i][j] = ' ';
            }
        }
    }
    
    private static void showGrid(int generation) {
        System.out.println("GENERATION " + generation + ":");
        System.out.println("   +---+");
        
        for (int currentRow = 2; currentRow <= 4; currentRow++) {
            System.out.print("   |");
            for (int currentCell = 2; currentCell <= 4; currentCell++) {
                System.out.print(cell[currentRow][currentCell]);
            }
            System.out.println("|");
        }
        
        System.out.println("   +---+");
        System.out.println();
    }
    
    private static void updateLife() {
        for (int currentRow = 2; currentRow <= 4; currentRow++) {
            for (int currentCell = 2; currentCell <= 4; currentCell++) {
                updateCell(currentRow, currentCell);
            }
        }
    }
    
    private static void updateCell(int currentRow, int currentCell) {
        int livingNeighbours = 0;
        
        for (int checkRow = -1; checkRow <= 1; checkRow++) {
            int neighbourRow = currentRow + checkRow;
            for (int checkCell = -1; checkCell <= 1; checkCell++) {
                int neighbourCell = currentCell + checkCell;
                if (cell[neighbourRow][neighbourCell] == '#' && 
                    (checkCell != 0 || checkRow != 0)) {
                    livingNeighbours++;
                }
            }
        }
        
        if (livingNeighbours == 2) {
            nextGenCell[currentRow][currentCell] = cell[currentRow][currentCell];
        } else if (livingNeighbours == 3) {
            nextGenCell[currentRow][currentCell] = '#';
        } else {
            nextGenCell[currentRow][currentCell] = ' ';
        }
    }
    
    private static void copyNextGenToCell() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cell[i][j] = nextGenCell[i][j];
            }
        }
    }
}