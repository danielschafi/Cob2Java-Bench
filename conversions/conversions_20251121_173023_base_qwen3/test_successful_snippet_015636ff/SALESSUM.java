import java.io.*;
import java.util.*;

public class SALESSUM {
    private static final String SALES_FILE_IN = "SALESIDAMT.DAT";
    private static final String SALES_FILE_OUT = "SALESIDOUT.DAT";
    private static final String STATE_FILE_OUT = "STATESOUT.DAT";
    
    // Table for sales IDs and amounts
    private static class SalesEntry {
        String id;
        double amount;
        
        public SalesEntry() {
            this.id = "";
            this.amount = 0.0;
        }
    }
    
    // Table for state entries and amounts
    private static class StateEntry {
        String state;
        double amount;
        
        public StateEntry() {
            this.state = "";
            this.amount = 0.0;
        }
    }
    
    private static SalesEntry[] salesTable = new SalesEntry[99];
    private static StateEntry[] stateTable = new StateEntry[50];
    private static int wsIdSub = 0;
    private static int wsStateSub = 0;
    private static double wsSalesTotal = 0.0;
    private static double wsStateTotal = 0.0;
    
    static {
        // Initialize tables
        for (int i = 0; i < 99; i++) {
            salesTable[i] = new SalesEntry();
        }
        for (int i = 0; i < 50; i++) {
            stateTable[i] = new StateEntry();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("SALESSUM PROGRAM START");
        
        try {
            // Open files
            BufferedReader salesIn = new BufferedReader(new FileReader(SALES_FILE_IN));
            PrintWriter salesOut = new PrintWriter(new FileWriter(SALES_FILE_OUT));
            PrintWriter stateOut = new PrintWriter(new FileWriter(STATE_FILE_OUT));
            
            // Initialize tables
            initializeTables();
            
            // Read first record
            String line = salesIn.readLine();
            while (line != null) {
                processRecord(line);
                line = salesIn.readLine();
            }
            
            // Write summary files
            writeSummaryFiles(salesOut, stateOut);
            
            // Write trailers
            writeTrailers(salesOut, stateOut);
            
            // Close files
            salesIn.close();
            salesOut.close();
            stateOut.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeTables() {
        // Initialize the sales ID table
        for (int i = 0; i < 99; i++) {
            salesTable[i].id = "";
            salesTable[i].amount = 0.0;
        }
        
        // Initialize the state table
        for (int i = 0; i < 50; i++) {
            stateTable[i].state = "";
            stateTable[i].amount = 0.0;
        }
    }
    
    private static void processRecord(String line) {
        if (line.length() < 10) return;
        
        String state = line.substring(0, 2);
        String id = line.substring(2, 5);
        double amount = Double.parseDouble(line.substring(5));
        
        // Search sales table for the sales ID or an entry with spaces
        wsIdSub = 0;
        while (wsIdSub < 99 && 
               !salesTable[wsIdSub].id.equals("") && 
               !salesTable[wsIdSub].id.equals(id)) {
            wsIdSub++;
        }
        
        // Check to see if the sales ID was found
        if (wsIdSub < 99 && salesTable[wsIdSub].id.equals(id)) {
            salesTable[wsIdSub].amount += amount;
        } else if (wsIdSub < 99) {
            salesTable[wsIdSub].amount += amount;
            salesTable[wsIdSub].id = id;
        }
        
        // Search state table for the state ID or an entry with spaces
        wsStateSub = 0;
        while (wsStateSub < 50 && 
               !stateTable[wsStateSub].state.equals("") && 
               !stateTable[wsStateSub].state.equals(state)) {
            wsStateSub++;
        }
        
        // Check to see if the state was found
        if (wsStateSub < 50 && stateTable[wsStateSub].state.equals(state)) {
            stateTable[wsStateSub].amount += amount;
        } else if (wsStateSub < 50) {
            stateTable[wsStateSub].amount += amount;
            stateTable[wsStateSub].state = state;
        }
    }
    
    private static void writeSummaryFiles(PrintWriter salesOut, PrintWriter stateOut) {
        // Process sales table
        for (int i = 0; i < 99; i++) {
            if (!salesTable[i].id.equals("")) {
                String idOut = String.format("%-3s", salesTable[i].id);
                String amountOut = String.format("%06.2f", salesTable[i].amount);
                salesOut.println(idOut + amountOut);
                wsSalesTotal += salesTable[i].amount;
            }
        }
        
        // Process state table
        for (int i = 0; i < 50; i++) {
            if (!stateTable[i].state.equals("")) {
                String stateOutStr = String.format("%-2s", stateTable[i].state);
                String amountOut = String.format("%06.2f", stateTable[i].amount);
                stateOut.println(stateOutStr + amountOut);
                wsStateTotal += stateTable[i].amount;
            }
        }
    }
    
    private static void writeTrailers(PrintWriter salesOut, PrintWriter stateOut) {
        // No trailer records needed
    }
}