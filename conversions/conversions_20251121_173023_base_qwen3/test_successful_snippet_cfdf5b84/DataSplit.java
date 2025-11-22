import java.io.*;
import java.text.DecimalFormat;

public class DataSplit {
    private static final String INPUT_FILE = "valid.out";
    private static final String SL_OUTPUT_FILE = "SL.out";
    private static final String RETURNS_OUTPUT_FILE = "returns.out";
    private static final String COUNTS_OUTPUT_FILE = "counts.out";

    // Input record fields
    private static char ilTransactionCode;
    private static double ilTransactionAmount;
    private static String ilPaymentType;
    private static int ilStoreNumber;
    private static String invoiceNumber1;
    private static char invoiceNumber2;
    private static int invoiceNumber3;
    private static String ilSKUCode;

    // Working storage variables
    private static boolean wsEof = false;
    private static String wsNewline = "\n";

    // SL storage
    private static int wsSaleCount = 0;
    private static double wsSaleTamount = 0.0;
    private static int wsLayawayCount = 0;
    private static double wsLayawayTamount = 0.0;
    private static double wsSLStore01Tamount = 0.0;
    private static double wsSLStore02Tamount = 0.0;
    private static double wsSLStore03Tamount = 0.0;
    private static double wsSLStore07Tamount = 0.0;
    private static double wsSLTamount = 0.0;

    // Return storage
    private static int wsReturnCount = 0;
    private static double wsReturnTamount = 0.0;
    private static double wsRStore01Tamount = 0.0;
    private static double wsRStore02Tamount = 0.0;
    private static double wsRStore03Tamount = 0.0;
    private static double wsRStore07Tamount = 0.0;

    // Output storage
    private static int olSRecord;
    private static double olSTotal;
    private static int olLRecord;
    private static double olLTotal;
    private static double olSLStore01;
    private static double olSLStore02;
    private static double olSLStore03;
    private static double olSLStore07;
    private static int wsSLCombineTotal;
    private static double wsSLTamountFinal;
    private static double wsSPerc;
    private static double wsLPerc;
    private static int olRRecord;
    private static double olRTotal;
    private static double olRStore01;
    private static double olRStore02;
    private static double olRStore03;
    private static double olRStore07;
    private static double wsRCalcTotal;

    public static void main(String[] args) {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            BufferedWriter slOutputFile = new BufferedWriter(new FileWriter(SL_OUTPUT_FILE));
            BufferedWriter returnsOutputFile = new BufferedWriter(new FileWriter(RETURNS_OUTPUT_FILE));
            BufferedWriter countsOutputFile = new BufferedWriter(new FileWriter(COUNTS_OUTPUT_FILE));

            String line;
            while ((line = inputFile.readLine()) != null) {
                parseInputLine(line);
                checkSLorR(slOutputFile, returnsOutputFile);
            }

            calcAndMoveSLTotals();
            writeSL(countsOutputFile);

            calcAndMoveRTotals();
            writeR(countsOutputFile);

            inputFile.close();
            slOutputFile.close();
            returnsOutputFile.close();
            countsOutputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInputLine(String line) {
        if (line.length() >= 36) {
            ilTransactionCode = line.charAt(0);
            ilTransactionAmount = Double.parseDouble(line.substring(1, 7).trim());
            ilPaymentType = line.substring(7, 9);
            ilStoreNumber = Integer.parseInt(line.substring(9, 11).trim());
            invoiceNumber1 = line.substring(11, 13);
            invoiceNumber2 = line.charAt(13);
            invoiceNumber3 = Integer.parseInt(line.substring(14, 20).trim());
            ilSKUCode = line.substring(20, 35).trim();
        }
    }

    private static void checkSLorR(BufferedWriter slOutputFile, BufferedWriter returnsOutputFile) throws IOException {
        if (ilTransactionCode == 'S' || ilTransactionCode == 'L') {
            slCountAndAdd();
            slOutputFile.write(lineToString());
            slOutputFile.newLine();
        } else {
            rCountAndAdd();
            returnsOutputFile.write(lineToString());
            returnsOutputFile.newLine();
        }
    }

    private static String lineToString() {
        return "" + ilTransactionCode +
                String.format("%05.2f", ilTransactionAmount) +
                ilPaymentType +
                String.format("%02d", ilStoreNumber) +
                invoiceNumber1 +
                invoiceNumber2 +
                String.format("%06d", invoiceNumber3) +
                ilSKUCode;
    }

    private static void slCountAndAdd() {
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
    }

    private static void rCountAndAdd() {
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
    }

    private static void calcAndMoveSLTotals() {
        olSRecord = wsSaleCount;
        olSTotal = wsSaleTamount;
        olLRecord = wsLayawayCount;
        olLTotal = wsLayawayTamount;
        olSLStore01 = wsSLStore01Tamount;
        olSLStore02 = wsSLStore02Tamount;
        olSLStore03 = wsSLStore03Tamount;
        olSLStore07 = wsSLStore07Tamount;

        wsSLCombineTotal = wsSaleCount + wsLayawayCount;
        wsSLTamount = wsSaleTamount + wsLayawayTamount;

        if (wsSLCombineTotal > 0) {
            wsSPerc = Math.round(((double) wsSaleCount / wsSLCombineTotal) * 10000.0) / 100.0;
            wsLPerc = Math.round(((double) wsLayawayCount / wsSLCombineTotal) * 10000.0) / 100.0;
        } else {
            wsSPerc = 0.0;
            wsLPerc = 0.0;
        }

        wsSLTamountFinal = wsSLTamount;
    }

    private static void calcAndMoveRTotals() {
        olRRecord = wsReturnCount;
        olRTotal = wsReturnTamount;
        olRStore01 = wsRStore01Tamount;
        olRStore02 = wsRStore02Tamount;
        olRStore03 = wsRStore03Tamount;
        olRStore07 = wsRStore07Tamount;

        wsRCalcTotal = wsSLTamount - wsReturnTamount;
    }

    private static void writeSL(BufferedWriter countsOutputFile) throws IOException {
        countsOutputFile.write("                       Sale & Layway Report");
        countsOutputFile.newLine();
        countsOutputFile.write("Total Sale records              Total Sale amount           Sale Percentage: ");
        countsOutputFile.write(String.format("%5.2f", wsSPerc) + "%");
        countsOutputFile.newLine();
        countsOutputFile.write("Total Layaway records           Total Layway amount         Layaway Percentage: ");
        countsOutputFile.write(String.format("%5.2f", wsLPerc) + "%");
        countsOutputFile.newLine();
        countsOutputFile.write("Total Sale and Layway record: " + wsSLCombineTotal +
                "                        Total Sale and Layway amount: " +
                formatCurrency(wsSLTamountFinal));
        countsOutputFile.newLine();
        countsOutputFile.write("Total Transaction Stores");
        countsOutputFile.newLine();
        countsOutputFile.write("Store-01: " + formatCurrency(olSLStore01));
        countsOutputFile.newLine();
        countsOutputFile.write("Store-02: " + formatCurrency(olSLStore02));
        countsOutputFile.newLine();
        countsOutputFile.write("Store-03: " + formatCurrency(olSLStore03));
        countsOutputFile.newLine();
        countsOutputFile.write("Store-07: " + formatCurrency(olSLStore07));
        countsOutputFile