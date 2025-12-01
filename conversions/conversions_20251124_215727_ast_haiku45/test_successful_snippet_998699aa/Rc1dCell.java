public class Rc1dCell {
    private static final int MAX_GENS = 9;
    private static final int STATE_WIDTH = 20;
    private static final String STATE_TABLE_INIT = ".@@@.@@.@.@.@.@..@..";
    private static final char ALIVE = '@';
    private static final char DEAD = '.';
    
    private int stateGen = 0;
    private char[] stateCells = new char[20];
    private char[] newStateCells = new char[20];
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
        for (int i = 0; i < STATE_WIDTH; i++) {
            stateCells[i] = STATE_TABLE_INIT.charAt(i);
        }
    }
    
    private void displayRow() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%3d", stateGen));
        sb.append(": ");
        for (int i = 0; i < STATE_WIDTH; i++) {
            sb.append(stateCells[i]);
        }
        System.out.println(sb.toString());
    }
    
    private void nextState() {
        stateGen++;
        System.arraycopy(stateCells, 0, newStateCells, 0, STATE_WIDTH);
        
        for (cellIndex = 1; cellIndex <= STATE_WIDTH; cellIndex++) {
            countNeighbors();
            dieOff();
            newBirths();
        }
        
        System.arraycopy(newStateCells, 0, stateCells, 0, STATE_WIDTH);
    }
    
    private void countNeighbors() {
        neighborCount = 0;
        
        if (cellIndex == 1 || cellIndex == STATE_WIDTH) {
            neighborCount++;
        } else {
            if (cellIndex >= 2 && cellIndex <= 19 && stateCells[cellIndex - 2] == ALIVE) {
                neighborCount++;
            }
            if (cellIndex >= 2 && cellIndex <= 19 && stateCells[cellIndex] == ALIVE) {
                neighborCount++;
            }
        }
    }
    
    private void dieOff() {
        if (stateCells[cellIndex - 1] == ALIVE && neighborCount != 1) {
            newStateCells[cellIndex - 1] = DEAD;
        }
    }
    
    private void newBirths() {
        if (stateCells[cellIndex - 1] == DEAD && neighborCount == 2) {
            newStateCells[cellIndex - 1] = ALIVE;
        }
    }
}