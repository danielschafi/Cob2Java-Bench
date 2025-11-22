import java.io.*;
import java.util.*;

public class RunStddev {
    private static final int MAX_SIZE = 100;
    private static int tbSize = 0;
    private static double[] tbTable = new double[MAX_SIZE];
    private static double stddev;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int inpFld = Integer.parseInt(line.trim());
                tbSize++;
                tbTable[tbSize - 1] = inpFld;
                calculateStdDev();
                System.out.println("inp=" + inpFld + " stddev=" + String.format("%.4f", stddev));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculateStdDev() {
        double sum = 0.0;
        for (int i = 0; i < tbSize; i++) {
            sum += tbTable[i];
        }
        double avg = sum / tbSize;
        double sumSq = 0.0;
        for (int i = 0; i < tbSize; i++) {
            double diff = tbTable[i] - avg;
            sumSq += diff * diff;
        }
        stddev = Math.sqrt(sumSq / tbSize);
    }
}