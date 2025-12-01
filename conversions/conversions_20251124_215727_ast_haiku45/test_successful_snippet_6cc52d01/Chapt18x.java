import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.*;

public class Chapt18x {
    static class TransRecord {
        String transactionDate = "";
        String transactionType = "";
        String transactionDealer = "";
        BigDecimal transactionPrice = BigDecimal.ZERO;
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
        BigDecimal rentAmount = BigDecimal.ZERO;
        int consignmentPercent = 0;
        BigDecimal lastSoldAmount = BigDecimal.ZERO;
        String lastSoldDate = "";
        BigDecimal soldToDate = BigDecimal.ZERO;
        BigDecimal commissionToDate = BigDecimal.ZERO;
        String filler = "";
    }

    static class WorkDate {
        String mm = "";
        String dd = "";
        String yyyy = "";
    }

    static class ReverseDate {
        String yyyy = "";
        String mm = "";
        String dd = "";
    }

    static BufferedReader transFileReader;
    static BufferedReader dealerFileReader;
    static BufferedWriter dealerOutWriter;

    static BigDecimal currentCommission = BigDecimal.ZERO;
    static BigDecimal totalCommission = BigDecimal.ZERO;
    static int transactionsRead = 0;
    static int masterRecordsWritten = 0;

    static TransRecord transRecord = new TransRecord();
    static DealerRecord dealerRecord = new DealerRecord();
    static WorkDate workDate = new WorkDate();
    static ReverseDate reverseDate = new ReverseDate();
    static String compareDate1 = "";
    static String compareDate2 = "";
    static String holdRecord = "";
    static boolean creatingNewMaster = false;

    public static void main(String[] args) throws IOException {
        System.out.println("Begin Process Chapt18x");

        transFileReader = new BufferedReader(new FileReader("Trans.Seq"));
        dealerFileReader = new BufferedReader(new FileReader("Dealer.Seq"));
        dealerOutWriter = new BufferedWriter(new FileWriter("Dealer.Out"));

        readDealer();
        readTrans();

        while (!isHighValues(transRecord) || !isHighValues(dealerRecord)) {
            processFiles();
        }

        dealerOutWriter.close();
        transFileReader.close();
        dealerFileReader.close();

        System.out.println("Processing Complete");
        System.out.println("Transactions Read " + formatCount(transactionsRead));
        System.out.println("Master Records Written " + formatCount(masterRecordsWritten));
        System.out.println("Total Commission  " + formatAmount(totalCommission));
    }

    static void processFiles() throws IOException {
        if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) < 0) {
            writeDealerOut();
            readDealer();
        } else if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) > 0) {
            createNewMaster();
            readTrans();
        } else if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) == 0) {
            applyTransaction();
            readTrans();
        }
    }

    static void applyTransaction() {
        dealerRecord.soldToDate = dealerRecord.soldToDate.add(
            BigDecimal.valueOf(transRecord.transactionQty).multiply(transRecord.transactionPrice)
        );

        currentCommission = BigDecimal.valueOf(transRecord.transactionQty)
            .multiply(transRecord.transactionPrice)
            .multiply(BigDecimal.valueOf(dealerRecord.consignmentPercent))
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        dealerRecord.commissionToDate = dealerRecord.commissionToDate.add(currentCommission);
        totalCommission = totalCommission.add(currentCommission);

        workDate.mm = transRecord.transactionDate.substring(4, 6);
        workDate.dd = transRecord.transactionDate.substring(6, 8);
        workDate.yyyy = transRecord.transactionDate.substring(0, 4);

        reverseDate.yyyy = workDate.yyyy;
        reverseDate.mm = workDate.mm;
        reverseDate.dd = workDate.dd;
        compareDate1 = reverseDate.yyyy + reverseDate.mm + reverseDate.dd;

        workDate.mm = dealerRecord.lastSoldDate.substring(4, 6);
        workDate.dd = dealerRecord.lastSoldDate.substring(6, 8);
        workDate.yyyy = dealerRecord.lastSoldDate.substring(0, 4);

        reverseDate.yyyy = workDate.yyyy;
        reverseDate.mm = workDate.mm;
        reverseDate.dd = workDate.dd;
        compareDate2 = reverseDate.yyyy + reverseDate.mm + reverseDate.dd;

        if (compareDate1.compareTo(compareDate2) > 0) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }

    static void writeDealerOut() throws IOException {
        masterRecordsWritten++;
        dealerOutWriter.write(serializeDealerRecord(dealerRecord));
        dealerOutWriter.newLine();
    }

    static void createNewMaster() {
        creatingNewMaster = true;
        holdRecord = serializeDealerRecord(dealerRecord);
        dealerRecord = new DealerRecord();
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        applyTransaction();
    }

    static void readDealer() throws IOException {
        if (creatingNewMaster) {
            dealerRecord = deserializeDealerRecord(holdRecord);
            creatingNewMaster = false;
        } else {
            String line = dealerFileReader.readLine();
            if (line == null) {
                dealerRecord = new DealerRecord();
                dealerRecord.dealerNumber = "~";
            } else {
                dealerRecord = deserializeDealerRecord(line);
            }
        }
    }

    static void readTrans() throws IOException {
        String line = transFileReader.readLine();
        if (line == null) {
            transRecord = new TransRecord();
            transRecord.transactionDealer = "~";
        } else {
            transRecord = deserializeTransRecord(line);
            transactionsRead++;
        }
    }

    static String serializeDealerRecord(DealerRecord dr) {
        StringBuilder sb = new StringBuilder();
        sb.append(padRight(dr.dealerNumber, 8));
        sb.append(padRight(dr.lastName, 25));
        sb.append(padRight(dr.firstName, 15));
        sb.append(padRight(dr.middleName, 10));
        sb.append(padRight(dr.addressLine1, 50));
        sb.append(padRight(dr.addressLine2, 50));
        sb.append(padRight(dr.city, 40));
        sb.append(padRight(dr.stateOrCountry, 20));
        sb.append(padRight(dr.postalCode, 15));
        sb.append(padRight(dr.homePhone, 20));
        sb.append(padRight(dr.workPhone, 20));