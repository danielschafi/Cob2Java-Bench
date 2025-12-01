import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Lab9CallCenterOperatorReport {
    private static final int NUMBER_OF_MONTHS = 6;
    private static final String ZERO = "ZERO";
    private static final int NO_CALLS_TOTAL = 0;
    private static final int NO_CALLS_MONTH = 0;
    private static final int INCREMENT = 1;

    private int wsArrayIter = 1;
    private int wsCountMonth = 0;
    private int wsCountCalls = 0;
    private int wsOpAvg = 0;
    private int wsOpRem = 0;
    private int wsNonZeroMonthCount = 0;
    private boolean isEndOfFile = false;
    private int wsGrandTotal = 0;
    private int wsEmpTotal = 0;
    private int wsTotalNoCalls = 0;

    private BufferedReader inputFile;
    private PrintWriter reportFile;

    private String empRecNum;
    private String empRecName;
    private int[] empRecArray = new int[6];

    private String[] detailLineMonths = new String[6];
    private String detailLineNum;
    private String detailLineName;
    private int detailLineTotal;
    private String detailLineAvgText;
    private int detailLineAvg;
    private String detailLineRemText;
    private int detailLineRem;

    public static void main(String[] args) {
        Lab9CallCenterOperatorReport program = new Lab9CallCenterOperatorReport();
        program.run();
    }

    public void run() {
        try {
            openFiles();
            printHeadings();
            readInputFile();
            while (!isEndOfFile) {
                processRecords();
            }
            printTotals();
            closeFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFiles() throws IOException {
        inputFile = new BufferedReader(new FileReader("lab9.data"));
        reportFile = new PrintWriter(new FileWriter("lab9.out"));
    }

    private void closeFiles() throws IOException {
        inputFile.close();
        reportFile.close();
    }

    private void printHeadings() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        String nameLineDate = today.format(dateFormatter);
        String nameLineTime = now.format(timeFormatter);

        String nameLine = "  Henry Zheng, lab 9     " + nameLineDate + "     " + nameLineTime;
        reportFile.println(nameLine);

        String reportHeading = "                    call centre volumes for july - december";
        reportFile.println(reportHeading);
        reportFile.println();

        String headingLine1 = "  operator  operator      jul    aug    sep    oct    nov    dec    total    avg    rem";
        reportFile.println(headingLine1);

        String headingLine2 = "     #        name";
        reportFile.println(headingLine2);
    }

    private void readInputFile() throws IOException {
        String line = inputFile.readLine();
        if (line == null) {
            isEndOfFile = true;
        } else {
            parseRecord(line);
        }
    }

    private void parseRecord(String line) {
        if (line.length() < 35) {
            isEndOfFile = true;
            return;
        }
        empRecNum = line.substring(0, 3);
        empRecName = line.substring(3, 15);
        for (int i = 0; i < 6; i++) {
            String numStr = line.substring(15 + (i * 3), 15 + (i * 3) + 3);
            empRecArray[i] = Integer.parseInt(numStr.trim());
        }
    }

    private void processRecords() throws IOException {
        wsCountMonth = 0;
        wsEmpTotal = 0;
        wsCountCalls = 0;

        for (wsArrayIter = 0; wsArrayIter < NUMBER_OF_MONTHS; wsArrayIter++) {
            if (empRecArray[wsArrayIter] != NO_CALLS_MONTH) {
                wsCountMonth++;
            }
            wsEmpTotal += empRecArray[wsArrayIter];
            wsCountCalls += empRecArray[wsArrayIter];
            wsGrandTotal += empRecArray[wsArrayIter];
            detailLineMonths[wsArrayIter] = String.format("%7d", empRecArray[wsArrayIter]);
        }

        detailLineNum = empRecNum;
        detailLineName = empRecName;
        detailLineTotal = wsEmpTotal;

        if (wsEmpTotal == NO_CALLS_TOTAL) {
            detailLineAvgText = ZERO;
            detailLineRemText = " ";
            wsTotalNoCalls++;
        } else {
            wsOpAvg = wsCountCalls / wsCountMonth;
            wsOpRem = wsCountCalls % wsCountMonth;
            detailLineAvg = wsOpAvg;
            detailLineRem = wsOpRem;
            detailLineAvgText = String.format("%4d", wsOpAvg);
            detailLineRemText = String.valueOf(wsOpRem);
        }

        printDetailLine();
        readInputFile();
    }

    private void printDetailLine() {
        StringBuilder line = new StringBuilder();
        line.append("    ");
        line.append(String.format("%-3s", detailLineNum));
        line.append("      ");
        line.append(String.format("%-12s", detailLineName));
        line.append(" ");
        for (int i = 0; i < 6; i++) {
            line.append(detailLineMonths[i]);
        }
        line.append(" ");
        line.append(String.format("%3d", detailLineTotal));
        line.append("    ");
        line.append(String.format("%4s", detailLineAvgText));
        line.append("    ");
        line.append(detailLineRemText);

        reportFile.println();
        reportFile.println(line.toString());
    }

    private void printTotals() {
        reportFile.println();
        String totalLine1 = String.format("      number of operators with no calls: %2d", wsTotalNoCalls);
        reportFile.println(totalLine1);

        reportFile.println();
        String totalLine2 = String.format("      overall total calls:               %5d", wsGrandTotal);
        reportFile.println(totalLine2);
    }
}