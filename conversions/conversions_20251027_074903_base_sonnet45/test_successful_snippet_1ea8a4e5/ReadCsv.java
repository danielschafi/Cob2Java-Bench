import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadCsv {
    public static void main(String[] args) {
        BufferedReader inputFile = null;
        BufferedWriter outputFile = null;
        
        try {
            inputFile = new BufferedReader(new FileReader("info.csv"));
            outputFile = new BufferedWriter(new FileWriter("output.txt"));
            
            String inputRecord;
            while ((inputRecord = inputFile.readLine()) != null) {
                if (inputRecord.trim().isEmpty()) {
                    continue;
                }
                
                String[] fields = inputRecord.split(",", -1);
                
                String lastName = fields.length > 0 ? fields[0] : "";
                String firstName = fields.length > 1 ? fields[1] : "";
                String streetAddr = fields.length > 2 ? fields[2] : "";
                String city = fields.length > 3 ? fields[3] : "";
                String state = fields.length > 4 ? fields[4] : "";
                String zip = fields.length > 5 ? fields[5] : "";
                
                String outLastName = padRight(lastName, 25);
                String outFirstName = padRight(firstName, 15);
                String outStreet = padRight(streetAddr, 30);
                String outCity = padRight(city, 15);
                String outState = padRight(state, 3);
                String outZip = padRight(zip, 10);
                
                StringBuilder outputRecord = new StringBuilder();
                outputRecord.append(outLastName);
                outputRecord.append(padRight("", 5));
                outputRecord.append(outFirstName);
                outputRecord.append(padRight("", 5));
                outputRecord.append(outStreet);
                outputRecord.append(padRight("", 5));
                outputRecord.append(outCity);
                outputRecord.append(padRight("", 5));
                outputRecord.append(outState);
                outputRecord.append(padRight("", 5));
                outputRecord.append(outZip);
                outputRecord.append(padRight("", 38));
                
                outputFile.write(outputRecord.toString());
                outputFile.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (inputFile != null) {
                    inputFile.close();
                }
                if (outputFile != null) {
                    outputFile.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing files: " + e.getMessage());
            }
        }
    }
    
    private static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}