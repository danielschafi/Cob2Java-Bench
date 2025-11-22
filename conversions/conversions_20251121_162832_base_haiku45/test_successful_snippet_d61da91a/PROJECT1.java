import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PROJECT1 {
    private static final String SOURCE_FILE = "PR1FA17.txt";
    private static final String REPORT_FILE = "report.txt";
    
    private static String eofFlag = "YES";
    private static int properSpacing = 0;
    private static int lineNum = 10;
    
    private static int wsMonth;
    private static int wsDay;
    private static int wsYear;
    
    private static int hlPageNum = 1;
    
    private static BufferedReader sourceReader;
    private static PrintWriter reportWriter;
    
    public static void main(String[] args) {
        try {
            housekeeping();
            readSourceFile();
            closeRoutine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void housekeeping() throws IOException {
        hlPageNum = 1;
        sourceReader = new BufferedReader(new FileReader(SOURCE_FILE));
        reportWriter = new PrintWriter(new FileWriter(REPORT_FILE));
        dateRoutine();
        headingRoutine();
    }
    
    private static void dateRoutine() {
        LocalDate today = LocalDate.now();
        wsMonth = today.getMonthValue();
        wsDay = today.getDayOfMonth();
        wsYear = today.getYear() % 100;
    }
    
    private static void headingRoutine() {
        String headingLine1 = String.format("%02d/%02d/20%02d     TSB          Drakea Cart Parts Warehouse        PAGE %02d",
                wsMonth, wsDay, wsYear, hlPageNum);
        reportWriter.println(headingLine1);
        reportWriter.println();
        reportWriter.println();
        
        String headingLine2 = String.format("%36s%s", "", "STOCK REPORT");
        reportWriter.println(headingLine2);
        reportWriter.println();
        reportWriter.println();
        
        String headingLine3 = " CAT            ITEM            PURCHASE   QUANTITY    QUANTITY    REORDER";
        reportWriter.println(headingLine3);
        
        String headingLine4 = " NUM      DESCRIPTION      PRICE      IN STK    ON ORDER      POINT";
        reportWriter.println(headingLine4);
        reportWriter.println();
    }
    
    private static void readSourceFile() throws IOException {
        eofFlag = "YES";
        String line;
        
        while ((line = sourceReader.readLine()) != null) {
            eofFlag = "NO";
            constructData(line);
            lineNum++;
            
            if (lineNum == 55) {
                newPage();
                lineNum = 10;
            }
        }
    }
    
    private static void constructData(String line) {
        if (line.length() < 67) {
            return;
        }
        
        String catalogNum = line.substring(0, 5).trim();
        String description = line.substring(5, 25).trim();
        String unitPriceStr = line.substring(25, 30).trim();
        String quantityOnHandStr = line.substring(37, 42).trim();
        String quantityOnOrderStr = line.substring(42, 47).trim();
        String reorderPointStr = line.substring(47, 52).trim();
        
        double unitPrice = 0;
        int quantityOnHand = 0;
        int quantityOnOrder = 0;
        int reorderPoint = 0;
        
        try {
            unitPrice = Double.parseDouble(unitPriceStr) / 100.0;
            quantityOnHand = Integer.parseInt(quantityOnHandStr);
            quantityOnOrder = Integer.parseInt(quantityOnOrderStr);
            reorderPoint = Integer.parseInt(reorderPointStr);
        } catch (NumberFormatException e) {
            return;
        }
        
        String detailLine = String.format("%-5s   %-20s   $%6.2f    %7.2f      %7.2f      %7.2f",
                catalogNum, description, unitPrice, (double)quantityOnHand, 
                (double)quantityOnOrder, (double)reorderPoint);
        
        reportWriter.println(detailLine);
    }
    
    private static void newPage() {
        hlPageNum++;
        reportWriter.println("\f");
        headingRoutine();
    }
    
    private static void closeRoutine() {
        if (sourceReader != null) {
            try {
                sourceReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (reportWriter != null) {
            reportWriter.close();
        }
    }
}