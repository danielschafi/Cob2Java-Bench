import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PROJECT1 {
    private static String catalogNum;
    private static String description;
    private static double unitPurchasePrice;
    private static int quantityOnHand;
    private static int quantityOnOrder;
    private static int reorderPoint;
    private static int receivedWeekly;
    private static int soldWeekly;
    private static int returnedWeekly;

    private static String eofFlag = "YES";
    private static int properSpacing = 0;
    private static int lineNum = 10;

    private static int wsMonth;
    private static int wsDay;
    private static int wsYear;

    private static int hlMonth;
    private static int hlDay;
    private static int hlYear;
    private static int hlPageNum;

    private static BufferedReader sourceFile;
    private static PrintWriter reportFile;

    public static void main(String[] args) {
        try {
            housekeeping();
            readSourceFile();
            closeRoutine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void housekeeping() throws IOException {
        hlPageNum = 1;
        sourceFile = new BufferedReader(new FileReader("PR1FA17.txt"));
        reportFile = new PrintWriter(new FileWriter("report.txt"));
        dateRoutine();
        headingRoutine();
    }

    private static void dateRoutine() {
        LocalDate today = LocalDate.now();
        wsMonth = today.getMonthValue();
        wsDay = today.getDayOfMonth();
        wsYear = today.getYear() % 100;

        hlMonth = wsMonth;
        hlDay = wsDay;
        hlYear = wsYear;
    }

    private static void headingRoutine() {
        properSpacing = 1;
        String headingLine1 = String.format("%02d/%02d/20%02d     TSB          Drakea Cart Parts Warehouse        PAGE %02d",
                hlMonth, hlDay, hlYear, hlPageNum);
        writeLine(headingLine1, properSpacing);

        properSpacing = 3;
        String headingLine2 = String.format("%36s%s", "", "STOCK REPORT");
        writeLine(headingLine2, properSpacing);

        properSpacing = 2;
        String headingLine3 = " CAT          ITEM          PURCHASE   QUANTITY    QUANTITY    REORDER";
        writeLine(headingLine3, properSpacing);

        properSpacing = 1;
        String headingLine4 = " NUM        DESCRIPTION      PRICE      IN STK    ON ORDER      POINT";
        writeLine(headingLine4, properSpacing);

        properSpacing = 1;
        writeLine("", properSpacing);
    }

    private static void readSourceFile() throws IOException {
        eofFlag = "YES";
        String line;

        while ((line = sourceFile.readLine()) != null) {
            eofFlag = "NO";
            if (line.length() >= 67) {
                parseRecord(line);
                constructData();
                lineNum++;

                if (lineNum == 55) {
                    newPage();
                    lineNum = 10;
                }
            }
        }
        eofFlag = "NO";
    }

    private static void parseRecord(String line) {
        try {
            catalogNum = line.substring(0, 5).trim();
            description = line.substring(5, 25).trim();
            String priceStr = line.substring(25, 31).trim();
            unitPurchasePrice = Double.parseDouble(priceStr) / 100.0;
            String qohStr = line.substring(38, 43).trim();
            quantityOnHand = Integer.parseInt(qohStr);
            String qooStr = line.substring(43, 48).trim();
            quantityOnOrder = Integer.parseInt(qooStr);
            String rpStr = line.substring(48, 53).trim();
            reorderPoint = Integer.parseInt(rpStr);
            String rwStr = line.substring(53, 57).trim();
            receivedWeekly = Integer.parseInt(rwStr);
            String swStr = line.substring(57, 61).trim();
            soldWeekly = Integer.parseInt(swStr);
            String retStr = line.substring(61, 65).trim();
            returnedWeekly = Integer.parseInt(retStr);
        } catch (Exception e) {
            catalogNum = "";
            description = "";
            unitPurchasePrice = 0;
            quantityOnHand = 0;
            quantityOnOrder = 0;
            reorderPoint = 0;
        }
    }

    private static void constructData() {
        String dlCatalogNumOut = String.format("%-5s", catalogNum);
        String dlDescriptionOut = String.format("%-20s", description);
        String dlUppOut = String.format("$%6.2f", unitPurchasePrice);
        String dlStockOut = String.format("%6.2f", (double) quantityOnHand);
        String dlOrderedOut = String.format("%6.2f", (double) quantityOnOrder);
        String dlReorderOut = String.format("%6.2f", (double) reorderPoint);

        String detailLine = dlCatalogNumOut + "   " + dlDescriptionOut + "   " + dlUppOut + "    " +
                dlStockOut + "      " + dlOrderedOut + "     " + dlReorderOut;

        properSpacing = 1;
        writeLine(detailLine, properSpacing);
    }

    private static void newPage() {
        hlPageNum++;
        properSpacing = 1;
        reportFile.println();
        reportFile.println();
        reportFile.println();
        reportFile.println();
        reportFile.println();
        reportFile.println();
        headingRoutine();
    }

    private static void writeLine(String line, int spacing) {
        for (int i = 0; i < spacing; i++) {
            reportFile.println(line);
        }
    }

    private static void closeRoutine() throws IOException {
        sourceFile.close();
        reportFile.close();
    }
}