import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main8 {
    private static final int SIZE = 102;
    private static char[][] wsB = new char[SIZE][SIZE];
    private static int total = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("9.dat"))) {
            String line;
            int lineCounter = 0;
            
            while ((line = br.readLine()) != null && lineCounter < SIZE) {
                for (int i = 0; i < line.length() && i < SIZE; i++) {
                    wsB[lineCounter][i] = line.charAt(i);
                }
                lineCounter++;
            }

            // Process the grid to find low points
            for (int i = 1; i < lineCounter - 1; i++) {
                for (int j = 1; j < SIZE - 1; j++) {
                    char current = wsB[i][j];
                    if (current < wsB[i][j-1] && 
                        current < wsB[i][j+1] && 
                        current < wsB[i-1][j] && 
                        current < wsB[i+1][j]) {
                        total += (current - '0') + 1;
                    }
                }
            }

            System.out.println("Count: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}