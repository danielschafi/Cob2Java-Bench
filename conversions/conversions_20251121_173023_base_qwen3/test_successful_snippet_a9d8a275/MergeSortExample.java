import java.io.*;
import java.util.*;

public class MergeSortExample {
    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String WORK_TEMP = "work-temp.txt";
    private static final String MERGE_OUTPUT = "merge-output.txt";
    private static final String SORTED_CONTRACT_ID = "sorted-contract-id.txt";

    private static class CustomerRecord {
        int customerId;
        String lastName;
        String firstName;
        int contractId;
        String comment;

        public CustomerRecord() {
            this.lastName = "";
            this.firstName = "";
            this.comment = "";
        }

        @Override
        public String toString() {
            return String.format("%05d%-50s%-50s%05d%-25s", 
                customerId, lastName, firstName, contractId, comment);
        }
    }

    private static class FileStatus {
        String status = "00";
    }

    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    private static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");

        // Create temporary file with sorted records
        try {
            List<CustomerRecord> records = new ArrayList<>();
            
            // Read from test-file-1
            try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_1))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    CustomerRecord record = parseRecord(line);
                    records.add(record);
                }
            }

            // Read from test-file-2
            try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_2))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    CustomerRecord record = parseRecord(line);
                    records.add(record);
                }
            }

            // Sort by customer ID
            records.sort(Comparator.comparingInt(r -> r.customerId));

            // Write to work-temp.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter(WORK_TEMP))) {
                for (CustomerRecord record : records) {
                    writer.println(record);
                }
            }

            // Merge into merge-output.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter(MERGE_OUTPUT))) {
                for (CustomerRecord record : records) {
                    writer.println(record);
                }
            }

            // Display merged file
            try (BufferedReader reader = new BufferedReader(new FileReader(MERGE_OUTPUT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error in merge operation: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");

        try {
            List<CustomerRecord> records = new ArrayList<>();
            
            // Read from merge-output.txt
            try (BufferedReader reader = new BufferedReader(new FileReader(MERGE_OUTPUT))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    CustomerRecord record = parseRecord(line);
                    records.add(record);
                }
            }

            // Sort by contract ID descending
            records.sort((r1, r2) -> Integer.compare(r2.contractId, r1.contractId));

            // Write to sorted-contract-id.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter(SORTED_CONTRACT_ID))) {
                for (CustomerRecord record : records) {
                    writer.println(record);
                }
            }

            // Display sorted file
            try (BufferedReader reader = new BufferedReader(new FileReader(SORTED_CONTRACT_ID))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error in sort operation: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void createTestData() {
        System.out.println("Creating test data files...");

        try {
            // Create test-file-1
            try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE_1))) {
                writeRecord(writer, 1, "last-1", "first-1", 5423, "comment-1");
                writeRecord(writer, 5, "last-5", "first-5", 12323, "comment-5");
                writeRecord(writer, 10, "last-10", "first-10", 653, "comment-10");
                writeRecord(writer, 50, "last-50", "first-50", 5050, "comment-50");
                writeRecord(writer, 25, "last-25", "first-25", 7725, "comment-25");
                writeRecord(writer, 75, "last-75", "first-75", 1175, "comment-75");
            }

            // Create test-file-2
            try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE_2))) {
                writeRecord(writer, 999, "last-999", "first-999", 1610, "comment-99");
                writeRecord(writer, 3, "last-03", "first-03", 3331, "comment-03");
                writeRecord(writer, 30, "last-30", "first-30", 8765, "comment-30");
                writeRecord(writer, 85, "last-85", "first-85", 4567, "comment-85");
                writeRecord(writer, 24, "last-24", "first-24", 247, "comment-24");
            }

        } catch (IOException e) {
            System.err.println("Error creating test data: " + e.getMessage());
            System.exit(1);
        }
    }

    private static CustomerRecord parseRecord(String line) {
        CustomerRecord record = new CustomerRecord();
        record.customerId = Integer.parseInt(line.substring(0, 5));
        record.lastName = line.substring(5, 55).trim();
        record.firstName = line.substring(55, 105).trim();
        record.contractId = Integer.parseInt(line.substring(105, 110));
        record.comment = line.substring(110, 135).trim();
        return record;
    }

    private static void writeRecord(PrintWriter writer, int id, String lastName, String firstName, 
                                  int contractId, String comment) {
        CustomerRecord record = new CustomerRecord();
        record.customerId = id;
        record.lastName = lastName;
        record.firstName = firstName;
        record.contractId = contractId;
        record.comment = comment;
        writer.println(record);
    }
}