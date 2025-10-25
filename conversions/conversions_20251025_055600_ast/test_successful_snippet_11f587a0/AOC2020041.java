import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020041 {
    private static int fileStatus = 0;
    private static int recLen;
    private static String[] wsRow = new String[8];
    private static char wsChar;
    private static int correctPassports = 0;
    private static int foundFields = 0;
    private static int stringPtr;
    private static int i;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d4.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                recLen = inputRecord.length();
                processRecord(inputRecord);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        if (recLen == 0) {
            nextPassport();
        } else {
            processRow(inputRecord);
        }
    }

    private static void nextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }

    private static void processRow(String inputRecord) {
        stringPtr = 0;
        for (i = 0; i < 8; i++) {
            int spaceIndex = inputRecord.indexOf(' ', stringPtr);
            if (spaceIndex == -1) {
                wsRow[i] = inputRecord.substring(stringPtr);
                stringPtr = inputRecord.length();
            } else {
                wsRow[i] = inputRecord.substring(stringPtr, spaceIndex);
                stringPtr = spaceIndex + 1;
            }
        }

        for (i = 0; i < 8; i++) {
            if (wsRow[i].length() > 0) {
                wsChar = wsRow[i].charAt(0);
                if (wsChar != 'c' && wsChar != ' ') {
                    foundFields++;
                }
            }
        }
    }
}