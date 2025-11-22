import java.io.*;
import java.util.*;

public class Main8 {
    private static final int MAX_LINES = 102;
    private static final int MAX_COLS = 102;
    
    private static char[][] wsB = new char[MAX_LINES][MAX_COLS];
    private static String[] currLine = new String[MAX_LINES];
    private static int lineCounter = 0;
    private static int charCounter = 0;
    private static int fakeCounter1 = 0;
    private static int fakeCounter2 = 0;
    private static int fakeCounter3 = 0;
    private static int fakeCounter4 = 0;
    private static int temp = 0;
    private static int total = 0;
    private static boolean endOfFile = false;
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("9.dat"));
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null && lineNumber < MAX_LINES) {
                currLine[lineNumber] = line;
                lineNumber++;
            }
            reader.close();
            
            // Initialize wsB array
            for (int i = 0; i < MAX_LINES; i++) {
                for (int j = 0; j < MAX_COLS; j++) {
                    wsB[i][j] = ' ';
                }
            }
            
            // Process input lines
            lineCounter = 2;
            while (!endOfFile && lineCounter < MAX_LINES) {
                charCounter = 2;
                while (charCounter < MAX_COLS) {
                    fakeCounter1 = charCounter - 1;
                    if (fakeCounter1 >= 0 && fakeCounter1 < currLine[lineCounter - 2].length()) {
                        wsB[lineCounter][charCounter] = currLine[lineCounter - 2].charAt(fakeCounter1);
                    } else {
                        wsB[lineCounter][charCounter] = ' ';
                    }
                    charCounter++;
                }
                lineCounter++;
                
                if (lineCounter >= lineNumber) {
                    endOfFile = true;
                }
            }
            
            // Calculate result
            lineCounter = 2;
            while (lineCounter < MAX_LINES) {
                charCounter = 2;
                while (charCounter < MAX_COLS) {
                    fakeCounter1 = charCounter - 1;
                    fakeCounter2 = lineCounter - 1;
                    fakeCounter3 = charCounter + 1;
                    fakeCounter4 = lineCounter + 1;
                    
                    if (fakeCounter1 >= 0 && fakeCounter1 < MAX_COLS &&
                        fakeCounter2 >= 0 && fakeCounter2 < MAX_LINES &&
                        fakeCounter3 >= 0 && fakeCounter3 < MAX_COLS &&
                        fakeCounter4 >= 0 && fakeCounter4 < MAX_LINES) {
                        
                        if (wsB[lineCounter][fakeCounter1] > wsB[lineCounter][charCounter] &&
                            wsB[lineCounter][fakeCounter3] > wsB[lineCounter][charCounter] &&
                            wsB[fakeCounter2][charCounter] > wsB[lineCounter][charCounter] &&
                            wsB[fakeCounter4][charCounter] > wsB[lineCounter][charCounter]) {
                            
                            temp = Character.getNumericValue(wsB[lineCounter][charCounter]);
                            total += temp + 1;
                        }
                    }
                    charCounter++;
                }
                lineCounter++;
            }
            
            System.out.println("Count: " + total);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}