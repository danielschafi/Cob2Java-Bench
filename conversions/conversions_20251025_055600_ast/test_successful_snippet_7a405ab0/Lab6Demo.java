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
    private static final String wsNoReset = "n";
    private static final String wsYesReset = "y";
    private static final String wsNewLine = " ";
    private static String wsNameLine;
    private static String wsGrRptHeading1;
    private static String wsNrRptHeading1;
    private static String wsRptHeading2;
    private static String wsHeadingLine1;
    private static String wsHeadingLine2;
    private static String wsGSubEmpL1;
    private static String wsNSubEmpL1;
    private static String wsGSubEmpL2;
    private static String wsNSubEmpL2;
    private static int wsAnalystStotal = 0;
    private static int wsSprogStotal = 0;
    private static int wsProgStotal = 0;
    private static int wsJrprogStotal = 0;
    private static int wsUnclassStotal = 0;
    private static int wsAnalystCount = 0;
    private static int wsSprogCount = 0;
    private static int wsProgCount = 0;
    private static int wsJrprogCount = 0;
    private static String wsGAvgTotalL1;
    private static String wsGAvgTotalL2;
    private static String wsNAvgTotalLine;
    private static double wsAnalystIncTotal = 0.0;
    private static double wsSprogIncTotal = 0.0;
    private static double wsProgIncTotal = 0.0;
    private static double wsJrprogIncTotal = 0.0;
    private static String wsOutput;
    private static int wsLineCount = 0;
    private static final double wsIncreaseAnalystCnst = 0.118;
    private static final double wsIncreaseSprogCnst = 0.083;
    private static final double wsIncreaseProgCnst = 0.067;
    private static final double wsIncreaseJrprogCnst = 0.042;
    private static final int wsPercConvertCnst = 100;
    private static final String wsPercSignCnst = "%";
    private static final String wsPlusSignCnst = "+";
    private static final String wsGrads = "G";
    private static final String wsNonGrads = "N";
    private static final String wsClassAnalystCnst = "ANALYST";
    private static final String wsClassSprogCnst = "SEN PROG";
    private static final String wsClassProgCnst = "PROG";
    private static final String wsClassJrprogCnst = "JR PROG";
    private static final int wsAnalystGtCnst = 15;
    private static final int wsSprogLteCnst = 15;
    private static final int wsSprogGteCnst = 7;
    private static final int wsGProgGtCnst = 2;
    private static final int wsGProgLtCnst = 7;
    private static final int wsGUnclassLteCnst = 2;
    private static final int wsNProgGtCnst = 10;
    private static final int wsNJrprogLteCnst = 10;
    private static final int wsNJrprogGtCnst = 4;
    private static final int wsNUnclassLteCnst = 4;
    private static final int wsIncOneCnst = 1;
    private static final int wsResetZeroCnst = 0;
    private static int wsPageCount = 1;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter gWriter = new BufferedWriter(new FileWriter(G_OUTPUT_FILE));
             BufferedWriter nWriter = new BufferedWriter(new FileWriter(N_OUTPUT_FILE))) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
            String nlDate = dateFormat.format(new Date());
            String nlTime = timeFormat.format(new Date());

            wsNameLine = String.format("%-5s%-20s%-6s%-5s", "LAB 8", " ", nlDate, " ");
            wsGrRptHeading1 = String.format("%-20s%-8s", " ", "GRADUATE");
            wsNrRptHeading1 = String.format("%-20s%-12s", " ", "NON-GRADUATE");
            wsRptHeading2 = String.format("%-18s%-22s%-26s%-6s%-9s", " ", "EMPLOYEE SALARY REPORT", " ", "PAGE  ", wsPageCount);
            wsHeadingLine1 = String.format("%-3s%-2s%-3s%-30s%-7s%-2s%-8s%-6s%-3s%-9s%-4s%-8s%-6s", "EMP", " ", "EMP", " ", "PRESENT", " ", "INCREASE", " ", "PAY", " ", "NEW", " ", " ");
            wsHeadingLine2 = String.format("%-3s%-2s%-4s%-10s%-5s%-2s%-6s%-7s%-1s%-6s%-8s%-5s%-6s", "NUM", " ", "NAME", " ", "YEARS", " ", "POSITION", " ", "%", " ", "INCREASE", " ", "SALARY");
            wsGSubEmpL1 = String.format("%-15s%-8s%-7s%-4s%-8s%-4s%-4s%-12s", "EMPLOYEE CLASS:", " ", "Analyst", " ", "Sen Prog", " ", "Prog", "Unclassified");
            wsNSubEmpL1 = String.format("%-15s%-8s%-4s%-4s%-7s%-4s%-12s", "EMPLOYEE CLASS:", " ", "Prog", " ", "Jr Prog", "Unclassified");
            wsGSubEmpL2 = String.format("%-15s%-13s%-9s%-6s%-9s%-14s%-9s", "# ON THIS PAGE:", " ", "0", " ", "0", " ", "0");
            wsNSubEmpL2 = String.format("%-15s%-10s%-9s%-14s%-9s", "# ON THIS PAGE:", " ", "0", " ", "0");
            wsGAvgTotalL1 = String.format("%-18s%-3s%-8s%-9s%-6s%-9s%-6s%-8s%-5s%-6s%-8s%-5s%-6s", "AVERAGE INCREASES:", " ", "ANALYST=", "0.00", " ", "SEN PROG=", "0.00");
            wsGAvgTotalL2 = String.format("%-24s%-5s%-9s", " ", "PROG=", "0.00");
            wsNAvgTotalLine = String.format("%-18s%-3s%-5s%-9s%-7s%-8s%-5s%-6s%-8s%-5s%-6s", "AVERAGE INCREASES:", " ", "PROG=", "0.00", " ", "JR PROG=", "0.00");

            gWriter.write(wsNameLine);
            gWriter.newLine();
            gWriter.write(wsNewLine);
            gWriter.write(wsGrRptHeading1);
            gWriter.newLine();
            gWriter.write(wsRptHeading2);
            gWriter.newLine();
            gWriter.write(wsNewLine);
            gWriter.write(wsHeadingLine1);
            gWriter.newLine();
            gWriter.write(wsHeadingLine2);
            gWriter.newLine();
            gWriter.write(wsNewLine());

            nWriter.write(wsNameLine);
            nWriter.newLine();
            nWriter.write(wsNewLine);
            nWriter.write(wsNrRptHeading1);
            nWriter.newLine();
            nWriter.write(wsRptHeading2);
            nWriter.newLine();
            nWriter.write(wsNewLine);
            nWriter.write(wsHeadingLine1);
            nWriter.newLine();
            nWriter.write(wsHeadingLine2);
            nWriter.newLine();
            nWriter.write(wsNewLine());

            String line;
            while ((line = reader.readLine()) != null) {
                String ilEmpNum = line.substring(0, 3).trim();
                String ilName = line.substring(3, 18).trim();
                String ilEduCode = line.substring(18, 19).trim();
                int ilYears = Integer.parseInt(line.substring(19, 21).trim());
                double ilCurSalary = Double.parseDouble(line.substring(21, 28).trim());

                if (ilEduCode.equals(wsGrads)) {
                    wsOutput = String.format("%-3s%-2s%-15s%-2s%-2s%-10s%-2s%-7s%-2s