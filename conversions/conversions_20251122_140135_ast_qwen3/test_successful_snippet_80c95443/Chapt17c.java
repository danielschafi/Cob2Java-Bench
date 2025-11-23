import java.io.*;
import java.util.*;

public class Chapt17c {
    static class DealerRecord {
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
        String rentAmount;
        String consignmentPercent;
        String lastSoldAmount;
        String lastSoldDate;
        String soldToDate;
        String commissionToDate;
        String filler;

        public DealerRecord() {
            dealerNumber = "";
            lastName = "";
            firstName = "";
            middleName = "";
            addressLine1 = "";
            addressLine2 = "";
            city = "";
            stateOrCountry = "";
            postalCode = "";
            homePhone = "";
            workPhone = "";
            otherPhone = "";
            startDate = "";
            lastRentPaidDate = "";
            nextRentDueDate = "";
            rentAmount = "";
            consignmentPercent = "";
            lastSoldAmount = "";
            lastSoldDate = "";
            soldToDate = "";
            commissionToDate = "";
            filler = "";
        }
    }

    public static void main(String[] args) {
        List<DealerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Dealer.TXT"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 179) {
                    DealerRecord record = new DealerRecord();
                    record.dealerNumber = line.substring(0, 8).trim();
                    record.lastName = line.substring(8, 33).trim();
                    record.firstName = line.substring(33, 48).trim();
                    record.middleName = line.substring(48, 58).trim();
                    record.addressLine1 = line.substring(58, 108).trim();
                    record.addressLine2 = line.substring(108, 158).trim();
                    record.city = line.substring(158, 198).trim();
                    record.stateOrCountry = line.substring(198, 218).trim();
                    record.postalCode = line.substring(218, 233).trim();
                    record.homePhone = line.substring(233, 253).trim();
                    record.workPhone = line.substring(253, 273).trim();
                    record.otherPhone = line.substring(273, 293).trim();
                    record.startDate = line.substring(293, 301).trim();
                    record.lastRentPaidDate = line.substring(301, 309).trim();
                    record.nextRentDueDate = line.substring(309, 317).trim();
                    record.rentAmount = line.substring(317, 322).trim();
                    record.consignmentPercent = line.substring(322, 325).trim();
                    record.lastSoldAmount = line.substring(325, 333).trim();
                    record.lastSoldDate = line.substring(333, 341).trim();
                    record.soldToDate = line.substring(341, 349).trim();
                    record.commissionToDate = line.substring(349, 357).trim();
                    record.filler = line.substring(357, 372).trim();
                    records.add(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        records.sort((r1, r2) -> {
            int stateCompare = r2.stateOrCountry.compareTo(r1.stateOrCountry); // Descending order
            if (stateCompare != 0) return stateCompare;
            
            int lastNameCompare = r1.lastName.compareTo(r2.lastName);
            if (lastNameCompare != 0) return lastNameCompare;
            
            int firstNameCompare = r1.firstName.compareTo(r2.firstName);
            if (firstNameCompare != 0) return firstNameCompare;
            
            return r1.middleName.compareTo(r2.middleName);
        });

        try (PrintWriter writer = new PrintWriter(new FileWriter("Dealer.TXT"))) {
            for (DealerRecord record : records) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-8s", record.dealerNumber));
                sb.append(String.format("%-25s", record.lastName));
                sb.append(String.format("%-15s", record.firstName));
                sb.append(String.format("%-10s", record.middleName));
                sb.append(String.format("%-50s", record.addressLine1));
                sb.append(String.format("%-50s", record.addressLine2));
                sb.append(String.format("%-40s", record.city));
                sb.append(String.format("%-20s", record.stateOrCountry));
                sb.append(String.format("%-15s", record.postalCode));
                sb.append(String.format("%-20s", record.homePhone));
                sb.append(String.format("%-20s", record.workPhone));
                sb.append(String.format("%-20s", record.otherPhone));
                sb.append(String.format("%-8s", record.startDate));
                sb.append(String.format("%-8s", record.lastRentPaidDate));
                sb.append(String.format("%-8s", record.nextRentDueDate));
                sb.append(String.format("%-5s", record.rentAmount));
                sb.append(String.format("%-3s", record.consignmentPercent));
                sb.append(String.format("%-8s", record.lastSoldAmount));
                sb.append(String.format("%-8s", record.lastSoldDate));
                sb.append(String.format("%-8s", record.soldToDate));
                sb.append(String.format("%-8s", record.commissionToDate));
                sb.append(String.format("%-15s", record.filler));
                writer.println(sb.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return;
        }

        System.out.println("Sort Complete");
    }
}