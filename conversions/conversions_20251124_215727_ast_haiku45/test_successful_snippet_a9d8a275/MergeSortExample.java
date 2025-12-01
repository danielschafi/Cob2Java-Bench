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

        @Override
        public String toString() {
            return String.format("%05d%-50s%-50s%05d%-25s", customerId, customerLastName, customerFirstName, customerContractId, customerComment);
        }
    }

    static String fsStatus1 = "00";
    static String fsStatus2 = "00";
    static String fsStatusMerge = "00";
    static String fsStatusSorted = "00";
    static boolean eof = false;

    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    static void createTestData() {
        System.out.println("Creating test data files...");
        
        try (PrintWriter writer1 = new PrintWriter(new FileWriter("test-file-1.txt"))) {
            writeRecord(writer1, 1, "last-1", "first-1", 5423, "comment-1");
            writeRecord(writer1, 5, "last-5", "first-5", 12323, "comment-5");
            writeRecord(writer1, 10, "last-10", "first-10", 653, "comment-10");
            writeRecord(writer1, 50, "last-50", "first-50", 5050, "comment-50");
            writeRecord(writer1, 25, "last-25", "first-25", 7725, "comment-25");
            writeRecord(writer1, 75, "last-75", "first-75", 1175, "comment-75");
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + e.getMessage());
            System.exit(1);
        }

        try (PrintWriter writer2 = new PrintWriter(new FileWriter("test-file-2.txt"))) {
            writeRecord(writer2, 999, "last-999", "first-999", 1610, "comment-99");
            writeRecord(writer2, 3, "last-03", "first-03", 3331, "comment-03");
            writeRecord(writer2, 30, "last-30", "first-30", 8765, "comment-30");
            writeRecord(writer2, 85, "last-85", "first-85", 4567, "comment-85");
            writeRecord(writer2, 24, "last-24", "first-24", 247, "comment-24");
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + e.getMessage());
            System.exit(1);
        }
    }

    static void writeRecord(PrintWriter writer, int id, String lastName, String firstName, int contractId, String comment) {
        writer.println(String.format("%05d%-50s%-50s%05d%-25s", id, lastName, firstName, contractId, comment));
    }

    static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");
        
        try {
            List<CustomerRecord> records1 = readFile("test-file-1.txt");
            List<CustomerRecord> records2 = readFile("test-file-2.txt");
            
            List<CustomerRecord> merged = new ArrayList<>(records1);
            merged.addAll(records2);
            
            merged.sort(Comparator.comparingInt(r -> r.customerId));
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("merge-output.txt"))) {
                for (CustomerRecord record : merged) {
                    writer.println(record.toString());
                    System.out.println(record.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening merged output file: " + e.getMessage());
            System.exit(1);
        }
    }

    static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");
        
        try {
            List<CustomerRecord> records = readFile("merge-output.txt");
            
            records.sort((r1, r2) -> Integer.compare(r2.customerContractId, r1.customerContractId));
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("sorted-contract-id.txt"))) {
                for (CustomerRecord record : records) {
                    writer.println(record.toString());
                    System.out.println(record.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening sorted output file: " + e.getMessage());
            System.exit(1);
        }
    }

    static List<CustomerRecord> readFile(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        
        for (String line : lines) {
            if (line.length() >= 135) {
                CustomerRecord record = new CustomerRecord();
                record.customerId = Integer.parseInt(line.substring(0, 5).trim());
                record.customerLastName = line.substring(5, 55).trim();
                record.customerFirstName = line.substring(55, 105).trim();
                record.customerContractId = Integer.parseInt(line.substring(105, 110).trim());
                record.customerComment = line.substring(110, 135).trim();
                records.add(record);
            }
        }
        
        return records;
    }
}