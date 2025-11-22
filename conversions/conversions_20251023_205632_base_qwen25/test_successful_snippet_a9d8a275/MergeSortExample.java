import java.io.*;
import java.util.*;

public class MergeSortExample {
    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    private static void createTestData() {
        System.out.println("Creating test data files...");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-1.txt"))) {
            writer.write("00001last-1                             first-1                             05423comment-1           \n");
            writer.write("00005last-5                             first-5                             12323comment-5           \n");
            writer.write("00010last-10                            first-10                            00653comment-10          \n");
            writer.write("00050last-50                            first-50                            05050comment-50          \n");
            writer.write("00025last-25                            first-25                            07725comment-25          \n");
            writer.write("00075last-75                            first-75                            01175comment-75          \n");
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + e.getMessage());
            System.exit(1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-2.txt"))) {
            writer.write("00999last-999                           first-999                           01610comment-99          \n");
            writer.write("00003last-03                            first-03                            03331comment-03          \n");
            writer.write("00030last-30                            first-30                            08765comment-30          \n");
            writer.write("00085last-85                            first-85                            04567comment-85          \n");
            writer.write("00024last-24                            first-24                            00247comment-24          \n");
        } catch (IOException e) {
            System.out.println("Failed to open file for output: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");

        List<String> file1 = readFile("test-file-1.txt");
        List<String> file2 = readFile("test-file-2.txt");

        List<String> merged = new ArrayList<>(file1);
        merged.addAll(file2);
        Collections.sort(merged, Comparator.comparingInt(MergeSortExample::getCustomerId));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merge-output.txt"))) {
            for (String record : merged) {
                writer.write(record);
            }
        } catch (IOException e) {
            System.out.println("Error opening merged output file: " + e.getMessage());
            System.exit(1);
        }

        displayFile("merge-output.txt");
    }

    private static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");

        List<String> merged = readFile("merge-output.txt");

        Collections.sort(merged, Comparator.comparingInt(MergeSortExample::getContractId).reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted-contract-id.txt"))) {
            for (String record : merged) {
                writer.write(record);
            }
        } catch (IOException e) {
            System.out.println("Error opening sorted output file: " + e.getMessage());
            System.exit(1);
        }

        displayFile("sorted-contract-id.txt");
    }

    private static List<String> readFile(String fileName) {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        return records;
    }

    private static void displayFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static int getCustomerId(String record) {
        return Integer.parseInt(record.substring(0, 5).trim());
    }

    private static int getContractId(String record) {
        return Integer.parseInt(record.substring(105, 110).trim());
    }
}