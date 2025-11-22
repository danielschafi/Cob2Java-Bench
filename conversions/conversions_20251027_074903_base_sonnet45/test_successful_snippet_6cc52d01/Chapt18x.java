import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;

public class Chapt18x {
    
    private static class TransRecord {
        String transactionDate = "";
        String transactionType = "";
        String transactionDealer = "";
        BigDecimal transactionPrice = BigDecimal.ZERO;
        int transactionQty = 0;
        
        boolean isHighValues = false;
    }
    
    private static class DealerRecord {
        String dealerNumber = "";
        String lastName = "";
        String firstName = "";
        String middleName = "";
        String addressLine1 = "";
        String addressLine2 = "";
        String city = "";
        String stateOrCountry = "";
        String postalCode = "";
        String homePhone = "";
        String workPhone = "";
        String otherPhone = "";
        String startDate = "";
        String lastRentPaidDate = "";
        String nextRentDueDate = "";
        BigDecimal rentAmount = BigDecimal.ZERO;
        int consignmentPercent = 0;
        BigDecimal lastSoldAmount = BigDecimal.ZERO;
        String lastSoldDate = "";
        BigDecimal soldToDate = BigDecimal.ZERO;
        BigDecimal commissionToDate = BigDecimal.ZERO;
        
        boolean isHighValues = false;
    }
    
    private static BigDecimal currentCommission = BigDecimal.ZERO;
    private static BigDecimal totalCommission = BigDecimal.ZERO;
    private static int transactionsRead = 0;
    private static int masterRecordsWritten = 0;
    private static String holdRecord = "";
    private static boolean creatingNewMaster = false;
    
    private static TransRecord transRecord = new TransRecord();
    private static DealerRecord dealerRecord = new DealerRecord();
    
    private static BufferedReader transFileReader;
    private static BufferedReader dealerFileReader;
    private static BufferedWriter dealerOutWriter;
    
    public static void main(String[] args) {
        try {
            chapt18xStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void chapt18xStart() throws IOException {
        System.out.println("Begin Process Chapt18x");
        
        dealerOutWriter = Files.newBufferedWriter(Paths.get("Dealer.Out"));
        
        if (Files.exists(Paths.get("Trans.Seq"))) {
            transFileReader = Files.newBufferedReader(Paths.get("Trans.Seq"));
        }
        
        if (Files.exists(Paths.get("Dealer.Seq"))) {
            dealerFileReader = Files.newBufferedReader(Paths.get("Dealer.Seq"));
        }
        
        readDealer();
        readTrans();
        
        while (!(transRecord.isHighValues && dealerRecord.isHighValues)) {
            processFiles();
        }
        
        if (dealerOutWriter != null) dealerOutWriter.close();
        if (transFileReader != null) transFileReader.close();
        if (dealerFileReader != null) dealerFileReader.close();
        
        System.out.println("Processing Complete");
        System.out.println("Transactions Read " + String.format("%,6d", transactionsRead));
        System.out.println("Master Records Written " + String.format("%,6d", masterRecordsWritten));
        System.out.println("Total Commission  " + formatAmount(totalCommission));
    }
    
    private static void processFiles() throws IOException {
        int comparison = compareKeys();
        
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
    }
    
    private static int compareKeys() {
        if (dealerRecord.isHighValues && transRecord.isHighValues) return 0;
        if (dealerRecord.isHighValues) return 1;
        if (transRecord.isHighValues) return -1;
        return dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer);
    }
    
    private static void applyTransaction() {
        BigDecimal transAmount = transRecord.transactionPrice.multiply(new BigDecimal(transRecord.transactionQty));
        dealerRecord.soldToDate = dealerRecord.soldToDate.add(transAmount);
        
        currentCommission = transAmount.multiply(new BigDecimal(dealerRecord.consignmentPercent))
                                      .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        
        dealerRecord.commissionToDate = dealerRecord.commissionToDate.add(currentCommission);
        totalCommission = totalCommission.add(currentCommission);
        
        String compareDate1 = convertToReverseDate(dealerRecord.lastSoldDate);
        String compareDate2 = convertToReverseDate(transRecord.transactionDate);
        
        if (compareDate2.compareTo(compareDate1) > 0) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }
    
    private static String convertToReverseDate(String date) {
        if (date == null || date.length() < 8) return "00000000";
        String mm = date.substring(0, 2);
        String dd = date.substring(2, 4);
        String yyyy = date.substring(4, 8);
        return yyyy + mm + dd;
    }
    
    private static void writeDealerOut() throws IOException {
        masterRecordsWritten++;
        String record = formatDealerRecord(dealerRecord);
        dealerOutWriter.write(record);
        dealerOutWriter.newLine();
    }
    
    private static void createNewMaster() {
        creatingNewMaster = true;
        holdRecord = formatDealerRecord(dealerRecord);
        
        dealerRecord = new DealerRecord();
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        
        applyTransaction();
    }
    
    private static void readDealer() throws IOException {
        if (creatingNewMaster) {
            parseDealerRecord(holdRecord);
            creatingNewMaster = false;
        } else {
            if (dealerFileReader != null) {
                String line = dealerFileReader.readLine();
                if (line == null) {
                    dealerRecord.isHighValues = true;
                } else {
                    parseDealerRecord(line);
                }
            } else {
                dealerRecord.isHighValues = true;
            }
        }
    }
    
    private static void readTrans() throws IOException {
        if (transFileReader != null) {
            String line = transFileReader.readLine();
            if (line == null) {
                transRecord.isHighValues = true;
            } else {
                parseTransRecord(line);
                transactionsRead++;
            }
        } else {
            transRecord.isHighValues = true;
        }
    }
    
    private static void parseTransRecord(String line) {
        if (line.length() < 74) line = String.format("%-74s", line);
        
        transRecord.transactionDate = line.substring(0, 8);
        transRecord.transactionType = line.substring(8, 12);
        transRecord.transactionDealer = line.substring(12, 20);
        
        String priceStr = line.substring(20, 29);
        boolean negative = priceStr.charAt(priceStr.length() - 1) >= 'p' && priceStr.charAt(priceStr.length() - 1) <= 'y';
        if (negative) {
            priceStr = priceStr.substring(0, pr