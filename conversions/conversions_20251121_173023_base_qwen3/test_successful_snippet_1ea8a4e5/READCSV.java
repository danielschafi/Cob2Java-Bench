import java.io.*;
import java.util.*;

public class READCSV {
    private static final String INPUT_FILE = "info.csv";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    StringBuilder outputLine = new StringBuilder();
                    
                    // LAST_NAME (25 characters)
                    outputLine.append(String.format("%-25s", parts[0].trim()));
                    
                    // Filler (5 spaces)
                    outputLine.append("     ");
                    
                    // FIRST_NAME (15 characters)
                    outputLine.append(String.format("%-15s", parts[1].trim()));
                    
                    // Filler (5 spaces)
                    outputLine.append("     ");
                    
                    // STREET_ADDR (30 characters)
                    outputLine.append(String.format("%-30s", parts[2].trim()));
                    
                    // Filler (5 spaces)
                    outputLine.append("     ");
                    
                    // CITY (15 characters)
                    outputLine.append(String.format("%-15s", parts[3].trim()));
                    
                    // Filler (5 spaces)
                    outputLine.append("     ");
                    
                    // STATE (3 characters)
                    outputLine.append(String.format("%-3s", parts[4].trim()));
                    
                    // Filler (5 spaces)
                    outputLine.append("     ");
                    
                    // ZIP (10 characters)
                    outputLine.append(String.format("%-10s", parts[5].trim()));
                    
                    // Filler (38 spaces)
                    outputLine.append("                                       ");
                    
                    writer.write(outputLine.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}