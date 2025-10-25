import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab6Demo {
    private static final int EMP_LIMIT = 14;
    private static final double INCREASE_ANALYST = 0.118;
    private static final double INCREASE_SPROG = 0.083;
    private static final double INCREASE_PROG = 0.067;
    private static final double INCREASE_JRPROG = 0.042;
    private static final int PERC_CONVERT = 100;
    private static final char PERC_SIGN = '%';
    private static final char PLUS_SIGN = '+';
    private static final char GRADS = 'G';
    private static final char NON_GRADS = 'N';
    private static final String CLASS_ANALYST = "ANALYST";
    private static final String CLASS_SPROG = "SEN PROG";
    private static final String CLASS_PROG = "PROG";
    private static final String CLASS_JRPROG = "JR PROG";
    private static final int ANALYST_GT = 15;
    private static final int SPROG_LTE = 15;
    private static final int SPROG_GTE = 7;
    private static final int G_PROG_GT = 2;
    private static final int G_PROG_LT = 7;
    private static final int G_UNCLASS_LTE = 2;
    private static final int N_PROG_GT = 10;
    private static final int N_JRPROG_LTE = 10;
    private static final int N_JRPROG_GT = 4;
    private static final int N_UNCLASS_LTE = 4;
    private static final int INC_ONE = 1;
    private static final int RESET_ZERO = 0;

    private static String wsEofFlag = "n";
    private static String wsNewLine = " ";
    private static String wsNameLine;
    private static String wsRptHeading;
    private static String wsHeadingLine1;
    private static String wsHeadingLine2;
    private static String wsSubEmpL1;
    private static String wsSubEmpL2;
    private static int wsAnalystStotal = 0;
    private static int wsSprogStotal = 0;
    private static int wsProgStotal = 0;
    private static int wsJrprogStotal = 0;
    private static int wsUnclassStotal = 0;
    private static int wsAnalystCount = 0;
    private static int wsSprogCount = 0;
    private static int wsProgCount = 0;
    private static int wsJrprogCount = 0;
    private static double wsAnalystIncTotal = 0;
    private static double wsSprogIncTotal = 0;
    private static double wsProgIncTotal = 0;
    private static double wsJrprogIncTotal = 0;
    private static int wsLineCount = 0;
    private static int wsPageCount = 1;

    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader("lab6.dat"));
            writer = new BufferedWriter(new FileWriter("lab6.out"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

            wsNameLine = String.format("%-5s%-20s%6s%8s", "LAB 6", " ", dateFormat.format(new Date()), timeFormat.format(new Date()));
            wsRptHeading = String.format("%18s%22s%26s%6s%1d", " ", "EMPLOYEE SALARY REPORT", " ", "PAGE  ", wsPageCount);
            wsHeadingLine1 = String.format("%3s%3s%30s%7s%8s%6s%3s%9s%3x%4s", "EMP", "EMP", " ", "PRESENT", "INCREASE", "PAY", "NEW", " ");
            wsHeadingLine2 = String.format("%3s%4s%10s%5s%8s%4s%6s%7s%1s%6s%8s%5s", "NUM", "NAME", "YEARS", "POSITION", "SALARY", "%", "INCREASE", "SALARY");
            wsSubEmpL1 = String.format("%15s%8s%4s%8s%4s%4s%7s%4s%12s", "EMPLOYEE CLASS:", " ", "Analyst", " ", "Sen Prog", " ", "Prog", " ", "Jr Prog", "Unclassified");

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
                if (line.length() >= 28) {
                    String ilEmpNum = line.substring(0, 3).trim();
                    String ilName = line.substring(3, 18).trim();
                    char ilEduCode = line.charAt(18);
                    int ilYears = Integer.parseInt(line.substring(19, 21).trim());
                    double ilCurSalary = Double.parseDouble(line.substring(21, 28).trim());

                    String wsOutput = processMain(ilEduCode, ilYears, ilCurSalary);

                    writer.write(String.format("%3s%15s%2d%10.2f%1s%6.2f%1s%8.2f%1s%8.2f", ilEmpNum, ilName, ilYears, ilCurSalary, wsOutput.charAt(0), Double.parseDouble(wsOutput.substring(1, 7)), wsOutput.charAt(7), Double.parseDouble(wsOutput.substring(8, 15)), wsOutput.charAt(15), Double.parseDouble(wsOutput.substring(16, 23))));
                    writer.newLine();

                    if (wsLineCount >= EMP_LIMIT) {
                        wsPageCount++;
                        subtotals(writer);
                        newPageHead(writer);
                        resetCountAndTotal();
                    } else {
                        wsLineCount++;
                    }
                }
            }

            subtotalsAndAveragePay(writer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String processMain(char ilEduCode, int ilYears, double ilCurSalary) {
        String olPosition = " ";
        double olIncPerc = 0;
        double olPayInc = 0;
        double olNewSal = 0;

        if (ilEduCode == GRADS) {
            if (ilYears <= G_UNCLASS_LTE) {
                processUnclass();
            }
            if (ilYears < G_PROG_LT && ilYears > G_PROG_GT) {
                processProg();
            }
            if (ilYears <= SPROG_LTE && ilYears >= SPROG_GTE) {
                processSprog();
            }
            if (ilYears > ANALYST_GT) {
                processAnalyst();
            }
        }

        if (ilEduCode == NON_GRADS) {
            if (ilYears > N_PROG_GT) {
                processProg();
            }
            if (ilYears <= N_JRPROG_LTE && ilYears > N_JRPROG_GT) {
                processJrprog();
            }
            if (ilYears <= N_UNCLASS_LTE) {
                processUnclass();
            }
        }

        return String.format("%6.2f%c%8.2f%c%8.2f", olIncPerc, PERC_SIGN, olPayInc, PLUS_SIGN, olNewSal);
    }

    private static void processAnalyst() {
        wsAnalystStotal++;
        wsAnalystCount++;
        double increaseByCalc = INCREASE_ANALYST * 100 * 100;
        wsAnalystIncTotal += increaseByCalc;
    }

    private static void processSprog() {
        wsSprogStotal++;
        wsSprogCount++;
        double increaseByCalc = INCREASE_SPROG * 100 * 100;
        wsSprogIncTotal += increaseByCalc;
    }

    private static void processProg() {
        wsProgStotal++;
        wsProgCount++;
        double increaseByCalc = INCREASE_PROG * 100 * 100;
        wsProgIncTotal += increaseByCalc;
    }

    private static void processJrprog() {
        wsJrprogStotal++;
        wsJrprogCount++;
        double increaseByCalc = INCREASE_JRPROG * 100 * 100;
        wsJrprogIncTotal += increaseByCalc;
    }

    private static void processUnclass() {
        wsUnclassStotal++;
    }

    private static void subtotals(BufferedWriter writer) throws IOException {
        wsSubEmpL2 = String.format("%15s%13d%10d%6d%9d%14d", "# ON THIS PAGE:", wsAnalystStotal, wsSprogStotal, wsProgStotal, wsJrprogStotal, wsUnclassStotal);