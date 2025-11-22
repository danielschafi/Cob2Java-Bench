import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lab6Demo {
    private static final String INPUT_FILE = "lab8.dat";
    private static final String G_OUTPUT_FILE = "G-lab8.out";
    private static final String N_OUTPUT_FILE = "N-lab8.out";

    private BufferedReader inputReader;
    private PrintWriter gOutputWriter;
    private PrintWriter nOutputWriter;

    private String wsEofFlag = "n";
    private String wsResetFlag = "n";
    private String wsNoReset = "n";
    private String wsYesReset = "y";

    private int wsPageCount = 1;
    private int wsLineCount = 0;
    private int wsEmpLimit = 14;

    private int wsAnalystStotal = 0;
    private int wsSprogStotal = 0;
    private int wsProgStotal = 0;
    private int wsJrprogStotal = 0;
    private int wsUnclassStotal = 0;

    private int wsAnalystCount = 0;
    private int wsSprogCount = 0;
    private int wsProgCount = 0;
    private int wsJrprogCount = 0;

    private double wsAnalystIncTotal = 0.0;
    private double wsSprogIncTotal = 0.0;
    private double wsProgIncTotal = 0.0;
    private double wsJrprogIncTotal = 0.0;

    private double wsAnalystAvgFinal = 0.0;
    private double wsSprogAvgFinal = 0.0;
    private double wsProgAvgFinal = 0.0;
    private double wsNProgAvgFinal = 0.0;
    private double wsNJrprogAvgFinal = 0.0;

    private static final double WS_INCREASE_ANALYST_CNST = 0.118;
    private static final double WS_INCREASE_SPROG_CNST = 0.083;
    private static final double WS_INCREASE_PROG_CNST = 0.067;
    private static final double WS_INCREASE_JRPROG_CNST = 0.042;
    private static final int WS_PERC_CONVERT_CNST = 100;
    private static final String WS_GRADS = "G";
    private static final String WS_NON_GRADS = "N";
    private static final String WS_CLASS_ANALYST_CNST = "ANALYST";
    private static final String WS_CLASS_SPROG_CNST = "SEN PROG";
    private static final String WS_CLASS_PROG_CNST = "PROG";
    private static final String WS_CLASS_JRPROG_CNST = "JR PROG";

    private static final int WS_ANALYST_GT_CNST = 15;
    private static final int WS_SPROG_LTE_CNST = 15;
    private static final int WS_SPROG_GTE_CNST = 7;
    private static final int WS_G_PROG_GT_CNST = 2;
    private static final int WS_G_PROG_LT_CNST = 7;
    private static final int WS_G_UNCLASS_LTE_CNST = 2;
    private static final int WS_N_PROG_GT_CNST = 10;
    private static final int WS_N_JRPROG_LTE_CNST = 10;
    private static final int WS_N_JRPROG_GT_CNST = 4;
    private static final int WS_N_UNCLASS_LTE_CNST = 4;

    private String ilEmpNum;
    private String ilName;
    private String ilEduCode;
    private int ilYears;
    private double ilCurSalary;

    private String olEmpNum;
    private String olName;
    private int olYears;
    private String olPosition;
    private double olCurSalary;
    private double olIncPerc;
    private double olPayInc;
    private double olNewSal;

    public static void main(String[] args) {
        Lab6Demo program = new Lab6Demo();
        program.run();
    }

    public void run() {
        try {
            openFiles();
            headingG();

            String line = inputReader.readLine();
            if (line == null) {
                wsEofFlag = "y";
            }

            while (!wsEofFlag.equals("y")) {
                parseLine(line);

                if (ilEduCode.equals(WS_GRADS)) {
                    olPosition = "";
                    processMainG();
                    moveInput();
                    printOutputG();
                    pagingG();
                }

                if (ilEduCode.equals(WS_NON_GRADS) && wsResetFlag.equals(wsNoReset)) {
                    subtotalsAndAveragePayG();
                    printAvgLineG();
                    gOutputWriter.close();
                    grandReset();
                    wsResetFlag = wsYesReset;
                    headingN();
                }

                if (ilEduCode.equals(WS_NON_GRADS) && wsResetFlag.equals(wsYesReset)) {
                    olPosition = "";
                    processMainN();
                    moveInput();
                    printOutputN();
                    pagingN();
                }

                line = inputReader.readLine();
                if (line == null) {
                    wsEofFlag = "y";
                }
            }

            subtotalsAndAveragePayN();
            printAvgLineN();
            nOutputWriter.close();
            inputReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFiles() throws IOException {
        inputReader = new BufferedReader(new FileReader(INPUT_FILE));
        gOutputWriter = new PrintWriter(new FileWriter(G_OUTPUT_FILE));
        nOutputWriter = new PrintWriter(new FileWriter(N_OUTPUT_FILE));
    }

    private void parseLine(String line) {
        if (line.length() >= 28) {
            ilEmpNum = line.substring(0, 3);
            ilName = line.substring(3, 18);
            ilEduCode = line.substring(18, 19);
            ilYears = Integer.parseInt(line.substring(19, 21));
            ilCurSalary = Double.parseDouble(line.substring(21, 28)) / 100.0;
        }
    }

    private void headingG() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        gOutputWriter.println("LAB 8                    " + today.format(dateFormatter) + "     " + now.format(timeFormatter));
        gOutputWriter.println();
        gOutputWriter.println("                    GRADUATE");
        gOutputWriter.println("                  EMPLOYEE SALARY REPORT                          PAGE  " + wsPageCount);
        gOutputWriter.println();
        gOutputWriter.println("EMP  EMP                              PRESENT  INCREASE      PAY    NEW    ");
        gOutputWriter.println("NUM  NAME                  YEARS      POSITION      SALARY      %      INCREASE    SALARY");
        gOutputWriter.println();
    }

    private void headingN() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        nOutputWriter.println("LAB 8                    " + today.format(dateFormatter) + "     " + now.format(timeFormatter));
        nOutputWriter.println();