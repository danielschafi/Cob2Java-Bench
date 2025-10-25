import java.io.*;
import java.text.DecimalFormat;

public class Chapt18c {
    private static final String HIGH_VALUES = "        ";
    private static final int RECORD_LENGTH_DEALER = 376;
    private static final int RECORD_LENGTH_TRANS = 80;
    private static final int RECORD_LENGTH_REJECT = 72;

    private static double currentCommission = 0.0;
    private static double totalCommission = 0.0;
    private static int transactionsRead = 0;
    private static int transactionsRejected = 0;
    private static String workDate = "00000000";
    private static String reverseDate = "00000000";
    private static String compareDate1 = "00000000";
    private static String compareDate2 = "00000000";
    private static String editCount = "00000";
    private static String editAmt = "0000000.00";

    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18c");

        try (BufferedReader transFile = new BufferedReader(new FileReader("Trans.Seq"));
             BufferedReader dealerFile = new BufferedReader(new FileReader("Dealer.Seq"));
             BufferedWriter rejectFile = new BufferedWriter(new FileWriter("Reject.Txt"));
             BufferedWriter dealerOut = new BufferedWriter(new FileWriter("Dealer.Out"))) {

            String dealerRecord = readDealer(dealerFile);
            String transRecord = readTrans(transFile);

            while (!(dealerRecord.equals(HIGH_VALUES) && transRecord.equals(HIGH_VALUES))) {
                processFiles(dealerRecord, transRecord, dealerFile, transFile, dealerOut, rejectFile);
                dealerRecord = readDealer(dealerFile);
                transRecord = readTrans(transFile);
            }

            System.out.println("Processing Complete");
            System.out.println("Transactions Read " + editCount);
            System.out.println("Transactions Rejected " + editCount);
            System.out.println("Total Commission  " + editAmt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFiles(String dealerRecord, String transRecord, BufferedReader dealerFile, BufferedReader transFile, BufferedWriter dealerOut, BufferedWriter rejectFile) throws IOException {
        String dealerNumber = dealerRecord.substring(0, 8).trim();
        String transactionDealer = transRecord.substring(12, 20).trim();

        if (dealerNumber.compareTo(transactionDealer) < 0) {
            dealerOut.write(dealerRecord, 0, RECORD_LENGTH_DEALER);
            dealerOut.newLine();
        } else if (dealerNumber.compareTo(transactionDealer) > 0) {
            transactionsRejected++;
            rejectFile.write(transRecord, 0, RECORD_LENGTH_TRANS);
            rejectFile.newLine();
        } else {
            applyTransaction(dealerRecord, transRecord);
        }
    }

    private static void applyTransaction(String dealerRecord, String transRecord) {
        double transactionPrice = Double.parseDouble(transRecord.substring(20, 30));
        int transactionQty = Integer.parseInt(transRecord.substring(30, 33));
        double consignmentPercent = Double.parseDouble(dealerRecord.substring(150, 153));
        double soldToDate = Double.parseDouble(dealerRecord.substring(210, 220));
        String lastSoldDate = dealerRecord.substring(220, 228);

        soldToDate += transactionQty * transactionPrice;
        currentCommission = Math.round((transactionQty * transactionPrice) * (consignmentPercent / 100) * 100.0) / 100.0;
        totalCommission += currentCommission;

        workDate = lastSoldDate;
        reverseDate = workDate.substring(4, 8) + workDate.substring(0, 4) + workDate.substring(2, 4);
        compareDate1 = reverseDate;

        workDate = transRecord.substring(0, 8);
        reverseDate = workDate.substring(4, 8) + workDate.substring(0, 4) + workDate.substring(2, 4);
        compareDate2 = reverseDate;

        if (compareDate2.compareTo(compareDate1) > 0) {
            lastSoldDate = transRecord.substring(0, 8);
        }

        dealerRecord = dealerRecord.substring(0, 220) + String.format("%010.2f", soldToDate) + dealerRecord.substring(230, 240) + String.format("%010.2f", totalCommission) + dealerRecord.substring(250);
    }

    private static String readDealer(BufferedReader dealerFile) throws IOException {
        String dealerRecord = dealerFile.readLine();
        return dealerRecord != null ? dealerRecord : HIGH_VALUES;
    }

    private static String readTrans(BufferedReader transFile) throws IOException {
        String transRecord = transFile.readLine();
        if (transRecord != null) {
            transactionsRead++;
        }
        return transRecord != null ? transRecord : HIGH_VALUES;
    }
}