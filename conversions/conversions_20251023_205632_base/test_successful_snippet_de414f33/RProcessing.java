import java.io.*;
import java.text.DecimalFormat;

public class RProcessing {
    public static void main(String[] args) {
        String inputFile = "returns.out";
        String outputFile = "rprocessing.out";
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));

            String wsEofFlag = "N";
            String wsReturns = "R";
            String wsPaymentCash = "CA";
            String wsPaymentCredit = "CR";
            String wsPaymentDebit = "DB";
            int wsPageNum = 0;
            int wsLineCount = 0;
            int wsLinesPerPage = 20;
            int wsTotNumRRecords = 0;
            double wsTotAmtRRecords = 0.0;
            double wsTotTaxOwing = 0.0;
            double wsTaxValue = 0.13;

            String wsHeadingLine1 = String.format("%30s%21s%19s", "", "R PROCESSING REPORT", "");
            String wsHeadingLine2 = String.format("%1s%11s%2s%11s%2s%7s%2s%5s%3s%7s%10s%3s%15s%3s", " ", "TRANSACTION", " ", "TRANSACTION", " ", "PAYMENT", " ", "STORE", " ", "INVOICE", " ", "SKU", " ", "TAX");
            String wsHeadingLine3 = String.format("%4s%4s%8s%6s%5s%4s%5s%6s%2s%7s%11s%4s%13s%5s", " ", "CODE", " ", "AMOUNT", " ", "TYPE", " ", "NUMBER", " ", "NUMBER", " ", "CODE", " ", "OWING");
            String wsUnderlines = String.format("%1s%11s%2s%11s%2s%7s%2s%6s%2s%7s%10s%4s%13s%5s", " ", "-----------", " ", "-----------", " ", "-------", " ", "------", " ", "-------", " ", "----", " ", "-----");

            writer.write(wsHeadingLine1);
            writer.newLine();
            writer.write(wsHeadingLine2);
            writer.newLine();
            writer.write(wsHeadingLine3);
            writer.newLine();
            writer.write(wsUnderlines);
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null && wsEofFlag.equals("N")) {
                if (line.length() < 36) {
                    wsEofFlag = "Y";
                    break;
                }

                String ilTransactionCode = line.substring(0, 1);
                double ilTransactionAmount = Double.parseDouble(line.substring(1, 8).replace(" ", ""));
                String ilPaymentType = line.substring(8, 10);
                int ilStoreNumber = Integer.parseInt(line.substring(10, 12).replace(" ", "0"));
                String ilInvoiceNumber = line.substring(12, 21);
                String ilSkuCode = line.substring(21, 36);

                if (wsLineCount >= wsLinesPerPage) {
                    writer.newLine();
                    writer.newLine();
                    writer.newLine();
                    writer.write(wsHeadingLine1);
                    writer.newLine();
                    writer.write(wsHeadingLine2);
                    writer.newLine();
                    writer.write(wsHeadingLine3);
                    writer.newLine();
                    writer.write(wsUnderlines);
                    writer.newLine();
                    wsLineCount = 0;
                    wsPageNum++;
                }

                if (ilTransactionCode.equals(wsReturns)) {
                    wsTotAmtRRecords += ilTransactionAmount;
                    wsTotNumRRecords++;

                    double wsTaxOwing = ilTransactionAmount * wsTaxValue;
                    wsTotTaxOwing += wsTaxOwing;

                    DecimalFormat df = new DecimalFormat("$#,##0.00");
                    String wsOlTransactionAmount = df.format(ilTransactionAmount);
                    String wsOlTaxOwing = df.format(wsTaxOwing);
                    String wsOlTotAmountRRecords = df.format(wsTotAmtRRecords);
                    String wsOlTotTaxOwing = df.format(wsTotTaxOwing);

                    String wsDetailLine = String.format("%5s%1s%8s%4s%2s%7s%4d%4s%15s%1s%5s", " ", ilTransactionCode, wsOlTransactionAmount, " ", ilPaymentType, " ", ilStoreNumber, " ", ilInvoiceNumber, " ", ilSkuCode, " ", wsOlTaxOwing);
                    writer.write(wsDetailLine);
                    writer.newLine();

                    wsLineCount++;
                }
            }

            writer.newLine();
            writer.newLine();
            writer.write(String.format("%29s%2d%29s%10s", "TOTAL NUMBER OF 'R' RECORDS: ", wsTotNumRRecords, "TOTAL AMOUNT OF 'R' RECORDS: ", wsOlTotAmountRRecords));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%17s%10s", "TOTAL TAX OWING: ", wsOlTotTaxOwing));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}