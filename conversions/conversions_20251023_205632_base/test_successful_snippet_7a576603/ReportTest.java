import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReportTest {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String reportFileName = "report.txt";
        String eofSw = "N";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportFileName))) {

            System.out.println("Starting test report program.");
            System.out.println("Init test report.");

            writer.write(String.format("%44s\n", "Customer Order Report"));
            writer.write(String.format("%100s%03d\n", "PAGE", 1));

            String line;
            int pageNumber = 1;
            int lineCount = 6;

            while ((line = reader.readLine()) != null && !eofSw.equals("Y")) {
                if (lineCount >= 66) {
                    writer.write(String.format("\f%44s\n", "Customer Order Report"));
                    writer.write(String.format("%100s%03d\n", "PAGE", ++pageNumber));
                    lineCount = 6;
                }

                String studentId = line.substring(0, 6).trim();
                String studentName = line.substring(6, 26).trim();
                String major = line.substring(26, 29).trim();
                String numCourses = line.substring(29, 31).trim();

                writer.write(String.format("%4s%-20s%-3s%4s\n", studentId, studentName, major, numCourses));
                lineCount++;
            }

            System.out.println("Terminate report.");
            System.out.println("Done.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}