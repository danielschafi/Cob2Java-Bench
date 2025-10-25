import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main8 {
    private static final int MAX_BORDER = 10;
    private static final int MAX_LINES = 102;
    private static final int MAX_CHARS = 102;
    private static char[][] state = new char[MAX_LINES][MAX_CHARS];
    private static int lineCounter;
    private static int charCounter;
    private static int fakeCounter1;
    private static int fakeCounter2;
    private static int fakeCounter3;
    private static int fakeCounter4;
    private static int temp;
    private static int total;
    private static boolean endOfFile;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("9.dat"))) {
            String currLine;
            endOfFile = false;

            currLine = reader.readLine();
            if (currLine == null) {
                endOfFile = true;
            }

            if (endOfFile) {
                return;
            }

            endOfFile = false;

            lineCounter = 2;
            while (!endOfFile) {
                charCounter = 2;
                while (charCounter < MAX_CHARS) {
                    fakeCounter1 = charCounter - 1;
                    state[lineCounter][charCounter] = currLine.charAt(fakeCounter1);
                    charCounter++;
                }

                lineCounter++;
                currLine = reader.readLine();
                if (currLine == null) {
                    endOfFile = true;
                }
            }

            lineCounter = 2;
            while (lineCounter < MAX_LINES) {
                charCounter = 2;
                while (charCounter < MAX_CHARS) {
                    fakeCounter1 = charCounter - 1;
                    fakeCounter2 = lineCounter - 1;
                    fakeCounter3 = charCounter + 1;
                    fakeCounter4 = lineCounter + 1;
                    if (state[lineCounter][fakeCounter1] > state[lineCounter][charCounter] &&
                        state[lineCounter][fakeCounter3] > state[lineCounter][charCounter] &&
                        state[fakeCounter2][charCounter] > state[lineCounter][charCounter] &&
                        state[fakeCounter4][charCounter] > state[lineCounter][charCounter]) {
                        temp = state[lineCounter][charCounter] - '0';
                        total += temp + 1;
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