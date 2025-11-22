import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;

public class SLProcessingReport {
    private static final String INPUT_FILE = "SL.out";
    private static final String OUTPUT_FILE = "SLProcessing.out";
    private static final int LINES_PER_PAGE = 20;
    private static final String SALES = "S";
    private static final String LAYAWAY = "L";
    private static final String PAYMENT_CASH = "CA";
    private static final String PAYMENT_CREDIT = "CR";
    private static final String PAYMENT_DEBIT = "DB";
    private static final BigDecimal TAX_VALUE = new BigDecimal("0.13");

    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private boolean eofFlag = false;
    private int pageNum = 1;
    private int lineCount = 0;
    private int count = 0;

    private int totNumSRecords = 0;
    private BigDecimal totAmountSRecords = BigDecimal.ZERO;
    private int totNumLRecords = 0;
    private BigDecimal totAmountLRecords = BigDecimal.ZERO;
    private BigDecimal overallTotAmount = BigDecimal.ZERO;

    private int totNumCash = 0;
    private int totNumCredit = 0;
    private int totNumDebit = 0;

    private BigDecimal totAmountCash = BigDecimal.ZERO;
    private BigDecimal totAmountCredit = BigDecimal.ZERO;
    private BigDecimal totAmountDebit = BigDecimal.ZERO;

    private BigDecimal totPrcntCash = BigDecimal.ZERO;
    private BigDecimal totPrcntCredit = BigDecimal.ZERO;
    private BigDecimal totPrcntDebit = BigDecimal.ZERO;

    private BigDecimal totTaxOwing = BigDecimal.ZERO;
    private BigDecimal[] totStoreAmount = new BigDecimal[9];
    private int storeNumHighestAmount = 0;

    public static void main(String[] args) {
        SLProcessingReport report = new SLProcessingReport();
        report.run();
    }

    public void run() {
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
            outputWriter = new PrintWriter(new FileWriter(OUTPUT_FILE));

            for (int i = 0; i < totStoreAmount.length; i++) {
                totStoreAmount[i] = BigDecimal.ZERO;
            }

            printHeadings();

            String line = inputReader.readLine();
            if (line == null) {
                eofFlag = true;
            }

            while (!eofFlag) {
                processPage(line);
                line = inputReader.readLine();
                if (line == null) {
                    eofFlag = true;
                }
            }

            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatTotalsForSRecords());

            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatTotalsForLRecords());

            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatPercentageOfEachPaymentType());

            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatTotalTaxOwing());

            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatStoreNumHighestTransAmount());

            inputReader.close();
            outputWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printHeadings() {
        outputWriter.println(formatHeadingLine1());
        outputWriter.println();
        outputWriter.println(formatHeadingLine2());
        outputWriter.println(formatHeadingLine3());
        outputWriter.println(formatUnderlines());
    }

    private void processPage(String line) {
        if (lineCount >= LINES_PER_PAGE) {
            outputWriter.println();
            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatHeadingLine1());
            outputWriter.println();
            outputWriter.println();
            outputWriter.println(formatHeadingLine2());
            outputWriter.println(formatHeadingLine3());
            outputWriter.println(formatUnderlines());
            outputWriter.println();
            lineCount = 0;
            pageNum++;
        }

        String transactionCode = line.substring(0, 1);
        BigDecimal transactionAmount = new BigDecimal(line.substring(1, 8));
        String paymentType = line.substring(8, 10);
        int storeNumber = Integer.parseInt(line.substring(10, 12));
        String invoiceNumber = line.substring(12, 21);
        String skuCode = line.substring(21, 36);

        if (SALES.equals(transactionCode)) {
            totAmountSRecords = totAmountSRecords.add(transactionAmount);
            totNumSRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType(paymentType);
            BigDecimal taxOwing = transactionAmount.multiply(TAX_VALUE).setScale(2, RoundingMode.HALF_UP);
            outputWriter.println(formatDetailLine(transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing));
        } else if (LAYAWAY.equals(transactionCode)) {
            totAmountLRecords = totAmountLRecords.add(transactionAmount);
            totNumLRecords++;
            lineCount++;
            count++;
            calcPercentageOfEachPaymentType(paymentType);
            BigDecimal taxOwing = transactionAmount.multiply(TAX_VALUE).setScale(2, RoundingMode.HALF_UP);
            outputWriter.println(formatDetailLine(transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing));
        }

        overallTotAmount = totAmountSRecords.add(totAmountLRecords);

        int subTotal = storeNumber;
        totStoreAmount[subTotal] = totStoreAmount[subTotal].add(transactionAmount);
        totStoreAmount[8] = totStoreAmount[8].add(transactionAmount);

        BigDecimal saveAmt = BigDecimal.ZERO;
        for (int i = 1; i < 8; i++) {
            if (totStoreAmount[i].compareTo(saveAmt) > 0) {
                saveAmt = totStoreAmount[i];
                storeNumHighestAmount = i;
            }
        }

        BigDecimal taxOwing = transactionAmount.multiply(TAX_VALUE).setScale(2, RoundingMode.HALF_UP);
        totTaxOwing = totTaxOwing.add(taxOwing);
    }

    private void calcPercentageOfEachPaymentType(String paymentType) {
        if (PAYMENT_CASH.equals(paymentType)) {
            totNumCash++;
            totPrcntCash = new BigDecimal(totNumCash).divide(new BigDecimal(count), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        } else if (PAYMENT_CREDIT.equals(paymentType)) {
            totNumCredit++;
            totPrcntCredit = new BigDecimal(totNumCredit).divide(new BigDecimal(count), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        } else if (PAYMENT_DEBIT.equals(paymentType)) {
            totNumDebit++;
            totPrcntDebit = new BigDecimal(totNumDebit).divide(new BigDecimal(count), 4, RoundingMode.HALF_UP).multiply(new