import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ASGN06RB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

        int WS_INPUT_1 = 0;
        int WS_INPUT_2 = 0;
        double WS_AVG = 0.0;
        int WS_AVG_RND = 0;
        int WS_AVG_INT = 0;
        int WS_USER_AGE = 0;
        int WS_WEIGHT_IN = 0;
        double WS_WEIGHT_MET = 0.0;
        int WS_INCH_HGT_IN = 0;
        double WS_CM_HGT = 0.0;
        double WS_BMI = 0.0;
        double WS_BMI_MET = 0.0;
        int WS_LAST_LEAP_YEAR = 2016;
        int WS_NEXT_LEAP_YEAR_1 = 0;
        int WS_NEXT_LEAP_YEAR_2 = 0;
        int WS_NEXT_LEAP_YEAR_3 = 0;
        int WS_NEXT_LEAP_YEAR_4 = 0;
        int WS_UNTIL_LEAP_YEAR = 0;
        int WS_TILL_CENTURY = 0;
        int WS_YEARS_OLD = 0;
        String WS_END = " ";

        String WS_TIME = timeFormat.format(new Date());
        String WS_TIME_HH = WS_TIME.substring(0, 2);
        String WS_TIME_MM = WS_TIME.substring(2, 4);
        String WS_TIME_SS = WS_TIME.substring(4, 6);
        String WS_TIME_HS = "00";

        String WS_TODAYS_DATE = dateFormat.format(new Date());
        String WS_8_DATE_YEAR = WS_TODAYS_DATE.substring(0, 4);
        String WS_8_DATE_CC = WS_TODAYS_DATE.substring(0, 2);
        String WS_8_DATE_YY = WS_TODAYS_DATE.substring(2, 4);
        String WS_8_DATE_MM = WS_TODAYS_DATE.substring(4, 6);
        String WS_8_DATE_DD = WS_TODAYS_DATE.substring(6, 8);

        System.out.println("START ASGN06RB FOR Ryan Brooks");
        System.out.println("PROGRAM EXECUTION DATE: " + WS_TODAYS_DATE);
        System.out.println("PROGRAM EXECUTION START TIME: " + WS_TIME_HH + ":" + WS_TIME_MM);
        System.out.println("******");

        System.out.println("CALCULATE THE AVG OF 2 WHOLE NUMBERS");
        System.out.println("Enter a two-digit number");
        WS_INPUT_1 = scanner.nextInt();
        System.out.println("Enter another two-digit number");
        WS_INPUT_2 = scanner.nextInt();
        WS_AVG_INT = WS_INPUT_1 + WS_INPUT_2;
        WS_AVG = WS_AVG_INT / 2.0;
        System.out.println("The Average of " + WS_INPUT_1 + " and " + WS_INPUT_2 + " is " + String.format("%.2f", WS_AVG));
        WS_AVG_RND = Math.round(WS_AVG_INT / 2.0);
        System.out.println("The Rounded Average of " + WS_INPUT_1 + " and " + WS_INPUT_2 + " is " + WS_AVG_RND);
        System.out.println("******");

        System.out.println("CALCULATE LEAP YEARS:");
        WS_NEXT_LEAP_YEAR_1 = WS_LAST_LEAP_YEAR + 4;
        System.out.println("NEXT LEAP YEAR: " + WS_NEXT_LEAP_YEAR_1);
        WS_NEXT_LEAP_YEAR_2 = WS_NEXT_LEAP_YEAR_1 + 4;
        System.out.println("2ND LEAP YEAR FROM NOW: " + WS_NEXT_LEAP_YEAR_2);
        WS_NEXT_LEAP_YEAR_3 = WS_NEXT_LEAP_YEAR_2 + 4;
        System.out.println("3RD LEAP YEAR FROM NOW: " + WS_NEXT_LEAP_YEAR_3);
        WS_NEXT_LEAP_YEAR_4 = WS_NEXT_LEAP_YEAR_3 + 4;
        System.out.println("4TH LEAP YEAR FROM NOW: " + WS_NEXT_LEAP_YEAR_4);

        WS_UNTIL_LEAP_YEAR = WS_NEXT_LEAP_YEAR_1 - Integer.parseInt(WS_8_DATE_YEAR);
        System.out.println("The next leap year will be in " + WS_UNTIL_LEAP_YEAR + " years");

        System.out.println("What is your age?");
        WS_USER_AGE = scanner.nextInt();
        WS_YEARS_OLD = WS_USER_AGE + WS_UNTIL_LEAP_YEAR;
        System.out.println("YOU WILL BE: " + WS_YEARS_OLD + " ON NEXT LEAP YEAR");
        WS_YEARS_OLD = WS_YEARS_OLD + 4;
        System.out.println("YOU WILL BE: " + WS_YEARS_OLD + " IN 2 LEAP YEARS");

        WS_TILL_CENTURY = 100 - WS_USER_AGE;
        System.out.println("You have " + WS_TILL_CENTURY + " years until 100");
        System.out.println("******");

        System.out.println("ENTER YOUR WEIGHT IN LBS: ");
        WS_WEIGHT_IN = scanner.nextInt();
        System.out.println("ENTER YOUR HEIGHT IN INCHES: ");
        WS_INCH_HGT_IN = scanner.nextInt();
        WS_BMI = WS_WEIGHT_IN * 703.0 / (WS_INCH_HGT_IN * WS_INCH_HGT_IN);
        System.out.println("YOUR BMI IS: " + String.format("%.2f", WS_BMI));
        System.out.println("******");

        WS_CM_HGT = WS_INCH_HGT_IN * 2.54;
        System.out.println("You are " + String.format("%.2f", WS_CM_HGT) + "cm tall");
        WS_WEIGHT_MET = WS_WEIGHT_IN * 0.453592;
        System.out.println("You weigh " + String.format("%.2f", WS_WEIGHT_MET) + "kg");
        System.out.println("******");

        System.out.println("END OF ASSIGNMENT 06");
        System.out.println("Press Enter to exit");
        WS_END = scanner.nextLine();
        scanner.nextLine();
    }
}