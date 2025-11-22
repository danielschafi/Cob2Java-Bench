import java.time.*;
import java.time.format.*;
import java.util.*;

public class DateManipulation {
    public static void main(String[] args) {
        String givenDate = "March 7 2009 7:30pm EST";
        String dateSpec = "%B %d %Y %I:%M%p %Z";
        
        System.out.println("Given: " + givenDate);
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma z");
            ZonedDateTime parsedDateTime = ZonedDateTime.parse(givenDate, formatter.withZone(TimeZone.getTimeZone("EST").toZoneId()));
            
            ZonedDateTime addedHours = parsedDateTime.plusHours(12);
            displayDateTime(addedHours, "America/New_York");
            
            displayDateTime(addedHours, "America/Los_Angeles");
            
            displayDateTime(addedHours, "GMT");
            
            displayDateTime(addedHours, "Asia/Tokyo");
            
        } catch (Exception e) {
            System.err.println("date parse error");
        }
        
        String isoSpec = "YYYY-MM-DDThh:mm:ss+hh:mm";
        String isoDate = "2009-03-07T19:30:00-05:00";
        
        System.out.println("Given: " + isoDate);
        
        try {
            DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDate, isoFormatter);
            
            ZonedDateTime addedSeconds = zonedDateTime.plusSeconds(43200);
            
            String formatted = addedSeconds.format(isoFormatter).replace("T", "/");
            System.out.println("       " + formatted);
            
        } catch (Exception e) {
            System.err.println("date format error");
        }
    }
    
    private static void displayDateTime(ZonedDateTime dateTime, String timeZoneId) {
        try {
            ZoneId zoneId = ZoneId.of(timeZoneId);
            ZonedDateTime convertedDateTime = dateTime.withZoneSameInstant(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy");
            System.out.println("       " + convertedDateTime.format(formatter));
        } catch (Exception e) {
            System.err.println("date format error");
        }
    }
}