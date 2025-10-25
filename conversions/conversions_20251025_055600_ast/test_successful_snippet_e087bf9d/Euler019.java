import java.time.LocalDate;
import java.time.DayOfWeek;

public class Euler019 {
    public static void main(String[] args) {
        int countOfSundays = 0;
        int startYear = 1901;
        int startMonth = 1;
        int endYear = 2000;
        int endMonth = 12;

        for (int yearNum = startYear; yearNum <= endYear; yearNum++) {
            for (int monthNum = 1; monthNum <= endMonth; monthNum++) {
                LocalDate date = LocalDate.of(yearNum, monthNum, 1);
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                if (dayOfWeek == DayOfWeek.SUNDAY) {
                    countOfSundays++;
                }
                System.out.println(date + ": " + dayOfWeek);
            }
        }
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}