import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chapt18x {
    private static final String TRANS_FILE = "Trans.Seq";
    private static final String DEALER_FILE = "Dealer.Seq";
    private static final String DEALER_OUT = "Dealer.Out";

    private static class TransRecord {
        String transactionDate;
        String transactionType;
        String transactionDealer;
        double transactionPrice;
        int transactionQty;
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
        String startDate;
        String lastRentPaidDate;
        String nextRentDueDate;
        double rentAmount;
        int consignmentPercent;
        double lastSoldAmount;
        String lastSoldDate;
        double soldToDate;
        double commissionToDate;
    }

    private static TransRecord transRecord = new TransRecord();
    private static DealerRecord dealerRecord = new DealerRecord();
    private static DealerRecord holdRecord = new DealerRecord();
    private static boolean creatingNewMaster = false;

    private static double currentCommission = 0.0;
    private static double totalCommission = 0.0;
    private static int transactionsRead = 0;
    private static int masterRecordsWritten = 0;

    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18x");

        try (BufferedReader transReader = Files.newBufferedReader(Paths.get(TRANS_FILE));
             BufferedReader dealerReader = Files.newBufferedReader(Paths.get(DEALER_FILE));
             BufferedWriter dealerOutWriter = Files.newBufferedWriter(Paths.get(DEALER_OUT))) {

            readDealer(dealerReader);
            readTrans(transReader);

            while (!(transRecord.transactionDate == null && dealerRecord.dealerNumber == null)) {
                processFiles(dealerOutWriter, transReader, dealerReader);
            }

            dealerOutWriter.close();
            transReader.close();
            dealerReader.close();

            System.out.println("Processing Complete");
            System.out.println("Transactions Read " + transactionsRead);
            System.out.println("Master Records Written " + masterRecordsWritten);
            System.out.printf("Total Commission  %.2f%n", totalCommission);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFiles(BufferedWriter dealerOutWriter, BufferedReader transReader, BufferedReader dealerReader) throws IOException {
        if (dealerRecord.dealerNumber == null || dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) < 0) {
            writeDealerOut(dealerOutWriter);
            readDealer(dealerReader);
        } else if (dealerRecord.dealerNumber.compareTo(transRecord.transactionDealer) > 0) {
            createNewMaster();
            readTrans(transReader);
        } else {
            applyTransaction();
            readTrans(transReader);
        }
    }

    private static void applyTransaction() {
        dealerRecord.soldToDate += transRecord.transactionQty * transRecord.transactionPrice;
        currentCommission = Math.round((transRecord.transactionQty * transRecord.transactionPrice) * (dealerRecord.consignmentPercent / 100.0) * 100.0) / 100.0;
        dealerRecord.commissionToDate += currentCommission;
        totalCommission += currentCommission;

        if (compareDates(transRecord.transactionDate, dealerRecord.lastSoldDate) > 0) {
            dealerRecord.lastSoldDate = transRecord.transactionDate;
        }
    }

    private static void writeDealerOut(BufferedWriter dealerOutWriter) throws IOException {
        masterRecordsWritten++;
        dealerOutWriter.write(String.format("%-8s%-25s%-15s%-10s%-50s%-50s%-40s%-20s%-15s%-20s%-20s%-20s%-8s%-8s%-8s%07.2f%03d%07.2f%07.2f%07.2f%07.2f%-15s",
                dealerRecord.dealerNumber, dealerRecord.lastName, dealerRecord.firstName, dealerRecord.middleName,
                dealerRecord.addressLine1, dealerRecord.addressLine2, dealerRecord.city, dealerRecord.stateOrCountry,
                dealerRecord.postalCode, dealerRecord.homePhone, dealerRecord.workPhone, dealerRecord.otherPhone,
                dealerRecord.startDate, dealerRecord.lastRentPaidDate, dealerRecord.nextRentDueDate, dealerRecord.rentAmount,
                dealerRecord.consignmentPercent, dealerRecord.lastSoldAmount, dealerRecord.lastSoldDate,
                dealerRecord.soldToDate, dealerRecord.commissionToDate, " "));
        dealerOutWriter.newLine();
    }

    private static void createNewMaster() {
        creatingNewMaster = true;
        holdRecord = dealerRecord;
        dealerRecord = new DealerRecord();
        dealerRecord.dealerNumber = transRecord.transactionDealer;
        dealerRecord.lastName = transRecord.transactionDealer;
        dealerRecord.consignmentPercent = 10;
        applyTransaction();
    }

    private static void readDealer(BufferedReader dealerReader) throws IOException {
        if (creatingNewMaster) {
            dealerRecord = holdRecord;
            creatingNewMaster = false;
        } else {
            String line = dealerReader.readLine();
            if (line == null) {
                dealerRecord = null;
            } else {
                dealerRecord.dealerNumber = line.substring(0, 8).trim();
                dealerRecord.lastName = line.substring(8, 33).trim();
                dealerRecord.firstName = line.substring(33, 48).trim();
                dealerRecord.middleName = line.substring(48, 58).trim();
                dealerRecord.addressLine1 = line.substring(58, 108).trim();
                dealerRecord.addressLine2 = line.substring(108, 158).trim();
                dealerRecord.city = line.substring(158, 198).trim();
                dealerRecord.stateOrCountry = line.substring(198, 218).trim();
                dealerRecord.postalCode = line.substring(218, 233).trim();
                dealerRecord.homePhone = line.substring(233, 253).trim();
                dealerRecord.workPhone = line.substring(253, 273).trim();
                dealerRecord.otherPhone = line.substring(273, 293).trim();
                dealerRecord.startDate = line.substring(293, 301).trim();
                dealerRecord.lastRentPaidDate = line.substring(301, 309).trim();
                dealerRecord.nextRentDueDate = line.substring(309, 317).trim();
                dealerRecord.rentAmount = Double.parseDouble(line.substring(317, 322) + "." + line.substring(322, 324));
                dealerRecord.consignmentPercent = Integer.parseInt(line.substring(324, 327).trim());
                dealerRecord.lastSoldAmount = Double.parseDouble(line.substring(327, 334) + "." + line.substring(334, 336));
                dealerRecord.lastSoldDate = line.substring(336, 344).trim();
                dealerRecord.soldToDate = Double.parseDouble(line.substring(344, 351) + "." + line.substring(351, 353));
                dealerRecord.commissionToDate = Double.parseDouble(line.substring(353, 360) + "." + line.substring(360, 362));
            }
        }
    }

    private static void readTrans(BufferedReader transReader) throws IOException {
        String line = transReader.readLine();
        if (line == null) {
            transRecord.transactionDate = null;
        } else {
            transactionsRead++;
            transRecord.transactionDate = line.substring(0, 8).trim();
            transRecord.transactionType = line.substring(8, 12).trim();
            transRecord.transactionDealer = line.substring(12, 20).trim();
            transRecord.transactionPrice = Double.parseDouble(line.substring(20, 27) + "." + line.substring(27, 29));
            transRecord.transactionQty = Integer.parseInt(line.substring(29, 32).trim());
        }
    }

    private static int compareDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.compareTo(d2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}