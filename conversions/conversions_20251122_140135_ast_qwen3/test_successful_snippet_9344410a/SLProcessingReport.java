import java.io.*;
import java.util.*;

public class SLProcessingReport {
    private static final String INPUT_FILE_NAME = "SL.out";
    private static final String OUTPUT_FILE_NAME = "SLProcessing.out";
    
    // Input record structure
    private static class InputLine {
        char ilTransactionCode;
        double ilTransactionAmount;
        String ilPaymentType;
        int ilStoreNumber;
        String ilInvoiceNumber;
        String ilSkuCode;
    }
    
    // Output record structure
    private static class OutputLine {
        String content;
        
        public OutputLine(String content) {
            this.content = content;
        }
    }
    
    // Working storage variables
    private static boolean wsEofFlag = false;
    
    // Heading lines
    private static final String WS_HEADING_LINE1 = "                   S&L PROCESSING REPORT                    PAGE 1";
    private static final String WS_HEADING_LINE2 = "TRANSACTION       TRANSACTION       PAYMENT       STORE       INVOICE              SKU       TAX";
    private static final String WS_HEADING_LINE3 = "CODE              AMOUNT            TYPE          NUMBER      NUMBER               CODE      OWING";
    private static final String WS_UNDERLINES = "-----------       -----------       -------       ------      -------              ----      -----";
    
    // Detail line format
    private static String wsDetailLine = "     ";
    
    // Totals for S records
    private static int wsOlTotNumSRecords = 0;
    private static double wsOlTotAmountSRecords = 0.0;
    
    // Totals for L records
    private static int wsOlTotNumLRecords = 0;
    private static double wsOlTotAmountLRecords = 0.0;
    
    // Percentage calculations
    private static double wsOlTotPercntgCash = 0.0;
    private static double wsOlTotPercntgCredit = 0.0;
    private static double wsOlTotPercntgDebit = 0.0;
    private static String wsOlPercntSign1 = "%";
    private static String wsOlPercntSign2 = "%";
    private static String wsOlPercntSign3 = "%";
    
    // Tax calculations
    private static double wsOlTotTaxOwing = 0.0;
    
    // Store with highest transaction amount
    private static int wsOlStoreNumHighestAmount = 0;
    
    // Counters and accumulators
    private static int wsLineCount = 0;
    private static int wsPageNum = 2;
    private static final int WS_LINES_PER_PAGE = 20;
    
    private static double wsTaxOwing = 0.0;
    private static final double WS_TAX_VALUE = 0.13;
    private static int wsTotNumSRecords = 0;
    private static double wsTotAmountSRecords = 0.0;
    private static int wsTotNumLRecords = 0;
    private static double wsTotAmountLRecords = 0.0;
    private static double wsOverallTotAmount = 0.0;
    
    private static int wsTotNumCash = 0;
    private static int wsTotNumCredit = 0;
    private static int wsTotNumDebit = 0;
    private static int wsCount = 0;
    
    private static double wsTotAmountCash = 0.0;
    private static double wsTotAmountCredit = 0.0;
    private static double wsTotAmountDebit = 0.0;
    
    private static double wsTotPrcntCash = 0.0;
    private static double wsTotPrcntCredit = 0.0;
    private static double wsTotPrcntDebit = 0.0;
    
    private static double wsTotTaxOwing = 0.0;
    
    private static double[] wsTotStoreAmount = new double[9]; // 1-8 indices
    private static double wsSaveAmt = 0.0;
    private static int wsSub = 0;
    
    // Constants
    private static final char WS_SALES = 'S';
    private static final char WS_LAYAWAY = 'L';
    private static final String WS_PAYMENT_CASH = "CA";
    private static final String WS_PAYMENT_CREDIT = "CR";
    private static final String WS_PAYMENT_DEBIT = "DB";
    private static final String WS_LINE_BREAK = "                                                                                                   ";
    
    private static BufferedReader inputFileReader;
    private static PrintWriter outputFileWriter;
    
    public static void main(String[] args) {
        try {
            // Open files
            inputFileReader = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            outputFileWriter = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME));
            
            // Read initial record
            String line = inputFileReader.readLine();
            if (line == null) {
                wsEofFlag = true;
            } else {
                processInputLine(line);
            }
            
            printHeadings();
            
            while (!wsEofFlag) {
                processPage();
            }
            
            // Write totals
            writeOutputLine("     TOTAL NUMBER OF 'S' RECORDS: " + String.format("%2d", wsOlTotNumSRecords) + 
                          "     TOTAL AMOUNT OF 'S' RECORDS: " + String.format("$%,6.2f", wsOlTotAmountSRecords));
            writeOutputLine("");
            writeOutputLine("     TOTAL NUMBER OF 'L' RECORDS: " + String.format("%2d", wsOlTotNumLRecords) + 
                          "     TOTAL AMOUNT OF 'L' RECORDS: " + String.format("$%,6.2f", wsOlTotAmountLRecords));
            writeOutputLine("");
            writeOutputLine("     THE PERCENTAGE OF EACH PAYMENT TYPE: " + 
                          "CASH 'CA' = " + String.format("%5.2f", wsOlTotPercntgCash) + "%" +
                          "    CREDIT CARD 'CR' = " + String.format("%5.2f", wsOlTotPercntgCredit) + "%" +
                          "    DEBIT 'DB' = " + String.format("%5.2f", wsOlTotPercntgDebit) + "%");
            writeOutputLine("");
            writeOutputLine("     TOTAL TAX OWING: " + String.format("$%,6.2f", wsOlTotTaxOwing));
            writeOutputLine("");
            writeOutputLine("     STORE NUMBER (" + String.format("%2d", wsOlStoreNumHighestAmount) + 
                          " ) HAS HIGHEST TRANSACTION AMOUNT.");
            
            // Close files
            inputFileReader.close();
            outputFileWriter.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static void processInputLine(String line) {
        if (line.length() < 36) {
            return; // Invalid line
        }
        
        InputLine inputLine = new InputLine();
        inputLine.ilTransactionCode = line.charAt(0);
        inputLine.ilTransactionAmount = parseDecimal(line.substring(1, 7));
        inputLine.ilPaymentType = line.substring(7, 9);
        inputLine.ilStoreNumber = Integer.parseInt(line.substring(9, 11));
        inputLine.ilInvoiceNumber = line.substring(11, 20);
        inputLine.ilSkuCode = line.substring(20, 35);
        
        // Process the record
        processRecord(inputLine);
    }
    
    private static double parseDecimal(String str) {
        try {
            return Double.parseDouble(str.replace(",", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    private static void processRecord(InputLine inputLine) {
        // Set detail line fields
        wsDetailLine = "     " + inputLine.ilTransactionCode + "              " + 
                      String.format("$%,6.2f", inputLine.ilTransactionAmount) + "              " + 
                      inputLine.ilPaymentType + "             " + 
                      String.format("%2d", inputLine.ilStoreNumber) + "           " + 
                      inputLine.ilInvoiceNumber + "               " + 
                      inputLine.ilSkuCode + "       ";
        
        // Calculate tax owing
        wsTaxOwing = inputLine.ilTransactionAmount * WS_TAX_VALUE;
        wsDetailLine += String.format("$%,6.2f", wsTaxOwing);
        
        if (inputLine.ilTransactionCode == WS_SALES) {
            wsTotAmountSRecords += inputLine.ilTransactionAmount;
            wsOlTotAmountSRecords = wsTotAmountSRecords;
            wsTotNumSRecords++;
            wsOlTotNumSRecords = wsTotNumSRecords;
            wsLineCount++;
            wsCount++;
            calculatePercentageOfEachPaymentType(inputLine);
            wsOlTaxOwing = wsTaxOwing;
            writeOutputLine(wsDetailLine);
        } else if (inputLine.ilTransactionCode == WS_LAYAWAY) {
            wsTotAmountLRecords += inputLine.ilTransactionAmount;
            wsOlTotAmountLRecords = wsTotAmountLRecords;
            wsLineCount++;
            wsTotNumLRecords++;
            wsOlTotNumLRecords = wsTotNumLRecords;
            wsCount++;
            calculatePercentageOfEachPaymentType(inputLine);
            wsOlTaxOwing = wsTaxOwing;
            writeOutputLine(wsDetailLine);
        }
        
        wsOverallTotAmount = wsTotAmountSRecords + wsTotAmountLRecords;
        
        // Check page break
        if (wsLineCount >= WS_LINES_PER_PAGE) {
            printHeadings();