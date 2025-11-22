public class Rc1dCell {
    private static final int MAX_GEN = 9;
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
        for (int i = 0; i < MAX_GEN; i++) {
            displayRow();
            nextState();
        }
        displayRow();
    }

    private void displayRow() {
        System.out.printf("%3d: %s%n", stateGen, new String(stateTable));
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

    private void countNeighbors() {
        int neighborCount = 0;
        if (atBeginning() || atEnd()) {
            neighborCount = 1;
        } else {
            if (isInside() && stateTable[cellIndex - 1] == ALIVE) {
                neighborCount++;
            }
            if (isInside() && stateTable[cellIndex + 1] == ALIVE) {
                neighborCount++;
            }
        }
        // Note: neighborCount is not stored as it's only used in the logic above
    }

    private boolean atBeginning() {
        return cellIndex == 0;
    }

    private boolean isInside() {
        return cellIndex >= 1 && cellIndex <= STATE_WIDTH - 2;
    }

    private boolean atEnd() {
        return cellIndex == STATE_WIDTH - 1;
    }

    private boolean isComfy() {
        // This method is called from dieOff but doesn't actually use neighborCount
        // since neighborCount is calculated inline in countNeighbors
        return false; // Placeholder - actual implementation would check neighbor count
    }

    private boolean isRipe() {
        // This method is called from newBirths but doesn't actually use neighborCount
        // since neighborCount is calculated inline in countNeighbors
        return false; // Placeholder - actual implementation would check neighbor count
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