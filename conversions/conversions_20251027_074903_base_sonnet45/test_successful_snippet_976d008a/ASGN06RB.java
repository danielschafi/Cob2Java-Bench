import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ASGN06RB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int wsInput1 = 0;
        int wsInput2 = 0;
        double wsAvg = 0.0;
        int wsAvgRnd = 0;
        int wsAvgInt = 0;
        int wsUserAge = 0;
        int wsWeightIn = 0;
        double wsWeightMet = 0.0;
        int wsInchHgtIn = 0;
        double wsCmHgt = 0.0;
        double wsBmi = 0.0;
        double wsBmiMet = 0.0;
        int wsLastLeapYear = 2016;
        int wsNextLeapYear1 = 0;
        int wsNextLeapYear2 = 0;
        int wsNextLeapYear3 = 0;
        int wsNextLeapYear4 = 0;
        int wsUntilLeapYear = 0;
        int wsTillCentury = 0;
        int wsYearsOld = 0;
        
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        int ws8DateYear = today.getYear();
        String ws8DateMm = String.format("%02d", today.getMonthValue());
        String ws8DateDd = String.format("%02d", today.getDayOfMonth());
        String wsTodaysDate = String.format("%04d%s%s", ws8DateYear, ws8DateMm, ws8DateDd);
        
        String wsTimeHh = String.format("%02d", currentTime.getHour());
        String wsTimeMm = String.format("%02d", currentTime.getMinute());
        
        System.out.println("START ASGN06RB FOR Ryan Brooks");
        System.out.println("PROGRAM EXECUTION DATE: " + wsTodaysDate);
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHh + ":" + wsTimeMm);
        System.out.println("******");
        
        System.out.println("CALCULATE THE AVG OF 2 WHOLE NUMBERS");
        System.out.println("Enter a two-digit number");
        wsInput1 = scanner.nextInt();
        System.out.println("Enter another two-digit number");
        wsInput2 = scanner.nextInt();
        wsAvgInt = wsInput1 + wsInput2;
        wsAvg = wsAvgInt / 2.0;
        System.out.println("The Average of " + wsInput1 + " and " + wsInput2 + " is " + String.format("%.1f", wsAvg));
        wsAvgRnd = (int) Math.round(wsAvgInt / 2.0);
        System.out.println("The Rounded Average of " + wsInput1 + " and " + wsInput2 + " is " + String.format("%02d", wsAvgRnd));
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
        wsBmi = wsWeightIn * 703.0 / (wsInchHgtIn * wsInchHgtIn);
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
        
        scanner.close();
    }
}