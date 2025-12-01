import java.io.*;
import java.util.*;

public class AOC202016Part2 {
    private static final int MAX_RULES = 20;
    private static final int MAX_TICKETS = 256;
    
    private long wsResult = 0;
    private int recLen = 0;
    private int fileStatus = 0;
    private int wsSection = 1;
    
    private int[] wsMin1 = new int[MAX_RULES];
    private int[] wsMax1 = new int[MAX_RULES];
    private int[] wsMin2 = new int[MAX_RULES];
    private int[] wsMax2 = new int[MAX_RULES];
    
    private String wsTmp1, wsTmp2, wsTmp3, wsTmp4, wsTmp5, wsTmp6;
    private String[][] wsTickets = new String[MAX_TICKETS][MAX_RULES];
    private String wsMyTicket = "";
    private int[] wsRow = new int[MAX_RULES];
    private int wsVal = 0;
    private int wsValCorrect = 0;
    private int wsRowCorrect = 0;
    private int[] wsRulesMet = new int[6];
    private int wsRuleMet = 0;
    
    private int i = 0, j = 0, k = 0, l = 0;
    private int i1 = 1, j1 = 1, k1 = 1, l1 = 1;
    private int n = 0;
    private int stringPtr = 1;
    
    public static void main(String[] args) {
        AOC202016Part2 program = new AOC202016Part2();
        program.run();
    }
    
    private void run() {
        readInput();
        checkRules();
        finish();
    }
    
    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processRecord(String inputRecord) {
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
        String[] parts = inputRecord.split("-|:|\\s+or\\s+");
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1].trim());
        wsMax1[n - 1] = Integer.parseInt(parts[2].trim());
        wsMin2[n - 1] = Integer.parseInt(parts[3].trim());
        wsMax2[n - 1] = Integer.parseInt(parts[4].trim());
    }
    
    private void processRecordType2(String inputRecord) {
        wsMyTicket = inputRecord;
    }
    
    private void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        
        stringPtr = 0;
        String[] values = inputRecord.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        checkTicket();
    }
    
    private void checkTicket() {
        wsRowCorrect = 1;
        for (k = 0; k < n; k++) {
            wsVal = wsRow[k];
            checkVal();
        }
        if (wsRowCorrect == 1) {
            l++;
            for (i1 = 0; i1 < n; i1++) {
                wsTickets[l - 1][i1] = String.valueOf(wsRow[i1]);
            }
        }
    }
    
    private void checkVal() {
        wsValCorrect = 0;
        for (k1 = 0; k1 < n; k1++) {
            if ((wsVal >= wsMin1[k1] && wsVal <= wsMax1[k1]) ||
                (wsVal >= wsMin2[k1] && wsVal <= wsMax2[k1])) {
                wsValCorrect = 1;
            }
        }
        if (wsValCorrect == 0) {
            wsRowCorrect = 0;
        }
    }
    
    private void checkRules() {
        for (i1 = 0; i1 < MAX_RULES; i1++) {
            checkRule();
        }
    }
    
    private void checkRule() {
        for (k1 = 0; k1 < MAX_RULES; k1++) {
            wsRuleMet = 1;
            for (k = 0; k < l; k++) {
                wsVal = Integer.parseInt(wsTickets[k][k1]);
                if (wsVal < wsMin1[i1] || wsVal > wsMax2[i1] ||
                    (wsVal > wsMax1[i1] && wsVal < wsMin2[i1])) {
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
        String[] myTicketValues = wsMyTicket.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(myTicketValues[j].trim());
        }
        
        wsResult = (long) wsRow[13] * wsRow[10] * wsRow[11] * wsRow[2] * wsRow[3] * wsRow[7];
        System.out.println(wsResult);
    }
}