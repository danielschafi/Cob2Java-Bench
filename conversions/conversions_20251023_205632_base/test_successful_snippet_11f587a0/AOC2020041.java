import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020041 {
    private static final int MAX_RECORD_LENGTH = 99;
    private static final int MAX_ROWS = 8;
    private static final int REQUIRED_FIELDS = 7;

    private int fileStatus;
    private int recLen;
    private String[] wsRow = new String[MAX_ROWS];
    private char wsChar;
    private int correctPassports;
    private int foundFields;
    private int stringPtr;
    private int i;

    public static void main(String[] args) {
        AOC2020041 program = new AOC2020041();
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen == 0) {
                    nextPassport();
                } else {
                    processRecord(line);
                }
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String line) {
        if (recLen == 0) {
            nextPassport();
        } else {
            processRow(line);
        }
    }

    private void nextPassport() {
        if (foundFields == REQUIRED_FIELDS) {
            correctPassports++;
        }
        foundFields = 0;
    }

    private void processRow(String line) {
        stringPtr = 0;
        for (i = 0; i < MAX_ROWS; i++) {
            int spaceIndex = line.indexOf(' ', stringPtr);
            if (spaceIndex == -1) {
                wsRow[i] = line.substring(stringPtr);
                stringPtr = recLen;
            } else {
                wsRow[i] = line.substring(stringPtr, spaceIndex);
                stringPtr = spaceIndex + 1;
            }
            if (stringPtr >= recLen) {
                break;
            }
        }

        for (i = 0; i <= MAX_ROWS && wsRow[i] != null; i++) {
            wsChar = wsRow[i].charAt(0);
            if (wsChar != 'c' && wsChar != ' ') {
                foundFields++;
            }
        }
    }
}