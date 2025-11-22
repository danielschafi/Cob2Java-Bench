import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Euler019 {
    public static void main(String[] args) {
        int countOfSundays = 0;
        int startYear = 1901;
        int startMonth = 1;
        int endYear = 2000;
        int endMonth = 12;

        for (int yearNum = startYear; yearNum <= endYear; yearNum++) {
            for (int monthNum = startMonth; monthNum <= endMonth; monthNum++) {
                LocalDate date = LocalDate.of(yearNum, monthNum, 1);
                int dayOfWeek = date.get(WeekFields.of(Locale.getDefault()).dayOfWeek());
                if (dayOfWeek == 7) { // 7 corresponds to Sunday in WeekFields
                    countOfSundays++;
                }
                System.out.println(date + ": " + dayOfWeek);
            }
        }
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}