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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        try {
            Date date = parser.parse(givenDate);
            System.out.println("Given: " + givenDate);

            // Add 12 hours and reform as local
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            System.out.println("       " + formatter.format(calendar.getTime()));

            // Reformat as Pacific time
            formatter.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
            System.out.println("       " + formatter.format(calendar.getTime()));

            // Reformat as Greenwich mean
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println("       " + formatter.format(calendar.getTime()));

            // Reformat for Tokyo time, as seen in Hong Kong
            formatter.setTimeZone(TimeZone.getTimeZone("Japan"));
            formatter.applyPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            System.out.println("       " + formatter.format(calendar.getTime()));

        } catch (ParseException e) {
            System.err.println("date parse error");
        }

        // A more standard COBOL approach, based on ISO8601
        String isoDate = "2009-03-07T19:30:00-05:00";
        SimpleDateFormat isoParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat isoFormatter = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ssZ");

        try {
            Date isoParsedDate = isoParser.parse(isoDate);
            System.out.println("Given: " + isoDate);

            Calendar isoCalendar = Calendar.getInstance();
            isoCalendar.setTime(isoParsedDate);
            isoCalendar.add(Calendar.SECOND, 43200);
            if (isoCalendar.get(Calendar.HOUR_OF_DAY) > 23) {
                isoCalendar.add(Calendar.DAY_OF_MONTH, 1);
                isoCalendar.set(Calendar.HOUR_OF_DAY, isoCalendar.get(Calendar.HOUR_OF_DAY) - 24);
            }
            System.out.println("       " + isoFormatter.format(isoCalendar.getTime()).replace("T", "/"));

        } catch (ParseException e) {
            System.err.println("date parse error");
        }
    }
}