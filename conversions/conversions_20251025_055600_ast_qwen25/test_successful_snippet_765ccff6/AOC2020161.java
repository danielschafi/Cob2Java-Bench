import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020161 {
    private static final int MAX_RECORDS = 20;
    private static int wsResult = 0;
    private static int recLen;
    private static int fileStatus = 0;
    private static int wsSection = 1;
    private static final int[] wsMin1 = new int[MAX_RECORDS];
    private static final int[] wsMax1 = new int[MAX_RECORDS];
    private static final int[] wsMin2 = new int[MAX_RECORDS];
    private static final int[] wsMax2 = new int[MAX_RECORDS];
    private static final String[] wsTmp = new String[6];
    private static final int[] wsRow = new int[MAX_RECORDS];
    private static int wsVal;
    private static int wsValCorrect;
    private static int i = 0;
    private static int j;
    private static int k;
    private static int n = 0;
    private static int stringPtr = 1;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d16.input"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                recLen = inputRecord.length();
                processRecord(inputRecord);
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
            switch (wsSection) {
                case 1:
                    processRecordType1(inputRecord);
                    break;
                case 2:
                    processRecordType2(inputRecord);
                    break;
                case 3:
                    processRecordType3(inputRecord);
                    break;
            }
        }
    }

    private static void processRecordType1(String inputRecord) {
        String[] parts = inputRecord.split("[-: or ]");
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1]);
        wsMax1[n - 1] = Integer.parseInt(parts[2]);
        wsMin2[n - 1] = Integer.parseInt(parts[3]);
        wsMax2[n - 1] = Integer.parseInt(parts[4]);
    }

    private static void processRecordType2(String inputRecord) {
        // Do nothing for type 2 records
    }

    private static void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) return;
        stringPtr = 0;
        String[] parts = inputRecord.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(parts[j].trim());
        }
        for (j = 0; j < n; j++) {
            wsVal = wsRow[j];
            checkVal();
        }
    }

    private static void checkVal() {
        wsValCorrect = 0;
        for (k = 0; k < n; k++) {
            if ((wsVal >= wsMin1[k] && wsVal <= wsMax1[k]) || (wsVal >= wsMin2[k] && wsVal <= wsMax2[k])) {
                wsValCorrect = 1;
            }
        }
        if (wsValCorrect == 0) {
            wsResult += wsVal;
        }
    }
}