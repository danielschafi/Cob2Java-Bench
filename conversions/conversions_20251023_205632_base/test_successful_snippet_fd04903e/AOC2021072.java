import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021072 {
    private static final int N = 1000;
    private int[] wsArray = new int[N];
    private long wsSum = 0;
    private double wsMean = 0;
    private long wsAns1 = 0;
    private long wsAns2 = 0;
    private long wsAns3 = 0;
    private long wsResult = 0;
    private int i = 0;

    public static void main(String[] args) {
        AOC2021072 program = new AOC2021072();
        program.execute();
    }

    private void execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d07.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compute();
        System.out.println(wsResult);
    }

    private void processRecord(String inputRecord) {
        wsArray[i] = Integer.parseInt(inputRecord);
        i++;
    }

    private void compute() {
        for (int j = 0; j < i; j++) {
            wsSum += wsArray[j];
        }
        wsMean = Math.round((double) wsSum / i);

        for (int j = 0; j < i; j++) {
            long x = Math.abs(wsMean - 1 - wsArray[j]);
            wsAns1 += (x * (x + 1)) / 2;
            x = Math.abs(wsMean - wsArray[j]);
            wsAns2 += (x * (x + 1)) / 2;
            x = Math.abs(wsMean + 1 - wsArray[j]);
            wsAns3 += (x * (x + 1)) / 2;
        }
        wsResult = Math.min(wsAns1, Math.min(wsAns2, wsAns3));
    }
}