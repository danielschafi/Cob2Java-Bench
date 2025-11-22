import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab9CallCenterOperatorReport {
    private static final String INPUT_FILE = "lab9.data";
    private static final String OUTPUT_FILE = "lab9.out";
    
    private static class EmpRec {
        String empRecNum;
        String empRecName;
        int[] empRecArray = new int[6];
    }
    
    private static class PrintLine {
        String content = new String(new char[132]);
    }
    
    private static class WsConstants {
        int wsNumberOfMonths = 6;
        String wsZero = "ZERO";
        int wsNoCallsTotal = 0;
        int wsNoCallsMonth = 0;
        int wsIncrement = 1;
    }
    
    private static class WsArrayIter {
        int value = 1;
    }
    
    private static class WsCountMonth {
        int value = 0;
    }
    
    private static class WsCountCalls {
        int value = 0;
    }
    
    private static class WsOpAvg {
        int value = 0;
    }
    
    private static class WsOpRem {
        int value = 0;
    }
    
    private static class WsCalculatedFields {
        int wsNonZeroMonthCount = 0;
    }
    
    private static class FoundEof {
        char value = 'n';
        boolean isEndOfFile() { return value == 'y'; }
    }
    
    private static class WsTotals {
        int wsGrandTotal = 0;
        int wsEmpTotal = 0;
        int wsTotalNoCalls = 0;
    }
    
    private static class NameLine {
        String filler1 = "  ";
        String filler2 = "Henry Zheng, lab 9";
        String filler3 = "     ";
        int nameLineDate;
        String filler4 = "     ";
        int nameLineTime;
    }
    
    private static class ReportHeading {
        String filler1 = "                    ";
        String filler2 = "call centre volumes for july - december";
    }
    
    private static class HeadingLine1 {
        String filler1 = "  ";
        String filler2 = "operator";
        String filler3 = "  ";
        String filler4 = "operator";
        String filler5 = "      ";
        String filler6 = "jul";
        String filler7 = "   ";
        String filler8 = "aug";
        String filler9 = "   ";
        String filler10 = "sep";
        String filler11 = "   ";
        String filler12 = "oct";
        String filler13 = "   ";
        String filler14 = "nov";
        String filler15 = "   ";
        String filler16 = "dec";
        String filler17 = "   ";
        String filler18 = "total";
        String filler19 = "   ";
        String filler20 = "avg";
        String filler21 = "   ";
        String filler22 = "rem";
    }
    
    private static class HeadingLine2 {
        String filler1 = "     ";
        String filler2 = "#";
        String filler3 = "        ";
        String filler4 = "name";
    }
    
    private static class DetailLine {
        String filler1 = "    ";
        String detailLineNum = "   ";
        String filler2 = "      ";
        String detailLineName = "            ";
        String filler3 = " ";
        String[] detailLineMonths = new String[6];
        String filler4 = " ";
        String detailLineTotal = "  0";
        String filler5 = "   ";
        String detailLineAvg = "0000";
        String detailLineRem = "0";
    }
    
    private static class MonthlyTotal {
        String filler1 = "Total number of calls";
    }
    
    private static class TotalLine1 {
        String filler1 = "      ";
        String filler2 = "number of operators with no calls: ";
        String totalLineNoCalls = " 0";
    }
    
    private static class TotalLine2 {
        String filler1 = "      ";
        String filler2 = "overall total calls:";
        String filler3 = "               ";
        String totalLineCalls = " 00000";
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            PrintWriter outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            WsConstants wsConstants = new WsConstants();
            WsArrayIter wsArrayIter = new WsArrayIter();
            WsCountMonth wsCountMonth = new WsCountMonth();
            WsCountCalls wsCountCalls = new WsCountCalls();
            WsOpAvg wsOpAvg = new WsOpAvg();
            WsOpRem wsOpRem = new WsOpRem();
            WsCalculatedFields wsCalculatedFields = new WsCalculatedFields();
            FoundEof foundEof = new FoundEof();
            WsTotals wsTotals = new WsTotals();
            NameLine nameLine = new NameLine();
            ReportHeading reportHeading = new ReportHeading();
            HeadingLine1 headingLine1 = new HeadingLine1();
            HeadingLine2 headingLine2 = new HeadingLine2();
            DetailLine detailLine = new DetailLine();
            TotalLine1 totalLine1 = new TotalLine1();
            TotalLine2 totalLine2 = new TotalLine2();
            
            // Get current date and time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
            nameLine.nameLineDate = Integer.parseInt(dateFormat.format(calendar.getTime()));
            nameLine.nameLineTime = Integer.parseInt(timeFormat.format(calendar.getTime()));
            
            // Print headings
            printHeadings(outputFile, nameLine, reportHeading, headingLine1, headingLine2);
            
            // Process records
            String line;
            while ((line = inputFile.readLine()) != null && !foundEof.isEndOfFile()) {
                EmpRec empRec = new EmpRec();
                
                if (line.length() >= 3) {
                    empRec.empRecNum = line.substring(0, 3);
                } else {
                    empRec.empRecNum = line;
                }
                
                if (line.length() >= 15) {
                    empRec.empRecName = line.substring(3, 15);
                } else {
                    empRec.empRecName = line.substring(3);
                }
                
                for (int i = 0; i < 6; i++) {
                    if (line.length() >= 18 + i * 3) {
                        String numStr = line.substring(15 + i * 3, 18 + i * 3);
                        try {
                            empRec.empRecArray[i] = Integer.parseInt(numStr);
                        } catch (NumberFormatException e) {
                            empRec.empRecArray[i] = 0;
                        }
                    } else {
                        empRec.empRecArray[i] = 0;
                    }
                }
                
                processRecords(empRec, wsConstants, wsArrayIter, wsCountMonth, wsCountCalls, 
                              wsOpAvg, wsOpRem, wsCalculatedFields, wsTotals, detailLine, 
                              outputFile);
            }
            
            // Print totals
            totalLine1.totalLineNoCalls = String.format("%2d", wsTotals.wsTotalNoCalls);
            totalLine2.totalLineCalls = String.format("%5d", wsTotals.wsGrandTotal);
            
            outputFile.println(totalLine1.filler1 + totalLine1.filler2 + totalLine1.totalLineNoCalls);
            outputFile.println();
            outputFile.println(totalLine2.filler1 + totalLine2.filler2 + totalLine2.filler3 + totalLine2.totalLineCalls);
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static void printHeadings(PrintWriter outputFile, NameLine nameLine, 
                                     ReportHeading reportHeading, HeadingLine1 headingLine1, 
                                     HeadingLine2 headingLine2) {
        String nameLineContent = nameLine.filler1 + nameLine.filler2 + nameLine.filler3 + 
                                String.format("%06d", nameLine.nameLineDate) + nameLine.filler4 + 
                                String.format("%08d", nameLine.nameLineTime) + nameLine.filler4;
        outputFile.println(nameLineContent);
        
        outputFile.println(reportHeading.filler1 + reportHeading.filler2);
        outputFile.println();
        outputFile.println(headingLine1.filler1 + headingLine1.filler2 + headingLine1.filler3 + 
                         headingLine1.filler4 + headingLine1.filler5 + headingLine1.filler6 + 
                         headingLine1.filler7 + headingLine1.filler8 + headingLine1.filler9 + 
                         headingLine1.filler10 + headingLine1.filler11 + headingLine1.filler12 + 
                         headingLine1.filler13 + headingLine1.filler14 + headingLine1.filler15 + 
                         headingLine1.filler16 + headingLine1.filler17 + headingLine1.filler18 + 
                         headingLine1.filler19 + headingLine1.filler20 + headingLine1.filler21 + 
                         headingLine1.filler22);
        outputFile.println();
        outputFile.println(headingLine2.filler1 + headingLine