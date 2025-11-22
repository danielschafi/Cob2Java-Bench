import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AoC20200601 {
    private static final int ALPHABET_SIZE = 26;
    private static final int ASCII_LOWER_A = 97;
    
    private static int total = 0;
    private static int groupTotal = 0;
    private static int[] groupAnswers = new int[ALPHABET_SIZE];
    
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
    
    private static void nextGroup() {
        total += groupTotal;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            groupAnswers[i] = 0;
        }
        groupTotal = 0;
    }
    
    private static void processRow(String row) {
        for (int i = 0; i < row.length(); i++) {
            char ch = row.charAt(i);
            int index = (int) ch - ASCII_LOWER_A;
            if (groupAnswers[index] == 0) {
                groupAnswers[index] = 1;
                groupTotal++;
            }
        }
    }
}