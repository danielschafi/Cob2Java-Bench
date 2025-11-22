import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class Chapt18x {
    private static final String TRANSACTION_FILE = "Trans.Seq";
    private static final String DEALER_FILE = "Dealer.Seq";
    private static final String DEALER_OUT_FILE = "Dealer.Out";

    private static final int TRANSACTION_RECORD_LENGTH = 100;
    private static final int DEALER_RECORD_LENGTH = 376;
    private static final int DEALER_OUT_RECORD_LENGTH = 376;

    private static double currentCommission = 0.0;
    private static double totalCommission = 0.0;
    private static int transactionsRead = 0;
    private static int masterRecordsWritten = 0;

    private static String dealerNumber = "";
    private static String transactionDealer = "";
    private static String lastSoldDate = "";
    private static String transactionDate = "";

    private static String holdRecord = "";
    private static boolean creatingNewMaster = false;

    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18x");

        try (BufferedReader transReader = Files.newBufferedReader(Paths.get(TRANSACTION_FILE));
             BufferedReader dealerReader = Files.newBufferedReader(Paths.get(DEALER_FILE));
             BufferedWriter dealerOutWriter = Files.newBufferedWriter(Paths.get(DEALER_OUT_FILE))) {

            readDealer(dealerReader);
            readTrans(transReader);

            while (!transactionDealer.equals("High-Values") && !dealerNumber.equals("High-Values")) {
                processFiles(dealerOutWriter, transReader, dealerReader);
            }

            dealerOutWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processing Complete");
        System.out.println("Transactions Read " + transactionsRead);
        System.out.println("Master Records Written " + masterRecordsWritten);
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        System.out.println("Total Commission  " + df.format(totalCommission));
    }

    private static void processFiles(BufferedWriter dealerOutWriter, BufferedReader transReader, BufferedReader dealerReader) throws IOException {
        if (dealerNumber.compareTo(transactionDealer) < 0) {
            writeDealerOut(dealerOutWriter);
            readDealer(dealerReader);
        } else if (dealerNumber.compareTo(transactionDealer) > 0) {
            createNewMaster();
            readTrans(transReader);
        } else {
            applyTransaction();
            readTrans(transReader);
        }
    }

    private static void applyTransaction() {
        double soldToDate = Double.parseDouble(lastSoldDate) + (Integer.parseInt(transactionDate) * Double.parseDouble(transactionDealer));
        currentCommission = Math.round((Integer.parseInt(transactionDate) * Double.parseDouble(transactionDealer)) * (Double.parseDouble(dealerNumber) / 100.0) * 100.0) / 100.0;
        totalCommission += currentCommission;

        if (transactionDate.compareTo(lastSoldDate) > 0) {
            lastSoldDate = transactionDate;
        }
    }

    private static void writeDealerOut(BufferedWriter dealerOutWriter) throws IOException {
        masterRecordsWritten++;
        dealerOutWriter.write(String.format("%-376s", dealerNumber));
        dealerOutWriter.newLine();
    }

    private static void createNewMaster() {
        creatingNewMaster = true;
        holdRecord = dealerNumber;
        dealerNumber = transactionDealer;
        lastSoldDate = "0";
        applyTransaction();
    }

    private static void readDealer(BufferedReader dealerReader) throws IOException {
        if (creatingNewMaster) {
            dealerNumber = holdRecord;
            creatingNewMaster = false;
        } else {
            String line = dealerReader.readLine();
            if (line == null) {
                dealerNumber = "High-Values";
            } else {
                dealerNumber = line.substring(0, 8);
                lastSoldDate = line.substring(113, 121);
            }
        }
    }

    private static void readTrans(BufferedReader transReader) throws IOException {
        String line = transReader.readLine();
        if (line == null) {
            transactionDealer = "High-Values";
        } else {
            transactionDealer = line.substring(12, 20);
            transactionDate = line.substring(0, 8);
            transactionsRead++;
        }
    }
}