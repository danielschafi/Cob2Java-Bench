import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202016 {
    private int wsResult = 0;
    private int fileStatus = 0;
    private int wsSection = 1;
    private int[] wsMin1 = new int[20];
    private int[] wsMax1 = new int[20];
    private int[] wsMin2 = new int[20];
    private int[] wsMax2 = new int[20];
    private int[] wsRow = new int[20];
    private int n = 0;
    private int i = 0;

    public static void main(String[] args) {
        AOC202016 program = new AOC202016();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            fileStatus = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }

    private void processRecord(String inputRecord) {
        int recLen = inputRecord.length();
        
        if (recLen == 0) {
            wsSection++;
        } else {
            if (wsSection == 1) {
                processRecordType1(inputRecord);
            } else if (wsSection == 2) {
                processRecordType2(inputRecord);
            } else {
                processRecordType3(inputRecord);
            }
        }
    }

    private void processRecordType1(String inputRecord) {
        String[] parts = inputRecord.split(":");
        if (parts.length < 2) return;
        
        String ranges = parts[1].trim();
        String[] rangeParts = ranges.split(" or ");
        
        if (rangeParts.length < 2) return;
        
        String[] range1 = rangeParts[0].trim().split("-");
        String[] range2 = rangeParts[1].trim().split("-");
        
        n++;
        wsMin1[n - 1] = Integer.parseInt(range1[0].trim());
        wsMax1[n - 1] = Integer.parseInt(range1[1].trim());
        wsMin2[n - 1] = Integer.parseInt(range2[0].trim());
        wsMax2[n - 1] = Integer.parseInt(range2[1].trim());
    }

    private void processRecordType2(String inputRecord) {
        // Type 2 records are skipped
    }

    private void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        
        String[] values = inputRecord.split(",");
        for (int j = 0; j < n && j < values.length; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        
        for (int j = 0; j < n; j++) {
            int wsVal = wsRow[j];
            checkVal(wsVal);
        }
    }

    private void checkVal(int wsVal) {
        int wsValCorrect = 0;
        
        for (int k = 0; k < n; k++) {
            if ((wsVal >= wsMin1[k] && wsVal <= wsMax1[k]) ||
                (wsVal >= wsMin2[k] && wsVal <= wsMax2[k])) {
                wsValCorrect = 1;
                break;
            }
        }
        
        if (wsValCorrect == 0) {
            wsResult += wsVal;
        }
    }
}