import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Chapt18x {
    
    static class TransRecord {
        String transactionDate = "";
        String transactionType = "";
        String transactionDealer = "";
        long transactionPrice = 0;
        int transactionQty = 0;
        String filler = "";
    }
    
    static class DealerRecord {
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
        long rentAmount = 0;
        int consignmentPercent = 0;
        long lastSoldAmount = 0;
        String lastSoldDate = "";
        long soldToDate = 0;
        long commissionToDate = 0;
        String filler = "";
    }
    
    static long currentCommission = 0;
    static long totalCommission = 0;
    static int transactionsRead = 0;
    static int masterRecordsWritten = 0;
    static DealerRecord dealerRecord = new DealerRecord();
    static TransRecord transRecord = new TransRecord();
    static DealerRecord holdRecord = new DealerRecord();
    static boolean creatingNewMaster = false;
    
    static BufferedReader transFileReader;
    static BufferedReader dealerFileReader;
    static BufferedWriter dealerOutWriter;
    
    public static void main(String[] args) {
        try {
            System.out.println("Begin Process Chapt18x");
            
            dealerOutWriter = new BufferedWriter(new FileWriter("Dealer.Out"));
            transFileReader = new BufferedReader(new FileReader("Trans.Seq"));
            dealerFileReader = new BufferedReader(new FileReader("Dealer.Seq"));
            
            readDealer();
            readTrans();
            
            while (!isHighValues(transRecord) || !isHighValues(dealerRecord)) {
                processFiles();
            }
            
            dealerOutWriter.close();
            transFileReader.close();
            dealerFileReader.close();
            
            System.out.println("Processing Complete");
            System.out.println("Transactions Read " + String.format("%,d", transactionsRead));
            System.out.println("Master Records Written " + String.format("%,d", masterRecordsWritten));
            System.out.println("Total Commission  " + formatAmount(totalCommission));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void processFiles() throws IOException {
        if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) < 0) {
            writeDealerOut();
            readDealer();
        } else if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) > 0) {
            createNewMaster();
            readTrans();
        } else if (dealerRecord.dealerNumber.equals(transRecord.transactionDealer)) {
            applyTransaction();
            readTrans();
        }
    }
    
    static void applyTransaction() {
        soldToDate += (transRecord.transactionQty * transRecord.transactionPrice);
        
        currentCommission = Math.round((transRecord.transactionQty * transRecord.transactionPrice) * 
                                       (dealerRecord.consignmentPercent / 100.0));
        dealerRecord.commissionToDate += currentCommission;
        totalCommission += currentCommission;
        
        String lastSoldDateStr = dealerRecord.lastSoldDate;
        String transDateStr = transRecord.transactionDate;
        
        if (transDateStr.compareTo(lastSoldDateStr) > 0) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }
    
    static void writeDealerOut() throws IOException {
        masterRecordsWritten++;
        String output = formatDealerRecord(dealerRecord);
        dealerOutWriter.write(output);
        dealerOutWriter.newLine();
    }
    
    static void createNewMaster() {
        creatingNewMaster = true;
        copyDealerRecord(dealerRecord, holdRecord);
        dealerRecord = new DealerRecord();
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        applyTransaction();
    }
    
    static void readDealer() throws IOException {
        if (creatingNewMaster) {
            copyDealerRecord(holdRecord, dealerRecord);
            creatingNewMaster = false;
        } else {
            String line = dealerFileReader.readLine();
            if (line == null) {
                setHighValues(dealerRecord);
            } else {
                parseDealerRecord(line, dealerRecord);
            }
        }
    }
    
    static void readTrans() throws IOException {
        String line = transFileReader.readLine();
        if (line == null) {
            setHighValues(transRecord);
        } else {
            parseTransRecord(line, transRecord);
            transactionsRead++;
        }
    }
    
    static void parseTransRecord(String line, TransRecord rec) {
        try {
            rec.transactionDate = line.substring(0, 8);
            rec.transactionType = line.substring(8, 12);
            rec.transactionDealer = line.substring(12, 20);
            String priceStr = line.substring(20, 29);
            rec.transactionPrice = Long.parseLong(priceStr);
            String qtyStr = line.substring(29, 32);
            rec.transactionQty = Integer.parseInt(qtyStr);
            if (line.length() > 32) {
                rec.filler = line.substring(32);
            }
        } catch (Exception e) {
            setHighValues(rec);
        }
    }
    
    static void parseDealerRecord(String line, DealerRecord rec) {
        try {
            int pos = 0;
            rec.dealerNumber = line.substring(pos, pos + 8).trim();
            pos += 8;
            rec.lastName = line.substring(pos, pos + 25).trim();
            pos += 25;
            rec.firstName = line.substring(pos, pos + 15).trim();
            pos += 15;
            rec.middleName = line.substring(pos, pos + 10).trim();
            pos += 10;
            rec.addressLine1 = line.substring(pos, pos + 50).trim();
            pos += 50;
            rec.addressLine2 = line.substring(pos, pos + 50).trim();
            pos += 50;
            rec.city = line.substring(pos, pos + 40).trim();
            pos += 40;
            rec.stateOrCountry = line.substring(pos, pos + 20).trim();
            pos += 20;
            rec.postalCode = line.substring(pos, pos + 15).trim();
            pos += 15;
            rec.homePhone = line.substring(pos, pos + 20).trim();
            pos += 20;
            rec.workPhone = line.substring(pos, pos + 20).trim();
            pos += 20;
            rec.otherPhone = line.substring(pos, pos + 20).trim();
            pos += 20;
            rec.startDate = line.substring(pos, pos + 8).trim();
            pos += 8;
            rec.lastRentPaidDate = line.substring(pos, pos + 8).trim();
            pos += 8;
            rec.nextRentDueDate = line.substring(pos, pos + 8).trim();