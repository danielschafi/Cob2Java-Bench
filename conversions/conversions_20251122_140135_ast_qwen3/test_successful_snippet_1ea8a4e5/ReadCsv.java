import java.io.*;
import java.util.*;

public class ReadCsv {
    private static final String INPUT_FILE = "info.csv";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                
                StringBuilder outputLine = new StringBuilder();
                
                // LAST_NAME (25 chars)
                if (fields.length > 0) {
                    String lastName = fields[0].trim();
                    outputLine.append(String.format("%-25s", lastName));
                } else {
                    outputLine.append(String.format("%-25s", ""));
                }
                
                // Filler (5 chars)
                outputLine.append("     ");
                
                // FIRST_NAME (15 chars)
                if (fields.length > 1) {
                    String firstName = fields[1].trim();
                    outputLine.append(String.format("%-15s", firstName));
                } else {
                    outputLine.append(String.format("%-15s", ""));
                }
                
                // Filler (5 chars)
                outputLine.append("     ");
                
                // STREET_ADDR (30 chars)
                if (fields.length > 2) {
                    String streetAddr = fields[2].trim();
                    outputLine.append(String.format("%-30s", streetAddr));
                } else {
                    outputLine.append(String.format("%-30s", ""));
                }
                
                // Filler (5 chars)
                outputLine.append("     ");
                
                // CITY (15 chars)
                if (fields.length > 3) {
                    String city = fields[3].trim();
                    outputLine.append(String.format("%-15s", city));
                } else {
                    outputLine.append(String.format("%-15s", ""));
                }
                
                // Filler (5 chars)
                outputLine.append("     ");
                
                // STATE (3 chars)
                if (fields.length > 4) {
                    String state = fields[4].trim();
                    outputLine.append(String.format("%-3s", state));
                } else {
                    outputLine.append(String.format("%-3s", ""));
                }
                
                // Filler (5 chars)
                outputLine.append("     ");
                
                // ZIP (10 chars)
                if (fields.length > 5) {
                    String zip = fields[5].trim();
                    outputLine.append(String.format("%-10s", zip));
                } else {
                    outputLine.append(String.format("%-10s", ""));
                }
                
                // Filler (38 chars)
                outputLine.append("                                      ");
                
                writer.write(outputLine.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}