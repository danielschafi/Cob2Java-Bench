import java.io.*;
import java.nio.file.*;

public class AOC202103 {
    private static final int N = 12;
    private static final int M = 1000;
    
    private int fileStatus = 0;
    private long wsGammaDec = 0;
    private long wsEpsilonDec = 0;
    private int[] wsCounts1 = new int[N + 1];
    private long wsResult = 0;
    private String inputRecord = "";
    
    public static void main(String[] args) {
        AOC202103 program = new AOC202103();
        program.run();
    }
    
    private void run() {
        try {
            openInputFile();
            while (fileStatus == 0) {
                readFile();
            }
            closeInputFile();
            computeGammaDec();
            wsResult = wsGammaDec * wsEpsilonDec;
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void openInputFile() throws IOException {
    }
    
    private void closeInputFile() throws IOException {
    }
    
    private void readFile() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d03.input"));
            String line;
            while ((line = reader.readLine()) != null) {
                inputRecord = line;
                processRecord();
            }
            reader.close();
            fileStatus = 1;
        } catch (FileNotFoundException e) {
            fileStatus = 1;
        }
    }
    
    private void processRecord() {
        for (int i = 1; i <= N; i++) {
            if (i <= inputRecord.length()) {
                if (inputRecord.charAt(i - 1) == '1') {
                    wsCounts1[i]++;
                }
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