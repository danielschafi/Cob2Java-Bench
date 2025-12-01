import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lab6Demo {
    private static class InputRecord {
        String empNum;
        String name;
        char eduCode;
        int years;
        double curSalary;
    }

    private static class OutputRecord {
        String empNum = "";
        String name = "";
        int years = 0;
        String position = "";
        double curSalary = 0.0;
        double incPerc = 0.0;
        char percSign = '%';
        double payInc = 0.0;
        char plusSign = '+';
        double newSal = 0.0;
    }

    private String eofFlag = "n";
    private String resetFlag = "n";
    private String noReset = "n";
    private String yesReset = "y";
    private String newLine = " ";

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

    private double increaseByCalc = 0.0;

    private final double INCREASE_ANALYST = 0.118;
    private final double INCREASE_SPROG = 0.083;
    private final double INCREASE_PROG = 0.067;
    private final double INCREASE_JRPROG = 0.042;
    private final int PERC_CONVERT = 100;

    private final String GRADS = "G";
    private final String NON_GRADS = "N";
    private final String CLASS_ANALYST = "ANALYST";
    private final String CLASS_SPROG = "SEN PROG";
    private final String CLASS_PROG = "PROG";
    private final String CLASS_JRPROG = "JR PROG";

    private BufferedReader inputFile;
    private PrintWriter gOutputFile;
    private PrintWriter nOutputFile;

    private OutputRecord output = new OutputRecord();

    public static void main(String[] args) {
        Lab6Demo program = new Lab6Demo();
        program.run();
    }

    private void run() {
        try {
            inputFile = new BufferedReader(new FileReader("lab8.dat"));
            gOutputFile = new PrintWriter(new FileWriter("G-lab8.out"));
            nOutputFile = new PrintWriter(new FileWriter("N-lab8.out"));

            headingG();
            String line = inputFile.readLine();
            if (line == null) eofFlag = "y";

            while (!eofFlag.equals("y")) {
                InputRecord input = parseLine(line);

                if (input.eduCode == GRADS.charAt(0)) {
                    output = new OutputRecord();
                    processMainG(input);
                    moveInput(input);
                    printOutputG();
                    pagingG();
                }

                if (input.eduCode == NON_GRADS.charAt(0) && resetFlag.equals(noReset)) {
                    subtotalsAndAveragePayG();
                    printAvgLineG();
                    gOutputFile.close();
                    grandReset();
                    resetFlag = yesReset;
                    headingN();
                }

                if (input.eduCode == NON_GRADS.charAt(0) && resetFlag.equals(yesReset)) {
                    output = new OutputRecord();
                    processMainN(input);
                    moveInput(input);
                    printOutputN();
                    pagingN();
                }

                line = inputFile.readLine();
                if (line == null) eofFlag = "y";
            }

            subtotalsAndAveragePayN();
            printAvgLineN();
            nOutputFile.close();
            inputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputRecord parseLine(String line) {
        InputRecord rec = new InputRecord();
        if (line.length() >= 28) {
            rec.empNum = line.substring(0, 3);
            rec.name = line.substring(3, 18);
            rec.eduCode = line.charAt(18);
            rec.years = Integer.parseInt(line.substring(19, 21));
            rec.curSalary = Double.parseDouble(line.substring(21, 28)) / 100.0;
        }
        return rec;
    }

    private void headingG() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        gOutputFile.println("LAB 8                    " + today.format(dateFormatter) + "     " + now.format(timeFormatter));
        gOutputFile.println();
        gOutputFile.println("                GRADUATE");
        gOutputFile.println("                  EMPLOYEE SALARY REPORT                          PAGE   " + pageCount);
        gOutputFile.println();
        gOutputFile.println("EMP  EMP                              PRESENT  INCREASE      PAY         NEW    ");
        gOutputFile.println("NUM  NAME                  YEARS    POSITION   SALARY      %      INCREASE   SALARY");
        gOutputFile.println();
    }

    private void headingN() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssss");

        nOutputFile.println("LAB 8                    " + today.format(dateFormatter) + "     " + now.format(timeFormatter));
        nOutputFile.println();
        nOutputFile.println("                NON-GRADUATE");
        nOutputFile.println("                  EMPLOYEE SALARY REPORT                          PAGE   " + pageCount);
        nOutputFile.println();
        nOutputFile.println("EMP  EMP                              PRESENT  INCREASE      PAY         NEW    ");
        nOutputFile.println("NUM  NAME                  YEARS    POSITION   SALARY      %      INCREASE   SALARY");
        nOutputFile.println();
    }

    private void processMainG(InputRecord input) {
        output.position = "";
        if (input.eduCode == GRADS.charAt(0)) {
            if (input.years <= 2) processUnclass(input);
            if (input.years < 7 && input.years > 2) processProg(input);
            if (input.years <= 15 && input.years >= 7) processSprog(input);
            if (input.years > 15) processAnalyst(input);
        }
    }

    private void processMainN(InputRecord input) {
        if (input.eduCode == NON_GRADS.charAt(0)) {
            if (input.years > 10) processProg(input);
            if (input.years <= 10 && input.years > 4) processJrprog(input);
            if (input.years <= 4) processUnclass(input);
        }
    }

    private void processAnalyst(InputRecord input) {
        output.position = CLASS_ANALYST;
        output.incPerc = PERC_CONVERT * INCREASE_ANALYST;
        increaseByCalc = Math.round(INCREASE_ANALYST * input.curSalary * 100.0) / 100.0;
        output.payIn