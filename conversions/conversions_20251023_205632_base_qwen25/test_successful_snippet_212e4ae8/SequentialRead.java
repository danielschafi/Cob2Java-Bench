import java.io.*;
import java.nio.charset.StandardCharsets;

public class SequentialRead {
    public static void main(String[] args) {
        String fileName = "database.dat";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            String line;
            boolean eof = false;

            while ((line = reader.readLine()) != null && !eof) {
                if (line.length() >= 18) {
                    String detailsId = line.substring(0, 7).trim();
                    String detailsSurname = line.substring(7, 15).trim();
                    String detailsBirthday = line.substring(15, 23).trim();
                    String someCode = line.substring(23, 28).trim();

                    System.out.println("DETAILS-ID: " + detailsId);
                    System.out.println("DETAILS-NAME: " + detailsSurname);
                    System.out.println("DETAILS-BIRTHDAY: " + detailsBirthday);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the DB file, program will exit.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}