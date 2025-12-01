import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    
    static class CurrentDateGroup {
        int todayYYYY;
        int todayMM;
        int todayDD;
        int timeHour;
        int timeMinutes;
        int timeSeconds;
        int timeHundredths;
        char gmtDirection;
        int gmtHours;
        int gmtMinutes;
    }
    
    static class DisplayDate {
        int todayMM;
        String filler1 = "/";
        int todayDD;
        String filler2 = "/";
        int todayYYYY;
    }
    
    static class DisplayTime {
        int timeHour;
        String filler1 = ":";
        int timeMinutes;
        String filler2 = ":";
        int timeSeconds;
    }
    
    public static void main(String[] args) {
        CurrentDateGroup currentDateGroup = new CurrentDateGroup();
        DisplayDate displayDate = new DisplayDate();
        DisplayTime displayTime = new DisplayTime();
        
        long totalSeconds = 0;
        long workNumber = 0;
        long workRemainder = 0;
        long gmtOffset = 0;
        
        // Move Function Current-Date To Current-Date-Group
        LocalDateTime now = LocalDateTime.now();
        currentDateGroup.todayYYYY = now.getYear();
        currentDateGroup.todayMM = now.getMonthValue();
        currentDateGroup.todayDD = now.getDayOfMonth();
        currentDateGroup.timeHour = now.getHour();
        currentDateGroup.timeMinutes = now.getMinute();
        currentDateGroup.timeSeconds = now.getSecond();
        currentDateGroup.timeHundredths = (now.getNano() / 10000000) % 100;
        
        // For this example, set GMT offset (would normally come from system)
        currentDateGroup.gmtDirection = '-';
        currentDateGroup.gmtHours = 5;
        currentDateGroup.gmtMinutes = 0;
        
        // Convert Today To Seconds
        workNumber = dateToInteger(currentDateGroup.todayYYYY, currentDateGroup.todayMM, currentDateGroup.todayDD);
        
        totalSeconds = (workNumber * 86400L) +
                       (currentDateGroup.timeHour * 3600L) +
                       (currentDateGroup.timeMinutes * 60L) +
                       currentDateGroup.timeSeconds;
        
        workNumber = (currentDateGroup.gmtHours * 3600L) +
                     (currentDateGroup.gmtMinutes * 60L);
        
        // Remember We Need To Change By The Opposite Of The Direction From Gmt
        if (currentDateGroup.gmtDirection == '+') {
            totalSeconds -= workNumber;
        } else {
            totalSeconds += workNumber;
        }
        
        // Convert The Time In Seconds Back To A Date And Time
        workNumber = totalSeconds / 86400L;
        workRemainder = totalSeconds % 86400L;
        
        integerToDate(workNumber, currentDateGroup);
        
        displayTime.timeHour = (int)(workRemainder / 3600L);
        workNumber = workRemainder % 3600L;
        
        displayTime.timeMinutes = (int)(workNumber / 60L);
        displayTime.timeSeconds = (int)(workNumber % 60L);
        
        // Move Corresponding Todays-Date To Display-Date
        displayDate.todayMM = currentDateGroup.todayMM;
        displayDate.todayDD = currentDateGroup.todayDD;
        displayDate.todayYYYY = currentDateGroup.todayYYYY;
        
        // Move Corresponding Time-Now To Display-Time
        displayTime.timeHour = currentDateGroup.timeHour;
        displayTime.timeMinutes = currentDateGroup.timeMinutes;
        displayTime.timeSeconds = currentDateGroup.timeSeconds;
        
        // Display
        System.out.printf("Current GMT %02d%s%02d%s%04d %02d%s%02d%s%02d%n",
                displayDate.todayMM, displayDate.filler1, displayDate.todayDD, displayDate.filler2, displayDate.todayYYYY,
                displayTime.timeHour, displayTime.filler1, displayTime.timeMinutes, displayTime.filler2, displayTime.timeSeconds);
    }
    
    static long dateToInteger(int yyyy, int mm, int dd) {
        // Simplified conversion - counts days since a reference date
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        long days = 0;
        
        // Add days for complete years
        for (int y = 1900; y < yyyy; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }
        
        // Add days for complete months in current year
        for (int m = 1; m < mm; m++) {
            days += daysInMonth[m];
            if (m == 2 && isLeapYear(yyyy)) {
                days += 1;
            }
        }
        
        // Add remaining days
        days += dd;
        
        return days;
    }
    
    static void integerToDate(long days, CurrentDateGroup group) {
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int yyyy = 1900;
        long remainingDays = days;
        
        // Find year
        while (remainingDays > (isLeapYear(yyyy) ? 366 : 365)) {
            remainingDays -= isLeapYear(yyyy) ? 366 : 365;
            yyyy++;
        }
        
        // Find month and day
        int mm = 1;
        while (remainingDays > daysInMonth[mm] + (mm == 2 && isLeapYear(yyyy) ? 1 : 0)) {
            remainingDays -= daysInMonth[mm] + (mm == 2 && isLeapYear(yyyy) ? 1 : 0);
            mm++;
        }
        
        group.todayYYYY = yyyy;
        group.todayMM = mm;
        group.todayDD = (int)remainingDays;
    }
    
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}