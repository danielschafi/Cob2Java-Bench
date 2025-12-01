import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;

public class Euler019 {
    public static void main(String[] args) {
        String theDate;
        int dayNum;
        int weekNum;
        int dayOfWeekNum;
        int countOfSundays = 0;
        
        int startYear = 1901;
        int startMonth = 1;
        int startDay = 1;
        
        int endYear = 2000;
        int endMonth = 12;
        int endDay = 31;
        
        int yearNum;
        int monthNum;
        
        for (yearNum = startYear; yearNum <= endYear; yearNum++) {
            for (monthNum = 1; monthNum <= 12; monthNum++) {
                theDate = String.format("%04d%02d01", yearNum, monthNum);
                
                LocalDate date = LocalDate.of(yearNum, monthNum, 1);
                dayNum = (int) date.getLong(ChronoField.EPOCH_DAY);
                
                weekNum = dayNum / 7;
                dayOfWeekNum = dayNum % 7;
                
                System.out.println(theDate + ": " + weekNum + " * 7 + " + dayOfWeekNum + " = " + dayNum);
                
                if (dayOfWeekNum == 0) {
                    countOfSundays++;
                }
            }
        }
        
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}