import java.io.*;
import java.util.*;

public class AOC202104 {
    private static final int MAX_DRAWN = 99;
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    
    private int[] wsDrawn = new int[MAX_DRAWN + 1];
    private int[][][] wsBoards = new int[MAX_BOARDS + 1][BOARD_SIZE + 1][BOARD_SIZE + 1];
    private int[][][] wsMarked = new int[MAX_BOARDS + 1][BOARD_SIZE + 1][BOARD_SIZE + 1];
    
    private int m = 0;
    private int y = 1;
    private long wsSum = 0;
    private int wsProd = 1;
    private long wsResult = 0;
    
    public static void main(String[] args) {
        AOC202104 program = new AOC202104();
        program.run();
    }
    
    private void run() {
        readInput();
        drawNumbers();
    }
    
    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d04.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processRecord(String line) {
        if (line.length() > 14) {
            readNumbers(line);
        } else if (line.length() > 0) {
            readBoards(line);
        } else {
            nextBoard();
        }
    }
    
    private void readNumbers(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length && i < MAX_DRAWN; i++) {
            wsDrawn[i + 1] = Integer.parseInt(parts[i].trim());
        }
    }
    
    private void readBoards(String line) {
        String[] parts = line.trim().split("\\s+");
        for (int x = 1; x <= BOARD_SIZE && x <= parts.length; x++) {
            wsBoards[m][y][x] = Integer.parseInt(parts[x - 1]);
        }
        y++;
    }
    
    private void nextBoard() {
        m++;
        y = 1;
    }
    
    private void drawNumbers() {
        for (int i = 1; i <= MAX_DRAWN; i++) {
            if (wsDrawn[i] == 0) break;
            
            for (int j = 1; j <= m; j++) {
                for (int x = 1; x <= BOARD_SIZE; x++) {
                    for (int yy = 1; yy <= BOARD_SIZE; yy++) {
                        if (wsBoards[j][x][yy] == wsDrawn[i]) {
                            wsMarked[j][x][yy] = 1;
                        }
                    }
                }
            }
            
            checkIfBingo(i);
        }
    }
    
    private void checkIfBingo(int drawnIndex) {
        for (int k = 1; k <= m; k++) {
            // Check columns
            for (int yy = 1; yy <= BOARD_SIZE; yy++) {
                wsProd = 1;
                for (int x = 1; x <= BOARD_SIZE; x++) {
                    wsProd *= wsMarked[k][x][yy];
                }
                check(k, drawnIndex);
            }
            
            // Check rows
            for (int x = 1; x <= BOARD_SIZE; x++) {
                wsProd = 1;
                for (int yy = 1; yy <= BOARD_SIZE; yy++) {
                    wsProd *= wsMarked[k][x][yy];
                }
                check(k, drawnIndex);
            }
        }
    }
    
    private void check(int k, int drawnIndex) {
        if (wsProd == 1) {
            wsSum = 0;
            for (int x = 1; x <= BOARD_SIZE; x++) {
                for (int yy = 1; yy <= BOARD_SIZE; yy++) {
                    if (wsMarked[k][x][yy] == 0) {
                        wsSum += wsBoards[k][x][yy];
                    }
                }
            }
            wsResult = wsSum * wsDrawn[drawnIndex];
            System.out.println("Bingo! " + wsResult);
            System.exit(0);
        }
    }
}