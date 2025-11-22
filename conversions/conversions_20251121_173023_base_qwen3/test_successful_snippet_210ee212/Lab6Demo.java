import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab6Demo {
    private static final String INPUT_FILE = "lab6.dat";
    private static final String OUTPUT_FILE = "lab6.out";
    private static final int EMP_LIMIT = 14;
    
    // Constants
    private static final double INCREASE_ANALYST_CONST = 0.118;
    private static final double INCREASE_SPROG_CONST = 0.083;
    private static final double INCREASE_PROG_CONST = 0.067;
    private static final double INCREASE_JRPROG_CONST = 0.042;
    private static final int PERCENT_CONVERT_CONST = 100;
    private static final String PERCENT_SIGN_CONST = "%";
    private static final String PLUS_SIGN_CONST = "+";
    private static final String GRADS = "G";
    private static final String NON_GRADS = "N";
    private static final String CLASS_ANALYST = "ANALYST";
    private static final String CLASS_SPROG = "SEN PROG";
    private static final String CLASS_PROG = "PROG";
    private static final String CLASS_JRPROG = "JR PROG";
    private static final int ANALYST_GT_CONST = 15;
    private static final int SPROG_LTE_CONST = 15;
    private static final int SPROG_GTE_CONST = 7;
    private static final int G_PROG_GT_CONST = 2;
    private static final int G_PROG_LT_CONST = 7;
    private static final int G_UNCLASS_LTE_CONST = 2;
    private static final int N_PROG_GT_CONST = 10;
    private static final int N_JRPROG_LTE_CONST = 10;
    private static final int N_JRPROG_GT_CONST = 4;
    private static final int N_UNCLASS_LTE_CONST = 4;
    private static final int INC_ONE_CONST = 1;
    private static final int RESET_ZERO_CONST = 0;
    
    // File variables
    private static BufferedReader inputFile;
    private static PrintWriter outputFile;
    
    // Working storage variables
    private static String eofFlag = "n";
    private static String newLine = "\n";
    private static String nameLine = "LAB 6" + " ".repeat(20) + "DATE" + " ".repeat(5) + "TIME";
    private static String rptHeading = " ".repeat(18) + "EMPLOYEE SALARY REPORT" + " ".repeat(26) + "PAGE  ";
    private static int pageCount = 1;
    private static String headingLine1 = "EMP" + " ".repeat(2) + "EMP" + " ".repeat(30) + "PRESENT" + " ".repeat(2) + "INCREASE" + " ".repeat(6) + "PAY" + " ".repeat(9) + "NEW" + " ".repeat(4);
    private static String headingLine2 = "NUM" + " ".repeat(2) + "NAME" + " ".repeat(10) + "YEARS" + " ".repeat(2) + "POSITION" + " ".repeat(4) + "SALARY" + " ".repeat(7) + "%" + " ".repeat(6) + "INCREASE" + " ".repeat(5) + "SALARY";
    private static String subEmpLine1 = "EMPLOYEE CLASS:" + " ".repeat(8) + "Analyst" + " ".repeat(4) + "Sen Prog" + " ".repeat(4) + "Prog" + " ".repeat(4) + "Jr Prog" + " ".repeat(4) + "Unclassified";
    private static String subEmpLine2 = "# ON THIS PAGE:" + " ".repeat(13) + "00" + " ".repeat(10) + "00" + " ".repeat(6) + "00" + " ".repeat(9) + "00" + " ".repeat(14) + "00";
    private static int analystStotal = 0;
    private static int sprogStotal = 0;
    private static int progStotal = 0;
    private static int jrprogStotal = 0;
    private static int unclassStotal = 0;
    private static int analystCount = 0;
    private static int sprogCount = 0;
    private static int progCount = 0;
    private static int jrprogCount = 0;
    private static String avgTotalLine1 = "AVERAGE INCREASES:" + " ".repeat(3) + "ANALYST=" + "000,000.00" + " ".repeat(6) + "SEN PROG=" + "000,000.00";
    private static String avgTotalLine2 = " ".repeat(24) + "PROG=" + "000,000.00" + " ".repeat(7) + "JR PROG=" + "000,000.00";
    private static double analystIncTotal = 0.0;
    private static double sprogIncTotal = 0.0;
    private static double progIncTotal = 0.0;
    private static double jrprogIncTotal = 0.0;
    private static int lineCount = 0;
    private static String outputLine = "";
    private static int analystFinal = 0;
    private static int sprogFinal = 0;
    private static int progFinal = 0;
    private static int jrprogFinal = 0;
    private static int unclassFinal = 0;
    private static double analystAvgFinal = 0.0;
    private static double sprogAvgFinal = 0.0;
    private static double progAvgFinal = 0.0;
    private static double jrprogAvgFinal = 0.0;
    
    // Input record fields
    private static String empNum = "";
    private static String name = "";
    private static String eduCode = "";
    private static int years = 0;
    private static double curSalary = 0.0;
    
    public static void main(String[] args) {
        try {
            openFiles();
            heading();
            
            readInputFile();
            
            while (!eofFlag.equals("y")) {
                processMain();
                
                // Move input data to output record and print employee's salary
                outputLine = formatOutput();
                writeOutput(outputLine);
                writeOutput(newLine);
                
                // Check if we need to print headings and subtotals
                if (lineCount >= EMP_LIMIT) {
                    pageCount++;
                    subtotals();
                    newPageHead();
                    resetCountAndTotal();
                } else {
                    lineCount++;
                }
                
                readInputFile();
            }
            
            subtotalsAndAveragePay();
            closeFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void openFiles() throws IOException {
        inputFile = new BufferedReader(new FileReader(INPUT_FILE));
        outputFile = new PrintWriter(new FileWriter(OUTPUT_FILE));
    }
    
    private static void closeFiles() throws IOException {
        inputFile.close();
        outputFile.close();
    }
    
    private static void heading() throws IOException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
        String dateStr = dateFormat.format(now);
        String timeStr = timeFormat.format(now);
        
        // Update nameLine with actual date and time
        nameLine = "LAB 6" + " ".repeat(20) + dateStr + " ".repeat(5) + timeStr;
        
        writeOutput(nameLine);
        writeOutput(newLine);
        writeOutput(rptHeading + pageCount);
        writeOutput(newLine);
        writeOutput(headingLine1);
        writeOutput(newLine);
        writeOutput(headingLine2);
        writeOutput(newLine);
    }
    
    private static void readInputFile() throws IOException {
        String line = inputFile.readLine();
        if (line == null) {
            eofFlag = "y";
            return;
        }
        
        // Parse input line (assuming fixed width of 28 chars)
        empNum = line.substring(0, Math.min(3, line.length())).trim();
        name = line.substring(3, Math.min(18, line.length())).trim();
        eduCode = line.substring(18, Math.min(19, line.length())).trim();
        years = Integer.parseInt(line.substring(19, Math.min(21, line.length())).trim());
        curSalary = Double.parseDouble(line.substring(21, Math.min(28, line.length())).trim());
    }
    
    private static void processMain() {
        // Clear output buffer
        outputLine = "";
        
        if (eduCode.equals(GRADS)) {
            if (years <= G_UNCLASS_LTE_CONST) {
                processUnclass();
            }
            if (years < G_PROG_LT_CONST && years > G_PROG_GT_CONST) {
                processProg();
            }
            if (years <= SPROG_LTE_CONST && years >= SPROG_GTE_CONST) {
                processSprog();
            }
            if (years > ANALYST_GT_CONST) {
                processAnalyst();
            }
        }
        
        if (eduCode.equals(NON_GRADS)) {
            if (years > N_PROG_GT_CONST) {
                processProg();
            }
            if (years <= N_JRPROG_L