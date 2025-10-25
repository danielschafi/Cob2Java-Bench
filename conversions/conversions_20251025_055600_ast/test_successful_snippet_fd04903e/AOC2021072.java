import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021072 {
    private static final int N = 1000;
    private static int[] wsArray = new int[N];
    private static long wsMean;
    private static long wsSum;
    private static long wsAns1;
    private static long wsAns2;
    private static long wsAns3;
    private static long wsResult;
    private static long x;

    public static void main(String[] args) {
        int fileStatus = 0;
        int i = 1;

        try (BufferedReader br = new BufferedReader(new FileReader("d07.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null && fileStatus == 0) {
                wsArray[i - 1] = Integer.parseInt(inputRecord);
                i++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        for (int j = 1; j < i; j++) {
            wsSum += wsArray[j - 1];
        }
        wsMean = Math.round((double) wsSum / (i - 1));

        for (int j = 1; j < i; j++) {
            x = Math.abs(wsMean - 1 - wsArray[j - 1]);
            wsAns1 += (x * (x + 1)) / 2;
            x = Math.abs(wsMean - wsArray[j - 1]);
            wsAns2 += (x * (x + 1)) / 2;
            x = Math.abs(wsMean + 1 - wsArray[j - 1]);
            wsAns3 += (x * (x + 1)) / 2;
        }
        wsResult = Math.min(wsAns1, Math.min(wsAns2, wsAns3));

        System.out.println(wsResult);
    }
}