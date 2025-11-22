import java.io.*;
import java.util.*;

public class ReadCSV {
    public static void main(String[] args) {
        String inputFile = "info.csv";
        String outputFile = "output.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] fields = line.split(",", -1);
                
                String lastName = fields.length > 0 ? fields[0].trim() : "";
                String firstName = fields.length > 1 ? fields[1].trim() : "";
                String streetAddr = fields.length > 2 ? fields[2].trim() : "";
                String city = fields.length > 3 ? fields[3].trim() : "";
                String state = fields.length > 4 ? fields[4].trim() : "";
                String zip = fields.length > 5 ? fields[5].trim() : "";
                
                lastName = padRight(lastName, 25);
                firstName = padRight(firstName, 15);
                streetAddr = padRight(streetAddr, 30);
                city = padRight(city, 15);
                state = padRight(state, 3);
                zip = padRight(zip, 10);
                
                String outputRecord = lastName + "     " + firstName + "     " + streetAddr + "     " + 
                                     city + "     " + state + "     " + zip;
                outputRecord = padRight(outputRecord, 160);
                
                writer.write(outputRecord);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String padRight(String s, int n) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= n) {
            return s.substring(0, n);
        }
        return String.format("%-" + n + "s", s);
    }
}