import java.time.*;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    public static void main(String[] args) {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Extract components
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        
        // Create date string in YYYYMMDD format
        String dateStr = String.format("%04d%02d%02d", year, month, day);
        int dateInt = Integer.parseInt(dateStr);
        
        // Calculate total seconds since epoch
        long totalSeconds = (long)dateInt * 86400L + 
                           (long)hour * 3600L + 
                           (long)minute * 60L + 
                           (long)second;
        
        // GMT offset values (example: +0500 for UTC+5)
        int gmtHours = 0;   // Modify as needed
        int gmtMinutes = 0; // Modify as needed
        char gmtDirection = '+'; // Modify as needed
        
        // Calculate offset in seconds
        long offsetSeconds = (long)gmtHours * 3600L + (long)gmtMinutes * 60L;
        
        // Apply offset (reverse direction)
        if (gmtDirection == '+') {
            totalSeconds -= offsetSeconds;
        } else {
            totalSeconds += offsetSeconds;
        }
        
        // Convert back to date/time
        long days = totalSeconds / 86400L;
        long remainder = totalSeconds % 86400L;
        
        // Convert days back to date
        LocalDate convertedDate = LocalDate.ofEpochDay(days);
        int convertedYear = convertedDate.getYear();
        int convertedMonth = convertedDate.getMonthValue();
        int convertedDay = convertedDate.getDayOfMonth();
        
        // Convert remainder to time
        int convertedHour = (int)(remainder / 3600L);
        remainder %= 3600L;
        int convertedMinute = (int)(remainder / 60L);
        int convertedSecond = (int)(remainder % 60L);
        
        // Format output
        String displayDate = String.format("%02d/%02d/%04d", convertedMonth, convertedDay, convertedYear);
        String displayTime = String.format("%02d:%02d:%02d", convertedHour, convertedMinute, convertedSecond);
        
        System.out.println("Current GMT " + displayDate + " " + displayTime);
    }
}