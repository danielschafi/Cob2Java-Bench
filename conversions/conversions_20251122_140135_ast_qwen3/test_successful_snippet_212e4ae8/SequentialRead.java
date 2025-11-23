import java.io.*;
import java.nio.file.*;

public class SequentialRead {
    private static class Details {
        private int detailsId;
        private String detailsSurname;
        private String initials;
        private String detailsBirthday;
        private String someCode;
        
        public Details() {}
        
        public Details(int detailsId, String detailsSurname, String initials, String detailsBirthday, String someCode) {
            this.detailsId = detailsId;
            this.detailsSurname = detailsSurname;
            this.initials = initials;
            this.detailsBirthday = detailsBirthday;
            this.someCode = someCode;
        }
        
        @Override
        public String toString() {
            return "Details{" +
                    "detailsId=" + detailsId +
                    ", detailsSurname='" + detailsSurname + '\'' +
                    ", initials='" + initials + '\'' +
                    ", detailsBirthday='" + detailsBirthday + '\'' +
                    ", someCode='" + someCode + '\'' +
                    '}';
        }
    }
    
    public static void main(String[] args) {
        String fileName = "database.dat";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            boolean eof = false;
            
            // Read first record
            if ((line = reader.readLine()) != null) {
                processRecord(line);
                eof = false;
            } else {
                eof = true;
            }
            
            // Read remaining records
            while (!eof && (line = reader.readLine()) != null) {
                processRecord(line);
            }
            
        } catch (IOException e) {
            System.err.println("Error opening the DB file, program will exit.");
            System.exit(1);
        }
    }
    
    private static void processRecord(String line) {
        // Assuming fixed format based on COBOL structure
        // Format: ID(7) + SURNAME(8) + INITIALS(2) + BIRTHDAY(8) + SOME-CODE(5)
        if (line.length() >= 30) {
            try {
                int detailsId = Integer.parseInt(line.substring(0, 7).trim());
                String detailsSurname = line.substring(7, 15).trim();
                String initials = line.substring(15, 17).trim();
                String detailsBirthday = line.substring(17, 25).trim();
                String someCode = line.substring(25, 30).trim();
                
                Details details = new Details(detailsId, detailsSurname, initials, detailsBirthday, someCode);
                displayDetails(details);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing record: " + line);
            }
        } else {
            System.err.println("Invalid record length: " + line);
        }
    }
    
    private static void displayDetails(Details details) {
        System.out.println(details);
        System.out.println("DETAILS-ID: " + details.detailsId);
        System.out.println("DETAILS-NAME: " + details.detailsSurname);
        System.out.println("DETAILS-BIRTHDAY: " + details.detailsBirthday);
    }
}