import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020162 {
    private static final int MAX_RULES = 20;
    private static final int MAX_TICKETS = 256;
    private static final int MAX_FIELDS = 20;

    private long wsResult = 0;
    private int recLen = 0;
    private int fileStatus = 0;
    private int wsSection = 1;
    private int[] wsMin1 = new int[MAX_RULES];
    private int[] wsMax1 = new int[MAX_RULES];
    private int[] wsMin2 = new int[MAX_RULES];
    private int[] wsMax2 = new int[MAX_RULES];
    private String wsTmp1 = new String(new char[32]);
    private String wsTmp2 = new String(new char[32]);
    private String wsTmp3 = new String(new char[32]);
    private String wsTmp4 = new String(new char[32]);
    private String wsTmp5 = new String(new char[32]);
    private String wsTmp6 = new String(new char[32]);
    private String[][] wsTickets = new String[MAX_TICKETS][MAX_FIELDS];
    private String wsMyTicket = new String(new char[99]);
    private int[] wsRow = new int[MAX_FIELDS];
    private int wsVal = 0;
    private int wsValCorrect = 0;
    private int wsRowCorrect = 0;
    private int[] wsRulesMet = new int[6];
    private int wsRuleMet = 0;

    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int i1 = 1;
    private int j1 = 1;
    private int k1 = 1;
    private int l1 = 1;
    private int n = 0;
    private int stringPtr = 1;

    public static void main(String[] args) {
        AOC2020162 program = new AOC2020162();
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkRules();
        finish();
    }

    private void processRecord(String record) {
        if (recLen == 0) {
            wsSection++;
        } else {
            switch (wsSection) {
                case 1:
                    processRecordType1(record);
                    break;
                case 2:
                    processRecordType2(record);
                    break;
                case 3:
                    processRecordType3(record);
                    break;
            }
        }
    }

    private void processRecordType1(String record) {
        String[] parts = record.split("[-: or ]");
        wsMin1[n] = Integer.parseInt(parts[1]);
        wsMax1[n] = Integer.parseInt(parts[2]);
        wsMin2[n] = Integer.parseInt(parts[3]);
        wsMax2[n] = Integer.parseInt(parts[4]);
        n++;
    }

    private void processRecordType2(String record) {
        wsMyTicket = record;
    }

    private void processRecordType3(String record) {
        if (i == 0) {
            i++;
            return;
        }
        stringPtr = 0;
        String[] values = record.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(values[j]);
        }
        checkTicket();
    }

    private void checkTicket() {
        wsRowCorrect = 1;
        for (k = 0; k < MAX_FIELDS; k++) {
            wsVal = wsRow[k];
            checkVal();
            if (wsValCorrect == 0) {
                wsRowCorrect = 0;
            }
        }
        if (wsRowCorrect == 1) {
            l++;
            for (i1 = 0; i1 < MAX_FIELDS; i1++) {
                wsTickets[l][i1] = String.valueOf(wsRow[i1]);
            }
        }
    }

    private void checkVal() {
        wsValCorrect = 0;
        for (k1 = 0; k1 < MAX_RULES; k1++) {
            if ((wsVal >= wsMin1[k1] && wsVal <= wsMax1[k1]) || (wsVal >= wsMin2[k1] && wsVal <= wsMax2[k1])) {
                wsValCorrect = 1;
            }
        }
    }

    private void checkRules() {
        for (i1 = 0; i1 < MAX_RULES; i1++) {
            checkRule();
        }
    }

    private void checkRule() {
        wsRuleMet = 1;
        for (k = 0; k < l; k++) {
            wsVal = Integer.parseInt(wsTickets[k][k1]);
            if (wsVal < wsMin1[i1] || wsVal > wsMax2[i1] || (wsVal > wsMax1[i1] && wsVal < wsMin2[i1])) {
                wsRuleMet = 0;
            }
        }
        if (wsRuleMet == 1) {
            System.out.println("Rule " + (i1 + 1) + " is met for " + (k1 + 1));
        }
    }

    private void finish() {
        stringPtr = 0;
        String[] values = wsMyTicket.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(values[j]);
        }
        wsResult = (long) wsRow[13] * wsRow[10];
        System.out.println(wsResult);
    }
}