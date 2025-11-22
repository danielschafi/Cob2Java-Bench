import java.io.*;
import java.nio.file.*;

public class SEQUENTIAL_READ {
    private static final String DATABASE_FILE = "database.dat";
    
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(DATABASE_FILE))) {
            String line;
            
            // Read first record
            line = reader.readLine();
            if (line != null) {
                processRecord(line);
                
                // Read remaining records
                while ((line = reader.readLine()) != null) {
                    processRecord(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening the DB file, program will exit.");
        }
    }
    
    private static void processRecord(String record) {
        if (record.length() >= 25) {
            String id = record.substring(0, 7).trim();
            String surname = record.substring(7, 15).trim();
            String initials = record.substring(15, 17).trim();
            String birthday = record.substring(17, 25).trim();
            String code = record.substring(25).trim();
            
            System.out.println(record);
            System.out.println("DETAILS-ID: " + id);
            System.out.println("DETAILS-NAME: " + surname);
            System.out.println("DETAILS-BIRTHDAY: " + birthday);
        }
    }
}