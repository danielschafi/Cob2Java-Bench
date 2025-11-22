import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ASGN06RB {
    
    static class WorkingStorage {
        int wsInput1 = 0;
        int wsInput2 = 0;
        double wsAvg = 0;
        int wsAvgRnd = 0;
        int wsAvgInt = 0;
        int wsUserAge = 0;
        int wsWeightIn = 0;
        double wsWeightMet = 0;
        int wsInchHgtIn = 0;
        double wsCmHgt = 0;
        double wsBmi = 0;
        double wsBmiMet = 0;
        int wsLastLeapYear = 2016;
        int wsNextLeapYear1 = 0;
        int wsNextLeapYear2 = 0;
        int wsNextLeapYear3 = 0;
        int wsNextLeapYear4 = 0;
        int wsUntilLeapYear = 0;
        int wsTillCentury = 0;
        int wsYearsOld = 0;
        String wsEnd = " ";
        
        String wsTimeHh = "";
        String wsTimeMm = "";
        String wsTimeSs = "";
        String wsTimeHs = "";
        
        int ws8DateYear = 0;
        String ws8DateMm = "";
        String ws8DateDd = "";
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WorkingStorage ws = new WorkingStorage();
        
        System.out.println("START ASGN06RB FOR Ryan Brooks");
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todaysDate = today.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE: " + todaysDate);
        
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
        String timeStr = now.format(timeFormatter);
        ws.wsTimeHh = timeStr.substring(0, 2);
        ws.wsTimeMm = timeStr.substring(2, 4);
        ws.wsTimeSs = timeStr.substring(4, 6);
        ws.wsTimeHs = timeStr.substring(6, 8);
        
        System.out.println("PROGRAM EXECUTION START TIME: " + ws.wsTimeHh + ":" + ws.wsTimeMm);
        
        ws.ws8DateYear = today.getYear();
        ws.ws8DateMm = String.format("%02d", today.getMonthValue());
        ws.ws8DateDd = String.format("%02d", today.getDayOfMonth());
        
        System.out.println("******");
        
        System.out.println("CALCULATE THE AVG OF 2 WHOLE NUMBERS");
        System.out.println("Enter a two-digit number");
        ws.wsInput1 = scanner.nextInt();
        System.out.println("Enter another two-digit number");
        ws.wsInput2 = scanner.nextInt();
        
        ws.wsAvgInt = ws.wsInput1 + ws.wsInput2;
        ws.wsAvg = (double) ws.wsAvgInt / 2;
        System.out.println("The Average of " + ws.wsInput1 + " and " + ws.wsInput2 + " is " + String.format("%.1f", ws.wsAvg));
        
        ws.wsAvgRnd = Math.round(ws.wsAvgInt / 2.0f);
        System.out.println("The Rounded Average of " + ws.wsInput1 + " and " + ws.wsInput2 + " is " + ws.wsAvgRnd);
        
        System.out.println("******");
        
        System.out.println("CALCULATE LEAP YEARS:");
        ws.wsNextLeapYear1 = ws.wsLastLeapYear + 4;
        System.out.println("NEXT LEAP YEAR: " + ws.wsNextLeapYear1);
        
        ws.wsNextLeapYear2 = ws.wsNextLeapYear1 + 4;
        System.out.println("2ND LEAP YEAR FROM NOW: " + ws.wsNextLeapYear2);
        
        ws.wsNextLeapYear3 = ws.wsNextLeapYear2 + 4;
        System.out.println("3RD LEAP YEAR FROM NOW: " + ws.wsNextLeapYear3);
        
        ws.wsNextLeapYear4 = ws.wsNextLeapYear3 + 4;
        System.out.println("4TH LEAP YEAR FROM NOW: " + ws.wsNextLeapYear4);
        
        ws.wsUntilLeapYear = ws.wsNextLeapYear1 - ws.ws8DateYear;
        System.out.println("The next leap year will be in " + ws.wsUntilLeapYear + " years");
        
        System.out.println("What is your age?");
        ws.wsUserAge = scanner.nextInt();
        
        ws.wsYearsOld = ws.wsUserAge + ws.wsUntilLeapYear;
        System.out.println("YOU WILL BE: " + ws.wsYearsOld + " ON NEXT LEAP YEAR");
        
        ws.wsYearsOld = ws.wsYearsOld + 4;
        System.out.println("YOU WILL BE: " + ws.wsYearsOld + " IN 2 LEAP YEARS");
        
        ws.wsTillCentury = 100 - ws.wsUserAge;
        System.out.println("You have " + ws.wsTillCentury + " years until 100");
        System.out.println("******");
        
        System.out.println("ENTER YOUR WEIGHT IN LBS: ");
        ws.wsWeightIn = scanner.nextInt();
        System.out.println("ENTER YOUR HEIGHT IN INCHES: ");
        ws.wsInchHgtIn = scanner.nextInt();
        
        ws.wsBmi = (double) ws.wsWeightIn * 703 / (ws.wsInchHgtIn * ws.wsInchHgtIn);
        System.out.println("YOUR BMI IS: " + String.format("%.2f", ws.wsBmi));
        System.out.println("******");
        
        ws.wsCmHgt = ws.wsInchHgtIn * 2.54;
        System.out.println("You are " + String.format("%.1f", ws.wsCmHgt) + "cm tall");
        
        ws.wsWeightMet = ws.wsWeightIn * 0.453592;
        System.out.println("You weigh " + String.format("%.1f", ws.wsWeightMet) + "kg");
        
        System.out.println("******");
        
        System.out.println("END OF ASSIGNMENT 06");
        System.out.println("Press Enter to exit");
        scanner.nextLine();
        scanner.nextLine();
        
        scanner.close();
    }
}