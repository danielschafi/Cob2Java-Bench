import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ASGN06RB {
    private static Scanner scanner = new Scanner(System.in);
    
    // Working storage variables
    private static int wsInput1 = 0;
    private static int wsInput2 = 0;
    private static double wsAvg = 0.0;
    private static int wsAvgRnd = 0;
    private static int wsAvgInt = 0;
    private static int wsUserAge = 0;
    private static int wsWeightIn = 0;
    private static double wsWeightMet = 0.0;
    private static int wsInchHgtIn = 0;
    private static double wsCmHgt = 0.0;
    private static double wsBmi = 0.0;
    private static double wsBmiMet = 0.0;
    private static int wsLastLeapYear = 2016;
    private static int wsNextLeapYear1 = 0;
    private static int wsNextLeapYear2 = 0;
    private static int wsNextLeapYear3 = 0;
    private static int wsNextLeapYear4 = 0;
    private static int wsUntilLeapYear = 0;
    private static int wsTillCentury = 0;
    private static int wsYearsOld = 0;
    private static char wsEnd = ' ';
    
    // Time and date variables
    private static String wsTimeHH = "";
    private static String wsTimeMM = "";
    private static String wsTimeSS = "";
    private static String wsTimeHS = "";
    private static int ws8DateYear = 0;
    private static int ws8DateCc = 0;
    private static int ws8DateYy = 0;
    private static String ws8DateMm = "";
    private static String ws8DateDd = "";

    public static void main(String[] args) {
        System.out.println("START ASGN06RB FOR Ryan Brooks");
        
        // Get today's date
        LocalDate currentDate = LocalDate.now();
        ws8DateYear = currentDate.getYear();
        ws8DateCc = ws8DateYear / 100;
        ws8DateYy = ws8DateYear % 100;
        ws8DateMm = String.format("%02d", currentDate.getMonthValue());
        ws8DateDd = String.format("%02d", currentDate.getDayOfMonth());
        
        System.out.println("PROGRAM EXECUTION DATE: " + ws8DateYear + ws8DateMm + ws8DateDd);
        
        // Get current time
        LocalTime currentTime = LocalTime.now();
        wsTimeHH = String.format("%02d", currentTime.getHour());
        wsTimeMM = String.format("%02d", currentTime.getMinute());
        wsTimeSS = String.format("%02d", currentTime.getSecond());
        wsTimeHS = "00"; // Not using hundredths
        
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHH + ":" + wsTimeMM);
        System.out.println("******");
        
        // Calculate average of two whole numbers
        System.out.println("CALCULATE THE AVG OF 2 WHOLE NUMBERS");
        System.out.println("Enter a two-digit number");
        wsInput1 = scanner.nextInt();
        System.out.println("Enter another two-digit number");
        wsInput2 = scanner.nextInt();
        
        wsAvgInt = wsInput1 + wsInput2;
        wsAvg = wsAvgInt / 2.0;
        System.out.println("The Average of " + wsInput1 + " and " + wsInput2 + " is " + String.format("%.1f", wsAvg));
        
        wsAvgRnd = (int) Math.round(wsAvg);
        System.out.println("The Rounded Average of " + wsInput1 + " and " + wsInput2 + " is " + wsAvgRnd);
        System.out.println("******");
        
        // Calculate leap years
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
        System.out.println("******");
        
        // User age calculations
        System.out.println("What is your age?");
        wsUserAge = scanner.nextInt();
        
        wsYearsOld = wsUserAge + wsUntilLeapYear;
        System.out.println("YOU WILL BE: " + wsYearsOld + " ON NEXT LEAP YEAR");
        wsYearsOld += 4;
        System.out.println("YOU WILL BE: " + wsYearsOld + " IN 2 LEAP YEARS");
        
        wsTillCentury = 100 - wsUserAge;
        System.out.println("You have " + wsTillCentury + " years until 100");
        System.out.println("******");
        
        // BMI calculations
        System.out.println("ENTER YOUR WEIGHT IN LBS: ");
        wsWeightIn = scanner.nextInt();
        System.out.println("ENTER YOUR HEIGHT IN INCHES: ");
        wsInchHgtIn = scanner.nextInt();
        
        wsBmi = (wsWeightIn * 703.0) / (wsInchHgtIn * wsInchHgtIn);
        System.out.println("YOUR BMI IS: " + String.format("%.2f", wsBmi));
        System.out.println("******");
        
        wsCmHgt = wsInchHgtIn * 2.54;
        System.out.println("You are " + String.format("%.1f", wsCmHgt) + "cm tall");
        
        wsWeightMet = wsWeightIn * 0.453592;
        System.out.println("You weigh " + String.format("%.3f", wsWeightMet) + "kg");
        
        System.out.println("******");
        System.out.println("END OF ASSIGNMENT 06");
        System.out.println("Press Enter to exit");
        scanner.nextLine(); // consume newline
        scanner.nextLine(); // wait for enter key
    }
}