import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2021041 {
    private static final int MAX_BOARDS = 100;
    private static final int BOARD_SIZE = 5;
    
    private static int[] drawnNumbers = new int[99];
    private static int[][][] boards = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private static int[][][] marked = new int[MAX_BOARDS][BOARD_SIZE][BOARD_SIZE];
    private static int boardCount = 0;
    private static int sum = 0;
    private static int result = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d04.input"))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    parseDrawnNumbers(line);
                    firstLine = false;
                } else if (!line.trim().isEmpty()) {
                    parseBoard(line, reader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        drawNumbers();
    }
    
    private static void parseDrawnNumbers(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length && i < 99; i++) {
            drawnNumbers[i] = Integer.parseInt(parts[i].trim());
        }
    }
    
    private static void parseBoard(String firstLine, BufferedReader reader) throws Exception {
        int boardIndex = boardCount;
        int row = 0;
        
        // Parse first line
        parseBoardRow(firstLine, boardIndex, row++);
        
        // Parse remaining lines
        for (int i = 0; i < BOARD_SIZE - 1; i++) {
            String line = reader.readLine();
            if (line != null) {
                parseBoardRow(line, boardIndex, row++);
            }
        }
        
        boardCount++;
    }
    
    private static void parseBoardRow(String line, int boardIndex, int rowIndex) {
        String[] parts = line.trim().split("\\s+");
        for (int col = 0; col < BOARD_SIZE && col < parts.length; col++) {
            boards[boardIndex][rowIndex][col] = Integer.parseInt(parts[col]);
        }
    }
    
    private static void drawNumbers() {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < boardCount; j++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    for (int y = 0; y < BOARD_SIZE; y++) {
                        if (boards[j][x][y] == drawnNumbers[i]) {
                            marked[j][x][y] = 1;
                        }
                    }
                }
                checkForBingo(j, i);
            }
        }
    }
    
    private static void checkForBingo(int boardIndex, int drawIndex) {
        // Check columns
        for (int y = 0; y < BOARD_SIZE; y++) {
            int product = 1;
            for (int x = 0; x < BOARD_SIZE; x++) {
                product *= marked[boardIndex][x][y];
            }
            if (product == 1) {
                calculateResult(boardIndex, drawIndex);
                return;
            }
        }
        
        // Check rows
        for (int x = 0; x < BOARD_SIZE; x++) {
            int product = 1;
            for (int y = 0; y < BOARD_SIZE; y++) {
                product *= marked[boardIndex][x][y];
            }
            if (product == 1) {
                calculateResult(boardIndex, drawIndex);
                return;
            }
        }
    }
    
    private static void calculateResult(int boardIndex, int drawIndex) {
        sum = 0;
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (marked[boardIndex][x][y] == 0) {
                    sum += boards[boardIndex][x][y];
                }
            }
        }
        result = sum * drawnNumbers[drawIndex];
        System.out.println("Bingo! " + result);
        System.exit(0);
    }
}