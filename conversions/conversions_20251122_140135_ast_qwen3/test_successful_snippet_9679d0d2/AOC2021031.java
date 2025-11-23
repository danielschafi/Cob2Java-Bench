import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021031 {
    private static final String INPUT_FILE = "d03.input";
    private static final int N = 12;
    private static final int M = 1000;
    
    public static void main(String[] args) {
        int[] wsCounts1 = new int[N];
        int wsGammaDec = 0;
        int wsEpsilonDec = 0;
        int wsResult;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < N; i++) {
                    if (line.charAt(i) == '1') {
                        wsCounts1[i]++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < N; i++) {
            wsGammaDec *= 2;
            wsEpsilonDec *= 2;
            if (wsCounts1[i] > M / 2) {
                wsGammaDec += 1;
            } else {
                wsEpsilonDec += 1;
            }
        }
        
        wsResult = wsGammaDec * wsEpsilonDec;
        System.out.println(wsResult);
    }
}