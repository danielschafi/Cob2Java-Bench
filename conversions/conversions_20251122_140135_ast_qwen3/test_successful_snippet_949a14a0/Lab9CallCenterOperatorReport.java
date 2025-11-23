import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab9CallCenterOperatorReport {
    private static final String INPUT_FILE = "lab9.data";
    private static final String OUTPUT_FILE = "lab9.out";
    private static final int NUMBER_OF_MONTHS = 6;
    private static final int NO_CALLS_MONTH = 0;
    private static final int NO_CALLS_TOTAL = 0;
    private static final int INCREMENT = 1;

    private static class EmployeeRecord {
        String num;
        String name;
        int[] calls = new int[NUMBER_OF_MONTHS];
    }

    private static class ReportData {
        int grandTotal = 0;
        int totalNoCalls = 0;
        int countMonth = 0;
        int countCalls = 0;
        int empTotal = 0;
        int nonZeroMonthCount = 0;
        int opAvg = 0;
        int opRem = 0;
        boolean isEndOfFile = false;
        int arrayIter = 1;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {

            // Get current date and time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
            String currentDate = dateFormat.format(calendar.getTime());
            String currentTime = timeFormat.format(calendar.getTime());

            // Print headings
            printHeadings(writer, currentDate, currentTime);

            // Process records
            ReportData reportData = new ReportData();
            EmployeeRecord empRec = new EmployeeRecord();

            while (!reportData.isEndOfFile) {
                String line = reader.readLine();
                if (line == null) {
                    reportData.isEndOfFile = true;
                    break;
                }
                
                // Parse input line
                empRec.num = line.substring(0, 3);
                empRec.name = line.substring(3, 15).trim();
                for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
                    empRec.calls[i] = Integer.parseInt(line.substring(15 + i * 3, 18 + i * 3));
                }

                processRecord(writer, empRec, reportData);
            }

            // Print totals
            printTotals(writer, reportData);

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    private static void printHeadings(PrintWriter writer, String date, String time) {
        // Name line
        StringBuilder nameLine = new StringBuilder();
        nameLine.append("  ");
        nameLine.append("Henry Zheng, lab 9");
        nameLine.append("     ");
        nameLine.append(date);
        nameLine.append("     ");
        nameLine.append(time);
        writer.println(nameLine.toString());

        // Report heading
        writer.println("                                       call centre volumes for july - december");

        // Heading line 1
        writer.println("  operator    operator      jul     aug     sep     oct     nov     dec     total     avg     rem");
        
        // Heading line 2
        writer.println("       #        name");
    }

    private static void processRecord(PrintWriter writer, EmployeeRecord empRec, ReportData reportData) {
        // Reset fields for each record
        reportData.empTotal = 0;
        reportData.countMonth = 0;
        reportData.countCalls = 0;

        // Process monthly calls
        for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
            int callValue = empRec.calls[i];
            
            if (callValue != NO_CALLS_MONTH) {
                reportData.countMonth++;
            }
            
            reportData.empTotal += callValue;
            reportData.countCalls += callValue;
            reportData.grandTotal += callValue;
        }

        // Prepare detail line
        StringBuilder detailLine = new StringBuilder();
        detailLine.append("   ");
        detailLine.append(String.format("%3s", empRec.num));
        detailLine.append("      ");
        detailLine.append(String.format("%-12s", empRec.name));
        
        // Add monthly calls
        for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
            detailLine.append(" ");
            detailLine.append(String.format("%-7d", empRec.calls[i]));
        }
        
        // Add total
        detailLine.append(" ");
        detailLine.append(String.format("%3d", reportData.empTotal));
        
        // Calculate average and remainder
        if (reportData.empTotal == NO_CALLS_TOTAL) {
            detailLine.append("    ZERO");
            detailLine.append("    ");
            reportData.totalNoCalls += INCREMENT;
        } else {
            reportData.opAvg = reportData.countCalls / reportData.countMonth;
            reportData.opRem = reportData.countCalls % reportData.countMonth;
            
            detailLine.append(" ");
            detailLine.append(String.format("%4d", reportData.opAvg));
            detailLine.append(" ");
            detailLine.append(reportData.opRem);
        }
        
        writer.println(detailLine.toString());
        writer.println();
    }

    private static void printTotals(PrintWriter writer, ReportData reportData) {
        // Total line 1
        StringBuilder totalLine1 = new StringBuilder();
        totalLine1.append("      ");
        totalLine1.append("number of operators with no calls: ");
        totalLine1.append(String.format("%2d", reportData.totalNoCalls));
        writer.println(totalLine1.toString());
        writer.println();
        
        // Total line 2
        StringBuilder totalLine2 = new StringBuilder();
        totalLine2.append("      ");
        totalLine2.append("overall total calls:");
        totalLine2.append("               ");
        totalLine2.append(String.format("%5d", reportData.grandTotal));
        writer.println(totalLine2.toString());
        writer.println();
    }
}