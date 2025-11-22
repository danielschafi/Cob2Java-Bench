import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Chapt21g {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime gmtDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("GMT"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String displayDate = gmtDateTime.format(dateFormatter);
        String displayTime = gmtDateTime.format(timeFormatter);

        System.out.println("Current GMT " + displayDate + " " + displayTime);
    }
}