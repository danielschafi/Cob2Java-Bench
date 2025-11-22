import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NOTES {
    public static void main(String[] args) {
        String noteRecord = new String(new char[256]);
        String noteStatus = "00";
        String currentYear, currentMonth, currentDay;
        String currentHour, currentMin, currentSec;
        String arguments = "";

        if (args.length > 0) {
            arguments = String.join(" ", args);
        }

        if (arguments.trim().isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("NOTES.TXT"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Error using NOTES.TXT. Error code: " + noteStatus);
            }
        } else {
            try (RandomAccessFile file = new RandomAccessFile("NOTES.TXT", "rw")) {
                file.seek(file.length());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
                Date now = new Date();

                currentYear = dateFormat.format(now).substring(0, 4);
                currentMonth = dateFormat.format(now).substring(4, 6);
                currentDay = dateFormat.format(now).substring(6, 8);
                currentHour = timeFormat.format(now).substring(0, 2);
                currentMin = timeFormat.format(now).substring(2, 4);
                currentSec = timeFormat.format(now).substring(4, 6);

                noteRecord = String.format("%s-%s-%s %s:%s:%s", currentYear, currentMonth, currentDay, currentHour, currentMin, currentSec);
                file.writeBytes(noteRecord + System.lineSeparator());

                noteRecord = String.format("\t%s", arguments);
                file.writeBytes(noteRecord + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error using NOTES.TXT. Error code: " + noteStatus);
            }
        }
    }
}