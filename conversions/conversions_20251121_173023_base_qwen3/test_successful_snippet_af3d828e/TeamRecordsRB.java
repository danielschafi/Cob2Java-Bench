import java.io.*;
import java.util.*;

public class TeamRecordsRB {
    private static final String INPUT_FILE = "league.dat";
    private static final String OUTPUT_FILE = "leagueRfmtd.dat";
    
    public static void main(String[] args) {
        System.out.println("PROGRAM COMPILED: " + new Date());
        System.out.println("START: REFORMAT LEAGUE DATA");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line, writer);
            }
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
        
        System.out.println("END: REFORMAT LEAGUE DATA");
    }
    
    private static void processRecord(String inputLine, BufferedWriter writer) throws IOException {
        // Split the input line by commas
        String[] parts = inputLine.split(",");
        if (parts.length < 3) return;
        
        String divisionCode = parts[0].trim();
        String city = parts[1].trim();
        String record = parts[2].trim();
        
        // Split the record by dashes
        String[] recordParts = record.split("-");
        if (recordParts.length < 3) return;
        
        int wins = Integer.parseInt(recordParts[0]);
        int losses = Integer.parseInt(recordParts[1]);
        int ties = Integer.parseInt(recordParts[2]);
        
        // Calculate points
        int totalPointsAvailable = (wins + losses + ties) * 2;
        int totalPointsEarned = (wins * 2) + ties;
        
        // Calculate winning percentage
        double winningPct = totalPointsAvailable > 0 ? 
            (double) totalPointsEarned / totalPointsAvailable : 0.0;
        
        // Format the new record
        String formattedRecord = String.format("%02d-%02d-%02d", wins, losses, ties);
        
        // Write to output file
        writer.write(String.format("%s,%s,%s,%.3f", divisionCode, city, formattedRecord, winningPct));
        writer.newLine();
    }
}