import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MergeSortExample {
    
    static class CustomerRecord {
        int customerId;
        String customerLastName;
        String customerFirstName;
        int customerContractId;
        String customerComment;
        
        CustomerRecord() {
            this.customerId = 0;
            this.customerLastName = "";
            this.customerFirstName = "";
            this.customerContractId = 0;
            this.customerComment = "";
        }
        
        CustomerRecord(int customerId, String lastN, String firstN, int contractId, String comment) {
            this.customerId = customerId;
            this.customerLastName = lastN;
            this.customerFirstName = firstN;
            this.customerContractId = contractId;
            this.customerComment = comment;
        }
        
        @Override
        public String toString() {
            return String.format("%05d%-50s%-50s%05d%-25s", 
                customerId, customerLastName, customerFirstName, customerContractId, customerComment);
        }
        
        static CustomerRecord fromString(String line) {
            if (line == null || line.length() < 130) return null;
            try {
                int id = Integer.parseInt(line.substring(0, 5).trim());
                String lastName = line.substring(5, 55).trim();
                String firstName = line.substring(55, 105).trim();
                int contractId = Integer.parseInt(line.substring(105, 110).trim());
                String comment = line.substring(110, 135).trim();
                return new CustomerRecord(id, lastName, firstName, contractId, comment);
            } catch (Exception e) {
                return null;
            }
        }
    }
    
    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }
    
    static void createTestData() {
        System.out.println("Creating test data files...");
        
        List<CustomerRecord> file1Records = new ArrayList<>();
        file1Records.add(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"));
        file1Records.add(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"));
        file1Records.add(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"));
        file1Records.add(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"));
        file1Records.add(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"));
        file1Records.add(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75"));
        
        writeRecordsToFile("test-file-1.txt", file1Records);
        
        List<CustomerRecord> file2Records = new ArrayList<>();
        file2Records.add(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"));
        file2Records.add(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"));
        file2Records.add(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"));
        file2Records.add(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"));
        file2Records.add(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24"));
        
        writeRecordsToFile("test-file-2.txt", file2Records);
    }
    
    static void writeRecordsToFile(String filename, List<CustomerRecord> records) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.println(record.toString());
            }
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + filename);
            System.exit(1);
        }
    }
    
    static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");
        
        List<CustomerRecord> mergedRecords = new ArrayList<>();
        
        try {
            List<String> file1Lines = Files.readAllLines(Paths.get("test-file-1.txt"));
            List<String> file2Lines = Files.readAllLines(Paths.get("test-file-2.txt"));
            
            for (String line : file1Lines) {
                CustomerRecord record = CustomerRecord.fromString(line);
                if (record != null) mergedRecords.add(record);
            }
            for (String line : file2Lines) {
                CustomerRecord record = CustomerRecord.fromString(line);
                if (record != null) mergedRecords.add(record);
            }
            
            mergedRecords.sort((a, b) -> Integer.compare(a.customerId, b.customerId));
            
            writeRecordsToFile("merge-output.txt", mergedRecords);
            
            for (CustomerRecord record : mergedRecords) {
                System.out.println(record.toString());
            }
        } catch (IOException e) {
            System.out.println("Error opening merged output file");
            System.exit(1);
        }
    }
    
    static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");
        
        try {
            List<CustomerRecord> records = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("merge-output.txt"));
            
            for (String line : lines) {
                CustomerRecord record = CustomerRecord.fromString(line);
                if (record != null) records.add(record);
            }
            
            records.sort((a, b) -> Integer.compare(b.customerContractId, a.customerContractId));
            
            writeRecordsToFile("sorted-contract-id.txt", records);
            
            for (CustomerRecord record : records) {
                System.out.println(record.toString());
            }
        } catch (IOException e) {
            System.out.println("Error opening sorted output file");
            System.exit(1);
        }
    }
}