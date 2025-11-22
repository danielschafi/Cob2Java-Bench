import java.io.*;
import java.text.DecimalFormat;

public class S_LProcessingReport {
    private static final String INPUT_FILE = "SL.out";
    private static final String OUTPUT_FILE = "SLProcessing.out";
    private static final int LINES_PER_PAGE = 20;
    private static final double TAX_VALUE = 0.13;

    private static String wsEofFlag = "N";
    private static int wsOlPageNum = 1;
    private static int wsLineCount = 0;
    private static int wsPageNum = 2;
    private static double wsTaxOwing = 0;
    private static int wsTotNumSRecords = 0;
    private static double wsTotAmountSRecords = 0;
    private static int wsTotNumLRecords = 0;
    private static double wsTotAmountLRecords = 0;
    private static double wsOverallTotAmount = 0;
    private static int wsTotNumCash = 0;
    private static int wsTotNumCredit = 0;
    private static int wsTotNumDebit = 0;
    private static int wsCount = 0;
    private static double wsTotAmountCash = 0;
    private static double wsTotAmountCredit = 0;
    private static double wsTotAmountDebit = 0;
    private static double wsTotPrcntCash = 0;
    private static double wsTotPrcntCredit = 0;
    private static double wsTotPrcntDebit = 0;
    private static double wsTotTaxOwing = 0;
    private static int wsSubTotal = 0;
    private static double[] wsTotStoreAmount = new double[8];
    private static double wsSaveAmt = 0;
    private static int wsSub = 0;

    private static final String WS_SALES = "S";
    private static final String WS_LAYAWAY = "L";
    private static final String WS_PAYMENT_CASH = "CA";
    private static final String WS_PAYMENT_CREDIT = "CR";
    private static final String WS_PAYMENT_DEBIT = "DB";

    private static final DecimalFormat TRANSACTION_AMOUNT_FORMAT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat TAX_OWING_FORMAT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("0.00");

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            printHeadings(writer);

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 36) continue;

                String ilTransactionCode = line.substring(0, 1);
                double ilTransactionAmount = Double.parseDouble(line.substring(1, 8).replace(" ", ""));
                String ilPaymentType = line.substring(8, 10);
                int ilStoreNumber = Integer.parseInt(line.substring(10, 12).trim());
                String ilInvoiceNumber = line.substring(12, 21);
                String ilSkuCode = line.substring(21, 36);

                processPage(writer, ilTransactionCode, ilTransactionAmount, ilPaymentType, ilStoreNumber, ilInvoiceNumber, ilSkuCode);
            }

            writer.write(String.format("%-120s", "TOTAL NUMBER OF 'S' RECORDS: " + wsTotNumSRecords + " TOTAL AMOUNT OF 'S' RECORDS: " + TRANSACTION_AMOUNT_FORMAT.format(wsTotAmountSRecords)));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-120s", "TOTAL NUMBER OF 'L' RECORDS: " + wsTotNumLRecords + " TOTAL AMOUNT OF 'L' RECORDS: " + TRANSACTION_AMOUNT_FORMAT.format(wsTotAmountLRecords)));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-120s", "THE PERCENTAGE OF EACH PAYMENT TYPE: CASH 'CA' = " + PERCENTAGE_FORMAT.format(wsTotPrcntCash) + "% CREDIT CARD 'CR' = " + PERCENTAGE_FORMAT.format(wsTotPrcntCredit) + "% DEBIT 'DB' = " + PERCENTAGE_FORMAT.format(wsTotPrcntDebit) + "%"));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-120s", "TOTAL TAX OWING: " + TAX_OWING_FORMAT.format(wsTotTaxOwing)));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-120s", "STORE NUMBER (" + wsSub + ") HAS HIGHEST TRANSACTION AMOUNT."));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printHeadings(BufferedWriter writer) throws IOException {
        writer.write(String.format("%-120s", "                              S&L PROCESSING REPORT                              PAGE " + wsOlPageNum));
        writer.newLine();
        writer.write(String.format("%-120s", " TRANSACTION  TRANSACTION  PAYMENT  STORE  INVOICE NUMBER  SKU CODE  TAX"));
        writer.newLine();
        writer.write(String.format("%-120s", "  CODE         AMOUNT       TYPE     NUMBER             CODE      OWING"));
        writer.newLine();
        writer.write(String.format("%-120s", "-----------  -----------  -------  ------  -------------  ----------  -----"));
        writer.newLine();
    }

    private static void processPage(BufferedWriter writer, String ilTransactionCode, double ilTransactionAmount, String ilPaymentType, int ilStoreNumber, String ilInvoiceNumber, String ilSkuCode) throws IOException {
        if (wsLineCount >= LINES_PER_PAGE) {
            printHeadings(writer);
            wsLineCount = 0;
            wsPageNum++;
        }

        if (ilTransactionCode.equals(WS_SALES)) {
            wsTotAmountSRecords += ilTransactionAmount;
            wsTotNumSRecords++;
            wsCount++;
            calcPercentageOfEachPaymentType(ilPaymentType, ilTransactionAmount);
            wsTaxOwing = ilTransactionAmount * TAX_VALUE;
            wsTotTaxOwing += wsTaxOwing;
            writer.write(String.format("%-120s", "     " + ilTransactionCode + "        " + TRANSACTION_AMOUNT_FORMAT.format(ilTransactionAmount) + "    " + ilPaymentType + "     " + ilStoreNumber + "  " + ilInvoiceNumber + "  " + ilSkuCode + " " + TAX_OWING_FORMAT.format(wsTaxOwing)));
            writer.newLine();
        } else if (ilTransactionCode.equals(WS_LAYAWAY)) {
            wsTotAmountLRecords += ilTransactionAmount;
            wsTotNumLRecords++;
            wsCount++;
            calcPercentageOfEachPaymentType(ilPaymentType, ilTransactionAmount);
            wsTaxOwing = ilTransactionAmount * TAX_VALUE;
            wsTotTaxOwing += wsTaxOwing;
            writer.write(String.format("%-120s", "     " + ilTransactionCode + "        " + TRANSACTION_AMOUNT_FORMAT.format(ilTransactionAmount) + "    " + ilPaymentType + "     " + ilStoreNumber + "  " + ilInvoiceNumber + "  " + ilSkuCode + " " + TAX_OWING_FORMAT.format(wsTaxOwing)));
            writer.newLine();
        }

        wsOverallTotAmount = wsTotAmountSRecords + wsTotAmountLRecords;

        wsSubTotal = ilStoreNumber;
        wsTotStoreAmount[wsSubTotal - 1] += ilTransactionAmount;
        wsTotStoreAmount[7] += ilTransactionAmount;

        wsSaveAmt = 0;
        for (int i = 0; i < 8; i++) {
            if (wsTotStoreAmount[i] > wsSaveAmt) {
                wsSaveAmt = wsTotStoreAmount[i];
                wsSub = i + 1;
            }
        }

        wsLineCount++;
    }

    private static void calcPercentageOfEachPaymentType(String ilPaymentType, double ilTransactionAmount) {
        if (ilPaymentType.equals(WS_PAYMENT_CASH)) {
            wsTotNumCash++;
            wsTotAmountCash = (double) wsTotNumCash / wsCount;
            wsTotPrcntCash = wsTotAmountCash * 100;
        } else if (ilPaymentType.equals(WS_PAYMENT_CREDIT)) {
            wsTotNumCredit++;
            wsTotAmountCredit = (double) wsTotNumCredit / wsCount;
            wsTotPrcntCredit = wsTotAmountCredit * 100;
        } else if (ilPaymentType.equals(WS_PAYMENT_DEBIT)) {
            wsTotNumDebit++;
            wsTotAmountDebit = (double) wsTotNumDebit / wsCount;
            wsTotPrcntDebit = wsTotAmountDebit * 100;
        }
    }
}