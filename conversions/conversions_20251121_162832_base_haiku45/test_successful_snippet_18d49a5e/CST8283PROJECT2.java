import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class CST8283PROJECT2 {
    
    static class SupplierRecord {
        String supplierCode;
        String supplierName;
        
        SupplierRecord(String code, String name) {
            this.supplierCode = code;
            this.supplierName = name;
        }
    }
    
    static class InventoryRecord {
        int partNumber;
        String partName;
        int quantity;
        double unitPrice;
        String supplierCode;
        int reorderPoint;
    }
    
    static List<SupplierRecord> supplierTable = new ArrayList<>();
    static long totalValue = 0;
    static int auditReadCounter = 0;
    static int auditWriteCounter = 0;
    static String foundSupplierName = "";
    
    public static void main(String[] args) {
        try {
            manageInventory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void manageInventory() throws IOException {
        initializeProduceInventoryReport();
        processInventoryReport();
        terminateProcess();
    }
    
    static void initializeProduceInventoryReport() throws IOException {
        openFiles();
        loadSupplierTable();
        writeHeading();
    }
    
    static void processInventoryReport() throws IOException {
        BufferedReader inventoryReader = new BufferedReader(new FileReader("INVENT.TXT"));
        String line;
        
        while ((line = inventoryReader.readLine()) != null) {
            InventoryRecord record = parseInventoryRecord(line);
            if (record != null) {
                searchSupplierRecord(record.supplierCode);
                long inventoryValue = calculateInventoryValue(record.quantity, record.unitPrice);
                calculateTotalValue(inventoryValue);
                checkForReorder(record);
                writeInventoryRecord(record, inventoryValue);
            }
        }
        inventoryReader.close();
    }
    
    static void terminateProcess() throws IOException {
        printAuditCounter();
        closeFiles();
    }
    
    static void openFiles() throws IOException {
    }
    
    static void loadSupplierTable() throws IOException {
        BufferedReader supplierReader = new BufferedReader(new FileReader("SUPPLIERS.TXT"));
        String line;
        
        while ((line = supplierReader.readLine()) != null && supplierTable.size() < 1000) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                String code = parts[0].trim();
                String name = parts[1].trim();
                supplierTable.add(new SupplierRecord(code, name));
            }
        }
        supplierReader.close();
    }
    
    static void writeHeading() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("INVREPRT.TXT"));
        writer.write(String.format("%-7s %-20s %6s %15s", "NUMBER", "PART NAME", "QTY", "VALUE"));
        writer.newLine();
        writer.close();
    }
    
    static InventoryRecord parseInventoryRecord(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 6) return null;
            
            InventoryRecord record = new InventoryRecord();
            record.partNumber = Integer.parseInt(parts[0].trim());
            record.partName = parts[1].trim();
            record.quantity = Integer.parseInt(parts[2].trim());
            record.unitPrice = Double.parseDouble(parts[3].trim());
            record.supplierCode = parts[4].trim();
            record.reorderPoint = Integer.parseInt(parts[5].trim());
            
            auditReadCounter++;
            return record;
        } catch (Exception e) {
            return null;
        }
    }
    
    static void searchSupplierRecord(String supplierCode) {
        foundSupplierName = "";
        for (SupplierRecord record : supplierTable) {
            if (record.supplierCode.equals(supplierCode)) {
                foundSupplierName = record.supplierName;
                break;
            }
        }
    }
    
    static long calculateInventoryValue(int quantity, double unitPrice) {
        return Math.round(quantity * unitPrice * 100);
    }
    
    static void calculateTotalValue(long inventoryValue) {
        totalValue += inventoryValue;
    }
    
    static void checkForReorder(InventoryRecord record) throws IOException {
        if (record.quantity <= record.reorderPoint) {
            writeReorderRecord(record);
        }
    }
    
    static void writeInventoryRecord(InventoryRecord record, long inventoryValue) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("INVREPRT.TXT", true));
        double value = inventoryValue / 100.0;
        DecimalFormat df = new DecimalFormat("$###,###,##0.00");
        String formattedValue = df.format(value);
        
        String line = String.format("%-7d %-20s %6d %15s", 
            record.partNumber, record.partName, record.quantity, formattedValue);
        writer.write(line);
        writer.newLine();
        writer.close();
        
        auditWriteCounter++;
    }
    
    static void writeReorderRecord(InventoryRecord record) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("INVREORD.TXT", true));
        String line = String.format("%-5d %-20s %3d %-15s",
            record.partNumber, record.partName, record.reorderPoint, foundSupplierName);
        writer.write(line);
        writer.newLine();
        writer.close();
    }
    
    static void printAuditCounter() {
        System.out.println("Inventory records read: " + auditReadCounter);
        System.out.println("Inventory records written: " + auditWriteCounter);
        double totalValueFormatted = totalValue / 100.0;
        DecimalFormat df = new DecimalFormat("$###,###,##0.00");
        System.out.println("Total value" + df.format(totalValueFormatted));
    }
    
    static void closeFiles() throws IOException {
    }
}