import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab6Demo {
    private static final String INPUT_FILE_PATH = "lab8.dat";
    private static final String G_OUTPUT_FILE_PATH = "G-lab8.out";
    private static final String N_OUTPUT_FILE_PATH = "N-lab8.out";

    // File records
    private static class InputLine {
        String ilEmpNum;
        String ilName;
        String ilEduCode;
        int ilYears;
        double ilCurSalary;
    }

    private static class OutputLine {
        String content;
        OutputLine(String content) {
            this.content = content;
        }
    }

    // Working storage variables
    private static String wsEofFlag = "n";
    private static String wsResetFlag = "n";
    private static String wsNoReset = "n";
    private static String wsYesReset = "y";
    private static char wsNewLine = ' ';
    
    private static String wsNameLine = "LAB 8" + " ".repeat(20) + "000000" + " ".repeat(5) + "00000000";
    private static String wsGRptHeading1 = " ".repeat(20) + "GRADUATE";
    private static String wsNRptHeading1 = " ".repeat(20) + "NON-GRADUATE";
    private static String wsRptHeading2 = " ".repeat(18) + "EMPLOYEE SALARY REPORT" + " ".repeat(26) + "PAGE  " + "1";
    private static String wsHeadingLine1 = "EMP" + "  " + "EMP" + " ".repeat(30) + "PRESENT" + "  " + "INCREASE" + "      " + "PAY" + "   " + "NEW" + "    ";
    private static String wsHeadingLine2 = "NUM" + "  " + "NAME" + "          " + "YEARS" + "  " + "POSITION" + "    " + "SALARY" + "   " + "%" + "      " + "INCREASE" + "     " + "SALARY";
    
    private static String wsGSubEmpL1 = "EMPLOYEE CLASS:" + " ".repeat(8) + "Analyst" + "    " + "Sen Prog" + "    " + "Prog" + "    " + "Unclassified";
    private static String wsNSubEmpL1 = "EMPLOYEE CLASS:" + " ".repeat(8) + "Prog" + "    " + "Jr Prog" + "    " + "Unclassified";
    
    private static int wsGAnalystFinal = 0;
    private static int wsGSprogFinal = 0;
    private static int wsGProgFinal = 0;
    private static int wsGUnclassFinal = 0;
    private static int wsNProgFinal = 0;
    private static int wsNJrprogFinal = 0;
    private static int wsNUnclassFinal = 0;
    
    private static int wsAnalystStotal = 0;
    private static int wsSprogStotal = 0;
    private static int wsProgStotal = 0;
    private static int wsJrprogStotal = 0;
    private static int wsUnclassStotal = 0;
    
    private static int wsAnalystCount = 0;
    private static int wsSprogCount = 0;
    private static int wsProgCount = 0;
    private static int wsJrprogCount = 0;
    
    private static double wsGAnalystAvgFinal = 0.0;
    private static double wsGSprogAvgFinal = 0.0;
    private static double wsGProgAvgFinal = 0.0;
    private static double wsNProgAvgFinal = 0.0;
    private static double wsNJrprogAvgFinal = 0.0;
    
    private static double wsAnalystIncTotal = 0.0;
    private static double wsSprogIncTotal = 0.0;
    private static double wsProgIncTotal = 0.0;
    private static double wsJrprogIncTotal = 0.0;
    
    private static String wsOutput = "";
    private static int wsLineCount = 0;
    private static int wsEmpLimit = 14;
    private static double wsIncreaseByCalc = 0.0;
    
    private static final double WS_INCREASE_ANALYST_CONST = 0.118;
    private static final double WS_INCREASE_SPROG_CONST = 0.083;
    private static final double WS_INCREASE_PROG_CONST = 0.067;
    private static final double WS_INCREASE_JRPROG_CONST = 0.042;
    private static final int WS_PERC_CONVERT_CONST = 100;
    private static final char WS_PERC_SIGN_CONST = '%';
    private static final char WS_PLUS_SIGN_CONST = '+';
    
    private static final String WS_GRADS = "G";
    private static final String WS_NON_GRADS = "N";
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
    
    // Files
    private static BufferedReader inputFileReader;
    private static PrintWriter gOutputFileWriter;
    private static PrintWriter nOutputFileWriter;

    public static void main(String[] args) {
        try {
            openFiles();
            perform100GHeading();
            
            InputLine inputLine = readInputFile();
            if (inputLine == null) {
                wsEofFlag = "y";
            }
            
            while (!wsEofFlag.equals("y")) {
                if (inputLine.ilEduCode.equals(WS_GRADS)) {
                    wsOutput = "";
                    perform200GProcessMain(inputLine);
                    perform600MoveInput(inputLine);
                    perform610GPrintOutput();
                    perform700GPaging();
                }
                
                if (inputLine.ilEduCode.equals(WS_NON_GRADS) && wsResetFlag.equals(wsNoReset)) {
                    perform500GSubtotalsAndAveragePay();
                    perform510GPrintAvgLine();
                    closeGOutputFile();
                    perform600GrandReset();
                    wsResetFlag = "y";
                    perform100NHeading();
                }
                
                if (inputLine.ilEduCode.equals(WS_NON_GRADS) && wsResetFlag.equals(wsYesReset)) {
                    wsOutput = "";
                    perform210NProcessMain(inputLine);
                    perform600MoveInput(inputLine);
                    perform620NPrintOutput();
                    perform710NPaging();
                }
                
                inputLine = readInputFile();
                if (inputLine == null) {
                    wsEofFlag = "y";
                }
            }
            
            perform505NSubtotalsAndAveragePay();
            perform515NPrintAvgLine();
            closeNOutputFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void openFiles() throws IOException {
        inputFileReader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
        gOutputFileWriter = new PrintWriter(new FileWriter(G_OUTPUT_FILE_PATH));
        nOutputFileWriter = new PrintWriter(new FileWriter(N_OUTPUT_FILE_PATH));
    }
    
    private static void closeGOutputFile() {
        gOutputFileWriter.close();
    }
    
    private static void closeNOutputFile() {
        nOutputFileWriter.close();
    }
    
    private static InputLine readInputFile() throws IOException {
        String line = inputFileReader.readLine();
        if (line == null) return null;
        
        InputLine inputLine = new InputLine();
        inputLine.ilEmpNum = line.substring(0, 3).trim();
        inputLine.ilName = line.substring(3, 18).trim();
        inputLine.ilEduCode = line.substring(18, 19);
        inputLine.ilYears = Integer.parseInt(line.substring(19, 21));
        inputLine.ilCurSalary = Double.parseDouble(line.substring(21, 28).replace(",", ""));
        return inputLine;
    }
    
    private static void perform100GHeading() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
        String dateStr = dateFormat.format