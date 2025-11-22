import java.io.*;
import java.text.DecimalFormat;

public class S_LProcessingReport {
    private static final String INPUT_FILE = "SL.out";
    private static final String OUTPUT_FILE = "SLProcessing.out";
    private static final int LINES_PER_PAGE = 20;
    private static final double TAX_RATE = 0.13;

    private static String wsEofFlag = "N";
    private static String wsHeadingLine1 = "                              S&L PROCESSING REPORT                PAGE 1   ";
    private static String wsHeadingLine2 = " TRANSACTION    TRANSACTION    PAYMENT  STORE  INVOICE       SKU   TAX";
    private static String wsHeadingLine3 = " CODE         AMOUNT       TYPE     NUMBER NUMBER        CODE  OWING";
    private static String wsUnderlines = " -----------    -----------    -------  ------ ---------       ---  -----";
    private static String wsDetailLine = "     %1s         %10.2f     %2s     %02d %9s %15s  %5.2f";
    private static String wsTotalsForSRecords = " TOTAL NUMBER OF 'S' RECORDS:  %02d TOTAL AMOUNT OF 'S' RECORDS: %10.2f";
    private static String wsTotalsForLRecords = " TOTAL NUMBER OF 'L' RECORDS:  %02d TOTAL AMOUNT OF 'L' RECORDS: %10.2f";
    private static String wsPercntgOfEachPaymentType = " THE PERCENTAGE OF EACH PAYMENT TYPE: CASH 'CA' = %5.2f%% CREDIT CARD 'CR' = %5.2f%% DEBIT 'DB' = %5.2f%%";
    private static String wsTotalTaxOwing = " TOTAL TAX OWING: %10.2f";
    private static String wsStoreNumHighstTransAmount = " STORE NUMBER (%02d) HAS HIGHEST TRANSACTION AMOUNT.";

    private static int wsPageNum = 2;
    private static int wsLineCount = 0;
    private static int wsTotNumSRecords = 0;
    private static double wsTotAmountSRecords = 0.0;
    private static int wsTotNumLRecords = 0;
    private static double wsTotAmountLRecords = 0.0;
    private static double wsOverallTotAmount = 0.0;
    private static int wsTotNumCash = 0;
    private static double wsTotAmountCash = 0.0;
    private static int wsTotNumCredit = 0;
    private static double wsTotAmountCredit = 0.0;
    private static int wsTotNumDebit = 0;
    private static double wsTotAmountDebit = 0.0;
    private static int wsCount = 0;
    private static double wsTaxOwing = 0.0;
    private static double wsTotTaxOwing = 0.0;
    private static int wsSubTotal = 0;
    private static double[] wsTotStoreAmount = new double[8];
    private static int wsSub = 0;
    private static double wsSaveAmt = 0.0;

    private static final String WS_SALES = "S";
    private static final String WS_LAYAWAY = "L";
    private static final String WS_PAYMENT_CASH = "CA";
    private static final String WS_PAYMENT_CREDIT = "CR";
    private static final String WS_PAYMENT_DEBIT = "DB";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            printHeadings(writer);
            processPage(reader, writer);

            writer.write(String.format(wsTotalsForSRecords, wsTotNumSRecords, wsTotAmountSRecords));
            writer.newLine();
            writer.newLine();
            writer.write(String.format(wsTotalsForLRecords, wsTotNumLRecords, wsTotAmountLRecords));
            writer.newLine();
            writer.newLine();
            writer.write(String.format(wsPercntgOfEachPaymentType, calculatePercentage(wsTotAmountCash, wsOverallTotAmount), calculatePercentage(wsTotAmountCredit, wsOverallTotAmount), calculatePercentage(wsTotAmountDebit, wsOverallTotAmount)));
            writer.newLine();
            writer.newLine();
            writer.write(String.format(wsTotalTaxOwing, wsTotTaxOwing));
            writer.newLine();
            writer.newLine();
            writer.write(String.format(wsStoreNumHighstTransAmount, wsSub));
            writer.newLine();
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printHeadings(BufferedWriter writer) throws IOException {
        writer.write(wsHeadingLine1);
        writer.newLine();
        writer.write(wsHeadingLine2);
        writer.newLine();
        writer.write(wsHeadingLine3);
        writer.newLine();
        writer.write(wsUnderlines);
        writer.newLine();
    }

    private static void processPage(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (wsLineCount >= LINES_PER_PAGE) {
                printHeadings(writer);
                wsLineCount = 0;
                wsPageNum++;
            }

            String transactionCode = line.substring(0, 1);
            double transactionAmount = Double.parseDouble(line.substring(1, 8)) / 100;
            String paymentType = line.substring(8, 10);
            int storeNumber = Integer.parseInt(line.substring(10, 12));
            String invoiceNumber = line.substring(12, 21);
            String skuCode = line.substring(21, 36);

            if (transactionCode.equals(WS_SALES)) {
                wsTotNumSRecords++;
                wsTotAmountSRecords += transactionAmount;
                wsOverallTotAmount += transactionAmount;
            } else if (transactionCode.equals(WS_LAYAWAY)) {
                wsTotNumLRecords++;
                wsTotAmountLRecords += transactionAmount;
                wsOverallTotAmount += transactionAmount;
            }

            wsLineCount++;
            wsCount++;

            calculateTaxOwing(transactionAmount);
            calculatePercentageOfEachPaymentType(paymentType, transactionAmount);
            updateStoreTotal(storeNumber, transactionAmount);

            writer.write(String.format(wsDetailLine, transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, wsTaxOwing));
            writer.newLine();
        }
    }

    private static void calculateTaxOwing(double transactionAmount) {
        wsTaxOwing = transactionAmount * TAX_RATE;
        wsTotTaxOwing += wsTaxOwing;
    }

    private static void calculatePercentageOfEachPaymentType(String paymentType, double transactionAmount) {
        if (paymentType.equals(WS_PAYMENT_CASH)) {
            wsTotNumCash++;
            wsTotAmountCash += transactionAmount;
        } else if (paymentType.equals(WS_PAYMENT_CREDIT)) {
            wsTotNumCredit++;
            wsTotAmountCredit += transactionAmount;
        } else if (paymentType.equals(WS_PAYMENT_DEBIT)) {
            wsTotNumDebit++;
            wsTotAmountDebit += transactionAmount;
        }
    }

    private static void updateStoreTotal(int storeNumber, double transactionAmount) {
        wsSubTotal = storeNumber;
        wsTotStoreAmount[wsSubTotal - 1] += transactionAmount;

        wsSaveAmt = 0.0;
        for (int i = 0; i < wsTotStoreAmount.length; i++) {
            if (wsTotStoreAmount[i] > wsSaveAmt) {
                wsSaveAmt = wsTotStoreAmount[i];
                wsSub = i + 1;
            }
        }
    }

    private static double calculatePercentage(double amount, double total) {
        return (amount / total) * 100;
    }
}