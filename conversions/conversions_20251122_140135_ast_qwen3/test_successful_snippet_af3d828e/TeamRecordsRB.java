import java.io.*;
import java.util.*;

public class TeamRecordsRB {
    private static final String LEAGUE_FILE = "league.dat";
    private static final String LEAGUE_FRMTD_FILE = "leagueRfmtd.dat";
    
    // Working storage variables
    private static String wsDivisionCode;
    private static String wsCity;
    private static String wsRecord6;
    private static String wsPct;
    private static String wsRfmtWins;
    private static String wsRfmtLosses;
    private static String wsRfmtTies;
    private static int wsRfmtWinsNum;
    private static int wsRfmtLossesNum;
    private static int wsRfmtTiesNum;
    private static int wsTotalPtsAvailable;
    private static int wsTotalPtsEarned;
    private static double wsWinningPct;
    
    public static void main(String[] args) {
        System.out.println("PROGRAM COMPILED: " + new Date());
        System.out.println("START: REFORMAT LEAGUE DATA");
        
        formatRecords();
        
        System.out.println("END: REFORMAT LEAGUE DATA");
    }
    
    private static void formatRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LEAGUE_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(LEAGUE_FRMTD_FILE))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                processLeagueFile(line, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processLeagueFile(String leagueRecord, BufferedWriter writer) {
        // Split into division, city, and record fields
        String[] parts = leagueRecord.split(",", 3);
        if (parts.length < 3) {
            return; // Skip malformed records
        }
        
        wsDivisionCode = parts[0].trim();
        wsCity = parts[1].trim();
        wsRecord6 = parts[2].trim();
        
        // Now split the team record into wins, losses, and ties
        String[] recordParts = wsRecord6.split("-", 3);
        if (recordParts.length < 3) {
            return; // Skip malformed records
        }
        
        wsRfmtWins = recordParts[0];
        wsRfmtLosses = recordParts[1];
        wsRfmtTies = recordParts[2];
        
        // Convert to numeric values
        wsRfmtWinsNum = Integer.parseInt(wsRfmtWins);
        wsRfmtLossesNum = Integer.parseInt(wsRfmtLosses);
        wsRfmtTiesNum = Integer.parseInt(wsRfmtTies);
        
        // Calculate points
        wsTotalPtsAvailable = (wsRfmtLossesNum + wsRfmtWinsNum + wsRfmtTiesNum) * 2;
        wsTotalPtsEarned = (wsRfmtWinsNum * 2) + wsRfmtTiesNum;
        wsWinningPct = (double) wsTotalPtsEarned / wsTotalPtsAvailable;
        
        // Format the new record
        StringBuilder formattedRecord = new StringBuilder();
        formattedRecord.append(String.format("%02d", wsRfmtWinsNum))
                      .append("-")
                      .append(String.format("%02d", wsRfmtLossesNum))
                      .append("-")
                      .append(String.format("%02d", wsRfmtTiesNum));
        
        // Write to output file
        try {
            writer.write(wsDivisionCode + "," + wsCity + "," + formattedRecord.toString() + "," + 
                        String.format("%.3f", wsWinningPct));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}