import java.io.*;
import java.util.*;

public class SALESSUM {
    static class SalesRecordIn {
        String salesStateIn;
        String salesIdIn;
        double salesAmountIn;
    }
    
    static class SalesRecordOut {
        String salesIdOut;
        double salesIdAmtOut;
    }
    
    static class StateRecordOut {
        String stateIdOut;
        double stateIdAmtOut;
    }
    
    static class TableSalesIdEntry {
        String tableSalesId;
        double tableSalesIdAmount;
    }
    
    static class TableStateEntry {
        String tableState;
        double tableStateAmount;
    }
    
    static TableSalesIdEntry[] tableSalesIdEntry = new TableSalesIdEntry[99];
    static TableStateEntry[] tableStateEntry = new TableStateEntry[50];
    static double wsSalesTotal = 0.0;
    static double wsStateTotal = 0.0;
    static int wsIdSub = 0;
    static int wsStateSub = 0;
    static int wsDataSwitch = 1;
    static String salesFileStatus = "";
    static boolean noData = false;
    
    static FileInputStream salesFileIn;
    static FileOutputStream salesFileOut;
    static FileOutputStream stateFileOut;
    static BufferedReader salesReader;
    static PrintWriter salesWriter;
    static PrintWriter stateWriter;
    
    public static void main(String[] args) {
        System.out.println("SALESSUM PROGRAM START");
        openFiles();
        initializeTable();
        readSalesFileIn();
        populateTables();
        writeSummaryFiles();
        writeTrailers();
        closeFiles();
    }
    
    static void openFiles() {
        try {
            salesFileIn = new FileInputStream("SALESIDAMT.DAT");
            salesReader = new BufferedReader(new InputStreamReader(salesFileIn));
            salesFileOut = new FileOutputStream("SALESIDOUT.DAT");
            salesWriter = new PrintWriter(salesFileOut);
            stateFileOut = new FileOutputStream("STATESOUT.DAT");
            stateWriter = new PrintWriter(stateFileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void closeFiles() {
        try {
            salesReader.close();
            salesWriter.close();
            stateWriter.close();
            salesFileIn.close();
            salesFileOut.close();
            stateFileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void initializeTable() {
        for (int i = 0; i < 99; i++) {
            tableSalesIdEntry[i] = new TableSalesIdEntry();
            tableSalesIdEntry[i].tableSalesId = "";
            tableSalesIdEntry[i].tableSalesIdAmount = 0.0;
        }
        
        for (int i = 0; i < 50; i++) {
            tableStateEntry[i] = new TableStateEntry();
            tableStateEntry[i].tableState = "";
            tableStateEntry[i].tableStateAmount = 0.0;
        }
    }
    
    static void readSalesFileIn() {
        try {
            String line = salesReader.readLine();
            if (line == null) {
                wsDataSwitch = 0;
                return;
            }
            
            SalesRecordIn record = new SalesRecordIn();
            record.salesStateIn = line.substring(0, 2);
            record.salesIdIn = line.substring(2, 5);
            record.salesAmountIn = Double.parseDouble(line.substring(5, 10)) / 100.0;
            
            // Process the record as needed
        } catch (Exception e) {
            wsDataSwitch = 0;
        }
    }
    
    static void populateTables() {
        while (wsDataSwitch != 0 && !noData) {
            try {
                String line = salesReader.readLine();
                if (line == null) {
                    wsDataSwitch = 0;
                    break;
                }
                
                SalesRecordIn record = new SalesRecordIn();
                record.salesStateIn = line.substring(0, 2);
                record.salesIdIn = line.substring(2, 5);
                record.salesAmountIn = Double.parseDouble(line.substring(5, 10)) / 100.0;
                
                // Search sales table for the sales ID or an entry with spaces
                wsIdSub = 0;
                while (wsIdSub < 99) {
                    if (tableSalesIdEntry[wsIdSub].tableSalesId.equals("") || 
                        tableSalesIdEntry[wsIdSub].tableSalesId.equals(record.salesIdIn)) {
                        break;
                    }
                    wsIdSub++;
                }
                
                // Check to see if the sale ID was found
                if (tableSalesIdEntry[wsIdSub].tableSalesId.equals(record.salesIdIn)) {
                    tableSalesIdEntry[wsIdSub].tableSalesIdAmount += record.salesAmountIn;
                } else {
                    tableSalesIdEntry[wsIdSub].tableSalesIdAmount += record.salesAmountIn;
                    tableSalesIdEntry[wsIdSub].tableSalesId = record.salesIdIn;
                }
                
                // Search state table for the state ID or an entry with spaces
                wsStateSub = 0;
                while (wsStateSub < 50) {
                    if (tableStateEntry[wsStateSub].tableState.equals("") || 
                        tableStateEntry[wsStateSub].tableState.equals(record.salesStateIn)) {
                        break;
                    }
                    wsStateSub++;
                }
                
                // Check to see if the state was found
                if (tableStateEntry[wsStateSub].tableState.equals(record.salesStateIn)) {
                    tableStateEntry[wsStateSub].tableStateAmount += record.salesAmountIn;
                } else {
                    tableStateEntry[wsStateSub].tableStateAmount += record.salesAmountIn;
                    tableStateEntry[wsStateSub].tableState = record.salesStateIn;
                }
                
            } catch (Exception e) {
                wsDataSwitch = 0;
            }
        }
    }
    
    static void writeSummaryFiles() {
        // Read through each sales ID table occurrence and move to the sales summary output record and write the record
        for (int i = 0; i < 99; i++) {
            if (tableSalesIdEntry[i].tableSalesId.equals("")) {
                break;
            }
            SalesRecordOut outRecord = new SalesRecordOut();
            outRecord.salesIdOut = tableSalesIdEntry[i].tableSalesId;
            outRecord.salesIdAmtOut = tableSalesIdEntry[i].tableSalesIdAmount;
            salesWriter.println(String.format("%-3s%06.2f", outRecord.salesIdOut, outRecord.salesIdAmtOut));
            wsSalesTotal += outRecord.salesIdAmtOut;
        }
        
        // Read through each state ID table occurrence and move to the state summary output record and write the record
        for (int i = 0; i < 50; i++) {
            if (tableStateEntry[i].tableState.equals("")) {
                break;
            }
            StateRecordOut outRecord = new StateRecordOut();
            outRecord.stateIdOut = tableStateEntry[i].tableState;
            outRecord.stateIdAmtOut = tableStateEntry[i].tableStateAmount;
            stateWriter.println(String.format("%-2s%06.2f", outRecord.stateIdOut, outRecord.stateIdAmtOut));
            wsStateTotal += outRecord.stateIdAmtOut;
        }
    }
    
    static void writeTrailers() {
        // If you want extra credit for creating a trailer record, remove exit statement and insert the extra credit code:
        // Not implemented as per original code
    }
}