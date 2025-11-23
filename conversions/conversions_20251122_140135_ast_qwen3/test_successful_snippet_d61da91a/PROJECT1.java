import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PROJECT1 {
    private static final String SOURCE_FILE_PATH = "PR1FA17.txt";
    private static final String REPORT_FILE_PATH = "report.txt";

    // File records
    private static class InventoryRecord {
        String catalogNum;
        String description;
        double unitPurchasePrice;
        int quantityOnHand;
        int quantityOnOrder;
        int reorderPoint;
        int receivedWeekly;
        int soldWeekly;
        int returnedWeekly;
    }

    private static class ReportRecord {
        String content;
    }

    // Working storage variables
    private static boolean eofFlag = true;
    private static int properSpacing = 0;
    private static int lineNum = 10;
    private static int page = 1;
    private static String currentDate;

    // Data structures for headings and detail lines
    private static final String HEADING_LINE1 = "%02d/%02d/20%02d           TSB                      Drakea Cart Parts Warehouse                           PAGE %02d%n";
    private static final String HEADING_LINE2 = "                                       STOCK REPORT%n";
    private static final String HEADING_LINE3 = " CAT       ITEM          PURCHASE      QUANTITY      QUANTITY      REORDER%n";
    private static final String HEADING_LINE4 = " NUM       DESCRIPTION   PRICE         IN STK        ON ORDER      POINT%n";

    public static void main(String[] args) {
        try {
            PrintWriter reportWriter = new PrintWriter(new FileWriter(REPORT_FILE_PATH));
            
            // Initialize date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            currentDate = sdf.format(new Date());
            
            // Set up page number
            page = 1;
            
            // Write initial heading
            writeHeading(reportWriter);
            
            // Read and process source file
            BufferedReader reader = new BufferedReader(new FileReader(SOURCE_FILE_PATH));
            String line;
            eofFlag = false;
            
            while ((line = reader.readLine()) != null && !eofFlag) {
                if (line.length() >= 45) { // Ensure we have enough data
                    InventoryRecord record = parseInventoryRecord(line);
                    if (record != null) {
                        writeDetailLine(reportWriter, record);
                        lineNum++;
                        
                        if (lineNum == 55) {
                            newPage(reportWriter);
                            lineNum = 10;
                        }
                    }
                }
            }
            
            reader.close();
            reportWriter.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static InventoryRecord parseInventoryRecord(String line) {
        InventoryRecord record = new InventoryRecord();
        
        try {
            record.catalogNum = line.substring(0, 5).trim();
            record.description = line.substring(5, 25).trim();
            String priceStr = line.substring(25, 32).trim();
            record.unitPurchasePrice = Double.parseDouble(priceStr.replace(',', '.'));
            String qtyOnHandStr = line.substring(32, 37).trim();
            record.quantityOnHand = Integer.parseInt(qtyOnHandStr);
            String qtyOnOrderStr = line.substring(37, 42).trim();
            record.quantityOnOrder = Integer.parseInt(qtyOnOrderStr);
            String reorderPointStr = line.substring(42, 47).trim();
            record.reorderPoint = Integer.parseInt(reorderPointStr);
            String receivedWeeklyStr = line.substring(47, 51).trim();
            record.receivedWeekly = Integer.parseInt(receivedWeeklyStr);
            String soldWeeklyStr = line.substring(51, 55).trim();
            record.soldWeekly = Integer.parseInt(soldWeeklyStr);
            String returnedWeeklyStr = line.substring(55, 59).trim();
            record.returnedWeekly = Integer.parseInt(returnedWeeklyStr);
            
            return record;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static void writeHeading(PrintWriter writer) {
        String[] parts = currentDate.split("/");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];
        
        writer.printf(HEADING_LINE1, Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(year), page);
        writer.print(HEADING_LINE2);
        writer.print(HEADING_LINE3);
        writer.print(HEADING_LINE4);
        writer.println();
    }
    
    private static void writeDetailLine(PrintWriter writer, InventoryRecord record) {
        // Format values properly
        String catalogNum = String.format("%-5s", record.catalogNum);
        String description = String.format("%-20s", record.description);
        String unitPrice = String.format("$%.2f", record.unitPurchasePrice);
        String stock = String.format("%.2f", (double)record.quantityOnHand);
        String ordered = String.format("%.2f", (double)record.quantityOnOrder);
        String reorder = String.format("%.2f", (double)record.reorderPoint);
        
        String detailLine = String.format("%s   %s   %s   %s   %s   %s%n",
                                          catalogNum, description, unitPrice, stock, ordered, reorder);
        
        writer.print(detailLine);
    }
    
    private static void newPage(PrintWriter writer) {
        page++;
        writer.println(); // blank line
        writer.println(); // blank line
        writeHeading(writer);
    }
}