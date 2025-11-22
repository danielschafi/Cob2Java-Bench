import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CST8283_PROJECT2 {
    private static final String INVENT_FILE_IN = "INVENT.TXT";
    private static final String SUPPLIER_FILE_IN = "SUPPLIERS.TXT";
    private static final String INVENT_REPORT_OUT = "INVREPRT.TXT";
    private static final String REORDER_REPORT_OUT = "INVREORD.TXT";

    private static final int MAX_SUPPLIERS = 1000;

    private static class InventoryRecord {
        int partNumber;
        String partName;
        int quantity;
        double unitPrice;
        String supplierCode;
        int reorderPoint;
    }

    private static class SupplierRecord {
        String supplierCode;
        String supplierName;
    }

    private static class SupplierTableRecord {
        String supplierCode;
        String supplierName;
    }

    private static class FlagsAndCounters {
        boolean invEofFlag;
        boolean supEofFlag;
        boolean foundFlag;
        int sub;
        double inventoryValue;
        int auditReadCounter;
        int auditWriteCounter;
    }

    private static class InventoryDetailLine {
        int partNumber;
        String partName;
        int quantity;
        double totalValue;
    }

    private static class ReorderReport {
        int partNumber;
        String partName;
        int reorderPoint;
        String supplierName;
    }

    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$#,###,##0.00");

    private static List<SupplierTableRecord> supplierTable = new ArrayList<>();
    private static FlagsAndCounters flagsAndCounters = new FlagsAndCounters();

    public static void main(String[] args) {
        manageInventory();
    }

    private static void manageInventory() {
        initializeProduceInventoryReport();
        while (!flagsAndCounters.invEofFlag) {
            processInventoryReport();
        }
        terminateProcess();
    }

    private static void initializeProduceInventoryReport() {
        openFiles();
        loadSupplierTable();
        writeHeading();
    }

    private static void processInventoryReport() {
        readInventoryRecord();
        if (!flagsAndCounters.invEofFlag) {
            searchSupplierRecord();
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
            new BufferedReader(new FileReader(INVENT_FILE_IN));
            new BufferedReader(new FileReader(SUPPLIER_FILE_IN));
            new BufferedWriter(new FileWriter(INVENT_REPORT_OUT));
            new BufferedWriter(new FileWriter(REORDER_REPORT_OUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSupplierTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE_IN))) {
            String line;
            while ((line = reader.readLine()) != null && flagsAndCounters.sub <= MAX_SUPPLIERS) {
                SupplierRecord supplierRecord = parseSupplierRecord(line);
                SupplierTableRecord supplierTableRecord = new SupplierTableRecord();
                supplierTableRecord.supplierCode = supplierRecord.supplierCode;
                supplierTableRecord.supplierName = supplierRecord.supplierName;
                supplierTable.add(supplierTableRecord);
                flagsAndCounters.sub++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SupplierRecord parseSupplierRecord(String line) {
        SupplierRecord supplierRecord = new SupplierRecord();
        supplierRecord.supplierCode = line.substring(0, 5).trim();
        supplierRecord.supplierName = line.substring(5, 20).trim();
        return supplierRecord;
    }

    private static void writeHeading() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENT_REPORT_OUT, true))) {
            writer.write(String.format("%-7s %-20s %-3s %-15s", "NUMBER", "PART NAME", "QTY", "VALUE"));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInventoryRecord() {
        flagsAndCounters.foundFlag = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENT_FILE_IN))) {
            String line = reader.readLine();
            if (line == null) {
                flagsAndCounters.invEofFlag = true;
            } else {
                flagsAndCounters.auditReadCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchSupplierRecord() {
        for (int i = 0; i < supplierTable.size(); i++) {
            if (supplierTable.get(i).supplierCode.equals(flagsAndCounters.supplierCode)) {
                flagsAndCounters.foundFlag = true;
                flagsAndCounters.supplierName = supplierTable.get(i).supplierName;
                break;
            }
        }
    }

    private static void calculateInventoryValue() {
        flagsAndCounters.inventoryValue = flagsAndCounters.quantity * flagsAndCounters.unitPrice;
    }

    private static void calculateTotalValue() {
        flagsAndCounters.totalValue += flagsAndCounters.inventoryValue;
    }

    private static void checkForReorder() {
        if (flagsAndCounters.quantity <= flagsAndCounters.reorderPoint) {
            writeReorderRecord();
        }
    }

    private static void writeInventoryRecord() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENT_REPORT_OUT, true))) {
            writer.write(String.format("%-7d %-20s %-3d %s", flagsAndCounters.partNumber, flagsAndCounters.partName, flagsAndCounters.quantity, MONEY_FORMAT.format(flagsAndCounters.inventoryValue)));
            writer.newLine();
            flagsAndCounters.auditWriteCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printAuditCounter() {
        System.out.println("Inventory records read: " + flagsAndCounters.auditReadCounter);
        System.out.println("Inventory records written: " + flagsAndCounters.auditWriteCounter);
        System.out.println("Total value: " + MONEY_FORMAT.format(flagsAndCounters.totalValue));
    }

    private static void writeReorderRecord() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REORDER_REPORT_OUT, true))) {
            writer.write(String.format("%-5d %-20s %-3d %-15s", flagsAndCounters.partNumber, flagsAndCounters.partName, flagsAndCounters.reorderPoint, flagsAndCounters.supplierName));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeFile() {
        try {
            new BufferedReader(new FileReader(INVENT_FILE_IN)).close();
            new BufferedReader(new FileReader(SUPPLIER_FILE_IN)).close();
            new BufferedWriter(new FileWriter(INVENT_REPORT_OUT)).close();
            new BufferedWriter(new FileWriter(REORDER_REPORT_OUT)).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}