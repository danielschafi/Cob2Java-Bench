import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab6Demo {
    public static void main(String[] args) {
        String inputFile = "lab6.dat";
        String outputFile = "lab6.out";
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));

            String wsEofFlag = "n";
            String wsNewLine = " ";
            String nlDate = new SimpleDateFormat("yyMMdd").format(new Date());
            String nlTime = new SimpleDateFormat("HHmmss").format(new Date());

            String wsNameLine = String.format("%-5s%-20s%6s%5s%8s", "LAB 6", " ", nlDate, " ", nlTime);
            String wsRptHeading = String.format("%18s%-22s%-26s%-6s%1d", " ", "EMPLOYEE SALARY REPORT", " ", "PAGE  ", 1);
            String wsHeadingLine1 = String.format("%3s%2s%3s%30s%7s%2s%8s%6s%3s%9s%3x%4s", "EMP", " ", "EMP", " ", "PRESENT", " ", "INCREASE", " ", "PAY", " ", "NEW", " ");
            String wsHeadingLine2 = String.format("%3s%2s%4s%10s%5s%2s%8s%4s%6s%7s%1s%6s%8s%5s%6s", "NUM", " ", "NAME", " ", "YEARS", " ", "POSITION", " ", "SALARY", " ", "%", " ", "INCREASE", " ", "SALARY");
            String wsSubEmpL1 = String.format("%-15s%-8s%-7s%-4s%-8s%-4s%-4s%-4s%-7s%-4s%-12s", "EMPLOYEE CLASS:", " ", "Analyst", " ", "Sen Prog", " ", "Prog", " ", "Jr Prog", " ", "Unclassified");
            String wsSubEmpL2 = String.format("%-15s%-13s%1d%-10s%1d%-6s%1d%-9s%1d%-14s%1d", "# ON THIS PAGE:", " ", 0, " ", 0, " ", 0, " ", 0, " ", 0);
            int wsAnalystStotal = 0;
            int wsSprogStotal = 0;
            int wsProgStotal = 0;
            int wsJrprogStotal = 0;
            int wsUnclassStotal = 0;
            int wsAnalystCount = 0;
            int wsSprogCount = 0;
            int wsProgCount = 0;
            int wsJrprogCount = 0;
            String wsAvgTotalLine1 = String.format("%18s%-3x%-8s%9.2f%-6s%-9s", "AVERAGE INCREASES:", ' ', "ANALYST=", 0.00, " ", "SEN PROG=");
            String wsAvgTotalLine2 = String.format("%24s%-5s%9.2f%-7s%-8s%9.2f", " ", "PROG=", 0.00, " ", "JR PROG=", 0.00);
            double wsAnalystIncTotal = 0.0;
            double wsSprogIncTotal = 0.0;
            double wsProgIncTotal = 0.0;
            double wsJrprogIncTotal = 0.0;
            String wsOutput = String.format("%3s%2s%-15s%2s%1d%2s%-10s%2s%8.2f%2s%1.1f%1s%2s%8.2f%1s%2s%8.2f", " ", " ", " ", " ", 0, " ", " ", " ", 0.00, " ", 0.0, "%", " ", 0.00, "+", " ", 0.00);
            int wsLineCount = 0;
            int wsEmpLimit = 14;
            double wsIncreaseByCalc = 0.0;
            double wsIncreaseAnalystCnst = 0.118;
            double wsIncreaseSprogCnst = 0.083;
            double wsIncreaseProgCnst = 0.067;
            double wsIncreaseJrprogCnst = 0.042;
            int wsPercConvertConst = 100;
            char wsPercSignCnst = '%';
            char wsPlusSignCnst = '+';
            char wsGRADS = 'G';
            char wsNONGRADS = 'N';
            String wsClassAnalystCnst = "ANALYST";
            String wsClassSprogCnst = "SEN PROG";
            String wsClassProgCnst = "PROG";
            String wsClassJrprogCnst = "JR PROG";
            int wsAnalystGtCnst = 15;
            int wsSprogLteCnst = 15;
            int wsSprogGteCnst = 7;
            int wsGProgGtCnst = 2;
            int wsGProgLtCnst = 7;
            int wsGUnclassLteCnst = 2;
            int wsNProgGtCnst = 10;
            int wsNJrprogLteCnst = 10;
            int wsNJrprogGtCnst = 4;
            int wsNUnclassLteCnst = 4;
            int wsIncOneCnst = 1;
            int wsResetZeroCnst = 0;

            writer.write(wsNameLine);
            writer.newLine();
            writer.write(wsRptHeading);
            writer.newLine();
            writer.write(wsHeadingLine1);
            writer.newLine();
            writer.write(wsHeadingLine2);
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 28) break;
                String ilEmpNum = line.substring(0, 3).trim();
                String ilName = line.substring(3, 18).trim();
                char ilEduCode = line.charAt(18);
                int ilYears = Integer.parseInt(line.substring(19, 21).trim());
                double ilCurSalary = Double.parseDouble(line.substring(21, 28).trim());

                wsOutput = String.format("%3s%2s%-15s%2s%1d%2s%-10s%2s%8.2f%2s%1.1f%1s%2s%8.2f%1s%2s%8.2f", ilEmpNum, " ", ilName, " ", ilYears, " ", " ", " ", ilCurSalary, " ", 0.0, "%", " ", 0.00, "+", " ", 0.00);

                if (ilEduCode == wsGRADS) {
                    if (ilYears <= wsGUnclassLteCnst) {
                        processUnclass(wsOutput, wsUnclassStotal, wsResetZeroCnst, ilCurSalary);
                    }
                    if (ilYears < wsGProgLtCnst && ilYears > wsGProgGtCnst) {
                        processProg(wsOutput, wsProgStotal, wsProgCount, wsProgIncTotal, wsIncreaseProgCnst, wsPercConvertConst, wsPercSignCnst, wsPlusSignCnst, ilCurSalary);
                    }
                    if (ilYears <= wsSprogLteCnst && ilYears >= wsSprogGteCnst) {
                        processSprog(wsOutput, wsSprogStotal, wsSprogCount, wsSprogIncTotal, wsIncreaseSprogCnst, wsPercConvertConst, wsPercSignCnst, wsPlusSignCnst, ilCurSalary);
                    }
                    if (ilYears > wsAnalystGtCnst) {
                        processAnalyst(wsOutput, wsAnalystStotal, wsAnalystCount, wsAnalystIncTotal, wsIncreaseAnalystCnst, wsPercConvertConst, wsPercSignCnst, wsPlusSignCnst, ilCurSalary);
                    }
                }

                if (ilEduCode == wsNONGRADS) {
                    if (ilYears > wsNProgGtCnst) {
                        processProg(wsOutput, wsProgStotal, wsProgCount, wsProgIncTotal, wsIncreaseProgCnst, wsPercConvertConst, wsPercSignCnst, wsPlusSignCnst, ilCurSalary);
                    }
                    if (ilYears <= wsNJrprogLteCnst && ilYears > wsNJrprogGtCnst) {
                        processJrprog(wsOutput, wsJrprogStotal, wsJrprogCount, wsJrprogIncTotal, wsIncreaseJrprogCnst, wsPercConvertConst, wsPercSignCnst, wsPlusSignCnst, ilCurSalary);
                    }
                    if (ilYears <= wsNUn