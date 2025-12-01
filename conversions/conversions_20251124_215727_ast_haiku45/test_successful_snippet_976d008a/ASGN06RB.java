import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ASGN06RB {
    private int wsInput1 = 0;
    private int wsInput2 = 0;
    private double wsAvg = 0;
    private int wsAvgRnd = 0;
    private int wsAvgInt = 0;
    private int wsUserAge = 0;
    private int wsWeightIn = 0;
    private double wsWeightMet = 0;
    private int wsInchHgtIn = 0;
    private double wsCmHgt = 0;
    private double wsBmi = 0;
    private double wsBmiMet = 0;
    private int wsLastLeapYear = 2016;
    private int wsNextLeapYear1 = 0;
    private int wsNextLeapYear2 = 0;
    private int wsNextLeapYear3 = 0;
    private int wsNextLeapYear4 = 0;
    private int wsUntilLeapYear = 0;
    private int wsTillCentury = 0;
    private int wsYearsOld = 0;
    private String wsEnd = " ";
    
    private String wsTimeHh = "";
    private String wsTimeMm = "";
    private String wsTimeSs = "";
    private String wsTimeHs = "";
    
    private String wsTodaysDate = "";
    private int ws8DateYear = 0;
    private int ws8DateCc = 0;
    private int ws8DateYy = 0;
    private String ws8DateMm = "";
    private String ws8DateDd = "";
    
    private Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        ASGN06RB program = new ASGN06RB();
        program.begin();
    }
    
    public void begin() {
        System.out.println("START ASGN06RB FOR Ryan Brooks");
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsTodaysDate = today.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE: " + wsTodaysDate);
        
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
        String timeStr = now.format(timeFormatter);
        wsTimeHh = timeStr.substring(0, 2);
        wsTimeMm = timeStr.substring(2, 4);
        wsTimeSs = timeStr.substring(4, 6);
        wsTimeHs = timeStr.substring(6, 8);
        
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHh + ":" + wsTimeMm);
        System.out.println("******");
        
        ws8DateYear = Integer.parseInt(wsTodaysDate.substring(0, 4));
        
        System.out.println("CALCULATE THE AVG OF 2 WHOLE NUMBERS");
        System.out.println("Enter a two-digit number");
        wsInput1 = scanner.nextInt();
        System.out.println("Enter another two-digit number");
        wsInput2 = scanner.nextInt();
        
        wsAvgInt = wsInput1 + wsInput2;
        wsAvg = (double) wsAvgInt / 2;
        System.out.println("The Average of " + wsInput1 + " and " + wsInput2 + " is " + wsAvg);
        
        wsAvgRnd = Math.round(wsAvgInt / 2.0f);
        System.out.println("The Rounded Average of " + wsInput1 + " and " + wsInput2 + " is " + wsAvgRnd);
        
        System.out.println("******");
        System.out.println("CALCULATE LEAP YEARS:");
        
        wsNextLeapYear1 = wsLastLeapYear + 4;
        System.out.println("NEXT LEAP YEAR: " + wsNextLeapYear1);
        
        wsNextLeapYear2 = wsNextLeapYear1 + 4;
        System.out.println("2ND LEAP YEAR FROM NOW: " + wsNextLeapYear2);
        
        wsNextLeapYear3 = wsNextLeapYear2 + 4;
        System.out.println("3RD LEAP YEAR FROM NOW: " + wsNextLeapYear3);
        
        wsNextLeapYear4 = wsNextLeapYear3 + 4;
        System.out.println("4TH LEAP YEAR FROM NOW: " + wsNextLeapYear4);
        
        wsUntilLeapYear = wsNextLeapYear1 - ws8DateYear;
        System.out.println("The next leap year will be in " + wsUntilLeapYear + " years");
        
        System.out.println("What is your age?");
        wsUserAge = scanner.nextInt();
        
        wsYearsOld = wsUserAge + wsUntilLeapYear;
        System.out.println("YOU WILL BE: " + wsYearsOld + " ON NEXT LEAP YEAR");
        
        wsYearsOld = wsYearsOld + 4;
        System.out.println("YOU WILL BE: " + wsYearsOld + " IN 2 LEAP YEARS");
        
        wsTillCentury = 100 - wsUserAge;
        System.out.println("You have " + wsTillCentury + " years until 100");
        System.out.println("******");
        
        System.out.println("ENTER YOUR WEIGHT IN LBS: ");
        wsWeightIn = scanner.nextInt();
        System.out.println("ENTER YOUR HEIGHT IN INCHES: ");
        wsInchHgtIn = scanner.nextInt();
        
        wsBmi = (double) wsWeightIn * 703 / (wsInchHgtIn * wsInchHgtIn);
        System.out.println("YOUR BMI IS: " + String.format("%.2f", wsBmi));
        System.out.println("******");
        
        wsCmHgt = wsInchHgtIn * 2.54;
        System.out.println("You are " + String.format("%.1f", wsCmHgt) + "cm tall");
        
        wsWeightMet = wsWeightIn * 0.453592;
        System.out.println("You weigh " + String.format("%.1f", wsWeightMet) + "kg");
        System.out.println("******");
        
        System.out.println("END OF ASSIGNMENT 06");
        System.out.println("Press Enter to exit");
        scanner.nextLine();
        scanner.nextLine();
    }
}