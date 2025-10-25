import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NOTES {
    public static void main(String[] args) {
        String noteRecord = new String(new char[256]);
        int noteStatus = 0;
        boolean notesOk = noteStatus >= 0 && noteStatus <= 9;
        String arguments = new String(new char[256]);

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
                System.out.println("Error using NOTES.TXT. Error code: " + e.getMessage());
            }
        } else {
            try (RandomAccessFile file = new RandomAccessFile("NOTES.TXT", "rw")) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateTime = now.format(formatter);
                file.seek(file.length());
                file.writeBytes(dateTime + "\n");
                file.writeBytes("\t" + arguments + "\n");
            } catch (IOException e) {
                System.out.println("Error using NOTES.TXT. Error code: " + e.getMessage());
            }
        }
    }
}