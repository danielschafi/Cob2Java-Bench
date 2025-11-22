import java.io.*;
import java.util.*;

public class CST8283PROJECT2 {
    private static final String INVENT_FILE = "INVENT.TXT";
    private static final String SUPPLIERS_FILE = "SUPPLIERS.TXT";
    private static final String INVREPRT_FILE = "INVREPRT.TXT";
    private static final String INVREORD_FILE = "INVREORD.TXT";

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

    private static class ReorderRecord {
        int partNumber;
        String partName;
        int reorderPoint;
        String supplierName;
    }

    private static List<SupplierRecord> supplierTable = new ArrayList<>();
    private static List<InventoryRecord> inventoryRecords = new ArrayList<>();
    private static List<ReorderRecord> reorderRecords = new ArrayList<>();

    private static int auditReadCounter = 0;
    private static int auditWriteCounter = 0;
    private static long totalValue = 0;

    public static void main(String[] args) {
        try {
            initializeProduceInventoryReport();
            processInventoryReport();
            terminateProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeProduceInventoryReport() throws IOException {
        openFiles();
        loadSupplierTable();
        writeHeading();
    }

    private static void processInventoryReport() throws IOException {
        BufferedReader inventReader = new BufferedReader(new FileReader(INVENT_FILE));
        String line;
        while ((line = inventReader.readLine()) != null) {
            parseInventoryLine(line);
            if (inventoryRecords.size() > 0) {
                InventoryRecord record = inventoryRecords.get(inventoryRecords.size() - 1);
                searchSupplierRecord(record);
                calculateInventoryValue(record);
                calculateTotalValue(record);
                checkForReorder(record);
                writeInventoryRecord(record);
            }
        }
        inventReader.close();
    }

    private static void terminateProcess() {
        System.out.println("Inventory records read: " + auditReadCounter);
        System.out.println("Inventory records written: " + auditWriteCounter);
        System.out.println("Total value $" + formatMoney(totalValue));
        closeFiles();
    }

    private static void openFiles() throws IOException {
        // Files will be opened when reading/writing
    }

    private static void loadSupplierTable() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(SUPPLIERS_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() >= 20) {
                SupplierRecord record = new SupplierRecord();
                record.supplierCode = line.substring(0, 5).trim();
                record.supplierName = line.substring(5, 20).trim();
                supplierTable.add(record);
            }
        }
        reader.close();
    }

    private static void writeHeading() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(INVREPRT_FILE));
        writer.write("NUMBER PART NAME       QTY   VALUE");
        writer.newLine();
        writer.close();
    }

    private static void parseInventoryLine(String line) {
        if (line.length() >= 40) {
            InventoryRecord record = new InventoryRecord();
            record.partNumber = Integer.parseInt(line.substring(0, 5).trim());
            record.partName = line.substring(5, 25).trim();
            record.quantity = Integer.parseInt(line.substring(25, 28).trim());
            record.unitPrice = Double.parseDouble(line.substring(28, 33).trim());
            record.supplierCode = line.substring(33, 38).trim();
            record.reorderPoint = Integer.parseInt(line.substring(38, 41).trim());
            inventoryRecords.add(record);
            auditReadCounter++;
        }
    }

    private static void searchSupplierRecord(InventoryRecord record) {
        for (SupplierRecord supplier : supplierTable) {
            if (supplier.supplierCode.equals(record.supplierCode)) {
                record.supplierCode = supplier.supplierName;
                break;
            }
        }
    }

    private static void calculateInventoryValue(InventoryRecord record) {
        record.quantity = record.quantity * (int)record.unitPrice;
    }

    private static void calculateTotalValue(InventoryRecord record) {
        totalValue += record.quantity;
    }

    private static void checkForReorder(InventoryRecord record) {
        if (record.quantity <= record.reorderPoint) {
            writeReorderRecord(record);
        }
    }

    private static void writeInventoryRecord(InventoryRecord record) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(INVREPRT_FILE, true));
        String formattedPartNumber = String.format("%07d", record.partNumber);
        String formattedQuantity = String.format("%3d", record.quantity);
        String formattedValue = formatMoney(record.quantity);
        
        String outputLine = String.format("%-7s %-20s %s %s", 
            formattedPartNumber, 
            record.partName, 
            formattedQuantity, 
            formattedValue);
        
        writer.write(outputLine);
        writer.newLine();
        writer.close();
        auditWriteCounter++;
    }

    private static void writeReorderRecord(InventoryRecord record) throws IOException {
        ReorderRecord reorderRecord = new ReorderRecord();
        reorderRecord.partNumber = record.partNumber;
        reorderRecord.partName = record.partName;
        reorderRecord.reorderPoint = record.reorderPoint;
        reorderRecord.supplierName = record.supplierCode;
        reorderRecords.add(reorderRecord);

        BufferedWriter writer = new BufferedWriter(new FileWriter(INVREORD_FILE, true));
        String outputLine = String.format("%-5d %-20s %3d %-15s", 
            record.partNumber,
            record.partName,
            record.reorderPoint,
            record.supplierCode);
        writer.write(outputLine);
        writer.newLine();
        writer.close();
    }

    private static void closeFiles() {
        // All files are closed automatically
    }

    private static String formatMoney(long amount) {
        return String.format("$%,.2f", amount / 100.0);
    }
}