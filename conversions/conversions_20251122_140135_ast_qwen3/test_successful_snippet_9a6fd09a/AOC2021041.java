import java.io.*;
import java.util.*;

public class AOC2021041 {
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    
    private static int[] drawnNumbers = new int[99];
    private static int[][][] boards = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private static int[][][] marked = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private static int boardCount = 0;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        readInput();
        drawNumbers();
    }

    private static void readInput() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("d04.input"))) {
            String line;
            
            // Read drawn numbers
            line = reader.readLine();
            String[] numbers = line.split(",");
            for (int i = 0; i < numbers.length && i < 99; i++) {
                drawnNumbers[i] = Integer.parseInt(numbers[i].trim());
            }
            
            // Read boards
            int currentBoard = 0;
            int currentRow = 0;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                String[] values = line.split("\\s+");
                for (int i = 0; i < values.length && i < BOARD_SIZE; i++) {
                    boards[currentBoard][currentRow][i] = Integer.parseInt(values[i]);
                }
                currentRow++;
                
                if (currentRow >= BOARD_SIZE) {
                    currentBoard++;
                    currentRow = 0;
                }
            }
            boardCount = currentBoard;
        }
    }

    private static void drawNumbers() {
        for (int i = 0; i < 99; i++) {
            int drawnNumber = drawnNumbers[i];
            
            // Mark numbers on all boards
            for (int j = 0; j < boardCount; j++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    for (int y = 0; y < BOARD_SIZE; y++) {
                        if (boards[j][x][y] == drawnNumber) {
                            marked[j][x][y] = 1;
                        }
                    }
                }
            }
            
            checkForBingo(i);
        }
    }

    private static void checkForBingo(int drawIndex) {
        for (int k = 0; k < boardCount; k++) {
            // Check columns
            for (int y = 0; y < BOARD_SIZE; y++) {
                int product = 1;
                for (int x = 0; x < BOARD_SIZE; x++) {
                    product *= marked[k][x][y];
                }
                if (product == 1) {
                    calculateResult(drawIndex, k);
                    return;
                }
            }
            
            // Check rows
            for (int x = 0; x < BOARD_SIZE; x++) {
                int product = 1;
                for (int y = 0; y < BOARD_SIZE; y++) {
                    product *= marked[k][x][y];
                }
                if (product == 1) {
                    calculateResult(drawIndex, k);
                    return;
                }
            }
        }
    }

    private static void calculateResult(int drawIndex, int boardIndex) {
        int sum = 0;
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (marked[boardIndex][x][y] == 0) {
                    sum += boards[boardIndex][x][y];
                }
            }
        }
        int result = sum * drawnNumbers[drawIndex];
        System.out.println("Bingo! " + result);
        System.exit(0);
    }
}