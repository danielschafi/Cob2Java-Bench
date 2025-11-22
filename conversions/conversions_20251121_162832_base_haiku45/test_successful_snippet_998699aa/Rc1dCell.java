public class Rc1dCell {
    private static final int MAX_GENS = 9;
    private static final int STATE_WIDTH = 20;
    private static final String STATE_TABLE_INIT = ".@@@.@@.@.@.@.@..@..";
    private static final char ALIVE = '@';
    private static final char DEAD = '.';
    
    private int stateGen = 0;
    private char[] stateTable = new char[STATE_WIDTH];
    private char[] newStateTable = new char[STATE_WIDTH];
    private int cellIndex;
    private int neighborCount;
    
    public static void main(String[] args) {
        Rc1dCell program = new Rc1dCell();
        program.initStateTable();
        
        for (int i = 0; i < MAX_GENS; i++) {
            program.displayRow();
            program.nextState();
        }
        
        program.displayRow();
    }
    
    private void initStateTable() {
        for (cellIndex = 1; cellIndex <= STATE_WIDTH; cellIndex++) {
            stateTable[cellIndex - 1] = STATE_TABLE_INIT.charAt(cellIndex - 1);
        }
    }
    
    private void displayRow() {
        System.out.printf("%3d: ", stateGen);
        for (int i = 0; i < STATE_WIDTH; i++) {
            System.out.print(stateTable[i]);
        }
        System.out.println();
    }
    
    private void nextState() {
        stateGen++;
        System.arraycopy(stateTable, 0, newStateTable, 0, STATE_WIDTH);
        
        for (cellIndex = 1; cellIndex <= STATE_WIDTH; cellIndex++) {
            countNeighbors();
            dieOff();
            newBirths();
        }
        
        System.arraycopy(newStateTable, 0, stateTable, 0, STATE_WIDTH);
    }
    
    private void countNeighbors() {
        neighborCount = 0;
        
        if (cellIndex == 1 || cellIndex == STATE_WIDTH) {
            neighborCount++;
        } else {
            if (cellIndex > 1 && stateTable[cellIndex - 2] == ALIVE) {
                neighborCount++;
            }
            if (cellIndex < STATE_WIDTH && stateTable[cellIndex] == ALIVE) {
                neighborCount++;
            }
        }
    }
    
    private void dieOff() {
        if (stateTable[cellIndex - 1] == ALIVE && neighborCount != 1) {
            newStateTable[cellIndex - 1] = DEAD;
        }
    }
    
    private void newBirths() {
        if (stateTable[cellIndex - 1] == DEAD && neighborCount == 2) {
            newStateTable[cellIndex - 1] = ALIVE;
        }
    }
}