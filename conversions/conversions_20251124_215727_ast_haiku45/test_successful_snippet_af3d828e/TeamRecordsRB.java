import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TeamRecordsRB {
    private static String wsLeagueFileStatus = "00";
    private static String wsRfmtFileStatus = "00";
    
    private static String wsDivisionCode = "";
    private static String wsCity = "";
    private static String wsRecord6 = "";
    private static double wsPct = 0.0;
    
    private static String wsRfmtWins = "";
    private static String wsRfmtLosses = "";
    private static String wsRfmtTies = "";
    
    private static int wsRfmtWinsNum = 0;
    private static int wsRfmtLossesNum = 0;
    private static int wsRfmtTiesNum = 0;
    
    private static int wsTotalPtsAvailable = 0;
    private static int wsTotalPtsEarned = 0;
    private static double wsWinningPct = 0.0;
    
    private static BufferedReader leagueFileReader;
    private static BufferedWriter leagueFormattedFileWriter;
    private static String currentLeagueRecord;
    private static boolean outOfData = false;
    private static boolean fileError = false;

    public static void main(String[] args) {
        System.out.println("PROGRAM COMPILED: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("START: REFORMAT LEAGUE DATA");
        formatRecords();
        System.out.println("END: REFORMAT LEAGUE DATA");
    }

    private static void formatRecords() {
        try {
            leagueFileReader = new BufferedReader(new FileReader("league.dat"));
            leagueFormattedFileWriter = new BufferedWriter(new FileWriter("leagueRfmtd.dat"));
            
            readLeagueFile();
            
            while (!outOfData && !fileError) {
                processLeagueFile();
            }
            
            leagueFileReader.close();
            leagueFormattedFileWriter.close();
        } catch (IOException e) {
            fileError = true;
            e.printStackTrace();
        }
    }

    private static void processLeagueFile() {
        try {
            String[] parts = currentLeagueRecord.split(",");
            
            if (parts.length < 3) {
                System.out.println("OVERFLOW 1");
                readLeagueFile();
                return;
            }
            
            wsDivisionCode = parts[0].trim();
            wsCity = parts[1].trim();
            wsRecord6 = parts[2].trim();
            
            String[] recordParts = wsRecord6.split("-");
            
            if (recordParts.length < 3) {
                System.out.println("OVERFLOW");
                readLeagueFile();
                return;
            }
            
            System.out.println("NO OVERFLOW");
            
            wsRfmtWins = recordParts[0].trim();
            wsRfmtLosses = recordParts[1].trim();
            wsRfmtTies = recordParts[2].trim();
            
            wsRfmtWinsNum = Integer.parseInt(wsRfmtWins);
            wsRfmtLossesNum = Integer.parseInt(wsRfmtLosses);
            wsRfmtTiesNum = Integer.parseInt(wsRfmtTies);
            
            wsTotalPtsAvailable = (wsRfmtLossesNum + wsRfmtWinsNum + wsRfmtTiesNum) * 2;
            wsTotalPtsEarned = (wsRfmtWinsNum * 2) + wsRfmtTiesNum;
            
            if (wsTotalPtsAvailable > 0) {
                wsWinningPct = (double) wsTotalPtsEarned / wsTotalPtsAvailable;
            } else {
                wsWinningPct = 0.0;
            }
            
            wsPct = wsWinningPct;
            
            String formattedWins = String.format("%02d", wsRfmtWinsNum);
            String formattedLosses = String.format("%02d", wsRfmtLossesNum);
            String formattedTies = String.format("%02d", wsRfmtTiesNum);
            
            String formattedRecord = formattedWins + "-" + formattedLosses + "-" + formattedTies;
            
            String outputRecord = wsDivisionCode + "," + wsCity + "," + formattedRecord + "," + String.format("%.3f", wsPct);
            
            leagueFormattedFileWriter.write(outputRecord);
            leagueFormattedFileWriter.newLine();
            
            readLeagueFile();
        } catch (IOException e) {
            fileError = true;
            e.printStackTrace();
        }
    }

    private static void readLeagueFile() {
        try {
            currentLeagueRecord = leagueFileReader.readLine();
            if (currentLeagueRecord == null) {
                outOfData = true;
                wsLeagueFileStatus = "10";
            } else {
                wsLeagueFileStatus = "00";
            }
        } catch (IOException e) {
            fileError = true;
            wsLeagueFileStatus = "99";
            e.printStackTrace();
        }
    }
}