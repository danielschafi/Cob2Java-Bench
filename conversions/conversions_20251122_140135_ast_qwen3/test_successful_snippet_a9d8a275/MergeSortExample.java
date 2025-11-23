import java.io.*;
import java.util.*;

public class MergeSortExample {
    static class CustomerRecord {
        int customerId;
        String lastName;
        String firstName;
        int contractId;
        String comment;

        public CustomerRecord() {
            this.customerId = 0;
            this.lastName = "";
            this.firstName = "";
            this.contractId = 0;
            this.comment = "";
        }

        @Override
        public String toString() {
            return String.format("%-5d %-50s %-50s %5d %-25s", 
                customerId, lastName, firstName, contractId, comment);
        }
    }

    static class FileStatus {
        String status;
        FileStatus(String status) { this.status = status; }
    }

    static FileStatus fsStatus1 = new FileStatus("00");
    static FileStatus fsStatus2 = new FileStatus("00");
    static FileStatus fsStatusMerge = new FileStatus("00");
    static FileStatus fsStatusSorted = new FileStatus("00");

    static boolean eof = false;

    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");

        // Simulate merge operation
        List<CustomerRecord> tempRecords = new ArrayList<>();
        try (BufferedReader reader1 = new BufferedReader(new FileReader("test-file-1.txt"));
             BufferedReader reader2 = new BufferedReader(new FileReader("test-file-2.txt"))) {

            String line;
            while ((line = reader1.readLine()) != null) {
                String[] parts = line.split("\\s+", 5);
                CustomerRecord record = new CustomerRecord();
                record.customerId = Integer.parseInt(parts[0]);
                record.lastName = parts[1];
                record.firstName = parts[2];
                record.contractId = Integer.parseInt(parts[3]);
                record.comment = parts[4];
                tempRecords.add(record);
            }

            while ((line = reader2.readLine()) != null) {
                String[] parts = line.split("\\s+", 5);
                CustomerRecord record = new CustomerRecord();
                record.customerId = Integer.parseInt(parts[0]);
                record.lastName = parts[1];
                record.firstName = parts[2];
                record.contractId = Integer.parseInt(parts[3]);
                record.comment = parts[4];
                tempRecords.add(record);
            }
        } catch (IOException e) {
            System.err.println("Error reading input files: " + e.getMessage());
            System.exit(1);
        }

        // Sort by customer ID (ascending)
        tempRecords.sort(Comparator.comparingInt(r -> r.customerId));

        // Write to temporary file
        try (PrintWriter writer = new PrintWriter(new FileWriter("work-temp.txt"))) {
            for (CustomerRecord record : tempRecords) {
                writer.println(record.customerId + " " + record.lastName + " " + 
                             record.firstName + " " + record.contractId + " " + record.comment);
            }
        } catch (IOException e) {
            System.err.println("Error writing to work-temp.txt: " + e.getMessage());
            System.exit(1);
        }

        // Merge files using merge statement logic
        try (BufferedReader reader = new BufferedReader(new FileReader("work-temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 5);
                CustomerRecord record = new CustomerRecord();
                record.customerId = Integer.parseInt(parts[0]);
                record.lastName = parts[1];
                record.firstName = parts[2];
                record.contractId = Integer.parseInt(parts[3]);
                record.comment = parts[4];
                System.out.println(record);
            }
        } catch (IOException e) {
            System.err.println("Error reading merged file: " + e.getMessage());
            System.exit(1);
        }
    }

    static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("work-temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 5);
                CustomerRecord record = new CustomerRecord();
                record.customerId = Integer.parseInt(parts[0]);
                record.lastName = parts[1];
                record.firstName = parts[2];
                record.contractId = Integer.parseInt(parts[3]);
                record.comment = parts[4];
                records.add(record);
            }
        } catch (IOException e) {
            System.err.println("Error reading work-temp.txt: " + e.getMessage());
            System.exit(1);
        }

        // Sort by contract ID (descending)
        records.sort((r1, r2) -> Integer.compare(r2.contractId, r1.contractId));

        // Write sorted records to output file
        try (PrintWriter writer = new PrintWriter(new FileWriter("sorted-contract-id.txt"))) {
            for (CustomerRecord record : records) {
                writer.println(record.customerId + " " + record.lastName + " " + 
                             record.firstName + " " + record.contractId + " " + record.comment);
            }
        } catch (IOException e) {
            System.err.println("Error writing to sorted-contract-id.txt: " + e.getMessage());
            System.exit(1);
        }

        // Display sorted records
        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }

    static void createTestData() {
        System.out.println("Creating test data files...");

        try (PrintWriter writer1 = new PrintWriter(new FileWriter("test-file-1.txt"))) {
            writer1.println("1 last-1 first-1 5423 comment-1");
            writer1.println("5 last-5 first-5 12323 comment-5");
            writer1.println("10 last-10 first-10 653 comment-10");
            writer1.println("50 last-50 first-50 5050 comment-50");
            writer1.println("25 last-25 first-25 7725 comment-25");
            writer1.println("75 last-75 first-75 1175 comment-75");
        } catch (IOException e) {
            System.err.println("Failed to open file for output: " + fsStatus1.status);
            System.exit(1);
        }

        try (PrintWriter writer2 = new PrintWriter(new FileWriter("test-file-2.txt"))) {
            writer2.println("999 last-999 first-999 1610 comment-99");
            writer2.println("3 last-03 first-03 3331 comment-03");
            writer2.println("30 last-30 first-30 8765 comment-30");
            writer2.println("85 last-85 first-85 4567 comment-85");
            writer2.println("24 last-24 first-24 247 comment-24");
        } catch (IOException e) {
            System.err.println("Failed to open file for output: " + fsStatus2.status);
            System.exit(1);
        }
    }
}