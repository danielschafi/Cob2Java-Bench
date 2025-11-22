import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2020_06_2 {
    private int[] wsGroupAnswers = new int[26];
    private int groupSize = 0;
    private int groupTotal = 0;
    private int total = 0;

    public static void main(String[] args) {
        AOC_2020_06_2 program = new AOC_2020_06_2();
        program.run();
    }

    public void run() {
        initVariables();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("d6.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
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

    private void initVariables() {
        for (int i = 0; i < 26; i++) {
            wsGroupAnswers[i] = 0;
        }
        groupSize = 0;
        groupTotal = 0;
    }

    private void nextGroup() {
        if (groupSize > 0) {
            tallyGroupTotal();
        }
        total += groupTotal;
        initVariables();
    }

    private void processRow(String inputRecord, int recLen) {
        groupSize++;
        for (int i = 0; i < recLen; i++) {
            char wsChar = inputRecord.charAt(i);
            int c = (int) wsChar;
            int x = wsGroupAnswers[c - 97] + 1;
            wsGroupAnswers[c - 97] = x;
        }
    }

    private void tallyGroupTotal() {
        for (int i = 0; i < 26; i++) {
            if (wsGroupAnswers[i] == groupSize) {
                groupTotal++;
            }
        }
    }
}