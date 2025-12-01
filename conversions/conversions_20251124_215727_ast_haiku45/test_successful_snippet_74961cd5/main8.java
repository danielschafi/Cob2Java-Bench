import java.io.*;
import java.nio.file.*;
import java.util.*;

public class main8 {
    private static char[][] wsB = new char[102][102];
    private static String currLine = "";
    private static int maxBoarder = 10;
    private static int lineCounter = 0;
    private static int charCounter = 0;
    private static int fakeCounter1 = 0;
    private static int fakeCounter2 = 0;
    private static int fakeCounter3 = 0;
    private static int fakeCounter4 = 0;
    private static int temp = 0;
    private static int temp1 = 0;
    private static int temp2 = 0;
    private static int temp3 = 0;
    private static int temp4 = 0;
    private static long total = 0;
    private static int endOfFile = 0;

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("9.dat"));
            
            if (lines.isEmpty()) {
                endOfFile = 1;
            }
            
            if (endOfFile == 1) {
                System.exit(0);
            }
            
            endOfFile = 0;
            
            lineCounter = 2;
            int lineIndex = 0;
            
            while (endOfFile == 0 && lineIndex < lines.size()) {
                currLine = lines.get(lineIndex);
                charCounter = 2;
                
                while (charCounter < 102) {
                    fakeCounter1 = charCounter - 1;
                    if (fakeCounter1 < currLine.length()) {
                        wsB[lineCounter][charCounter] = currLine.charAt(fakeCounter1);
                    } else {
                        wsB[lineCounter][charCounter] = ' ';
                    }
                    temp = wsB[lineCounter][charCounter];
                    wsB[lineCounter][charCounter] = (char) temp;
                    charCounter = charCounter + 1;
                }
                
                lineCounter = lineCounter + 1;
                lineIndex++;
                
                if (lineIndex >= lines.size()) {
                    endOfFile = 1;
                }
            }
            
            lineCounter = 2;
            while (lineCounter < 102) {
                charCounter = 2;
                while (charCounter < 102) {
                    fakeCounter1 = charCounter - 1;
                    fakeCounter2 = lineCounter - 1;
                    fakeCounter3 = charCounter + 1;
                    fakeCounter4 = lineCounter + 1;
                    
                    if (wsB[lineCounter][fakeCounter1] > wsB[lineCounter][charCounter] &&
                        wsB[lineCounter][fakeCounter3] > wsB[lineCounter][charCounter] &&
                        wsB[fakeCounter2][charCounter] > wsB[lineCounter][charCounter] &&
                        wsB[fakeCounter4][charCounter] > wsB[lineCounter][charCounter]) {
                        
                        temp = wsB[lineCounter][charCounter];
                        total = total + temp + 1;
                    }
                    
                    charCounter = charCounter + 1;
                }
                
                lineCounter = lineCounter + 1;
            }
            
            System.out.println("Count: " + total);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}