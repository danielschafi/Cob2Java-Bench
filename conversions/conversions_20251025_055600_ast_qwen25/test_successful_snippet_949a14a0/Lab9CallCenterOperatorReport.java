import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab9CallCenterOperatorReport {
    private static final int WS_NUMBER_OF_MONTHS = 6;
    private static final String WS_ZERO = "ZERO";
    private static final int WS_NO_CALLS_TOTAL = 0;
    private static final int WS_NO_CALLS_MONTH = 0;
    private static final int WS_INCREMENT = 1;

    private int wsArrayIter = 1;
    private int wsCountMonth = 0;
    private int wsCountCalls = 0;
    private int wsOpAvg = 0;
    private int wsOpRem = 0;
    private int wsNonZeroMonthCount = 0;
    private char foundEof = 'n';
    private int wsGrandTotal = 0;
    private int wsEmpTotal = 0;
    private int wsTotalNoCalls = 0;

    private String nameLineDate;
    private String nameLineTime;

    private String[] empRecArray = new String[WS_NUMBER_OF_MONTHS];
    private String[] detailLineMonths = new String[WS_NUMBER_OF_MONTHS];

    public static void main(String[] args) {
        Lab9CallCenterOperatorReport report = new Lab9CallCenterOperatorReport();
        report.processReport();
    }

    private void processReport() {
        try (BufferedReader inputFile = new BufferedReader(new FileReader("lab9.data"));
             BufferedWriter reportFile = new BufferedWriter(new FileWriter("lab9.out"))) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

            nameLineDate = dateFormat.format(new Date());
            nameLineTime = timeFormat.format(new Date());

            printHeadings(reportFile);
            readInputFile(inputFile);
            while (foundEof != 'y') {
                processRecords();
                readInputFile(inputFile);
            }
            printTotals(reportFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printHeadings(BufferedWriter reportFile) throws IOException {
        reportFile.write(String.format("%-2s%-29s%-5s%-6s%-5s%-8s%n", "", "Henry Zheng, lab 9", "", nameLineDate, "", nameLineTime));
        reportFile.newLine();
        reportFile.write(String.format("%39s%n", "call centre volumes for july - december"));
        reportFile.newLine();
        reportFile.newLine();
        reportFile.write(String.format("%-2s%-8s%-2s%-8s%-6s%-3s%-4s%-3s%-4s%-3s%-4s%-3s%-4s%-3s%-5s%-4s%-3s%-4s%-3s%n", "", "operator", "", "operator", "", "jul", "", "aug", "", "sep", "", "oct", "", "nov", "", "dec", "", "total", "", "avg", "", "rem"));
        reportFile.newLine();
        reportFile.write(String.format("%5s%-1s%-8s%-4s%n", "", "#", "", "name"));
        reportFile.newLine();
    }

    private void readInputFile(BufferedReader inputFile) throws IOException {
        String line = inputFile.readLine();
        if (line == null) {
            foundEof = 'y';
        } else {
            empRecArray[0] = line.substring(0, 3);
            empRecArray[1] = line.substring(3, 15);
            for (int i = 0; i < WS_NUMBER_OF_MONTHS; i++) {
                empRecArray[i] = line.substring(15 + i * 3, 18 + i * 3);
            }
        }
    }

    private void processRecords() {
        wsCountMonth = 0;
        wsEmpTotal = 0;
        wsCountCalls = 0;

        for (wsArrayIter = 0; wsArrayIter < WS_NUMBER_OF_MONTHS; wsArrayIter++) {
            int calls = Integer.parseInt(empRecArray[wsArrayIter]);
            if (calls != WS_NO_CALLS_MONTH) {
                wsCountMonth++;
            }
            wsEmpTotal += calls;
            wsCountCalls += calls;
            wsGrandTotal += calls;
            detailLineMonths[wsArrayIter] = String.format("%3s", calls);
        }

        String detailLineNum = empRecArray[0];
        String detailLineName = empRecArray[1];
        String detailLineTotal = String.format("%3d", wsEmpTotal);

        String detailLineAvgText;
        String detailLineRemText;
        if (wsEmpTotal == WS_NO_CALLS_TOTAL) {
            detailLineAvgText = WS_ZERO;
            detailLineRemText = " ";
            wsTotalNoCalls++;
        } else {
            wsOpAvg = wsCountCalls / wsCountMonth;
            wsOpRem = wsCountCalls % wsCountMonth;
            detailLineAvgText = String.format("%3d", wsOpAvg);
            detailLineRemText = String.format("%1d", wsOpRem);
        }

        try (BufferedWriter reportFile = new BufferedWriter(new FileWriter("lab9.out", true))) {
            reportFile.write(String.format("%4s%-3s%-6s%-12s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-5s%-4s%-3s%-4s%-3s%n", "", detailLineNum, "", detailLineName, "", detailLineMonths[0], "", detailLineMonths[1], "", detailLineMonths[2], "", detailLineMonths[3], "", detailLineMonths[4], "", detailLineMonths[5], "", detailLineTotal, "", detailLineAvgText, "", detailLineRemText));
            reportFile.newLine();
            reportFile.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTotals(BufferedWriter reportFile) throws IOException {
        reportFile.write(String.format("%21s%n", "Total number of calls"));
        reportFile.newLine();
        reportFile.write(String.format("%6s%-35s%-1d%n", "", "number of operators with no calls: ", wsTotalNoCalls));
        reportFile.newLine();
        reportFile.write(String.format("%6s%-20s%-15s%-5d%n", "", "overall total calls:", "", wsGrandTotal));
        reportFile.newLine();
    }
}