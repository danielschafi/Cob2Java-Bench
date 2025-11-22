import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TeamRecordsRB {
    private static final String INPUT_FILE = "league.dat";
    private static final String OUTPUT_FILE = "leagueRfmtd.dat";
    
    private String wsDivisionCode;
    private String wsCity;
    private String wsRecord6;
    private double wsPct;
    
    private int wsRfmtWinsNum;
    private int wsRfmtLossesNum;
    private int wsRfmtTiesNum;
    
    private int wsTotalPtsAvailable;
    private int wsTotalPtsEarned;
    private double wsWinningPct;
    
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private boolean outOfData;
    private boolean fileError;
    
    public static void main(String[] args) {
        TeamRecordsRB program = new TeamRecordsRB();
        program.run();
    }
    
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        System.out.println("PROGRAM COMPILED: " + now.format(formatter));
        System.out.println("START: REFORMAT LEAGUE DATA");
        
        formatRecords();
        
        System.out.println("END: REFORMAT LEAGUE DATA");
    }
    
    private void formatRecords() {
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
            outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            
            readLeagueFile();
            
            while (!outOfData && !fileError) {
                processLeagueFile();
                readLeagueFile();
            }
            
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            fileError = true;
            e.printStackTrace();
        }
    }
    
    private void processLeagueFile() {
        try {
            String[] parts = wsDivisionCode.split(",");
            if (parts.length >= 3) {
                wsDivisionCode = parts[0].trim();
                wsCity = parts[1].trim();
                wsRecord6 = parts[2].trim();
            }
            
            String[] recordParts = wsRecord6.split("-");
            if (recordParts.length >= 3) {
                String winsStr = recordParts[0].trim();
                String lossesStr = recordParts[1].trim();
                String tiesStr = recordParts[2].trim();
                
                wsRfmtWinsNum = Integer.parseInt(winsStr);
                wsRfmtLossesNum = Integer.parseInt(lossesStr);
                wsRfmtTiesNum = Integer.parseInt(tiesStr);
            }
            
            wsTotalPtsAvailable = (wsRfmtLossesNum + wsRfmtWinsNum + wsRfmtTiesNum) * 2;
            wsTotalPtsEarned = (wsRfmtWinsNum * 2) + wsRfmtTiesNum;
            
            if (wsTotalPtsAvailable > 0) {
                wsWinningPct = (double) wsTotalPtsEarned / wsTotalPtsAvailable;
            } else {
                wsWinningPct = 0.0;
            }
            
            wsPct = wsWinningPct;
            
            String reformattedRecord = String.format("%02d-%02d-%02d", 
                wsRfmtWinsNum, wsRfmtLossesNum, wsRfmtTiesNum);
            
            String outputLine = String.format("%-2s%-15s%-6s%.3f", 
                wsDivisionCode, wsCity, reformattedRecord, wsPct);
            
            outputWriter.write(outputLine);
            outputWriter.newLine();
            
        } catch (IOException e) {
            fileError = true;
            e.printStackTrace();
        }
    }
    
    private void readLeagueFile() {
        try {
            String line = inputReader.readLine();
            if (line == null) {
                outOfData = true;
            } else {
                wsDivisionCode = line;
            }
        } catch (IOException e) {
            fileError = true;
            e.printStackTrace();
        }
    }
}