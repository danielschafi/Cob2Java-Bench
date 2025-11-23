import java.time.*;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    public static void main(String[] args) {
        // Get current date and time
        ZonedDateTime now = ZonedDateTime.now();
        
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
        
        // Convert date to seconds since epoch
        LocalDate localDate = LocalDate.of(year, month, day);
        long dateSeconds = localDate.toEpochDay() * 86400L;
        
        // Convert time to seconds
        long timeSeconds = hour * 3600L + minute * 60L + second;
        
        // Total seconds
        long totalSeconds = dateSeconds + timeSeconds;
        
        // GMT offset (hardcoded as example; in real app would come from input)
        // For this example we'll assume GMT+5:30
        int gmtHours = 5;
        int gmtMinutes = 30;
        char gmtDirection = '+'; // or '-' depending on timezone
        
        // Calculate offset in seconds
        long offsetSeconds = gmtHours * 3600L + gmtMinutes * 60L;
        
        // Apply offset (subtract if positive, add if negative)
        if (gmtDirection == '+') {
            totalSeconds -= offsetSeconds;
        } else {
            totalSeconds += offsetSeconds;
        }
        
        // Convert back to date and time
        long days = totalSeconds / 86400L;
        long remainder = totalSeconds % 86400L;
        
        // Convert days back to date
        LocalDate resultDate = LocalDate.ofEpochDay(days);
        
        // Convert remainder back to time
        int resultHour = (int)(remainder / 3600L);
        remainder %= 3600L;
        int resultMinute = (int)(remainder / 60L);
        int resultSecond = (int)(remainder % 60L);
        
        // Format display
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        String displayDate = resultDate.format(dateFormatter);
        String displayTime = String.format("%02d:%02d:%02d", resultHour, resultMinute, resultSecond);
        
        System.out.println("Current GMT " + displayDate + " " + displayTime);
    }
}