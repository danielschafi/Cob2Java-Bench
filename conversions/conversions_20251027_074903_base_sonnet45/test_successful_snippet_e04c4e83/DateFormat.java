import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

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
        LocalDate currentDate = LocalDate.now();
        
        String currentYear = String.format("%04d", currentDate.getYear());
        String currentMonth = String.format("%02d", currentDate.getMonthValue());
        String currentDay = String.format("%02d", currentDate.getDayOfMonth());
        
        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);
        
        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();
        
        String dayName = DAYS_TABLE[currentDayOfWeek - 1].trim();
        String monthName = MONTHS_TABLE[Integer.parseInt(currentMonth) - 1].trim();
        
        System.out.println(dayName + ", " + monthName + " " + currentDay + ", " + currentYear);
    }
}