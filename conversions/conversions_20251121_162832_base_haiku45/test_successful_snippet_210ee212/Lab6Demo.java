import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lab6Demo {
    private static final String INPUT_FILE = "lab6.dat";
    private static final String OUTPUT_FILE = "lab6.out";
    
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
    
    private static final double INCREASE_ANALYST = 0.118;
    private static final double INCREASE_SPROG = 0.083;
    private static final double INCREASE_PROG = 0.067;
    private static final double INCREASE_JRPROG = 0.042;
    private static final int PERC_CONVERT = 100;
    
    private String eofFlag = "n";
    private int pageCount = 1;
    private int lineCount = 0;
    private int empLimit = 14;
    
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
    
    private PrintWriter outputWriter;
    private BufferedReader inputReader;
    
    public static void main(String[] args) {
        Lab6Demo program = new Lab6Demo();
        program.run();
    }
    
    public void run() {
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
            outputWriter = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            heading100();
            
            String line = inputReader.readLine();
            if (line == null) {
                eofFlag = "y";
            }
            
            while (!eofFlag.equals("y")) {
                processLine(line);
                
                line = inputReader.readLine();
                if (line == null) {
                    eofFlag = "y";
                }
            }
            
            subtotalsAndAveragePay500();
            
            inputReader.close();
            outputWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processLine(String line) {
        if (line.length() < 28) {
            return;
        }
        
        String empNum = line.substring(0, 3);
        String name = line.substring(3, 18);
        String eduCode = line.substring(18, 19);
        int years = Integer.parseInt(line.substring(19, 21));
        double curSalary = Double.parseDouble(line.substring(21, 28)) / 100.0;
        
        String position = "";
        double incPerc = 0.0;
        double increaseByCalc = 0.0;
        double newSal = curSalary;
        String percSign = "";
        String plusSign = "";
        
        if (eduCode.equals(GRADS)) {
            if (years <= G_UNCLASS_LTE) {
                processUnclass250();
            }
            if (years < G_PROG_LT && years > G_PROG_GT) {
                position = CLASS_PROG;
                incPerc = PERC_CONVERT * INCREASE_PROG;
                increaseByCalc = Math.round(INCREASE_PROG * curSalary * 100.0) / 100.0;
                progIncTotal += increaseByCalc;
                newSal = increaseByCalc + curSalary;
                progStotal++;
                progCount++;
                percSign = "%";
                plusSign = "+";
            }
            if (years <= SPROG_LTE && years >= SPROG_GTE) {
                position = CLASS_SPROG;
                incPerc = PERC_CONVERT * INCREASE_SPROG;
                increaseByCalc = Math.round(INCREASE_SPROG * curSalary * 100.0) / 100.0;
                sprogIncTotal += increaseByCalc;
                newSal = increaseByCalc + curSalary;
                sprogStotal++;
                sprogCount++;
                percSign = "%";
                plusSign = "+";
            }
            if (years > ANALYST_GT) {
                position = CLASS_ANALYST;
                incPerc = PERC_CONVERT * INCREASE_ANALYST;
                increaseByCalc = Math.round(INCREASE_ANALYST * curSalary * 100.0) / 100.0;
                analystIncTotal += increaseByCalc;
                newSal = increaseByCalc + curSalary;
                analystStotal++;
                analystCount++;
                percSign = "%";
                plusSign = "+";
            }
        }
        
        if (eduCode.equals(NON_GRADS)) {
            if (years > N_PROG_GT) {
                position = CLASS_PROG;
                incPerc = PERC_CONVERT * INCREASE_PROG;
                increaseByCalc = Math.round(INCREASE_PROG * curSalary * 100.0) / 100.0;
                progIncTotal += increaseByCalc;
                newSal = increaseByCalc + curSalary;
                progStotal++;
                progCount++;
                percSign = "%";
                plusSign = "+";
            }
            if (years <= N_JRPROG_LTE && years > N_JRPROG_GT) {
                position = CLASS_JRPROG;
                incPerc = PERC_CONVERT * INCREASE_JRPROG;
                increaseByCalc = Math.round(INCREASE_JRPROG * curSalary * 100.0) / 100.0;
                jrprogIncTotal += increaseByCalc;
                newSal = increaseByCalc + curSalary;
                jrprogStotal++;
                jrprogCount++;
                percSign = "%";
                plusSign = "+";
            }
            if (years <= N_UNCLASS_LTE) {
                processUnclass250();
            }
        }
        
        String output = formatOutput(empNum, name, years, position, curSalary, incPerc, increaseByCalc, newSal, percSign, plusSign);
        outputWriter.println(output);
        outputWriter.println();
        
        if (lineCount >= empLimit) {
            pageCount++;
            subtotals300();
            newPageHead310();
            resetCountAndTotal400();
        } else {
            lineCount++;
        }
    }
    
    private void processUnclass250() {
        unclassStotal++;
    }
    
    private