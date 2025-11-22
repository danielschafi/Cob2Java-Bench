import java.time.LocalDate;
import java.time.DayOfWeek;

public class Euler019 {
    public static void main(String[] args) {
        int countOfSundays = 0;
        int startYear = 1901;
        int startMonth = 1;
        int endYear = 2000;
        
        for (int yearNum = startYear; yearNum <= endYear; yearNum++) {
            for (int monthNum = 1; monthNum <= 12; monthNum++) {
                LocalDate date = LocalDate.of(yearNum, monthNum, 1);
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                
                long dayNum = date.toEpochDay();
                long weekNum = dayNum / 7;
                long dayOfWeekNum = dayNum % 7;
                
                String dateStr = String.format("%04d%02d01", yearNum, monthNum);
                System.out.println(dateStr + ": " + weekNum + " * 7 + " + dayOfWeekNum + " = " + dayNum);
                
                if (dayOfWeek == DayOfWeek.SUNDAY) {
                    countOfSundays++;
                }
            }
        }
        
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}