import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PROJECT1 {
    private static final String SOURCE_FILE_PATH = "PR1FA17.txt";
    private static final String REPORT_FILE_PATH = "report.txt";
    
    private static String eofFlag = "YES";
    private static int properSpacing = 0;
    private static int lineNum = 10;
    private static int page = 1;
    
    private static int wsMonth = 0;
    private static int wsDay = 0;
    private static int wsYear = 0;
    
    private static int hlMonth = 0;
    private static int hlDay = 0;
    private static int hlYear = 0;
    private static int hlPageNum = 1;
    
    private static BufferedReader sourceFileReader;
    private static PrintWriter reportFileWriter;
    
    public static void main(String[] args) {
        try {
            perform100MainModule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void perform100MainModule() throws IOException {
        perform125Housekeeping();
        perform150ReadSourceFile();
        perform250CloseRoutine();
    }
    
    private static void perform125Housekeeping() throws IOException {
        hlPageNum = 1;
        sourceFileReader = new BufferedReader(new FileReader(SOURCE_FILE_PATH));
        reportFileWriter = new PrintWriter(new FileWriter(REPORT_FILE_PATH));
        perform130DateRoutine();
        perform145HeadingRoutine();
    }
    
    private static void perform130DateRoutine() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String dateString = sdf.format(date);
        String[] parts = dateString.split("/");
        wsMonth = Integer.parseInt(parts[0]);
        wsDay = Integer.parseInt(parts[1]);
        wsYear = Integer.parseInt(parts[2]);
        
        hlMonth = wsMonth;
        hlDay = wsDay;
        hlYear = wsYear;
    }
    
    private static void perform145HeadingRoutine() throws IOException {
        properSpacing = 1;
        writeReportLine(buildHeadingLine1(), properSpacing);
        properSpacing = 3;
        writeReportLine(buildHeadingLine2(), properSpacing);
        properSpacing = 2;
        writeReportLine(buildHeadingLine3(), properSpacing);
        properSpacing = 1;
        writeReportLine(buildHeadingLine4(), properSpacing);
        properSpacing = 1;
        writeReportLine("", properSpacing);
    }
    
    private static String buildHeadingLine1() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", hlMonth)).append("/")
          .append(String.format("%02d", hlDay)).append("/20")
          .append(String.format("%02d", hlYear)).append("     ")
          .append("TSB                ")
          .append("Drakea Cart Parts Warehouse               ")
          .append("         PAGE ").append(String.format("%02d", hlPageNum));
        return sb.toString();
    }
    
    private static String buildHeadingLine2() {
        StringBuilder sb = new StringBuilder();
        sb.append("                                    ");
        sb.append("STOCK REPORT");
        return sb.toString();
    }
    
    private static String buildHeadingLine3() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CAT   ");
        sb.append("  ITEM   ");
        sb.append("PURCHASE  ");
        sb.append("QUANTITY   ");
        sb.append("QUANTITY   ");
        sb.append("REORDER");
        return sb.toString();
    }
    
    private static String buildHeadingLine4() {
        StringBuilder sb = new StringBuilder();
        sb.append(" NUM   ");
        sb.append("DESCRIPTION       ");
        sb.append("PRICE   ");
        sb.append("IN STK     ");
        sb.append("ON ORDER   ");
        sb.append("POINT");
        return sb.toString();
    }
    
    private static void perform150ReadSourceFile() throws IOException {
        String line;
        while ((line = sourceFileReader.readLine()) != null) {
            if (eofFlag.equals("NO")) break;
            
            processInventoryRecord(line);
            lineNum++;
            
            if (lineNum == 55) {
                perform225NewPage();
                lineNum = 10;
            }
        }
    }
    
    private static void processInventoryRecord(String record) {
        if (record.length() < 50) return;
        
        String catalogNum = record.substring(0, 5).trim();
        String description = record.substring(5, 25).trim();
        String unitPurchasePriceStr = record.substring(25, 32).trim();
        String quantityOnHandStr = record.substring(32, 37).trim();
        String quantityOnOrderStr = record.substring(37, 42).trim();
        String reorderPointStr = record.substring(42, 47).trim();
        
        double unitPurchasePrice = 0.0;
        double quantityOnHand = 0.0;
        double quantityOnOrder = 0.0;
        double reorderPoint = 0.0;
        
        try {
            unitPurchasePrice = Double.parseDouble(unitPurchasePriceStr);
            quantityOnHand = Double.parseDouble(quantityOnHandStr);
            quantityOnOrder = Double.parseDouble(quantityOnOrderStr);
            reorderPoint = Double.parseDouble(reorderPointStr);
        } catch (NumberFormatException e) {
            // Handle parsing errors if needed
        }
        
        // Build detail line
        StringBuilder detailLine = new StringBuilder();
        detailLine.append(String.format("%-5s", catalogNum));
        detailLine.append("   ");
        detailLine.append(String.format("%-20s", description));
        detailLine.append("   $");
        detailLine.append(String.format("%6.2f", unitPurchasePrice));
        detailLine.append("    ");
        detailLine.append(String.format("%6.2f", quantityOnHand));
        detailLine.append("      ");
        detailLine.append(String.format("%6.2f", quantityOnOrder));
        detailLine.append("     ");
        detailLine.append(String.format("%5.2f", reorderPoint));
        
        properSpacing = 1;
        writeReportLine(detailLine.toString(), properSpacing);
    }
    
    private static void perform225NewPage() throws IOException {
        hlPageNum++;
        writeReportLine("", 1); // Blank line for page break
        perform145HeadingRoutine();
    }
    
    private static void perform250CloseRoutine() throws IOException {
        sourceFileReader.close();
        reportFileWriter.close();
    }
    
    private static void writeReportLine(String content, int spacing) throws IOException {
        for (int i = 0; i < spacing; i++) {
            reportFileWriter.println();
        }
        reportFileWriter.println(content);
        reportFileWriter.flush();
    }
}