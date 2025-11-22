import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lab9CallCenterOperatorReport {
    
    static class EmployeeRecord {
        String empRecNum;
        String empRecName;
        int[] empRecArray;
        
        EmployeeRecord() {
            empRecNum = "";
            empRecName = "";
            empRecArray = new int[6];
        }
    }
    
    static int wsNumberOfMonths = 6;
    static String wsZero = "ZERO";
    static int wsNoCallsTotal = 0;
    static int wsNoCallsMonth = 0;
    static int wsIncrement = 1;
    
    static int wsArrayIter = 1;
    static int wsCountMonth = 0;
    static int wsCountCalls = 0;
    static int wsOpAvg = 0;
    static int wsOpRem = 0;
    
    static int wsNonZeroMonthCount = 0;
    static boolean isEndOfFile = false;
    
    static int wsGrandTotal = 0;
    static int wsEmpTotal = 0;
    static int wsTotalNoCalls = 0;
    
    static String nameLine = "";
    static String reportHeading = "";
    static String headingLine1 = "";
    static String headingLine2 = "";
    static String detailLine = "";
    static String totalLine1 = "";
    static String totalLine2 = "";
    
    static BufferedReader inputFile;
    static PrintWriter reportFile;
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader("lab9.data"));
            reportFile = new PrintWriter(new FileWriter("lab9.out"));
            
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");
            
            String dateStr = currentDate.format(dateFormatter);
            String timeStr = currentTime.format(timeFormatter);
            
            printHeadings(dateStr, timeStr);
            
            readInputFile();
            while (!isEndOfFile) {
                processRecords();
            }
            
            printTotals();
            
            inputFile.close();
            reportFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void printHeadings(String dateStr, String timeStr) {
        String nameLine = String.format("  Henry Zheng, lab 9                %s     %s", dateStr, timeStr);
        reportFile.println(nameLine);
        
        String reportHeading = String.format("%20s%s", "", "call centre volumes for july - december");
        reportFile.println(reportHeading);
        reportFile.println();
        
        String headingLine1 = "  operator  operator      jul    aug    sep    oct    nov    dec    total    avg    rem";
        reportFile.println(headingLine1);
        
        String headingLine2 = "     #        name";
        reportFile.println(headingLine2);
    }
    
    static void readInputFile() {
        try {
            String line = inputFile.readLine();
            if (line == null) {
                isEndOfFile = true;
            }
        } catch (IOException e) {
            isEndOfFile = true;
        }
    }
    
    static void processRecords() {
        try {
            String line = inputFile.readLine();
            if (line == null) {
                isEndOfFile = true;
                return;
            }
            
            EmployeeRecord empRec = parseRecord(line);
            
            wsCountMonth = 0;
            wsEmpTotal = 0;
            wsCountCalls = 0;
            
            for (int i = 0; i < wsNumberOfMonths; i++) {
                if (empRec.empRecArray[i] != wsNoCallsMonth) {
                    wsCountMonth++;
                }
                wsEmpTotal += empRec.empRecArray[i];
                wsCountCalls += empRec.empRecArray[i];
                wsGrandTotal += empRec.empRecArray[i];
            }
            
            StringBuilder detailLineBuilder = new StringBuilder();
            detailLineBuilder.append(String.format("    %s      %s", empRec.empRecNum, empRec.empRecName));
            
            for (int i = 0; i < wsNumberOfMonths; i++) {
                detailLineBuilder.append(String.format(" %3d  ", empRec.empRecArray[i]));
            }
            
            detailLineBuilder.append(String.format(" %3d    ", wsEmpTotal));
            
            if (wsEmpTotal == wsNoCallsTotal) {
                detailLineBuilder.append(String.format("%s    ", wsZero));
                wsTotalNoCalls++;
            } else {
                wsOpAvg = wsCountCalls / wsCountMonth;
                wsOpRem = wsCountCalls % wsCountMonth;
                detailLineBuilder.append(String.format("%4d    %d", wsOpAvg, wsOpRem));
            }
            
            reportFile.println();
            reportFile.println(detailLineBuilder.toString());
            
        } catch (IOException e) {
            isEndOfFile = true;
        }
    }
    
    static EmployeeRecord parseRecord(String line) {
        EmployeeRecord rec = new EmployeeRecord();
        
        if (line.length() >= 3) {
            rec.empRecNum = line.substring(0, 3);
        }
        if (line.length() >= 15) {
            rec.empRecName = line.substring(3, 15);
        }
        
        for (int i = 0; i < 6; i++) {
            int startIdx = 15 + (i * 3);
            int endIdx = startIdx + 3;
            if (endIdx <= line.length()) {
                try {
                    rec.empRecArray[i] = Integer.parseInt(line.substring(startIdx, endIdx).trim());
                } catch (NumberFormatException e) {
                    rec.empRecArray[i] = 0;
                }
            }
        }
        
        return rec;
    }
    
    static void printTotals() {
        reportFile.println();
        reportFile.println(String.format("      number of operators with no calls: %2d", wsTotalNoCalls));
        reportFile.println();
        reportFile.println(String.format("      overall total calls:        %5d", wsGrandTotal));
    }
}