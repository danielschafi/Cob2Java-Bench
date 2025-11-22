import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chapt18x {
    private static final String TRANS_FILE = "Trans.Seq";
    private static final String DEALER_FILE = "Dealer.Seq";
    private static final String DEALER_OUT = "Dealer.Out";

    // File records
    private static class TransRecord {
        public int transactionDate;      // 9(8)
        public String transactionType;   // X(4)
        public String transactionDealer; // X(8)
        public double transactionPrice;  // S9(7)v99
        public int transactionQty;       // 9(3)
        public String filler;            // X(40)
    }

    private static class DealerRecord {
        public String dealerNumber;         // X(8)
        public String lastName;             // X(25)
        public String firstName;            // X(15)
        public String middleName;           // X(10)
        public String addressLine1;         // X(50)
        public String addressLine2;         // X(50)
        public String city;                 // X(40)
        public String stateOrCountry;       // X(20)
        public String postalCode;           // X(15)
        public String homePhone;            // X(20)
        public String workPhone;            // X(20)
        public String otherPhone;           // X(20)
        public int startDate;               // 9(8)
        public int lastRentPaidDate;        // 9(8)
        public int nextRentDueDate;         // 9(8)
        public double rentAmount;           // 9(4)v99
        public int consignmentPercent;      // 9(3)
        public double lastSoldAmount;       // S9(7)v99
        public int lastSoldDate;            // 9(8)
        public double soldToDate;           // S9(7)v99
        public double commissionToDate;     // S9(7)v99
        public String filler;               // X(15)
    }

    private static class DealerOutRecord {
        public String data; // X(376)
    }

    // Working storage variables
    private static double currentCommission = 0.0;
    private static double totalCommission = 0.0;
    private static int transactionsRead = 0;
    private static int masterRecordsWritten = 0;

    private static class WorkDate {
        public int workMM;
        public int workDD;
        public int workYYYY;
    }

    private static WorkDate workDate = new WorkDate();
    private static WorkDate reverseDate = new WorkDate();
    private static int compareDate1 = 0;
    private static int compareDate2 = 0;
    private static String editCount = "";
    private static String editAmt = "";
    private static DealerRecord holdRecord = new DealerRecord();
    private static boolean creatingNewMaster = false;

    private static TransRecord transRecord = new TransRecord();
    private static DealerRecord dealerRecord = new DealerRecord();
    private static DealerOutRecord dealerOutRecord = new DealerOutRecord();

    private static BufferedReader transReader;
    private static BufferedReader dealerReader;
    private static PrintWriter dealerWriter;

    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18x");

        try {
            openFiles();
            readDealer();
            readTrans();
            processFiles();
            closeFiles();
            displayResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openFiles() throws IOException {
        dealerWriter = new PrintWriter(new FileWriter(DEALER_OUT));
        transReader = new BufferedReader(new FileReader(TRANS_FILE));
        dealerReader = new BufferedReader(new FileReader(DEALER_FILE));
    }

    private static void closeFiles() throws IOException {
        dealerWriter.close();
        transReader.close();
        dealerReader.close();
    }

    private static void processFiles() throws IOException {
        while (!(isHighValues(transRecord) && isHighValues(dealerRecord))) {
            if (dealerRecord.dealerNumber != null && transRecord.transactionDealer != null) {
                int comparison = dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer);
                if (comparison < 0) {
                    writeDealerOut();
                    readDealer();
                } else if (comparison > 0) {
                    createNewMaster();
                    readTrans();
                } else {
                    applyTransaction();
                    readTrans();
                }
            } else if (dealerRecord.dealerNumber == null && transRecord.transactionDealer != null) {
                createNewMaster();
                readTrans();
            } else if (dealerRecord.dealerNumber != null && transRecord.transactionDealer == null) {
                writeDealerOut();
                readDealer();
            } else {
                break;
            }
        }
    }

    private static void applyTransaction() {
        dealerRecord.soldToDate += (transRecord.transactionQty * transRecord.transactionPrice);
        currentCommission = (transRecord.transactionQty * transRecord.transactionPrice) * 
                           (dealerRecord.consignmentPercent / 100.0);
        dealerRecord.commissionToDate += currentCommission;
        totalCommission += currentCommission;

        // Date handling
        workDate.workMM = Integer.parseInt(String.valueOf(dealerRecord.lastSoldDate).substring(4, 6));
        workDate.workDD = Integer.parseInt(String.valueOf(dealerRecord.lastSoldDate).substring(6, 8));
        workDate.workYYYY = Integer.parseInt(String.valueOf(dealerRecord.lastSoldDate).substring(0, 4));

        reverseDate.workYYYY = workDate.workYYYY;
        reverseDate.workMM = workDate.workMM;
        reverseDate.workDD = workDate.workDD;
        compareDate1 = reverseDate.workYYYY * 10000 + reverseDate.workMM * 100 + reverseDate.workDD;

        workDate.workMM = Integer.parseInt(String.valueOf(transRecord.transactionDate).substring(4, 6));
        workDate.workDD = Integer.parseInt(String.valueOf(transRecord.transactionDate).substring(6, 8));
        workDate.workYYYY = Integer.parseInt(String.valueOf(transRecord.transactionDate).substring(0, 4));

        reverseDate.workYYYY = workDate.workYYYY;
        reverseDate.workMM = workDate.workMM;
        reverseDate.workDD = workDate.workDD;
        compareDate2 = reverseDate.workYYYY * 10000 + reverseDate.workMM * 100 + reverseDate.workDD;

        if (compareDate2 > compareDate1) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }

    private static void writeDealerOut() {
        masterRecordsWritten++;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s", dealerRecord.dealerNumber));
        sb.append(String.format("%-25s", dealerRecord.lastName));
        sb.append(String.format("%-15s", dealerRecord.firstName));
        sb.append(String.format("%-10s", dealerRecord.middleName));
        sb.append(String.format("%-50s", dealerRecord.addressLine1));
        sb.append(String.format("%-50s", dealerRecord.addressLine2));
        sb.append(String.format("%-40s", dealerRecord.city));
        sb.append(String.format("%-20s", dealerRecord.stateOrCountry));
        sb.append(String.format("%-15s", dealerRecord.postalCode));
        sb.append(String.format("%-20s", dealerRecord.homePhone));
        sb.append(String.format("%-20s", dealerRecord.workPhone));
        sb.append(String.format("%-20s", dealerRecord.otherPhone));
        sb.append(String.format("%08d", dealerRecord.startDate));
        sb.append(String.format("%08d", dealerRecord.lastRentPaidDate));
        sb.append(String.format("%08d", dealerRecord.nextRentDueDate));
        sb.append(String.format("%04d", (int) dealerRecord.rentAmount));
        sb.append(String.format("%03d", dealerRecord.consignmentPercent));
        sb.append(String.format("%07d", (long) dealerRecord.lastSoldAmount));
        sb.append(String.format("%08d", dealerRecord.lastSoldDate));
        sb.append(String.format("%07d", (long) dealerRecord.soldToDate));
        sb.append(String.format("%07d", (long) dealerRecord.commissionToDate));
        sb.append(String.format("%-15s", dealerRecord.filler));
        dealerOutRecord.data = sb.toString();
        dealerWriter.println(dealerOutRecord.data);
    }

    private static void createNewMaster() {
        creatingNewMaster = true;
        holdRecord = copyDealerRecord(dealerRecord);
        initializeDealerRecord(dealerRecord);
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        applyTransaction();
    }

    private static void readDealer() throws IOException {
        if (creatingNewMaster) {
            dealerRecord = copyDealerRecord(holdRecord);
            creatingNewMaster = false;
        } else {
            String line = dealerReader.readLine();
            if (line == null) {
                dealerRecord = new DealerRecord();
                dealerRecord.dealerNumber = null;
            } else {
                parseDealerRecord(line);
            }
        }
    }

    private static void readTrans() throws IOException {
        String line = transReader.readLine();
        if (line == null) {
            transRecord = new TransRecord();
            transRecord.transactionDealer = null;
        } else {
            parseTransRecord(line);
            transactionsRead++;
        }