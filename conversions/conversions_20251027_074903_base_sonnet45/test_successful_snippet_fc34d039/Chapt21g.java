import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    public static void main(String[] args) {
        // Get current date and time with timezone offset
        ZonedDateTime now = ZonedDateTime.now();
        
        // Extract local date and time components
        int todayYYYY = now.getYear();
        int todayMM = now.getMonthValue();
        int todayDD = now.getDayOfMonth();
        int timeHour = now.getHour();
        int timeMinutes = now.getMinute();
        int timeSeconds = now.getSecond();
        
        // Get GMT offset
        ZoneOffset offset = now.getOffset();
        int totalOffsetSeconds = offset.getTotalSeconds();
        
        String gmtDirection;
        int gmtHours;
        int gmtMinutes;
        
        if (totalOffsetSeconds >= 0) {
            gmtDirection = "+";
            gmtHours = totalOffsetSeconds / 3600;
            gmtMinutes = (totalOffsetSeconds % 3600) / 60;
        } else {
            gmtDirection = "-";
            gmtHours = Math.abs(totalOffsetSeconds) / 3600;
            gmtMinutes = (Math.abs(totalOffsetSeconds) % 3600) / 60;
        }
        
        // Convert today to seconds
        int todaysDateN = todayYYYY * 10000 + todayMM * 100 + todayDD;
        long workNumber = dateToInteger(todaysDateN);
        long totalSeconds = (workNumber * 86400L) +
                           (timeHour * 3600L) +
                           (timeMinutes * 60L) +
                           timeSeconds;
        
        // Calculate GMT offset in seconds
        long gmtOffsetSeconds = (gmtHours * 3600L) + (gmtMinutes * 60L);
        
        // Adjust by opposite of direction from GMT
        if (gmtDirection.equals("+")) {
            totalSeconds -= gmtOffsetSeconds;
        } else {
            totalSeconds += gmtOffsetSeconds;
        }
        
        // Convert the time in seconds back to a date and time
        workNumber = totalSeconds / 86400L;
        long workRemainder = totalSeconds % 86400L;
        
        todaysDateN = integerToDate((int) workNumber);
        todayYYYY = todaysDateN / 10000;
        todayMM = (todaysDateN % 10000) / 100;
        todayDD = todaysDateN % 100;
        
        timeHour = (int) (workRemainder / 3600L);
        workNumber = workRemainder % 3600L;
        timeMinutes = (int) (workNumber / 60L);
        timeSeconds = (int) (workNumber % 60L);
        
        // Format and display
        String displayDate = String.format("%02d/%02d/%04d", todayMM, todayDD, todayYYYY);
        String displayTime = String.format("%02d:%02d:%02d", timeHour, timeMinutes, timeSeconds);
        
        System.out.println("Current GMT " + displayDate + " " + displayTime);
    }
    
    private static int dateToInteger(int dateN) {
        int year = dateN / 10000;
        int month = (dateN % 10000) / 100;
        int day = dateN % 100;
        
        LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime baseDate = LocalDateTime.of(1600, 12, 31, 0, 0);
        
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(baseDate, date);
        return (int) daysBetween;
    }
    
    private static int integerToDate(int days) {
        LocalDateTime baseDate = LocalDateTime.of(1600, 12, 31, 0, 0);
        LocalDateTime resultDate = baseDate.plusDays(days);
        
        int year = resultDate.getYear();
        int month = resultDate.getMonthValue();
        int day = resultDate.getDayOfMonth();
        
        return year * 10000 + month * 100 + day;
    }
}