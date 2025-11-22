import java.io.*;
import java.util.*;

public class MergeSortExample {
    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGED_FILE = "merge-output.txt";
    private static final String SORTED_CONTRACT_ID_FILE = "sorted-contract-id.txt";

    private static class CustomerRecord {
        int customerId;
        String lastName;
        String firstName;
        int contractId;
        String comment;

        @Override
        public String toString() {
            return String.format("%05d%-50s%-50s%05d%-25s", customerId, lastName, firstName, contractId, comment);
        }
    }

    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    private static void createTestData() {
        System.out.println("Creating test data files...");

        List<CustomerRecord> testData1 = Arrays.asList(
            new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"),
            new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"),
            new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"),
            new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"),
            new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"),
            new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75")
        );

        List<CustomerRecord> testData2 = Arrays.asList(
            new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"),
            new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"),
            new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"),
            new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"),
            new CustomerRecord(24, "last-24", "first-24", 247, "comment-24")
        );

        writeRecordsToFile(TEST_FILE_1, testData1);
        writeRecordsToFile(TEST_FILE_2, testData2);
    }

    private static void writeRecordsToFile(String fileName, List<CustomerRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> mergedRecords = new ArrayList<>();
        try (BufferedReader reader1 = new BufferedReader(new FileReader(TEST_FILE_1));
             BufferedReader reader2 = new BufferedReader(new FileReader(TEST_FILE_2))) {

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            while (line1 != null || line2 != null) {
                if (line2 == null || (line1 != null && Integer.parseInt(line1.substring(0, 5)) < Integer.parseInt(line2.substring(0, 5)))) {
                    mergedRecords.add(parseRecord(line1));
                    line1 = reader1.readLine();
                } else {
                    mergedRecords.add(parseRecord(line2));
                    line2 = reader2.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening merged output file: " + e.getMessage());
            System.exit(1);
        }

        writeRecordsToFile(MERGED_FILE, mergedRecords);
        displayFile(MERGED_FILE);
    }

    private static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MERGED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(parseRecord(line));
            }
        } catch (IOException e) {
            System.out.println("Error opening sorted output file: " + e.getMessage());
            System.exit(1);
        }

        records.sort(Comparator.comparingInt((CustomerRecord r) -> r.contractId).reversed());

        writeRecordsToFile(SORTED_CONTRACT_ID_FILE, records);
        displayFile(SORTED_CONTRACT_ID_FILE);
    }

    private static CustomerRecord parseRecord(String line) {
        CustomerRecord record = new CustomerRecord();
        record.customerId = Integer.parseInt(line.substring(0, 5).trim());
        record.lastName = line.substring(5, 55).trim();
        record.firstName = line.substring(55, 105).trim();
        record.contractId = Integer.parseInt(line.substring(105, 110).trim());
        record.comment = line.substring(110, 135).trim();
        return record;
    }

    private static void displayFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}