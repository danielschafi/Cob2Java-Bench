import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReportTest {
    
    private static final int PAGE_LIMIT = 66;
    private static final int HEADING_LINE = 1;
    private static final int FIRST_DETAIL = 6;
    private static final int LAST_DETAIL = 42;
    private static final int FOOTING_LINE = 52;
    
    private String eofSwitch = "N";
    private int pageCounter = 1;
    private int currentLine = 0;
    private PrintWriter reportWriter;
    
    static class TestRecord {
        String studentId;
        String studentName;
        String major;
        String numCourses;
        
        public TestRecord(String line) {
            if (line.length() >= 31) {
                studentId = line.substring(0, 6);
                studentName = line.substring(6, 26);
                major = line.substring(26, 29);
                numCourses = line.substring(29, 31);
            }
        }
    }
    
    public static void main(String[] args) {
        ReportTest program = new ReportTest();
        program.mainProcedure();
    }
    
    private void mainProcedure() {
        System.out.println("Starting test report program.");
        
        eofSwitch = "N";
        
        try (BufferedReader inputReader = Files.newBufferedReader(Paths.get("input.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
            
            reportWriter = writer;
            
            System.out.println("Init test report.");
            initiateReport();
            
            String line;
            while (!eofSwitch.equals("Y")) {
                line = inputReader.readLine();
                if (line == null) {
                    eofSwitch = "Y";
                } else {
                    TestRecord record = new TestRecord(line);
                    System.out.println("Generate report line.");
                    generateReportLine(record);
                }
            }
            
            System.out.println("Terminate report.");
            terminateReport();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Done.");
    }
    
    private void initiateReport() {
        currentLine = 0;
        pageCounter = 1;
        printReportHeader();
    }
    
    private void printReportHeader() {
        for (int i = currentLine; i < HEADING_LINE; i++) {
            reportWriter.println();
        }
        
        reportWriter.println(String.format("%" + 64 + "s", "Customer Order Report"));
        currentLine = 1;
        
        reportWriter.println(String.format("%" + 104 + "s%" + 3 + "s", "PAGE", formatNumber(pageCounter, 3)));
        currentLine = 2;
        
        while (currentLine < FIRST_DETAIL) {
            reportWriter.println();
            currentLine++;
        }
    }
    
    private void generateReportLine(TestRecord record) {
        if (currentLine >= LAST_DETAIL) {
            pageCounter++;
            currentLine = 0;
            printReportHeader();
        }
        
        if (currentLine < FIRST_DETAIL) {
            while (currentLine < FIRST_DETAIL) {
                reportWriter.println();
                currentLine++;
            }
        }
        
        StringBuilder line = new StringBuilder();
        line.append(String.format("   %6s", record.studentId));
        line.append(String.format("          %-20s", record.studentName));
        line.append(String.format("     %3s", record.major));
        line.append(String.format("     %2s", record.numCourses));
        
        reportWriter.println(line.toString());
        currentLine++;
    }
    
    private void terminateReport() {
        while (currentLine < PAGE_LIMIT) {
            reportWriter.println();
            currentLine++;
        }
    }
    
    private String formatNumber(int number, int width) {
        String numStr = String.valueOf(number);
        while (numStr.length() < width) {
            numStr = " " + numStr;
        }
        return numStr;
    }
}