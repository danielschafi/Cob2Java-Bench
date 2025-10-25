import java.io.*;
import java.text.DecimalFormat;

public class DataSplit {
    public static void main(String[] args) {
        String inputFileName = "valid.out";
        String slRecordsFileName = "SL.out";
        String returnRecordsFileName = "returns.out";
        String countsFileName = "counts.out";

        String wsEof = "N";
        String wsNewline = " ";

        int wsSaleCount = 0;
        double wsSaleTamount = 0.0;
        int wsLayawayCount = 0;
        double wsLayawayTamount = 0.0;
        double wsSLStore01Tamount = 0.0;
        double wsSLStore02Tamount = 0.0;
        double wsSLStore03Tamount = 0.0;
        double wsSLStore07Tamount = 0.0;
        double wsSLTamount = 0.0;

        int wsReturnCount = 0;
        double wsReturnTamount = 0.0;
        double wsRStore01Tamount = 0.0;
        double wsRStore02Tamount = 0.0;
        double wsRStore03Tamount = 0.0;
        double wsRStore07Tamount = 0.0;
        double wsRCalcTotal = 0.0;

        String wsSLHeadline = "                  Sale & Layway Report";
        String wsSColumns = "Total Sale records     Total Sale amount     Sale Percentage: ";
        String wsLColumns = "Total Layaway records  Total Layway amount   Layaway Percentage: ";
        String wsSOutput = "       000     $000,000.00     00.0%";
        String wsLOutput = "       000     $000,000.00     00.0%";
        String wsSLTotal = "Total Sale and Layway record: 000   Total Sale and Layway amount: $000,000.00";
        String wsSLStoreHead = "Total Transaction Stores";
        String wsSLStore01 = "Store-01: $000,000.00";
        String wsSLStore02 = "Store-02: $000,000.00";
        String wsSLStore03 = "Store-03: $000,000.00";
        String wsSLStore07 = "Store-07: $000,000.00";

        String wsRHeadline = "                  Returns Report";
        String wsROutput = "       000     $000,000.00";
        String wsRColumns = "Total returns records  Total returns amount";
        String wsRStoreHead = "Total Return Stores";
        String wsRStore01 = "Store-01: $000,000.00";
        String wsRStore02 = "Store-02: $000,000.00";
        String wsRStore03 = "Store-03: $000,000.00";
        String wsRStore07 = "Store-07: $000,000.00";
        String wsRGrandTotal = "Grand Total Amount: $000,000.00";

        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter slRecordsFile = new BufferedWriter(new FileWriter(slRecordsFileName));
             BufferedWriter returnRecordsFile = new BufferedWriter(new FileWriter(returnRecordsFileName));
             BufferedWriter countsFile = new BufferedWriter(new FileWriter(countsFileName))) {

            String inputLine;
            while ((inputLine = inputFile.readLine()) != null) {
                if (inputLine.length() < 36) continue;

                char ilTransactionCode = inputLine.charAt(0);
                double ilTransactionAmount = Double.parseDouble(inputLine.substring(1, 8).replace(" ", ""));
                String ilPaymentType = inputLine.substring(8, 10);
                int ilStoreNumber = Integer.parseInt(inputLine.substring(10, 12));
                String ilInvoiceNumber = inputLine.substring(12, 20);
                String ilSKUCode = inputLine.substring(20, 35);

                if (ilTransactionCode == 'S' || ilTransactionCode == 'L') {
                    if (ilTransactionCode == 'S') {
                        wsSaleCount++;
                        wsSaleTamount += ilTransactionAmount;
                    }
                    if (ilTransactionCode == 'L') {
                        wsLayawayCount++;
                        wsLayawayTamount += ilTransactionAmount;
                    }

                    switch (ilStoreNumber) {
                        case 1:
                            wsSLStore01Tamount += ilTransactionAmount;
                            break;
                        case 2:
                            wsSLStore02Tamount += ilTransactionAmount;
                            break;
                        case 3:
                            wsSLStore03Tamount += ilTransactionAmount;
                            break;
                        case 7:
                            wsSLStore07Tamount += ilTransactionAmount;
                            break;
                    }

                    slRecordsFile.write(inputLine);
                    slRecordsFile.newLine();
                } else {
                    wsReturnCount++;
                    wsReturnTamount += ilTransactionAmount;

                    switch (ilStoreNumber) {
                        case 1:
                            wsRStore01Tamount += ilTransactionAmount;
                            break;
                        case 2:
                            wsRStore02Tamount += ilTransactionAmount;
                            break;
                        case 3:
                            wsRStore03Tamount += ilTransactionAmount;
                            break;
                        case 7:
                            wsRStore07Tamount += ilTransactionAmount;
                            break;
                    }

                    returnRecordsFile.write(inputLine);
                    returnRecordsFile.newLine();
                }
            }

            wsSLTamount = wsSaleTamount + wsLayawayTamount;
            double wsSperc = (wsSaleCount * 100.0) / (wsSaleCount + wsLayawayCount);
            double wsLperc = (wsLayawayCount * 100.0) / (wsSaleCount + wsLayawayCount);

            DecimalFormat df = new DecimalFormat("$#,##0.00");
            DecimalFormat df2 = new DecimalFormat("000");
            DecimalFormat df3 = new DecimalFormat("00.00");

            wsSOutput = "       " + df2.format(wsSaleCount) + "     " + df.format(wsSaleTamount) + "     " + df3.format(wsSperc) + "%";
            wsLOutput = "       " + df2.format(wsLayawayCount) + "     " + df.format(wsLayawayTamount) + "     " + df3.format(wsLperc) + "%";
            wsSLTotal = "Total Sale and Layway record: " + df2.format(wsSaleCount + wsLayawayCount) + "   Total Sale and Layway amount: " + df.format(wsSLTamount);
            wsSLStore01 = "Store-01: " + df.format(wsSLStore01Tamount);
            wsSLStore02 = "Store-02: " + df.format(wsSLStore02Tamount);
            wsSLStore03 = "Store-03: " + df.format(wsSLStore03Tamount);
            wsSLStore07 = "Store-07: " + df.format(wsSLStore07Tamount);

            wsROutput = "       " + df2.format(wsReturnCount) + "     " + df.format(wsReturnTamount);
            wsRStore01 = "Store-01: " + df.format(wsRStore01Tamount);
            wsRStore02 = "Store-02: " + df.format(wsRStore02Tamount);
            wsRStore03 = "Store-03: " + df.format(wsRStore03Tamount);
            wsRStore07 = "Store-07: " + df.format(wsRStore07Tamount);
            wsRCalcTotal = wsSLTamount - wsReturnTamount;
            wsRGrandTotal = "Grand Total Amount: " + df.format(wsRCalcTotal);

            countsFile.write(wsSLHeadline);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsSColumns);
            countsFile.newLine();
            countsFile.write(wsSOutput);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsLColumns);
            countsFile.newLine();
            countsFile.write(wsLOutput);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsSLTotal);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsSLStoreHead);
            countsFile.newLine();
            countsFile.write(wsSLStore01);
            countsFile.newLine();
            countsFile.write(wsSLStore02);
            countsFile.newLine();
            countsFile.write(wsSLStore03);
            countsFile.newLine();
            countsFile.write(wsSLStore07);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsRHeadline);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsRColumns);
            countsFile.newLine();
            countsFile.write(wsROutput);
            countsFile.newLine();
            countsFile.write(wsNewline);
            countsFile.write(wsRStoreHead);