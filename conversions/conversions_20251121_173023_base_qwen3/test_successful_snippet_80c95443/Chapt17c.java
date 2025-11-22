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
            this.dealerNumber = "";
            this.lastName = "";
            this.firstName = "";
            this.middleName = "";
            this.addressLine1 = "";
            this.addressLine2 = "";
            this.city = "";
            this.stateOrCountry = "";
            this.postalCode = "";
            this.homePhone = "";
            this.workPhone = "";
            this.otherPhone = "";
            this.startDate = 0;
            this.lastRentPaidDate = 0;
            this.nextRentDueDate = 0;
            this.rentAmount = 0.0;
            this.consignmentPercent = 0;
            this.lastSoldAmount = 0.0;
            this.lastSoldDate = 0;
            this.soldToDate = 0.0;
            this.commissionToDate = 0.0;
            this.filler = "";
        }

        public DealerRecord(String line) {
            this();
            if (line.length() >= 150) {
                this.dealerNumber = line.substring(0, 8).trim();
                this.lastName = line.substring(8, 33).trim();
                this.firstName = line.substring(33, 48).trim();
                this.middleName = line.substring(48, 58).trim();
                this.addressLine1 = line.substring(58, 108).trim();
                this.addressLine2 = line.substring(108, 158).trim();
                this.city = line.substring(158, 198).trim();
                this.stateOrCountry = line.substring(198, 218).trim();
                this.postalCode = line.substring(218, 233).trim();
                this.homePhone = line.substring(233, 253).trim();
                this.workPhone = line.substring(253, 273).trim();
                this.otherPhone = line.substring(273, 293).trim();
                try {
                    this.startDate = Integer.parseInt(line.substring(293, 301).trim());
                } catch (NumberFormatException e) {
                    this.startDate = 0;
                }
                try {
                    this.lastRentPaidDate = Integer.parseInt(line.substring(301, 309).trim());
                } catch (NumberFormatException e) {
                    this.lastRentPaidDate = 0;
                }
                try {
                    this.nextRentDueDate = Integer.parseInt(line.substring(309, 317).trim());
                } catch (NumberFormatException e) {
                    this.nextRentDueDate = 0;
                }
                try {
                    this.rentAmount = Double.parseDouble(line.substring(317, 321).trim()) / 100.0;
                } catch (NumberFormatException e) {
                    this.rentAmount = 0.0;
                }
                try {
                    this.consignmentPercent = Integer.parseInt(line.substring(321, 324).trim());
                } catch (NumberFormatException e) {
                    this.consignmentPercent = 0;
                }
                try {
                    this.lastSoldAmount = Double.parseDouble(line.substring(324, 331).trim()) / 100.0;
                } catch (NumberFormatException e) {
                    this.lastSoldAmount = 0.0;
                }
                try {
                    this.lastSoldDate = Integer.parseInt(line.substring(331, 339).trim());
                } catch (NumberFormatException e) {
                    this.lastSoldDate = 0;
                }
                try {
                    this.soldToDate = Double.parseDouble(line.substring(339, 346).trim()) / 100.0;
                } catch (NumberFormatException e) {
                    this.soldToDate = 0.0;
                }
                try {
                    this.commissionToDate = Double.parseDouble(line.substring(346, 353).trim()) / 100.0;
                } catch (NumberFormatException e) {
                    this.commissionToDate = 0.0;
                }
                this.filler = line.substring(353, 368).trim();
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-8s", dealerNumber));
            sb.append(String.format("%-25s", lastName));
            sb.append(String.format("%-15s", firstName));
            sb.append(String.format("%-10s", middleName));
            sb.append(String.format("%-50s", addressLine1));
            sb.append(String.format("%-50s", addressLine2));
            sb.append(String.format("%-40s", city));
            sb.append(String.format("%-20s", stateOrCountry));
            sb.append(String.format("%-15s", postalCode));
            sb.append(String.format("%-20s", homePhone));
            sb.append(String.format("%-20s", workPhone));
            sb.append(String.format("%-20s", otherPhone));
            sb.append(String.format("%08d", startDate));
            sb.append(String.format("%08d", lastRentPaidDate));
            sb.append(String.format("%08d", nextRentDueDate));
            sb.append(String.format("%04.2f", rentAmount));
            sb.append(String.format("%03d", consignmentPercent));
            sb.append(String.format("%07.2f", lastSoldAmount));
            sb.append(String.format("%08d", lastSoldDate));
            sb.append(String.format("%07.2f", soldToDate));
            sb.append(String.format("%07.2f", commissionToDate));
            sb.append(String.format("%-15s", filler));
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        List<DealerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Dealer.TXT"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(new DealerRecord(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading Dealer.TXT: " + e.getMessage());
            return;
        }

        records.sort((r1, r2) -> {
            // Sort by State-Or-Country descending
            int stateCompare = r2.stateOrCountry.compareTo(r1.stateOrCountry);
            if (stateCompare != 0) return stateCompare;
            
            // Then by Last-Name ascending
            int lastNameCompare = r1.lastName.compareTo(r2.lastName);
            if (lastNameCompare != 0) return lastNameCompare;
            
            // Then by First-Name ascending
            int firstNameCompare = r1.firstName.compareTo(r2.firstName);
            if (firstNameCompare != 0) return firstNameCompare;
            
            // Finally by Middle-Name ascending
            return r1.middleName.compareTo(r2.middleName);
        });

        try (PrintWriter writer = new PrintWriter(new FileWriter("Dealer.TXT"))) {
            for (DealerRecord record : records) {
                writer.println(record.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to Dealer.TXT: " + e.getMessage());
            return;
        }

        System.out.println("Sort Complete");
    }
}