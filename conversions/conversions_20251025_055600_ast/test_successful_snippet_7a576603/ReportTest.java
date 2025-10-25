import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReportTest {
    private static final String INPUT_FILE = "input.txt";
    private static final String REPORT_FILE = "report.txt";
    private static final int PAGE_LIMIT = 66;
    private static final int HEADING_LINE = 1;
    private static final int FIRST_DETAIL_LINE = 6;
    private static final int LAST_DETAIL_LINE = 42;
    private static final int FOOTING_LINE = 52;

    private static class StudentRecord {
        int studentId;
        String studentName;
        String major;
        int numCourses;
    }

    private static class ReportWriter {
        private PrintWriter writer;
        private int currentPage = 1;
        private int currentLine = 1;

        public ReportWriter(String fileName) throws FileNotFoundException {
            writer = new PrintWriter(new FileWriter(fileName));
        }

        public void writeHeader() {
            writeLine(HEADING_LINE, "Customer Order Report", 44);
            writeLine(2, "PAGE", 100);
            writeLine(2, String.format("%03d", currentPage), 105);
        }

        public void writeDetail(StudentRecord record) {
            if (currentLine >= LAST_DETAIL_LINE) {
                newPage();
            }
            writeLine(currentLine, String.format("%06d", record.studentId), 4);
            writeLine(currentLine, record.studentName, 15);
            writeLine(currentLine, record.major, 40);
            writeLine(currentLine, String.format("%02d", record.numCourses), 46);
            currentLine++;
        }

        public void writeFooter() {
            writeLine(FOOTING_LINE, "End of Report", 44);
        }

        public void newPage() {
            while (currentLine <= PAGE_LIMIT) {
                writeLine(currentLine, "", 1);
                currentLine++;
            }
            currentPage++;
            currentLine = 1;
            writeHeader();
        }

        private void writeLine(int line, String text, int column) {
            if (currentLine != line) {
                newPage();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < column; i++) {
                sb.append(" ");
            }
            sb.append(text);
            writer.println(sb.toString());
            currentLine++;
        }

        public void close() {
            writer.close();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting test report program.");
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             ReportWriter reportWriter = new ReportWriter(REPORT_FILE)) {

            reportWriter.writeHeader();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 32) {
                    continue;
                }
                StudentRecord record = new StudentRecord();
                record.studentId = Integer.parseInt(line.substring(0, 6).trim());
                record.studentName = line.substring(6, 26).trim();
                record.major = line.substring(26, 29).trim();
                record.numCourses = Integer.parseInt(line.substring(29, 31).trim());
                reportWriter.writeDetail(record);
            }
            reportWriter.writeFooter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done.");
    }
}