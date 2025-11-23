import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class DateManipulation {
    public static void main(String[] args) {
        String givenDate = "March 7 2009 7:30pm EST";
        String dateSpec = "%B %d %Y %I:%M%p %Z";
        
        // Parse the initial date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma z");
        ZonedDateTime parsedDate = ZonedDateTime.parse(givenDate, formatter);
        
        System.out.println("Given: " + givenDate);
        
        // Add 12 hours
        ZonedDateTime adjustedDate = parsedDate.plusHours(12);
        
        // Display in local time
        DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy");
        System.out.println("       " + adjustedDate.format(localFormatter));
        
        // Display in Pacific time
        ZonedDateTime pacificTime = adjustedDate.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("       " + pacificTime.format(localFormatter));
        
        // Display in GMT
        ZonedDateTime gmtTime = adjustedDate.withZoneSameInstant(ZoneId.of("GMT"));
        System.out.println("       " + gmtTime.format(localFormatter));
        
        // Display in Tokyo time with Hong Kong locale
        ZonedDateTime tokyoTime = adjustedDate.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        DateTimeFormatter tokyoFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.forLanguageTag("en_HK"));
        System.out.println("       " + tokyoTime.format(tokyoFormatter));
        
        // ISO 8601 approach
        String isoSpec = "yyyy-MM-dd'T'HH:mm:ssXXX";
        String isoDate = "2009-03-07T19:30:00-05:00";
        
        System.out.println("Given: " + isoDate);
        
        LocalDateTime localDateTime = LocalDateTime.parse(isoDate.substring(0, 19), 
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        ZoneOffset zoneOffset = ZoneOffset.of(isoDate.substring(19));
        
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneOffset);
        
        // Add 12 hours (43200 seconds)
        ZonedDateTime plusTwelveHours = zonedDateTime.plusHours(12);
        
        // Format and display
        String formatted = plusTwelveHours.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
                                         .replace('T', '/');
        System.out.println("       " + formatted);
    }
}