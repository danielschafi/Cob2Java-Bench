import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportTest {
    
    static class TestRecord {
        int studentId;
        String studentName;
        String major;
        int numCourses;
    }
    
    static class ReportState {
        int pageCounter = 1;
        int lineCounter = 0;
        int detailLineCount = 0;
        StringBuilder pageBuffer = new StringBuilder();
    }
    
    private static final int PAGE_LIMIT = 66;
    private static final int HEADING_LINE = 1;
    private static final int FIRST_DETAIL = 6;
    private static final int LAST_DETAIL = 42;
    private static final int FOOTING_LINE = 52;
    
    public static void main(String[] args) {
        System.out.println("Starting test report program.");
        
        String eofSw = "N";
        ReportState reportState = new ReportState();
        
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter reportWriter = new BufferedWriter(new FileWriter("report.txt"));
            
            System.out.println("Init test report.");
            initializeReport(reportWriter, reportState);
            
            String line;
            while ((line = inputReader.readLine()) != null && !eofSw.equals("Y")) {
                TestRecord record = parseRecord(line);
                System.out.println("Generate report line.");
                generateReportLine(reportWriter, record, reportState);
            }
            
            System.out.println("Terminate report.");
            terminateReport(reportWriter, reportState);
            
            inputReader.close();
            reportWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Done.");
    }
    
    private static TestRecord parseRecord(String line) {
        TestRecord record = new TestRecord();
        
        if (line.length() >= 6) {
            record.studentId = Integer.parseInt(line.substring(0, 6).trim());
        }
        if (line.length() >= 26) {
            record.studentName = line.substring(6, 26);
        }
        if (line.length() >= 29) {
            record.major = line.substring(26, 29);
        }
        if (line.length() >= 31) {
            record.numCourses = Integer.parseInt(line.substring(29, 31).trim());
        }
        
        return record;
    }
    
    private static void initializeReport(BufferedWriter writer, ReportState state) throws IOException {
        state.pageCounter = 1;
        state.lineCounter = 0;
        state.detailLineCount = 0;
        writePageHeader(writer, state);
    }
    
    private static void writePageHeader(BufferedWriter writer, ReportState state) throws IOException {
        writer.write(String.format("%44s%s\n", "", "Customer Order Report"));
        writer.write(String.format("%100s%4s%3d\n", "", "PAGE", state.pageCounter));
        writer.write("\n");
        writer.write("\n");
        writer.write("\n");
        writer.write("\n");
        state.lineCounter = 5;
    }
    
    private static void generateReportLine(BufferedWriter writer, TestRecord record, ReportState state) throws IOException {
        if (state.lineCounter >= LAST_DETAIL) {
            writePageFooter(writer, state);
            state.pageCounter++;
            writePageHeader(writer, state);
        }
        
        String line = String.format("%4d%-20s%-6s%2d",
                record.studentId,
                record.studentName != null ? record.studentName : "",
                record.major != null ? record.major : "",
                record.numCourses);
        
        writer.write(line);
        writer.write("\n");
        state.lineCounter++;
        state.detailLineCount++;
    }
    
    private static void writePageFooter(BufferedWriter writer, ReportState state) throws IOException {
        while (state.lineCounter < FOOTING_LINE) {
            writer.write("\n");
            state.lineCounter++;
        }
    }
    
    private static void terminateReport(BufferedWriter writer, ReportState state) throws IOException {
        writePageFooter(writer, state);
        writer.flush();
    }
}