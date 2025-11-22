import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateManipulation {
    public static void main(String[] args) {
        String givenDate = "March 7 2009 7:30pm EST";
        String dateSpec = "%B %d %Y %I:%M%p %Z";
        SimpleDateFormat parser = new SimpleDateFormat("MMMM d yyyy h:mma z");
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        try {
            Date date = parser.parse(givenDate);
            System.out.println("Given: " + givenDate);

            // Add 12 hours
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            date = calendar.getTime();

            // Local time
            SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            System.out.println("       " + localFormat.format(date));

            // Pacific time
            localFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
            System.out.println("       " + localFormat.format(date));

            // Greenwich mean time
            localFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println("       " + localFormat.format(date));

            // Tokyo time as seen in Hong Kong
            localFormat.setTimeZone(TimeZone.getTimeZone("Japan"));
            System.out.println("       " + localFormat.format(date));

        } catch (ParseException e) {
            System.err.println("date parse error");
        }

        // ISO8601 approach
        String isoDate = "2009-03-07T19:30:00-05:00";
        try {
            Date date = isoFormat.parse(isoDate);
            System.out.println("Given: " + isoDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, 43200);
            date = calendar.getTime();

            isoFormat.setTimeZone(TimeZone.getTimeZone("GMT-05:00"));
            String formattedDate = isoFormat.format(date).replace("GMT-05:00", "-05:00");
            formattedDate = formattedDate.replace("T", "/");
            System.out.println("       " + formattedDate);

        } catch (ParseException e) {
            System.err.println("date parse error");
        }
    }
}