import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOCDay3 {
    private static final int N = 12;
    private static final int M = 1000;
    private static int[] wsCounts1 = new int[12];
    private static int wsGammaDec = 0;
    private static int wsEpsilonDec = 0;
    private static long wsResult = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d03.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        computeGammaDec();
        wsResult = (long) wsGammaDec * wsEpsilonDec;
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
        for (int i = 0; i < N; i++) {
            if (inputRecord.charAt(i) == '1') {
                wsCounts1[i]++;
            }
        }
    }

    private static void computeGammaDec() {
        for (int i = 0; i < N; i++) {
            wsGammaDec *= 2;
            wsEpsilonDec *= 2;
            if (wsCounts1[i] > M / 2) {
                wsGammaDec += 1;
            } else {
                wsEpsilonDec += 1;
            }
        }
    }
}