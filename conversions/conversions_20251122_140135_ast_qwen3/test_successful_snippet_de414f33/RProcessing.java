import java.io.*;
import java.text.DecimalFormat;

public class RProcessing {
    private static final String INPUT_FILE = "returns.out";
    private static final String OUTPUT_FILE = "rprocessing.out";
    
    private static class Record {
        char transactionCode;
        double transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
    }
    
    private static class ReportData {
        int totNumRRecords = 0;
        double totAmtRRecords = 0.0;
        double totTaxOwing = 0.0;
    }
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            
            // Print headings
            printHeadings(writer);
            
            ReportData reportData = new ReportData();
            int lineCount = 0;
            int pageNum = 0;
            final int LINES_PER_PAGE = 20;
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 36) continue;
                
                Record record = parseRecord(line);
                
                if (record.transactionCode == 'R') {
                    // Calculate totals
                    reportData.totAmtRRecords += record.transactionAmount;
                    reportData.totNumRRecords++;
                    
                    // Calculate tax owing
                    double taxOwing = record.transactionAmount * 0.13;
                    reportData.totTaxOwing += taxOwing;
                    
                    // Write detail line
                    writeDetailLine(writer, record, reportData);
                    
                    lineCount++;
                    
                    // Check page break
                    if (lineCount >= LINES_PER_PAGE) {
                        printPageBreak(writer);
                        lineCount = 0;
                        pageNum++;
                    }
                }
            }
            
            // Write totals
            writeTotals(writer, reportData);
            
            // Write total tax owing
            writeTotalTaxOwing(writer, reportData);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Record parseRecord(String line) {
        Record record = new Record();
        record.transactionCode = line.charAt(0);
        record.transactionAmount = Double.parseDouble(line.substring(1, 6).trim().replace(",", ""));
        record.paymentType = line.substring(6, 8);
        record.storeNumber = Integer.parseInt(line.substring(8, 10).trim());
        record.invoiceNumber = line.substring(10, 19).trim();
        record.skuCode = line.substring(19, 34).trim();
        return record;
    }
    
    private static void printHeadings(PrintWriter writer) {
        writer.println("                           R PROCESSING REPORT");
        writer.println("TRANSACTION          TRANSACTION        PAYMENT     STORE     INVOICE           SKU       TAX");
        writer.println("CODE                 AMOUNT             TYPE      NUMBER    NUMBER            CODE      OWING");
        writer.println("-----------          -----------        -------     ------    -------           ----      -----");
    }
    
    private static void writeDetailLine(PrintWriter writer, Record record, ReportData reportData) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        DecimalFormat taxDf = new DecimalFormat("$#,##0.00");
        
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        sb.append(record.transactionCode);
        sb.append("               ");
        sb.append(df.format(record.transactionAmount));
        sb.append("          ");
        sb.append(record.paymentType);
        sb.append("         ");
        sb.append(String.format("%02d", record.storeNumber));
        sb.append("        ");
        sb.append(record.invoiceNumber);
        sb.append("         ");
        sb.append(record.skuCode);
        sb.append("     ");
        sb.append(taxDf.format(record.transactionAmount * 0.13));
        sb.append("\n");
        
        writer.print(sb.toString());
    }
    
    private static void printPageBreak(PrintWriter writer) {
        writer.println("\n\n\n");
        printHeadings(writer);
        writer.println();
    }
    
    private static void writeTotals(PrintWriter writer, ReportData reportData) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        writer.println("TOTAL NUMBER OF 'R' RECORDS: " + String.format("%02d", reportData.totNumRRecords) + 
                     "        TOTAL AMOUNT OF 'R' RECORDS: " + df.format(reportData.totAmtRRecords));
        writer.println();
    }
    
    private static void writeTotalTaxOwing(PrintWriter writer, ReportData reportData) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        writer.println("TOTAL TAX OWING: " + df.format(reportData.totTaxOwing));
        writer.println();
    }
}