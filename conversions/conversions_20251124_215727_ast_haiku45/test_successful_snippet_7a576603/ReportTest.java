import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReportTest {
    private static class TestRecord {
        int studentId;
        String studentName;
        String major;
        int numCourses;
    }

    private static String wsEofSw = "N";
    private static int pageCounter = 1;
    private static TestRecord fTestRecord = new TestRecord();
    private static BufferedReader inputReader;
    private static PrintWriter reportWriter;
    private static boolean eof = false;

    public static void main(String[] args) {
        try {
            System.out.println("Starting test report program.");
            
            wsEofSw = "N";
            
            inputReader = new BufferedReader(new FileReader("input.txt"));
            reportWriter = new PrintWriter(new FileWriter("report.txt"));
            
            System.out.println("Init test report.");
            initiateReport();
            
            while (!eof) {
                readInputFile();
                if (!eof) {
                    System.out.println("Generate report line.");
                    generateReportLine();
                }
            }
            
            System.out.println("Terminate report.");
            terminateReport();
            
            inputReader.close();
            reportWriter.close();
            
            System.out.println("Done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInputFile() {
        try {
            String line = inputReader.readLine();
            if (line == null) {
                eof = true;
                wsEofSw = "Y";
            } else {
                parseRecord(line);
            }
        } catch (IOException e) {
            eof = true;
            wsEofSw = "Y";
        }
    }

    private static void parseRecord(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
                fTestRecord.studentId = Integer.parseInt(parts[0].trim());
                fTestRecord.studentName = parts[1].trim();
                fTestRecord.major = parts[2].trim();
                fTestRecord.numCourses = Integer.parseInt(parts[3].trim());
            }
        } catch (NumberFormatException e) {
            eof = true;
            wsEofSw = "Y";
        }
    }

    private static void initiateReport() {
        pageCounter = 1;
        reportWriter.println("                                            Customer Order Report");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
    }

    private static void generateReportLine() {
        StringBuilder line = new StringBuilder();
        
        for (int i = 0; i < 3; i++) {
            line.append(" ");
        }
        line.append(String.format("%06d", fTestRecord.studentId));
        
        while (line.length() < 14) {
            line.append(" ");
        }
        line.append(String.format("%-20s", fTestRecord.studentName));
        
        while (line.length() < 39) {
            line.append(" ");
        }
        line.append(String.format("%-3s", fTestRecord.major));
        
        while (line.length() < 45) {
            line.append(" ");
        }
        line.append(String.format("%02d", fTestRecord.numCourses));
        
        reportWriter.println(line.toString());
    }

    private static void terminateReport() {
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
        reportWriter.println("");
    }

    private static void populateInputFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("input.txt", true));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}