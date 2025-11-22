import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab9CallCenterOperatorReport {
    
    private static final int NUMBER_OF_MONTHS = 6;
    private static final String ZERO_TEXT = "ZERO";
    private static final int NO_CALLS_TOTAL = 0;
    private static final int NO_CALLS_MONTH = 0;
    
    private int grandTotal = 0;
    private int totalNoCallsOperators = 0;
    
    public static void main(String[] args) {
        Lab9CallCenterOperatorReport report = new Lab9CallCenterOperatorReport();
        report.run();
    }
    
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("lab9.data"));
             PrintWriter writer = new PrintWriter(new FileWriter("lab9.out"))) {
            
            printHeadings(writer);
            
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line, writer);
            }
            
            printTotals(writer);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void printHeadings(PrintWriter writer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
        Date now = new Date();
        
        String nameLine = "  Henry Zheng, lab 9     " + dateFormat.format(now) + "     " + timeFormat.format(now);
        writer.println(nameLine);
        
        writer.println();
        writer.println("                    call centre volumes for july - december");
        
        writer.println();
        writer.println("  operator  operator      jul    aug    sep    oct    nov    dec    total    avg    rem");
        writer.println("     #        name");
    }
    
    private void processRecord(String record, PrintWriter writer) {
        String empNum = record.substring(0, 3);
        String empName = record.substring(3, 15);
        int[] monthlyCalls = new int[NUMBER_OF_MONTHS];
        
        for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
            int startPos = 15 + (i * 3);
            monthlyCalls[i] = Integer.parseInt(record.substring(startPos, startPos + 3));
        }
        
        int empTotal = 0;
        int countMonth = 0;
        int countCalls = 0;
        
        for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
            if (monthlyCalls[i] != NO_CALLS_MONTH) {
                countMonth++;
            }
            empTotal += monthlyCalls[i];
            countCalls += monthlyCalls[i];
        }
        
        grandTotal += empTotal;
        
        StringBuilder detailLine = new StringBuilder();
        detailLine.append("    ");
        detailLine.append(empNum);
        detailLine.append("      ");
        detailLine.append(empName);
        detailLine.append(" ");
        
        for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
            detailLine.append(String.format("%3d", monthlyCalls[i]));
            detailLine.append("    ");
        }
        
        detailLine.append(String.format("%3d", empTotal));
        detailLine.append("    ");
        
        if (empTotal == NO_CALLS_TOTAL) {
            detailLine.append(ZERO_TEXT);
            detailLine.append("    ");
            detailLine.append(" ");
            totalNoCallsOperators++;
        } else {
            int avg = countCalls / countMonth;
            int rem = countCalls % countMonth;
            detailLine.append(String.format("%4d", avg));
            detailLine.append("    ");
            detailLine.append(rem);
        }
        
        writer.println();
        writer.println();
        writer.println(detailLine.toString());
    }
    
    private void printTotals(PrintWriter writer) {
        writer.println();
        writer.println();
        writer.println("      number of operators with no calls: " + String.format("%2d", totalNoCallsOperators));
        
        writer.println();
        writer.println();
        writer.println("      overall total calls:               " + String.format("%5d", grandTotal));
    }
}