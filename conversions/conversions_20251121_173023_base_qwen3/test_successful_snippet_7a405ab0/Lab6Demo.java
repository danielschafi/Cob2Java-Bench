import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab6Demo {
    private static final String INPUT_FILE_PATH = "lab8.dat";
    private static final String G_OUTPUT_FILE_PATH = "G-lab8.out";
    private static final String N_OUTPUT_FILE_PATH = "N-lab8.out";

    // File handlers
    private static BufferedReader inputFile;
    private static PrintWriter gOutputFile;
    private static PrintWriter nOutputFile;

    // Data records
    private static String inputLine;
    private static String gOutputLine;
    private static String nOutputLine;

    // Working storage variables
    private static char wsEofFlag = 'n';
    private static char wsResetFlag = 'n';
    private static char wsNoReset = 'n';
    private static char wsYesReset = 'y';
    private static char wsNewLine = ' ';

    // Name line with date/time
    private static String wsNameLine = "LAB 8" + " ".repeat(20) + "000000" + " ".repeat(5) + "00000000";

    // Report headers
    private static String wsGrGraduate = " ".repeat(20) + "GRADUATE";
    private static String wsNrNonGraduate = " ".repeat(20) + "NON-GRADUATE";
    private static String wsRptHeading2 = " ".repeat(18) + "EMPLOYEE SALARY REPORT" + " ".repeat(26) + "PAGE  " + "1";
    
    // Heading lines
    private static String wsHeadingLine1 = "EMP" + " ".repeat(2) + "EMP" + " ".repeat(30) + "PRESENT" + " ".repeat(2) + "INCREASE" + " ".repeat(6) + "PAY" + " ".repeat(9) + "NEW" + " ".repeat(4);
    private static String wsHeadingLine2 = "NUM" + " ".repeat(2) + "NAME" + " ".repeat(10) + "YEARS" + " ".repeat(2) + "POSITION" + " ".repeat(4) + "SALARY" + " ".repeat(7) + "%" + " ".repeat(6) + "INCREASE" + " ".repeat(5) + "SALARY";

    // Graduate sub-employee lines
    private static String wsGSubEmpL1 = "EMPLOYEE CLASS:" + " ".repeat(8) + "Analyst" + " ".repeat(4) + "Sen Prog" + " ".repeat(4) + "Prog" + " ".repeat(4) + "Unclassified";
    private static String wsGSubEmpL2 = "# ON THIS PAGE:" + " ".repeat(13) + "00" + " ".repeat(10) + "00" + " ".repeat(6) + "00" + " ".repeat(14) + "00";

    // Non-graduate sub-employee lines
    private static String wsNSubEmpL1 = "EMPLOYEE CLASS:" + " ".repeat(8) + "Prog" + " ".repeat(4) + "Jr Prog" + " ".repeat(4) + "Unclassified";
    private static String wsNSubEmpL2 = "# ON THIS PAGE:" + " ".repeat(10) + "00" + " ".repeat(9) + "00" + " ".repeat(14) + "00";

    // Counters
    private static int wsAnalystStotal = 0;
    private static int wsSprogStotal = 0;
    private static int wsProgStotal = 0;
    private static int wsJrprogStotal = 0;
    private static int wsUnclassStotal = 0;

    private static int wsAnalystCount = 0;
    private static int wsSprogCount = 0;
    private static int wsProgCount = 0;
    private static int wsJrprogCount = 0;

    // Averages
    private static double wsGAnalystAvgFinal = 0.0;
    private static double wsGSprogAvgFinal = 0.0;
    private static double wsGProgAvgFinal = 0.0;
    private static double wsNProgAvgFinal = 0.0;
    private static double wsNJrprogAvgFinal = 0.0;

    // Increase totals
    private static double wsAnalystIncTotal = 0.0;
    private static double wsSprogIncTotal = 0.0;
    private static double wsProgIncTotal = 0.0;
    private static double wsJrprogIncTotal = 0.0;

    // Output structure
    private static String olEmpNum;
    private static String olName;
    private static int olYears;
    private static String olPosition;
    private static double olCurSalary;
    private static double olIncPerc;
    private static char olPercSign;
    private static double olPayInc;
    private static char olPlusSign;
    private static double olNewSal;

    // Line counting and limits
    private static int wsLineCount = 0;
    private static int wsEmpLimit = 14;
    private static int wsPageCount = 1;

    // Constants
    private static final double WS_INCREASE_ANALYST_CONST = 0.118;
    private static final double WS_INCREASE_SPROG_CONST = 0.083;
    private static final double WS_INCREASE_PROG_CONST = 0.067;
    private static final double WS_INCREASE_JRPROG_CONST = 0.042;
    private static final int WS_PERC_CONVERT_CONST = 100;
    private static final char WS_PERC_SIGN_CONST = '%';
    private static final char WS_PLUS_SIGN_CONST = '+';
    private static final char WS_GRADS = 'G';
    private static final char WS_NON_GRADS = 'N';
    private static final String WS_CLASS_ANALYST_CONST = "ANALYST";
    private static final String WS_CLASS_SPROG_CONST = "SEN PROG";
    private static final String WS_CLASS_PROG_CONST = "PROG";
    private static final String WS_CLASS_JRPROG_CONST = "JR PROG";

    private static final int WS_ANALYST_GT_CONST = 15;
    private static final int WS_SPROG_LTE_CONST = 15;
    private static final int WS_SPROG_GTE_CONST = 7;
    private static final int WS_G_PROG_GT_CONST = 2;
    private static final int WS_G_PROG_LT_CONST = 7;
    private static final int WS_G_UNCLASS_LTE_CONST = 2;

    private static final int WS_N_PROG_GT_CONST = 10;
    private static final int WS_N_JRPROG_LTE_CONST = 10;
    private static final int WS_N_JRPROG_GT_CONST = 4;
    private static final int WS_N_UNCLASS_LTE_CONST = 4;

    private static final int WS_INC_ONE_CONST = 1;
    private static final int WS_RESET_ZERO_CONST = 0;

    public static void main(String[] args) {
        try {
            // Open files
            inputFile = new BufferedReader(new FileReader(INPUT_FILE_PATH));
            gOutputFile = new PrintWriter(new FileWriter(G_OUTPUT_FILE_PATH));
            nOutputFile = new PrintWriter(new FileWriter(N_OUTPUT_FILE_PATH));

            // Get current date and time
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
            
            String dateStr = dateFormat.format(now);
            String timeStr = timeFormat.format(now);
            
            wsNameLine = "LAB 8" + " ".repeat(20) + dateStr + " ".repeat(5) + timeStr;

            // Read first record
            inputLine = inputFile.readLine();
            if (inputLine == null) {
                wsEofFlag = 'y';
            }

            // Process records
            while (wsEofFlag != 'y') {
                // Check if graduate
                if (inputLine.charAt(18) == WS_GRADS) {
                    // Clear output buffer
                    clearOutputBuffer();
                    processGraduateMain();
                    
                    // Move input data to output record
                    moveInputData();
                    
                    // Print output
                    printGraduateOutput();
                    
                    // Handle paging
                    handleGraduatePaging();
                }
                
                // Check if non-graduate and no reset performed yet
                if (inputLine.charAt(18) == WS_NON_GRADS && wsResetFlag == wsNoReset) {
                    subtotalsAndAveragePayGraduate();
                    printGraduateAvgLine();
                    gOutputFile.close();
                    grandReset();
                    wsResetFlag = wsYesReset;
                    printNonGraduateHeading();
                }
                
                // Check if non-graduate and reset flag set
                if (inputLine.charAt(18) == WS_NON_GRADS && wsResetFlag == wsYesReset) {
                    // Clear output buffer
                    clearOutputBuffer();
                    processNonGraduateMain();
                    
                    // Move input data to output record
                    moveInputData();
                    
                    // Print output
                    printNonGraduateOutput();
                    
                    // Handle paging
                    handleNonGraduatePaging();
                }
                
                // Read next record
                inputLine = inputFile.readLine();
                if (inputLine == null