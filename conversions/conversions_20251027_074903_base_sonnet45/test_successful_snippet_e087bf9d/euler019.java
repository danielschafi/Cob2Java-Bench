import java.time.LocalDate;
import java.time.DayOfWeek;

public class euler019 {
    public static void main(String[] args) {
        int countOfSundays = 0;
        int startYear = 1901;
        int startMonth = 1;
        int endYear = 2000;
        int endMonth = 12;
        
        for (int yearNum = startYear; yearNum <= endYear; yearNum++) {
            for (int monthNum = 1; monthNum <= 12; monthNum++) {
                LocalDate date = LocalDate.of(yearNum, monthNum, 1);
                
                long dayNum = date.toEpochDay() + 719163;
                long weekNum = dayNum / 7;
                int dayOfWeekNum = (int)(dayNum % 7);
                
                System.out.printf("%04d%02d01: %d * 7 + %d = %d%n", 
                    yearNum, monthNum, weekNum, dayOfWeekNum, dayNum);
                
                if (dayOfWeekNum == 0) {
                    countOfSundays++;
                }
            }
        }
        
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}