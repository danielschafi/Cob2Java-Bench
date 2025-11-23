import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020161 {
    private static int wsResult = 0;
    private static int recLen = 0;
    private static int fileStatus = 0;
    private static int wsSection = 1;
    private static int[] wsMin1 = new int[20];
    private static int[] wsMax1 = new int[20];
    private static int[] wsMin2 = new int[20];
    private static int[] wsMax2 = new int[20];
    private static String wsTmp1 = "";
    private static String wsTmp2 = "";
    private static String wsTmp3 = "";
    private static String wsTmp4 = "";
    private static String wsTmp5 = "";
    private static String wsTmp6 = "";
    private static int[] wsRow = new int[20];
    private static int wsVal = 0;
    private static int wsValCorrect = 0;
    private static int i = 0;
    private static int j = 0;
    private static int k = 0;
    private static int n = 0;
    private static int stringPtr = 1;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
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

    private static void processRecordType1(String inputRecord) {
        // Simulate UNSTRING operation
        String[] parts = inputRecord.split("[-:\\s]+");
        if (parts.length >= 6) {
            n++;
            wsMin1[n - 1] = Integer.parseInt(parts[1]);
            wsMax1[n - 1] = Integer.parseInt(parts[2]);
            wsMin2[n - 1] = Integer.parseInt(parts[3]);
            wsMax2[n - 1] = Integer.parseInt(parts[4]);
        }
    }

    private static void processRecordType2(String inputRecord) {
        // Type 2 processing - currently empty
    }

    private static void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        stringPtr = 1;
        
        // Split by comma and populate wsRow
        String[] values = inputRecord.split(",");
        for (j = 0; j < Math.min(values.length, 20); j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        
        // Check each value
        for (j = 0; j < Math.min(values.length, 20); j++) {
            wsVal = wsRow[j];
            checkVal();
        }
    }

    private static void checkVal() {
        wsValCorrect = 0;
        for (k = 0; k < n; k++) {
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