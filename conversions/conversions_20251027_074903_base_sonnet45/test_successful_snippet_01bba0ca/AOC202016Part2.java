import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC202016Part2 {
    private long wsResult = 0;
    private int wsSection = 1;
    private int[] wsMin1 = new int[20];
    private int[] wsMax1 = new int[20];
    private int[] wsMin2 = new int[20];
    private int[] wsMax2 = new int[20];
    private String[][] wsTickets = new String[256][20];
    private String wsMyTicket = "";
    private int[] wsRow = new int[20];
    private int n = 0;
    private int i = 0;
    private int l = 0;

    public static void main(String[] args) {
        AOC202016Part2 program = new AOC202016Part2();
        program.run();
    }

    public void run() {
        readFile();
        checkRules();
        finish();
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        if (inputRecord.trim().isEmpty()) {
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
        String temp = inputRecord.replaceAll(" or ", "|");
        String[] parts = temp.split("[-:|]");
        
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
        
        String[] values = inputRecord.split(",");
        for (int j = 0; j < n && j < values.length; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        checkTicket();
    }

    private void checkTicket() {
        boolean wsRowCorrect = true;
        
        for (int k = 0; k < 20; k++) {
            int wsVal = wsRow[k];
            boolean wsValCorrect = false;
            
            for (int k1 = 0; k1 < 20; k1++) {
                if ((wsVal >= wsMin1[k1] && wsVal <= wsMax1[k1]) ||
                    (wsVal >= wsMin2[k1] && wsVal <= wsMax2[k1])) {
                    wsValCorrect = true;
                    break;
                }
            }
            
            if (!wsValCorrect) {
                wsRowCorrect = false;
                break;
            }
        }
        
        if (wsRowCorrect) {
            for (int i1 = 0; i1 < 20; i1++) {
                wsTickets[l][i1] = String.valueOf(wsRow[i1]);
            }
            l++;
        }
    }

    private void checkRules() {
        for (int i1 = 0; i1 < 20; i1++) {
            checkRule(i1);
        }
    }

    private void checkRule(int i1) {
        for (int k1 = 0; k1 < 20; k1++) {
            boolean wsRuleMet = true;
            
            for (int k = 0; k < l; k++) {
                int wsVal = Integer.parseInt(wsTickets[k][k1]);
                
                if (wsVal < wsMin1[i1] || wsVal > wsMax2[i1] ||
                    (wsVal > wsMax1[i1] && wsVal < wsMin2[i1])) {
                    wsRuleMet = false;
                    break;
                }
            }
            
            if (wsRuleMet) {
                System.out.println("Rule " + (i1 + 1) + " is met for " + (k1 + 1));
            }
        }
    }

    private void finish() {
        String[] values = wsMyTicket.split(",");
        for (int j = 0; j < n && j < values.length; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        
        wsResult = (long) wsRow[13] * wsRow[10] * wsRow[11] * wsRow[2] * wsRow[3] * wsRow[7];
        System.out.println(wsResult);
    }
}