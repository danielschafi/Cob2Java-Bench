import java.time.*;
import java.time.format.*;
import java.util.*;

public class DateManipulation {
    static class TimeStruct {
        long tmSec;
        long tmMin;
        long tmHour;
        long tmMday;
        long tmMon;
        long tmYear;
        long tmWday;
        long tmYday;
        long tmIsdst;
        long tmGmtoff;
        String tmZone;
    }

    static String givenDate = "March 7 2009 7:30pm EST";
    static String dateSpec = "%B %d %Y %I:%M%p %Z";
    static TimeStruct timeStruct = new TimeStruct();
    static Object scanIndex = null;
    static long timeT = 0;
    static Object timeTm = null;
    static String reformBuffer = "";
    static long reformLength = 0;
    static Object currentLocale = null;
    static final String isoSpec = "YYYY-MM-DDThh:mm:ss+hh:mm";
    static final String isoDate = "2009-03-07T19:30:00-05:00";
    static long dateInteger = 0;
    static long timeInteger = 0;

    public static void main(String[] args) {
        try {
            scanIndex = strptime(givenDate, dateSpec, timeStruct);
            System.out.println("Given: " + givenDate);

            if (scanIndex != null) {
                timeT = mktime(timeStruct);
                timeT += 43200;
                formDatetime();

                System.setProperty("TZ", "PST8PDT");
                TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
                formDatetime();

                System.setProperty("TZ", "GMT");
                TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
                formDatetime();

                System.setProperty("TZ", "Japan");
                TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
                formDatetime();

                dateSpec = "%c";
                formDatetime();
            } else {
                System.err.println("date parse error");
            }

            System.out.println("Given: " + isoDate);
            dateInteger = integerOfFormattedDate(isoSpec, isoDate);
            timeInteger = secondsFromFormattedTime(isoSpec, isoDate);

            timeInteger += 43200;
            if (timeInteger > 86400) {
                timeInteger -= 86400;
                dateInteger += 1;
            }

            String formatted = formattedDatetime(isoSpec, dateInteger, timeInteger, -300);
            String result = formatted.replace("T", "/");
            System.out.println("       " + result);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static Object strptime(String dateStr, String format, TimeStruct ts) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma zzz");
            ZonedDateTime zdt = ZonedDateTime.parse(dateStr, formatter);
            ts.tmSec = zdt.getSecond();
            ts.tmMin = zdt.getMinute();
            ts.tmHour = zdt.getHour();
            ts.tmMday = zdt.getDayOfMonth();
            ts.tmMon = zdt.getMonthValue() - 1;
            ts.tmYear = zdt.getYear() - 1900;
            ts.tmWday = zdt.getDayOfWeek().getValue() % 7;
            ts.tmYday = zdt.getDayOfYear() - 1;
            ts.tmZone = zdt.getZone().toString();
            return new Object();
        } catch (Exception e) {
            return null;
        }
    }

    static long mktime(TimeStruct ts) {
        try {
            LocalDateTime ldt = LocalDateTime.of(
                (int)(ts.tmYear + 1900),
                (int)(ts.tmMon + 1),
                (int)ts.tmMday,
                (int)ts.tmHour,
                (int)ts.tmMin,
                (int)ts.tmSec
            );
            return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
        } catch (Exception e) {
            return 0;
        }
    }

    static void formDatetime() {
        try {
            Instant instant = Instant.ofEpochSecond(timeT);
            ZonedDateTime zdt = instant.atZone(TimeZone.getDefault().toZoneId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            String result = zdt.format(formatter);
            System.out.println("       " + result);
        } catch (Exception e) {
            System.err.println("error calling strftime");
        }
    }

    static long integerOfFormattedDate(String spec, String dateStr) {
        try {
            LocalDate ld = LocalDate.parse(dateStr.substring(0, 10));
            return ld.toEpochDay() + 719163;
        } catch (Exception e) {
            return 0;
        }
    }

    static long secondsFromFormattedTime(String spec, String dateStr) {
        try {
            LocalTime lt = LocalTime.parse(dateStr.substring(11, 19));
            return lt.toSecondOfDay();
        } catch (Exception e) {
            return 0;
        }
    }

    static String formattedDatetime(String spec, long dateInt, long timeInt, int offset) {
        try {
            LocalDate ld = LocalDate.ofEpochDay(dateInt - 719163);
            LocalTime lt = LocalTime.ofSecondOfDay(timeInt);
            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            ZoneOffset zo = ZoneOffset.ofTotalSeconds(offset * 60);
            OffsetDateTime odt = OffsetDateTime.of(ldt, zo);
            return odt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception e) {
            return "";
        }
    }
}