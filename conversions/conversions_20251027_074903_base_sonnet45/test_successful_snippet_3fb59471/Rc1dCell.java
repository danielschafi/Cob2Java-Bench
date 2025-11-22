public class Rc1dCell {
    private static final int MAX_GENS = 9;
    private static final int STATE_WIDTH = 20;
    private static final String STATE_TABLE_INIT = ".@@@.@@.@.@.@.@..@..";
    private static final char ALIVE = '@';
    private static final char DEAD = '.';

    private int stateGen;
    private char[] stateTable;
    private char[] newStateTable;

    public Rc1dCell() {
        this.stateGen = 0;
        this.stateTable = new char[STATE_WIDTH];
        this.newStateTable = new char[STATE_WIDTH];
    }

    public static void main(String[] args) {
        Rc1dCell cell = new Rc1dCell();
        cell.run();
    }

    public void run() {
        initStateTable();
        for (int i = 0; i < MAX_GENS; i++) {
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

        for (int cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            int neighborCount = countNeighbors(cellIndex);
            dieOff(cellIndex, neighborCount);
            newBirths(cellIndex, neighborCount);
        }

        System.arraycopy(newStateTable, 0, stateTable, 0, STATE_WIDTH);
    }

    private void dieOff(int cellIndex, int neighborCount) {
        if (stateTable[cellIndex] == ALIVE && neighborCount != 1) {
            newStateTable[cellIndex] = DEAD;
        }
    }

    private void newBirths(int cellIndex, int neighborCount) {
        if (stateTable[cellIndex] == DEAD && neighborCount == 2) {
            newStateTable[cellIndex] = ALIVE;
        }
    }

    private int countNeighbors(int cellIndex) {
        int neighborCount = 0;
        boolean atBeginning = (cellIndex == 0);
        boolean atEnd = (cellIndex == STATE_WIDTH - 1);
        boolean isInside = (cellIndex >= 1 && cellIndex <= STATE_WIDTH - 2);

        if (atBeginning || atEnd) {
            neighborCount = 1;
        } else {
            if (isInside && stateTable[cellIndex - 1] == ALIVE) {
                neighborCount++;
            }
            if (isInside && stateTable[cellIndex + 1] == ALIVE) {
                neighborCount++;
            }
        }

        return neighborCount;
    }

    private void initStateTable() {
        for (int cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            stateTable[cellIndex] = STATE_TABLE_INIT.charAt(cellIndex);
        }
    }
}