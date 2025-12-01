import java.io.*;
import java.util.*;

public class ReadCSV {
    private static final String INPUT_FILE = "info.csv";
    private static final String OUTPUT_FILE = "output.txt";
    
    private String lastNameField;
    private String firstNameField;
    private String streetAddrField;
    private String cityField;
    private String stateField;
    private String zipField;
    
    public ReadCSV() {
        lastNameField = "";
        firstNameField = "";
        streetAddrField = "";
        cityField = "";
        stateField = "";
        zipField = "";
    }
    
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s).substring(0, n);
    }
    
    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s).substring(Math.max(0, s.length() - n));
    }
    
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                lastNameField = "";
                firstNameField = "";
                streetAddrField = "";
                cityField = "";
                stateField = "";
                zipField = "";
                
                String[] parts = line.split(",", -1);
                
                if (parts.length > 0) {
                    lastNameField = padRight(parts[0].trim(), 25);
                }
                if (parts.length > 1) {
                    firstNameField = padRight(parts[1].trim(), 15);
                }
                if (parts.length > 2) {
                    streetAddrField = padRight(parts[2].trim(), 30);
                }
                if (parts.length > 3) {
                    cityField = padRight(parts[3].trim(), 15);
                }
                if (parts.length > 4) {
                    stateField = padRight(parts[4].trim(), 3);
                }
                if (parts.length > 5) {
                    zipField = padRight(parts[5].trim(), 10);
                }
                
                StringBuilder outputRecord = new StringBuilder();
                outputRecord.append(lastNameField);
                outputRecord.append(padRight("", 5));
                outputRecord.append(firstNameField);
                outputRecord.append(padRight("", 5));
                outputRecord.append(streetAddrField);
                outputRecord.append(padRight("", 5));
                outputRecord.append(cityField);
                outputRecord.append(padRight("", 5));
                outputRecord.append(stateField);
                outputRecord.append(padRight("", 5));
                outputRecord.append(zipField);
                outputRecord.append(padRight("", 38));
                
                writer.write(outputRecord.toString());
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ReadCSV program = new ReadCSV();
        program.run();
    }
}