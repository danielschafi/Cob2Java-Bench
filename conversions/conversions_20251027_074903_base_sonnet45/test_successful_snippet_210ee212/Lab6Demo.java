import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lab6Demo {
    private static final String INPUT_FILE = "lab6.dat";
    private static final String OUTPUT_FILE = "lab6.out";
    
    private static BufferedReader inputFile;
    private static BufferedWriter outputFile;
    
    private static boolean eofFlag = false;
    private static int pageCount = 1;
    private static int lineCount = 0;
    private static final int EMP_LIMIT = 14;
    
    private static int analystStotal = 0;
    private static int sprogStotal = 0;
    private static int progStotal = 0;
    private static int jrprogStotal = 0;
    private static int unclassStotal = 0;
    
    private static int analystCount = 0;
    private static int sprogCount = 0;
    private static int progCount = 0;
    private static int jrprogCount = 0;
    
    private static double analystIncTotal = 0.0;
    private static double sprogIncTotal = 0.0;
    private static double progIncTotal = 0.0;
    private static double jrprogIncTotal = 0.0;
    
    private static final double INCREASE_ANALYST_CNST = 0.118;
    private static final double INCREASE_SPROG_CNST = 0.083;
    private static final double INCREASE_PROG_CNST = 0.067;
    private static final double INCREASE_JRPROG_CNST = 0.042;
    
    private static String ilEmpNum;
    private static String ilName;
    private static String ilEduCode;
    private static int ilYears;
    private static double ilCurSalary;
    
    private static String olEmpNum;
    private static String olName;
    private static int olYears;
    private static String olPosition;
    private static double olCurSalary;
    private static double olIncPerc;
    private static String olPercSign;
    private static double olPayInc;
    private static String olPlusSign;
    private static double olNewSal;
    
    public static void main(String[] args) {
        try {
            inputFile = new BufferedReader(new FileReader(INPUT_FILE));
            outputFile = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            heading100();
            
            if (!readInputFile()) {
                eofFlag = true;
            }
            
            while (!eofFlag) {
                clearOutput();
                processMain200();
                
                olEmpNum = ilEmpNum;
                olName = ilName;
                olYears = ilYears;
                olCurSalary = ilCurSalary;
                
                writeOutputLine(formatOutputLine());
                writeOutputLine("");
                
                if (lineCount >= EMP_LIMIT) {
                    pageCount++;
                    subtotals300();
                    newPageHead310();
                    resetCountAndTotal400();
                } else {
                    lineCount++;
                }
                
                if (!readInputFile()) {
                    eofFlag = true;
                }
            }
            
            subtotalsAndAveragePay500();
            
            inputFile.close();
            outputFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void heading100() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmmssSSS")).substring(0, 8);
        
        String nameLine = "LAB 6" + spaces(20) + date + spaces(5) + time;
        writeOutputLine(nameLine);
        writeOutputLine("");
        
        String rptHeading = spaces(18) + "EMPLOYEE SALARY REPORT" + spaces(26) + "PAGE  " + pageCount;
        writeOutputLine(rptHeading);
        writeOutputLine("");
        
        String headingLine1 = "EMP" + spaces(2) + "EMP" + spaces(30) + "PRESENT" + spaces(2) + "INCREASE" + spaces(6) + "PAY" + spaces(9) + "NEW" + spaces(4);
        writeOutputLine(headingLine1);
        
        String headingLine2 = "NUM" + spaces(2) + "NAME" + spaces(10) + "YEARS" + spaces(2) + "POSITION" + spaces(4) + "SALARY" + spaces(7) + "%" + spaces(6) + "INCREASE" + spaces(5) + "SALARY";
        writeOutputLine(headingLine2);
        writeOutputLine("");
    }
    
    private static void processMain200() {
        olPosition = "";
        
        if ("G".equals(ilEduCode)) {
            if (ilYears <= 2) {
                processUnclass250();
            }
            if (ilYears < 7 && ilYears > 2) {
                processProg230();
            }
            if (ilYears <= 15 && ilYears >= 7) {
                processSprog220();
            }
            if (ilYears > 15) {
                processAnalyst210();
            }
        }
        
        if ("N".equals(ilEduCode)) {
            if (ilYears > 10) {
                processProg230();
            }
            if (ilYears <= 10 && ilYears > 4) {
                processJrprog240();
            }
            if (ilYears <= 4) {
                processUnclass250();
            }
        }
    }
    
    private static void processAnalyst210() {
        olPosition = "ANALYST";
        olIncPerc = 100 * INCREASE_ANALYST_CNST;
        double increaseByCalc = Math.round(INCREASE_ANALYST_CNST * ilCurSalary * 100.0) / 100.0;
        olPayInc = increaseByCalc;
        analystIncTotal += increaseByCalc;
        olNewSal = increaseByCalc + ilCurSalary;
        analystStotal++;
        analystCount++;
        moveSuffixSigns260();
    }
    
    private static void processSprog220() {
        olPosition = "SEN PROG";
        olIncPerc = 100 * INCREASE_SPROG_CNST;
        double increaseByCalc = Math.round(INCREASE_SPROG_CNST * ilCurSalary * 100.0) / 100.0;
        olPayInc = increaseByCalc;
        sprogIncTotal += increaseByCalc;
        olNewSal = increaseByCalc + ilCurSalary;
        sprogStotal++;
        sprogCount++;
        moveSuffixSigns260();
    }
    
    private static void processProg230() {
        olPosition = "PROG";
        olIncPerc = 100 * INCREASE_PROG_CNST;
        double increaseByCalc = Math.round(INCREASE_PROG_CNST * ilCurSalary * 100.0) / 100.0;
        olPayInc = increaseByCalc;
        progIncTotal += increaseByCalc;
        olNewSal = increaseByCalc + ilCurSalary;
        progStotal++;
        progCount++;
        moveSuffixSigns260();
    }
    
    private static void processJrprog240() {
        olPosition = "JR PROG";
        olIncPerc = 100 * INCREASE_JRPROG_CNST;
        double increaseByCalc = Math.round(INCREASE_JRPROG_CNST * ilCurSalary * 100.0) / 100.0;
        olPayInc = increaseByCalc;