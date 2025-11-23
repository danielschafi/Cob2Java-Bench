import java.io.*;
import java.util.*;

public class ReportTest {
    private static final String INPUT_FILE = "input.txt";
    private static final String REPORT_FILE = "report.txt";
    
    // Data structures to simulate COBOL records
    static class FTestRecord {
        int fTestStudentId;
        String fTestStudentName;
        String fTestMajor;
        int fTestNumCourses;
        
        public FTestRecord() {
            this.fTestStudentName = "";
            this.fTestMajor = "";
        }
    }
    
    static class WsEofSw {
        char value = 'N';
        boolean wsEof = false;
        boolean wsNotEof = true;
    }
    
    static class ReportHeader {
        String line1Column44 = "Customer Order Report";
        String line2Column100 = "PAGE";
        int line2Column105 = 1; // page counter
    }
    
    static class ReportLine {
        int column4;
        String column15;
        String column40;
        int column46;
    }
    
    public static void main(String[] args) {
        System.out.println("Starting test report program.");
        
        WsEofSw wsEofSw = new WsEofSw();
        wsEofSw.value = 'N';
        wsEofSw.wsEof = false;
        wsEofSw.wsNotEof = true;
        
        try {
            // Open files
            BufferedReader inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            PrintWriter reportFile = new PrintWriter(new FileWriter(REPORT_FILE));
            
            System.out.println("Init test report.");
            
            // Initialize report
            ReportHeader reportHeader = new ReportHeader();
            reportFile.println();
            reportFile.println("                    Customer Order Report");
            reportFile.println("                                       PAGE   1");
            reportFile.println();
            
            // Read and process records
            String line;
            int pageNumber = 1;
            int recordCount = 0;
            
            while ((line = inputFile.readLine()) != null) {
                recordCount++;
                if (recordCount > 66) break; // Simulate page limit
                
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    FTestRecord record = new FTestRecord();
                    record.fTestStudentId = Integer.parseInt(parts[0].trim());
                    record.fTestStudentName = parts[1].trim();
                    record.fTestMajor = parts[2].trim();
                    record.fTestNumCourses = Integer.parseInt(parts[3].trim());
                    
                    // Generate report line
                    System.out.println("Generate report line.");
                    ReportLine reportLine = new ReportLine();
                    reportLine.column4 = record.fTestStudentId;
                    reportLine.column15 = String.format("%-20s", record.fTestStudentName);
                    reportLine.column40 = record.fTestMajor;
                    reportLine.column46 = record.fTestNumCourses;
                    
                    // Write to report file
                    reportFile.printf("%6d%-20s%-3s%3d%n", 
                        reportLine.column4,
                        reportLine.column15,
                        reportLine.column40,
                        reportLine.column46);
                }
            }
            
            // Close files
            inputFile.close();
            reportFile.close();
            
            System.out.println("Terminate report.");
            System.out.println("Done.");
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}