import java.io.*;
import java.util.*;

public class SLProcessingReport {
    private static final String INPUT_FILE = "SL.out";
    private static final String OUTPUT_FILE = "SLProcessing.out";
    
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
        String content;
        
        public OutputRecord(String content) {
            this.content = content;
        }
    }
    
    // Global variables
    private static boolean eofFlag = false;
    private static int pageNumber = 1;
    private static int lineCount = 0;
    private static final int LINES_PER_PAGE = 20;
    
    // Totals for S records
    private static int totNumSRecords = 0;
    private static double totAmountSRecords = 0.0;
    
    // Totals for L records
    private static int totNumLRecords = 0;
    private static double totAmountLRecords = 0.0;
    
    // Payment type counts
    private static int totNumCash = 0;
    private static int totNumCredit = 0;
    private static int totNumDebit = 0;
    private static int count = 0;
    
    // Payment type amounts
    private static double totAmountCash = 0.0;
    private static double totAmountCredit = 0.0;
    private static double totAmountDebit = 0.0;
    
    // Percentages
    private static double totPrcntCash = 0.0;
    private static double totPrcntCredit = 0.0;
    private static double totPrcntDebit = 0.0;
    
    // Tax calculations
    private static final double TAX_VALUE = 0.13;
    private static double totTaxOwing = 0.0;
    
    // Store amounts
    private static double[] totStoreAmount = new double[9]; // index 1-8
    private static int saveAmt = 0;
    private static int sub = 0;
    
    // File handles
    private static BufferedReader inputFile;
    private static PrintWriter outputFile;
    
    // Constants
    private static final String SALES_CODE = "S";
    private static final String LAYAWAY_CODE = "L";
    private static final String CASH_PAYMENT = "CA";
    private static final String CREDIT_PAYMENT = "CR";
    private static final String DEBIT_PAYMENT = "DB";
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            // Read first record
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = true;
            } else {
                processInputLine(line);
            }
            
            printHeadings();
            
            while (!eofFlag) {
                processPage();
            }
            
            // Write final totals
            writeTotalsForSRecords();
            outputFile.println(); // 2 lines
            outputFile.println();
            
            writeTotalsForLRecords();
            outputFile.println(); // 2 lines
            outputFile.println();
            
            writePercentageOfEachPaymentType();
            outputFile.println(); // 2 lines
            outputFile.println();
            
            writeTotalTaxOwing();
            outputFile.println(); // 2 lines
            outputFile.println();
            
            writeStoreNumHighestTransAmount();
            
            // Close files
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static void processInputLine(String line) {
        if (line.length() < 36) return;
        
        InputRecord record = new InputRecord();
        record.transactionCode = line.charAt(0);
        record.transactionAmount = Double.parseDouble(line.substring(1, 6).replace(",", ""));
        record.paymentType = line.substring(6, 8);
        record.storeNumber = Integer.parseInt(line.substring(8, 10));
        record.invoiceNumber = line.substring(10, 19);
        record.skuCode = line.substring(19, 34);
        
        // Process record
        processRecord(record);
    }
    
    private static void processRecord(InputRecord record) {
        // Calculate totals for 'S' and 'L' records
        if (record.transactionCode == SALES_CODE.charAt(0)) {
            totAmountSRecords += record.transactionAmount;
            totNumSRecords++;
            count++;
            
            // Calculate percentages
            calculatePercentageOfEachPaymentType(record);
            
            // Calculate tax owing
            double taxOwing = record.transactionAmount * TAX_VALUE;
            totTaxOwing += taxOwing;
            
            // Write detail line
            writeDetailLine(record, taxOwing);
            
        } else if (record.transactionCode == LAYAWAY_CODE.charAt(0)) {
            totAmountLRecords += record.transactionAmount;
            totNumLRecords++;
            count++;
            
            // Calculate percentages
            calculatePercentageOfEachPaymentType(record);
            
            // Calculate tax owing
            double taxOwing = record.transactionAmount * TAX_VALUE;
            totTaxOwing += taxOwing;
            
            // Write detail line
            writeDetailLine(record, taxOwing);
        }
        
        // Update overall total
        double overallTotal = totAmountSRecords + totAmountLRecords;
        
        // Handle page breaks
        if (lineCount >= LINES_PER_PAGE) {
            printHeadings();
            lineCount = 0;
            pageNumber++;
        }
    }
    
    private static void processPage() {
        try {
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = true;
                return;
            }
            
            processInputLine(line);
            lineCount++;
            
        } catch (IOException e) {
            eofFlag = true;
        }
    }
    
    private static void printHeadings() {
        // Heading line 1
        StringBuilder heading1 = new StringBuilder();
        heading1.append(" ".repeat(30));
        heading1.append("S&L PROCESSING REPORT");
        heading1.append(" ".repeat(19));
        heading1.append("PAGE ");
        heading1.append(pageNumber);
        heading1.append("   ");
        outputFile.println(heading1.toString());
        
        // Heading line 2
        StringBuilder heading2 = new StringBuilder();
        heading2.append(" ");
        heading2.append("TRANSACTION".substring(0, 11));
        heading2.append("  ");
        heading2.append("TRANSACTION".substring(0, 11));
        heading2.append("  ");
        heading2.append("PAYMENT".substring(0, 7));
        heading2.append("  ");
        heading2.append("STORE".substring(0, 6));
        heading2.append("   ");
        heading2.append("INVOICE".substring(0, 7));
        heading2.append("          ");
        heading2.append("SKU".substring(0, 3));
        heading2.append("   ");
        heading2.append("TAX".substring(0, 5));
        outputFile.println(heading2.toString());
        
        // Heading line 3
        StringBuilder heading3 = new StringBuilder();
        heading3.append("    ");
        heading3.append("CODE".substring(0, 4));
        heading3.append("        ");
        heading3.append("AMOUNT".substring(0, 6));
        heading3.append("     ");
        heading3.append("TYPE".substring(0, 4));
        heading3.append("      ");
        heading3.append("NUMBER".substring(0, 6));
        heading3.append("  ");
        heading3.append("NUMBER".substring(0, 6));
        heading3.append("          ");
        heading3.append("CODE".substring(0, 4));
        heading3.append("         ");
        heading3.append("OWING".substring(0, 5));
        outputFile.println(heading3.toString());
        
        // Underlines
        StringBuilder underlines = new StringBuilder();
        underlines.append(" ");
        underlines.append("-----------".substring(0, 11));
        underlines.append("  ");
        underlines.append("-----------".substring(0, 11));
        underlines.append("  ");
        underlines.append("-------".substring(0, 7));
        underlines.append("  ");
        underlines.append("------".substring(0, 6));
        underlines.append("  ");
        underlines.append("-------".substring(0, 7));
        underlines.append("          ");
        underlines.append("----".substring(0, 4));
        underlines.append("         ");
        underlines.append("-----".substring(0, 5));
        outputFile.println(underlines.toString());
    }
    
    private static void writeDetailLine(InputRecord record, double taxOwing) {
        StringBuilder detailLine = new StringBuilder();
        detailLine.append("     ");
        detailLine.append(record.transactionCode);
        detailLine.append("        ");
        detailLine.append(String.format("%1$,.2f", record.transactionAmount));
        detailLine.append("    ");
        detailLine.append(record.paymentType);
        detailLine.append("       ");
        detailLine.append(String.format("%02d", record.storeNumber));
        detailLine.append("    ");
        detailLine.append(record.invoiceNumber);
        detailLine.append("           ");
        detailLine.append(record.skuCode);
        detailLine.append("    ");
        detailLine.append(String.format("%1$,.2f", tax