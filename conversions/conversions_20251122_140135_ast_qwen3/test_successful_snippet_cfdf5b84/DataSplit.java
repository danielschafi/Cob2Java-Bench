import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataSplit {
    private static final String INPUT_FILE = "valid.out";
    private static final String SL_OUTPUT_FILE = "SL.out";
    private static final String RETURNS_OUTPUT_FILE = "returns.out";
    private static final String COUNTS_OUTPUT_FILE = "counts.out";

    // Input record fields
    private static char ilTransactionCode;
    private static BigDecimal ilTransactionAmount;
    private static String ilPaymentType;
    private static int ilStoreNumber;
    private static String invoiceNumber1;
    private static char invoiceNumber2;
    private static int invoiceNumber3;
    private static String ilSKUCode;

    // Working storage variables
    private static String wsEof = "N";
    private static String wsNewline = "\n";
    
    // SL storage section
    private static int wsSaleCount = 0;
    private static BigDecimal wsSaleTamount = new BigDecimal("0");
    private static int wsLayawayCount = 0;
    private static BigDecimal wsLayawayTamount = new BigDecimal("0");
    private static BigDecimal wsSLStore01Tamount = new BigDecimal("0");
    private static BigDecimal wsSLStore02Tamount = new BigDecimal("0");
    private static BigDecimal wsSLStore03Tamount = new BigDecimal("0");
    private static BigDecimal wsSLStore07Tamount = new BigDecimal("0");
    private static BigDecimal wsSLTamount = new BigDecimal("0");

    // Return storage section
    private static int wsReturnCount = 0;
    private static BigDecimal wsReturnTamount = new BigDecimal("0");
    private static BigDecimal wsRStore01Tamount = new BigDecimal("0");
    private static BigDecimal wsRStore02Tamount = new BigDecimal("0");
    private static BigDecimal wsRStore03Tamount = new BigDecimal("0");
    private static BigDecimal wsRStore07Tamount = new BigDecimal("0");

    // Output storage sections
    private static String wsSLHeadline = "                    Sale & Layway Report";
    private static String wsSColumns = "Total Sale records        Total Sale amount           Sale Percentage: ";
    private static String wsLColumns = "Total Layaway records     Total Layway amount         Layaway Percentage: ";
    private static String wsSOutput = "       000                    $00,000.00           00.00%";
    private static String wsLOutput = "       000                    $00,000.00           00.00%";
    private static String wsSLTotal = "Total Sale and Layway record: 000   Total Sale and Layway amount: $00,000.00";
    private static String wsSLStoreHead = "Total Transaction Stores";
    private static String wsSLStore01 = "Store-01: $00,000.00";
    private static String wsSLStore02 = "Store-02: $00,000.00";
    private static String wsSLStore03 = "Store-03: $00,000.00";
    private static String wsSLStore07 = "Store-07: $00,000.00";
    
    private static String wsRHeadline = "                    Returns Report";
    private static String wsROutput = "       000                    $00,000.00";
    private static String wsRColumns = "Total returns records     Total returns amount";
    private static String wsRStoreHead = "Total Return Stores";
    private static String wsRStore01 = "Store-01: $00,000.00";
    private static String wsRStore02 = "Store-02: $00,000.00";
    private static String wsRStore03 = "Store-03: $00,000.00";
    private static String wsRStore07 = "Store-07: $00,000.00";
    private static String wsRGrandTotal = "Grand Total Amount: $00,000.00";

    // Output record fields
    private static String slLine = "";
    private static String rLine = "";
    private static String cLine = "";

    public static void main(String[] args) {
        try {
            BufferedReader inputFileReader = new BufferedReader(new FileReader(INPUT_FILE));
            BufferedWriter slOutputWriter = new BufferedWriter(new FileWriter(SL_OUTPUT_FILE));
            BufferedWriter returnsOutputWriter = new BufferedWriter(new FileWriter(RETURNS_OUTPUT_FILE));
            BufferedWriter countsOutputWriter = new BufferedWriter(new FileWriter(COUNTS_OUTPUT_FILE));

            String line = inputFileReader.readLine();
            while (line != null && !wsEof.equals("Y")) {
                parseInputLine(line);
                
                if (ilTransactionCode == 'S' || ilTransactionCode == 'L') {
                    perform110SLCountAndAdd();
                    slOutputWriter.write(line);
                    slOutputWriter.newLine();
                } else {
                    perform120RCountAndAdd();
                    returnsOutputWriter.write(line);
                    returnsOutputWriter.newLine();
                }
                
                line = inputFileReader.readLine();
            }

            perform210CalcAndMoveSLTotals();
            perform230WriteSL(countsOutputWriter);

            perform220CalcAndMoveRTotals();
            perform240WroteR(countsOutputWriter);

            inputFileReader.close();
            slOutputWriter.close();
            returnsOutputWriter.close();
            countsOutputWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInputLine(String line) {
        if (line.length() >= 36) {
            ilTransactionCode = line.charAt(0);
            ilTransactionAmount = new BigDecimal(line.substring(1, 7)).setScale(2, RoundingMode.HALF_UP);
            ilPaymentType = line.substring(7, 9);
            ilStoreNumber = Integer.parseInt(line.substring(9, 11));
            invoiceNumber1 = line.substring(11, 13);
            invoiceNumber2 = line.charAt(13);
            invoiceNumber3 = Integer.parseInt(line.substring(14, 20));
            ilSKUCode = line.substring(20, 35).trim();
        }
    }

    private static void perform110SLCountAndAdd() {
        if (ilTransactionCode == 'S') {
            wsSaleCount++;
            wsSaleTamount = wsSaleTamount.add(ilTransactionAmount);
        }

        if (ilTransactionCode == 'L') {
            wsLayawayCount++;
            wsLayawayTamount = wsLayawayTamount.add(ilTransactionAmount);
        }

        switch (ilStoreNumber) {
            case 1:
                wsSLStore01Tamount = wsSLStore01Tamount.add(ilTransactionAmount);
                break;
            case 2:
                wsSLStore02Tamount = wsSLStore02Tamount.add(ilTransactionAmount);
                break;
            case 3:
                wsSLStore03Tamount = wsSLStore03Tamount.add(ilTransactionAmount);
                break;
            case 7:
                wsSLStore07Tamount = wsSLStore07Tamount.add(ilTransactionAmount);
                break;
        }
    }

    private static void perform120RCountAndAdd() {
        wsReturnCount++;
        wsReturnTamount = wsReturnTamount.add(ilTransactionAmount);

        switch (ilStoreNumber) {
            case 1:
                wsRStore01Tamount = wsRStore01Tamount.add(ilTransactionAmount);
                break;
            case 2:
                wsRStore02Tamount = wsRStore02Tamount.add(ilTransactionAmount);
                break;
            case 3:
                wsRStore03Tamount = wsRStore03Tamount.add(ilTransactionAmount);
                break;
            case 7:
                wsRStore07Tamount = wsRStore07Tamount.add(ilTransactionAmount);
                break;
        }
    }

    private static void perform210CalcAndMoveSLTotals() {
        // Format sale count
        String formattedSaleCount = String.format("%03d", wsSaleCount);
        // Format sale total
        String formattedSaleTotal = String.format("$%,.2f", wsSaleTamount);
        
        // Format layaway count
        String formattedLayawayCount = String.format("%03d", wsLayawayCount);
        // Format layaway total
        String formattedLayawayTotal = String.format("$%,.2f", wsLayawayTamount);
        
        // Format store amounts
        String formattedStore01 = String.format("$%,.2f", wsSLStore01Tamount);
        String formattedStore02 = String.format("$%,.2f", wsSLStore02Tamount);
        String formattedStore03 = String.format("$%,.2f", wsSLStore03Tamount);
        String formattedStore07 = String.format("$%,.2f", wsSLStore07Tamount);

        // Update wsSOutput
        wsSOutput = "       " + formattedSaleCount + "                    " + formattedSaleTotal + "           00.00%";
        // Update wsLOutput
        wsLOutput = "       " + formattedLayawayCount + "                    " + formattedLayawayTotal + "           00.