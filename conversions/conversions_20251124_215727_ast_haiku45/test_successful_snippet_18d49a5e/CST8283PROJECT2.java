import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class CST8283PROJECT2 {
    static class SupplierRecord {
        String code;
        String name;
    }

    static class InventoryRecord {
        int partNumber;
        String partName;
        int quantity;
        double unitPrice;
        String supplierCode;
        int reorderPoint;
    }

    static SupplierRecord[] supplierTable = new SupplierRecord[1000];
    static String invEofFlag = "NO";
    static String supEofFlag = "NO";
    static String foundFlag = "NO";
    static int sub = 1;
    static long inventoryValue = 0;
    static int auditReadCounter = 0;
    static int auditWritCounter = 0;
    static long totalValue = 0;

    static BufferedReader inventFileIn;
    static BufferedReader supplierFileIn;
    static PrintWriter inventReportOut;
    static PrintWriter reorderReportOut;

    static InventoryRecord currentInventRecord;
    static String wsSupplierNameOut = "";

    public static void main(String[] args) {
        manageInventory();
    }

    static void manageInventory() {
        initializeProduceInventoryReport();
        while (!invEofFlag.equals("YES")) {
            processInventoryReport();
        }
        terminateProcess();
    }

    static void initializeProduceInventoryReport() {
        openFiles();
        for (sub = 1; sub <= 1000 && !supEofFlag.equals("YES"); sub++) {
            loadSupplierTable();
        }
        writeHeading();
    }

    static void processInventoryReport() {
        readInventoryRecord();
        if (invEofFlag.equals("NO")) {
            for (sub = 1; sub <= 1000 && !foundFlag.equals("YES"); sub++) {
                searchSupplierRecord();
            }
            calculateInventoryValue();
            calculateTotalValue();
            checkForReorder();
            writeInventoryRecord();
        }
    }

    static void terminateProcess() {
        printAuditCounter();
        closeFile();
    }

    static void openFiles() {
        try {
            inventFileIn = new BufferedReader(new FileReader("INVENT.TXT"));
            supplierFileIn = new BufferedReader(new FileReader("SUPPLIERS.TXT"));
            inventReportOut = new PrintWriter(new FileWriter("INVREPRT.TXT"));
            reorderReportOut = new PrintWriter(new FileWriter("INVREORD.TXT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadSupplierTable() {
        try {
            String line = supplierFileIn.readLine();
            if (line == null) {
                supEofFlag = "YES";
            } else {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    SupplierRecord record = new SupplierRecord();
                    record.code = parts[0].trim();
                    record.name = parts[1].trim();
                    supplierTable[sub - 1] = record;
                }
            }
        } catch (IOException e) {
            supEofFlag = "YES";
        }
    }

    static void writeHeading() {
        String heading = String.format("%-7s %-20s %6s %15s", "NUMBER", "PART NAME", "QTY", "VALUE");
        inventReportOut.println(heading);
    }

    static void readInventoryRecord() {
        foundFlag = "NO";
        try {
            String line = inventFileIn.readLine();
            if (line == null) {
                invEofFlag = "YES";
            } else {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    currentInventRecord = new InventoryRecord();
                    currentInventRecord.partNumber = Integer.parseInt(parts[0].trim());
                    currentInventRecord.partName = parts[1].trim();
                    currentInventRecord.quantity = Integer.parseInt(parts[2].trim());
                    currentInventRecord.unitPrice = Double.parseDouble(parts[3].trim());
                    currentInventRecord.supplierCode = parts[4].trim();
                    currentInventRecord.reorderPoint = Integer.parseInt(parts[5].trim());
                    auditReadCounter++;
                }
            }
        } catch (IOException e) {
            invEofFlag = "YES";
        }
    }

    static void searchSupplierRecord() {
        if (supplierTable[sub - 1] != null && 
            supplierTable[sub - 1].code.equals(currentInventRecord.supplierCode)) {
            foundFlag = "YES";
            wsSupplierNameOut = supplierTable[sub - 1].name;
        }
    }

    static void calculateInventoryValue() {
        inventoryValue = (long)(currentInventRecord.quantity * currentInventRecord.unitPrice);
    }

    static void calculateTotalValue() {
        totalValue += inventoryValue;
    }

    static void checkForReorder() {
        if (currentInventRecord.quantity <= currentInventRecord.reorderPoint) {
            writeReorderRecord();
        }
    }

    static void writeInventoryRecord() {
        DecimalFormat df = new DecimalFormat("$###,###,##0.00");
        String line = String.format("%-7d %-20s %6d %15s",
            currentInventRecord.partNumber,
            currentInventRecord.partName,
            currentInventRecord.quantity,
            df.format(inventoryValue / 100.0));
        inventReportOut.println(line);
        auditWritCounter++;
    }

    static void printAuditCounter() {
        System.out.println("Inventory records read: " + auditReadCounter);
        System.out.println("Inventory records written: " + auditWritCounter);
        DecimalFormat df = new DecimalFormat("$###,###,##0.00");
        System.out.println("Total value" + df.format(totalValue / 100.0));
    }

    static void writeReorderRecord() {
        String line = String.format("%-5d %-20s %3d %-15s",
            currentInventRecord.partNumber,
            currentInventRecord.partName,
            currentInventRecord.reorderPoint,
            wsSupplierNameOut);
        reorderReportOut.println(line);
    }

    static void closeFile() {
        try {
            if (inventFileIn != null) inventFileIn.close();
            if (supplierFileIn != null) supplierFileIn.close();
            if (inventReportOut != null) inventReportOut.close();
            if (reorderReportOut != null) reorderReportOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}