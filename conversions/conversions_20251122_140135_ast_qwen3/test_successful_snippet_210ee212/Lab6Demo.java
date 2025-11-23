import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab6Demo {
    private static final String INPUT_FILE_NAME = "lab6.dat";
    private static final String OUTPUT_FILE_NAME = "lab6.out";
    
    // File records
    private static class InputRecord {
        String empNum;
        String name;
        String eduCode;
        int years;
        double curSalary;
    }
    
    private static class OutputRecord {
        String empNum;
        String name;
        int years;
        String position;
        double curSalary;
        double incPerc;
        String percSign;
        double payInc;
        String plusSign;
        double newSal;
    }
    
    // Working storage variables
    private static boolean eofFlag = false;
    private static char newLine = '\n';
    
    // Report headers and formatting
    private static String[] nameLine = {"LAB 6", "", "", "", ""};
    private static String[] rptHeading = {"", "EMPLOYEE SALARY REPORT", "", "", "PAGE  ", "1"};
    private static String[] headingLine1 = {"EMP", " ", "EMP", "", "PRESENT", " ", "INCREASE", " ", "PAY", "", "NEW", " "};
    private static String[] headingLine2 = {"NUM", " ", "NAME", " ", "YEARS", " ", "POSITION", " ", "SALARY", " ", "%", " ", "INCREASE", " ", "SALARY"};
    private static String[] subEmpL1 = {"EMPLOYEE CLASS:", " ", "Analyst", " ", "Sen Prog", " ", "Prog", " ", "Jr Prog", " ", "Unclassified"};
    private static String[] subEmpL2 = {"# ON THIS PAGE:", " ", "0", " ", "0", " ", "0", " ", "0", " ", "0"};
    
    // Counters and totals
    private static int[] analystStotal = {0};
    private static int[] sprogStotal = {0};
    private static int[] progStotal = {0};
    private static int[] jrprogStotal = {0};
    private static int[] unclassStotal = {0};
    
    private static int[] analystCount = {0};
    private static int[] sprogCount = {0};
    private static int[] progCount = {0};
    private static int[] jrprogCount = {0};
    
    private static double[] analystIncTotal = {0.0};
    private static double[] sprogIncTotal = {0.0};
    private static double[] progIncTotal = {0.0};
    private static double[] jrprogIncTotal = {0.0};
    
    private static double[] analystAvgFinal = {0.0};
    private static double[] sprogAvgFinal = {0.0};
    private static double[] progAvgFinal = {0.0};
    private static double[] jrprogAvgFinal = {0.0};
    
    private static int[] lineCount = {0};
    private static int[] pageCount = {1};
    private static int empLimit = 14;
    
    // Constants
    private static final double increaseAnalystCnst = 0.118;
    private static final double increaseSprogCnst = 0.083;
    private static final double increaseProgCnst = 0.067;
    private static final double increaseJrprogCnst = 0.042;
    private static final int percConvertConst = 100;
    private static final String percSignCnst = "%";
    private static final String plusSignCnst = "+";
    private static final String GRADS = "G";
    private static final String NON_GRADS = "N";
    private static final String CLASS_ANALYST = "ANALYST";
    private static final String CLASS_SPROG = "SEN PROG";
    private static final String CLASS_PROG = "PROG";
    private static final String CLASS_JRPROG = "JR PROG";
    private static final int ANALYST_GT_CNST = 15;
    private static final int SPROG_LTE_CNST = 15;
    private static final int SPROG_GTE_CNST = 7;
    private static final int G_PROG_GT_CNST = 2;
    private static final int G_PROG_LT_CNST = 7;
    private static final int G_UNCLASS_LTE_CNST = 2;
    private static final int N_PROG_GT_CNST = 10;
    private static final int N_JRPROG_LTE_CNST = 10;
    private static final int N_JRPROG_GT_CNST = 4;
    private static final int N_UNCLASS_LTE_CNST = 4;
    private static final int INC_ONE_CNST = 1;
    private static final int RESET_ZERO_CNST = 0;
    
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(INPUT_FILE_NAME));
            PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME));
            
            // Set up date/time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
            nameLine[2] = dateFormat.format(calendar.getTime());
            nameLine[4] = timeFormat.format(calendar.getTime());
            
            // Print header
            printHeader(writer);
            
            // Read first record
            InputRecord inputRec = readInputRecord(scanner);
            if (inputRec == null) {
                eofFlag = true;
            }
            
            while (!eofFlag) {
                // Clear output buffer
                OutputRecord outputRec = new OutputRecord();
                
                // Process main logic
                processMain(inputRec, outputRec);
                
                // Move input data to output record
                outputRec.empNum = inputRec.empNum;
                outputRec.name = inputRec.name;
                outputRec.years = inputRec.years;
                outputRec.curSalary = inputRec.curSalary;
                
                // Write output record
                writeOutput(writer, outputRec);
                writer.println();
                
                // Check if we need to print subtotals and reset counters
                if (lineCount[0] >= empLimit) {
                    pageCount[0]++;
                    printSubtotals(writer);
                    printNewPageHead(writer);
                    resetCountAndTotal();
                } else {
                    lineCount[0] += INC_ONE_CNST;
                }
                
                // Read next input record
                inputRec = readInputRecord(scanner);
                if (inputRec == null) {
                    eofFlag = true;
                }
            }
            
            // Print final subtotals and averages
            printSubtotalsAndAveragePay(writer);
            
            scanner.close();
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static InputRecord readInputRecord(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return null;
        }
        
        String line = scanner.nextLine();
        if (line.length() < 28) {
            return null;
        }
        
        InputRecord rec = new InputRecord();
        rec.empNum = line.substring(0, 3).trim();
        rec.name = line.substring(3, 18).trim();
        rec.eduCode = line.substring(18, 19);
        rec.years = Integer.parseInt(line.substring(19, 21).trim());
        rec.curSalary = Double.parseDouble(line.substring(21, 28).replace(",", ""));
        
        return rec;
    }
    
    private static void printHeader(PrintWriter writer) {
        writer.println(nameLine[0] + nameLine[1] + nameLine[2] + nameLine[3] + nameLine[4]);
        writer.println();
        writer.println(rptHeading[0] + rptHeading[1] + rptHeading[2] + rptHeading[3] + rptHeading[4] + rptHeading[5]);
        writer.println();
        writer.println(headingLine1[0] + headingLine1[1] + headingLine1[2] + headingLine1[3] + headingLine1[4] + headingLine1[5] + headingLine1[6] + headingLine1[7] + headingLine1[8] + headingLine1[9] + headingLine1[10] + headingLine1[11]);
        writer.println(headingLine2[0] + headingLine2[1] + headingLine2[2] + headingLine2[3] + headingLine2[4] + headingLine2[5] + headingLine2[6] + headingLine2[7] + headingLine2[8] + headingLine2[9] + headingLine2[10] + headingLine2[11] + headingLine2[12] + headingLine2[13] + headingLine2[14]);
        writer.println();
    }
    
    private static void processMain(InputRecord inputRec, OutputRecord outputRec) {
        outputRec.position = "";
        
        if (inputRec.eduCode.equals(GRADS)) {
            if (inputRec.years <= G_UNCLASS_LTE_CNST) {
                processUnclass(inputRec, outputRec);
            }
            
            if (inputRec.years < G_PROG_LT_CNST && inputRec.years > G_PROG_GT_CNST) {
                processProg(inputRec, outputRec);
            }
            
            if (inputRec.years <= SPROG_LTE_CNST && inputRec.y