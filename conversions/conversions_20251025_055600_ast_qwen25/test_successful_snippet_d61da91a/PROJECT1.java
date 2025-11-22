import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PROJECT1 {
    private static final String SOURCE_FILE = "PR1FA17.txt";
    private static final String REPORT_FILE = "report.txt";
    private static final int RECORD_LENGTH = 79;
    private static final int LINES_PER_PAGE = 55;

    private String eofFlag = "YES";
    private int properSpacing = 0;
    private int lineNum = 10;
    private int hlPageNum = 1;
    private String wsMonth;
    private String wsDay;
    private String wsYear;

    public static void main(String[] args) {
        PROJECT1 project1 = new PROJECT1();
        project1.run();
    }

    private void run() {
        try (BufferedReader sourceReader = new BufferedReader(new FileReader(SOURCE_FILE));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter(REPORT_FILE))) {

            housekeeping();
            readSourceFile(sourceReader, reportWriter);
            closeRoutine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void housekeeping() throws IOException {
        hlPageNum = 1;
        dateRoutine();
        headingRoutine();
    }

    private void dateRoutine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
        String date = dateFormat.format(new Date());
        wsMonth = date.substring(0, 2);
        wsDay = date.substring(3, 5);
        wsYear = date.substring(6, 8);
    }

    private void headingRoutine() throws IOException {
        properSpacing = 1;
        writeReportRecord(" " + wsMonth + "/" + wsDay + "/20   TSB           Drakea Cart Parts Warehouse          PAGE " + String.format("%02d", hlPageNum));
        properSpacing = 3;
        writeReportRecord("            STOCK REPORT");
        properSpacing = 2;
        writeReportRecord(" CAT         ITEM         PURCHASE   QUANTITY   QUANTITY  REORDER");
        properSpacing = 1;
        writeReportRecord(" NUM         DESCRIPTION      PRICE      IN STK   ON ORDER  POINT");
        properSpacing = 1;
        writeReportRecord("");
    }

    private void readSourceFile(BufferedReader sourceReader, BufferedWriter reportWriter) throws IOException {
        String line;
        while ((line = sourceReader.readLine()) != null) {
            if (line.length() < RECORD_LENGTH) {
                line = String.format("%-" + RECORD_LENGTH + "s", line);
            }
            constructData(line, reportWriter);
            lineNum++;
            if (lineNum == LINES_PER_PAGE) {
                newPage(reportWriter);
                lineNum = 10;
            }
        }
        eofFlag = "NO";
    }

    private void constructData(String line, BufferedWriter reportWriter) throws IOException {
        String catalogNum = line.substring(0, 5).trim();
        String description = line.substring(5, 25).trim();
        String unitPurchasePrice = line.substring(25, 31).trim();
        String quantityOnHand = line.substring(38, 43).trim();
        String quantityOnOrder = line.substring(43, 48).trim();
        String reorderPoint = line.substring(48, 53).trim();

        String detailLine = String.format("%-5s   %-20s   $%-6s   %-6s   %-5s   %-5s",
                catalogNum, description, unitPurchasePrice, quantityOnHand, quantityOnOrder, reorderPoint);

        properSpacing = 1;
        writeReportRecord(detailLine);
    }

    private void newPage(BufferWriter reportWriter) throws IOException {
        hlPageNum++;
        writeReportRecord("");
        headingRoutine();
    }

    private void closeRoutine() {
        // Files are closed automatically by try-with-resources
    }

    private void writeReportRecord(String record) throws IOException {
        for (int i = 0; i < properSpacing; i++) {
            reportWriter.newLine();
        }
        reportWriter.write(record);
        reportWriter.newLine();
    }
}