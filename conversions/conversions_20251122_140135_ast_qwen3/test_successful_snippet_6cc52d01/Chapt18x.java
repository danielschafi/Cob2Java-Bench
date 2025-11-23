import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Chapt18x {
    private static final String TRANS_FILE = "Trans.Seq";
    private static final String DEALER_FILE = "Dealer.Seq";
    private static final String DEALER_OUT = "Dealer.Out";
    
    // File records
    private static class TransRecord {
        int transactionDate;
        String transactionType;
        String transactionDealer;
        double transactionPrice;
        int transactionQty;
        String filler;
        
        public TransRecord() {
            this.transactionType = new String(new char[4]);
            this.transactionDealer = new String(new char[8]);
            this.filler = new String(new char[40]);
        }
    }
    
    private static class DealerRecord {
        String dealerNumber;
        String lastName;
        String firstName;
        String middleName;
        String addressLine1;
        String addressLine2;
        String city;
        String stateOrCountry;
        String postalCode;
        String homePhone;
        String workPhone;
        String otherPhone;
        int startDate;
        int lastRentPaidDate;
        int nextRentDueDate;
        double rentAmount;
        int consignmentPercent;
        double lastSoldAmount;
        int lastSoldDate;
        double soldToDate;
        double commissionToDate;
        String filler;
        
        public DealerRecord() {
            this.dealerNumber = new String(new char[8]);
            this.lastName = new String(new char[25]);
            this.firstName = new String(new char[15]);
            this.middleName = new String(new char[10]);
            this.addressLine1 = new String(new char[50]);
            this.addressLine2 = new String(new char[50]);
            this.city = new String(new char[40]);
            this.stateOrCountry = new String(new char[20]);
            this.postalCode = new String(new char[15]);
            this.homePhone = new String(new char[20]);
            this.workPhone = new String(new char[20]);
            this.otherPhone = new String(new char[20]);
            this.filler = new String(new char[15]);
        }
    }
    
    // Working storage variables
    private static double currentCommission = 0.0;
    private static double totalCommission = 0.0;
    private static int transactionsRead = 0;
    private static int masterRecordsWritten = 0;
    
    private static TransRecord transRecord = new TransRecord();
    private static DealerRecord dealerRecord = new DealerRecord();
    private static DealerRecord holdRecord = new DealerRecord();
    private static boolean creatingNewMaster = false;
    
    private static DecimalFormat editCountFormat = new DecimalFormat("###,###");
    private static DecimalFormat editAmtFormat = new DecimalFormat("#,##0.00-");
    
    private static BufferedReader transReader;
    private static BufferedReader dealerReader;
    private static PrintWriter dealerOutWriter;
    
    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18x");
        
        try {
            openFiles();
            readDealer();
            readTrans();
            
            while (!(isHighValues(transRecord) && isHighValues(dealerRecord))) {
                processFiles();
            }
            
            closeFiles();
            
            System.out.println("Processing Complete");
            System.out.println("Transactions Read " + editCountFormat.format(transactionsRead));
            System.out.println("Master Records Written " + editCountFormat.format(masterRecordsWritten));
            System.out.println("Total Commission  " + editAmtFormat.format(totalCommission));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void openFiles() throws IOException {
        transReader = new BufferedReader(new FileReader(TRANS_FILE));
        dealerReader = new BufferedReader(new FileReader(DEALER_FILE));
        dealerOutWriter = new PrintWriter(new FileWriter(DEALER_OUT));
    }
    
    private static void closeFiles() throws IOException {
        if (transReader != null) transReader.close();
        if (dealerReader != null) dealerReader.close();
        if (dealerOutWriter != null) dealerOutWriter.close();
    }
    
    private static void processFiles() {
        if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) < 0) {
            writeDealerOut();
            readDealer();
        } else if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) > 0) {
            createNewMaster();
            readTrans();
        } else {
            applyTransaction();
            readTrans();
        }
    }
    
    private static void applyTransaction() {
        dealerRecord.soldToDate += (transRecord.transactionQty * transRecord.transactionPrice);
        
        currentCommission = (transRecord.transactionQty * transRecord.transactionPrice) * 
                           (dealerRecord.consignmentPercent / 100.0);
        
        dealerRecord.commissionToDate += currentCommission;
        totalCommission += currentCommission;
        
        // Date handling logic
        int compareDate1 = reverseDate(dealerRecord.lastSoldDate);
        int compareDate2 = reverseDate(transRecord.transactionDate);
        
        if (compareDate2 > compareDate1) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }
    
    private static int reverseDate(int date) {
        // Convert date from YYYYMMDD format to MMDDYYYY format for comparison
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;
        return month * 10000 + day * 100 + year;
    }
    
    private static void writeDealerOut() {
        masterRecordsWritten++;
        dealerOutWriter.println(formatDealerRecord());
    }
    
    private static String formatDealerRecord() {
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
        sb.append(String.format("%04.2f", dealerRecord.rentAmount));
        sb.append(String.format("%03d", dealerRecord.consignmentPercent));
        sb.append(String.format("%07.2f", dealerRecord.lastSoldAmount));
        sb.append(String.format("%08d", dealerRecord.lastSoldDate));
        sb.append(String.format("%07.2f", dealerRecord.soldToDate));
        sb.append(String.format("%07.2f", dealerRecord.commissionToDate));
        sb.append(String.format("%-15s", dealerRecord.filler));
        return sb.toString();
    }
    
    private static void createNewMaster() {
        creatingNewMaster = true;
        // Move the current dealer master to a hold area
        holdRecord = copyDealerRecord(dealerRecord);
        
        // Initialize the dealer record
        dealerRecord = new DealerRecord();
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        
        // Apply the transaction like any other
        applyTransaction();
    }
    
    private static DealerRecord copyDealerRecord(DealerRecord source) {
        DealerRecord copy = new DealerRecord();
        copy.dealerNumber = source.dealerNumber;
        copy.lastName = source.lastName;
        copy.firstName = source.firstName;
        copy.middleName = source.middleName;
        copy.addressLine1 = source.addressLine1;
        copy.addressLine2 = source.addressLine2;
        copy.city = source.city;
        copy.stateOrCountry = source.stateOrCountry;
        copy.postalCode = source.postalCode;
        copy.homePhone = source.homePhone;
        copy.workPhone = source.workPhone;
        copy.otherPhone = source.otherPhone;
        copy.startDate = source.startDate;
        copy.lastRentPaidDate = source.lastRentPaidDate;
        copy.nextRentDueDate = source.nextRentDueDate;
        copy.rentAmount = source.rentAmount;
        copy.consignmentPercent = source.consignmentPercent;
        copy.lastSoldAmount = source.lastSoldAmount;
        copy.lastSoldDate = source.lastSoldDate;
        copy.soldToDate = source.soldToDate;
        copy.commissionToDate = source.commissionToDate;
        copy.filler = source.filler;
        return copy;
    }
    
    private static void readDealer() {
        if (creatingNewMaster) {
            dealerRecord = holdRecord;
            creatingNewMaster = false;
        } else {
            try {
                String line = dealerReader.readLine();
                if (line == null) {
                    dealerRecord = new DealerRecord();
                    dealerRecord.dealerNumber = "        "; // High values
                } else {
                    parseDealerRecord(line);
                }
            } catch (IOException e) {
                dealer