import java.io.*;
import java.util.*;

public class RProcessing {
    private static final String INPUT_FILE = "returns.out";
    private static final String OUTPUT_FILE = "rprocessing.out";
    private static final int LINES_PER_PAGE = 20;
    
    // Input record structure
    private static class InputRecord {
        char transactionCode;
        double transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
    }
    
    // Output record structure
    private static class OutputRecord {
        String line;
        
        public OutputRecord(String line) {
            this.line = line;
        }
    }
    
    private static PrintWriter outputFile;
    private static BufferedReader inputFile;
    
    // Working storage variables
    private static boolean eofFlag = false;
    private static int pageNum = 0;
    private static int lineCount = 0;
    private static double taxOwing = 0.0;
    private static final double TAX_VALUE = 0.13;
    private static int totNumRRRecords = 0;
    private static double totAmtRRRecords = 0.0;
    private static double totTaxOwing = 0.0;
    
    // Constants
    private static final char RETURNS_CODE = 'R';
    private static final String PAYMENT_CASH = "CA";
    private static final String PAYMENT_CREDIT = "CR";
    private static final String PAYMENT_DEBIT = "DB";
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            // Read first record
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = true;
            } else {
                processRecord(line);
            }
            
            printHeadings();
            
            while (!eofFlag) {
                processPage();
            }
            
            // Write totals
            writeLine(formatTotalsForRRecords());
            writeLine("");
            writeLine(formatTotalTaxOwing());
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void processPage() {
        if (lineCount >= LINES_PER_PAGE) {
            writeLine("");
            writeLine("");
            writeLine("");
            printHeadings();
            lineCount = 0;
            pageNum++;
        }
        
        if (lineCount < LINES_PER_PAGE && !eofFlag) {
            String currentLine = getCurrentLine();
            if (currentLine != null) {
                processRecord(currentLine);
            }
        }
    }
    
    private static void processRecord(String line) {
        if (line.length() < 36) return;
        
        InputRecord record = new InputRecord();
        record.transactionCode = line.charAt(0);
        record.transactionAmount = parseDecimal(line.substring(1, 6));
        record.paymentType = line.substring(6, 8);
        record.storeNumber = Integer.parseInt(line.substring(8, 10));
        record.invoiceNumber = line.substring(10, 19);
        record.skuCode = line.substring(19, 34).trim();
        
        if (record.transactionCode == RETURNS_CODE) {
            totAmtRRRecords += record.transactionAmount;
            totNumRRRecords++;
            
            // Format and write detail line
            String detailLine = formatDetailLine(record);
            writeLine(detailLine);
            
            // Calculate tax owing
            double taxOwing = calculateTaxOwing(record.transactionAmount);
            totTaxOwing += taxOwing;
            
            lineCount++;
        }
        
        // Read next record
        try {
            String nextLine = inputFile.readLine();
            if (nextLine == null) {
                eofFlag = true;
            } else {
                // Store current line for next processing
                setCurrentLine(nextLine);
            }
        } catch (IOException e) {
            eofFlag = true;
        }
    }
    
    private static double parseDecimal(String str) {
        try {
            return Double.parseDouble(str.replace(",", "").replace("$", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    private static String formatDetailLine(InputRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        sb.append(record.transactionCode);
        sb.append("      ");
        sb.append(String.format("%10.2f", record.transactionAmount));
        sb.append("    ");
        sb.append(record.paymentType);
        sb.append("       ");
        sb.append(String.format("%02d", record.storeNumber));
        sb.append("    ");
        sb.append(record.invoiceNumber);
        sb.append("    ");
        sb.append(record.skuCode);
        sb.append("     ");
        sb.append(String.format("%7.2f", calculateTaxOwing(record.transactionAmount)));
        return sb.toString();
    }
    
    private static double calculateTaxOwing(double amount) {
        return Math.round(amount * TAX_VALUE * 100.0) / 100.0;
    }
    
    private static String formatTotalsForRRecords() {
        StringBuilder sb = new StringBuilder();
        sb.append("                            TOTAL NUMBER OF 'R' RECORDS: ");
        sb.append(String.format("%2d", totNumRRRecords));
        sb.append("                    TOTAL AMOUNT OF 'R' RECORDS: ");
        sb.append(String.format("%10.2f", totAmtRRRecords));
        return sb.toString();
    }
    
    private static String formatTotalTaxOwing() {
        StringBuilder sb = new StringBuilder();
        sb.append("                      TOTAL TAX OWING: ");
        sb.append(String.format("%7.2f", totTaxOwing));
        return sb.toString();
    }
    
    private static void printHeadings() {
        writeLine("                           R PROCESSING REPORT");
        writeLine("TRANSACTION        TRANSACTION     PAYMENT    STORE    INVOICE        SKU      TAX");
        writeLine("CODE               AMOUNT          TYPE       NUMBER   NUMBER         CODE     OWING");
        writeLine("-----------        -----------     -------    ------   -------        ----     -----");
    }
    
    private static void writeLine(String line) {
        outputFile.println(line);
    }
    
    private static String getCurrentLine() {
        // This would need to be implemented with proper state management
        // For now, we'll just return null to indicate we're using the reader approach
        return null;
    }
    
    private static void setCurrentLine(String line) {
        // Implementation would require maintaining state between calls
        // For now, we're handling this through the BufferedReader approach
    }
}