import java.io.*;
import java.util.*;

public class CST8283Project2 {
    private static final String INVENT_FILE_IN = "INVENT.TXT";
    private static final String SUPPLIER_FILE_IN = "SUPPLIERS.TXT";
    private static final String INVENT_REPORT_OUT = "INVREPRT.TXT";
    private static final String REORDER_REPORT_OUT = "INVREORD.TXT";

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

    private static class InventoryReportRecord {
        String partNumber;
        String partName;
        String quantity;
        String totalValue;
    }

    private static class ReorderReportRecord {
        int partNumber;
        String partName;
        int reorderPoint;
        String supplierName;
    }

    private static List<SupplierRecord> supplierTable = new ArrayList<>();
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
        BufferedReader inventReader = new BufferedReader(new FileReader(INVENT_FILE_IN));
        String line;
        
        while ((line = inventReader.readLine()) != null) {
            String[] parts = line.split("\\s+", 6);
            if (parts.length < 6) continue;
            
            InventoryRecord record = new InventoryRecord();
            record.partNumber = Integer.parseInt(parts[0]);
            record.partName = parts[1];
            record.quantity = Integer.parseInt(parts[2]);
            record.unitPrice = Double.parseDouble(parts[3]);
            record.supplierCode = parts[4];
            record.reorderPoint = Integer.parseInt(parts[5]);
            
            // Find supplier name
            String supplierName = "";
            for (SupplierRecord supplier : supplierTable) {
                if (supplier.supplierCode.equals(record.supplierCode)) {
                    supplierName = supplier.supplierName;
                    break;
                }
            }
            
            // Calculate inventory value
            long inventoryValue = (long) Math.round(record.quantity * record.unitPrice);
            
            // Update total value
            totalValue += inventoryValue;
            
            // Check for reorder
            if (record.quantity <= record.reorderPoint) {
                writeReorderRecord(record.partNumber, record.partName, record.reorderPoint, supplierName);
            }
            
            // Write inventory record
            writeInventoryRecord(record.partNumber, record.partName, record.quantity, inventoryValue);
        }
        
        inventReader.close();
    }

    private static void terminateProcess() {
        System.out.println("Inventory records read: " + auditReadCounter);
        System.out.println("Inventory records written: " + auditWriteCounter);
        System.out.println("Total value $" + formatMoney(totalValue));
    }

    private static void openFiles() throws IOException {
        // Files are opened implicitly when reading/writing
    }

    private static void loadSupplierTable() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE_IN));
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+", 2);
            if (parts.length < 2) continue;
            
            SupplierRecord record = new SupplierRecord();
            record.supplierCode = parts[0];
            record.supplierName = parts[1];
            supplierTable.add(record);
        }
        
        reader.close();
    }

    private static void writeHeading() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(INVENT_REPORT_OUT));
        writer.println("NUMBER        PART NAME              QTY       VALUE");
        writer.close();
    }

    private static void writeInventoryRecord(int partNumber, String partName, int quantity, long inventoryValue) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(INVENT_REPORT_OUT, true));
        String formattedPartNumber = String.format("%07d", partNumber);
        String formattedQuantity = String.format("%3d", quantity);
        String formattedValue = formatMoney(inventoryValue);
        writer.printf("%-7s %-20s %s %s%n", formattedPartNumber, partName, formattedQuantity, formattedValue);
        writer.close();
        auditWriteCounter++;
    }

    private static void writeReorderRecord(int partNumber, String partName, int reorderPoint, String supplierName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(REORDER_REPORT_OUT, true));
        writer.printf("%-5d %-20s %3d %s%n", partNumber, partName, reorderPoint, supplierName);
        writer.close();
    }

    private static String formatMoney(long amount) {
        return String.format("$%,.2f", amount / 100.0);
    }
}