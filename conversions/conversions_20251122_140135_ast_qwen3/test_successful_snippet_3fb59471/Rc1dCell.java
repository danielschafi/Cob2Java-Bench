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
    
    public void run() {
        initStateTable();
        for (int i = 0; i < MAX_GENS; i++) {
            displayRow();
            nextState();
        }
        displayRow();
    }
    
    private void displayRow() {
        System.out.println(stateGen + ": " + new String(stateTable));
    }
    
    private void nextState() {
        stateGen++;
        System.arraycopy(stateTable, 0, newStateTable, 0, STATE_WIDTH);
        
        for (cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            countNeighbors();
            dieOff();
            newBirths();
        }
        
        System.arraycopy(newStateTable, 0, stateTable, 0, STATE_WIDTH);
    }
    
    private void dieOff() {
        if (stateTable[cellIndex] == ALIVE && !isComfy()) {
            newStateTable[cellIndex] = DEAD;
        }
    }
    
    private void newBirths() {
        if (stateTable[cellIndex] == DEAD && isRipe()) {
            newStateTable[cellIndex] = ALIVE;
        }
    }
    
    private int neighborCount;
    
    private void countNeighbors() {
        neighborCount = 0;
        if (isAtBeginning() || isAtEnd()) {
            neighborCount = 1;
        } else {
            if (isInside() && stateTable[cellIndex - 1] == ALIVE) {
                neighborCount++;
            }
            if (isInside() && stateTable[cellIndex + 1] == ALIVE) {
                neighborCount++;
            }
        }
    }
    
    private boolean isComfy() {
        return neighborCount == 1;
    }
    
    private boolean isRipe() {
        return neighborCount == 2;
    }
    
    private boolean isAtBeginning() {
        return cellIndex == 0;
    }
    
    private boolean isInside() {
        return cellIndex >= 1 && cellIndex <= 18;
    }
    
    private boolean isAtEnd() {
        return cellIndex == STATE_WIDTH - 1;
    }
    
    private void initStateTable() {
        for (cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            stateTable[cellIndex] = STATE_TABLE_INIT.charAt(cellIndex);
        }
    }
    
    public static void main(String[] args) {
        new Rc1dCell().run();
    }
}