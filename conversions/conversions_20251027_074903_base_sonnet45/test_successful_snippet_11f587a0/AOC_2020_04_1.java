import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2020_04_1 {
    private int correctPassports = 0;
    private int foundFields = 0;

    public static void main(String[] args) {
        AOC_2020_04_1 program = new AOC_2020_04_1();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        int recLen = inputRecord.length();
        if (recLen == 0) {
            nextPassport();
        } else {
            processRow(inputRecord);
        }
    }

    private void nextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }

    private void processRow(String inputRecord) {
        String[] tokens = inputRecord.split(" ", -1);
        
        for (int i = 0; i < tokens.length && i < 8; i++) {
            if (tokens[i].length() > 0) {
                char firstChar = tokens[i].charAt(0);
                if (firstChar != 'c' && firstChar != ' ') {
                    foundFields++;
                }
            }
        }
    }
}