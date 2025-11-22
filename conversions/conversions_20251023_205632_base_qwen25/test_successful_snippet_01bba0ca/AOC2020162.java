import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020162 {
    private static final int MAX_RULES = 20;
    private static final int MAX_TICKETS = 256;
    private static final int MAX_FIELDS = 20;

    private long wsResult = 0;
    private int recLen;
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
    private int wsVal;
    private int wsValCorrect;
    private int wsRowCorrect;
    private int[] wsRulesMet = new int[6];
    private int wsRuleMet;

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
        program.execute();
    }

    private void execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                if (recLen == 0) {
                    wsSection++;
                } else {
                    if (wsSection == 1) {
                        processRecordType1(line);
                    } else if (wsSection == 2) {
                        processRecordType2(line);
                    } else {
                        processRecordType3(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkRules();
        finish();
    }

    private void processRecordType1(String line) {
        String[] parts = line.split("[-:, or ]+");
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1]);
        wsMax1[n - 1] = Integer.parseInt(parts[2]);
        wsMin2[n - 1] = Integer.parseInt(parts[3]);
        wsMax2[n - 1] = Integer.parseInt(parts[4]);
    }

    private void processRecordType2(String line) {
        wsMyTicket = line;
    }

    private void processRecordType3(String line) {
        i++;
        if (i == 1) {
            return;
        }
        stringPtr = 0;
        String[] parts = line.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(parts[j]);
        }
        checkTicket();
    }

    private void checkTicket() {
        wsRowCorrect = 1;
        for (k = 0; k < 20; k++) {
            wsVal = wsRow[k];
            checkVal();
            if (wsValCorrect == 0) {
                wsRowCorrect = 0;
            }
        }
        if (wsRowCorrect == 1) {
            l++;
            for (i1 = 0; i1 < 20; i1++) {
                wsTickets[l - 1][i1] = String.valueOf(wsRow[i1]);
            }
        }
    }

    private void checkVal() {
        wsValCorrect = 0;
        for (k1 = 0; k1 < 20; k1++) {
            if ((wsVal >= wsMin1[k1] && wsVal <= wsMax1[k1]) || (wsVal >= wsMin2[k1] && wsVal <= wsMax2[k1])) {
                wsValCorrect = 1;
            }
        }
    }

    private void checkRules() {
        for (i1 = 0; i1 < 20; i1++) {
            checkRule();
        }
    }

    private void checkRule() {
        for (k1 = 0; k1 < 20; k1++) {
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
    }

    private void finish() {
        stringPtr = 0;
        String[] parts = wsMyTicket.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(parts[j]);
        }
        wsResult = (long) wsRow[13] * wsRow[10] * wsRow[11] * wsRow[2] * wsRow[3] * wsRow[7];
        System.out.println(wsResult);
    }
}