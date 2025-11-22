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

        CustomerRecord(int customerId, String customerLastName, String customerFirstName, 
                      int customerContractId, String customerComment) {
            this.customerId = customerId;
            this.customerLastName = customerLastName;
            this.customerFirstName = customerFirstName;
            this.customerContractId = customerContractId;
            this.customerComment = customerComment;
        }

        String toFileString() {
            return String.format("%05d%-50s%-50s%05d%-25s", 
                customerId, customerLastName, customerFirstName, customerContractId, customerComment);
        }

        static CustomerRecord fromFileString(String line) {
            if (line.length() < 135) {
                line = String.format("%-135s", line);
            }
            int customerId = Integer.parseInt(line.substring(0, 5).trim());
            String customerLastName = line.substring(5, 55);
            String customerFirstName = line.substring(55, 105);
            int customerContractId = Integer.parseInt(line.substring(105, 110).trim());
            String customerComment = line.substring(110, 135);
            return new CustomerRecord(customerId, customerLastName, customerFirstName, 
                                     customerContractId, customerComment);
        }

        @Override
        public String toString() {
            return toFileString();
        }
    }

    public static void main(String[] args) {
        try {
            createTestData();
            mergeAndDisplayFiles();
            sortAndDisplayFile();
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void createTestData() throws IOException {
        System.out.println("Creating test data files...");

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("test-file-1.txt"))) {
            writer.write(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75").toFileString());
            writer.newLine();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("test-file-2.txt"))) {
            writer.write(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85").toFileString());
            writer.newLine();
            writer.write(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24").toFileString());
            writer.newLine();
        }
    }

    static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> records = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("test-file-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFileString(line));
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("test-file-2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFileString(line));
            }
        }

        records.sort(Comparator.comparingInt(r -> r.customerId));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("merge-output.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFileString());
                writer.newLine();
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFileString(line));
            }
        }

        records.sort(Comparator.comparingInt((CustomerRecord r) -> r.customerContractId).reversed());

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("sorted-contract-id.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFileString());
                writer.newLine();
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("sorted-contract-id.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}