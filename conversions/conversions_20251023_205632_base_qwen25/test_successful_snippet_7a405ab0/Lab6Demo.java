import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab6Demo {
    private static final String INPUT_FILE = "lab8.dat";
    private static final String G_OUTPUT_FILE = "G-lab8.out";
    private static final String N_OUTPUT_FILE = "N-lab8.out";
    private static final int EMP_LIMIT = 14;

    private static String wsEofFlag = "n";
    private static String wsResetFlag = "n";
    private static String wsNoReset = "n";
    private static String wsYesReset = "y";
    private static String wsNewLine = " ";
    private static String wsNameLine;
    private static String wsGrptRptHeading1;
    private static String wsNgrptRptHeading1;
    private static String wsRptHeading2;
    private static String wsHeadingLine1;
    private static String wsHeadingLine2;
    private static String wsGSubEmpL1;
    private static String wsNSubEmpL1;
    private static String wsGSubEmpL2;
    private static String wsNSubEmpL2;
    private static String wsGAvgTotalL1;
    private static String wsGAvgTotalL2;
    private static String wsNAvgTotalLine;
    private static int wsPageCount = 1;
    private static int wsLineCount = 0;
    private static int wsAnalystStotal = 0;
    private static int wsSprogStotal = 0;
    private static int wsProgStotal = 0;
    private static int wsJrprogStotal = 0;
    private static int wsUnclassStotal = 0;
    private static int wsAnalystCount = 0;
    private static int wsSprogCount = 0;
    private static int wsProgCount = 0;
    private static int wsJrprogCount = 0;
    private static double wsAnalystIncTotal = 0.0;
    private static double wsSprogIncTotal = 0.0;
    private static double wsProgIncTotal = 0.0;
    private static double wsJrprogIncTotal = 0.0;
    private static double wsAnalystAvgFinal = 0.0;
    private static double wsSprogAvgFinal = 0.0;
    private static double wsProgAvgFinal = 0.0;
    private static double wsJrprogAvgFinal = 0.0;
    private static double wsIncreaseAnalystCnst = 0.118;
    private static double wsIncreaseSprogCnst = 0.083;
    private static double wsIncreaseProgCnst = 0.067;
    private static double wsIncreaseJrprogCnst = 0.042;
    private static int wsPercConvertCnst = 100;
    private static String wsPercSignCnst = "%";
    private static String wsPlusSignCnst = "+";
    private static String wsGrads = "G";
    private static String wsNonGrads = "N";
    private static String wsClassAnalystCnst = "ANALYST";
    private static String wsClassSprogCnst = "SEN PROG";
    private static String wsClassProgCnst = "PROG";
    private static String wsClassJrprogCnst = "JR PROG";
    private static int wsAnalystGtCnst = 15;
    private static int wsSprogLteCnst = 15;
    private static int wsSprogGteCnst = 7;
    private static int wsGProgGtCnst = 2;
    private static int wsGProgLtCnst = 7;
    private static int wsGUnclassLteCnst = 2;
    private static int wsNProgGtCnst = 10;
    private static int wsNJrprogLteCnst = 10;
    private static int wsNJrprogGtCnst = 4;
    private static int wsNUnclassLteCnst = 4;
    private static int wsIncOneCnst = 1;
    private static int wsResetZeroCnst = 0;

    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter gWriter = null;
        BufferedWriter nWriter = null;
        try {
            reader = new BufferedReader(new FileReader(INPUT_FILE));
            gWriter = new BufferedWriter(new FileWriter(G_OUTPUT_FILE));
            nWriter = new BufferedWriter(new FileWriter(N_OUTPUT_FILE));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
            String nlDate = dateFormat.format(new Date());
            String nlTime = timeFormat.format(new Date());

            wsNameLine = String.format("%5s%20s%6s%5s%8s", "LAB 8", " ", nlDate, " ", nlTime);
            wsGrptRptHeading1 = String.format("%20s%8s", " ", "GRADUATE");
            wsNgrptRptHeading1 = String.format("%20s%12s", " ", "NON-GRADUATE");
            wsRptHeading2 = String.format("%18s%22s%26s%6s%1d", " ", "EMPLOYEE SALARY REPORT", " ", "PAGE  ", wsPageCount);
            wsHeadingLine1 = String.format("%3s%2s%3s%30s%7s%2s%8s%6s%3s%9s%4s", "EMP", " ", "EMP", " ", " ", "PRESENT", " ", "INCREASE", " ", "PAY", " ", "NEW", " ");
            wsHeadingLine2 = String.format("%3s%2s%4s%10s%5s%2s%8s%4s%6s%7s%1s%6s%8s%5s%6s", "NUM", " ", "NAME", " ", "YEARS", " ", "POSITION", " ", "SALARY", " ", "%", " ", "INCREASE", " ", "SALARY");
            wsGSubEmpL1 = String.format("%15s%8s%7s%4s%8s%4s%4s%4s%12s", "EMPLOYEE CLASS:", " ", "Analyst", " ", "Sen Prog", " ", "Prog", " ", "Unclassified");
            wsNSubEmpL1 = String.format("%15s%8s%4s%4s%7s%4s%12s", "EMPLOYEE CLASS:", " ", "Prog", " ", "Jr Prog", " ", "Unclassified");
            wsGSubEmpL2 = String.format("%15s%13s%1d%10s%1d%6s%1d%14s%1d", "# ON THIS PAGE:", " ", 0, " ", 0, " ", 0, " ", 0);
            wsNSubEmpL2 = String.format("%15s%10s%1d%9s%1d%14s%1d", "# ON THIS PAGE:", " ", 0, " ", 0, " ", 0);
            wsGAvgTotalL1 = String.format("%18s%3s%8s%9.2f%6s%9s%9.2f", "AVERAGE INCREASES:", " ", "ANALYST=", 0.0, " ", "SEN PROG=", 0.0);
            wsGAvgTotalL2 = String.format("%24s%5s%9.2f", " ", "PROG=", 0.0);
            wsNAvgTotalLine = String.format("%18s%3s%5s%9.2f%7s%8s%9.2f", "AVERAGE INCREASES:", " ", "PROG=", 0.0, " ", "JR PROG=", 0.0);

            gWriter.write(wsNameLine);
            gWriter.newLine();
            gWriter.write(wsNewLine);
            gWriter.write(wsGrptRptHeading1);
            gWriter.newLine();
            gWriter.write(wsRptHeading2);
            gWriter.newLine();
            gWriter.write(wsNewLine);
            gWriter.write(wsHeadingLine1);
            gWriter.newLine();
            gWriter.write(wsHeadingLine2);
            gWriter.newLine();
            gWriter.write(wsNewLine);

            String line;
            while ((line = reader.readLine()) != null) {
                String ilEmpNum = line.substring(0, 3).trim();
                String ilName = line.substring(3, 18).trim();
                String ilEduCode = line.substring(18, 19).trim();
                int ilYears = Integer.parseInt(line.substring(19, 21).trim());
                double ilCurSalary = Double.parseDouble(line.substring(21, 28).trim());

                if (ilEduCode.equals(wsGrads)) {
                    String wsOutput = String.format("%3s%2s%-15s%2s%1d%2s%-10s%4s%8.2f%2s%1s%6.2f%2s%8.2f", ilEmpNum, " ", ilName, " ", ilYears, " ", " ", " ",