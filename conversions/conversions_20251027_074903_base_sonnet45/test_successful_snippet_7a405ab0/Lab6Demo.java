import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lab6Demo {
    private static final DecimalFormat DF_CURRENCY = new DecimalFormat("$###,##0.00");
    private static final DecimalFormat DF_PERCENT = new DecimalFormat("##0.0");
    private static final DecimalFormat DF_SALARY = new DecimalFormat("##,##0.00");
    private static final DecimalFormat DF_YEARS = new DecimalFormat("#0");
    private static final DecimalFormat DF_COUNT = new DecimalFormat("#0");
    private static final DecimalFormat DF_AVG = new DecimalFormat("###,##0.00");

    private String eofFlag = "n";
    private String resetFlag = "n";
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
    private double analystIncTotal = 0.0;
    private double sprogIncTotal = 0.0;
    private double progIncTotal = 0.0;
    private double jrprogIncTotal = 0.0;

    private BufferedReader inputFile;
    private PrintWriter gOutputFile;
    private PrintWriter nOutputFile;

    public static void main(String[] args) {
        Lab6Demo program = new Lab6Demo();
        program.run();
    }

    public void run() {
        try {
            inputFile = new BufferedReader(new FileReader("lab8.dat"));
            gOutputFile = new PrintWriter(new FileWriter("G-lab8.out"));
            nOutputFile = new PrintWriter(new FileWriter("N-lab8.out"));

            writeGHeading();
            String line = inputFile.readLine();
            if (line == null) {
                eofFlag = "y";
            }

            while (!eofFlag.equals("y")) {
                String empNum = line.substring(0, 3);
                String name = line.substring(3, 18);
                String eduCode = line.substring(18, 19);
                int years = Integer.parseInt(line.substring(19, 21).trim());
                double curSalary = Double.parseDouble(line.substring(21, 28).trim()) / 100.0;

                if (eduCode.equals("G")) {
                    OutputRecord output = processGMain(eduCode, years, curSalary);
                    output.empNum = empNum;
                    output.name = name;
                    output.years = years;
                    output.curSalary = curSalary;
                    printGOutput(output);
                    gPaging();
                }

                if (eduCode.equals("N") && resetFlag.equals("n")) {
                    gSubtotalsAndAveragePay();
                    printGAvgLine();
                    gOutputFile.close();
                    grandReset();
                    resetFlag = "y";
                    writeNHeading();
                }

                if (eduCode.equals("N") && resetFlag.equals("y")) {
                    OutputRecord output = processNMain(eduCode, years, curSalary);
                    output.empNum = empNum;
                    output.name = name;
                    output.years = years;
                    output.curSalary = curSalary;
                    printNOutput(output);
                    nPaging();
                }

                line = inputFile.readLine();
                if (line == null) {
                    eofFlag = "y";
                }
            }

            nSubtotalsAndAveragePay();
            printNAvgLine();
            nOutputFile.close();
            inputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeGHeading() {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmmssSSS")).substring(0, 8);
        
        gOutputFile.println("LAB 8                    " + date + "     " + time);
        gOutputFile.println();
        gOutputFile.println("                    GRADUATE");
        gOutputFile.println("                  EMPLOYEE SALARY REPORT                          PAGE  " + pageCount);
        gOutputFile.println();
        gOutputFile.println("EMP  EMP                               PRESENT  INCREASE      PAY         NEW    ");
        gOutputFile.println("NUM  NAME              YEARS  POSITION    SALARY      %      INCREASE     SALARY");
        gOutputFile.println();
    }

    private void writeNHeading() {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmmssSSS")).substring(0, 8);
        
        nOutputFile.println("LAB 8                    " + date + "     " + time);
        nOutputFile.println();
        nOutputFile.println("                    NON-GRADUATE");
        nOutputFile.println("                  EMPLOYEE SALARY REPORT                          PAGE  " + pageCount);
        nOutputFile.println();
        nOutputFile.println("EMP  EMP                               PRESENT  INCREASE      PAY         NEW    ");
        nOutputFile.println("NUM  NAME              YEARS  POSITION    SALARY      %      INCREASE     SALARY");
        nOutputFile.println();
    }

    private OutputRecord processGMain(String eduCode, int years, double curSalary) {
        OutputRecord output = new OutputRecord();
        
        if (eduCode.equals("G")) {
            if (years <= 2) {
                processUnclass(output, curSalary);
            } else if (years < 7 && years > 2) {
                processProg(output, curSalary);
            } else if (years <= 15 && years >= 7) {
                processSprog(output, curSalary);
            } else if (years > 15) {
                processAnalyst(output, curSalary);
            }
        }
        return output;
    }

    private OutputRecord processNMain(String eduCode, int years, double curSalary) {
        OutputRecord output = new OutputRecord();
        
        if (eduCode.equals("N")) {
            if (years > 10) {
                processProg(output, curSalary);
            } else if (years <= 10 && years > 4) {
                processJrprog(output, curSalary);
            } else if (years <= 4) {
                processUnclass(output, curSalary);
            }
        }
        return output;
    }

    private void processAnalyst(OutputRecord output, double curSalary) {
        output.position = "ANALYST";
        output.incPerc = 100 * 0.118;
        double increaseByCalc = Math.round(0.118 * curSalary * 100.0) / 100.0;
        output.payInc = increaseByCalc;
        analystIncTotal += increaseByCalc;
        output.newSal = increaseByCalc + curSalary;
        analystStotal++;
        analystCount++;
        output.percSign = "%";
        output.plusSign = "+";
    }

    private void processSprog(OutputRecord output, double curSalary) {
        output.position = "SEN PROG";
        output.incPerc = 100 * 0.083;
        double increaseByCalc = Math.round(0.083 * curSalary * 100.0) / 100.0;
        output.payInc = incre