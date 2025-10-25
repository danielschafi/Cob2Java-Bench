import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int todayYYYY = currentDateTime.getYear();
        int todayMM = currentDateTime.getMonthValue();
        int todayDD = currentDateTime.getDayOfMonth();
        int timeHour = currentDateTime.getHour();
        int timeMinutes = currentDateTime.getMinute();
        int timeSeconds = currentDateTime.getSecond();

        int gmtHours = 0;
        int gmtMinutes = 0;
        char gmtDirection = '+';

        long totalSeconds = (currentDateTime.toEpochSecond(ZoneOffset.UTC) / 86400) * 86400 +
                           (timeHour * 3600) +
                           (timeMinutes * 60) +
                           timeSeconds;

        long workNumber = (gmtHours * 3600) + (gmtMinutes * 60);

        if (gmtDirection == '+') {
            totalSeconds -= workNumber;
        } else {
            totalSeconds += workNumber;
        }

        LocalDateTime gmtDateTime = LocalDateTime.ofEpochSecond(totalSeconds, 0, ZoneOffset.UTC);
        int gmtTodayYYYY = gmtDateTime.getYear();
        int gmtTodayMM = gmtDateTime.getMonthValue();
        int gmtTodayDD = gmtDateTime.getDayOfMonth();
        int gmtTimeHour = gmtDateTime.getHour();
        int gmtTimeMinutes = gmtDateTime.getMinute();
        int gmtTimeSeconds = gmtDateTime.getSecond();

        String displayDate = String.format("%02d/%02d/%04d", gmtTodayMM, gmtTodayDD, gmtTodayYYYY);
        String displayTime = String.format("%02d:%02d:%02d", gmtTimeHour, gmtTimeMinutes, gmtTimeSeconds);

        System.out.println("Current GMT " + displayDate + " " + displayTime);
    }
}