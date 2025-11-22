import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SALESSUM {
    
    static class TableSalesIdEntry {
        String tableId = "";
        double tableAmount = 0.0;
    }
    
    static class TableStateEntry {
        String tableState = "";
        double tableAmount = 0.0;
    }
    
    static TableSalesIdEntry[] tableSalesId = new TableSalesIdEntry[99];
    static TableStateEntry[] tableState = new TableStateEntry[50];
    
    static double wsSalesTotal = 0.0;
    static double wsStateTotal = 0.0;
    static int wsIdSub = 0;
    static int wsStateSub = 0;
    static int wsDataSwitch = 1;
    
    static String salesState = "";
    static String salesId = "";
    static double salesAmount = 0.0;
    
    static BufferedReader salesFileIn;
    static PrintWriter salesFileOut;
    static PrintWriter stateFileOut;
    
    public static void main(String[] args) {
        try {
            System.out.println("SALESSUM PROGRAM START");
            
            salesFileIn = new BufferedReader(new FileReader("SALESIDAMT.DAT"));
            salesFileOut = new PrintWriter(new FileWriter("SALESIDOUT.DAT"));
            stateFileOut = new PrintWriter(new FileWriter("STATESOUT.DAT"));
            
            initializeTable();
            
            String line = salesFileIn.readLine();
            
            while (line != null && wsDataSwitch == 1) {
                parseSalesRecord(line);
                populateTables();
                line = salesFileIn.readLine();
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
    
    static void initializeTable() {
        for (int i = 0; i < 99; i++) {
            tableSalesId[i] = new TableSalesIdEntry();
            tableSalesId[i].tableId = "";
            tableSalesId[i].tableAmount = 0.0;
        }
        
        for (int i = 0; i < 50; i++) {
            tableState[i] = new TableStateEntry();
            tableState[i].tableState = "";
            tableState[i].tableAmount = 0.0;
        }
    }
    
    static void parseSalesRecord(String line) {
        if (line.length() >= 9) {
            salesState = line.substring(0, 2).trim();
            salesId = line.substring(2, 5).trim();
            String amountStr = line.substring(5, 9);
            try {
                double intPart = Double.parseDouble(amountStr.substring(0, 4));
                double decPart = Double.parseDouble(amountStr.substring(4, 6));
                salesAmount = intPart + (decPart / 100.0);
            } catch (NumberFormatException e) {
                salesAmount = 0.0;
            }
        }
    }
    
    static void populateTables() {
        wsIdSub = 0;
        while (wsIdSub < 98 && 
               (tableSalesId[wsIdSub].tableId.isEmpty() || 
                tableSalesId[wsIdSub].tableId.equals(salesId))) {
            wsIdSub++;
        }
        wsIdSub--;
        
        if (tableSalesId[wsIdSub].tableId.equals(salesId)) {
            tableSalesId[wsIdSub].tableAmount += salesAmount;
        } else {
            tableSalesId[wsIdSub].tableAmount += salesAmount;
            tableSalesId[wsIdSub].tableId = salesId;
        }
        
        wsStateSub = 0;
        while (wsStateSub < 49 && 
               (tableState[wsStateSub].tableState.isEmpty() || 
                tableState[wsStateSub].tableState.equals(salesState))) {
            wsStateSub++;
        }
        wsStateSub--;
        
        if (tableState[wsStateSub].tableState.equals(salesState)) {
            tableState[wsStateSub].tableAmount += salesAmount;
        } else {
            tableState[wsStateSub].tableAmount += salesAmount;
            tableState[wsStateSub].tableState = salesState;
        }
    }
    
    static void writeSummaryFiles() {
        for (int i = 0; i < 98; i++) {
            if (!tableSalesId[i].tableId.isEmpty()) {
                String output = String.format("%s%08.2f", 
                    tableSalesId[i].tableId, 
                    tableSalesId[i].tableAmount);
                salesFileOut.println(output);
                wsSalesTotal += tableSalesId[i].tableAmount;
            }
        }
        
        for (int i = 0; i < 49; i++) {
            if (!tableState[i].tableState.isEmpty()) {
                String output = String.format("%s%08.2f", 
                    tableState[i].tableState, 
                    tableState[i].tableAmount);
                stateFileOut.println(output);
                wsStateTotal += tableState[i].tableAmount;
            }
        }
    }
    
    static void writeTrailers() {
    }
}