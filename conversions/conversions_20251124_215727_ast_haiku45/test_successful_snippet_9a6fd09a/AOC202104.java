import java.io.*;
import java.util.*;

public class AOC202104 {
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    private static final int MAX_DRAWN = 99;
    
    private int[] wsDrawn = new int[MAX_DRAWN];
    private int[][][] wsBoards = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int[][][] wsMarked = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private int m = 0;
    private int y = 1;
    private int wsSum = 0;
    private int wsProd = 1;
    private int wsResult = 0;
    
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
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                int recLen = line.length();
                
                if (recLen > 14) {
                    readNumbers(line);
                } else if (recLen > 0) {
                    readBoards(line);
                } else {
                    nextBoard();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readNumbers(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length && i < MAX_DRAWN; i++) {
            wsDrawn[i] = Integer.parseInt(parts[i].trim());
        }
    }
    
    private void readBoards(String line) {
        String[] parts = line.trim().split("\\s+");
        for (int x = 0; x < parts.length && x < BOARD_SIZE; x++) {
            wsBoards[m][y - 1][x] = Integer.parseInt(parts[x]);
        }
        y++;
    }
    
    private void nextBoard() {
        m++;
        y = 1;
    }
    
    private void drawNumbers() {
        for (int i = 0; i < MAX_DRAWN; i++) {
            if (wsDrawn[i] == 0) break;
            
            for (int j = 0; j < m; j++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    for (int yy = 0; yy < BOARD_SIZE; yy++) {
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
        for (int k = 0; k < m; k++) {
            // Check columns
            for (int yy = 0; yy < BOARD_SIZE; yy++) {
                wsProd = 1;
                for (int x = 0; x < BOARD_SIZE; x++) {
                    wsProd *= wsMarked[k][x][yy];
                }
                check(k, drawnIndex);
            }
            
            // Check rows
            for (int x = 0; x < BOARD_SIZE; x++) {
                wsProd = 1;
                for (int yy = 0; yy < BOARD_SIZE; yy++) {
                    wsProd *= wsMarked[k][x][yy];
                }
                check(k, drawnIndex);
            }
        }
    }
    
    private void check(int k, int drawnIndex) {
        if (wsProd == 1) {
            wsSum = 0;
            for (int x = 0; x < BOARD_SIZE; x++) {
                for (int yy = 0; yy < BOARD_SIZE; yy++) {
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