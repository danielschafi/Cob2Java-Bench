import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Chapt17c {
    
    static class DealerRecord implements Comparable<DealerRecord> {
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
        
        public static DealerRecord fromLine(String line) {
            DealerRecord record = new DealerRecord();
            int pos = 0;
            
            record.dealerNumber = safeSubstring(line, pos, pos + 8); pos += 8;
            record.lastName = safeSubstring(line, pos, pos + 25); pos += 25;
            record.firstName = safeSubstring(line, pos, pos + 15); pos += 15;
            record.middleName = safeSubstring(line, pos, pos + 10); pos += 10;
            record.addressLine1 = safeSubstring(line, pos, pos + 50); pos += 50;
            record.addressLine2 = safeSubstring(line, pos, pos + 50); pos += 50;
            record.city = safeSubstring(line, pos, pos + 40); pos += 40;
            record.stateOrCountry = safeSubstring(line, pos, pos + 20); pos += 20;
            record.postalCode = safeSubstring(line, pos, pos + 15); pos += 15;
            record.homePhone = safeSubstring(line, pos, pos + 20); pos += 20;
            record.workPhone = safeSubstring(line, pos, pos + 20); pos += 20;
            record.otherPhone = safeSubstring(line, pos, pos + 20); pos += 20;
            record.startDate = safeSubstring(line, pos, pos + 8); pos += 8;
            record.lastRentPaidDate = safeSubstring(line, pos, pos + 8); pos += 8;
            record.nextRentDueDate = safeSubstring(line, pos, pos + 8); pos += 8;
            record.rentAmount = safeSubstring(line, pos, pos + 6); pos += 6;
            record.consignmentPercent = safeSubstring(line, pos, pos + 3); pos += 3;
            record.lastSoldAmount = safeSubstring(line, pos, pos + 9); pos += 9;
            record.lastSoldDate = safeSubstring(line, pos, pos + 8); pos += 8;
            record.soldToDate = safeSubstring(line, pos, pos + 9); pos += 9;
            record.commissionToDate = safeSubstring(line, pos, pos + 9); pos += 9;
            record.filler = safeSubstring(line, pos, pos + 15);
            
            return record;
        }
        
        private static String safeSubstring(String str, int start, int end) {
            if (start >= str.length()) {
                return padRight("", end - start);
            }
            if (end > str.length()) {
                return padRight(str.substring(start), end - start);
            }
            return str.substring(start, end);
        }
        
        private static String padRight(String str, int length) {
            if (str.length() >= length) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            while (sb.length() < length) {
                sb.append(' ');
            }
            return sb.toString();
        }
        
        public String toLine() {
            return dealerNumber + lastName + firstName + middleName +
                   addressLine1 + addressLine2 + city + stateOrCountry +
                   postalCode + homePhone + workPhone + otherPhone +
                   startDate + lastRentPaidDate + nextRentDueDate +
                   rentAmount + consignmentPercent + lastSoldAmount +
                   lastSoldDate + soldToDate + commissionToDate + filler;
        }
        
        @Override
        public int compareTo(DealerRecord other) {
            int stateCompare = other.stateOrCountry.compareTo(this.stateOrCountry);
            if (stateCompare != 0) {
                return stateCompare;
            }
            
            int lastNameCompare = this.lastName.compareTo(other.lastName);
            if (lastNameCompare != 0) {
                return lastNameCompare;
            }
            
            int firstNameCompare = this.firstName.compareTo(other.firstName);
            if (firstNameCompare != 0) {
                return firstNameCompare;
            }
            
            return this.middleName.compareTo(other.middleName);
        }
    }
    
    public static void main(String[] args) {
        try {
            List<DealerRecord> records = new ArrayList<>();
            
            Path inputPath = Paths.get("Dealer.TXT");
            if (Files.exists(inputPath)) {
                List<String> lines = Files.readAllLines(inputPath);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        records.add(DealerRecord.fromLine(line));
                    }
                }
            }
            
            Collections.sort(records);
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("Dealer.TXT"));
            for (DealerRecord record : records) {
                writer.write(record.toLine());
                writer.newLine();
            }
            writer.close();
            
            System.out.println("Sort Complete");
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}