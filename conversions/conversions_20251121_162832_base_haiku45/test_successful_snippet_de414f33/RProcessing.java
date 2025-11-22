import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;

public class RProcessing {
    
    private static final String INPUT_FILE = "returns.out";
    private static final String OUTPUT_FILE = "rprocessing.out";
    private static final int LINES_PER_PAGE = 20;
    private static final String RETURNS = "R";
    private static final String PAYMENT_CASH = "CA";
    private static final String PAYMENT_CREDIT = "CR";
    private static final String PAYMENT_DEBIT = "DB";
    private static final BigDecimal TAX_VALUE = new BigDecimal("0.13");
    
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private String eofFlag = "N";
    private int lineCount = 0;
    private int pageNum = 0;
    private int totNumRRecords = 0;
    private BigDecimal totAmtRRecords = BigDecimal.ZERO;
    private BigDecimal totTaxOwing = BigDecimal.ZERO;
    
    private String headingLine1 = String.format("%30s%-21s%19s", "", "R PROCESSING REPORT", "");
    private String headingLine2 = String.format(" %-11s  %-11s  %-7s  %-5s   %-7s          %-3s             %-3s", 
        "TRANSACTION", "TRANSACTION", "PAYMENT", "STORE", "INVOICE", "SKU", "TAX");
    private String headingLine3 = String.format("    %-4s        %-6s     %-4s     %-6s  %-7s          %-4s             %-5s", 
        "CODE", "AMOUNT", "TYPE", "NUMBER", "NUMBER", "CODE", "OWING");
    private String underlines = " -----------  -----------  -------  ------  -------          ----             -----";
    
    public static void main(String[] args) {
        RProcessing processor = new RProcessing();
        processor.run();
    }
    
    public void run() {
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
            outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            String inputLine = inputReader.readLine();
            if (inputLine == null) {
                eofFlag = "Y";
            }
            
            printHeadings();
            
            while (!eofFlag.equals("Y")) {
                processPage(inputLine);
                inputLine = inputReader.readLine();
                if (inputLine == null) {
                    eofFlag = "Y";
                }
            }
            
            String totalsLine = String.format(" TOTAL NUMBER OF 'R' RECORDS:  %2d          TOTAL AMOUNT OF 'R' RECORDS:  %10.2f", 
                totNumRRecords, totAmtRRecords);
            outputWriter.write(totalsLine);
            outputWriter.newLine();
            outputWriter.newLine();
            
            String taxLine = String.format(" TOTAL TAX OWING:  %9.2f", totTaxOwing);
            outputWriter.write(taxLine);
            outputWriter.newLine();
            
            inputReader.close();
            outputWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void printHeadings() throws IOException {
        outputWriter.write(headingLine1);
        outputWriter.newLine();
        outputWriter.write(headingLine2);
        outputWriter.newLine();
        outputWriter.write(headingLine3);
        outputWriter.newLine();
        outputWriter.write(underlines);
        outputWriter.newLine();
    }
    
    private void processPage(String inputLine) throws IOException {
        if (inputLine == null || inputLine.length() < 36) {
            return;
        }
        
        char transactionCode = inputLine.charAt(0);
        String transactionAmountStr = inputLine.substring(1, 8);
        String paymentType = inputLine.substring(8, 10);
        String storeNumberStr = inputLine.substring(10, 12);
        String invoiceNumber = inputLine.substring(12, 21);
        String skuCode = inputLine.substring(21, 36);
        
        BigDecimal transactionAmount = new BigDecimal(transactionAmountStr).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        int storeNumber = Integer.parseInt(storeNumberStr);
        
        if (lineCount >= LINES_PER_PAGE) {
            outputWriter.newLine();
            outputWriter.newLine();
            outputWriter.newLine();
            printHeadings();
            lineCount = 0;
            pageNum++;
        }
        
        if (String.valueOf(transactionCode).equals(RETURNS)) {
            totAmtRRecords = totAmtRRecords.add(transactionAmount);
            
            lineCount++;
            totNumRRecords++;
            
            BigDecimal taxOwing = transactionAmount.multiply(TAX_VALUE).setScale(2, RoundingMode.HALF_UP);
            totTaxOwing = totTaxOwing.add(taxOwing);
            
            String detailLine = String.format("     %c        %10.2f    %s       %2d    %-9s    %-15s  %9.2f",
                transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing);
            
            outputWriter.write(detailLine);
            outputWriter.newLine();
        }
    }
}