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
        
        static DealerRecord parseFromLine(String line) {
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
            
            return record;
        }
        
        private static String extractField(String line, int start, int length) {
            if (start >= line.length()) {
                return "";
            }
            int end = Math.min(start + length, line.length());
            return line.substring(start, end);
        }
        
        String toLine() {
            StringBuilder sb = new StringBuilder();
            sb.append(padRight(dealerNumber, 8));
            sb.append(padRight(lastName, 25));
            sb.append(padRight(firstName, 15));
            sb.append(padRight(middleName, 10));
            sb.append(padRight(addressLine1, 50));
            sb.append(padRight(addressLine2, 50));
            sb.append(padRight(city, 40));
            sb.append(padRight(stateOrCountry, 20));
            sb.append(padRight(postalCode, 15));
            sb.append(padRight(homePhone, 20));
            sb.append(padRight(workPhone, 20));
            sb.append(padRight(otherPhone, 20));
            sb.append(padRight(startDate, 8));
            sb.append(padRight(lastRentPaidDate, 8));
            sb.append(padRight(nextRentDueDate, 8));
            sb.append(padRight(rentAmount, 6));
            sb.append(padRight(consignmentPercent, 3));
            sb.append(padRight(lastSoldAmount, 9));
            sb.append(padRight(lastSoldDate, 8));
            sb.append(padRight(soldToDate, 9));
            sb.append(padRight(commissionToDate, 9));
            sb.append(padRight(filler, 15));
            return sb.toString();
        }
        
        private static String padRight(String str, int length) {
            if (str.length() >= length) {
                return str.substring(0, length);
            }
            StringBuilder sb = new StringBuilder(str);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        try {
            List<DealerRecord> records = new ArrayList<>();
            
            File inputFile = new File("Dealer.TXT");
            if (inputFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    records.add(DealerRecord.parseFromLine(line));
                }
                reader.close();
            }
            
            records.sort((r1, r2) -> {
                int cmp = r2.stateOrCountry.compareTo(r1.stateOrCountry);
                if (cmp != 0) return cmp;
                
                cmp = r1.lastName.compareTo(r2.lastName);
                if (cmp != 0) return cmp;
                
                cmp = r1.firstName.compareTo(r2.firstName);
                if (cmp != 0) return cmp;
                
                return r1.middleName.compareTo(r2.middleName);
            });
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            for (DealerRecord record : records) {
                writer.write(record.toLine());
                writer.newLine();
            }
            writer.close();
            
            System.out.println("Sort Complete");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}