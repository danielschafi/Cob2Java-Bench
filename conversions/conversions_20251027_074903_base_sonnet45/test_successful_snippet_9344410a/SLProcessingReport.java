import java.io.*;
import java.text.DecimalFormat;

public class SLProcessingReport {

    private static class InputRecord {
        char transactionCode;
        double transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
    }

    private static boolean eofFlag = false;
    private static int pageNum = 1;
    private static int lineCount = 0;
    private static final int LINES_PER_PAGE = 20;
    
    private static int totNumSRecords = 0;
    private static double totAmountSRecords = 0.0;
    private static int totNumLRecords = 0;
    private static double totAmountLRecords = 0.0;
    
    private static int totNumCash = 0;
    private static int totNumCredit = 0;
    private static int totNumDebit = 0;
    private static int count = 0;
    
    private static double totTaxOwing = 0.0;
    private static final double TAX_VALUE = 0.13;
    
    private static double[] totStoreAmount = new double[9];
    
    private static BufferedReader inputFile;
    private static BufferedWriter outputFile;
    private static InputRecord currentRecord;
    
    private static DecimalFormat amountFormat = new DecimalFormat("$###,##0.00");
    private static DecimalFormat percentFormat = new DecimalFormat("00.00");
    private static DecimalFormat taxFormat = new DecimalFormat("$###0.00");
    private static DecimalFormat storeFormat = new DecimalFormat("0");

    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader("SL.out"));
            outputFile = new BufferedWriter(new FileWriter("SLProcessing.out"));
            
            currentRecord = readRecord();
            if (currentRecord == null) {
                eofFlag = true;
            }
            
            printHeadings();
            
            while (!eofFlag) {
                processPage();
            }
            
            writeTotalsForSRecords();
            writeTotalsForLRecords();
            writePercentageOfEachPaymentType();
            writeTotalTaxOwing();
            writeStoreNumHighestTransAmount();
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static InputRecord readRecord() throws IOException {
        String line = inputFile.readLine();
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        
        if (line.length() < 36) {
            return null;
        }
        
        InputRecord record = new InputRecord();
        record.transactionCode = line.charAt(0);
        record.transactionAmount = Double.parseDouble(line.substring(1, 8)) / 100.0;
        record.paymentType = line.substring(8, 10);
        record.storeNumber = Integer.parseInt(line.substring(10, 12));
        record.invoiceNumber = line.substring(12, 21);
        record.skuCode = line.substring(21, 36);
        
        return record;
    }
    
    private static void printHeadings() throws IOException {
        String heading1 = String.format("%-30s%-21s%-19s%-5s%d%-3s",
            "", "S&L PROCESSING REPORT", "", "PAGE ", pageNum, "");
        outputFile.write(heading1);
        outputFile.newLine();
        
        String heading2 = String.format(" %-11s  %-11s  %-7s  %-5s   %-7s          %-3s               %-3s",
            "TRANSACTION", "TRANSACTION", "PAYMENT", "STORE", "INVOICE", "SKU", "TAX");
        outputFile.write(heading2);
        outputFile.newLine();
        
        String heading3 = String.format("    %-4s        %-6s     %-4s     %-6s  %-6s           %-4s             %-5s",
            "CODE", "AMOUNT", "TYPE", "NUMBER", "NUMBER", "CODE", "OWING");
        outputFile.write(heading3);
        outputFile.newLine();
        
        String underlines = String.format(" %-11s  %-11s  %-7s  %-6s  %-7s          %-4s             %-5s",
            "-----------", "-----------", "-------", "------", "-------", "----", "-----");
        outputFile.write(underlines);
        outputFile.newLine();
    }
    
    private static void processPage() throws IOException {
        if (lineCount >= LINES_PER_PAGE) {
            outputFile.newLine();
            outputFile.newLine();
            outputFile.newLine();
            pageNum++;
            String heading1 = String.format("%-30s%-21s%-19s%-5s%d%-3s",
                "", "S&L PROCESSING REPORT", "", "PAGE ", pageNum, "");
            outputFile.write(heading1);
            outputFile.newLine();
            outputFile.newLine();
            
            String heading2 = String.format(" %-11s  %-11s  %-7s  %-5s   %-7s          %-3s               %-3s",
                "TRANSACTION", "TRANSACTION", "PAYMENT", "STORE", "INVOICE", "SKU", "TAX");
            outputFile.write(heading2);
            outputFile.newLine();
            
            String heading3 = String.format("    %-4s        %-6s     %-4s     %-6s  %-6s           %-4s             %-5s",
                "CODE", "AMOUNT", "TYPE", "NUMBER", "NUMBER", "CODE", "OWING");
            outputFile.write(heading3);
            outputFile.newLine();
            
            String underlines = String.format(" %-11s  %-11s  %-7s  %-6s  %-7s          %-4s             %-5s",
                "-----------", "-----------", "-------", "------", "-------", "----", "-----");
            outputFile.write(underlines);
            outputFile.newLine();
            
            outputFile.newLine();
            lineCount = 0;
        }
        
        if (currentRecord.transactionCode == 'S') {
            totAmountSRecords += currentRecord.transactionAmount;
            totNumSRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType();
            double taxOwing = currentRecord.transactionAmount * TAX_VALUE;
            writeDetailLine(taxOwing);
        } else if (currentRecord.transactionCode == 'L') {
            totAmountLRecords += currentRecord.transactionAmount;
            totNumLRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType();
            double taxOwing = currentRecord.transactionAmount * TAX_VALUE;
            writeDetailLine(taxOwing);
        }
        
        currentRecord = readRecord();
        if (currentRecord == null) {
            eofFlag = true;
        }
    }
    
    private static void writeDetailLine(double taxOwing) throws IOException {
        String storeNum = currentRecord.storeNumber < 10 ? 
            " " + currentRecord.storeNumber : String.valueOf(currentRecord.storeNumber);
        
        String detailLine = String.format("     %c        %s    %s       %s    %s    %s %s",
            currentRecord.transactionCode,
            amountFormat.format(currentRecord.transactionAmount),
            currentRecord.paymentType,
            storeNum,
            currentRecord.invoiceNumber,
            currentRecord.skuCode,
            taxFormat.format(taxOwing));
        
        outputFile.write(detailLine);
        outputFile.newLine();
    }
    
    private static void calcPercentageOfEach