import java.io.*;
import java.nio.file.*;
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
        
        DealerRecord() {
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
        try {
            List<DealerRecord> records = readDealerFile("Dealer.TXT");
            sortRecords(records);
            writeDealerFile("Dealer.TXT", records);
            System.out.println("Sort Complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static List<DealerRecord> readDealerFile(String filename) throws IOException {
        List<DealerRecord> records = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            DealerRecord record = new DealerRecord();
            int pos = 0;
            
            record.dealerNumber = extractField(line, pos, 8);
            pos += 8;
            record.lastName = extractField(line, pos, 25);
            pos += 25;
            record.firstName = extractField(line, pos, 15);
            pos += 15;
            record.middleName = extractField(line, pos, 10);
            pos += 10;
            record.addressLine1 = extractField(line, pos, 50);
            pos += 50;
            record.addressLine2 = extractField(line, pos, 50);
            pos += 50;
            record.city = extractField(line, pos, 40);
            pos += 40;
            record.stateOrCountry = extractField(line, pos, 20);
            pos += 20;
            record.postalCode = extractField(line, pos, 15);
            pos += 15;
            record.homePhone = extractField(line, pos, 20);
            pos += 20;
            record.workPhone = extractField(line, pos, 20);
            pos += 20;
            record.otherPhone = extractField(line, pos, 20);
            pos += 20;
            record.startDate = extractField(line, pos, 8);
            pos += 8;
            record.lastRentPaidDate = extractField(line, pos, 8);
            pos += 8;
            record.nextRentDueDate = extractField(line, pos, 8);
            pos += 8;
            record.rentAmount = extractField(line, pos, 6);
            pos += 6;
            record.consignmentPercent = extractField(line, pos, 3);
            pos += 3;
            record.lastSoldAmount = extractField(line, pos, 9);
            pos += 9;
            record.lastSoldDate = extractField(line, pos, 8);
            pos += 8;
            record.soldToDate = extractField(line, pos, 9);
            pos += 9;
            record.commissionToDate = extractField(line, pos, 9);
            pos += 9;
            record.filler = extractField(line, pos, 15);
            
            records.add(record);
        }
        
        return records;
    }
    
    static String extractField(String line, int start, int length) {
        if (start >= line.length()) {
            return "";
        }
        int end = Math.min(start + length, line.length());
        return line.substring(start, end);
    }
    
    static void sortRecords(List<DealerRecord> records) {
        records.sort((r1, r2) -> {
            int cmp = r2.stateOrCountry.compareTo(r1.stateOrCountry);
            if (cmp != 0) return cmp;
            
            cmp = r1.lastName.compareTo(r2.lastName);
            if (cmp != 0) return cmp;
            
            cmp = r1.firstName.compareTo(r2.firstName);
            if (cmp != 0) return cmp;
            
            return r1.middleName.compareTo(r2.middleName);
        });
    }
    
    static void writeDealerFile(String filename, List<DealerRecord> records) throws IOException {
        List<String> lines = new ArrayList<>();
        
        for (DealerRecord record : records) {
            StringBuilder sb = new StringBuilder();
            sb.append(padRight(record.dealerNumber, 8));
            sb.append(padRight(record.lastName, 25));
            sb.append(padRight(record.firstName, 15));
            sb.append(padRight(record.middleName, 10));
            sb.append(padRight(record.addressLine1, 50));
            sb.append(padRight(record.addressLine2, 50));
            sb.append(padRight(record.city, 40));
            sb.append(padRight(record.stateOrCountry, 20));
            sb.append(padRight(record.postalCode, 15));
            sb.append(padRight(record.homePhone, 20));
            sb.append(padRight(record.workPhone, 20));
            sb.append(padRight(record.otherPhone, 20));
            sb.append(padRight(record.startDate, 8));
            sb.append(padRight(record.lastRentPaidDate, 8));
            sb.append(padRight(record.nextRentDueDate, 8));
            sb.append(padRight(record.rentAmount, 6));
            sb.append(padRight(record.consignmentPercent, 3));
            sb.append(padRight(record.lastSoldAmount, 9));
            sb.append(padRight(record.lastSoldDate, 8));
            sb.append(padRight(record.soldToDate, 9));
            sb.append(padRight(record.commissionToDate, 9));
            sb.append(padRight(record.filler, 15));
            lines.add(sb.toString());
        }
        
        Files.write(Paths.get(filename), lines);
    }
    
    static String padRight(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        return String.format("%-" + length + "s", s);
    }
}