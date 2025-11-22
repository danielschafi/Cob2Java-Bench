import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final String[] DAYS_TABLE = {
        "Monday   ",
        "Tuesday  ",
        "Wednesday",
        "Thursday ",
        "Friday   ",
        "Saturday ",
        "Sunday   "
    };

    private static final String[] MONTHS_TABLE = {
        "January  ",
        "February ",
        "March    ",
        "April    ",
        "May      ",
        "June     ",
        "July     ",
        "August   ",
        "September",
        "October  ",
        "November ",
        "December "
    };

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        
        String year = String.format("%04d", today.getYear());
        String month = String.format("%02d", today.getMonthValue());
        String day = String.format("%02d", today.getDayOfMonth());
        
        System.out.println(year + "-" + month + "-" + day);
        
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        
        String dayName = DAYS_TABLE[dayOfWeekValue - 1].trim();
        String monthName = MONTHS_TABLE[today.getMonthValue() - 1].trim();
        
        System.out.println(dayName + ", " + monthName + " " + day + ", " + year);
    }
}