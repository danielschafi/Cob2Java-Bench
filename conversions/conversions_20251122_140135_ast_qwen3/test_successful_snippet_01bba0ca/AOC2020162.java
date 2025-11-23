import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020162 {
    private static final String INPUT_FILE = "d16.input";
    
    // Working storage variables
    private static long wsResult = 0;
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
    private static String[][] wsTickets = new String[256][20];
    private static String wsMyTicket = "";
    private static int[] wsRow = new int[20];
    private static int wsVal = 0;
    private static int wsValCorrect = 0;
    private static int wsRowCorrect = 1;
    private static int[] wsRulesMet = new int[6];
    private static int wsRuleMet = 0;
    
    // Local storage variables
    private static int i = 0;
    private static int j = 0;
    private static int k = 0;
    private static int l = 0;
    private static int i1 = 1;
    private static int j1 = 1;
    private static int k1 = 1;
    private static int l1 = 1;
    private static int n = 0;
    private static int stringPtr = 1;
    
    public static void main(String[] args) {
        try {
            readFile();
            checkRules();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
        String line;
        
        while ((line = reader.readLine()) != null) {
            recLen = line.length();
            processRecord(line);
        }
        
        reader.close();
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
        String[] parts = inputRecord.split("[-: or ]+");
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1]);
        wsMax1[n - 1] = Integer.parseInt(parts[2]);
        wsMin2[n - 1] = Integer.parseInt(parts[3]);
        wsMax2[n - 1] = Integer.parseInt(parts[4]);
    }
    
    private static void processRecordType2(String inputRecord) {
        wsMyTicket = inputRecord;
    }
    
    private static void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        
        stringPtr = 1;
        for (j = 1; j <= n; j++) {
            int commaIndex = inputRecord.indexOf(',', stringPtr - 1);
            if (commaIndex != -1) {
                wsRow[j - 1] = Integer.parseInt(inputRecord.substring(stringPtr - 1, commaIndex));
                stringPtr = commaIndex + 2;
            } else {
                wsRow[j - 1] = Integer.parseInt(inputRecord.substring(stringPtr - 1));
                break;
            }
        }
        checkTicket();
    }
    
    private static void checkTicket() {
        wsRowCorrect = 1;
        for (k = 1; k <= 20; k++) {
            wsVal = wsRow[k - 1];
            checkVal();
        }
        
        if (wsRowCorrect == 1) {
            l++;
            for (i1 = 1; i1 <= 20; i1++) {
                wsTickets[l - 1][i1 - 1] = String.valueOf(wsRow[i1 - 1]);
            }
        }
    }
    
    private static void checkVal() {
        wsValCorrect = 0;
        for (k1 = 1; k1 <= 20; k1++) {
            if ((wsVal >= wsMin1[k1 - 1] && wsVal <= wsMax1[k1 - 1]) ||
                (wsVal >= wsMin2[k1 - 1] && wsVal <= wsMax2[k1 - 1])) {
                wsValCorrect = 1;
            }
        }
        
        if (wsValCorrect == 0) {
            wsRowCorrect = 0;
        }
    }
    
    private static void checkRules() {
        for (i1 = 1; i1 <= 20; i1++) {
            checkRule();
        }
    }
    
    private static void checkRule() {
        for (k1 = 1; k1 <= 20; k1++) {
            wsRuleMet = 1;
            for (k = 1; k <= l; k++) {
                wsVal = Integer.parseInt(wsTickets[k - 1][k1 - 1]);
                if (wsVal < wsMin1[i1 - 1] || 
                    wsVal > wsMax2[i1 - 1] || 
                    (wsVal > wsMax1[i1 - 1] && wsVal < wsMin2[i1 - 1])) {
                    wsRuleMet = 0;
                }
            }
            
            if (wsRuleMet == 1) {
                System.out.println("Rule " + i1 + " is met for " + k1);
            }
        }
    }
    
    private static void finish() {
        stringPtr = 1;
        for (j = 1; j <= n; j++) {
            int commaIndex = wsMyTicket.indexOf(',', stringPtr - 1);
            if (commaIndex != -1) {
                wsRow[j - 1] = Integer.parseInt(wsMyTicket.substring(stringPtr - 1, commaIndex));
                stringPtr = commaIndex + 2;
            } else {
                wsRow[j - 1] = Integer.parseInt(wsMyTicket.substring(stringPtr - 1));
                break;
            }
        }
        
        wsResult = wsRow[13] * wsRow[10]; // 14th and 11th elements (0-indexed)
        System.out.println(wsResult);
    }
}