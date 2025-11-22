import java.io.*;
import java.util.*;

public class AOC202107Part2 {
    private static final int MAX_N = 1000;
    private int[] wsArray = new int[MAX_N];
    private int n = 0;
    private long wsMean = 0;
    private long wsSum = 0;
    private long wsAns1 = 0;
    private long wsAns2 = 0;
    private long wsAns3 = 0;
    private long wsResult = 0;
    private long x = 0;

    public static void main(String[] args) {
        AOC202107Part2 program = new AOC202107Part2();
        program.run();
    }

    private void run() {
        readInput();
        compute();
        System.out.println(wsResult);
    }

    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d07.input"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < MAX_N) {
                wsArray[i] = Integer.parseInt(line.trim());
                i++;
            }
            n = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compute() {
        for (int i = 0; i < n; i++) {
            wsSum += wsArray[i];
        }
        wsMean = Math.round((double) wsSum / n);

        for (int i = 0; i < n; i++) {
            x = Math.abs(wsMean - 1 - wsArray[i]);
            wsAns1 += (x * (x + 1)) / 2;

            x = Math.abs(wsMean - wsArray[i]);
            wsAns2 += (x * (x + 1)) / 2;

            x = Math.abs(wsMean + 1 - wsArray[i]);
            wsAns3 += (x * (x + 1)) / 2;
        }

        wsResult = Math.min(wsAns1, Math.min(wsAns2, wsAns3));
    }
}