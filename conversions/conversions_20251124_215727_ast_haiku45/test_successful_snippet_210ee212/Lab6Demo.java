import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Lab6Demo {
    private static final double INCREASE_ANALYST = 0.118;
    private static final double INCREASE_SPROG = 0.083;
    private static final double INCREASE_PROG = 0.067;
    private static final double INCREASE_JRPROG = 0.042;
    private static final int PERC_CONVERT = 100;
    private static final String GRADS = "G";
    private static final String NON_GRADS = "N";
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
    private static final int EMP_LIMIT = 14;

    private String eofFlag = "n";
    private int pageCount = 1;
    private int lineCount = 0;
    private int analystStotal = 0;
    private int sprogStotal = 0;
    private int progStotal = 0;
    private int jrprogStotal = 0;
    private int unclassStotal = 0;
    private int analystCount = 0;
    private int sprogCount = 0;
    private int progCount = 0;
    private int jrprogCount = 0;
    private double analystIncTotal = 0;
    private double sprogIncTotal = 0;
    private double progIncTotal = 0;
    private double jrprogIncTotal = 0;
    private double increaseByCalc = 0;

    private PrintWriter outputWriter;
    private Scanner inputScanner;

    public static void main(String[] args) {
        Lab6Demo program = new Lab6Demo();
        program.run();
    }

    public void run() {
        try {
            inputScanner = new Scanner(new File("lab6.dat"));
            outputWriter = new PrintWriter(new FileWriter("lab6.out"));

            heading();

            if (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                processLine(line);
            } else {
                eofFlag = "y";
            }

            while (!eofFlag.equals("y")) {
                String empNum = "";
                String name = "";
                int years = 0;
                double curSalary = 0;
                String eduCode = "";

                if (inputScanner.hasNextLine()) {
                    String line = inputScanner.nextLine();
                    if (line.length() >= 28) {
                        empNum = line.substring(0, 3);
                        name = line.substring(3, 18);
                        eduCode = line.substring(18, 19);
                        years = Integer.parseInt(line.substring(19, 21));
                        curSalary = Double.parseDouble(line.substring(21, 28)) / 100.0;
                    }
                } else {
                    eofFlag = "y";
                    break;
                }

                processMain(eduCode, years, curSalary, empNum, name);

                outputWriter.println(formatOutput(empNum, name, years, curSalary));
                outputWriter.println();

                if (lineCount >= EMP_LIMIT) {
                    pageCount++;
                    subtotals();
                    newPageHead();
                    resetCountAndTotal();
                } else {
                    lineCount++;
                }

                if (!inputScanner.hasNextLine()) {
                    eofFlag = "y";
                }
            }

            subtotalsAndAveragePay();
            outputWriter.close();
            inputScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void heading() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        String dateLine = String.format("LAB 6                    %s     %s", today.format(dateFormatter), now.format(timeFormatter));
        outputWriter.println(dateLine);
        outputWriter.println();
        outputWriter.println("                      EMPLOYEE SALARY REPORT                          PAGE  " + pageCount);
        outputWriter.println();
        outputWriter.println("EMP  EMP                              PRESENT  INCREASE      PAY         NEW    ");
        outputWriter.println("NUM  NAME          YEARS  POSITION    SALARY   %      INCREASE    SALARY");
        outputWriter.println();
    }

    private void processMain(String eduCode, int years, double curSalary, String empNum, String name) {
        if (eduCode.equals(GRADS)) {
            if (years <= G_UNCLASS_LTE) {
                processUnclass();
            }
            if (years < G_PROG_LT && years > G_PROG_GT) {
                processProg(curSalary);
            }
            if (years <= SPROG_LTE && years >= SPROG_GTE) {
                processSProg(curSalary);
            }
            if (years > ANALYST_GT) {
                processAnalyst(curSalary);
            }
        }

        if (eduCode.equals(NON_GRADS)) {
            if (years > N_PROG_GT) {
                processProg(curSalary);
            }
            if (years <= N_JRPROG_LTE && years > N_JRPROG_GT) {
                processJrProg(curSalary);
            }
            if (years <= N_UNCLASS_LTE) {
                processUnclass();
            }
        }
    }

    private void processAnalyst(double curSalary) {
        double incPerc = PERC_CONVERT * INCREASE_ANALYST;
        increaseByCalc = Math.round(INCREASE_ANALYST * curSalary * 100.0) / 100.0;
        analystIncTotal += increaseByCalc;
        analystStotal++;
        analystCount++;
    }

    private void processSProg(double curSalary) {
        double incPerc = PERC_CONVERT * INCREASE_SPROG;
        increaseByCalc = Math.round(INCREASE_SPROG * curSalary * 100.0) / 100.0;
        sprogIncTotal += increaseByCalc;
        sprogStotal++;
        sprogCount++;
    }

    private void processProg(double curSalary) {
        double incPerc = PERC_CONVERT * INCREASE_PROG;
        increaseByCalc = Math.round(INCREASE_PROG * curSalary * 100.0) / 100.0;
        progIncTotal += increaseByCalc;
        progStotal++;
        progCount++;
    }

    private void processJrProg(double curSalary) {
        double incPerc = PERC_CONVERT * INCREASE_JRPROG;
        increaseByCalc = Math.round(INCREASE_JRPROG * curSalary * 100.0) / 100.0;
        jrprogIncTotal += increaseByCalc