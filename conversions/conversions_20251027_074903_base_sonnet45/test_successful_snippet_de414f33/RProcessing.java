import java.io.*;
import java.text.DecimalFormat;

public class RProcessing {
    
    private static final String INPUT_FILE = "returns.out";
    private static final String OUTPUT_FILE = "rprocessing.out";
    
    private static class InputLine {
        char transactionCode;
        double transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
    }
    
    private String wsEofFlag = "N";
    private int wsPageNum = 0;
    private int wsLineCount = 0;
    private int wsLinesPerPage = 20;
    private double wsTaxOwing = 0.0;
    private double wsTaxValue = 0.13;
    private int wsTotNumRRecords = 0;
    private double wsTotAmtRRecords = 0.0;
    private double wsTotTaxOwing = 0.0;
    
    private BufferedReader inputFile;
    private BufferedWriter outputFile;
    private InputLine currentRecord;
    
    private DecimalFormat amountFormat = new DecimalFormat("$###,##0.00");
    private DecimalFormat taxFormat = new DecimalFormat("$##0.00");
    
    public static void main(String[] args) {
        RProcessing program = new RProcessing();
        program.execute();
    }
    
    public void execute() {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            readInputFile();
            
            printHeadings();
            
            while (!wsEofFlag.equals("Y")) {
                processPage();
            }
            
            writeLine(getTotalsForRRecords(), 2);
            writeLine(getTotalTaxOwing(), 2);
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readInputFile() throws IOException {
        String line = inputFile.readLine();
        if (line == null) {
            wsEofFlag = "Y";
        } else {
            currentRecord = parseLine(line);
        }
    }
    
    private InputLine parseLine(String line) {
        InputLine record = new InputLine();
        if (line.length() >= 36) {
            record.transactionCode = line.charAt(0);
            record.transactionAmount = Double.parseDouble(line.substring(1, 8)) / 100.0;
            record.paymentType = line.substring(8, 10);
            record.storeNumber = Integer.parseInt(line.substring(10, 12));
            record.invoiceNumber = line.substring(12, 21);
            record.skuCode = line.substring(21, 36);
        }
        return record;
    }
    
    private void printHeadings() throws IOException {
        String heading1 = String.format("%30s%21s%19s", "", "R PROCESSING REPORT", "");
        writeLine(heading1, 0);
        
        String heading2 = String.format(" %-11s  %-11s  %-7s  %-5s   %-7s          %-3s               %-3s",
            "TRANSACTION", "TRANSACTION", "PAYMENT", "STORE", "INVOICE", "SKU", "TAX");
        writeLine(heading2, 1);
        
        String heading3 = String.format("    %-4s        %-6s     %-4s     %-6s  %-6s           %-4s             %-5s",
            "CODE", "AMOUNT", "TYPE", "NUMBER", "NUMBER", "CODE", "OWING");
        writeLine(heading3, 0);
        
        String underlines = " -----------  -----------  -------  ------  -------          ----             -----";
        writeLine(underlines, 0);
    }
    
    private void processPage() throws IOException {
        if (wsLineCount >= wsLinesPerPage) {
            String heading1 = String.format("%30s%21s%19s", "", "R PROCESSING REPORT", "");
            writeLine(heading1, 3);
            
            String heading2 = String.format(" %-11s  %-11s  %-7s  %-5s   %-7s          %-3s               %-3s",
                "TRANSACTION", "TRANSACTION", "PAYMENT", "STORE", "INVOICE", "SKU", "TAX");
            writeLine(heading2, 2);
            
            String heading3 = String.format("    %-4s        %-6s     %-4s     %-6s  %-6s           %-4s             %-5s",
                "CODE", "AMOUNT", "TYPE", "NUMBER", "NUMBER", "CODE", "OWING");
            writeLine(heading3, 0);
            
            String underlines = " -----------  -----------  -------  ------  -------          ----             -----";
            writeLine(underlines, 0);
            writeLine("", 0);
            
            wsLineCount = 0;
            wsPageNum++;
        }
        
        if (currentRecord.transactionCode == 'R') {
            wsTotAmtRRecords += currentRecord.transactionAmount;
            wsLineCount++;
            wsTotNumRRecords++;
            calculations();
            
            String detailLine = formatDetailLine();
            writeLine(detailLine, 0);
        }
        
        readInputFile();
    }
    
    private void calculations() {
        wsTaxOwing = Math.round(currentRecord.transactionAmount * wsTaxValue * 100.0) / 100.0;
        calcPercentageOfEachPaymentType();
    }
    
    private void calcPercentageOfEachPaymentType() {
        wsTotTaxOwing += wsTaxOwing;
    }
    
    private String formatDetailLine() {
        String storeNum = String.format("%2d", currentRecord.storeNumber).replace(' ', ' ');
        if (currentRecord.storeNumber < 10) {
            storeNum = " " + currentRecord.storeNumber;
        }
        
        String detailLine = String.format("     %c        %s    %s       %s    %s    %s %s",
            currentRecord.transactionCode,
            amountFormat.format(currentRecord.transactionAmount),
            currentRecord.paymentType,
            storeNum,
            currentRecord.invoiceNumber,
            currentRecord.skuCode,
            amountFormat.format(wsTaxOwing));
        
        return detailLine;
    }
    
    private String getTotalsForRRecords() {
        return String.format(" TOTAL NUMBER OF 'R' RECORDS:  %02d          TOTAL AMOUNT OF 'R' RECORDS: %s",
            wsTotNumRRecords,
            amountFormat.format(wsTotAmtRRecords));
    }
    
    private String getTotalTaxOwing() {
        return String.format(" TOTAL TAX OWING:  %s",
            taxFormat.format(wsTotTaxOwing));
    }
    
    private void writeLine(String line, int advanceLines) throws IOException {
        for (int i = 0; i < advanceLines; i++) {
            outputFile.newLine();
        }
        outputFile.write(line);
        outputFile.newLine();
    }
}