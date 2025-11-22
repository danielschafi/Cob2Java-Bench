import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020161 {
    private static int WS_RESULT = 0;
    private static int REC_LEN;
    private static int FILE_STATUS = 0;
    private static int WS_SECTION = 1;
    private static final int[] WS_MIN_1 = new int[20];
    private static final int[] WS_MAX_1 = new int[20];
    private static final int[] WS_MIN_2 = new int[20];
    private static final int[] WS_MAX_2 = new int[20];
    private static final String[] WS_TMP = new String[6];
    private static final int[] WS_ROW = new int[20];
    private static int WS_VAL;
    private static int WS_VAL_CORRECT;
    private static int I = 0;
    private static int J;
    private static int K;
    private static int N = 0;
    private static int STRING_PTR = 1;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                REC_LEN = inputRecord.length();
                if (REC_LEN == 0) {
                    WS_SECTION++;
                } else {
                    if (WS_SECTION == 1) {
                        processRecordType1(inputRecord);
                    } else if (WS_SECTION == 2) {
                        // processRecordType2(inputRecord);
                    } else {
                        processRecordType3(inputRecord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(WS_RESULT);
    }

    private static void processRecordType1(String inputRecord) {
        String[] parts = inputRecord.split("[-: or ]");
        N++;
        WS_MIN_1[N - 1] = Integer.parseInt(parts[1]);
        WS_MAX_1[N - 1] = Integer.parseInt(parts[2]);
        WS_MIN_2[N - 1] = Integer.parseInt(parts[3]);
        WS_MAX_2[N - 1] = Integer.parseInt(parts[4]);
    }

    private static void processRecordType3(String inputRecord) {
        I++;
        if (I == 1) {
            return;
        }
        STRING_PTR = 0;
        String[] parts = inputRecord.split(",");
        for (J = 0; J < N; J++) {
            WS_ROW[J] = Integer.parseInt(parts[J]);
        }
        for (J = 0; J < N; J++) {
            WS_VAL = WS_ROW[J];
            checkVal();
        }
    }

    private static void checkVal() {
        WS_VAL_CORRECT = 0;
        for (K = 0; K < N; K++) {
            if ((WS_VAL >= WS_MIN_1[K] && WS_VAL <= WS_MAX_1[K]) ||
                (WS_VAL >= WS_MIN_2[K] && WS_VAL <= WS_MAX_2[K])) {
                WS_VAL_CORRECT = 1;
            }
        }
        if (WS_VAL_CORRECT == 0) {
            WS_RESULT += WS_VAL;
        }
    }
}