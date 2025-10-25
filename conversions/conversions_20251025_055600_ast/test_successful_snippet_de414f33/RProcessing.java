import java.io.*;
import java.text.DecimalFormat;

public class RProcessing {
    private static final String INPUT_FILE = "returns.out";
    private static final String OUTPUT_FILE = "rprocessing.out";
    private static final int LINES_PER_PAGE = 20;
    private static final char EOF_FLAG = 'N';
    private static final char RETURNS = 'R';
    private static final double TAX_VALUE = 0.13;

    private static String wsHeadingLine1 = "                      R PROCESSING REPORT                      ";
    private static String wsHeadingLine2 = " TRANSACTION  TRANSACTION  PAYMENT  STORE  INVOICE       SKU  TAX";
    private static String wsHeadingLine3 = " CODE       AMOUNT     TYPE     NUMBER NUMBER       CODE OWING";
    private static String wsUnderlines = " -----------  -----------  -------  ------ -------  ----  -----";

    private static String wsDetailLine = new String(new char[99]).replace('\0', ' ');
    private static String wsTotalsForRRecords = new String(new char[99]).replace('\0', ' ');
    private static String wsTotalTaxOwing = new String(new char[99]).replace('\0', ' ');

    private static char wsEofFlag = EOF_FLAG;
    private static int wsPageNum = 0;
    private static int wsLineCount = 0;
    private static int wsTotNumRRecords = 0;
    private static double wsTotAmtRRecords = 0.0;
    private static double wsTotTaxOwing = 0.0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            printHeadings(writer);

            String line;
            while ((line = reader.readLine()) != null) {
                if (processPage(line, writer)) {
                    break;
                }
            }

            writeTotals(writer);

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

    private static boolean processPage(String line, BufferedWriter writer) throws IOException {
        if (line.length() < 36) {
            return true;
        }

        char transactionCode = line.charAt(0);
        double transactionAmount = Double.parseDouble(line.substring(1, 10).trim());
        String paymentType = line.substring(10, 12).trim();
        int storeNumber = Integer.parseInt(line.substring(12, 14).trim());
        String invoiceNumber = line.substring(14, 23).trim();
        String skuCode = line.substring(23, 38).trim();

        if (wsLineCount >= LINES_PER_PAGE) {
            printHeadings(writer);
            wsLineCount = 0;
            wsPageNum++;
        }

        if (transactionCode == RETURNS) {
            wsTotAmtRRecords += transactionAmount;
            wsTotNumRRecords++;

            double taxOwing = transactionAmount * TAX_VALUE;
            wsTotTaxOwing += taxOwing;

            wsDetailLine = String.format("%5s%10.2f%5s%6d%10s%15s%8.2f",
                    transactionCode, transactionAmount, paymentType, storeNumber, invoiceNumber, skuCode, taxOwing);

            writer.write(wsDetailLine);
            writer.newLine();

            wsLineCount++;
        }

        return false;
    }

    private static void writeTotals(BufferedWriter writer) throws IOException {
        wsTotalsForRRecords = String.format("TOTAL NUMBER OF 'R' RECORDS: %2d TOTAL AMOUNT OF 'R' RECORDS: %10.2f",
                wsTotNumRRecords, wsTotAmtRRecords);
        wsTotalTaxOwing = String.format("TOTAL TAX OWING: %8.2f", wsTotTaxOwing);

        writer.newLine();
        writer.write(wsTotalsForRRecords);
        writer.newLine();
        writer.newLine();
        writer.write(wsTotalTaxOwing);
    }
}