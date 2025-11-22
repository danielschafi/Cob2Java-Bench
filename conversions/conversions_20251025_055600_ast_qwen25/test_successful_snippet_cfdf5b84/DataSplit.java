import java.io.*;
import java.text.DecimalFormat;

public class DataSplit {
    private static final String INPUT_FILE = "valid.out";
    private static final String SL_FILE = "SL.out";
    private static final String RETURN_FILE = "returns.out";
    private static final String COUNTS_FILE = "counts.out";

    private static char wsEof = 'N';
    private static final char WS_NEWLINE = ' ';
    private static int wsSaleCount = 0;
    private static double wsSaleTamount = 0.0;
    private static int wsLayawayCount = 0;
    private static double wsLayawayTamount = 0.0;
    private static double wsSLStore01Tamount = 0.0;
    private static double wsSLStore02Tamount = 0.0;
    private static double wsSLStore03Tamount = 0.0;
    private static double wsSLStore07Tamount = 0.0;
    private static double wsSLTamount = 0.0;
    private static int wsReturnCount = 0;
    private static double wsReturnTamount = 0.0;
    private static double wsRStore01Tamount = 0.0;
    private static double wsRStore02Tamount = 0.0;
    private static double wsRStore03Tamount = 0.0;
    private static double wsRStore07Tamount = 0.0;
    private static double wsRCalcTotal = 0.0;

    private static final DecimalFormat DF = new DecimalFormat("000");
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("$#,##0.00");

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter slWriter = new BufferedWriter(new FileWriter(SL_FILE));
             BufferedWriter returnWriter = new BufferedWriter(new FileWriter(RETURN_FILE));
             BufferedWriter countsWriter = new BufferedWriter(new FileWriter(COUNTS_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 36) {
                    checkSLorR(line, slWriter, returnWriter);
                }
            }

            calcAndMoveSLTotals();
            writeSL(countsWriter);

            calcAndMoveRTotals();
            writeR(countsWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkSLorR(String line, BufferedWriter slWriter, BufferedWriter returnWriter) throws IOException {
        char transactionCode = line.charAt(0);
        if (transactionCode == 'S' || transactionCode == 'L') {
            slCountAndAdd(line);
            slWriter.write(line, 0, 36);
            slWriter.newLine();
        } else {
            rCountAndAdd(line);
            returnWriter.write(line, 0, 36);
            returnWriter.newLine();
        }
    }

    private static void slCountAndAdd(String line) {
        char transactionCode = line.charAt(0);
        double transactionAmount = Double.parseDouble(line.substring(1, 8).replace(" ", ""));

        if (transactionCode == 'S') {
            wsSaleCount++;
            wsSaleTamount += transactionAmount;
        }

        if (transactionCode == 'L') {
            wsLayawayCount++;
            wsLayawayTamount += transactionAmount;
        }

        int storeNumber = Integer.parseInt(line.substring(9, 11).trim());
        switch (storeNumber) {
            case 1:
                wsSLStore01Tamount += transactionAmount;
                break;
            case 2:
                wsSLStore02Tamount += transactionAmount;
                break;
            case 3:
                wsSLStore03Tamount += transactionAmount;
                break;
            case 7:
                wsSLStore07Tamount += transactionAmount;
                break;
        }
    }

    private static void rCountAndAdd(String line) {
        wsReturnCount++;
        double transactionAmount = Double.parseDouble(line.substring(1, 8).replace(" ", ""));
        wsReturnTamount += transactionAmount;

        int storeNumber = Integer.parseInt(line.substring(9, 11).trim());
        switch (storeNumber) {
            case 1:
                wsRStore01Tamount += transactionAmount;
                break;
            case 2:
                wsRStore02Tamount += transactionAmount;
                break;
            case 3:
                wsRStore03Tamount += transactionAmount;
                break;
            case 7:
                wsRStore07Tamount += transactionAmount;
                break;
        }
    }

    private static void calcAndMoveSLTotals() {
        wsSLTamount = wsSaleTamount + wsLayawayTamount;
        int wsSLCombineTotal = wsSaleCount + wsLayawayCount;
        double wsSPerc = (wsSaleCount * 100.0) / wsSLCombineTotal;
        double wsLperc = (wsLayawayCount * 100.0) / wsSLCombineTotal;

        writeCounts("Sale & Layway Report", wsSaleCount, wsSaleTamount, wsSPerc, wsLayawayCount, wsLayawayTamount, wsLperc, wsSLCombineTotal, wsSLTamount);
    }

    private static void writeSL(BufferedWriter writer) throws IOException {
        writer.write(String.format("%-80s", "Sale & Layway Report"));
        writer.newLine();
        writer.write(String.format("%-80s", " "));
        writer.newLine();
        writer.write(String.format("%-18s%-5s%-17s%-4s%-17s%-1s", "Total Sale records", " ", "Total Sale amount", " ", "Sale Percentage: ", "%"));
        writer.newLine();
        writer.write(String.format("%-7s%-3s%-20s%-10s%-4s%-10s%-1s", " ", DF.format(wsSaleCount), " ", AMOUNT_FORMAT.format(wsSaleTamount), " ", String.format("%.2f", (wsSaleCount * 100.0) / (wsSaleCount + wsLayawayCount)), "%"));
        writer.newLine();
        writer.write(String.format("%-21s%-5s%-19s%-2s%-20s%-1s", "Total Layaway records", " ", "Total Layway amount", " ", "Layaway Percentage: ", "%"));
        writer.newLine();
        writer.write(String.format("%-7s%-3s%-20s%-10s%-4s%-10s%-1s", " ", DF.format(wsLayawayCount), " ", AMOUNT_FORMAT.format(wsLayawayTamount), " ", String.format("%.2f", (wsLayawayCount * 100.0) / (wsSaleCount + wsLayawayCount)), "%"));
        writer.newLine();
        writer.write(String.format("%-80s", " "));
        writer.newLine();
        writer.write(String.format("%-30s%-3s%-30s%-10s", "Total Sale and Layway record: ", DF.format(wsSaleCount + wsLayawayCount), "Total Sale and Layway amount: ", AMOUNT_FORMAT.format(wsSLTamount)));
        writer.newLine();
        writer.write(String.format("%-80s", "Total Transaction Stores"));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-01: ", AMOUNT_FORMAT.format(wsSLStore01Tamount)));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-02: ", AMOUNT_FORMAT.format(wsSLStore02Tamount)));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-03: ", AMOUNT_FORMAT.format(wsSLStore03Tamount)));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-07: ", AMOUNT_FORMAT.format(wsSLStore07Tamount)));
        writer.newLine();
    }

    private static void calcAndMoveRTotals() {
        wsRCalcTotal = wsSLTamount - wsReturnTamount;
        writeCounts("Returns Report", wsReturnCount, wsReturnTamount, 0, 0, 0, 0, 0, wsRCalcTotal);
    }

    private static void writeR(BufferedWriter writer) throws IOException {
        writer.write(String.format("%-80s", "Returns Report"));
        writer.newLine();
        writer.write(String.format("%-80s", " "));
        writer.newLine();
        writer.write(String.format("%-21s%-5s%-20s", "Total returns records", " ", "Total returns amount"));
        writer.newLine();
        writer.write(String.format("%-7s%-3s%-20s", " ", DF.format(wsReturnCount), " ", AMOUNT_FORMAT.format(wsReturnTamount)));
        writer.newLine();
        writer.write(String.format("%-80s", "Total Return Stores"));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-01: ", AMOUNT_FORMAT.format(wsRStore01Tamount)));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-02: ", AMOUNT_FORMAT.format(wsRStore02Tamount)));
        writer.newLine();
        writer.write(String.format("%-10s%-10s", "Store-03: ", AMOUNT_FORMAT.format