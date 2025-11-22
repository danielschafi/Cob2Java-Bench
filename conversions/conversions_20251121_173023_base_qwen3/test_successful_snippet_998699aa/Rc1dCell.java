public class Rc1dCell {
    private static final int MAX_GENS = 9;
    private static final int STATE_WIDTH = 20;
    private static final String STATE_TABLE_INIT = ".@@@.@@.@.@.@.@..@..";
    private static final char ALIVE = '@';
    private static final char DEAD = '.';
    
    private static int stateGen = 0;
    private static char[] stateTable = new char[STATE_WIDTH];
    private static char[] newStateTable = new char[STATE_WIDTH];
    
    public static void main(String[] args) {
        initStateTable();
        for (int i = 0; i < MAX_GENS; i++) {
            displayRow();
            nextState();
        }
        displayRow();
    }
    
    private static void displayRow() {
        System.out.printf("%3d: %s%n", stateGen, new String(stateTable));
    }
    
    private static void nextState() {
        stateGen++;
        System.arraycopy(stateTable, 0, newStateTable, 0, STATE_WIDTH);
        
        for (int cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            countNeighbors(cellIndex);
            dieOff(cellIndex);
            newBirths(cellIndex);
        }
        
        System.arraycopy(newStateTable, 0, stateTable, 0, STATE_WIDTH);
    }
    
    private static void dieOff(int cellIndex) {
        if (stateTable[cellIndex] == ALIVE && !isComfy()) {
            newStateTable[cellIndex] = DEAD;
        }
    }
    
    private static void newBirths(int cellIndex) {
        if (stateTable[cellIndex] == DEAD && isRipe()) {
            newStateTable[cellIndex] = ALIVE;
        }
    }
    
    private static int neighborCount = 0;
    
    private static void countNeighbors(int cellIndex) {
        neighborCount = 0;
        if (cellIndex == 0 || cellIndex == STATE_WIDTH - 1) {
            neighborCount = 1;
        } else {
            if (stateTable[cellIndex - 1] == ALIVE) {
                neighborCount++;
            }
            if (stateTable[cellIndex + 1] == ALIVE) {
                neighborCount++;
            }
        }
    }
    
    private static boolean isComfy() {
        return neighborCount == 1;
    }
    
    private static boolean isRipe() {
        return neighborCount == 2;
    }
    
    private static void initStateTable() {
        for (int i = 0; i < STATE_WIDTH; i++) {
            stateTable[i] = STATE_TABLE_INIT.charAt(i);
        }
    }
}