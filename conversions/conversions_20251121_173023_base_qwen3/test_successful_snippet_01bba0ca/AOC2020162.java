import java.io.*;
import java.util.*;

public class AOC2020162 {
    private static final int MAX_RULES = 20;
    private static final int MAX_TICKETS = 256;
    private static final int MAX_FIELDS = 20;

    private static int[] min1 = new int[MAX_RULES];
    private static int[] max1 = new int[MAX_RULES];
    private static int[] min2 = new int[MAX_RULES];
    private static int[] max2 = new int[MAX_RULES];
    
    private static String[] myTicket = new String[MAX_FIELDS];
    private static String[][] tickets = new String[MAX_TICKETS][MAX_FIELDS];
    private static int ticketCount = 0;
    private static int section = 1;
    private static int ruleCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d16.input"));
        String line;
        
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
        
        checkRules();
        finish();
    }

    private static void processRecord(String record) {
        if (record.isEmpty()) {
            section++;
            return;
        }

        switch (section) {
            case 1:
                processRuleRecord(record);
                break;
            case 2:
                myTicket = record.split(",");
                break;
            case 3:
                if (ticketCount == 0) {
                    ticketCount++;
                    return;
                }
                String[] fields = record.split(",");
                for (int i = 0; i < fields.length; i++) {
                    tickets[ticketCount][i] = fields[i];
                }
                ticketCount++;
                break;
        }
    }

    private static void processRuleRecord(String record) {
        String[] parts = record.split("[-: or]+");
        ruleCount++;
        min1[ruleCount - 1] = Integer.parseInt(parts[1]);
        max1[ruleCount - 1] = Integer.parseInt(parts[2]);
        min2[ruleCount - 1] = Integer.parseInt(parts[3]);
        max2[ruleCount - 1] = Integer.parseInt(parts[4]);
    }

    private static void checkRules() {
        for (int i = 0; i < MAX_FIELDS; i++) {
            checkRule(i);
        }
    }

    private static void checkRule(int fieldIndex) {
        for (int k1 = 0; k1 < MAX_RULES; k1++) {
            boolean ruleMet = true;
            for (int k = 0; k < ticketCount; k++) {
                int value = Integer.parseInt(tickets[k][fieldIndex]);
                if (value < min1[k1] || value > max2[k1] || 
                   (value > max1[k1] && value < min2[k1])) {
                    ruleMet = false;
                    break;
                }
            }
            if (ruleMet) {
                System.out.println("Rule " + (k1 + 1) + " is met for " + (fieldIndex + 1));
            }
        }
    }

    private static void finish() {
        long result = 1;
        result *= Integer.parseInt(myTicket[13]); // 14th field (0-indexed)
        result *= Integer.parseInt(myTicket[10]); // 11th field (0-indexed)
        // Skipping other fields as per original logic
        
        System.out.println(result);
    }
}