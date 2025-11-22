import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Euler019 {
    private static int countOfSundays = 0;
    private static final int START_YEAR = 1901;
    private static final int END_YEAR = 2000;

    public static void main(String[] args) {
        for (int year = START_YEAR; year <= END_YEAR; year++) {
            for (int month = 1; month <= 12; month++) {
                LocalDate date = LocalDate.of(year, month, 1);
                long days = ChronoUnit.DAYS.between(LocalDate.of(1901, 1, 1), date);
                int dayOfWeek = (int) (days % 7);
                if (dayOfWeek == 0) {
                    countOfSundays++;
                }
            }
        }
        System.out.println("No Sundays the 1st every month: " + countOfSundays);
    }
}