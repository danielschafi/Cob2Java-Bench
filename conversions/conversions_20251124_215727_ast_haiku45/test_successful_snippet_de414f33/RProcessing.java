import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RProcessing {
    private static class InputLine {
        char transactionCode;
        BigDecimal transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
    }

    private static class OutputLine {
        char transactionCode;
        BigDecimal transactionAmount;
        String paymentType;
        int storeNumber;
        String invoiceNumber;
        String skuCode;
        BigDecimal taxOwing;
    }

    private static String headingLine1 = "                              R PROCESSING REPORT           ";
    private static String headingLine2 = " TRANSACTION  TRANSACTION  PAYMENT  STORE   INVOICE          SKU             TAX";
    private static String headingLine3 = "    CODE        AMOUNT      TYPE    NUMBER  NUMBER        CODE             OWING";
    private static String underlines = " -----------  -----------  -------  ------  -------          ----             -----";

    private static char eofFlag = 'N';
    private static int lineCount = 0;
    private static int linesPerPage = 20;
    private static int pageNum = 0;

    private static BigDecimal taxOwing = BigDecimal.ZERO;
    private static BigDecimal taxValue = new BigDecimal("0.13");
    private static int totNumRRecords = 0;
    private static BigDecimal totAmtRRecords = BigDecimal.ZERO;
    private static BigDecimal totTaxOwing = BigDecimal.ZERO;

    private static char wsReturns = 'R';
    private static String wsPaymentCash = "CA";
    private static String wsPaymentCredit = "CR";
    private static String wsPaymentDebit = "DB";

    public static void main(String[] args) {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("returns.out"));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter("rprocessing.out"))) {

            String line;
            if ((line = inputReader.readLine()) != null) {
                InputLine inputLine = parseInputLine(line);
                printHeadings(outputWriter);

                while (eofFlag == 'N') {
                    processPage(inputLine, outputWriter);
                    if ((line = inputReader.readLine()) != null) {
                        inputLine = parseInputLine(line);
                    } else {
                        eofFlag = 'Y';
                    }
                }
            } else {
                eofFlag = 'Y';
            }

            outputWriter.write(formatTotalsLine());
            outputWriter.newLine();
            outputWriter.newLine();
            outputWriter.write(formatTaxOwingLine());
            outputWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InputLine parseInputLine(String line) {
        InputLine il = new InputLine();
        if (line.length() >= 36) {
            il.transactionCode = line.charAt(0);
            String amountStr = line.substring(1, 8).trim();
            il.transactionAmount = new BigDecimal(amountStr).divide(new BigDecimal("100"));
            il.paymentType = line.substring(8, 10);
            il.storeNumber = Integer.parseInt(line.substring(10, 12));
            il.invoiceNumber = line.substring(12, 21);
            il.skuCode = line.substring(21, 36);
        }
        return il;
    }

    private static void printHeadings(BufferedWriter writer) throws IOException {
        writer.write(headingLine1);
        writer.newLine();
        writer.write(headingLine2);
        writer.newLine();
        writer.write(headingLine3);
        writer.newLine();
        writer.write(underlines);
        writer.newLine();
    }

    private static void processPage(InputLine inputLine, BufferedWriter writer) throws IOException {
        if (lineCount >= linesPerPage) {
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(headingLine1);
            writer.newLine();
            writer.newLine();
            writer.write(headingLine2);
            writer.newLine();
            writer.newLine();
            writer.write(headingLine3);
            writer.newLine();
            writer.write(underlines);
            writer.newLine();
            writer.newLine();
            lineCount = 0;
            pageNum++;
        }

        if (inputLine.transactionCode == wsReturns) {
            totAmtRRecords = totAmtRRecords.add(inputLine.transactionAmount);
            lineCount++;
            totNumRRecords++;
            calculations(inputLine, writer);
            writeDetailLine(inputLine, writer);
        }
    }

    private static void calculations(InputLine inputLine, BufferedWriter writer) {
        taxOwing = inputLine.transactionAmount.multiply(taxValue).setScale(2, RoundingMode.HALF_UP);
        calcPercentageOfEachPaymentType();
    }

    private static void calcPercentageOfEachPaymentType() {
        totTaxOwing = totTaxOwing.add(taxOwing);
    }

    private static void writeDetailLine(InputLine inputLine, BufferedWriter writer) throws IOException {
        DecimalFormat amountFormat = new DecimalFormat("$#,##0.00");
        String line = String.format("     %c        %s  %s    %2d    %-9s    %-15s %s",
                inputLine.transactionCode,
                amountFormat.format(inputLine.transactionAmount),
                inputLine.paymentType,
                inputLine.storeNumber,
                inputLine.invoiceNumber,
                inputLine.skuCode,
                amountFormat.format(taxOwing));
        writer.write(line);
        writer.newLine();
    }

    private static String formatTotalsLine() {
        return String.format(" TOTAL NUMBER OF 'R' RECORDS:  %2d          TOTAL AMOUNT OF 'R' RECORDS: $%,d.%02d",
                totNumRRecords,
                totAmtRRecords.longValue() / 100,
                totAmtRRecords.longValue() % 100);
    }

    private static String formatTaxOwingLine() {
        DecimalFormat format = new DecimalFormat("$#,##0.00");
        return " TOTAL TAX OWING:  " + format.format(totTaxOwing);
    }
}