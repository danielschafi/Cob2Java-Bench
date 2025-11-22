import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.JulianFields;

public class Chapt21g {
    
    static class CurrentDateGroup {
        static class TodaysDate {
            int todayYYYY;
            int todayMM;
            int todayDD;
        }
        
        TodaysDate todaysDate = new TodaysDate();
        int todaysDateN;
        
        static class TimeNow {
            int timeHour;
            int timeMinutes;
            int timeSeconds;
            int timeHundredths;
        }
        
        TimeNow timeNow = new TimeNow();
        
        static class GMTOffset {
            char gmtDirection;
            int gmtHours;
            int gmtMinutes;
        }
        
        GMTOffset gmtOffset = new GMTOffset();
    }
    
    static class DisplayDate {
        int todayMM;
        int todayDD;
        int todayYYYY;
    }
    
    static class DisplayTime {
        int timeHour;
        int timeMinutes;
        int timeSeconds;
    }
    
    public static void main(String[] args) {
        CurrentDateGroup currentDateGroup = new CurrentDateGroup();
        DisplayDate displayDate = new DisplayDate();
        DisplayTime displayTime = new DisplayTime();
        
        long totalSeconds = 0;
        long workNumber = 0;
        long workRemainder = 0;
        long gmtOffsetSeconds = 0;
        
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedNow = ZonedDateTime.now();
        
        currentDateGroup.todaysDate.todayYYYY = now.getYear();
        currentDateGroup.todaysDate.todayMM = now.getMonthValue();
        currentDateGroup.todaysDate.todayDD = now.getDayOfMonth();
        currentDateGroup.todaysDate.todaysDateN = 
            currentDateGroup.todaysDate.todayYYYY * 10000 +
            currentDateGroup.todaysDate.todayMM * 100 +
            currentDateGroup.todaysDate.todayDD;
        
        currentDateGroup.timeNow.timeHour = now.getHour();
        currentDateGroup.timeNow.timeMinutes = now.getMinute();
        currentDateGroup.timeNow.timeSeconds = now.getSecond();
        currentDateGroup.timeNow.timeHundredths = (now.getNano() / 10000000) % 100;
        
        ZoneId zoneId = zonedNow.getZone();
        int offsetSeconds = zonedNow.getOffset().getTotalSeconds();
        
        if (offsetSeconds >= 0) {
            currentDateGroup.gmtOffset.gmtDirection = '+';
        } else {
            currentDateGroup.gmtOffset.gmtDirection = '-';
            offsetSeconds = -offsetSeconds;
        }
        
        currentDateGroup.gmtOffset.gmtHours = offsetSeconds / 3600;
        currentDateGroup.gmtOffset.gmtMinutes = (offsetSeconds % 3600) / 60;
        
        workNumber = julianDayNumber(
            currentDateGroup.todaysDate.todayYYYY,
            currentDateGroup.todaysDate.todayMM,
            currentDateGroup.todaysDate.todayDD
        );
        
        totalSeconds = (workNumber * 86400L) +
                      (currentDateGroup.timeNow.timeHour * 3600L) +
                      (currentDateGroup.timeNow.timeMinutes * 60L) +
                      currentDateGroup.timeNow.timeSeconds;
        
        gmtOffsetSeconds = (currentDateGroup.gmtOffset.gmtHours * 3600L) +
                          (currentDateGroup.gmtOffset.gmtMinutes * 60L);
        
        if (currentDateGroup.gmtOffset.gmtDirection == '+') {
            totalSeconds -= gmtOffsetSeconds;
        } else {
            totalSeconds += gmtOffsetSeconds;
        }
        
        workNumber = totalSeconds / 86400L;
        workRemainder = totalSeconds % 86400L;
        
        int[] dateFromJulian = dateFromJulianDayNumber(workNumber);
        currentDateGroup.todaysDate.todayYYYY = dateFromJulian[0];
        currentDateGroup.todaysDate.todayMM = dateFromJulian[1];
        currentDateGroup.todaysDate.todayDD = dateFromJulian[2];
        
        currentDateGroup.timeNow.timeHour = (int)(workRemainder / 3600L);
        workNumber = workRemainder % 3600L;
        currentDateGroup.timeNow.timeMinutes = (int)(workNumber / 60L);
        currentDateGroup.timeNow.timeSeconds = (int)(workNumber % 60L);
        
        displayDate.todayMM = currentDateGroup.todaysDate.todayMM;
        displayDate.todayDD = currentDateGroup.todaysDate.todayDD;
        displayDate.todayYYYY = currentDateGroup.todaysDate.todayYYYY;
        
        displayTime.timeHour = currentDateGroup.timeNow.timeHour;
        displayTime.timeMinutes = currentDateGroup.timeNow.timeMinutes;
        displayTime.timeSeconds = currentDateGroup.timeNow.timeSeconds;
        
        System.out.printf("Current GMT %02d/%02d/%04d %02d:%02d:%02d%n",
            displayDate.todayMM,
            displayDate.todayDD,
            displayDate.todayYYYY,
            displayTime.timeHour,
            displayTime.timeMinutes,
            displayTime.timeSeconds);
    }
    
    static long julianDayNumber(int year, int month, int day) {
        long a = (14 - month) / 12;
        long y = year + 4800 - a;
        long m = month + 12 * a - 3;
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
    }
    
    static int[] dateFromJulianDayNumber(long jd) {
        long a = jd + 32044;
        long b = (4 * a + 3) / 146097;
        long c = a - (146097 * b) / 4;
        long d = (4 * c + 3) / 1461;
        long e = c - (1461 * d) / 4;
        long m = (5 * e + 2) / 153;
        
        int day = (int)(e - (153 * m + 2) / 5 + 1);
        int month = (int)(m + 3 - 12 * (m / 10));
        int year = (int)(100 * b + d - 4800 + m / 10);
        
        return new int[]{year, month, day};
    }
}