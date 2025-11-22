import java.io.*;
import java.nio.file.*;

public class AOC202103_1 {
    private static final String INPUT_FILE = "d03.input";
    private static final int N = 12;
    private static final int M = 1000;
    
    private int[] wsCounts1;
    private long wsGammaDec;
    private long wsEpsilonDec;
    
    public AOC202103_1() {
        wsCounts1 = new int[N + 1];
        wsGammaDec = 0;
        wsEpsilonDec = 0;
    }
    
    public static void main(String[] args) {
        AOC202103_1 program = new AOC202103_1();
        program.run();
    }
    
    private void run() {
        try {
            readInput();
            computeGammaDec();
            long result = wsGammaDec * wsEpsilonDec;
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readInput() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        }
    }
    
    private void processRecord(String inputRecord) {
        for (int i = 1; i <= N; i++) {
            if (inputRecord.charAt(i - 1) == '1') {
                wsCounts1[i]++;
            }
        }
    }
    
    private void computeGammaDec() {
        for (int i = 1; i <= N; i++) {
            wsGammaDec = wsGammaDec * 2;
            wsEpsilonDec = wsEpsilonDec * 2;
            
            if (wsCounts1[i] > M / 2) {
                wsGammaDec = wsGammaDec + 1;
            } else {
                wsEpsilonDec = wsEpsilonDec + 1;
            }
        }
    }
}