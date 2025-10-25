import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PROJECT1 {
    private static final String SOURCE_FILE = "PR1FA17.txt";
    private static final String REPORT_FILE = "report.txt";
    private static final int LINES_PER_PAGE = 55;

    private BufferedReader sourceReader;
    private BufferedWriter reportWriter;
    private String eofFlag = "YES";
    private int properSpacing = 0;
    private int lineNum = 10;
    private int hlPageNum = 1;
    private String wsDate;
    private String hlMonth;
    private String hlDay;
    private String hlYear;

    public static void main(String[] args) {
        PROJECT1 project1 = new PROJECT1();
        project1.run();
    }

    private void run() {
        try {
            housekeeping();
            readSourceFile();
            closeRoutine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void housekeeping() throws IOException {
        hlPageNum = 1;
        sourceReader = new BufferedReader(new FileReader(SOURCE_FILE));
        reportWriter = new BufferedWriter(new FileWriter(REPORT_FILE));
        dateRoutine();
        headingRoutine();
    }

    private void dateRoutine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        wsDate = dateFormat.format(new Date());
        hlMonth = wsDate.substring(0, 2);
        hlDay = wsDate.substring(3, 5);
        hlYear = wsDate.substring(8, 10);
    }

    private void headingRoutine() throws IOException {
        properSpacing = 1;
        writeReportRecord(String.format("%s/%s/20%s     TSB     Drakea Cart Parts Warehouse          PAGE %02d", hlMonth, hlDay, hlYear, hlPageNum));
        properSpacing = 3;
        writeReportRecord("                                    STOCK REPORT");
        properSpacing = 2;
        writeReportRecord(" CAT         ITEM         PURCHASE   QUANTITY   QUANTITY  REORDER");
        properSpacing = 1;
        writeReportRecord(" NUM         DESCRIPTION  PRICE      IN STK     ON ORDER  POINT");
        properSpacing = 1;
        writeReportRecord("");
    }

    private void readSourceFile() throws IOException {
        String line;
        while ((line = sourceReader.readLine()) != null) {
            eofFlag = "NO";
            constructData(line);
            lineNum++;
            if (lineNum == LINES_PER_PAGE) {
                newPage();
                lineNum = 10;
            }
        }
    }

    private void constructData(String line) throws IOException {
        String catalogNum = line.substring(0, 5).trim();
        String description = line.substring(5, 25).trim();
        String unitPurchasePrice = line.substring(25, 32).trim();
        String quantityOnHand = line.substring(39, 44).trim();
        String quantityOnOrder = line.substring(44, 49).trim();
        String reorderPoint = line.substring(49, 54).trim();

        properSpacing = 1;
        String detailLine = String.format("%-5s   %-20s $%7s   %5s      %5s     %5s", catalogNum, description, unitPurchasePrice, quantityOnHand, quantityOnOrder, reorderPoint);
        writeReportRecord(detailLine);
    }

    private void newPage() throws IOException {
        hlPageNum++;
        writeReportRecord("", true);
        headingRoutine();
    }

    private void closeRoutine() throws IOException {
        sourceReader.close();
        reportWriter.close();
    }

    private void writeReportRecord(String record) throws IOException {
        writeReportRecord(record, false);
    }

    private void writeReportRecord(String record, boolean newPage) throws IOException {
        for (int i = 0; i < properSpacing; i++) {
            reportWriter.newLine();
        }
        reportWriter.write(record);
        reportWriter.newLine();
        if (newPage) {
            reportWriter.newLine();
        }
    }
}