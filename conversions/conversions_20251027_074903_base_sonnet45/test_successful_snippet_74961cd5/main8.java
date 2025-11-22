import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main8 {
    private static char[][] state = new char[102][102];
    private static int maxBoarder = 10;
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
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) {
                state[i][j] = '0';
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("9.dat"))) {
            String currLine;
            lineCounter = 2;

            while ((currLine = br.readLine()) != null) {
                charCounter = 2;
                while (charCounter < 102) {
                    fakeCounter1 = charCounter - 1;
                    if (fakeCounter1 - 1 < currLine.length()) {
                        state[lineCounter - 1][charCounter - 1] = currLine.charAt(fakeCounter1 - 1);
                        temp = Character.getNumericValue(state[lineCounter - 1][charCounter - 1]);
                    }
                    charCounter++;
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lineCounter = 2;
        while (lineCounter < 102) {
            charCounter = 2;
            while (charCounter < 102) {
                fakeCounter1 = charCounter - 1;
                fakeCounter2 = lineCounter - 1;
                fakeCounter3 = charCounter + 1;
                fakeCounter4 = lineCounter + 1;

                char current = state[lineCounter - 1][charCounter - 1];
                char left = state[lineCounter - 1][fakeCounter1 - 1];
                char right = state[lineCounter - 1][fakeCounter3 - 1];
                char up = state[fakeCounter2 - 1][charCounter - 1];
                char down = state[fakeCounter4 - 1][charCounter - 1];

                if (left > current && right > current && up > current && down > current) {
                    temp = Character.getNumericValue(current);
                    total = total + temp + 1;
                }
                charCounter++;
            }
            lineCounter++;
        }

        System.out.println("Count: " + total);
    }
}