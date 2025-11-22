import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class SALESSUM {
    
    static class SalesRecordIn {
        String salesState;
        String salesId;
        double salesAmount;
    }
    
    static class SalesRecordOut {
        String salesId;
        double salesIdAmt;
    }
    
    static class StateRecordOut {
        String stateId;
        double stateIdAmt;
    }
    
    static class TableSalesIdEntry {
        String tableSalesId;
        double tableSalesIdAmount;
        
        TableSalesIdEntry() {
            this.tableSalesId = "   ";
            this.tableSalesIdAmount = 0.0;
        }
    }
    
    static class TableStateEntry {
        String tableState;
        double tableStateAmount;
        
        TableStateEntry() {
            this.tableState = "   ";
            this.tableStateAmount = 0.0;
        }
    }
    
    private TableSalesIdEntry[] tableSalesIdEntries = new TableSalesIdEntry[99];
    private TableStateEntry[] tableStateEntries = new TableStateEntry[50];
    
    private double wsSalesTotal = 0.0;
    private double wsStateTotal = 0.0;
    private int wsIdSub = 0;
    private int wsStateSub = 0;
    
    private int wsDataSwitch = 1;
    private String salesFileStatus = "00";
    
    private BufferedReader salesFileIn;
    private BufferedWriter salesFileOut;
    private BufferedWriter stateFileOut;
    
    public static void main(String[] args) {
        SALESSUM program = new SALESSUM();
        program.driver();
    }
    
    private void driver() {
        System.out.println("SALESSUM PROGRAM START");
        
        try {
            salesFileIn = Files.newBufferedReader(Paths.get("SALESIDAMT.DAT"));
            salesFileOut = Files.newBufferedWriter(Paths.get("SALESIDOUT.DAT"));
            stateFileOut = Files.newBufferedWriter(Paths.get("STATESOUT.DAT"));
            
            initializeTable();
            
            SalesRecordIn record = readSalesFileIn();
            
            while (!isNoData() && wsDataSwitch != 0) {
                if (record != null) {
                    populateTables(record);
                    record = readSalesFileIn();
                } else {
                    break;
                }
            }
            
            writeSummaryFiles();
            writeTrailers();
            
            salesFileIn.close();
            salesFileOut.close();
            stateFileOut.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void initializeTable() {
        for (wsIdSub = 0; wsIdSub < 98; wsIdSub++) {
            tableSalesIdEntries[wsIdSub] = new TableSalesIdEntry();
        }
        
        for (wsStateSub = 0; wsStateSub < 49; wsStateSub++) {
            tableStateEntries[wsStateSub] = new TableStateEntry();
        }
    }
    
    private SalesRecordIn readSalesFileIn() {
        try {
            String line = salesFileIn.readLine();
            if (line == null) {
                wsDataSwitch = 0;
                salesFileStatus = "10";
                return null;
            }
            
            if (line.length() < 11) {
                wsDataSwitch = 0;
                return null;
            }
            
            SalesRecordIn record = new SalesRecordIn();
            record.salesState = line.substring(0, 2);
            record.salesId = line.substring(2, 5);
            String amountStr = line.substring(5, 11);
            record.salesAmount = Double.parseDouble(amountStr) / 100.0;
            
            return record;
        } catch (IOException e) {
            wsDataSwitch = 0;
            salesFileStatus = "99";
            return null;
        }
    }
    
    private boolean isNoData() {
        return salesFileStatus.compareTo("02") >= 0 && salesFileStatus.compareTo("99") <= 0;
    }
    
    private void populateTables(SalesRecordIn record) {
        for (wsIdSub = 0; wsIdSub < 98; wsIdSub++) {
            if (tableSalesIdEntries[wsIdSub].tableSalesId.trim().isEmpty() ||
                tableSalesIdEntries[wsIdSub].tableSalesId.equals(record.salesId)) {
                break;
            }
        }
        
        if (tableSalesIdEntries[wsIdSub].tableSalesId.equals(record.salesId)) {
            tableSalesIdEntries[wsIdSub].tableSalesIdAmount += record.salesAmount;
        } else {
            tableSalesIdEntries[wsIdSub].tableSalesIdAmount += record.salesAmount;
            tableSalesIdEntries[wsIdSub].tableSalesId = record.salesId;
        }
        
        for (wsStateSub = 0; wsStateSub < 49; wsStateSub++) {
            if (tableStateEntries[wsStateSub].tableState.trim().isEmpty() ||
                tableStateEntries[wsStateSub].tableState.equals(record.salesState)) {
                break;
            }
        }
        
        if (tableStateEntries[wsStateSub].tableState.equals(record.salesState)) {
            tableStateEntries[wsStateSub].tableStateAmount += record.salesAmount;
        } else {
            tableStateEntries[wsStateSub].tableStateAmount += record.salesAmount;
            tableStateEntries[wsStateSub].tableState = record.salesState;
        }
    }
    
    private void writeSummaryFiles() throws IOException {
        DecimalFormat df = new DecimalFormat("00000000");
        
        for (wsIdSub = 0; wsIdSub < 98; wsIdSub++) {
            if (tableSalesIdEntries[wsIdSub].tableSalesId.trim().isEmpty()) {
                break;
            }
            
            String salesIdOut = tableSalesIdEntries[wsIdSub].tableSalesId;
            long salesIdAmtOut = Math.round(tableSalesIdEntries[wsIdSub].tableSalesIdAmount * 100);
            
            String outputLine = salesIdOut + df.format(salesIdAmtOut);
            salesFileOut.write(outputLine);
            salesFileOut.newLine();
            
            wsSalesTotal += tableSalesIdEntries[wsIdSub].tableSalesIdAmount;
        }
        
        for (wsStateSub = 0; wsStateSub < 49; wsStateSub++) {
            if (tableStateEntries[wsStateSub].tableState.trim().isEmpty()) {
                break;
            }
            
            String stateIdOut = tableStateEntries[wsStateSub].tableState;
            long stateIdAmtOut = Math.round(tableStateEntries[wsStateSub].tableStateAmount * 100);
            
            String outputLine = stateIdOut + df.format(stateIdAmtOut);
            stateFileOut.write(outputLine);
            stateFileOut.newLine();
            
            wsStateTotal += tableStateEntries[wsStateSub].tableStateAmount;
        }
    }
    
    private void writeTrailers() {
    }
}