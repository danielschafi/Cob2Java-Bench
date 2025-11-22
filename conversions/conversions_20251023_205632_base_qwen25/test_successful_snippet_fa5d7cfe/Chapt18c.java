import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chapt18c {
    private static final String TRANS_FILE = "Trans.Seq";
    private static final String DEALER_FILE = "Dealer.Seq";
    private static final String DEALER_OUT = "Dealer.Out";
    private static final String REJECT_FILE = "Reject.Txt";

    private static String transactionDate;
    private static String transactionType;
    private static String transactionDealer;
    private static double transactionPrice;
    private static int transactionQty;
    private static String dealerNumber;
    private static String lastName;
    private static String firstName;
    private static String middleName;
    private static String addressLine1;
    private static String addressLine2;
    private static String city;
    private static String stateOrCountry;
    private static String postalCode;
    private static String homePhone;
    private static String workPhone;
    private static String otherPhone;
    private static String startDate;
    private static String lastRentPaidDate;
    private static String nextRentDueDate;
    private static double rentAmount;
    private static int consignmentPercent;
    private static double lastSoldAmount;
    private static String lastSoldDate;
    private static double soldToDate;
    private static double commissionToDate;
    private static double currentCommission;
    private static double totalCommission;
    private static int transactionsRead;
    private static int transactionsRejected;
    private static String workDate;
    private static String reverseDate;
    private static String compareDate1;
    private static String compareDate2;

    public static void main(String[] args) {
        System.out.println("Begin Process Chapt18c");

        try (BufferedReader transReader = Files.newBufferedReader(Paths.get(TRANS_FILE));
             BufferedReader dealerReader = Files.newBufferedReader(Paths.get(DEALER_FILE));
             BufferedWriter dealerOutWriter = Files.newBufferedWriter(Paths.get(DEALER_OUT));
             BufferedWriter rejectWriter = Files.newBufferedWriter(Paths.get(REJECT_FILE))) {

            readDealer(dealerReader);
            readTrans(transReader);

            while (!(transactionDate == null && dealerNumber == null)) {
                processFiles(dealerOutWriter, rejectWriter, transReader, dealerReader);
            }

            System.out.println("Processing Complete");
            System.out.println("Transactions Read " + transactionsRead);
            System.out.println("Transactions Rejected " + transactionsRejected);
            System.out.printf("Total Commission  %,.2f%n", totalCommission);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFiles(BufferedWriter dealerOutWriter, BufferedWriter rejectWriter, BufferedReader transReader, BufferedReader dealerReader) throws IOException {
        if (dealerNumber == null || dealerNumber.compareTo(transactionDealer) < 0) {
            writeDealerOut(dealerOutWriter);
            readDealer(dealerReader);
        } else if (dealerNumber.compareTo(transactionDealer) > 0) {
            writeReject(rejectWriter);
            readTrans(transReader);
        } else {
            applyTransaction();
            readTrans(transReader);
        }
    }

    private static void applyTransaction() {
        soldToDate += transactionQty * transactionPrice;
        currentCommission = Math.round((transactionQty * transactionPrice) * (consignmentPercent / 100.0) * 100.0) / 100.0;
        commissionToDate += currentCommission;
        totalCommission += currentCommission;

        if (transactionDate.compareTo(lastSoldDate) > 0) {
            lastSoldDate = transactionDate;
        }
    }

    private static void writeDealerOut(BufferedWriter dealerOutWriter) throws IOException {
        dealerOutWriter.write(String.format("%-8s%-25s%-15s%-10s%-50s%-50s%-40s%-20s%-15s%-20s%-20s%-20s%-8s%-8s%-8s%07.2f%03d%07.2f%07.2f%07.2f%15s",
                dealerNumber, lastName, firstName, middleName, addressLine1, addressLine2, city, stateOrCountry, postalCode, homePhone, workPhone, otherPhone, startDate, lastRentPaidDate, nextRentDueDate, rentAmount, consignmentPercent, lastSoldAmount, lastSoldDate, soldToDate, commissionToDate, ""));
        dealerOutWriter.newLine();
    }

    private static void writeReject(BufferedWriter rejectWriter) throws IOException {
        transactionsRejected++;
        rejectWriter.write(String.format("%-8s%-4s%-8s%07.2f%03d%40s", transactionDate, transactionType, transactionDealer, transactionPrice, transactionQty, ""));
        rejectWriter.newLine();
    }

    private static void readDealer(BufferedReader dealerReader) throws IOException {
        String line = dealerReader.readLine();
        if (line == null) {
            dealerNumber = null;
        } else {
            dealerNumber = line.substring(0, 8).trim();
            lastName = line.substring(8, 33).trim();
            firstName = line.substring(33, 48).trim();
            middleName = line.substring(48, 58).trim();
            addressLine1 = line.substring(58, 108).trim();
            addressLine2 = line.substring(108, 158).trim();
            city = line.substring(158, 198).trim();
            stateOrCountry = line.substring(198, 218).trim();
            postalCode = line.substring(218, 233).trim();
            homePhone = line.substring(233, 253).trim();
            workPhone = line.substring(253, 273).trim();
            otherPhone = line.substring(273, 293).trim();
            startDate = line.substring(293, 301).trim();
            lastRentPaidDate = line.substring(301, 309).trim();
            nextRentDueDate = line.substring(309, 317).trim();
            rentAmount = Double.parseDouble(line.substring(317, 322) + "." + line.substring(322, 324));
            consignmentPercent = Integer.parseInt(line.substring(324, 327).trim());
            lastSoldAmount = Double.parseDouble(line.substring(327, 334) + "." + line.substring(334, 336));
            lastSoldDate = line.substring(336, 344).trim();
            soldToDate = Double.parseDouble(line.substring(344, 351) + "." + line.substring(351, 353));
            commissionToDate = Double.parseDouble(line.substring(353, 360) + "." + line.substring(360, 362));
        }
    }

    private static void readTrans(BufferedReader transReader) throws IOException {
        String line = transReader.readLine();
        if (line == null) {
            transactionDate = null;
        } else {
            transactionsRead++;
            transactionDate = line.substring(0, 8).trim();
            transactionType = line.substring(8, 12).trim();
            transactionDealer = line.substring(12, 20).trim();
            transactionPrice = Double.parseDouble(line.substring(20, 27) + "." + line.substring(27, 29));
            transactionQty = Integer.parseInt(line.substring(29, 32).trim());
        }
    }
}