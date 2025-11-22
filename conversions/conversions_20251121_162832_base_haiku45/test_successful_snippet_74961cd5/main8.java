import java.io.*;
import java.nio.file.*;

public class main8 {
    static class State {
        char[][] WS_B = new char[102][102];
        
        State() {
            for (int i = 0; i < 102; i++) {
                for (int j = 0; j < 102; j++) {
                    WS_B[i][j] = '0';
                }
            }
        }
    }
    
    public static void main(String[] args) {
        State state = new State();
        String currLine = "";
        int lineCounter = 0;
        int charCounter = 0;
        int fakeCounter1 = 0;
        int fakeCounter2 = 0;
        int fakeCounter3 = 0;
        int fakeCounter4 = 0;
        int temp = 0;
        long Total = 0;
        int endOfFile = 0;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("9.dat"));
            
            currLine = reader.readLine();
            if (currLine == null) {
                endOfFile = 1;
            }
            
            if (endOfFile == 1) {
                reader.close();
            }
            
            endOfFile = 0;
            
            lineCounter = 2;
            while (endOfFile != 1) {
                charCounter = 2;
                while (charCounter < 102) {
                    fakeCounter1 = charCounter - 1;
                    if (fakeCounter1 < currLine.length()) {
                        state.WS_B[lineCounter][charCounter] = currLine.charAt(fakeCounter1);
                    }
                    charCounter = charCounter + 1;
                }
                
                lineCounter = lineCounter + 1;
                currLine = reader.readLine();
                if (currLine == null) {
                    endOfFile = 1;
                }
            }
            
            reader.close();
            
            lineCounter = 2;
            while (lineCounter < 102) {
                charCounter = 2;
                while (charCounter < 102) {
                    fakeCounter1 = charCounter - 1;
                    fakeCounter2 = lineCounter - 1;
                    fakeCounter3 = charCounter + 1;
                    fakeCounter4 = lineCounter + 1;
                    
                    char current = state.WS_B[lineCounter][charCounter];
                    char left = state.WS_B[lineCounter][fakeCounter1];
                    char right = state.WS_B[lineCounter][fakeCounter3];
                    char up = state.WS_B[fakeCounter2][charCounter];
                    char down = state.WS_B[fakeCounter4][charCounter];
                    
                    if (left > current && right > current && up > current && down > current) {
                        temp = current - '0';
                        Total = Total + temp + 1;
                    }
                    
                    charCounter = charCounter + 1;
                }
                
                lineCounter = lineCounter + 1;
            }
            
            System.out.println("Count: " + Total);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}