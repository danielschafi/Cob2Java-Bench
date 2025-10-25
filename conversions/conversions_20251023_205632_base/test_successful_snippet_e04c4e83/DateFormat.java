import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.Month;

public class DateFormat {
    public static void main(String[] args) {
        String[] daysTable = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] monthsTable = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        String currentYear = formattedDate.substring(0, 4);
        String currentMonth = formattedDate.substring(5, 7);
        String currentDay = formattedDate.substring(8, 10);

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        int currentDayOfWeek = dayOfWeek.getValue() % 7;

        Month month = currentDate.getMonth();
        int currentMonthValue = month.getValue() - 1;

        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);
        System.out.println(daysTable[currentDayOfWeek] + ", " + monthsTable[currentMonthValue] + " " + currentDay + ", " + currentYear);
    }
}