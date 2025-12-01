import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class SLProcessingReport {
    private BufferedReader inputFile;
    private PrintWriter outputFile;
    private String eofFlag = "N";
    
    private int pageNum = 2;
    private int lineCount = 0;
    private int linesPerPage = 20;
    
    private int totNumSRecords = 0;
    private double totAmountSRecords = 0.0;
    private int totNumLRecords = 0;
    private double totAmountLRecords = 0.0;
    private double overallTotAmount = 0.0;
    
    private int totNumCash = 0;
    private int totNumCredit = 0;
    private int totNumDebit = 0;
    private int count = 0;
    
    private double totAmountCash = 0.0;
    private double totAmountCredit = 0.0;
    private double totAmountDebit = 0.0;
    
    private double totPrcntCash = 0.0;
    private double totPrcntCredit = 0.0;
    private double totPrcntDebit = 0.0;
    
    private double totTaxOwing = 0.0;
    private double taxOwing = 0.0;
    private double taxValue = 0.13;
    
    private double[] totStoreAmount = new double[9];
    private int saveAmt = 0;
    private int sub = 0;
    
    private String sales = "S";
    private String layaway = "L";
    private String paymentCash = "CA";
    private String paymentCredit = "CR";
    private String paymentDebit = "DB";
    
    private DecimalFormat currencyFormat = new DecimalFormat("$###,##0.00");
    private DecimalFormat percentFormat = new DecimalFormat("00.00");
    
    public static void main(String[] args) {
        SLProcessingReport report = new SLProcessingReport();
        report.run();
    }
    
    public void run() {
        try {
            inputFile = new BufferedReader(new FileReader("SL.out"));
            outputFile = new PrintWriter(new FileWriter("SLProcessing.out"));
            
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = "Y";
            }
            
            printHeadings();
            
            while (!eofFlag.equals("Y")) {
                processPage(line);
                line = inputFile.readLine();
                if (line == null) {
                    eofFlag = "Y";
                }
            }
            
            outputFile.println();
            outputFile.println();
            outputFile.printf(" TOTAL NUMBER OF 'S' RECORDS:  %2d          TOTAL AMOUNT OF 'S' RECORDS: %s%n", 
                totNumSRecords, currencyFormat.format(totAmountSRecords));
            
            outputFile.println();
            outputFile.println();
            outputFile.printf(" TOTAL NUMBER OF 'L' RECORDS:  %2d          TOTAL AMOUNT OF 'L' RECORDS: %s%n", 
                totNumLRecords, currencyFormat.format(totAmountLRecords));
            
            outputFile.println();
            outputFile.println();
            outputFile.printf(" THE PERCENTAGE OF EACH PAYMENT TYPE:  CASH 'CA' = %s%%   CREDIT CARD 'CR' = %s%%   DEBIT 'DB' = %s%%%n",
                percentFormat.format(totPrcntCash), percentFormat.format(totPrcntCredit), percentFormat.format(totPrcntDebit));
            
            outputFile.println();
            outputFile.println();
            outputFile.printf(" TOTAL TAX OWING:  %s%n", currencyFormat.format(totTaxOwing));
            
            outputFile.println();
            outputFile.println();
            outputFile.printf(" STORE NUMBER (%2d ) HAS HIGHEST TRANSACTION AMOUNT.%n", sub);
            
            inputFile.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void printHeadings() {
        outputFile.printf("%30sS&L PROCESSING REPORT%19sPAGE %d%3s%n", "", "", pageNum, "");
        outputFile.printf(" TRANSACTION  TRANSACTION  PAYMENT  STORE   INVOICE          SKU             TAX%n");
        outputFile.printf("     CODE      AMOUNT      TYPE    NUMBER   NUMBER           CODE            OWING%n");
        outputFile.printf(" -----------  -----------  -------  ------  -------          ----             -----%n");
    }
    
    private void processPage(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        
        String transactionCode = line.substring(0, 1);
        double transactionAmount = Double.parseDouble(line.substring(1, 8)) / 100.0;
        String paymentType = line.substring(8, 10);
        int storeNumber = Integer.parseInt(line.substring(10, 12));
        String invoiceNumber = line.substring(12, 21);
        String skuCode = line.substring(21, 36);
        
        if (lineCount >= linesPerPage) {
            outputFile.println();
            outputFile.println();
            outputFile.println();
            printHeadings();
            lineCount = 0;
            pageNum++;
        }
        
        if (transactionCode.equals(sales)) {
            totAmountSRecords += transactionAmount;
            totNumSRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType(paymentType);
            taxOwing = transactionAmount * taxValue;
            outputLine(transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing);
        } else if (transactionCode.equals(layaway)) {
            totAmountLRecords += transactionAmount;
            totNumLRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType(paymentType);
            taxOwing = transactionAmount * taxValue;
            outputLine(transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing);
        }
        
        overallTotAmount = totAmountSRecords + totAmountLRecords;
        
        int subTotal = storeNumber;
        totStoreAmount[subTotal] += transactionAmount;
        totStoreAmount[8] += transactionAmount;
        
        saveAmt = 0;
        for (int i = 1; i < 8; i++) {
            if (totStoreAmount[i] > saveAmt) {
                saveAmt = (int)totStoreAmount[i];
                sub = i;
            }
        }
    }
    
    private void calcPercentageOfEachPaymentType(String paymentType) {
        if (paymentType.equals(paymentCash)) {
            totNumCash++;
            totAmountCash = (double)totNumCash / count;
            totPrcntCash = totAmountCash * 100;
        } else if (paymentType.equals(paymentCredit)) {
            totNumCredit++;
            totAmountCredit = (double)totNumCredit / count;
            totPrcntCredit = totAmountCredit * 100;
        } else if (paymentType.equals(paymentDebit)) {
            totNumDebit++;
            totAmountDebit = (double)totNumDebit / count;
            totPrcntDebit = totAmountDebit * 100;
        }
        
        totTaxOwing = taxOwing + totTaxOwing;
    }
    
    private void outputLine(String code, double amount, String paymentType, int storeNum, String invoice, String sku, double tax) {