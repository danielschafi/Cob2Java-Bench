```java
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

        for (cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            countNeighbors();
            dieOff();
            newBirths();
        }

        System.arraycopy(newStateTable, 0, stateTable, 0, STATE_WIDTH);
    }

    private void dieOff() {
        if (stateTable[cellIndex] == ALIVE && neighborCount != 1) {
            newStateTable[cellIndex] = DEAD;
        }
    }

    private void newBirths() {
        if (stateTable[cellIndex] == DEAD && neighborCount == 2) {
            newStateTable[cellIndex] = ALIVE;
        }
    }

    private void countNeighbors() {
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

    private void initStateTable() {
        for (cellIndex = 0; cellIndex < STATE_WIDTH; cellIndex++) {
            stateTable[cellIndex] = STATE_TABLE_INIT.charAt(cellIndex);
        }
    }
}