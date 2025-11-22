import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class DateManipulation {
    public static void main(String[] args) {
        String givenDate = "March 7 2009 7:30pm EST";
        String dateSpec = "%B %d %Y %I:%M%p %Z";
        
        // Parse initial date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma z");
        ZonedDateTime dateTime = ZonedDateTime.parse(givenDate, formatter);
        
        System.out.println("Given: " + givenDate);
        
        // Add 12 hours
        ZonedDateTime localDateTime = dateTime.plusHours(12);
        System.out.println("       " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        // Convert to Pacific time
        ZonedDateTime pacificTime = localDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("       " + pacificTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        // Convert to GMT
        ZonedDateTime gmtTime = localDateTime.withZoneSameInstant(ZoneId.of("GMT"));
        System.out.println("       " + gmtTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        // Convert to Tokyo time as seen in Hong Kong
        ZonedDateTime tokyoTime = localDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("       " + tokyoTime.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy")));
        
        // Standard COBOL approach based on ISO8601
        String isoDate = "2009-03-07T19:30:00-05:00";
        System.out.println("Given: " + isoDate);
        
        // Parse ISO date
        OffsetDateTime parsedDateTime = OffsetDateTime.parse(isoDate);
        LocalDateTime localDateTimeISO = parsedDateTime.toLocalDateTime();
        ZoneOffset offset = parsedDateTime.getOffset();
        
        // Add 12 hours
        LocalDateTime addedTime = localDateTimeISO.plusHours(12);
        
        // Adjust date if necessary
        if (addedTime.toLocalTime().getHour() >= 24) {
            addedTime = addedTime.plusDays(1);
        }
        
        // Format result
        String formattedResult = addedTime.toString().replace('T', '/');
        System.out.println("       " + formattedResult);
    }
}