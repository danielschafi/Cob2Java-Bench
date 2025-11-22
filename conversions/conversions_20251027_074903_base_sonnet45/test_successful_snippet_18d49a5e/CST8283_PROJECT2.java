import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CST8283_PROJECT2 {
    
    private static class InventoryRecord {
        int partNumber;
        String partName;
        int quantity;
        double unitPrice;
        String supplierCode;
        int reOrderPoint;
    }
    
    private static class SupplierRecord {
        String supplierCode;
        String supplierName;
    }
    
    private static class SupplierTableRecord {
        String tblSupplierCode;
        String supplierNameTbl;
    }
    
    private static final int MAX_SUPPLIERS = 1000;
    private static SupplierTableRecord[] supplierTable = new SupplierTableRecord[MAX_SUPPLIERS];
    
    private static String invEofFlag = "NO";
    private static String supEofFlag = "NO";
    private static String foundFlag = "NO";
    private static int sub = 1;
    private static long inventoryValue = 0;
    private static int auditReadCounter = 0;
    private static int auditWritCounter = 0;
    private static long totalValue = 0;
    
    private static BufferedReader inventFileIn;
    private static BufferedReader supplierFileIn;
    private static BufferedWriter inventReportOut;
    private static BufferedWriter reorderReportOut;
    
    private static InventoryRecord currentInventRecord = new InventoryRecord();
    private static String wsSupplierNameOut = "";
    
    private static DecimalFormat moneyFormat = new DecimalFormat("$###,###,##0.00");
    
    public static void main(String[] args) {
        manageInventory();
    }
    
    private static void manageInventory() {
        initializeProduceInventoryReport();
        while (!invEofFlag.equals("YES")) {
            processInventoryReport();
        }
        terminateProcess();
    }
    
    private static void initializeProduceInventoryReport() {
        openFiles();
        sub = 1;
        while (sub <= MAX_SUPPLIERS && !supEofFlag.equals("YES")) {
            loadSupplierTable();
            sub++;
        }
        writeHeading();
    }
    
    private static void processInventoryReport() {
        readInventoryRecord();
        if (invEofFlag.equals("NO")) {
            sub = 1;
            while (sub <= MAX_SUPPLIERS && !foundFlag.equals("YES")) {
                searchSupplierRecord();
                sub++;
            }
            calculateInventoryValue();
            calculateTotalValue();
            checkForReorder();
            writeInventoryRecord();
        }
    }
    
    private static void terminateProcess() {
        printAuditCounter();
        closeFile();
    }
    
    private static void openFiles() {
        try {
            inventFileIn = new BufferedReader(new FileReader("INVENT.TXT"));
            supplierFileIn = new BufferedReader(new FileReader("SUPPLIERS.TXT"));
            inventReportOut = new BufferedWriter(new FileWriter("INVREPRT.TXT"));
            reorderReportOut = new BufferedWriter(new FileWriter("INVREORD.TXT"));
            
            for (int i = 0; i < MAX_SUPPLIERS; i++) {
                supplierTable[i] = new SupplierTableRecord();
                supplierTable[i].tblSupplierCode = "";
                supplierTable[i].supplierNameTbl = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void loadSupplierTable() {
        try {
            String line = supplierFileIn.readLine();
            if (line == null) {
                supEofFlag = "YES";
            } else {
                if (line.length() >= 20) {
                    supplierTable[sub - 1].tblSupplierCode = line.substring(0, 5);
                    supplierTable[sub - 1].supplierNameTbl = line.substring(5, 20);
                }
            }
        } catch (IOException e) {
            supEofFlag = "YES";
        }
    }
    
    private static void writeHeading() {
        try {
            String heading = String.format("%-7s %-20s %-5s %-3s %-3s %-15s",
                "NUMBER", "PART NAME", "", "QTY", "", "VALUE");
            inventReportOut.write(heading);
            inventReportOut.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readInventoryRecord() {
        foundFlag = "NO";
        try {
            String line = inventFileIn.readLine();
            if (line == null) {
                invEofFlag = "YES";
            } else {
                auditReadCounter++;
                if (line.length() >= 38) {
                    currentInventRecord.partNumber = Integer.parseInt(line.substring(0, 5).trim());
                    currentInventRecord.partName = line.substring(5, 25);
                    currentInventRecord.quantity = Integer.parseInt(line.substring(25, 28).trim());
                    String priceStr = line.substring(28, 33).trim();
                    currentInventRecord.unitPrice = Double.parseDouble(priceStr) / 100.0;
                    currentInventRecord.supplierCode = line.substring(33, 38);
                    if (line.length() >= 41) {
                        currentInventRecord.reOrderPoint = Integer.parseInt(line.substring(38, 41).trim());
                    } else {
                        currentInventRecord.reOrderPoint = 0;
                    }
                }
            }
        } catch (IOException e) {
            invEofFlag = "YES";
        } catch (NumberFormatException e) {
            invEofFlag = "YES";
        }
    }
    
    private static void searchSupplierRecord() {
        if (supplierTable[sub - 1].tblSupplierCode.equals(currentInventRecord.supplierCode)) {
            foundFlag = "YES";
            wsSupplierNameOut = supplierTable[sub - 1].supplierNameTbl;
        }
    }
    
    private static void calculateInventoryValue() {
        inventoryValue = (long)(currentInventRecord.quantity * currentInventRecord.unitPrice * 100);
    }
    
    private static void calculateTotalValue() {
        totalValue += inventoryValue;
    }
    
    private static void checkForReorder() {
        if (currentInventRecord.quantity <= currentInventRecord.reOrderPoint) {
            writeReorderRecord();
        }
    }
    
    private static void writeInventoryRecord() {
        try {
            String partNumberStr = String.format("%07d", currentInventRecord.partNumber);
            String partNameStr = String.format("%-20s", currentInventRecord.partName);
            String quantityStr = String.format("%3d", currentInventRecord.quantity);
            double valueInDollars = inventoryValue / 100.0;
            String valueStr = moneyFormat.format(valueInDollars);
            
            String detailLine = String.format("%s %s     %s   %14s",
                partNumberStr, partNameStr, quantityStr, valueStr);
            
            inventReportOut.write(detailLine);
            inventReportOut.newLine();
            auditWritCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printAuditCounter() {
        System.out.println("Inventory records read: " + auditReadCounter);
        System.out.println("Inventory records written: " + auditWritCounter);
        double totalValueInDollars = totalValue / 100.0;
        System.out.println("Total value" + moneyFormat.format(totalValueInDollars));
    }
    
    private static void writeReorderRecord() {
        try {
            String reorderLine = String.format