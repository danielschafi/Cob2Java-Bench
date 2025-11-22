import java.io.*;
import java.util.*;

public class AOC202016Part2 {
    private static final String INPUT_FILE = "d16.input";
    private static final int MAX_FIELDS = 20;
    private static final int MAX_TICKETS = 256;
    
    private int[] wsMin1 = new int[MAX_FIELDS];
    private int[] wsMax1 = new int[MAX_FIELDS];
    private int[] wsMin2 = new int[MAX_FIELDS];
    private int[] wsMax2 = new int[MAX_FIELDS];
    private int[][][] wsTickets = new int[MAX_TICKETS][MAX_FIELDS][1];
    private int[] wsMyTicket = new int[MAX_FIELDS];
    private int[] wsRow = new int[MAX_FIELDS];
    private int wsSection = 1;
    private int n = 0;
    private int l = 0;
    private long wsResult = 0;
    
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
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processRecord(String line) {
        if (line.trim().isEmpty()) {
            wsSection++;
            return;
        }
        
        if (wsSection == 1) {
            processRecordType1(line);
        } else if (wsSection == 2) {
            processRecordType2(line);
        } else {
            processRecordType3(line);
        }
    }
    
    private void processRecordType1(String line) {
        String[] parts = line.split("-|:|\\s+or\\s+");
        List<String> tokens = new ArrayList<>();
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                tokens.add(part.trim());
            }
        }
        
        wsMin1[n] = Integer.parseInt(tokens.get(1));
        wsMax1[n] = Integer.parseInt(tokens.get(2));
        wsMin2[n] = Integer.parseInt(tokens.get(3));
        wsMax2[n] = Integer.parseInt(tokens.get(4));
        n++;
    }
    
    private void processRecordType2(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length && i < MAX_FIELDS; i++) {
            wsMyTicket[i] = Integer.parseInt(parts[i].trim());
        }
    }
    
    private void processRecordType3(String line) {
        if (line.contains("ticket")) {
            return;
        }
        
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length && i < MAX_FIELDS; i++) {
            wsRow[i] = Integer.parseInt(parts[i].trim());
        }
        
        checkTicket();
    }
    
    private void checkTicket() {
        boolean rowCorrect = true;
        
        for (int k = 0; k < n; k++) {
            int val = wsRow[k];
            boolean valCorrect = false;
            
            for (int k1 = 0; k1 < n; k1++) {
                if ((val >= wsMin1[k1] && val <= wsMax1[k1]) ||
                    (val >= wsMin2[k1] && val <= wsMax2[k1])) {
                    valCorrect = true;
                    break;
                }
            }
            
            if (!valCorrect) {
                rowCorrect = false;
                break;
            }
        }
        
        if (rowCorrect) {
            for (int i1 = 0; i1 < n; i1++) {
                wsTickets[l][i1][0] = wsRow[i1];
            }
            l++;
        }
    }
    
    private void checkRules() {
        for (int i1 = 0; i1 < n; i1++) {
            for (int k1 = 0; k1 < n; k1++) {
                boolean ruleMet = true;
                
                for (int k = 0; k < l; k++) {
                    int val = wsTickets[k][k1][0];
                    if (val < wsMin1[i1] || val > wsMax2[i1] ||
                        (val > wsMax1[i1] && val < wsMin2[i1])) {
                        ruleMet = false;
                        break;
                    }
                }
                
                if (ruleMet) {
                    System.out.println("Rule " + (i1 + 1) + " is met for " + (k1 + 1));
                }
            }
        }
    }
    
    private void finish() {
        wsResult = (long) wsMyTicket[13] * wsMyTicket[10]
                 * wsMyTicket[11] * wsMyTicket[2] * wsMyTicket[3] * wsMyTicket[7];
        System.out.println(wsResult);
    }
}