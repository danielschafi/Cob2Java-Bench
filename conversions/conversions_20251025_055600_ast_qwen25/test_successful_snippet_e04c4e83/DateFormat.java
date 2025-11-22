import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.Month;

public class DateFormat {
    public static void main(String[] args) {
        String[] daysTable = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] monthsTable = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        LocalDate currentDate = LocalDate.now();
        String currentYear = String.format("%04d", currentDate.getYear());
        String currentMonth = String.format("%02d", currentDate.getMonthValue());
        String currentDay = String.format("%02d", currentDate.getDayOfMonth());

        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        Month month = currentDate.getMonth();

        System.out.println(
            daysTable[dayOfWeek.getValue() - 1] + ", " +
            monthsTable[month.getValue() - 1] + " " +
            currentDay + ", " +
            currentYear
        );
    }
}