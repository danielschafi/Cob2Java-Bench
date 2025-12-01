```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.util.Scanner;

public class DateFormat {
    private static final String[] daysTable = {
        "Monday   ",
        "Tuesday  ",
        "Wednesday",
        "Thursday ",
        "Friday   ",
        "Saturday ",
        "Sunday   "
    };

    private static final String[] monthsTable = {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDateStr = today.format(formatter);

        String currentYear = currentDateStr.substring(0, 4);
        String currentMonth = currentDateStr.substring(4, 6);
        String currentDay = currentDateStr.substring(6, 8);

        System.out.println(currentYear + "-" + currentMonth + "-" + currentDay);

        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        if (dayOfWeekValue == 7) {
            dayOfWeekValue = 0;
        }
        dayOfWeekValue = dayOfWeekValue + 1;

        int monthValue = Integer.parseInt(currentMonth);

        String dayName = daysTable[dayOfWeekValue - 1].trim();
        String monthName = monthsTable[monthValue - 1].trim();

        System.out.println(dayName + ", " + monthName + " " + currentDay + ", " + currentYear);
    }
}