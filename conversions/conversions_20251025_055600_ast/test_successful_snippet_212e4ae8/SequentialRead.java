import java.io.*;
import java.nio.file.*;

public class SequentialRead {
    public static void main(String[] args) {
        String filePath = "database.dat";
        String fileStatus = "";
        boolean eof = false;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 29) {
                    System.out.println("Error reading the DB file, program will exit.");
                    return;
                }

                String detailsId = line.substring(0, 7).trim();
                String detailsSurname = line.substring(7, 15).trim();
                String detailsBirthday = line.substring(15, 23).trim();

                System.out.println("DETAILS-ID: " + detailsId);
                System.out.println("DETAILS-NAME: " + detailsSurname);
                System.out.println("DETAILS-BIRTHDAY: " + detailsBirthday);
            }
        } catch (IOException e) {
            System.out.println("Error opening the DB file, program will exit.");
        }
    }
}