import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PROJECT1 {
    private static final String SOURCE_FILE = "PR1FA17.txt";
    private static final String REPORT_FILE = "report.txt";
    
    private String eofFlag = "YES";
    private int properSpacing = 0;
    private int lineNum = 10;
    private int pageNum = 1;
    
    private String catalogNum;
    private String description;
    private double unitPurchasePrice;
    private int quantityOnHand;
    private int quantityOnOrder;
    private int reorderPoint;
    
    private BufferedReader sourceReader;
    private PrintWriter reportWriter;
    
    public static void main(String[] args) {
        PROJECT1 program = new PROJECT1();
        program.mainModule();
    }
    
    private void mainModule() {
        housekeeping();
        readSourceFile();
        closeRoutine();
    }
    
    private void housekeeping() {
        pageNum = 1;
        try {
            sourceReader = new BufferedReader(new FileReader(SOURCE_FILE));
            reportWriter = new PrintWriter(new FileWriter(REPORT_FILE));
            dateRoutine();
            headingRoutine();
        } catch (IOException e) {
            System.err.println("Error opening files: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void dateRoutine() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String dateStr = sdf.format(new Date());
        String[] parts = dateStr.split("/");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];
        
        String headingLine1 = String.format("%s/%s/20%s     TSB          Drakea Cart Parts Warehouse                PAGE %02d",
            month, day, year, pageNum);
        reportWriter.println(headingLine1);
    }
    
    private void headingRoutine() {
        for (int i = 0; i < 2; i++) {
            reportWriter.println();
        }
        
        String headingLine2 = String.format("%36s%s", "", "STOCK REPORT");
        reportWriter.println(headingLine2);
        reportWriter.println();
        
        String headingLine3 = " CAT           ITEM           PURCHASE   QUANTITY    QUANTITY    REORDER";
        reportWriter.println(headingLine3);
        
        String headingLine4 = " NUM        DESCRIPTION        PRICE      IN STK     ON ORDER     POINT";
        reportWriter.println(headingLine4);
        
        reportWriter.println();
    }
    
    private void readSourceFile() {
        try {
            String line;
            while (eofFlag.equals("YES")) {
                line = sourceReader.readLine();
                if (line == null) {
                    eofFlag = "NO";
                } else {
                    parseInventoryRecord(line);
                    constructData();
                    lineNum++;
                    if (lineNum == 55) {
                        newPage();
                        lineNum = 10;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private void parseInventoryRecord(String line) {
        if (line.length() < 64) {
            line = String.format("%-64s", line);
        }
        
        catalogNum = line.substring(0, 5);
        description = line.substring(5, 25);
        String priceStr = line.substring(25, 30);
        String qtyHandStr = line.substring(37, 42);
        String qtyOrderStr = line.substring(42, 47);
        String reorderStr = line.substring(47, 52);
        
        try {
            unitPurchasePrice = Double.parseDouble(priceStr) / 100.0;
            quantityOnHand = Integer.parseInt(qtyHandStr.trim());
            quantityOnOrder = Integer.parseInt(qtyOrderStr.trim());
            reorderPoint = Integer.parseInt(reorderStr.trim());
        } catch (NumberFormatException e) {
            unitPurchasePrice = 0.0;
            quantityOnHand = 0;
            quantityOnOrder = 0;
            reorderPoint = 0;
        }
    }
    
    private void constructData() {
        String detailLine = String.format("%s   %-20s   $%6.2f    %6.2f      %6.2f     %6.2f",
            catalogNum,
            description,
            unitPurchasePrice,
            (double) quantityOnHand,
            (double) quantityOnOrder,
            (double) reorderPoint);
        
        reportWriter.println(detailLine);
    }
    
    private void newPage() {
        pageNum++;
        reportWriter.print("\f");
        dateRoutine();
        headingRoutine();
    }
    
    private void closeRoutine() {
        try {
            if (sourceReader != null) {
                sourceReader.close();
            }
            if (reportWriter != null) {
                reportWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing files: " + e.getMessage());
        }
    }
}