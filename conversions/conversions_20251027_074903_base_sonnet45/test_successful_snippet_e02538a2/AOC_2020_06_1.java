import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2020_06_1 {
    private int[] wsGroupAnswers = new int[26];
    private int groupTotal = 0;
    private int total = 0;

    public static void main(String[] args) {
        AOC_2020_06_1 program = new AOC_2020_06_1();
        program.run();
    }

    public void run() {
        nextGroup();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("d6.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        nextGroup();
        System.out.println(total);
    }

    private void processRecord(String inputRecord) {
        int recLen = inputRecord.length();
        
        if (recLen == 0) {
            nextGroup();
        } else {
            processRow(inputRecord, recLen);
        }
    }

    private void nextGroup() {
        total += groupTotal;
        
        for (int i = 0; i < 26; i++) {
            wsGroupAnswers[i] = 0;
        }
        
        groupTotal = 0;
    }

    private void processRow(String inputRecord, int recLen) {
        for (int i = 0; i < recLen; i++) {
            char wsChar = inputRecord.charAt(i);
            int c = (int) wsChar;
            int index = c - 97;
            
            if (index >= 0 && index < 26 && wsGroupAnswers[index] == 0) {
                wsGroupAnswers[index] = 1;
                groupTotal++;
            }
        }
    }
}