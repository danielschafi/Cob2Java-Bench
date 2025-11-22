import java.time.*;
import java.time.format.*;
import java.util.*;

public class DateManipulation {
    
    public static void main(String[] args) {
        String givenDate = "March 7 2009 7:30pm EST";
        String dateSpec = "MMMM d yyyy h:mma zzz";
        
        System.out.println("Given: " + givenDate);
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateSpec, Locale.US);
            ZonedDateTime parsedDate = ZonedDateTime.parse(givenDate, formatter);
            
            ZonedDateTime plus12Hours = parsedDate.plusHours(12);
            
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mma zzz", Locale.US);
            System.out.println("       " + plus12Hours.format(outputFormatter));
            
            ZonedDateTime pacificTime = plus12Hours.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
            System.out.println("       " + pacificTime.format(outputFormatter));
            
            ZonedDateTime gmtTime = plus12Hours.withZoneSameInstant(ZoneId.of("GMT"));
            System.out.println("       " + gmtTime.format(outputFormatter));
            
            ZonedDateTime japanTime = plus12Hours.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
            DateTimeFormatter hongKongFormatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy", Locale.forLanguageTag("en-HK"));
            System.out.println("       " + japanTime.format(hongKongFormatter));
            
        } catch (Exception e) {
            System.err.println("date parse error");
        }
        
        String isoDate = "2009-03-07T19:30:00-05:00";
        System.out.println("Given: " + isoDate);
        
        try {
            ZonedDateTime isoDateTime = ZonedDateTime.parse(isoDate);
            
            ZonedDateTime isoPlus12Hours = isoDateTime.plusHours(12);
            
            DateTimeFormatter isoOutputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ssXXX");
            System.out.println("       " + isoPlus12Hours.format(isoOutputFormatter));
            
        } catch (Exception e) {
            System.err.println("date parse error");
        }
    }
}