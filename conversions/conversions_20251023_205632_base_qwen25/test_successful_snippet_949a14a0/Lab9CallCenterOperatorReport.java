import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab9CallCenterOperatorReport {
    private static final int WS_NUMBER_OF_MONTHS = 6;
    private static final String WS_ZERO = "ZERO";
    private static final int WS_NO_CALLS_TOTAL = 0;
    private static final int WS_NO_CALLS_MONTH = 0;
    private static final int WS_INCREMENT = 1;

    private static int wsArrayIter = 1;
    private static int wsCountMonth = 0;
    private static int wsCountCalls = 0;
    private static int wsOpAvg = 0;
    private static int wsOpRem = 0;
    private static int wsNonZeroMonthCount = 0;
    private static char foundEof = 'n';
    private static int wsGrandTotal = 0;
    private static int wsEmpTotal = 0;
    private static int wsTotalNoCalls = 0;

    private static final String NAME_LINE_DATE_FORMAT = "yyMMdd";
    private static final String NAME_LINE_TIME_FORMAT = "HHmmss";

    public static void main(String[] args) {
        BufferedReader inputFile = null;
        BufferedWriter reportFile = null;

        try {
            inputFile = new BufferedReader(new FileReader("lab9.data"));
            reportFile = new BufferedWriter(new FileWriter("lab9.out"));

            String nameLineDate = new SimpleDateFormat(NAME_LINE_DATE_FORMAT).format(new Date());
            String nameLineTime = new SimpleDateFormat(NAME_LINE_TIME_FORMAT).format(new Date());

            printHeadings(reportFile, nameLineDate, nameLineTime);

            String empRec;
            while ((empRec = inputFile.readLine()) != null) {
                processRecord(reportFile, empRec);
            }

            printTotals(reportFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputFile != null) inputFile.close();
                if (reportFile != null) reportFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printHeadings(BufferedWriter reportFile, String nameLineDate, String nameLineTime) throws IOException {
        reportFile.write(String.format("%-2s%-29s%-5s%-6s%-5s%-8s%n", "", "Henry Zheng, lab 9", "", nameLineDate, "", nameLineTime));
        reportFile.newLine();
        reportFile.write(String.format("%-20s%-39s%n", "", "call centre volumes for july - december"));
        reportFile.newLine();
        reportFile.newLine();
        reportFile.write(String.format("%-2s%-8s%-2s%-8s%-6s%-3s%-4s%-3s%-4s%-3s%-4s%-3s%-4s%-3s%-4s%-5s%-4s%-3s%-4s%-3s%n", "", "operator", "", "operator", "", "jul", "", "aug", "", "sep", "", "oct", "", "nov", "", "dec", "", "total", "", "avg", "", "rem"));
        reportFile.newLine();
        reportFile.write(String.format("%-5s%-1s%-8s%-4s%-4s%n", "", "#", "", "name", ""));
        reportFile.newLine();
    }

    private static void processRecord(BufferedWriter reportFile, String empRec) throws IOException {
        String empRecNum = empRec.substring(0, 3).trim();
        String empRecName = empRec.substring(3, 15).trim();
        int[] empRecArray = new int[WS_NUMBER_OF_MONTHS];
        for (int i = 0; i < WS_NUMBER_OF_MONTHS; i++) {
            empRecArray[i] = Integer.parseInt(empRec.substring(15 + i * 3, 18 + i * 3).trim());
        }

        wsCountMonth = 0;
        wsEmpTotal = 0;
        wsCountCalls = 0;

        for (wsArrayIter = 0; wsArrayIter < WS_NUMBER_OF_MONTHS; wsArrayIter++) {
            if (empRecArray[wsArrayIter] != WS_NO_CALLS_MONTH) {
                wsCountMonth++;
            }
            wsEmpTotal += empRecArray[wsArrayIter];
            wsCountCalls += empRecArray[wsArrayIter];
            wsGrandTotal += empRecArray[wsArrayIter];
        }

        reportFile.write(String.format("%-4s%-3s%-6s%-12s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-7s%-1s%-5s%-4s%-4s%-4s%n", "", empRecNum, "", empRecName, "", empRecArray[0], "", empRecArray[1], "", empRecArray[2], "", empRecArray[3], "", empRecArray[4], "", empRecArray[5], "", wsEmpTotal, "", wsEmpTotal == WS_NO_CALLS_TOTAL ? WS_ZERO : String.format("%04d", wsEmpTotal / wsCountMonth), "", wsEmpTotal == WS_NO_CALLS_TOTAL ? " " : String.valueOf(wsEmpTotal % wsCountMonth)));
        reportFile.newLine();
        reportFile.newLine();

        if (wsEmpTotal == WS_NO_CALLS_TOTAL) {
            wsTotalNoCalls += WS_INCREMENT;
        }
    }

    private static void printTotals(BufferedWriter reportFile) throws IOException {
        reportFile.write(String.format("%-6s%-35s%-3s%n", "", "number of operators with no calls: ", wsTotalNoCalls));
        reportFile.newLine();
        reportFile.write(String.format("%-6s%-20s%-15s%-5s%n", "", "overall total calls:", "", wsGrandTotal));
        reportFile.newLine();
        reportFile.newLine();
    }
}