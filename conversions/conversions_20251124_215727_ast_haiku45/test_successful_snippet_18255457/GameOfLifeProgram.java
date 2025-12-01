```java
public class GameOfLifeProgram {
    private char[][] cell = new char[6][6];
    private char[][] nextGenCell = new char[6][6];
    
    private int generation;
    private int currentRow;
    private int currentCell;
    private int livingNeighbours;
    private int neighbourRow;
    private int neighbourCell;
    private int checkRow;
    private int checkCell;
    
    public GameOfLifeProgram() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cell[i][j] = ' ';
                nextGenCell[i][j] = ' ';
            }
        }
    }
    
    public static void main(String[] args) {
        GameOfLifeProgram program = new GameOfLifeProgram();
        program.controlParagraph();
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
        cell[3][currentCell] = '#';
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
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cell[i][j] = nextGenCell[i][j];
            }
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
        System.out.print(cell[currentRow][currentCell]);
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
        
        if (livingNeighbours == 2) {
            nextGenCell[currentRow][currentCell] = cell[currentRow][currentCell];
        } else if (livingNeighbours == 3) {
            nextGenCell[currentRow][currentCell] = '#';
        } else {
            nextGenCell[currentRow][currentCell] = ' ';
        }
    }
    
    private void checkRowParagraph() {
        neighbourRow = checkRow + currentRow;
        for (checkCell = -1; checkCell <= 1; checkCell++) {
            checkCellParagraph();
        }
    }
    
    private void checkCellParagraph() {
        neighbourCell = checkCell + currentCell;
        if (neighbourRow >= 0 && neighbourRow < 6 && neighbourCell >= 0 && neighbourCell < 6) {
            if (cell[neighbourRow][neighbourCell] == '#' && 
                (checkCell != 0 || checkRow != 0)) {
                livingNeighbours++;
            }
        }
    }
}