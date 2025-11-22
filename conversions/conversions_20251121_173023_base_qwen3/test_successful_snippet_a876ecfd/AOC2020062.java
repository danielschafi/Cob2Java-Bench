import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020062 {
    private static final int ALPHABET_SIZE = 26;
    private static final int ASCII_A = 97;
    
    private static int[] wsGroupAnswers = new int[ALPHABET_SIZE];
    private static int groupSize = 0;
    private static int groupTotal = 0;
    private static int total = 0;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d6.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    nextGroup();
                } else {
                    processRow(line);
                }
            }
            nextGroup();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void initVariables() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            wsGroupAnswers[i] = 0;
        }
        groupSize = 0;
        groupTotal = 0;
    }
    
    private static void nextGroup() {
        if (groupSize > 0) {
            tallyGroupTotal();
        }
        total += groupTotal;
        initVariables();
    }
    
    private static void processRow(String inputRecord) {
        groupSize++;
        for (int i = 0; i < inputRecord.length(); i++) {
            char ch = inputRecord.charAt(i);
            int c = (int) ch;
            int x = wsGroupAnswers[c - ASCII_A] + 1;
            wsGroupAnswers[c - ASCII_A] = x;
        }
    }
    
    private static void tallyGroupTotal() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (wsGroupAnswers[i] == groupSize) {
                groupTotal++;
            }
        }
    }
}