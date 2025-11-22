import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main8 {
    public static void main(String[] args) {
        char[][] WS_B = new char[102][102];
        int MaxBoarder = 10;
        int lineCounter = 0;
        int charCounter = 0;
        int fakeCounter1 = 0;
        int fakeCounter2 = 0;
        int fakeCounter3 = 0;
        int fakeCounter4 = 0;
        int temp = 0;
        int Total = 0;
        boolean END_OF_FILE = false;

        try (BufferedReader br = new BufferedReader(new FileReader("9.dat"))) {
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lineCounter++;
                if (lineCounter >= 2 && lineCounter < 102) {
                    for (charCounter = 2; charCounter < 102; charCounter++) {
                        fakeCounter1 = charCounter - 1;
                        WS_B[lineCounter][charCounter] = currLine.charAt(fakeCounter1);
                        temp = WS_B[lineCounter][charCounter];
                        WS_B[lineCounter][charCounter] = (char) temp;
                    }
                }
            }
        } catch (IOException e) {
            END_OF_FILE = true;
        }

        if (!END_OF_FILE) {
            for (lineCounter = 2; lineCounter < 102; lineCounter++) {
                for (charCounter = 2; charCounter < 102; charCounter++) {
                    fakeCounter1 = charCounter - 1;
                    fakeCounter2 = lineCounter - 1;
                    fakeCounter3 = charCounter + 1;
                    fakeCounter4 = lineCounter + 1;
                    if (WS_B[lineCounter][fakeCounter1] > WS_B[lineCounter][charCounter] &&
                        WS_B[lineCounter][fakeCounter3] > WS_B[lineCounter][charCounter] &&
                        WS_B[fakeCounter2][charCounter] > WS_B[lineCounter][charCounter] &&
                        WS_B[fakeCounter4][charCounter] > WS_B[lineCounter][charCounter]) {
                        temp = WS_B[lineCounter][charCounter];
                        Total = Total + temp + 1;
                    }
                }
            }
        }

        System.out.println("Count: " + Total);
    }
}