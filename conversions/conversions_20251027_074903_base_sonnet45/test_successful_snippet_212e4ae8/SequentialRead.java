import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SequentialRead {
    
    private static class Details {
        String detailsId;
        String detailsSurname;
        String initials;
        String detailsBirthday;
        String someCode;
        
        public Details(String record) {
            if (record.length() >= 30) {
                this.detailsId = record.substring(0, 7);
                this.detailsSurname = record.substring(7, 15);
                this.initials = record.substring(15, 17);
                this.detailsBirthday = record.substring(17, 25);
                this.someCode = record.substring(25, 30);
            }
        }
        
        @Override
        public String toString() {
            return detailsId + detailsSurname + initials + detailsBirthday + someCode;
        }
    }
    
    public static void main(String[] args) {
        String fileName = "database.dat";
        BufferedReader reader = null;
        boolean eof = false;
        
        try {
            reader = new BufferedReader(new FileReader(fileName));
            
            String line = reader.readLine();
            if (line != null) {
                Details details = new Details(line);
                displayDetails(details);
                
                while ((line = reader.readLine()) != null) {
                    details = new Details(line);
                    displayDetails(details);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error opening the DB file, program will exit.");
            System.exit(1);
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
    
    private static void displayDetails(Details details) {
        System.out.println(details.toString());
        System.out.println("DETAILS-ID: " + details.detailsId);
        System.out.println("DETAILS-NAME: " + details.detailsSurname);
        System.out.println("DETAILS-BIRTHDAY: " + details.detailsBirthday);
    }
}